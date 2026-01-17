/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ConfigWrapper<C extends Config>
/*    */   extends UnmodifiableConfigWrapper<C>
/*    */   implements Config
/*    */ {
/*    */   protected ConfigWrapper(C config) {
/* 16 */     super(config);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends Config.Entry> entrySet() {
/* 21 */     return ((Config)this.config).entrySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 26 */     return (T)((Config)this.config).set(path, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(List<String> path, Object value) {
/* 31 */     return ((Config)this.config).add(path, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T remove(List<String> path) {
/* 36 */     return (T)((Config)this.config).remove(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 41 */     ((Config)this.config).clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public Config createSubConfig() {
/* 46 */     return ((Config)this.config).createSubConfig();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     return getClass().getSimpleName() + ':' + this.config;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\ConfigWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */