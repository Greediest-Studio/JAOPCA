/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.channels.AsynchronousFileChannel;
/*     */ import java.nio.channels.CompletionHandler;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingMode;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WritingException;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WritingMode;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*     */ 
/*     */ final class WriteAsyncFileConfig<C extends Config>
/*     */   extends ConfigWrapper<C>
/*     */   implements FileConfig {
/*     */   private final Path nioPath;
/*     */   private final Charset charset;
/*  29 */   private final AtomicBoolean closed = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */   
/*     */   private AsynchronousFileChannel channel;
/*     */ 
/*     */ 
/*     */   
/*  37 */   private final Object channelGuard = new Object();
/*     */ 
/*     */ 
/*     */   
/*  41 */   private final AtomicBoolean currentlyWriting = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */   
/*  45 */   private final AtomicBoolean mustWriteAgain = new AtomicBoolean();
/*     */   
/*     */   private final ConfigWriter writer;
/*     */   
/*     */   private final WriteCompletedHandler writeCompletedHandler;
/*     */   
/*     */   private final OpenOption[] openOptions;
/*     */   
/*     */   private final ConfigParser<?> parser;
/*     */   private final FileNotFoundAction nefAction;
/*     */   private final ParsingMode parsingMode;
/*     */   
/*     */   WriteAsyncFileConfig(C config, Path nioPath, Charset charset, ConfigWriter writer, WritingMode writingMode, ConfigParser<?> parser, ParsingMode parsingMode, FileNotFoundAction nefAction) {
/*  58 */     super((Config)config);
/*  59 */     this.nioPath = nioPath;
/*  60 */     this.charset = charset;
/*  61 */     this.writer = writer;
/*  62 */     this.parser = parser;
/*  63 */     this.parsingMode = parsingMode;
/*  64 */     this.nefAction = nefAction;
/*  65 */     if (writingMode == WritingMode.APPEND) {
/*  66 */       this.openOptions = new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE };
/*     */     } else {
/*  68 */       this.openOptions = new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING };
/*     */     } 
/*  70 */     this.writeCompletedHandler = new WriteCompletedHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   public File getFile() {
/*  75 */     return this.nioPath.toFile();
/*     */   }
/*     */ 
/*     */   
/*     */   public Path getNioPath() {
/*  80 */     return this.nioPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/*  85 */     if (this.closed.get()) {
/*  86 */       throw new IllegalStateException("Cannot save a closed FileConfig");
/*     */     }
/*  88 */     save(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  93 */     if (this.closed.compareAndSet(false, true)) {
/*  94 */       synchronized (this.channelGuard) {
/*  95 */         while (this.currentlyWriting.get()) {
/*     */           
/*     */           try {
/*  98 */             this.channelGuard.wait();
/*  99 */           } catch (InterruptedException e) {
/* 100 */             Thread.currentThread().interrupt();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void save(boolean saveLaterIfWriting) {
/* 110 */     boolean canSaveNow = this.currentlyWriting.compareAndSet(false, true);
/* 111 */     if (canSaveNow) {
/*     */       
/* 113 */       CharsWrapper.Builder builder = new CharsWrapper.Builder(512);
/* 114 */       this.writer.write(this.config, (Writer)builder);
/* 115 */       CharBuffer chars = CharBuffer.wrap((CharSequence)builder.build());
/* 116 */       ByteBuffer buffer = this.charset.encode(chars);
/*     */ 
/*     */       
/* 119 */       synchronized (this.channelGuard) {
/*     */         try {
/* 121 */           this.channel = AsynchronousFileChannel.open(this.nioPath, this.openOptions);
/* 122 */           this.channel.write(buffer, this.channel.size(), null, this.writeCompletedHandler);
/* 123 */         } catch (IOException e) {
/* 124 */           this.writeCompletedHandler.failed(e, null);
/*     */         } 
/*     */       } 
/* 127 */     } else if (saveLaterIfWriting) {
/* 128 */       this.mustWriteAgain.set(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() {
/* 134 */     if (this.closed.get()) {
/* 135 */       throw new IllegalStateException("Cannot (re)load a closed FileConfig");
/*     */     }
/* 137 */     if (!this.currentlyWriting.get())
/* 138 */       this.parser.parse(this.nioPath, (Config)this.config, this.parsingMode, this.nefAction); 
/*     */   }
/*     */   
/*     */   private final class WriteCompletedHandler implements CompletionHandler<Integer, Object> {
/*     */     private WriteCompletedHandler() {}
/*     */     
/*     */     public void completed(Integer result, Object attachment) {
/* 145 */       WriteAsyncFileConfig.this.currentlyWriting.set(false);
/* 146 */       if (WriteAsyncFileConfig.this.mustWriteAgain.getAndSet(false)) {
/* 147 */         WriteAsyncFileConfig.this.save(false);
/*     */       }
/*     */       else {
/*     */         
/* 151 */         synchronized (WriteAsyncFileConfig.this.channelGuard) {
/*     */           try {
/* 153 */             WriteAsyncFileConfig.this.channel.close();
/* 154 */             WriteAsyncFileConfig.this.channel = null;
/* 155 */           } catch (IOException e) {
/* 156 */             failed(e, null);
/*     */           } finally {
/* 158 */             WriteAsyncFileConfig.this.channelGuard.notify();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void failed(Throwable exc, Object attachment) {
/* 166 */       throw new WritingException("Error while saving the FileConfig to " + WriteAsyncFileConfig.this.nioPath, exc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\WriteAsyncFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */