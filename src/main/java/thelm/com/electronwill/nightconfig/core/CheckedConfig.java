/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.TransformingMap;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
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
/*    */ class CheckedConfig
/*    */   extends ConfigWrapper<Config>
/*    */ {
/*    */   CheckedConfig(Config config) {
/* 27 */     super(config);
/* 28 */     config.valueMap().forEach((k, v) -> checkValue(v));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Config checked() {
/* 34 */     return (Config)this;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 39 */     return (T)super.set(path, checkedValue(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(List<String> path, Object value) {
/* 44 */     return super.add(path, checkedValue(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 49 */     return (Map<String, Object>)new TransformingMap(super.valueMap(), v -> v, this::checkedValue, o -> o);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends Config.Entry> entrySet() {
/* 54 */     return (Set<? extends Config.Entry>)new TransformingSet(super.entrySet(), v -> v, this::checkedValue, o -> o);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return "checked of " + this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void checkValue(Object value) {
/* 67 */     ConfigFormat<?> format = configFormat();
/* 68 */     if (value != null && !format.supportsType(value.getClass()))
/* 69 */       throw new IllegalArgumentException("Unsupported value type: " + value
/* 70 */           .getClass().getTypeName()); 
/* 71 */     if (value == null && !format.supportsType(null)) {
/* 72 */       throw new IllegalArgumentException("Null values aren't supported by this configuration.");
/*    */     }
/*    */     
/* 75 */     if (value instanceof Config) {
/* 76 */       ((Config)value).valueMap().forEach((k, v) -> checkValue(v));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private <T> T checkedValue(T value) {
/* 85 */     checkValue(value);
/* 86 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\CheckedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */