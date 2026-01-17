/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ObservedMap;
/*    */ 
/*    */ 
/*    */ final class AutosaveCommentedFileConfig
/*    */   extends CommentedConfigWrapper<CommentedConfig>
/*    */   implements CommentedFileConfig
/*    */ {
/*    */   private final FileConfig fileConfig;
/*    */   
/*    */   AutosaveCommentedFileConfig(CommentedConfig config, FileConfig fileConfig) {
/* 19 */     super(config);
/* 20 */     this.fileConfig = fileConfig;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 25 */     T result = (T)super.set(path, value);
/* 26 */     save();
/* 27 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(List<String> path, Object value) {
/* 32 */     boolean result = super.add(path, value);
/* 33 */     save();
/* 34 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T remove(List<String> path) {
/* 39 */     T result = (T)super.remove(path);
/* 40 */     save();
/* 41 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public String setComment(List<String> path, String comment) {
/* 46 */     String result = super.setComment(path, comment);
/* 47 */     save();
/* 48 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public String removeComment(List<String> path) {
/* 53 */     String result = super.removeComment(path);
/* 54 */     save();
/* 55 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 60 */     return (Map<String, Object>)new ObservedMap(super.valueMap(), this::save);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> commentMap() {
/* 65 */     return (Map<String, String>)new ObservedMap(super.commentMap(), this::save);
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 70 */     return this.fileConfig.getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 75 */     return this.fileConfig.getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 80 */     this.fileConfig.save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 85 */     this.fileConfig.load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 90 */     this.fileConfig.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\AutosaveCommentedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */