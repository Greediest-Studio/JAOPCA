/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.nio.file.Path;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
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
/*     */ class CheckedFileConfig
/*     */   extends ConfigWrapper<FileConfig>
/*     */   implements FileConfig
/*     */ {
/*     */   CheckedFileConfig(FileConfig config) {
/*  27 */     super(config);
/*     */   }
/*     */ 
/*     */   
/*     */   public Path getNioPath() {
/*  32 */     return ((FileConfig)this.config).getNioPath();
/*     */   }
/*     */ 
/*     */   
/*     */   public File getFile() {
/*  37 */     return ((FileConfig)this.config).getFile();
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/*  42 */     ((FileConfig)this.config).save();
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() {
/*  47 */     ((FileConfig)this.config).load();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  52 */     ((FileConfig)this.config).close();
/*     */   }
/*     */ 
/*     */   
/*     */   public FileConfig checked() {
/*  57 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T set(List<String> path, Object value) {
/*  62 */     return (T)super.set(path, checkedValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(List<String> path, Object value) {
/*  67 */     return super.add(path, checkedValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> valueMap() {
/*  72 */     return (Map<String, Object>)new TransformingMap(super.valueMap(), v -> v, this::checkedValue, o -> o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<? extends Config.Entry> entrySet() {
/*  77 */     return (Set<? extends Config.Entry>)new TransformingSet(super.entrySet(), v -> v, this::checkedValue, o -> o);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  82 */     return "checked of " + this.config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkValue(Object value) {
/*  90 */     ConfigFormat<?> format = configFormat();
/*  91 */     if (value != null && !format.supportsType(value.getClass()))
/*  92 */       throw new IllegalArgumentException("Unsupported value type: " + value
/*  93 */           .getClass().getTypeName()); 
/*  94 */     if (value == null && !format.supportsType(null)) {
/*  95 */       throw new IllegalArgumentException("Null values aren't supported by this configuration.");
/*     */     }
/*     */     
/*  98 */     if (value instanceof Config) {
/*  99 */       ((Config)value).valueMap().forEach((k, v) -> checkValue(v));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> T checkedValue(T value) {
/* 108 */     checkValue(value);
/* 109 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\CheckedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */