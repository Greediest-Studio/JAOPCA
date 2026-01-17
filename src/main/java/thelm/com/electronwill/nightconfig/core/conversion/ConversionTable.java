/*     */ package thelm.com.electronwill.nightconfig.core.conversion;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.file.FileConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingMap;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.UnmodifiableConfigWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConversionTable
/*     */   implements Cloneable
/*     */ {
/*     */   private final Map<Class<?>, Function<?, Object>> conversionMap;
/*     */   
/*     */   public ConversionTable() {
/*  34 */     this.conversionMap = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ConversionTable(ConversionTable toCopy) {
/*  43 */     this.conversionMap = new HashMap<>(toCopy.conversionMap);
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
/*     */   public <T> void put(Class<T> classToConvert, Function<? super T, Object> conversionFunction) {
/*  55 */     this.conversionMap.put(classToConvert, conversionFunction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(Class<?> classToConvert) {
/*  64 */     this.conversionMap.remove(classToConvert);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Class<?> classToConvert) {
/*  74 */     return this.conversionMap.containsKey(classToConvert);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object convert(Object value) {
/*  84 */     Function<Object, Object> conversionFunction = getConversionFunction(value);
/*  85 */     if (conversionFunction == null) {
/*  86 */       return value;
/*     */     }
/*  88 */     return conversionFunction.apply(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertShallow(Config config) {
/*  98 */     for (Map.Entry<String, Object> configEntry : (Iterable<Map.Entry<String, Object>>)config.valueMap().entrySet()) {
/*  99 */       Object value = configEntry.getValue();
/* 100 */       Function<Object, Object> conversionFunction = getConversionFunction(value);
/* 101 */       if (conversionFunction != null) {
/* 102 */         configEntry.setValue(conversionFunction.apply(value));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertDeep(Config config) {
/* 114 */     for (Map.Entry<String, Object> configEntry : (Iterable<Map.Entry<String, Object>>)config.valueMap().entrySet()) {
/* 115 */       Object value = configEntry.getValue();
/* 116 */       if (value instanceof Config) {
/* 117 */         convertDeep(config); continue;
/*     */       } 
/* 119 */       Function<Object, Object> conversionFunction = getConversionFunction(value);
/* 120 */       if (conversionFunction != null) {
/* 121 */         configEntry.setValue(conversionFunction.apply(value));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Function<Object, Object> getConversionFunction(Object value) {
/* 129 */     if (value == null) {
/* 130 */       return (Function<Object, Object>)this.conversionMap.get(null);
/*     */     }
/* 132 */     Class<?> clazz = value.getClass();
/* 133 */     Function<?, Object> conversionFunction = this.conversionMap.get(clazz);
/*     */     
/* 135 */     while (conversionFunction == null) {
/* 136 */       clazz = clazz.getSuperclass();
/* 137 */       if (clazz == null) {
/*     */         break;
/*     */       }
/* 140 */       conversionFunction = this.conversionMap.get(clazz);
/*     */     } 
/* 142 */     return (Function)conversionFunction;
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
/*     */   public ConversionTable chainThen(ConversionTable after) {
/* 154 */     ConversionTable result = new ConversionTable(this);
/*     */     
/* 156 */     for (Map.Entry<Class<?>, Function<?, Object>> entry : result.conversionMap.entrySet()) {
/* 157 */       Objects.requireNonNull(after); entry.setValue(((Function)entry.getValue()).andThen(after::convert));
/*     */     } 
/*     */     
/* 160 */     for (Map.Entry<Class<?>, Function<?, Object>> entry : after.conversionMap.entrySet()) {
/* 161 */       result.conversionMap.putIfAbsent(entry.getKey(), entry.getValue());
/*     */     }
/* 163 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnmodifiableConfig wrap(UnmodifiableConfig config) {
/* 174 */     return (UnmodifiableConfig)new UnmodifiableConfigWrapper<UnmodifiableConfig>(config)
/*     */       {
/*     */         public <T> T getRaw(List<String> path) {
/* 177 */           return (T)ConversionTable.this.convert(this.config.getRaw(path));
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<String, Object> valueMap() {
/* 182 */           return (Map<String, Object>)new TransformingMap(this.config.valueMap(), v -> ConversionTable.this.convert(v), v -> v, v -> v);
/*     */         }
/*     */ 
/*     */         
/*     */         public Set<? extends UnmodifiableConfig.Entry> entrySet() {
/* 187 */           Function<UnmodifiableConfig.Entry, UnmodifiableConfig.Entry> readTransfo = entry -> new UnmodifiableConfig.Entry()
/*     */             {
/*     */               public String getKey() {
/* 190 */                 return entry.getKey();
/*     */               }
/*     */ 
/*     */               
/*     */               public <T> T getRawValue() {
/* 195 */                 return (T)ConversionTable.this.convert(entry.getRawValue());
/*     */               }
/*     */             };
/* 198 */           return (Set<? extends UnmodifiableConfig.Entry>)new TransformingSet(this.config.entrySet(), readTransfo, o -> null, e -> e);
/*     */         }
/*     */ 
/*     */         
/*     */         public ConfigFormat<?> configFormat() {
/* 203 */           return this.config.configFormat();
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
/*     */   public Config wrapRead(Config config) {
/* 217 */     Objects.requireNonNull(config.configFormat()); return new ConvertedConfig(config, this::convert, v -> v, config.configFormat()::supportsType);
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
/*     */   public CommentedConfig wrapRead(CommentedConfig config) {
/* 229 */     Objects.requireNonNull(config.configFormat()); return new ConvertedCommentedConfig(config, this::convert, v -> v, config.configFormat()::supportsType);
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
/*     */   public FileConfig wrapRead(FileConfig config) {
/* 241 */     Objects.requireNonNull(config.configFormat()); return new ConvertedFileConfig(config, this::convert, v -> v, config.configFormat()::supportsType);
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
/*     */   public CommentedFileConfig wrapRead(CommentedFileConfig config) {
/* 253 */     Objects.requireNonNull(config.configFormat()); return new ConvertedCommentedFileConfig(config, this::convert, v -> v, config.configFormat()::supportsType);
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
/*     */   public Config wrapWrite(Config config, Predicate<Class<?>> supportValueTypePredicate) {
/* 266 */     return new ConvertedConfig(config, v -> v, this::convert, supportValueTypePredicate);
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
/*     */   public CommentedConfig wrapWrite(CommentedConfig config, Predicate<Class<?>> supportValueTypePredicate) {
/* 280 */     return new ConvertedCommentedConfig(config, v -> v, this::convert, supportValueTypePredicate);
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
/*     */   public FileConfig wrapWrite(FileConfig config, Predicate<Class<?>> supportValueTypePredicate) {
/* 294 */     return new ConvertedFileConfig(config, v -> v, this::convert, supportValueTypePredicate);
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
/*     */   public CommentedFileConfig wrapWrite(CommentedFileConfig config, Predicate<Class<?>> supportValueTypePredicate) {
/* 308 */     return new ConvertedCommentedFileConfig(config, v -> v, this::convert, supportValueTypePredicate);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConversionTable clone() {
/* 314 */     return new ConversionTable(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 319 */     return "ConversionTable: " + this.conversionMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ConversionTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */