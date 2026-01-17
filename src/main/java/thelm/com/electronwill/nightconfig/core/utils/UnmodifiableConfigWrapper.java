/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class UnmodifiableConfigWrapper<C extends UnmodifiableConfig>
/*    */   implements UnmodifiableConfig
/*    */ {
/*    */   protected final C config;
/*    */   
/*    */   protected UnmodifiableConfigWrapper(C config) {
/* 19 */     this.config = (C)Objects.<UnmodifiableConfig>requireNonNull((UnmodifiableConfig)config, "The wrapped config must not be null");
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T getRaw(List<String> path) {
/* 24 */     return (T)this.config.getRaw(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 29 */     return this.config.valueMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends UnmodifiableConfig.Entry> entrySet() {
/* 34 */     return this.config.entrySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean contains(List<String> path) {
/* 39 */     return this.config.contains(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 44 */     return this.config.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 49 */     return this.config.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 54 */     return this.config.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 59 */     return this.config.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigFormat<?> configFormat() {
/* 64 */     return this.config.configFormat();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\UnmodifiableConfigWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */