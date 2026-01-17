/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.time.temporal.Temporal;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*    */ import thelm.com.electronwill.nightconfig.core.file.FormatDetector;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*    */ 
/*    */ public final class TomlFormat
/*    */   implements ConfigFormat<CommentedConfig> {
/* 15 */   private static final TomlFormat INSTANCE = new TomlFormat();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TomlFormat instance() {
/* 21 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CommentedConfig newConfig() {
/* 28 */     return (CommentedConfig)INSTANCE.createConfig();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CommentedConfig newConfig(Supplier<Map<String, Object>> s) {
/* 35 */     return INSTANCE.createConfig(s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CommentedConfig newConcurrentConfig() {
/* 42 */     return (CommentedConfig)INSTANCE.createConcurrentConfig();
/*    */   }
/*    */   
/*    */   static {
/* 46 */     FormatDetector.registerExtension("toml", INSTANCE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TomlWriter createWriter() {
/* 53 */     return new TomlWriter();
/*    */   }
/*    */ 
/*    */   
/*    */   public TomlParser createParser() {
/* 58 */     return new TomlParser();
/*    */   }
/*    */ 
/*    */   
/*    */   public CommentedConfig createConfig(Supplier<Map<String, Object>> mapCreator) {
/* 63 */     return CommentedConfig.of(mapCreator, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsComments() {
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsType(Class<?> type) {
/* 73 */     return (type != null && (super.supportsType(type) || Temporal.class.isAssignableFrom(type)));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TomlFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */