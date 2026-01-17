/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardWatchEventKinds;
/*     */ import java.nio.file.WatchEvent;
/*     */ import java.nio.file.WatchKey;
/*     */ import java.nio.file.WatchService;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.LockSupport;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FileWatcher
/*     */ {
/*     */   private static final long SLEEP_TIME_NANOS = 1000L;
/*     */   private static volatile FileWatcher DEFAULT_INSTANCE;
/*     */   
/*     */   public static synchronized FileWatcher defaultInstance() {
/*  33 */     if (DEFAULT_INSTANCE == null || !DEFAULT_INSTANCE.run) {
/*  34 */       DEFAULT_INSTANCE = new FileWatcher();
/*     */     }
/*  36 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*  39 */   private final Map<Path, WatchedDir> watchedDirs = new ConcurrentHashMap<>();
/*  40 */   private final Map<Path, WatchedFile> watchedFiles = new ConcurrentHashMap<>();
/*  41 */   private final Thread thread = new WatcherThread();
/*     */ 
/*     */   
/*     */   private final Consumer<Exception> exceptionHandler;
/*     */   
/*     */   private volatile boolean run = true;
/*     */ 
/*     */   
/*     */   public FileWatcher() {
/*  50 */     this(Throwable::printStackTrace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileWatcher(Consumer<Exception> exceptionHandler) {
/*  58 */     this.exceptionHandler = exceptionHandler;
/*  59 */     this.thread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWatch(File file, Runnable changeHandler) throws IOException {
/*  69 */     addWatch(file.toPath(), changeHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWatch(Path file, Runnable changeHandler) throws IOException {
/*  79 */     file = file.toAbsolutePath();
/*  80 */     Path dir = file.getParent();
/*  81 */     WatchedDir watchedDir = this.watchedDirs.computeIfAbsent(dir, k -> new WatchedDir(dir));
/*  82 */     WatchKey watchKey = dir.register(watchedDir.watchService, (WatchEvent.Kind<?>[])new WatchEvent.Kind[] { StandardWatchEventKinds.ENTRY_MODIFY });
/*     */     
/*  84 */     this.watchedFiles.computeIfAbsent(file, k -> new WatchedFile(watchedDir, watchKey, changeHandler));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWatch(File file, Runnable changeHandler) throws IOException {
/*  96 */     setWatch(file.toPath(), changeHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWatch(Path file, Runnable changeHandler) throws IOException {
/* 107 */     file = file.toAbsolutePath();
/* 108 */     WatchedFile watchedFile = this.watchedFiles.get(file);
/* 109 */     if (watchedFile == null) {
/* 110 */       addWatch(file, changeHandler);
/*     */     } else {
/* 112 */       watchedFile.changeHandler = changeHandler;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeWatch(File file) {
/* 122 */     removeWatch(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeWatch(Path file) {
/* 131 */     file = file.toAbsolutePath();
/* 132 */     Path dir = file.getParent();
/* 133 */     WatchedDir watchedDir = this.watchedDirs.get(dir);
/* 134 */     int remainingChildCount = watchedDir.watchedFileCount.decrementAndGet();
/* 135 */     if (remainingChildCount == 0) {
/* 136 */       this.watchedDirs.remove(dir);
/*     */     }
/* 138 */     WatchedFile watchedFile = this.watchedFiles.remove(file);
/* 139 */     if (watchedFile != null) {
/* 140 */       watchedFile.watchKey.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws IOException {
/* 149 */     this.run = false;
/*     */   }
/*     */   
/*     */   private final class WatcherThread extends Thread {
/*     */     private WatcherThread() {
/* 154 */       setDaemon(true);
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 159 */       while (FileWatcher.this.run) {
/* 160 */         boolean allNull = true;
/*     */         Iterator<FileWatcher.WatchedDir> it;
/* 162 */         label41: for (it = FileWatcher.this.watchedDirs.values().iterator(); it.hasNext() && FileWatcher.this.run; ) {
/* 163 */           FileWatcher.WatchedDir watchedDir = it.next();
/* 164 */           WatchKey key = watchedDir.watchService.poll();
/* 165 */           if (key == null) {
/*     */             continue;
/*     */           }
/* 168 */           allNull = false;
/* 169 */           for (WatchEvent<?> event : key.pollEvents()) {
/* 170 */             if (!FileWatcher.this.run) {
/*     */               break label41;
/*     */             }
/* 173 */             if (event.kind() != StandardWatchEventKinds.ENTRY_MODIFY || event.count() > 1) {
/*     */               continue;
/*     */             }
/* 176 */             Path childPath = (Path)event.context();
/* 177 */             Path filePath = watchedDir.dir.resolve(childPath);
/* 178 */             FileWatcher.WatchedFile watchedFile = (FileWatcher.WatchedFile)FileWatcher.this.watchedFiles.get(filePath);
/* 179 */             if (watchedFile != null) {
/*     */               try {
/* 181 */                 watchedFile.changeHandler.run();
/* 182 */               } catch (Exception e) {
/* 183 */                 FileWatcher.this.exceptionHandler.accept(e);
/*     */               } 
/*     */             }
/*     */           } 
/* 187 */           key.reset();
/*     */         } 
/* 189 */         if (allNull) {
/* 190 */           LockSupport.parkNanos(1000L);
/*     */         }
/*     */       } 
/*     */       
/* 194 */       for (FileWatcher.WatchedDir watchedDir : FileWatcher.this.watchedDirs.values()) {
/*     */         try {
/* 196 */           watchedDir.watchService.close();
/* 197 */         } catch (IOException e) {
/* 198 */           FileWatcher.this.exceptionHandler.accept(e);
/*     */         } 
/*     */       } 
/*     */       
/* 202 */       FileWatcher.this.watchedDirs.clear();
/* 203 */       FileWatcher.this.watchedFiles.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class WatchedDir
/*     */   {
/*     */     final Path dir;
/*     */     
/*     */     final WatchService watchService;
/* 213 */     final AtomicInteger watchedFileCount = new AtomicInteger();
/*     */     
/*     */     private WatchedDir(Path dir) {
/* 216 */       this.dir = dir;
/*     */       try {
/* 218 */         this.watchService = dir.getFileSystem().newWatchService();
/* 219 */       } catch (IOException e) {
/* 220 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class WatchedFile
/*     */   {
/*     */     final WatchKey watchKey;
/*     */     
/*     */     volatile Runnable changeHandler;
/*     */     
/*     */     private WatchedFile(FileWatcher.WatchedDir watchedDir, WatchKey watchKey, Runnable changeHandler) {
/* 233 */       this.watchKey = watchKey;
/* 234 */       this.changeHandler = changeHandler;
/* 235 */       watchedDir.watchedFileCount.getAndIncrement();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\FileWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */