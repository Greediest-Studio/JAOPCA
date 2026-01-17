/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.function.Supplier;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FormatDetector
/*    */ {
/* 19 */   private static final Map<String, Supplier<ConfigFormat<?>>> registry = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerExtension(String fileExtension, ConfigFormat<?> format) {
/* 28 */     registry.put(fileExtension, () -> format);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerExtension(String fileExtension, Supplier<ConfigFormat<?>> formatSupplier) {
/* 39 */     registry.put(fileExtension, formatSupplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ConfigFormat<?> detect(File file) {
/* 49 */     return detectByName(file.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ConfigFormat<?> detect(Path file) {
/* 59 */     return detectByName(file.getFileName().toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ConfigFormat<?> detectByName(String fileName) {
/* 69 */     List<String> splitted = StringUtils.split(fileName, '.');
/* 70 */     String fileExtension = splitted.get(splitted.size() - 1);
/* 71 */     Supplier<ConfigFormat<?>> supplier = registry.get(fileExtension);
/* 72 */     return (supplier == null) ? null : supplier.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 78 */     tryLoad("thelm.com.electronwill.nightconfig.toml.TomlFormat");
/* 79 */     tryLoad("com.electronwill.nightconfig.hocon.HoconFormat");
/* 80 */     tryLoad("com.electronwill.nightconfig.json.JsonFormat");
/* 81 */     tryLoad("com.electronwill.nightconfig.yaml.YamlFormat");
/*    */   }
/*    */   
/*    */   private static void tryLoad(String className) {
/*    */     try {
/* 86 */       Class.forName(className);
/* 87 */     } catch (ClassNotFoundException classNotFoundException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\FormatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */