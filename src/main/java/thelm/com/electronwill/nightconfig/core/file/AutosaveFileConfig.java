/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ObservedMap;
/*    */ 
/*    */ final class AutosaveFileConfig<C extends FileConfig>
/*    */   extends ConfigWrapper<C>
/*    */   implements FileConfig
/*    */ {
/*    */   AutosaveFileConfig(C config) {
/* 16 */     super((Config)config);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 21 */     T result = (T)super.set(path, value);
/* 22 */     save();
/* 23 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(List<String> path, Object value) {
/* 28 */     boolean result = super.add(path, value);
/* 29 */     save();
/* 30 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T remove(List<String> path) {
/* 35 */     T result = (T)super.remove(path);
/* 36 */     save();
/* 37 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 42 */     return (Map<String, Object>)new ObservedMap(super.valueMap(), this::save);
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 47 */     return ((FileConfig)this.config).getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 52 */     return ((FileConfig)this.config).getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 57 */     ((FileConfig)this.config).save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 62 */     ((FileConfig)this.config).load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 67 */     ((FileConfig)this.config).close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\AutosaveFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */