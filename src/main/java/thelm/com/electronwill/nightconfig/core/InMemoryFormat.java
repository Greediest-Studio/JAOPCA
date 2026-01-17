/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*    */ 
/*    */ 
/*    */ public final class InMemoryFormat
/*    */   implements ConfigFormat<Config>
/*    */ {
/*    */   static final Predicate<Class<?>> DEFAULT_PREDICATE;
/*    */   
/*    */   static {
/* 17 */     DEFAULT_PREDICATE = (type -> 
/* 18 */       (type == null || type.isPrimitive() || type == Integer.class || type == Long.class || type == Float.class || type == Double.class || type == Boolean.class || type == String.class || type == NullObject.class || Collection.class.isAssignableFrom(type) || Config.class.isAssignableFrom(type) || Enum.class.isAssignableFrom(type)));
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
/* 31 */   private static final InMemoryFormat DEFAULT_INSTANCE = new InMemoryFormat(DEFAULT_PREDICATE);
/* 32 */   private static final InMemoryFormat UNIVERSAL_INSTANCE = new InMemoryFormat(t -> true);
/*    */   
/*    */   public static InMemoryFormat defaultInstance() {
/* 35 */     return DEFAULT_INSTANCE;
/*    */   }
/*    */   private final Predicate<Class<?>> supportPredicate;
/*    */   public static InMemoryFormat withSupport(Predicate<Class<?>> supportPredicate) {
/* 39 */     return new InMemoryFormat(supportPredicate);
/*    */   }
/*    */   
/*    */   public static InMemoryFormat withUniversalSupport() {
/* 43 */     return UNIVERSAL_INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private InMemoryFormat(Predicate<Class<?>> supportPredicate) {
/* 49 */     this.supportPredicate = supportPredicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigWriter createWriter() {
/* 54 */     throw new UnsupportedOperationException("In memory configurations aren't meant to be written.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ConfigParser<Config> createParser() {
/* 60 */     throw new UnsupportedOperationException("In memory configurations aren't meant to be parsed.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Config createConfig(Supplier<Map<String, Object>> mapCreator) {
/* 66 */     return Config.of(mapCreator, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsComments() {
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsType(Class<?> type) {
/* 76 */     return this.supportPredicate.test(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInMemory() {
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\InMemoryFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */