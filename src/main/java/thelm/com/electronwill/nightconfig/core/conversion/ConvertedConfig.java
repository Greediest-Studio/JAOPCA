/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
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
/*    */ public final class ConvertedConfig
/*    */   extends AbstractConvertedConfig<Config>
/*    */ {
/*    */   public ConvertedConfig(Config config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
/* 27 */     this(config, readTable::convert, writeTable::convert, supportPredicate);
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
/*    */   public ConvertedConfig(Config config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/* 42 */     super(config, readConversion, writeConversion, supportPredicate);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<? extends Config.Entry> entrySet() {
/* 48 */     Function<Config.Entry, Config.Entry> readTransfo = entry -> new Config.Entry()
/*    */       {
/*    */         public Object setValue(Object value) {
/* 51 */           return ConvertedConfig.this.readConversion.apply(entry.setValue(ConvertedConfig.this.writeConversion.apply(value)));
/*    */         }
/*    */ 
/*    */         
/*    */         public String getKey() {
/* 56 */           return entry.getKey();
/*    */         }
/*    */ 
/*    */         
/*    */         public <T> T getRawValue() {
/* 61 */           return (T)ConvertedConfig.this.readConversion.apply(entry.getRawValue());
/*    */         }
/*    */       };
/* 64 */     return (Set<? extends Config.Entry>)new TransformingSet(((Config)this.config).entrySet(), readTransfo, o -> null, e -> e);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConvertedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */