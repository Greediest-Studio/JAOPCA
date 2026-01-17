/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
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
/*    */ public final class ConvertedCommentedConfig
/*    */   extends AbstractConvertedCommentedConfig<CommentedConfig>
/*    */ {
/*    */   public ConvertedCommentedConfig(CommentedConfig config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
/* 26 */     this(config, readTable::convert, writeTable::convert, supportPredicate);
/*    */   }
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
/*    */   public ConvertedCommentedConfig(CommentedConfig config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/* 41 */     super(config, readConversion, writeConversion, supportPredicate);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConvertedCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */