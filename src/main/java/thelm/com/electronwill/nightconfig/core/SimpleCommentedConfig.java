/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class SimpleCommentedConfig
/*     */   extends AbstractCommentedConfig
/*     */ {
/*     */   private final ConfigFormat<?> configFormat;
/*     */   
/*     */   SimpleCommentedConfig(ConfigFormat<?> configFormat, boolean concurrent) {
/*  24 */     super(concurrent ? new ConcurrentHashMap<>() : new HashMap<>());
/*  25 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleCommentedConfig(Map<String, Object> valueMap, ConfigFormat<?> configFormat) {
/*  33 */     super(valueMap);
/*  34 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleCommentedConfig(Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> configFormat) {
/*  44 */     super(mapCreator);
/*  45 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleCommentedConfig(UnmodifiableConfig toCopy, ConfigFormat<?> configFormat, boolean concurrent) {
/*  56 */     super(toCopy, concurrent);
/*  57 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleCommentedConfig(UnmodifiableConfig toCopy, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> configFormat) {
/*  69 */     super(toCopy, mapCreator);
/*  70 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleCommentedConfig(UnmodifiableCommentedConfig toCopy, ConfigFormat<?> configFormat, boolean concurrent) {
/*  81 */     super(toCopy, concurrent);
/*  82 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleCommentedConfig(UnmodifiableCommentedConfig toCopy, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> configFormat) {
/*  94 */     super(toCopy, mapCreator);
/*  95 */     this.configFormat = configFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigFormat<?> configFormat() {
/* 100 */     return this.configFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommentedConfig createSubConfig() {
/* 105 */     return new SimpleCommentedConfig(this.mapCreator, this.configFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCommentedConfig clone() {
/* 110 */     return new SimpleCommentedConfig(this, this.mapCreator, this.configFormat);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\SimpleCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */