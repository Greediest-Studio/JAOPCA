/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.StringUtils;
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
/*     */ 
/*     */ 
/*     */ public interface Config
/*     */   extends UnmodifiableConfig
/*     */ {
/*     */   default <T> T set(String path, Object value) {
/*  27 */     return set(StringUtils.split(path, '.'), value);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean add(String path, Object value) {
/*  59 */     return add(StringUtils.split(path, '.'), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void addAll(UnmodifiableConfig config) {
/*  68 */     for (UnmodifiableConfig.Entry ue : config.entrySet()) {
/*  69 */       List<String> key = Collections.singletonList(ue.getKey());
/*  70 */       Object value = ue.getRawValue();
/*  71 */       boolean existed = !add(key, value);
/*  72 */       if (!existed || value instanceof UnmodifiableConfig);
/*     */     } 
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
/*     */   default void putAll(UnmodifiableConfig config) {
/*  85 */     valueMap().putAll(config.valueMap());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> T remove(String path) {
/*  96 */     return remove(StringUtils.split(path, '.'));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void removeAll(UnmodifiableConfig config) {
/* 114 */     valueMap().keySet().removeAll(config.valueMap().keySet());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default UnmodifiableConfig unmodifiable() {
/* 130 */     return new UnmodifiableConfig()
/*     */       {
/*     */         public <T> T getRaw(List<String> path) {
/* 133 */           return Config.this.getRaw(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean contains(List<String> path) {
/* 138 */           return Config.this.contains(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public int size() {
/* 143 */           return Config.this.size();
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<String, Object> valueMap() {
/* 148 */           return Collections.unmodifiableMap(Config.this.valueMap());
/*     */         }
/*     */ 
/*     */         
/*     */         public Set<? extends UnmodifiableConfig.Entry> entrySet() {
/* 153 */           return (Set)Config.this.entrySet();
/*     */         }
/*     */ 
/*     */         
/*     */         public ConfigFormat<?> configFormat() {
/* 158 */           return Config.this.configFormat();
/*     */         }
/*     */       };
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
/*     */   
/*     */   default Config checked() {
/* 173 */     return (Config)new CheckedConfig(this);
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
/*     */ 
/*     */ 
/*     */   
/*     */   default void update(String path, Object value) {
/* 221 */     set(path, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void update(List<String> path, Object value) {
/* 232 */     set(path, value);
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
/*     */   static Config of(ConfigFormat<? extends Config> format) {
/* 244 */     return new SimpleConfig(format, false);
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
/*     */ 
/*     */   
/*     */   static Config of(Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> format) {
/* 258 */     return new SimpleConfig(mapCreator, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config ofConcurrent(ConfigFormat<? extends Config> format) {
/* 268 */     return new SimpleConfig(format, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config inMemory() {
/* 277 */     return InMemoryFormat.defaultInstance().createConfig();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config inMemoryUniversal() {
/* 286 */     return InMemoryFormat.withUniversalSupport().createConfig();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config inMemoryConcurrent() {
/* 295 */     return InMemoryFormat.defaultInstance().createConcurrentConfig();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config inMemoryUniversalConcurrent() {
/* 304 */     return InMemoryFormat.withUniversalSupport().createConcurrentConfig();
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
/*     */ 
/*     */ 
/*     */   
/*     */   static Config wrap(Map<String, Object> map, ConfigFormat<?> format) {
/* 319 */     return new SimpleConfig(map, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config copy(UnmodifiableConfig config) {
/* 330 */     return new SimpleConfig(config, config.configFormat(), false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator) {
/* 347 */     return new SimpleConfig(config, mapCreator, config.configFormat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config copy(UnmodifiableConfig config, ConfigFormat<?> format) {
/* 358 */     return new SimpleConfig(config, format, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> format) {
/* 376 */     return new SimpleConfig(config, mapCreator, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config concurrentCopy(UnmodifiableConfig config) {
/* 387 */     return new SimpleConfig(config, config.configFormat(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Config concurrentCopy(UnmodifiableConfig config, ConfigFormat<?> format) {
/* 398 */     return new SimpleConfig(config, format, true);
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
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isInsertionOrderPreserved() {
/* 413 */     String prop = System.getProperty("nightconfig.preserveInsertionOrder");
/* 414 */     return (prop != null && (prop.equals("true") || prop.equals("1")));
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
/*     */ 
/*     */ 
/*     */   
/*     */   static void setInsertionOrderPreserved(boolean orderPreserved) {
/* 429 */     System.setProperty("nightconfig.preserveInsertionOrder", orderPreserved ? "true" : "false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> Supplier<Map<String, T>> getDefaultMapCreator(boolean concurrent, boolean insertionOrderPreserved) {
/* 440 */     if (insertionOrderPreserved) {
/* 441 */       return concurrent ? (() -> Collections.synchronizedMap(new LinkedHashMap<>())) : LinkedHashMap::new;
/*     */     }
/*     */     
/* 444 */     return concurrent ? java.util.concurrent.ConcurrentHashMap::new : java.util.HashMap::new;
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
/*     */   static <T> Supplier<Map<String, T>> getDefaultMapCreator(boolean concurrent) {
/* 456 */     return getDefaultMapCreator(concurrent, isInsertionOrderPreserved());
/*     */   }
/*     */   
/*     */   <T> T set(List<String> paramList, Object paramObject);
/*     */   
/*     */   boolean add(List<String> paramList, Object paramObject);
/*     */   
/*     */   <T> T remove(List<String> paramList);
/*     */   
/*     */   void clear();
/*     */   
/*     */   Map<String, Object> valueMap();
/*     */   
/*     */   Set<? extends Entry> entrySet();
/*     */   
/*     */   Config createSubConfig();
/*     */   
/*     */   public static interface Entry extends UnmodifiableConfig.Entry {
/*     */     <T> T setValue(Object param1Object);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */