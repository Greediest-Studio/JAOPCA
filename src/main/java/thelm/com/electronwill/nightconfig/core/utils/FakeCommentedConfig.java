/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
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
/*     */ public final class FakeCommentedConfig
/*     */   extends ConfigWrapper<Config>
/*     */   implements CommentedConfig
/*     */ {
/*     */   public FakeCommentedConfig(Config config) {
/*  26 */     super(config);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComment(List<String> path) {
/*  31 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsComment(List<String> path) {
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String setComment(List<String> path, String comment) {
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeComment(List<String> path) {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearComments() {}
/*     */ 
/*     */   
/*     */   public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
/*  54 */     return Collections.emptyMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {}
/*     */ 
/*     */   
/*     */   public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {}
/*     */ 
/*     */   
/*     */   public Map<String, String> commentMap() {
/*  65 */     return Collections.emptyMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<? extends CommentedConfig.Entry> entrySet() {
/*  70 */     return new TransformingSet<>(this.config.entrySet(), x$0 -> new FakeCommentedEntry(x$0), o -> null, o -> o);
/*     */   }
/*     */   
/*     */   private static final class FakeCommentedEntry implements CommentedConfig.Entry {
/*     */     private final Config.Entry entry;
/*     */     
/*     */     private FakeCommentedEntry(Config.Entry entry) {
/*  77 */       this.entry = entry;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getComment() {
/*  82 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getKey() {
/*  87 */       return this.entry.getKey();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T getRawValue() {
/*  92 */       return (T)this.entry.getRawValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public String setComment(String comment) {
/*  97 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String removeComment() {
/* 102 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T setValue(Object value) {
/* 107 */       return (T)this.entry.setValue(value);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CommentedConfig createSubConfig() {
/* 113 */     return CommentedConfig.fake(super.createSubConfig());
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\FakeCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */