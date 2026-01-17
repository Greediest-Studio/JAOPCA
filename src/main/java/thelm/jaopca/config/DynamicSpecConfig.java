/*     */ package thelm.jaopca.config;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.function.DoublePredicate;
/*     */ import java.util.function.IntPredicate;
/*     */ import java.util.function.LongPredicate;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.EnumGetMethod;
/*     */ import thelm.com.electronwill.nightconfig.core.file.FileConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ 
/*     */ public class DynamicSpecConfig
/*     */   extends CommentedConfigWrapper<CommentedConfig>
/*     */   implements IDynamicSpecConfig {
/*  33 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   final CommentedConfig config;
/*     */   
/*     */   public DynamicSpecConfig(CommentedConfig config) {
/*  38 */     super(config);
/*  39 */     this.config = config;
/*  40 */     if (config instanceof FileConfig) {
/*  41 */       FileConfig fileConfig = (FileConfig)config;
/*  42 */       Path path = fileConfig.getNioPath();
/*  43 */       String fileName = path.getFileName().toString();
/*  44 */       String oldFileName = Arrays.<String>stream(StringUtils.split(fileName, '_')).map(StringUtils::capitalize).collect(Collectors.joining());
/*  45 */       Path oldPath = path.resolveSibling(oldFileName);
/*  46 */       if (Files.exists(oldPath, new java.nio.file.LinkOption[0])) {
/*     */         try {
/*  48 */           Path realPath = oldPath.toRealPath(new java.nio.file.LinkOption[0]);
/*  49 */           String realFileName = realPath.getFileName().toString();
/*  50 */           if (!realFileName.equals(fileName)) {
/*  51 */             LOGGER.debug("Moving config with path {} to path {}", oldPath, path);
/*  52 */             Path tempPath = Files.createTempFile(path.getParent(), null, null, (FileAttribute<?>[])new FileAttribute[0]);
/*  53 */             Files.move(oldPath, tempPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*  54 */             Files.move(tempPath, path, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */           }
/*     */         
/*  57 */         } catch (Exception e) {
/*  58 */           LOGGER.error("Unable to move config with path {}", oldPath, e);
/*     */         } 
/*     */       }
/*     */       try {
/*  62 */         fileConfig.load();
/*     */       }
/*  64 */       catch (ParsingException e) {
/*  65 */         LOGGER.warn("Config with path {} is malformed, moving", path);
/*     */         try {
/*  67 */           Files.move(path, path.resolveSibling(fileName + ".bak"), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */         }
/*  69 */         catch (Exception e1) {
/*  70 */           LOGGER.error("Unable to move config with path {}", path, e1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(String path, String defaultValue, String comment) {
/*  78 */     return getDefinedString(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(List<String> path, String defaultValue, String comment) {
/*  83 */     return getDefinedString(path, defaultValue, (Predicate<String>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(String path, String defaultValue, Collection<String> validValues, String comment) {
/*  88 */     return getDefinedString(split(path), defaultValue, validValues, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(List<String> path, String defaultValue, Collection<String> validValues, String comment) {
/*  93 */     return getDefinedString(path, defaultValue, validValues::contains, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(String path, String defaultValue, Predicate<String> validator, String comment) {
/*  98 */     return getDefinedString(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefinedString(List<String> path, String defaultValue, Predicate<String> validator, String comment) {
/* 103 */     if (!this.config.contains(path) || !validator.test("" + this.config.get(path))) {
/* 104 */       this.config.set(path, defaultValue);
/*     */     }
/* 106 */     if (comment != null) {
/* 107 */       this.config.setComment(path, comment);
/*     */     }
/* 109 */     return "" + this.config.get(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(String path, List<String> defaultValue, String comment) {
/* 114 */     return getDefinedStringList(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(List<String> path, List<String> defaultValue, String comment) {
/* 119 */     return getDefinedStringList(path, defaultValue, (Predicate<String>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(String path, List<String> defaultValue, Collection<String> validValues, String comment) {
/* 124 */     return getDefinedStringList(split(path), defaultValue, validValues, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(List<String> path, List<String> defaultValue, Collection<String> validValues, String comment) {
/* 129 */     return getDefinedStringList(path, defaultValue, validValues::contains, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(String path, List<String> defaultValue, Predicate<String> elementValidator, String comment) {
/* 134 */     return getDefinedStringList(split(path), defaultValue, elementValidator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDefinedStringList(List<String> path, List<String> defaultValue, Predicate<String> elementValidator, String comment) {
/* 139 */     if (!this.config.contains(path) || !(this.config.get(path) instanceof List)) {
/* 140 */       this.config.set(path, defaultValue);
/*     */     }
/* 142 */     List<?> list = (List)this.config.get(path);
/* 143 */     list.removeIf(obj -> !elementValidator.test("" + obj));
/* 144 */     if (comment != null) {
/* 145 */       this.config.setComment(path, comment);
/*     */     }
/* 147 */     return (List<String>)list.stream().map(Object::toString).collect(Collectors.toCollection(java.util.ArrayList::new));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDefinedBoolean(String path, boolean defaultValue, String comment) {
/* 152 */     return getDefinedBoolean(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDefinedBoolean(List<String> path, boolean defaultValue, String comment) {
/* 157 */     if (!this.config.contains(path) || !(this.config.get(path) instanceof Boolean)) {
/* 158 */       this.config.set(path, Boolean.valueOf(defaultValue));
/*     */     }
/* 160 */     if (comment != null) {
/* 161 */       this.config.setComment(path, comment);
/*     */     }
/* 163 */     return ((Boolean)this.config.get(path)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getDefinedNumber(String path, Number defaultValue, String comment) {
/* 168 */     return getDefinedNumber(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getDefinedNumber(List<String> path, Number defaultValue, String comment) {
/* 173 */     return getDefinedNumber(path, defaultValue, (Predicate<Number>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getDefinedNumber(String path, Number defaultValue, Predicate<Number> validator, String comment) {
/* 178 */     return getDefinedNumber(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getDefinedNumber(List<String> path, Number defaultValue, Predicate<Number> validator, String comment) {
/* 183 */     if (!this.config.contains(path) || !(this.config.get(path) instanceof Number) || !validator.test(Integer.valueOf(this.config.getInt(path)))) {
/* 184 */       this.config.set(path, defaultValue);
/*     */     }
/* 186 */     if (comment != null) {
/* 187 */       this.config.setComment(path, comment);
/*     */     }
/* 189 */     return (Number)this.config.get(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(String path, int defaultValue, String comment) {
/* 194 */     return getDefinedInt(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(List<String> path, int defaultValue, String comment) {
/* 199 */     return getDefinedInt(path, defaultValue, value -> true, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(String path, int defaultValue, int min, int max, String comment) {
/* 204 */     return getDefinedInt(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(List<String> path, int defaultValue, int min, int max, String comment) {
/* 209 */     return getDefinedInt(path, defaultValue, value -> (value >= min && value <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(String path, int defaultValue, IntPredicate validator, String comment) {
/* 214 */     return getDefinedInt(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinedInt(List<String> path, int defaultValue, IntPredicate validator, String comment) {
/* 219 */     return getDefinedNumber(path, Integer.valueOf(defaultValue), n -> validator.test(n.intValue()), comment).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(String path, long defaultValue, String comment) {
/* 224 */     return getDefinedLong(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(List<String> path, long defaultValue, String comment) {
/* 229 */     return getDefinedLong(path, defaultValue, value -> true, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(String path, long defaultValue, long min, long max, String comment) {
/* 234 */     return getDefinedLong(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(List<String> path, long defaultValue, long min, long max, String comment) {
/* 239 */     return getDefinedLong(path, defaultValue, value -> (value >= min && value <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(String path, long defaultValue, LongPredicate validator, String comment) {
/* 244 */     return getDefinedLong(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDefinedLong(List<String> path, long defaultValue, LongPredicate validator, String comment) {
/* 249 */     return getDefinedNumber(path, Long.valueOf(defaultValue), n -> validator.test(n.longValue()), comment).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(String path, float defaultValue, String comment) {
/* 254 */     return getDefinedFloat(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(List<String> path, float defaultValue, String comment) {
/* 259 */     return getDefinedFloat(path, defaultValue, (Predicate<Float>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(String path, float defaultValue, float min, float max, String comment) {
/* 264 */     return getDefinedFloat(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(List<String> path, float defaultValue, float min, float max, String comment) {
/* 269 */     return getDefinedFloat(path, defaultValue, value -> (value.floatValue() >= min && value.floatValue() <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(String path, float defaultValue, Predicate<Float> validator, String comment) {
/* 274 */     return getDefinedFloat(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefinedFloat(List<String> path, float defaultValue, Predicate<Float> validator, String comment) {
/* 279 */     return getDefinedNumber(path, Float.valueOf(defaultValue), n -> validator.test(Float.valueOf(n.floatValue())), comment).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(String path, double defaultValue, String comment) {
/* 284 */     return getDefinedDouble(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(List<String> path, double defaultValue, String comment) {
/* 289 */     return getDefinedDouble(path, defaultValue, value -> true, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(String path, double defaultValue, double min, double max, String comment) {
/* 294 */     return getDefinedDouble(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(List<String> path, double defaultValue, double min, double max, String comment) {
/* 299 */     return getDefinedDouble(path, defaultValue, value -> (value >= min && value <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(String path, double defaultValue, DoublePredicate validator, String comment) {
/* 304 */     return getDefinedDouble(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDefinedDouble(List<String> path, double defaultValue, DoublePredicate validator, String comment) {
/* 309 */     return getDefinedNumber(path, Double.valueOf(defaultValue), n -> validator.test(n.doubleValue()), comment).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(String path, byte defaultValue, String comment) {
/* 314 */     return getDefinedByte(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(List<String> path, byte defaultValue, String comment) {
/* 319 */     return getDefinedByte(path, defaultValue, (Predicate<Byte>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(String path, byte defaultValue, byte min, byte max, String comment) {
/* 324 */     return getDefinedByte(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(List<String> path, byte defaultValue, byte min, byte max, String comment) {
/* 329 */     return getDefinedByte(path, defaultValue, value -> (value.byteValue() >= min && value.byteValue() <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(String path, byte defaultValue, Predicate<Byte> validator, String comment) {
/* 334 */     return getDefinedByte(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getDefinedByte(List<String> path, byte defaultValue, Predicate<Byte> validator, String comment) {
/* 339 */     return getDefinedNumber(path, Byte.valueOf(defaultValue), n -> validator.test(Byte.valueOf(n.byteValue())), comment).byteValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(String path, short defaultValue, String comment) {
/* 344 */     return getDefinedShort(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(List<String> path, short defaultValue, String comment) {
/* 349 */     return getDefinedShort(path, defaultValue, (Predicate<Short>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(String path, short defaultValue, short min, short max, String comment) {
/* 354 */     return getDefinedShort(split(path), defaultValue, min, max, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(List<String> path, short defaultValue, short min, short max, String comment) {
/* 359 */     return getDefinedShort(path, defaultValue, value -> (value.shortValue() >= min && value.shortValue() <= max), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(String path, short defaultValue, Predicate<Short> validator, String comment) {
/* 364 */     return getDefinedShort(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDefinedShort(List<String> path, short defaultValue, Predicate<Short> validator, String comment) {
/* 369 */     return getDefinedNumber(path, Short.valueOf(defaultValue), n -> validator.test(Short.valueOf(n.shortValue())), comment).shortValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(String path, char defaultValue, String comment) {
/* 374 */     return getDefinedChar(split(path), defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(List<String> path, char defaultValue, String comment) {
/* 379 */     return getDefinedChar(path, defaultValue, (Predicate<Character>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(String path, char defaultValue, Collection<Character> validValues, String comment) {
/* 384 */     return getDefinedChar(split(path), defaultValue, validValues, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(List<String> path, char defaultValue, Collection<Character> validValues, String comment) {
/* 389 */     return getDefinedChar(path, defaultValue, validValues::contains, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(String path, char defaultValue, Predicate<Character> validator, String comment) {
/* 394 */     return getDefinedChar(split(path), defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDefinedChar(List<String> path, char defaultValue, Predicate<Character> validator, String comment) {
/* 399 */     if (!this.config.contains(path) || (!(this.config.get(path) instanceof Number) && !(this.config.get(path) instanceof CharSequence)) || !validator.test(Character.valueOf(this.config.getChar(path)))) {
/* 400 */       this.config.set(path, Character.toString(defaultValue));
/*     */     }
/* 402 */     if (comment != null) {
/* 403 */       this.config.setComment(path, comment);
/*     */     }
/* 405 */     return this.config.getChar(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(String path, Class<T> enumType, T defaultValue, String comment) {
/* 410 */     return getDefinedEnum(split(path), enumType, defaultValue, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(List<String> path, Class<T> enumType, T defaultValue, String comment) {
/* 415 */     return getDefinedEnum(path, enumType, defaultValue, (Predicate<T>)Predicates.alwaysTrue(), comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(String path, Class<T> enumType, T defaultValue, Collection<T> validValues, String comment) {
/* 420 */     return getDefinedEnum(split(path), enumType, defaultValue, validValues, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(List<String> path, Class<T> enumType, T defaultValue, Collection<T> validValues, String comment) {
/* 425 */     return getDefinedEnum(path, enumType, defaultValue, validValues::contains, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(String path, Class<T> enumType, T defaultValue, Predicate<T> validator, String comment) {
/* 430 */     return getDefinedEnum(split(path), enumType, defaultValue, validator, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> T getDefinedEnum(List<String> path, Class<T> enumType, T defaultValue, Predicate<T> validator, String comment) {
/* 435 */     if (!this.config.contains(path) || (!(this.config.get(path) instanceof CharSequence) && !(this.config.get(path) instanceof Number)) || !validator.test((T)this.config.getEnum(path, enumType, EnumGetMethod.ORDINAL_OR_NAME_IGNORECASE))) {
/* 436 */       this.config.set(path, defaultValue.name().toLowerCase(Locale.US));
/*     */     }
/* 438 */     if (comment != null) {
/* 439 */       this.config.setComment(path, comment);
/*     */     }
/* 441 */     return (T)this.config.getEnum(path, enumType, EnumGetMethod.ORDINAL_OR_NAME_IGNORECASE);
/*     */   }
/*     */   
/*     */   static List<String> split(String str) {
/* 445 */     return Lists.newArrayList((Object[])StringUtils.split(str, '.'));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\config\DynamicSpecConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */