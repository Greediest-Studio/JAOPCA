/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
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
/*    */ class CheckedCommentedConfig
/*    */   extends CommentedConfigWrapper<CommentedConfig>
/*    */   implements CommentedConfig
/*    */ {
/*    */   CheckedCommentedConfig(CommentedConfig config) {
/* 28 */     super(config);
/* 29 */     config.valueMap().forEach((k, v) -> checkValue(v));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CommentedConfig checked() {
/* 36 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 41 */     return (T)super.set(path, checkedValue(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(List<String> path, Object value) {
/* 46 */     return super.add(path, checkedValue(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 51 */     return (Map<String, Object>)new TransformingMap(super.valueMap(), v -> v, this::checkedValue, o -> o);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends CommentedConfig.Entry> entrySet() {
/* 56 */     return (Set<? extends CommentedConfig.Entry>)new TransformingSet(super.entrySet(), v -> v, this::checkedValue, o -> o);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "checked " + this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void checkValue(Object value) {
/* 69 */     ConfigFormat<?> format = configFormat();
/* 70 */     if (value != null && !format.supportsType(value.getClass()))
/* 71 */       throw new IllegalArgumentException("Unsupported value type: " + value
/* 72 */           .getClass().getTypeName()); 
/* 73 */     if (value == null && !format.supportsType(null)) {
/* 74 */       throw new IllegalArgumentException("Null values aren't supported by this configuration.");
/*    */     }
/*    */     
/* 77 */     if (value instanceof Config) {
/* 78 */       ((Config)value).valueMap().forEach((k, v) -> checkValue(v));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private <T> T checkedValue(T value) {
/* 87 */     checkValue(value);
/* 88 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\CheckedCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */