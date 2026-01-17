/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.file.FileConfig;
/*    */ 
/*    */ public class ConvertedFileConfig
/*    */   extends AbstractConvertedConfig<FileConfig>
/*    */   implements FileConfig {
/*    */   public ConvertedFileConfig(FileConfig config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
/* 16 */     this(config, readTable::convert, writeTable::convert, supportPredicate);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ConvertedFileConfig(FileConfig config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/* 22 */     super(config, readConversion, writeConversion, supportPredicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 27 */     return ((FileConfig)this.config).getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 32 */     return ((FileConfig)this.config).getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 37 */     ((FileConfig)this.config).save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 42 */     ((FileConfig)this.config).load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 47 */     ((FileConfig)this.config).close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConvertedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */