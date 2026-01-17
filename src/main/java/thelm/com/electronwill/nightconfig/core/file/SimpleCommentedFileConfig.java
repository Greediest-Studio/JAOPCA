/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ class SimpleCommentedFileConfig
/*    */   extends CommentedConfigWrapper<CommentedConfig>
/*    */   implements CommentedFileConfig
/*    */ {
/*    */   private final FileConfig fileConfig;
/*    */   
/*    */   SimpleCommentedFileConfig(CommentedConfig config, FileConfig fileConfig) {
/* 17 */     super(config);
/* 18 */     this.fileConfig = fileConfig;
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 23 */     return this.fileConfig.getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 28 */     return this.fileConfig.getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 33 */     this.fileConfig.save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 38 */     this.fileConfig.load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 43 */     this.fileConfig.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\SimpleCommentedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */