/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface CommentedFileConfig
/*     */   extends CommentedConfig, FileConfig
/*     */ {
/*     */   default CommentedFileConfig checked() {
/*  16 */     return new CheckedCommentedFileConfig(this);
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
/*     */   static CommentedFileConfig of(File file) {
/*  29 */     return of(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig of(File file, ConfigFormat<? extends CommentedConfig> format) {
/*  40 */     return of(file.toPath(), format);
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
/*     */   static CommentedFileConfig of(Path file) {
/*  53 */     ConfigFormat<?> format = FormatDetector.detect(file);
/*  54 */     if (format == null || !format.supportsComments()) {
/*  55 */       throw new NoFormatFoundException("No suitable format for " + file.getFileName());
/*     */     }
/*  57 */     return of(file, (ConfigFormat)format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig of(Path file, ConfigFormat<? extends CommentedConfig> format) {
/*  68 */     return builder(file, format).build();
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
/*     */   static CommentedFileConfig of(String filePath) {
/*  81 */     return of(Paths.get(filePath, new String[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig of(String filePath, ConfigFormat<? extends CommentedConfig> format) {
/*  92 */     return of(Paths.get(filePath, new String[0]), format);
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
/*     */   static CommentedFileConfig ofConcurrent(File file) {
/* 105 */     return ofConcurrent(file.toPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig ofConcurrent(File file, ConfigFormat<? extends CommentedConfig> format) {
/* 116 */     return ofConcurrent(file.toPath(), format);
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
/*     */   static CommentedFileConfig ofConcurrent(Path file) {
/* 129 */     return builder(file).concurrent().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig ofConcurrent(Path file, ConfigFormat<? extends CommentedConfig> format) {
/* 140 */     return builder(file, format).concurrent().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedFileConfig ofConcurrent(String filePath, ConfigFormat<? extends CommentedConfig> format) {
/* 151 */     return ofConcurrent(Paths.get(filePath, new String[0]), format);
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
/*     */   static CommentedFileConfig ofConcurrent(String filePath) {
/* 164 */     return ofConcurrent(Paths.get(filePath, new String[0]));
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
/*     */   static CommentedFileConfigBuilder builder(File file, ConfigFormat<? extends CommentedConfig> format) {
/* 176 */     return builder(file.toPath(), format);
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
/*     */   
/*     */   static CommentedFileConfigBuilder builder(File file) {
/* 190 */     return builder(file.toPath());
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
/*     */   static CommentedFileConfigBuilder builder(Path file, ConfigFormat<? extends CommentedConfig> format) {
/* 202 */     return new CommentedFileConfigBuilder(file, format);
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
/*     */   
/*     */   static CommentedFileConfigBuilder builder(Path file) {
/* 216 */     ConfigFormat<?> format = FormatDetector.detect(file);
/* 217 */     if (format == null)
/* 218 */       throw new NoFormatFoundException("No suitable format for " + file.getFileName()); 
/* 219 */     if (!format.supportsComments()) {
/* 220 */       throw new NoFormatFoundException("The available format doesn't support comments for " + file
/* 221 */           .getFileName());
/*     */     }
/* 223 */     return builder(file, (ConfigFormat)format);
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
/*     */   
/*     */   static CommentedFileConfigBuilder builder(String filePath) {
/* 237 */     return builder(Paths.get(filePath, new String[0]));
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
/*     */   static CommentedFileConfigBuilder builder(String filePath, ConfigFormat<? extends CommentedConfig> format) {
/* 249 */     return builder(Paths.get(filePath, new String[0]), format);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\CommentedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */