/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.TransformingMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class AbstractConvertedConfig<C extends Config>
/*    */   extends ConfigWrapper<C>
/*    */   implements Config
/*    */ {
/*    */   final Function<Object, Object> readConversion;
/*    */   final Function<Object, Object> writeConversion;
/*    */   final Predicate<Class<?>> supportPredicate;
/*    */   final ConfigFormat<?> format;
/*    */   
/*    */   AbstractConvertedConfig(C config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/* 26 */     super((Config)config);
/* 27 */     this.readConversion = readConversion;
/* 28 */     this.writeConversion = writeConversion;
/* 29 */     this.supportPredicate = supportPredicate;
/* 30 */     this.format = new ConvertedFormat<>(config.configFormat(), supportPredicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T set(List<String> path, Object value) {
/* 35 */     return (T)this.readConversion.apply(((Config)this.config).set(path, this.writeConversion.apply(value)));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Object> valueMap() {
/* 40 */     return (Map<String, Object>)new TransformingMap(((Config)this.config).valueMap(), this.readConversion, this.writeConversion, this.writeConversion);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> T getRaw(List<String> path) {
/* 46 */     return (T)this.readConversion.apply(((Config)this.config).getRaw(path));
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigFormat<?> configFormat() {
/* 51 */     return this.format;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return getClass().getSimpleName() + ':' + valueMap() + " (original: " + this.config + ')';
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\AbstractConvertedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */