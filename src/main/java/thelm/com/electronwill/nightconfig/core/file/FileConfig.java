/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface FileConfig
/*     */   extends Config, AutoCloseable
/*     */ {
/*     */   default FileConfig checked() {
/*  48 */     return new CheckedFileConfig(this);
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
/*     */   static FileConfig of(File file) {
/*  60 */     return of(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig of(File file, ConfigFormat<? extends Config> format) {
/*  71 */     return of(file.toPath(), format);
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
/*     */   static FileConfig of(Path file) {
/*  83 */     ConfigFormat<?> format = FormatDetector.detect(file);
/*  84 */     if (format == null) {
/*  85 */       throw new NoFormatFoundException("No suitable format for " + file.getFileName());
/*     */     }
/*  87 */     return of(file, (ConfigFormat)format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig of(Path file, ConfigFormat<? extends Config> format) {
/*  98 */     return builder(file, format).build();
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
/*     */   static FileConfig of(String filePath) {
/* 110 */     return of(Paths.get(filePath, new String[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig of(String filePath, ConfigFormat<?> format) {
/* 121 */     return of(Paths.get(filePath, new String[0]), (ConfigFormat)format);
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
/*     */   
/*     */   static FileConfig ofConcurrent(File file) {
/* 134 */     return ofConcurrent(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig ofConcurrent(File file, ConfigFormat<?> format) {
/* 145 */     return ofConcurrent(file.toPath(), format);
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
/*     */   
/*     */   static FileConfig ofConcurrent(Path file) {
/* 158 */     return builder(file).concurrent().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig ofConcurrent(Path file, ConfigFormat<?> format) {
/* 169 */     return builder(file, format).concurrent().build();
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
/*     */   
/*     */   static FileConfig ofConcurrent(String filePath) {
/* 182 */     return ofConcurrent(Paths.get(filePath, new String[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfig ofConcurrent(String filePath, ConfigFormat<?> format) {
/* 193 */     return ofConcurrent(Paths.get(filePath, new String[0]), format);
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
/*     */   
/*     */   static FileConfigBuilder builder(File file) {
/* 206 */     return builder(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfigBuilder builder(File file, ConfigFormat<?> format) {
/* 217 */     return builder(file.toPath(), format);
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
/*     */   
/*     */   static FileConfigBuilder builder(Path file) {
/* 230 */     ConfigFormat<?> format = FormatDetector.detect(file);
/* 231 */     if (format == null) {
/* 232 */       throw new NoFormatFoundException("No suitable format for " + file.getFileName());
/*     */     }
/* 234 */     return builder(file, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfigBuilder builder(Path file, ConfigFormat<?> format) {
/* 245 */     return new FileConfigBuilder(file, (ConfigFormat)format);
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
/*     */   
/*     */   static FileConfigBuilder builder(String filePath) {
/* 258 */     return builder(Paths.get(filePath, new String[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FileConfigBuilder builder(String filePath, ConfigFormat<?> format) {
/* 269 */     return builder(Paths.get(filePath, new String[0]), format);
/*     */   }
/*     */   
/*     */   File getFile();
/*     */   
/*     */   Path getNioPath();
/*     */   
/*     */   void save();
/*     */   
/*     */   void load();
/*     */   
/*     */   void close();
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\FileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */