/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Consumer;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ParsingMode
/*    */ {
/* 17 */   REPLACE(Config::clear, Config::set, Map::put),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   MERGE(c -> {  }Config::set, Map::put), ADD(c -> {  }Config::set, Map::put);
/*    */   private final Consumer<? super Config> preparationAction;
/*    */   private final PutAction putAction;
/*    */   private final MapPutAction mapPutAction;
/*    */   
/*    */   static {
/* 29 */     ADD = new ParsingMode("ADD", 2, c -> {  }(cfg, path, value) -> { cfg.add(path, value); return null; }Map::putIfAbsent);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ParsingMode(Consumer<? super Config> preparationAction, PutAction putAction, MapPutAction mapPutAction) {
/* 40 */     this.preparationAction = preparationAction;
/* 41 */     this.putAction = putAction;
/* 42 */     this.mapPutAction = mapPutAction;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void prepareParsing(Config config) {
/* 52 */     this.preparationAction.accept(config);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object put(Config config, List<String> key, Object value) {
/* 61 */     return this.putAction.put(config, key, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object put(Config config, String key, Object value) {
/* 70 */     return this.putAction.put(config, key, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object put(Map<String, Object> map, String key, Object value) {
/* 79 */     return this.mapPutAction.put(map, key, value);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   private static interface PutAction {
/*    */     Object put(Config param1Config, List<String> param1List, Object param1Object);
/*    */     
/*    */     default Object put(Config config, String key, Object value) {
/* 87 */       return put(config, StringUtils.split(key, '.'), value);
/*    */     }
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   private static interface MapPutAction {
/*    */     Object put(Map<String, Object> param1Map, String param1String, Object param1Object);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ParsingMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */