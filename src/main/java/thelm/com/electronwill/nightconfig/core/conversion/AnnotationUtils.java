/*     */ package thelm.com.electronwill.nightconfig.core.conversion;
/*     */ 
/*     */ import java.lang.reflect.AnnotatedElement;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import thelm.com.electronwill.nightconfig.core.EnumGetMethod;
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
/*     */ final class AnnotationUtils
/*     */ {
/*     */   static boolean isEnum(Field annotatedElement) {
/*  26 */     return (annotatedElement.getType().isEnum() || annotatedElement.isAnnotationPresent((Class)PreserveNotNull.class));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean hasPreserveNotNull(AnnotatedElement annotatedElement) {
/*  33 */     return annotatedElement.isAnnotationPresent((Class)PreserveNotNull.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean mustPreserve(Field field, Class<?> fieldClass) {
/*  40 */     return (hasPreserveNotNull(field) || hasPreserveNotNull(fieldClass));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Converter<Object, Object> getConverter(Field field) {
/*  50 */     Conversion conversion = field.<Conversion>getAnnotation(Conversion.class);
/*  51 */     if (conversion != null) {
/*     */       
/*     */       try {
/*  54 */         Constructor<? extends Converter> constructor = (Constructor)conversion.value().getDeclaredConstructor(new Class[0]);
/*  55 */         if (!constructor.isAccessible()) {
/*  56 */           constructor.setAccessible(true);
/*     */         }
/*  58 */         return constructor.newInstance(new Object[0]);
/*  59 */       } catch (ReflectiveOperationException ex) {
/*  60 */         throw new ReflectionException("Cannot create a converter for field " + field, ex);
/*     */       } 
/*     */     }
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List<String> getPath(Field field) {
/*  73 */     List<String> annotatedPath = getPath(field);
/*  74 */     return (annotatedPath == null) ? Collections.<String>singletonList(field.getName()) : annotatedPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List<String> getPath(AnnotatedElement annotatedElement) {
/*  83 */     Path path = annotatedElement.<Path>getDeclaredAnnotation(Path.class);
/*  84 */     if (path != null) {
/*  85 */       return StringUtils.split(path.value(), '.');
/*     */     }
/*  87 */     AdvancedPath advancedPath = annotatedElement.<AdvancedPath>getDeclaredAnnotation(AdvancedPath.class);
/*  88 */     if (advancedPath != null) {
/*  89 */       return Arrays.asList(advancedPath.value());
/*     */     }
/*  91 */     return null;
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
/*     */   static void checkField(Field field, Object value) {
/* 107 */     SpecNotNull specNotNull = field.<SpecNotNull>getDeclaredAnnotation(SpecNotNull.class);
/* 108 */     if (specNotNull != null) {
/* 109 */       checkNotNull(field, value);
/*     */       return;
/*     */     } 
/* 112 */     SpecClassInArray specClassInArray = field.<SpecClassInArray>getDeclaredAnnotation(SpecClassInArray.class);
/* 113 */     if (specClassInArray != null) {
/* 114 */       checkFieldSpec(field, value, specClassInArray);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 119 */     SpecStringInArray specStringInArray = field.<SpecStringInArray>getDeclaredAnnotation(SpecStringInArray.class);
/* 120 */     if (specStringInArray != null) {
/* 121 */       checkFieldSpec(field, value, specStringInArray);
/*     */       return;
/*     */     } 
/* 124 */     SpecStringInRange specStringInRange = field.<SpecStringInRange>getDeclaredAnnotation(SpecStringInRange.class);
/* 125 */     if (specStringInRange != null) {
/* 126 */       checkFieldSpec(field, value, specStringInRange);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 131 */     SpecDoubleInRange specDoubleInRange = field.<SpecDoubleInRange>getDeclaredAnnotation(SpecDoubleInRange.class);
/* 132 */     if (specDoubleInRange != null) {
/* 133 */       checkFieldSpec(field, value, specDoubleInRange);
/*     */       return;
/*     */     } 
/* 136 */     SpecFloatInRange specFloatInRange = field.<SpecFloatInRange>getDeclaredAnnotation(SpecFloatInRange.class);
/* 137 */     if (specFloatInRange != null) {
/* 138 */       checkFieldSpec(field, value, specFloatInRange);
/*     */       return;
/*     */     } 
/* 141 */     SpecLongInRange specLongInRange = field.<SpecLongInRange>getDeclaredAnnotation(SpecLongInRange.class);
/* 142 */     if (specLongInRange != null) {
/* 143 */       checkFieldSpec(field, value, specLongInRange);
/*     */       return;
/*     */     } 
/* 146 */     SpecIntInRange specIntInRange = field.<SpecIntInRange>getDeclaredAnnotation(SpecIntInRange.class);
/* 147 */     if (specIntInRange != null) {
/* 148 */       checkFieldSpec(field, value, specIntInRange);
/*     */     }
/*     */ 
/*     */     
/* 152 */     SpecEnum specEnum = field.<SpecEnum>getDeclaredAnnotation(SpecEnum.class);
/* 153 */     if (specEnum != null) {
/* 154 */       checkFieldSpec(field, value, specEnum);
/*     */     }
/*     */ 
/*     */     
/* 158 */     SpecValidator specValidator = field.<SpecValidator>getDeclaredAnnotation(SpecValidator.class);
/* 159 */     if (specValidator != null) {
/* 160 */       checkFieldSpec(field, value, specValidator);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecValidator spec) {
/*     */     Predicate<Object> validatorInstance;
/*     */     try {
/* 168 */       Constructor<? extends Predicate<Object>> constructor = spec.value().getDeclaredConstructor(new Class[0]);
/* 169 */       constructor.setAccessible(true);
/* 170 */       validatorInstance = constructor.newInstance(new Object[0]);
/* 171 */     } catch (ReflectiveOperationException ex) {
/* 172 */       throw new ReflectionException("Cannot create a converter for field " + field, ex);
/*     */     } 
/* 174 */     if (!validatorInstance.test(value)) {
/* 175 */       throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecClassInArray spec) {
/* 182 */     checkNotNull(field, value);
/* 183 */     Class<?> valueClass = value.getClass();
/* 184 */     if (spec.strict())
/* 185 */     { for (Class<?> aClass : spec.value()) {
/* 186 */         if (aClass.isAssignableFrom(valueClass))
/*     */           return; 
/*     */       }  }
/* 189 */     else { for (Class<?> aClass : spec.value()) {
/* 190 */         if (aClass.equals(valueClass))
/*     */           return; 
/*     */       }  }
/* 193 */      throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecStringInRange spec) {
/* 199 */     checkClass(field, value, String.class);
/* 200 */     String s = (String)value;
/* 201 */     if (s.compareTo(spec.min()) < 0 || s.compareTo(spec.max()) > 0) {
/* 202 */       throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecEnum spec) {
/* 210 */     EnumGetMethod m = spec.method();
/* 211 */     Class<?> fieldType = field.getType();
/* 212 */     if (!fieldType.isEnum()) {
/* 213 */       throw new InvalidValueException("Field %s is annotated with @SpecEnum but isn't of type enum", new Object[] { field });
/*     */     }
/*     */     
/* 216 */     Class<? extends Enum> t = (Class)fieldType;
/* 217 */     if (!m.validate(value, t)) {
/* 218 */       throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecStringInArray spec) {
/* 224 */     checkClass(field, value, String.class);
/* 225 */     String s = (String)value;
/* 226 */     if (spec.ignoreCase())
/* 227 */     { for (String acceptable : spec.value()) {
/* 228 */         if (s.equalsIgnoreCase(acceptable))
/*     */           return; 
/*     */       }  }
/* 231 */     else { for (String acceptable : spec.value()) {
/* 232 */         if (s.equals(acceptable))
/*     */           return; 
/*     */       }  }
/* 235 */      throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecDoubleInRange spec) {
/* 240 */     checkClass(field, value, Double.class);
/* 241 */     double d = ((Double)value).doubleValue();
/* 242 */     if (d < spec.min() || d > spec.max()) {
/* 243 */       throw new InvalidValueException("Invalid value %f for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecFloatInRange spec) {
/* 249 */     checkClass(field, value, Float.class);
/* 250 */     float d = ((Float)value).floatValue();
/* 251 */     if (d < spec.min() || d > spec.max()) {
/* 252 */       throw new InvalidValueException("Invalid value %f for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecLongInRange spec) {
/* 258 */     checkClass(field, value, Long.class);
/* 259 */     long d = ((Long)value).longValue();
/* 260 */     if (d < spec.min() || d > spec.max()) {
/* 261 */       throw new InvalidValueException("Invalid value %d for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkFieldSpec(Field field, Object value, SpecIntInRange spec) {
/* 267 */     checkClass(field, value, Integer.class);
/* 268 */     int d = ((Integer)value).intValue();
/* 269 */     if (d < spec.min() || d > spec.max()) {
/* 270 */       throw new InvalidValueException("Invalid value %d for field %s: it doesn't conform to %s", new Object[] { value, field, spec });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkNotNull(Field field, Object value) {
/* 276 */     if (value == null) {
/* 277 */       throw new InvalidValueException("Invalid null value for field %s", new Object[] { field });
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkClass(Field field, Object value, Class<?> expectedClass) {
/* 282 */     checkNotNull(field, value);
/* 283 */     Class<?> valueClass = value.getClass();
/* 284 */     if (valueClass != expectedClass)
/* 285 */       throw new InvalidValueException("Invalid type %s for field %s, expected %s", new Object[] { valueClass, field, expectedClass }); 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\AnnotationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */