/*     */ package thelm.com.electronwill.nightconfig.core.conversion;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.EnumGetMethod;
/*     */ import thelm.com.electronwill.nightconfig.core.InMemoryFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingMap;
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
/*     */ public final class ObjectBinder
/*     */ {
/*     */   private final boolean bypassTransient;
/*     */   private final boolean bypassFinal;
/*     */   
/*     */   public ObjectBinder(boolean bypassTransient, boolean bypassFinal) {
/*  33 */     this.bypassTransient = bypassTransient;
/*  34 */     this.bypassFinal = bypassFinal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectBinder() {
/*  44 */     this(false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Config bind(Class<?> clazz) {
/*  54 */     return bind(clazz, (ConfigFormat<?>)InMemoryFormat.defaultInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Config bind(Class<?> clazz, ConfigFormat<?> configFormat) {
/*  65 */     return bind(null, clazz, configFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Config bind(Object object) {
/*  75 */     return bind(object, (ConfigFormat<?>)InMemoryFormat.defaultInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Config bind(Object object, ConfigFormat<?> configFormat) {
/*  86 */     return bind(object, object.getClass(), configFormat);
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
/*     */   private Config bind(Object object, Class<?> clazz, ConfigFormat<?> configFormat) {
/*  99 */     BoundConfig boundConfig = createBoundConfig(object, clazz, configFormat);
/* 100 */     List<String> annotatedPath = AnnotationUtils.getPath(clazz);
/* 101 */     if (annotatedPath != null) {
/* 102 */       Config parentConfig = configFormat.createConfig();
/* 103 */       parentConfig.set(annotatedPath, boundConfig);
/* 104 */       return parentConfig;
/*     */     } 
/* 106 */     return boundConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BoundConfig createBoundConfig(Object object, Class<?> clazz, ConfigFormat<?> configFormat) {
/* 114 */     BoundConfig boundConfig = new BoundConfig(object, configFormat, this.bypassFinal);
/* 115 */     for (Field field : clazz.getDeclaredFields()) {
/* 116 */       int fieldModifiers = field.getModifiers();
/* 117 */       if (object != null || !Modifier.isStatic(fieldModifiers))
/*     */       {
/*     */         
/* 120 */         if (this.bypassTransient || !Modifier.isTransient(fieldModifiers)) {
/*     */           FieldInfos fieldInfos;
/*     */           
/* 123 */           if (!field.isAccessible()) {
/* 124 */             field.setAccessible(true);
/*     */           }
/* 126 */           List<String> path = AnnotationUtils.getPath(field);
/*     */           
/* 128 */           Converter<Object, Object> converter = AnnotationUtils.getConverter(field);
/* 129 */           boolean isEnum = Enum.class.isAssignableFrom(field.getType());
/* 130 */           if (converter == null) {
/* 131 */             if (isEnum) {
/* 132 */               SpecEnum spec = field.<SpecEnum>getAnnotation(SpecEnum.class);
/* 133 */               EnumGetMethod method = (spec == null) ? EnumGetMethod.NAME_IGNORECASE : spec.method();
/* 134 */               converter = new EnumValueConverter(field.getType(), method);
/*     */             } else {
/* 136 */               converter = NoOpConverter.INSTANCE;
/*     */             } 
/*     */           }
/*     */           try {
/* 140 */             Object value = converter.convertFromField(field.get(object));
/* 141 */             if (value == null || isEnum || configFormat.supportsType(value.getClass())) {
/*     */               
/* 143 */               fieldInfos = new FieldInfos(field, null, converter);
/*     */             } else {
/*     */               
/* 146 */               BoundConfig subConfig = createBoundConfig(value, field.getType(), configFormat);
/* 147 */               fieldInfos = new FieldInfos(field, subConfig, converter);
/*     */             } 
/* 149 */           } catch (IllegalAccessException e) {
/* 150 */             throw new ReflectionException("Failed to bind field " + field, e);
/*     */           } 
/* 152 */           boundConfig.registerField(fieldInfos, path);
/*     */         }  } 
/* 154 */     }  return boundConfig;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class BoundConfig
/*     */     implements Config
/*     */   {
/*     */     private Object object;
/*     */     
/*     */     private final Map<String, Object> dataMap;
/*     */     
/*     */     private final ConfigFormat<?> configFormat;
/*     */     private final boolean bypassFinal;
/*     */     
/*     */     private BoundConfig(Object object, Map<String, Object> dataMap, ConfigFormat<?> configFormat, boolean bypassFinal) {
/* 169 */       this.object = object;
/* 170 */       this.dataMap = dataMap;
/* 171 */       this.configFormat = configFormat;
/* 172 */       this.bypassFinal = bypassFinal;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private BoundConfig(Object object, ConfigFormat<?> configFormat, boolean bypassFinal) {
/* 180 */       this(object, new HashMap<>(), configFormat, bypassFinal);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void registerField(ObjectBinder.FieldInfos fieldInfos, List<String> path) {
/* 187 */       int lastIndex = path.size() - 1;
/* 188 */       Map<String, Object> currentMap = this.dataMap;
/* 189 */       for (String currentKey : path.subList(0, lastIndex)) {
/* 190 */         BoundConfig config; Object currentValue = currentMap.get(currentKey);
/*     */         
/* 192 */         if (currentValue == null)
/* 193 */         { config = new BoundConfig(null, new HashMap<>(1), this.configFormat, this.bypassFinal);
/* 194 */           currentMap.put(currentKey, config); }
/* 195 */         else { if (!(currentValue instanceof BoundConfig)) {
/* 196 */             throw new IllegalArgumentException("Cannot add an element to an intermediary value of type: " + currentValue
/*     */                 
/* 198 */                 .getClass());
/*     */           }
/* 200 */           config = (BoundConfig)currentValue; }
/*     */         
/* 202 */         currentMap = config.dataMap;
/*     */       } 
/* 204 */       String lastKey = path.get(lastIndex);
/* 205 */       currentMap.put(lastKey, fieldInfos);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ObjectBinder.BoundSearchResult searchInfosOrConfig(List<String> path) {
/* 216 */       int lastIndex = path.size() - 1;
/* 217 */       BoundConfig currentConfig = this;
/* 218 */       for (String key : path.subList(0, lastIndex)) {
/* 219 */         Object v = currentConfig.dataMap.get(key);
/* 220 */         if (v == null)
/* 221 */           return null; 
/* 222 */         if (v instanceof BoundConfig) {
/* 223 */           currentConfig = (BoundConfig)v; continue;
/*     */         } 
/* 225 */         ObjectBinder.FieldInfos fieldInfos = (ObjectBinder.FieldInfos)v;
/* 226 */         currentConfig = fieldInfos.getUpdatedConfig(currentConfig.object);
/*     */       } 
/*     */       
/* 229 */       String lastKey = path.get(lastIndex);
/* 230 */       Object data = currentConfig.dataMap.get(lastKey);
/* 231 */       return new ObjectBinder.BoundSearchResult(currentConfig, data);
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T getRaw(List<String> path) {
/* 236 */       ObjectBinder.BoundSearchResult searchResult = searchInfosOrConfig(path);
/* 237 */       if (searchResult == null)
/* 238 */         return null; 
/* 239 */       if (searchResult.hasSubConfig()) {
/* 240 */         return (T)searchResult.subConfig;
/*     */       }
/* 242 */       return (T)searchResult.fieldInfos.getValue(searchResult.parentConfig.object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean contains(List<String> path) {
/* 248 */       return (searchInfosOrConfig(path) != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T set(List<String> path, Object value) {
/* 253 */       ObjectBinder.BoundSearchResult searchResult = searchInfosOrConfig(path);
/* 254 */       if (searchResult == null)
/* 255 */         throw new UnsupportedOperationException("Cannot add elements to a bound config"); 
/* 256 */       if (searchResult.hasFieldInfos()) {
/* 257 */         return (T)searchResult.fieldInfos.setValue(searchResult.parentConfig.object, value, this.bypassFinal);
/*     */       }
/*     */       
/* 260 */       throw new UnsupportedOperationException("Cannot modify non-field elements of a bound config");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean add(List<String> path, Object value) {
/* 267 */       throw new UnsupportedOperationException("Cannot add elements to a bound config");
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T remove(List<String> path) {
/* 272 */       ObjectBinder.BoundSearchResult searchResult = searchInfosOrConfig(path);
/* 273 */       if (searchResult == null)
/* 274 */         return null; 
/* 275 */       if (searchResult.hasFieldInfos()) {
/* 276 */         return (T)searchResult.fieldInfos.removeValue(searchResult.parentConfig.object, this.bypassFinal);
/*     */       }
/*     */       
/* 279 */       Config copy = Config.copy((UnmodifiableConfig)searchResult.subConfig);
/* 280 */       searchResult.subConfig.clear();
/* 281 */       return (T)copy;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() {
/* 287 */       for (Map.Entry<String, Object> dataEntry : this.dataMap.entrySet()) {
/* 288 */         Object value = dataEntry.getValue();
/* 289 */         if (value instanceof ObjectBinder.FieldInfos) {
/* 290 */           ((ObjectBinder.FieldInfos)value).removeValue(this.object, this.bypassFinal); continue;
/* 291 */         }  if (value instanceof BoundConfig) {
/* 292 */           ((BoundConfig)value).clear();
/*     */         }
/*     */       } 
/* 295 */       this.dataMap.clear();
/*     */     }
/*     */ 
/*     */     
/*     */     public ConfigFormat<?> configFormat() {
/* 300 */       return this.configFormat;
/*     */     }
/*     */ 
/*     */     
/*     */     public Config createSubConfig() {
/* 305 */       return new BoundConfig(null, new HashMap<>(1), this.configFormat, this.bypassFinal);
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<String, Object> valueMap() {
/* 310 */       Function<Object, Object> readConversion = o -> {
/*     */           if (o instanceof ObjectBinder.FieldInfos) {
/*     */             ObjectBinder.FieldInfos fieldInfos = (ObjectBinder.FieldInfos)o;
/*     */             
/*     */             return (fieldInfos.boundConfig != null) ? fieldInfos.getUpdatedConfig(this.object) : fieldInfos.getValue(this.object);
/*     */           } 
/*     */           
/*     */           return o;
/*     */         };
/*     */       
/* 320 */       return (Map<String, Object>)new TransformingMap(this.dataMap, readConversion, o -> o, o -> o);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Set<? extends Config.Entry> entrySet() {
/* 326 */       Function<Map.Entry<String, Object>, Config.Entry> readTransfo = entry -> new Config.Entry()
/*     */         {
/*     */           public <T> T setValue(Object value) {
/* 329 */             return (T)ObjectBinder.BoundConfig.this.set((String)entry.getKey(), value);
/*     */           }
/*     */ 
/*     */           
/*     */           public String getKey() {
/* 334 */             return (String)entry.getKey();
/*     */           }
/*     */ 
/*     */           
/*     */           public <T> T getRawValue() {
/* 339 */             return (T)entry.getValue();
/*     */           }
/*     */         };
/* 342 */       return (Set<? extends Config.Entry>)new TransformingSet(this.dataMap.entrySet(), readTransfo, o -> null, o -> o);
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 347 */       return this.dataMap.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 352 */       return "BoundConfig{object=" + this.object + ", dataMap=" + this.dataMap + '}';
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class BoundSearchResult {
/*     */     final ObjectBinder.BoundConfig parentConfig;
/*     */     final ObjectBinder.FieldInfos fieldInfos;
/*     */     final ObjectBinder.BoundConfig subConfig;
/*     */     
/*     */     BoundSearchResult(ObjectBinder.BoundConfig parentConfig, Object data) {
/* 362 */       this.parentConfig = parentConfig;
/* 363 */       if (data instanceof ObjectBinder.FieldInfos) {
/* 364 */         this.fieldInfos = (ObjectBinder.FieldInfos)data;
/* 365 */         if (this.fieldInfos.boundConfig == null) {
/* 366 */           this.subConfig = null;
/*     */         } else {
/* 368 */           this.subConfig = this.fieldInfos.getUpdatedConfig(parentConfig.object);
/*     */         } 
/*     */       } else {
/* 371 */         this.fieldInfos = null;
/* 372 */         this.subConfig = (ObjectBinder.BoundConfig)data;
/*     */       } 
/*     */     }
/*     */     
/*     */     boolean hasFieldInfos() {
/* 377 */       return (this.fieldInfos != null);
/*     */     }
/*     */     
/*     */     boolean hasSubConfig() {
/* 381 */       return (this.subConfig != null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class FieldInfos
/*     */   {
/*     */     final Field field;
/*     */     
/*     */     final ObjectBinder.BoundConfig boundConfig;
/*     */     final Converter<Object, Object> converter;
/*     */     
/*     */     FieldInfos(Field field, ObjectBinder.BoundConfig boundConfig, Converter<Object, Object> converter) {
/* 394 */       this.field = field;
/* 395 */       this.boundConfig = boundConfig;
/* 396 */       this.converter = converter;
/*     */     }
/*     */     
/*     */     Object setValue(Object fieldObject, Object value, boolean bypassFinal) {
/* 400 */       if (!bypassFinal && Modifier.isFinal(this.field.getModifiers())) {
/* 401 */         throw new UnsupportedOperationException("Cannot modify the field " + this.field);
/*     */       }
/*     */       try {
/* 404 */         Object previousValue = this.converter.convertFromField(this.field.get(fieldObject));
/* 405 */         Object newValue = this.converter.convertToField(value);
/* 406 */         AnnotationUtils.checkField(this.field, newValue);
/* 407 */         this.field.set(fieldObject, newValue);
/* 408 */         return previousValue;
/* 409 */       } catch (IllegalAccessException e) {
/* 410 */         throw new ReflectionException("Failed to set field " + this.field, e);
/*     */       } 
/*     */     }
/*     */     
/*     */     Object removeValue(Object fieldObject, boolean bypassFinal) {
/* 415 */       Object previousValue = getValue(fieldObject);
/* 416 */       if (this.field.getType().isPrimitive()) {
/* 417 */         setValue(fieldObject, Byte.valueOf((byte)0), bypassFinal);
/*     */       } else {
/* 419 */         setValue(fieldObject, null, bypassFinal);
/* 420 */         if (this.boundConfig != null) {
/* 421 */           this.boundConfig.clear();
/*     */         }
/*     */       } 
/* 424 */       return previousValue;
/*     */     }
/*     */     
/*     */     Object getValue(Object fieldObject) {
/*     */       try {
/* 429 */         return this.converter.convertFromField(this.field.get(fieldObject));
/* 430 */       } catch (IllegalAccessException e) {
/* 431 */         throw new ReflectionException("Failed to get field " + this.field, e);
/*     */       } 
/*     */     }
/*     */     
/*     */     ObjectBinder.BoundConfig getUpdatedConfig(Object fieldObject) {
/* 436 */       this.boundConfig.object = getValue(fieldObject);
/* 437 */       return this.boundConfig;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 442 */       return "FieldInfos{field=" + this.field + ", boundConfig=" + this.boundConfig + '}';
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class NoOpConverter implements Converter<Object, Object> {
/* 447 */     static final NoOpConverter INSTANCE = new NoOpConverter();
/*     */ 
/*     */     
/*     */     public Object convertToField(Object value) {
/* 451 */       return value;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object convertFromField(Object value) {
/* 456 */       return value;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class EnumValueConverter<T extends Enum<T>> implements Converter<T, Object> {
/*     */     private final Class<T> enumType;
/*     */     private final EnumGetMethod method;
/*     */     
/*     */     EnumValueConverter(Class<T> enumType, EnumGetMethod method) {
/* 465 */       this.enumType = enumType;
/* 466 */       this.method = method;
/*     */     }
/*     */ 
/*     */     
/*     */     public T convertToField(Object value) {
/* 471 */       return (T)this.method.get(value, this.enumType);
/*     */     }
/*     */ 
/*     */     
/*     */     public String convertFromField(T value) {
/* 476 */       return (value == null) ? null : value.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ObjectBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */