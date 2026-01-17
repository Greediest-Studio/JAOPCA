/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.OptionalLong;
/*     */ import java.util.Set;
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.function.LongSupplier;
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
/*     */ public interface UnmodifiableConfig
/*     */ {
/*     */   default <T> T get(String path) {
/*  25 */     return get(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> T get(List<String> path) {
/*  36 */     Object raw = getRaw(path);
/*  37 */     return (raw == NullObject.NULL_OBJECT) ? null : (T)raw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> T getRaw(String path) {
/*  48 */     return getRaw(StringUtils.split(path, '.'));
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
/*     */   <T> T getRaw(List<String> paramList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> Optional<T> getOptional(String path) {
/*  69 */     return getOptional(StringUtils.split(path, '.'));
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
/*     */   default <T> Optional<T> getOptional(List<String> path) {
/*  81 */     return Optional.ofNullable(get(path));
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
/*     */   default <T> T getOrElse(String path, T defaultValue) {
/*  93 */     return getOrElse(StringUtils.split(path, '.'), defaultValue);
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
/*     */   default <T> T getOrElse(List<String> path, T defaultValue) {
/* 105 */     T value = getRaw(path);
/* 106 */     return (value == null || value == NullObject.NULL_OBJECT) ? defaultValue : value;
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
/*     */   default <T> T getOrElse(List<String> path, Supplier<T> defaultValueSupplier) {
/* 118 */     T value = getRaw(path);
/* 119 */     return (value == null || value == NullObject.NULL_OBJECT) ? defaultValueSupplier.get() : value;
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
/*     */   default <T> T getOrElse(String path, Supplier<T> defaultValueSupplier) {
/* 131 */     return getOrElse(StringUtils.split(path, '.'), defaultValueSupplier);
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
/*     */   default <T extends Enum<T>> T getEnum(String path, Class<T> enumType, EnumGetMethod method) {
/* 149 */     return getEnum(StringUtils.split(path, '.'), enumType, method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnum(String path, Class<T> enumType) {
/* 157 */     return getEnum(StringUtils.split(path, '.'), enumType, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> T getEnum(List<String> path, Class<T> enumType, EnumGetMethod method) {
/* 174 */     Object value = getRaw(path);
/* 175 */     return method.get(value, enumType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnum(List<String> path, Class<T> enumType) {
/* 183 */     return getEnum(path, enumType, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> Optional<T> getOptionalEnum(String path, Class<T> enumType, EnumGetMethod method) {
/* 200 */     return getOptionalEnum(StringUtils.split(path, '.'), enumType, method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> Optional<T> getOptionalEnum(String path, Class<T> enumType) {
/* 208 */     return getOptionalEnum(path, enumType, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> Optional<T> getOptionalEnum(List<String> path, Class<T> enumType, EnumGetMethod method) {
/* 225 */     return Optional.ofNullable(getEnum(path, enumType, method));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> Optional<T> getOptionalEnum(List<String> path, Class<T> enumType) {
/* 233 */     return getOptionalEnum(path, enumType, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> T getEnumOrElse(String path, T defaultValue, EnumGetMethod method) {
/* 250 */     return getEnumOrElse(StringUtils.split(path, '.'), defaultValue, method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnumOrElse(String path, T defaultValue) {
/* 258 */     return getEnumOrElse(path, defaultValue, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> T getEnumOrElse(List<String> path, T defaultValue, EnumGetMethod method) {
/* 275 */     T value = getEnum(path, defaultValue.getDeclaringClass(), method);
/* 276 */     return (value == null) ? defaultValue : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnumOrElse(List<String> path, T defaultValue) {
/* 284 */     return getEnumOrElse(path, defaultValue, EnumGetMethod.NAME_IGNORECASE);
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
/*     */   default <T extends Enum<T>> T getEnumOrElse(String path, Class<T> enumType, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 304 */     return getEnumOrElse(StringUtils.split(path, '.'), enumType, method, defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnumOrElse(String path, Class<T> enumType, Supplier<T> defaultValueSupplier) {
/* 314 */     return getEnumOrElse(path, enumType, EnumGetMethod.NAME_IGNORECASE, defaultValueSupplier);
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
/*     */   default <T extends Enum<T>> T getEnumOrElse(List<String> path, Class<T> enumType, EnumGetMethod method, Supplier<T> defaultValueSupplier) {
/* 335 */     T value = getEnum(path, enumType, method);
/* 336 */     return (value == null) ? defaultValueSupplier.get() : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T extends Enum<T>> T getEnumOrElse(List<String> path, Class<T> enumType, Supplier<T> defaultValueSupplier) {
/* 346 */     return getEnumOrElse(path, enumType, EnumGetMethod.NAME_IGNORECASE, defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getInt(String path) {
/* 355 */     return ((Number)get(path)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getInt(List<String> path) {
/* 363 */     return ((Number)getRaw(path)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default OptionalInt getOptionalInt(String path) {
/* 371 */     return getOptionalInt(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default OptionalInt getOptionalInt(List<String> path) {
/* 379 */     Number n = get(path);
/* 380 */     return (n == null) ? OptionalInt.empty() : OptionalInt.of(n.intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getIntOrElse(String path, int defaultValue) {
/* 388 */     return getIntOrElse(StringUtils.split(path, '.'), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getIntOrElse(List<String> path, int defaultValue) {
/* 396 */     Number n = get(path);
/* 397 */     return (n == null) ? defaultValue : n.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getIntOrElse(String path, IntSupplier defaultValueSupplier) {
/* 405 */     return getIntOrElse(StringUtils.split(path, '.'), defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getIntOrElse(List<String> path, IntSupplier defaultValueSupplier) {
/* 413 */     Number n = get(path);
/* 414 */     return (n == null) ? defaultValueSupplier.getAsInt() : n.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLong(String path) {
/* 423 */     return ((Number)getRaw(path)).longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLong(List<String> path) {
/* 431 */     return ((Number)getRaw(path)).longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default OptionalLong getOptionalLong(String path) {
/* 439 */     return getOptionalLong(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default OptionalLong getOptionalLong(List<String> path) {
/* 447 */     Number n = get(path);
/* 448 */     return (n == null) ? OptionalLong.empty() : OptionalLong.of(n.longValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLongOrElse(String path, long defaultValue) {
/* 456 */     return getLongOrElse(StringUtils.split(path, '.'), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLongOrElse(List<String> path, long defaultValue) {
/* 464 */     Number n = get(path);
/* 465 */     return (n == null) ? defaultValue : n.longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLongOrElse(String path, LongSupplier defaultValueSupplier) {
/* 473 */     return getLongOrElse(StringUtils.split(path, '.'), defaultValueSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getLongOrElse(List<String> path, LongSupplier defaultValueSupplier) {
/* 481 */     Number n = get(path);
/* 482 */     return (n == null) ? defaultValueSupplier.getAsLong() : n.longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   default byte getByte(String path) {
/* 487 */     return ((Number)getRaw(path)).byteValue();
/*     */   }
/*     */   
/*     */   default byte getByte(List<String> path) {
/* 491 */     return ((Number)getRaw(path)).byteValue();
/*     */   }
/*     */   
/*     */   default byte getByteOrElse(String path, byte defaultValue) {
/* 495 */     return getByteOrElse(StringUtils.split(path, '.'), defaultValue);
/*     */   }
/*     */   
/*     */   default byte getByteOrElse(List<String> path, byte defaultValue) {
/* 499 */     Number n = get(path);
/* 500 */     return (n == null) ? defaultValue : n.byteValue();
/*     */   }
/*     */ 
/*     */   
/*     */   default short getShort(String path) {
/* 505 */     return ((Number)getRaw(path)).shortValue();
/*     */   }
/*     */   
/*     */   default short getShort(List<String> path) {
/* 509 */     return ((Number)getRaw(path)).shortValue();
/*     */   }
/*     */   
/*     */   default short getShortOrElse(String path, short defaultValue) {
/* 513 */     return getShortOrElse(StringUtils.split(path, '.'), defaultValue);
/*     */   }
/*     */   
/*     */   default short getShortOrElse(List<String> path, short defaultValue) {
/* 517 */     Number n = get(path);
/* 518 */     return (n == null) ? defaultValue : n.shortValue();
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
/*     */   default char getChar(String path) {
/* 534 */     return (char)getInt(path);
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
/*     */   default char getChar(List<String> path) {
/* 548 */     Object value = getRaw(path);
/* 549 */     if (value instanceof Number)
/* 550 */       return (char)((Number)value).intValue(); 
/* 551 */     if (value instanceof CharSequence) {
/* 552 */       return ((CharSequence)value).charAt(0);
/*     */     }
/* 554 */     return ((Character)value).charValue();
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
/*     */   default char getCharOrElse(String path, char defaultValue) {
/* 571 */     return getCharOrElse(StringUtils.split(path, '.'), defaultValue);
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
/*     */   default char getCharOrElse(List<String> path, char defaultValue) {
/* 587 */     Object value = getRaw(path);
/* 588 */     if (value == null || value == NullObject.NULL_OBJECT)
/* 589 */       return defaultValue; 
/* 590 */     if (value instanceof Number)
/* 591 */       return (char)((Number)value).intValue(); 
/* 592 */     if (value instanceof CharSequence) {
/* 593 */       return ((CharSequence)value).charAt(0);
/*     */     }
/* 595 */     return ((Character)value).charValue();
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
/*     */   default boolean contains(String path) {
/* 608 */     return contains(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean contains(List<String> paramList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isNull(String path) {
/* 627 */     return isNull(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isNull(List<String> path) {
/* 638 */     return (getRaw(path) == NullObject.NULL_OBJECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isEmpty() {
/* 655 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Map<String, Object> valueMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Set<? extends Entry> entrySet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConfigFormat<?> configFormat();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Entry
/*     */   {
/*     */     String getKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     <T> T getRawValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default <T> T getValue() {
/* 696 */       Object raw = getRawValue();
/* 697 */       return (raw == NullObject.NULL_OBJECT) ? null : (T)raw;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default boolean isNull() {
/* 704 */       return (getRawValue() == NullObject.NULL_OBJECT);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default <T> Optional<T> getOptional() {
/* 712 */       return Optional.ofNullable(getValue());
/*     */     }
/*     */     
/*     */     default <T> T getOrElse(T defaultValue) {
/* 716 */       T value = getRawValue();
/* 717 */       return (value == null || value == NullObject.NULL_OBJECT) ? defaultValue : value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default int getInt() {
/* 726 */       return ((Number)getRawValue()).intValue();
/*     */     }
/*     */     
/*     */     default OptionalInt getOptionalInt() {
/* 730 */       Number value = getRawValue();
/* 731 */       return (value == null) ? OptionalInt.empty() : OptionalInt.of(value.intValue());
/*     */     }
/*     */     
/*     */     default int getIntOrElse(int defaultValue) {
/* 735 */       Number value = getRawValue();
/* 736 */       return (value == null) ? defaultValue : value.intValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default long getLong() {
/* 745 */       return ((Number)getRawValue()).longValue();
/*     */     }
/*     */     
/*     */     default OptionalLong getOptionalLong() {
/* 749 */       Number value = getRawValue();
/* 750 */       return (value == null) ? OptionalLong.empty() : OptionalLong.of(value.longValue());
/*     */     }
/*     */     
/*     */     default long getLongOrElse(long defaultValue) {
/* 754 */       Number value = getRawValue();
/* 755 */       return (value == null) ? defaultValue : value.longValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default byte getByte() {
/* 764 */       return ((Number)getRawValue()).byteValue();
/*     */     }
/*     */     
/*     */     default byte getByteOrElse(byte defaultValue) {
/* 768 */       Number value = getRawValue();
/* 769 */       return (value == null) ? defaultValue : value.byteValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     default short getShort() {
/* 776 */       return ((Number)getRawValue()).shortValue();
/*     */     }
/*     */     
/*     */     default short getShortOrElse(short defaultValue) {
/* 780 */       Number value = getRawValue();
/* 781 */       return (value == null) ? defaultValue : value.shortValue();
/*     */     }
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
/*     */     default char getChar() {
/* 794 */       Object value = getRawValue();
/* 795 */       if (value instanceof Number)
/* 796 */         return (char)((Number)value).intValue(); 
/* 797 */       if (value instanceof CharSequence) {
/* 798 */         return ((CharSequence)value).charAt(0);
/*     */       }
/* 800 */       return ((Character)value).charValue();
/*     */     }
/*     */ 
/*     */     
/*     */     default char getCharOrElse(char defaultValue) {
/* 805 */       Object value = getRawValue();
/* 806 */       if (value == null)
/* 807 */         return defaultValue; 
/* 808 */       if (value instanceof Number)
/* 809 */         return (char)((Number)value).intValue(); 
/* 810 */       if (value instanceof CharSequence) {
/* 811 */         return ((CharSequence)value).charAt(0);
/*     */       }
/* 813 */       return ((Character)value).charValue();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> T apply(String path) {
/* 835 */     return get(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> T apply(List<String> path) {
/* 846 */     return get(path);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\UnmodifiableConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */