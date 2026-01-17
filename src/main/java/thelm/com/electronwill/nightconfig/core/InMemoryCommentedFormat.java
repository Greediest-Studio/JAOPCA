/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InMemoryCommentedFormat
/*    */   implements ConfigFormat<CommentedConfig>
/*    */ {
/* 16 */   private static final InMemoryCommentedFormat DEFAULT_INSTANCE = new InMemoryCommentedFormat(InMemoryFormat.DEFAULT_PREDICATE);
/* 17 */   private static final InMemoryCommentedFormat UNIVERSAL_INSTANCE = new InMemoryCommentedFormat(t -> true);
/*    */   
/*    */   public static InMemoryCommentedFormat defaultInstance() {
/* 20 */     return DEFAULT_INSTANCE;
/*    */   }
/*    */   private final Predicate<Class<?>> supportPredicate;
/*    */   public static InMemoryCommentedFormat withSupport(Predicate<Class<?>> supportPredicate) {
/* 24 */     return new InMemoryCommentedFormat(supportPredicate);
/*    */   }
/*    */   
/*    */   public static InMemoryCommentedFormat withUniversalSupport() {
/* 28 */     return UNIVERSAL_INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private InMemoryCommentedFormat(Predicate<Class<?>> supportPredicate) {
/* 34 */     this.supportPredicate = supportPredicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigWriter createWriter() {
/* 39 */     throw new UnsupportedOperationException("In memory configurations aren't meant to be written.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ConfigParser<CommentedConfig> createParser() {
/* 45 */     throw new UnsupportedOperationException("In memory configurations aren't meant to be parsed.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CommentedConfig createConfig(Supplier<Map<String, Object>> mapCreator) {
/* 51 */     return CommentedConfig.of(mapCreator, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsComments() {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsType(Class<?> type) {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInMemory() {
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\InMemoryCommentedFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */