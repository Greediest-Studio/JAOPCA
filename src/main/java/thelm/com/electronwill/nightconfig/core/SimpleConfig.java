/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class SimpleConfig
/*    */   extends AbstractConfig
/*    */ {
/*    */   private final ConfigFormat<?> configFormat;
/*    */   
/*    */   SimpleConfig(ConfigFormat<?> configFormat, boolean concurrent) {
/* 19 */     super(concurrent);
/* 20 */     this.configFormat = configFormat;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SimpleConfig(Map<String, Object> map, ConfigFormat<?> configFormat) {
/* 31 */     super(map);
/* 32 */     this.configFormat = configFormat;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SimpleConfig(Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> configFormat) {
/* 42 */     super(mapCreator);
/* 43 */     this.configFormat = configFormat;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SimpleConfig(UnmodifiableConfig toCopy, ConfigFormat<?> configFormat, boolean concurrent) {
/* 54 */     super(toCopy, concurrent);
/* 55 */     this.configFormat = configFormat;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SimpleConfig(UnmodifiableConfig toCopy, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> configFormat) {
/* 66 */     super(toCopy, mapCreator);
/* 67 */     this.configFormat = configFormat;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigFormat<?> configFormat() {
/* 72 */     return this.configFormat;
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleConfig createSubConfig() {
/* 77 */     return new SimpleConfig(this.mapCreator, this.configFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleConfig clone() {
/* 82 */     return new SimpleConfig(this, this.mapCreator, this.configFormat);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\SimpleConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */