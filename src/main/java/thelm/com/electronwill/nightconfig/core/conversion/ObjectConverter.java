/*     */ package thelm.com.electronwill.nightconfig.core.conversion;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.EnumGetMethod;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ObjectConverter
/*     */ {
/*     */   private final boolean bypassTransient;
/*     */   private final boolean bypassFinal;
/*     */   
/*     */   public ObjectConverter(boolean bypassTransient, boolean bypassFinal) {
/*  27 */     this.bypassTransient = bypassTransient;
/*  28 */     this.bypassFinal = bypassFinal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectConverter() {
/*  38 */     this(false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toConfig(Object o, Config destination) {
/*  48 */     Objects.requireNonNull(o, "The object must not be null.");
/*  49 */     Objects.requireNonNull(destination, "The config must not be null.");
/*  50 */     Class<?> clazz = o.getClass();
/*  51 */     List<String> annotatedPath = AnnotationUtils.getPath(clazz);
/*  52 */     if (annotatedPath != null) {
/*  53 */       destination = (Config)destination.getRaw(annotatedPath);
/*     */     }
/*  55 */     convertToConfig(o, clazz, destination);
/*     */   }
/*     */   
/*     */   public void toConfig(Class<?> clazz, Config destination) {
/*  59 */     Objects.requireNonNull(destination, "The config must not be null.");
/*  60 */     List<String> annotatedPath = AnnotationUtils.getPath(clazz);
/*  61 */     if (annotatedPath != null) {
/*  62 */       destination = (Config)destination.getRaw(annotatedPath);
/*     */     }
/*  64 */     convertToConfig(null, clazz, destination);
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
/*     */   public <C extends Config> C toConfig(Object o, Supplier<C> destinationSupplier) {
/*  76 */     Config config = (Config)destinationSupplier.get();
/*  77 */     toConfig(o, config);
/*  78 */     return (C)config;
/*     */   }
/*     */   
/*     */   public <C extends Config> C toConfig(Class<?> clazz, Supplier<C> destinationSupplier) {
/*  82 */     Config config = (Config)destinationSupplier.get();
/*  83 */     toConfig(clazz, config);
/*  84 */     return (C)config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toObject(UnmodifiableConfig config, Object destination) {
/*  94 */     Objects.requireNonNull(config, "The config must not be null.");
/*  95 */     Objects.requireNonNull(destination, "The object must not be null.");
/*  96 */     Class<?> clazz = destination.getClass();
/*  97 */     List<String> annotatedPath = AnnotationUtils.getPath(clazz);
/*  98 */     if (annotatedPath != null) {
/*  99 */       config = (UnmodifiableConfig)config.getRaw(annotatedPath);
/*     */     }
/* 101 */     convertToObject(config, destination, clazz);
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
/*     */   public <O> O toObject(UnmodifiableConfig config, Supplier<O> destinationSupplier) {
/* 113 */     O destination = destinationSupplier.get();
/* 114 */     toObject(config, destination);
/* 115 */     return destination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertToConfig(Object object, Class<?> clazz, Config destination) {
/* 123 */     while (clazz != Object.class) {
/* 124 */       for (Field field : clazz.getDeclaredFields()) {
/*     */         
/* 126 */         int fieldModifiers = field.getModifiers();
/* 127 */         if (object != null || !Modifier.isStatic(fieldModifiers))
/*     */         {
/*     */           
/* 130 */           if (this.bypassTransient || !Modifier.isTransient(fieldModifiers)) {
/*     */             Object value;
/*     */             
/* 133 */             if (!field.isAccessible()) {
/* 134 */               field.setAccessible(true);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 140 */               value = field.get(object);
/* 141 */             } catch (IllegalAccessException e) {
/* 142 */               throw new ReflectionException("Unable to parse the field " + field, e);
/*     */             } 
/* 144 */             AnnotationUtils.checkField(field, value);
/*     */             
/* 146 */             Converter<Object, Object> converter = AnnotationUtils.getConverter(field);
/* 147 */             if (converter != null) {
/* 148 */               value = converter.convertFromField(value);
/*     */             }
/* 150 */             List<String> path = AnnotationUtils.getPath(field);
/* 151 */             ConfigFormat<?> format = destination.configFormat();
/*     */ 
/*     */             
/* 154 */             if (value == null) {
/* 155 */               destination.set(path, null);
/*     */             } else {
/* 157 */               Class<?> valueType = value.getClass();
/* 158 */               if (Enum.class.isAssignableFrom(valueType)) {
/*     */ 
/*     */                 
/* 161 */                 if (destination.configFormat().supportsType(Enum.class)) {
/* 162 */                   destination.set(path, value);
/*     */                 } else {
/* 164 */                   destination.set(path, value.toString());
/*     */                 } 
/* 166 */               } else if (field.isAnnotationPresent((Class)ForceBreakdown.class) || !format.supportsType(valueType)) {
/*     */                 
/* 168 */                 destination.set(path, value);
/* 169 */                 Config converted = destination.createSubConfig();
/* 170 */                 convertToConfig(value, valueType, converted);
/* 171 */                 destination.set(path, converted);
/* 172 */               } else if (value instanceof Collection) {
/*     */                 
/* 174 */                 Collection<?> src = (Collection)value;
/* 175 */                 Class<?> bottomType = bottomElementType(src);
/* 176 */                 if (format.supportsType(bottomType)) {
/*     */                   
/* 178 */                   destination.set(path, value);
/*     */                 } else {
/*     */                   
/* 181 */                   Collection<Object> dst = new ArrayList(src.size());
/* 182 */                   convertObjectsToConfigs(src, bottomType, dst, destination);
/* 183 */                   destination.set(path, dst);
/*     */                 } 
/*     */               } else {
/*     */                 
/* 187 */                 destination.set(path, value);
/*     */               } 
/*     */             } 
/*     */           }  } 
/* 191 */       }  clazz = clazz.getSuperclass();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertToObject(UnmodifiableConfig config, Object object, Class<?> clazz) {
/* 201 */     while (clazz != Object.class) {
/* 202 */       for (Field field : clazz.getDeclaredFields()) {
/*     */         
/* 204 */         int fieldModifiers = field.getModifiers();
/* 205 */         if (object != null || !Modifier.isStatic(fieldModifiers))
/*     */         {
/*     */           
/* 208 */           if (this.bypassFinal || !Modifier.isFinal(fieldModifiers)) {
/* 209 */             field.setAccessible(true);
/*     */ 
/*     */ 
/*     */             
/* 213 */             if (this.bypassTransient || !Modifier.isTransient(fieldModifiers)) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 218 */               List<String> path = AnnotationUtils.getPath(field);
/* 219 */               Object value = config.get(path);
/* 220 */               Converter<Object, Object> converter = AnnotationUtils.getConverter(field);
/* 221 */               if (converter != null) {
/* 222 */                 value = converter.convertToField(value);
/*     */               }
/*     */ 
/*     */               
/* 226 */               Class<?> fieldType = field.getType();
/*     */               
/* 228 */               try { if (value instanceof UnmodifiableConfig && !fieldType.isAssignableFrom(value.getClass())) {
/*     */                   
/* 230 */                   UnmodifiableConfig cfg = (UnmodifiableConfig)value;
/*     */ 
/*     */                   
/* 233 */                   Object fieldValue = field.get(object);
/* 234 */                   if (fieldValue == null) {
/* 235 */                     fieldValue = createInstance(fieldType);
/* 236 */                     field.set(object, fieldValue);
/* 237 */                     convertToObject(cfg, fieldValue, field.getType());
/* 238 */                   } else if (!AnnotationUtils.mustPreserve(field, clazz)) {
/* 239 */                     convertToObject(cfg, fieldValue, field.getType());
/*     */                   }
/*     */                 
/* 242 */                 } else if (value instanceof Collection && Collection.class.isAssignableFrom(fieldType)) {
/*     */                   
/* 244 */                   Collection<?> src = (Collection)value;
/* 245 */                   Class<?> srcBottomType = bottomElementType(src);
/*     */                   
/* 247 */                   ParameterizedType genericType = (ParameterizedType)field.getGenericType();
/* 248 */                   List<Class<?>> dstTypes = elementTypes(genericType);
/* 249 */                   Class<?> dstBottomType = dstTypes.get(dstTypes.size() - 1);
/*     */                   
/* 251 */                   if (srcBottomType == null || dstBottomType == null || dstBottomType
/*     */                     
/* 253 */                     .isAssignableFrom(srcBottomType))
/*     */                   {
/*     */                     
/* 256 */                     AnnotationUtils.checkField(field, value);
/* 257 */                     field.set(object, value);
/*     */                   
/*     */                   }
/*     */                   else
/*     */                   {
/*     */                     
/* 263 */                     Collection<Object> dst = (Collection<Object>)field.get(object);
/* 264 */                     if (dst == null) {
/* 265 */                       if (fieldType == ArrayList.class || fieldType
/* 266 */                         .isInterface() || 
/* 267 */                         Modifier.isAbstract(fieldType.getModifiers())) {
/* 268 */                         dst = new ArrayList(src.size());
/*     */                       } else {
/* 270 */                         dst = (Collection<Object>)createInstance(fieldType);
/*     */                       } 
/* 272 */                       field.set(object, dst);
/*     */                     } 
/*     */ 
/*     */                     
/* 276 */                     convertConfigsToObject(src, dst, dstTypes, 0);
/*     */ 
/*     */                     
/* 279 */                     AnnotationUtils.checkField(field, dst);
/*     */                   }
/*     */                 
/*     */                 }
/* 283 */                 else if (value == null && AnnotationUtils.mustPreserve(field, clazz)) {
/* 284 */                   AnnotationUtils.checkField(field, field.get(object));
/*     */                 } else {
/* 286 */                   AnnotationUtils.checkField(field, value);
/* 287 */                   if (field.getType().isEnum()) {
/* 288 */                     Class<? extends Enum> enumType = (Class)field.getType();
/* 289 */                     SpecEnum specEnum = field.<SpecEnum>getAnnotation(SpecEnum.class);
/* 290 */                     EnumGetMethod method = (specEnum == null) ? EnumGetMethod.NAME_IGNORECASE : specEnum.method();
/* 291 */                     field.set(object, method.get(value, enumType));
/*     */                   } else {
/* 293 */                     field.set(object, value);
/*     */                   }
/*     */                 
/*     */                 }  }
/* 297 */               catch (ReflectiveOperationException ex)
/* 298 */               { throw new ReflectionException("Unable to work with field " + field, ex); } 
/*     */             } 
/*     */           }  } 
/* 301 */       }  clazz = clazz.getSuperclass();
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
/*     */   private Class<?> bottomElementType(ParameterizedType genericType) {
/* 314 */     if (genericType != null && (genericType.getActualTypeArguments()).length > 0) {
/* 315 */       Type parameter = genericType.getActualTypeArguments()[0];
/* 316 */       if (parameter instanceof ParameterizedType) {
/* 317 */         ParameterizedType genericParameter = (ParameterizedType)parameter;
/* 318 */         Class<?> paramClass = (Class)genericParameter.getRawType();
/* 319 */         if (paramClass.isAssignableFrom(Collection.class)) {
/* 320 */           return bottomElementType(genericParameter);
/*     */         }
/* 322 */         return paramClass;
/*     */       } 
/*     */       
/* 325 */       if (parameter instanceof Class) {
/* 326 */         return (Class)parameter;
/*     */       }
/*     */     } 
/* 329 */     return null;
/*     */   }
/*     */   
/*     */   private void detectElementTypes(ParameterizedType genericType, List<Class<?>> storage) {
/* 333 */     if (genericType != null && (genericType.getActualTypeArguments()).length > 0) {
/* 334 */       Type parameter = genericType.getActualTypeArguments()[0];
/* 335 */       if (parameter instanceof ParameterizedType) {
/* 336 */         ParameterizedType genericParameter = (ParameterizedType)parameter;
/* 337 */         Class<?> paramClass = (Class)genericParameter.getRawType();
/*     */         
/* 339 */         storage.add(paramClass);
/* 340 */         if (Collection.class.isAssignableFrom(paramClass)) {
/* 341 */           detectElementTypes(genericParameter, storage);
/*     */         }
/* 343 */       } else if (parameter instanceof Class) {
/* 344 */         storage.add((Class)parameter);
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
/*     */ 
/*     */   
/*     */   private List<Class<?>> elementTypes(ParameterizedType genericType) {
/* 358 */     List<Class<?>> storage = new ArrayList<>();
/* 359 */     detectElementTypes(genericType, storage);
/* 360 */     return storage;
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
/*     */   private Class<?> bottomElementType(Collection<?> list) {
/* 372 */     for (Object elem : list) {
/* 373 */       if (elem instanceof Collection)
/* 374 */         return bottomElementType((Collection)elem); 
/* 375 */       if (elem != null) {
/* 376 */         return elem.getClass();
/*     */       }
/*     */     } 
/* 379 */     return null;
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
/*     */   private void convertConfigsToObject(Collection<?> src, Collection<Object> dst, List<Class<?>> dstElementTypes, int currentLevel) {
/* 393 */     Class<?> currentType = dstElementTypes.get(currentLevel);
/* 394 */     for (Object elem : src) {
/* 395 */       if (elem == null) {
/* 396 */         dst.add(null); continue;
/* 397 */       }  if (elem instanceof Collection) {
/* 398 */         Collection<Object> subDst; Collection<?> subSrc = (Collection)elem;
/*     */ 
/*     */         
/* 401 */         if (currentType == ArrayList.class || currentType
/* 402 */           .isInterface() || 
/* 403 */           Modifier.isAbstract(currentType.getModifiers())) {
/*     */           
/* 405 */           subDst = new ArrayList();
/*     */         } else {
/* 407 */           subDst = (Collection<Object>)createInstance(currentType);
/*     */         } 
/* 409 */         convertConfigsToObject(subSrc, subDst, dstElementTypes, currentLevel + 1);
/* 410 */         dst.add(subDst); continue;
/* 411 */       }  if (elem instanceof UnmodifiableConfig) {
/* 412 */         Object elementObj = createInstance(currentType);
/* 413 */         convertToObject((UnmodifiableConfig)elem, elementObj, currentType);
/* 414 */         dst.add(elementObj); continue;
/*     */       } 
/* 416 */       String elemType = elem.getClass().toString();
/* 417 */       throw new InvalidValueException("Unexpected element of type " + elemType + " in collection of objects");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertObjectsToConfigs(Collection<?> src, Class<?> srcBottomType, Collection<Object> dst, Config parentConfig) {
/* 434 */     for (Object elem : src) {
/* 435 */       if (elem == null) {
/* 436 */         dst.add(null); continue;
/* 437 */       }  if (srcBottomType.isAssignableFrom(elem.getClass())) {
/* 438 */         Config elementConfig = parentConfig.createSubConfig();
/* 439 */         convertToConfig(elem, elem.getClass(), elementConfig);
/* 440 */         dst.add(elementConfig); continue;
/* 441 */       }  if (elem instanceof Collection) {
/* 442 */         ArrayList<Object> subList = new ArrayList();
/* 443 */         convertObjectsToConfigs((Collection)elem, srcBottomType, subList, parentConfig);
/* 444 */         subList.trimToSize();
/* 445 */         dst.add(subList); continue;
/*     */       } 
/* 447 */       String elemType = elem.getClass().toString();
/* 448 */       throw new InvalidValueException("Unexpected element of type " + elemType + " in collection of " + srcBottomType);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> T createInstance(Class<T> tClass) {
/*     */     try {
/* 465 */       Constructor<T> ctor = tClass.getDeclaredConstructor(new Class[0]);
/* 466 */       if (!ctor.isAccessible()) {
/* 467 */         ctor.setAccessible(true);
/*     */       }
/* 469 */       return ctor.newInstance(new Object[0]);
/* 470 */     } catch (ReflectiveOperationException ex) {
/* 471 */       throw new ReflectionException("Unable to create an instance of " + tClass, ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\ObjectConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */