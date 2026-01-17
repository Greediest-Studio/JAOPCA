/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ConvertedFormat<C extends Config, F extends ConfigFormat<C>>
/*    */   implements ConfigFormat<C>
/*    */ {
/*    */   private final F initialFormat;
/*    */   private final Predicate<Class<?>> supportPredicate;
/*    */   
/*    */   public ConvertedFormat(F initialFormat, Predicate<Class<?>> supportPredicate) {
/* 20 */     this.initialFormat = initialFormat;
/* 21 */     this.supportPredicate = supportPredicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigWriter createWriter() {
/* 26 */     return this.initialFormat.createWriter();
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigParser<C> createParser() {
/* 31 */     return this.initialFormat.createParser();
/*    */   }
/*    */ 
/*    */   
/*    */   public C createConfig() {
/* 36 */     return (C)this.initialFormat.createConfig();
/*    */   }
/*    */ 
/*    */   
/*    */   public C createConcurrentConfig() {
/* 41 */     return (C)this.initialFormat.createConcurrentConfig();
/*    */   }
/*    */ 
/*    */   
/*    */   public C createConfig(Supplier<Map<String, Object>> mapCreator) {
/* 46 */     return (C)this.initialFormat.createConfig(mapCreator);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsComments() {
/* 51 */     return this.initialFormat.supportsComments();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsType(Class<?> type) {
/* 56 */     return this.supportPredicate.test(type);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConvertedFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */