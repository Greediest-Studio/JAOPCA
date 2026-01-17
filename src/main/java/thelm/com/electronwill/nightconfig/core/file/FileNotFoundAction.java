/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.NoSuchFileException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @FunctionalInterface
/*     */ public interface FileNotFoundAction
/*     */ {
/*     */   public static final FileNotFoundAction CREATE_EMPTY;
/*     */   
/*     */   static {
/*  33 */     CREATE_EMPTY = ((f, c) -> {
/*     */         Files.createFile(f, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         c.initEmptyFile(f);
/*     */         return false;
/*     */       });
/*     */   } static {
/*  39 */     THROW_ERROR = ((f, c) -> {
/*     */         throw new NoSuchFileException(f.toAbsolutePath().toString());
/*     */       });
/*     */   }
/*     */ 
/*     */   
/*     */   public static final FileNotFoundAction READ_NOTHING = (f, c) -> false;
/*     */   
/*     */   public static final FileNotFoundAction THROW_ERROR;
/*     */   
/*     */   static FileNotFoundAction copyData(URL url) {
/*  50 */     return (f, c) -> {
/*     */         Files.copy(url.openStream(), f, new java.nio.file.CopyOption[0]);
/*     */         return true;
/*     */       };
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
/*     */   static FileNotFoundAction copyData(File file) {
/*  65 */     return (f, c) -> {
/*     */         Files.copy(new FileInputStream(file), f, new java.nio.file.CopyOption[0]);
/*     */         return true;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileNotFoundAction copyData(Path file) {
/*  78 */     return (f, c) -> {
/*     */         Files.copy(file, f, new java.nio.file.CopyOption[0]);
/*     */         return true;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileNotFoundAction copyData(InputStream data) {
/*  91 */     return (f, c) -> {
/*     */         Files.copy(data, f, new java.nio.file.CopyOption[0]);
/*     */         return true;
/*     */       };
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
/*     */   static FileNotFoundAction copyResource(String resourcePath) {
/* 106 */     return copyData(FileNotFoundAction.class.getResource(resourcePath));
/*     */   }
/*     */   
/*     */   boolean run(Path paramPath, ConfigFormat<?> paramConfigFormat) throws IOException;
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\FileNotFoundAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */