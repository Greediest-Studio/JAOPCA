/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.nio.file.Path;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingMap;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
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
/*     */ class CheckedCommentedFileConfig
/*     */   extends CommentedConfigWrapper<CommentedFileConfig>
/*     */   implements CommentedFileConfig
/*     */ {
/*     */   CheckedCommentedFileConfig(CommentedFileConfig config) {
/*  29 */     super(config);
/*     */   }
/*     */ 
/*     */   
/*     */   public Path getNioPath() {
/*  34 */     return ((CommentedFileConfig)this.config).getNioPath();
/*     */   }
/*     */ 
/*     */   
/*     */   public File getFile() {
/*  39 */     return ((CommentedFileConfig)this.config).getFile();
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/*  44 */     ((CommentedFileConfig)this.config).save();
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() {
/*  49 */     ((CommentedFileConfig)this.config).load();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  54 */     ((CommentedFileConfig)this.config).close();
/*     */   }
/*     */ 
/*     */   
/*     */   public CommentedFileConfig checked() {
/*  59 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T set(List<String> path, Object value) {
/*  64 */     return (T)super.set(path, checkedValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(List<String> path, Object value) {
/*  69 */     return super.add(path, checkedValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> valueMap() {
/*  74 */     return (Map<String, Object>)new TransformingMap(super.valueMap(), v -> v, this::checkedValue, o -> o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<? extends CommentedConfig.Entry> entrySet() {
/*  79 */     return (Set<? extends CommentedConfig.Entry>)new TransformingSet(super.entrySet(), v -> v, this::checkedValue, o -> o);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  84 */     return "checked of " + this.config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkValue(Object value) {
/*  92 */     ConfigFormat<?> format = configFormat();
/*  93 */     if (value != null && !format.supportsType(value.getClass()))
/*  94 */       throw new IllegalArgumentException("Unsupported value type: " + value
/*  95 */           .getClass().getTypeName()); 
/*  96 */     if (value == null && !format.supportsType(null)) {
/*  97 */       throw new IllegalArgumentException("Null values aren't supported by this configuration.");
/*     */     }
/*     */     
/* 100 */     if (value instanceof Config) {
/* 101 */       ((Config)value).valueMap().forEach((k, v) -> checkValue(v));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> T checkedValue(T value) {
/* 110 */     checkValue(value);
/* 111 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\CheckedCommentedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */