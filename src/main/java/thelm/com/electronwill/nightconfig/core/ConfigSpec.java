/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Predicate;
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
/*     */ public class ConfigSpec
/*     */ {
/*     */   protected final Config storage;
/*     */   
/*     */   public ConfigSpec() {
/*  65 */     this(Config.inMemoryUniversal());
/*     */   }
/*     */   
/*     */   ConfigSpec(Config storage) {
/*  69 */     this.storage = storage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void define(String path, Object defaultValue) {
/*  80 */     define(StringUtils.split(path, '.'), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void define(List<String> path, Object defaultValue) {
/*  91 */     define(path, defaultValue, o -> 
/*  92 */         (o != null && defaultValue.getClass().isAssignableFrom(o.getClass())));
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
/*     */   public void define(String path, Object defaultValue, Predicate<Object> validator) {
/* 104 */     define(StringUtils.split(path, '.'), defaultValue, validator);
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
/*     */   public void define(String path, Supplier<?> defaultValueSupplier, Predicate<Object> validator) {
/* 116 */     define(StringUtils.split(path, '.'), defaultValueSupplier, validator);
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
/*     */   public void define(List<String> path, Object defaultValue, Predicate<Object> validator) {
/* 128 */     this.storage.set(path, new ValueSpec(defaultValue, validator));
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
/*     */   public void define(List<String> path, Supplier<?> defaultValueSupplier, Predicate<Object> validator) {
/* 141 */     this.storage.set(path, new ValueSpec(defaultValueSupplier, validator));
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
/*     */   public <V> void defineOfClass(String path, V defaultValue, Class<? super V> acceptableValueClass) {
/* 154 */     defineOfClass(StringUtils.split(path, '.'), new DumbSupplier<>(defaultValue), acceptableValueClass);
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
/*     */   public <V> void defineOfClass(String path, Supplier<V> defaultValueSupplier, Class<? super V> acceptableValueClass) {
/* 167 */     defineOfClass(StringUtils.split(path, '.'), defaultValueSupplier, acceptableValueClass);
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
/*     */   public <V> void defineOfClass(List<String> path, V defaultValue, Class<? super V> acceptableValueClass) {
/* 180 */     defineOfClass(path, new DumbSupplier<>(defaultValue), acceptableValueClass);
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
/*     */   public <V> void defineOfClass(List<String> path, Supplier<V> defaultValueSupplier, Class<? super V> acceptableValueClass) {
/* 193 */     define(path, defaultValueSupplier, o -> 
/* 194 */         (o != null && acceptableValueClass.isAssignableFrom(o.getClass())));
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
/*     */   public void defineInList(String path, Object defaultValue, Collection<?> acceptableValues) {
/* 206 */     defineInList(StringUtils.split(path, '.'), defaultValue, acceptableValues);
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
/*     */   public void defineInList(String path, Supplier<?> defaultValueSupplier, Collection<?> acceptableValues) {
/* 219 */     defineInList(StringUtils.split(path, '.'), defaultValueSupplier, acceptableValues);
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
/*     */   public void defineInList(List<String> path, Object defaultValue, Collection<?> acceptableValues) {
/* 232 */     defineInList(path, new DumbSupplier(defaultValue), acceptableValues);
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
/*     */   public void defineInList(List<String> path, Supplier<?> defaultValueSupplier, Collection<?> acceptableValues) {
/* 245 */     Objects.requireNonNull(acceptableValues); define(path, defaultValueSupplier, acceptableValues::contains);
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
/*     */   public <V extends Comparable<? super V>> void defineInRange(String path, V defaultValue, V min, V max) {
/* 259 */     defineInRange(StringUtils.split(path, '.'), defaultValue, min, max);
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
/*     */   public <V extends Comparable<? super V>> void defineInRange(String path, Supplier<V> defaultValueSupplier, V min, V max) {
/* 274 */     defineInRange(StringUtils.split(path, '.'), defaultValueSupplier, min, max);
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
/*     */   public <V extends Comparable<? super V>> void defineInRange(List<String> path, V defaultValue, V min, V max) {
/* 288 */     defineInRange(path, new DumbSupplier<>(defaultValue), min, max);
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
/*     */   public <V extends Comparable<? super V>> void defineInRange(List<String> path, Supplier<V> defaultValueSupplier, V min, V max) {
/* 303 */     if (min.compareTo(max) > 0) {
/* 304 */       throw new IllegalArgumentException("The minimum must be less than the maximum.");
/*     */     }
/* 306 */     define(path, defaultValueSupplier, o -> {
/*     */           if (!(o instanceof Comparable))
/*     */             return false;  Comparable<V> c = (Comparable<V>)o;
/*     */           try {
/* 310 */             return (c.compareTo((V)min) >= 0 && c.compareTo((V)max) <= 0);
/* 311 */           } catch (ClassCastException ex) {
/*     */             return false;
/*     */           } 
/*     */         });
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
/*     */   public void defineList(String path, List<?> defaultValue, Predicate<Object> elementValidator) {
/* 328 */     defineList(StringUtils.split(path, '.'), defaultValue, elementValidator);
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
/*     */   public void defineList(String path, Supplier<List<?>> defaultValueSupplier, Predicate<Object> elementValidator) {
/* 343 */     defineList(StringUtils.split(path, '.'), defaultValueSupplier, elementValidator);
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
/*     */   public void defineList(List<String> path, List<?> defaultValue, Predicate<Object> elementValidator) {
/* 357 */     defineList(path, new DumbSupplier<>(defaultValue), elementValidator);
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
/*     */   public void defineList(List<String> path, Supplier<List<?>> defaultValueSupplier, Predicate<Object> elementValidator) {
/* 372 */     define(path, defaultValueSupplier, o -> {
/*     */           if (!(o instanceof List)) {
/*     */             return false;
/*     */           }
/*     */           List<?> list = (List)o;
/*     */           for (Object element : list) {
/*     */             if (!elementValidator.test(element))
/*     */               return false; 
/*     */           } 
/*     */           return true;
/*     */         });
/*     */   }
/*     */   
/*     */   public <T extends Enum<T>> void defineEnum(String path, T defaultValue, EnumGetMethod method) {
/* 386 */     defineEnum(StringUtils.split(path, '.'), defaultValue, method);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineEnum(List<String> path, T defaultValue, EnumGetMethod method) {
/* 392 */     defineEnum(path, defaultValue.getDeclaringClass(), method, new DumbSupplier<>(defaultValue));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineEnum(String path, Class<T> enumType, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 399 */     defineEnum(StringUtils.split(path, '.'), enumType, method, defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineEnum(List<String> path, Class<T> enumType, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 406 */     define(path, defaultValueSupplier, o -> method.validate(o, enumType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineRestrictedEnum(String path, T defaultValue, Collection<T> acceptableValues, EnumGetMethod method) {
/* 416 */     defineRestrictedEnum(StringUtils.split(path, '.'), defaultValue, acceptableValues, method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineRestrictedEnum(List<String> path, T defaultValue, Collection<T> acceptableValues, EnumGetMethod method) {
/* 423 */     defineRestrictedEnum(path, defaultValue.getDeclaringClass(), acceptableValues, method, new DumbSupplier<>(defaultValue));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineRestrictedEnum(String path, Class<T> enumType, Collection<T> acceptableValues, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 431 */     defineRestrictedEnum(StringUtils.split(path, '.'), enumType, acceptableValues, method, defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> void defineRestrictedEnum(List<String> path, Class<T> enumType, Collection<T> acceptableValues, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 439 */     define(path, defaultValueSupplier, o -> 
/* 440 */         (method.validate(o, enumType) && acceptableValues.contains(method.get(o, enumType))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undefine(String path) {
/* 450 */     undefine(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undefine(List<String> path) {
/* 459 */     this.storage.remove(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefined(String path) {
/* 469 */     return isDefined(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefined(List<String> path) {
/* 479 */     return this.storage.contains(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrect(String path, Object value) {
/* 490 */     return isCorrect(StringUtils.split(path, '.'), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrect(List<String> path, Object value) {
/* 501 */     ValueSpec spec = this.storage.<ValueSpec>getRaw(path);
/* 502 */     return spec.validator.test(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrect(Config config) {
/* 512 */     return isCorrect(config.valueMap(), this.storage.valueMap());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCorrect(Map<String, Object> configMap, Map<String, Object> specMap) {
/* 522 */     for (Map.Entry<String, Object> specEntry : specMap.entrySet()) {
/* 523 */       String key = specEntry.getKey();
/* 524 */       Object specValue = specEntry.getValue();
/* 525 */       Object configValue = configMap.get(key);
/* 526 */       if (configValue == null) {
/* 527 */         return false;
/*     */       }
/* 529 */       if (specValue instanceof Config) {
/* 530 */         if (!(configValue instanceof Config)) {
/* 531 */           return false;
/*     */         }
/* 533 */         if (!isCorrect(((Config)configValue).valueMap(), ((Config)specValue).valueMap()))
/* 534 */           return false; 
/*     */         continue;
/*     */       } 
/* 537 */       ValueSpec valueSpec = (ValueSpec)specValue;
/* 538 */       if (!valueSpec.validator.test(configValue)) {
/* 539 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 544 */     for (Map.Entry<String, Object> configEntry : configMap.entrySet()) {
/* 545 */       String key = configEntry.getKey();
/* 546 */       Object specValue = specMap.get(key);
/* 547 */       if (specValue == null) {
/* 548 */         return false;
/*     */       }
/*     */     } 
/* 551 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object correct(String path, Object value) {
/* 562 */     return correct(StringUtils.split(path, '.'), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object correct(List<String> path, Object value) {
/* 573 */     ValueSpec spec = this.storage.<ValueSpec>getRaw(path);
/* 574 */     return spec.validator.test(value) ? value : spec.defaultValueSupplier.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int correct(Config config) {
/* 584 */     return correct(config, (action, path, incorrectValue, correctedValue) -> {
/*     */         
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int correct(Config config, CorrectionListener listener) {
/* 596 */     Objects.requireNonNull(config); return correct(config.valueMap(), this.storage.valueMap(), new ArrayList<>(), listener, config::createSubConfig);
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
/*     */   private int correct(Map<String, Object> configMap, Map<String, Object> specMap, List<String> parentPath, CorrectionListener listener, Supplier<Config> subConfigSupplier) {
/* 613 */     int count = 0;
/*     */     
/* 615 */     for (Map.Entry<String, Object> specEntry : specMap.entrySet()) {
/* 616 */       String key = specEntry.getKey();
/* 617 */       Object specValue = specEntry.getValue();
/*     */       
/* 619 */       Object configValue = configMap.get(key);
/* 620 */       if (specValue instanceof Config) {
/* 621 */         if (!(configValue instanceof Config)) {
/*     */           
/* 623 */           Config newValue = subConfigSupplier.get();
/* 624 */           configMap.put(key, newValue);
/*     */           
/* 626 */           CorrectionAction correctionAction = (configValue == null) ? CorrectionAction.ADD : CorrectionAction.REPLACE;
/* 627 */           handleCorrection(parentPath, key, configValue, newValue, listener, correctionAction);
/*     */           
/* 629 */           count++;
/* 630 */           configValue = newValue;
/*     */         } 
/*     */ 
/*     */         
/* 634 */         parentPath.add(key);
/* 635 */         Map<String, Object> configValueMap = ((Config)configValue).valueMap();
/* 636 */         Map<String, Object> specValueMap = ((Config)specValue).valueMap();
/* 637 */         count += correct(configValueMap, specValueMap, parentPath, listener, subConfigSupplier);
/* 638 */         parentPath.remove(parentPath.size() - 1);
/*     */         continue;
/*     */       } 
/* 641 */       ValueSpec valueSpec = (ValueSpec)specValue;
/* 642 */       if (!valueSpec.validator.test(configValue)) {
/*     */         
/* 644 */         Object newValue = valueSpec.defaultValueSupplier.get();
/* 645 */         configMap.put(key, newValue);
/*     */         
/* 647 */         CorrectionAction correctionAction = (configValue == null) ? CorrectionAction.ADD : CorrectionAction.REPLACE;
/* 648 */         handleCorrection(parentPath, key, configValue, newValue, listener, correctionAction);
/*     */         
/* 650 */         count++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 655 */     for (Iterator<Map.Entry<String, Object>> it = configMap.entrySet().iterator(); it.hasNext(); ) {
/* 656 */       Map.Entry<String, Object> configEntry = it.next();
/* 657 */       String key = configEntry.getKey();
/* 658 */       Object configValue = configEntry.getValue();
/* 659 */       Object specValue = specMap.get(key);
/* 660 */       if (specValue == null) {
/*     */         
/* 662 */         it.remove();
/* 663 */         handleCorrection(parentPath, key, configValue, null, listener, CorrectionAction.REMOVE);
/* 664 */         count++;
/*     */       } 
/*     */     } 
/* 667 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleCorrection(List<String> parentPath, String key, Object value, Object newValue, CorrectionListener listener, CorrectionAction action) {
/* 676 */     parentPath.add(key);
/* 677 */     List<String> valuePath = Collections.unmodifiableList(parentPath);
/*     */     
/* 679 */     listener.onCorrect(action, valuePath, value, newValue);
/* 680 */     parentPath.remove(parentPath.size() - 1);
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
/*     */   public enum CorrectionAction
/*     */   {
/* 710 */     ADD,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 716 */     REPLACE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 722 */     REMOVE;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class ValueSpec
/*     */   {
/*     */     private final Supplier<?> defaultValueSupplier;
/*     */     
/*     */     private final Predicate<Object> validator;
/*     */     
/*     */     private ValueSpec(Object defaultValue, Predicate<Object> validator) {
/* 733 */       this(new ConfigSpec.DumbSupplier(
/* 734 */             Objects.requireNonNull(defaultValue, "The default value must not be null."), null), validator);
/*     */     }
/*     */ 
/*     */     
/*     */     private ValueSpec(Supplier<?> defaultValueSupplier, Predicate<Object> validator) {
/* 739 */       this.defaultValueSupplier = Objects.<Supplier>requireNonNull(defaultValueSupplier, "The supplier of the default value must not be null.");
/*     */       
/* 741 */       this.validator = Objects.<Predicate<Object>>requireNonNull(validator, "The validator must not be null.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class DumbSupplier<T>
/*     */     implements Supplier<T>
/*     */   {
/*     */     private final T value;
/*     */ 
/*     */     
/*     */     private DumbSupplier(T value) {
/* 753 */       this.value = value;
/*     */     }
/*     */     
/*     */     public T get() {
/* 757 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface CorrectionListener {
/*     */     void onCorrect(ConfigSpec.CorrectionAction param1CorrectionAction, List<String> param1List, Object param1Object1, Object param1Object2);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\ConfigSpec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */