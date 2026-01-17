/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
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
/*     */ public abstract class AbstractConfig
/*     */   implements Config, Cloneable
/*     */ {
/*     */   protected final Supplier<Map<String, Object>> mapCreator;
/*     */   final Map<String, Object> map;
/*     */   
/*     */   public AbstractConfig(boolean concurrent) {
/*  30 */     this(getDefaultMapCreator(concurrent));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractConfig(Supplier<Map<String, Object>> mapCreator) {
/*  39 */     this.mapCreator = mapCreator;
/*  40 */     this.map = mapCreator.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractConfig(Map<String, Object> map) {
/*  49 */     this.map = map;
/*  50 */     this.mapCreator = getDefaultMapCreator(map instanceof java.util.concurrent.ConcurrentMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractConfig(UnmodifiableConfig toCopy, boolean concurrent) {
/*  59 */     this(toCopy, getDefaultMapCreator(concurrent));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractConfig(UnmodifiableConfig toCopy, Supplier<Map<String, Object>> mapCreator) {
/*  70 */     this.map = mapCreator.get();
/*  71 */     this.map.putAll(toCopy.valueMap());
/*  72 */     this.mapCreator = mapCreator;
/*     */   }
/*     */   
/*     */   protected static <T> Supplier<Map<String, T>> getDefaultMapCreator(boolean concurrent) {
/*  76 */     return Config.getDefaultMapCreator(concurrent);
/*     */   }
/*     */   
/*     */   protected static <T> Supplier<Map<String, T>> getWildcardMapCreator(Supplier<Map<String, Object>> mapCreator) {
/*  80 */     return () -> {
/*     */         Map<String, Object> map = mapCreator.get();
/*     */         map.clear();
/*     */         return map;
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getRaw(List<String> path) {
/*  89 */     int lastIndex = path.size() - 1;
/*  90 */     Map<String, Object> parentMap = getMap(path.subList(0, lastIndex));
/*  91 */     if (parentMap == null) {
/*  92 */       return null;
/*     */     }
/*  94 */     String lastKey = path.get(lastIndex);
/*  95 */     return (T)parentMap.get(lastKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T set(List<String> path, Object value) {
/* 100 */     int lastIndex = path.size() - 1;
/* 101 */     Map<String, Object> parentMap = getOrCreateMap(path.subList(0, lastIndex));
/* 102 */     String lastKey = path.get(lastIndex);
/* 103 */     Object nonNull = (value == null) ? NullObject.NULL_OBJECT : value;
/* 104 */     return (T)parentMap.put(lastKey, nonNull);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(List<String> path, Object value) {
/* 109 */     int lastIndex = path.size() - 1;
/* 110 */     Map<String, Object> parentMap = getOrCreateMap(path.subList(0, lastIndex));
/* 111 */     String lastKey = path.get(lastIndex);
/* 112 */     Object nonNull = (value == null) ? NullObject.NULL_OBJECT : value;
/* 113 */     return (parentMap.putIfAbsent(lastKey, nonNull) == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T remove(List<String> path) {
/* 118 */     int lastIndex = path.size() - 1;
/* 119 */     Map<String, Object> parentMap = getMap(path.subList(0, lastIndex));
/* 120 */     if (parentMap == null) {
/* 121 */       return null;
/*     */     }
/* 123 */     String lastKey = path.get(lastIndex);
/* 124 */     return (T)parentMap.remove(lastKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(List<String> path) {
/* 129 */     int lastIndex = path.size() - 1;
/* 130 */     Map<String, Object> parentMap = getMap(path.subList(0, lastIndex));
/* 131 */     if (parentMap == null) {
/* 132 */       return false;
/*     */     }
/* 134 */     String lastKey = path.get(lastIndex);
/* 135 */     return parentMap.containsKey(lastKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNull(List<String> path) {
/* 140 */     int lastIndex = path.size() - 1;
/* 141 */     Map<String, Object> parentMap = getMap(path.subList(0, lastIndex));
/* 142 */     if (parentMap == null) {
/* 143 */       return false;
/*     */     }
/* 145 */     String lastKey = path.get(lastIndex);
/* 146 */     Object value = parentMap.get(lastKey);
/* 147 */     return (value == NullObject.NULL_OBJECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Object> getOrCreateMap(List<String> path) {
/* 157 */     Map<String, Object> currentMap = this.map;
/* 158 */     for (String currentKey : path) {
/* 159 */       Config config; Object currentValue = currentMap.get(currentKey);
/*     */       
/* 161 */       if (currentValue == null)
/* 162 */       { config = createSubConfig();
/* 163 */         currentMap.put(currentKey, config); }
/* 164 */       else { if (!(currentValue instanceof Config)) {
/* 165 */           throw new IllegalArgumentException("Cannot add an element to an intermediary value of type: " + currentValue
/*     */               
/* 167 */               .getClass());
/*     */         }
/* 169 */         config = (Config)currentValue; }
/*     */       
/* 171 */       currentMap = config.valueMap();
/*     */     } 
/* 173 */     return currentMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Object> getMap(List<String> path) {
/* 183 */     Map<String, Object> currentMap = this.map;
/* 184 */     for (String key : path) {
/* 185 */       Object value = currentMap.get(key);
/* 186 */       if (!(value instanceof Config)) {
/* 187 */         return null;
/*     */       }
/* 189 */       currentMap = ((Config)value).valueMap();
/*     */     } 
/* 191 */     return currentMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 196 */     this.map.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 201 */     return this.map.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> valueMap() {
/* 206 */     return this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<? extends Config.Entry> entrySet() {
/* 211 */     return (Set<? extends Config.Entry>)new TransformingSet(this.map.entrySet(), EntryWrapper::new, o -> null, o -> o);
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
/*     */   public int hashCode() {
/* 226 */     return this.map.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 231 */     if (obj == this) return true; 
/* 232 */     if (!(obj instanceof AbstractConfig)) return false; 
/* 233 */     AbstractConfig other = (AbstractConfig)obj;
/* 234 */     return this.map.equals(other.map);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 239 */     return getClass().getSimpleName() + ':' + valueMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract AbstractConfig clone();
/*     */   
/*     */   protected static class EntryWrapper
/*     */     implements Config.Entry
/*     */   {
/*     */     protected final Map.Entry<String, Object> mapEntry;
/*     */     
/*     */     public EntryWrapper(Map.Entry<String, Object> mapEntry) {
/* 251 */       this.mapEntry = mapEntry;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getKey() {
/* 256 */       return this.mapEntry.getKey();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T getRawValue() {
/* 261 */       return (T)this.mapEntry.getValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T setValue(Object value) {
/* 266 */       return (T)this.mapEntry.setValue(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 271 */       if (obj == this) {
/* 272 */         return true;
/*     */       }
/* 274 */       if (obj instanceof EntryWrapper) {
/* 275 */         EntryWrapper other = (EntryWrapper)obj;
/* 276 */         return (Objects.equals(getKey(), other.getKey()) && 
/* 277 */           Objects.equals(getValue(), other.getValue()));
/*     */       } 
/* 279 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 284 */       int result = 1;
/* 285 */       result = 31 * result + Objects.hashCode(getKey());
/* 286 */       result = 31 * result + Objects.hashCode(getValue());
/* 287 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\AbstractConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */