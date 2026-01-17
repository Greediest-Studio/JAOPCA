/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
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
/*    */ public class FileConfigBuilder
/*    */   extends GenericBuilder<Config, FileConfig>
/*    */ {
/*    */   FileConfigBuilder(Path file, ConfigFormat<? extends Config> format) {
/* 30 */     super(file, format);
/*    */   }
/*    */   
/*    */   protected FileConfig buildAutosave(FileConfig chain) {
/* 34 */     return new AutosaveFileConfig<>(chain);
/*    */   }
/*    */   
/*    */   protected FileConfig buildNormal(FileConfig chain) {
/* 38 */     return chain;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\FileConfigBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */