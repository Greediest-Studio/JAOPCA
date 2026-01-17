/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.FakeCommentedConfig;
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
/*     */ public interface CommentedConfig
/*     */   extends UnmodifiableCommentedConfig, Config
/*     */ {
/*     */   default String setComment(String path, String comment) {
/*  27 */     return setComment(StringUtils.split(path, '.'), comment);
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
/*     */   default String removeComment(String path) {
/*  46 */     return removeComment(StringUtils.split(path, '.'));
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
/*     */   default void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
/*  69 */     for (Map.Entry<String, UnmodifiableCommentedConfig.CommentNode> entry : comments.entrySet()) {
/*  70 */       String key = entry.getKey();
/*  71 */       UnmodifiableCommentedConfig.CommentNode node = entry.getValue();
/*  72 */       String comment = node.getComment();
/*  73 */       if (comment != null) {
/*  74 */         setComment(Collections.singletonList(key), comment);
/*     */       }
/*  76 */       Map<String, UnmodifiableCommentedConfig.CommentNode> children = node.getChildren();
/*  77 */       if (children != null) {
/*  78 */         CommentedConfig config = getRaw(Collections.singletonList(key));
/*  79 */         config.putAllComments(children);
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
/*     */   default void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
/*  91 */     for (UnmodifiableCommentedConfig.Entry entry : commentedConfig.entrySet()) {
/*  92 */       String key = entry.getKey();
/*  93 */       String comment = entry.getComment();
/*  94 */       if (comment != null) {
/*  95 */         setComment(Collections.singletonList(key), comment);
/*     */       }
/*  97 */       Object value = entry.getValue();
/*  98 */       if (value instanceof UnmodifiableCommentedConfig) {
/*  99 */         CommentedConfig config = getRaw(Collections.singletonList(key));
/* 100 */         config.putAllComments((UnmodifiableCommentedConfig)value);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default UnmodifiableCommentedConfig unmodifiable() {
/* 108 */     return new UnmodifiableCommentedConfig()
/*     */       {
/*     */         public <T> T getRaw(List<String> path) {
/* 111 */           return CommentedConfig.this.getRaw(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public String getComment(List<String> path) {
/* 116 */           return CommentedConfig.this.getComment(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean contains(List<String> path) {
/* 121 */           return CommentedConfig.this.contains(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean containsComment(List<String> path) {
/* 126 */           return CommentedConfig.this.containsComment(path);
/*     */         }
/*     */ 
/*     */         
/*     */         public int size() {
/* 131 */           return CommentedConfig.this.size();
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<String, Object> valueMap() {
/* 136 */           return Collections.unmodifiableMap(CommentedConfig.this.valueMap());
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<String, String> commentMap() {
/* 141 */           return Collections.unmodifiableMap(CommentedConfig.this.commentMap());
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
/* 146 */           return CommentedConfig.this.getComments();
/*     */         }
/*     */ 
/*     */         
/*     */         public Set<? extends UnmodifiableCommentedConfig.Entry> entrySet() {
/* 151 */           return (Set)CommentedConfig.this.entrySet();
/*     */         }
/*     */ 
/*     */         
/*     */         public ConfigFormat<?> configFormat() {
/* 156 */           return CommentedConfig.this.configFormat();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   default CommentedConfig checked() {
/* 162 */     return new CheckedCommentedConfig(this);
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
/*     */   static CommentedConfig of(ConfigFormat<? extends CommentedConfig> format) {
/* 208 */     return new SimpleCommentedConfig(format, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig of(Supplier<Map<String, Object>> mapCreator, ConfigFormat<? extends CommentedConfig> format) {
/* 219 */     return new SimpleCommentedConfig(mapCreator, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig ofConcurrent(ConfigFormat<? extends CommentedConfig> format) {
/* 229 */     return new SimpleCommentedConfig(format, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig inMemory() {
/* 238 */     return InMemoryCommentedFormat.defaultInstance().createConfig();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig inMemoryConcurrent() {
/* 247 */     return InMemoryCommentedFormat.defaultInstance().createConcurrentConfig();
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
/*     */   static CommentedConfig wrap(Map<String, Object> map, ConfigFormat<?> format) {
/* 259 */     return new SimpleCommentedConfig(map, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig copy(UnmodifiableConfig config) {
/* 270 */     return new SimpleCommentedConfig(config, config.configFormat(), false);
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
/*     */   static CommentedConfig copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator) {
/* 284 */     return new SimpleCommentedConfig(config, mapCreator, config.configFormat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig copy(UnmodifiableConfig config, ConfigFormat<?> format) {
/* 295 */     return new SimpleCommentedConfig(config, format, false);
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
/*     */   static CommentedConfig copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> format) {
/* 310 */     return new SimpleCommentedConfig(config, mapCreator, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig copy(UnmodifiableCommentedConfig config) {
/* 321 */     return new SimpleCommentedConfig(config, config.configFormat(), false);
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
/*     */   static CommentedConfig copy(UnmodifiableCommentedConfig config, Supplier<Map<String, Object>> mapCreator) {
/* 333 */     return new SimpleCommentedConfig(config, mapCreator, config.configFormat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig copy(UnmodifiableCommentedConfig config, ConfigFormat<?> format) {
/* 344 */     return new SimpleCommentedConfig(config, format, false);
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
/*     */   static CommentedConfig copy(UnmodifiableCommentedConfig config, Supplier<Map<String, Object>> mapCreator, ConfigFormat<? extends CommentedConfig> format) {
/* 359 */     return new SimpleCommentedConfig(config, mapCreator, format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig concurrentCopy(UnmodifiableConfig config) {
/* 370 */     return new SimpleCommentedConfig(config, config.configFormat(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig concurrentCopy(UnmodifiableConfig config, ConfigFormat<?> format) {
/* 381 */     return new SimpleCommentedConfig(config, format, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static CommentedConfig concurrentCopy(UnmodifiableCommentedConfig config) {
/* 392 */     return new SimpleCommentedConfig(config, config.configFormat(), true);
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
/*     */   static CommentedConfig concurrentCopy(UnmodifiableCommentedConfig config, ConfigFormat<?> format) {
/* 404 */     return new SimpleCommentedConfig(config, format, true);
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
/*     */   static CommentedConfig fake(Config config) {
/* 417 */     if (config instanceof CommentedConfig) {
/* 418 */       return (CommentedConfig)config;
/*     */     }
/* 420 */     return (CommentedConfig)new FakeCommentedConfig(config);
/*     */   }
/*     */   
/*     */   String setComment(List<String> paramList, String paramString);
/*     */   
/*     */   String removeComment(List<String> paramList);
/*     */   
/*     */   void clearComments();
/*     */   
/*     */   Map<String, String> commentMap();
/*     */   
/*     */   Set<? extends Entry> entrySet();
/*     */   
/*     */   CommentedConfig createSubConfig();
/*     */   
/*     */   public static interface Entry extends Config.Entry, UnmodifiableCommentedConfig.Entry {
/*     */     String setComment(String param1String);
/*     */     
/*     */     String removeComment();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\CommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */