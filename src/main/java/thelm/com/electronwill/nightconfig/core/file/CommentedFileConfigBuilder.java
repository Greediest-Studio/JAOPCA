/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CommentedFileConfigBuilder
/*    */   extends GenericBuilder<CommentedConfig, CommentedFileConfig>
/*    */ {
/*    */   CommentedFileConfigBuilder(Path file, ConfigFormat<? extends CommentedConfig> format) {
/* 31 */     super(file, format);
/*    */   }
/*    */ 
/*    */   
/*    */   protected CommentedFileConfig buildAutosave(FileConfig chain) {
/* 36 */     return new AutosaveCommentedFileConfig(getConfig(), chain);
/*    */   }
/*    */ 
/*    */   
/*    */   protected CommentedFileConfig buildNormal(FileConfig chain) {
/* 41 */     return new SimpleCommentedFileConfig(getConfig(), chain);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\CommentedFileConfigBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */