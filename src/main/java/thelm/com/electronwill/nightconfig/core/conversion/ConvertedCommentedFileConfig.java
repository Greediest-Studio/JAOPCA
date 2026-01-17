/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ 
/*    */ public final class ConvertedCommentedFileConfig
/*    */   extends AbstractConvertedCommentedConfig<CommentedFileConfig>
/*    */   implements CommentedFileConfig
/*    */ {
/*    */   public ConvertedCommentedFileConfig(CommentedFileConfig config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
/* 20 */     this(config, readTable::convert, writeTable::convert, supportPredicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConvertedCommentedFileConfig(CommentedFileConfig config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/* 27 */     super(config, readConversion, writeConversion, supportPredicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 32 */     return ((CommentedFileConfig)this.config).getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 37 */     return ((CommentedFileConfig)this.config).getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 42 */     ((CommentedFileConfig)this.config).save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 47 */     ((CommentedFileConfig)this.config).load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 52 */     ((CommentedFileConfig)this.config).close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConvertedCommentedFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */