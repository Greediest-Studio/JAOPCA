/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.FakeUnmodifiableCommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface UnmodifiableCommentedConfig
/*     */   extends UnmodifiableConfig
/*     */ {
/*     */   default String getComment(String path) {
/*  22 */     return getComment(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getComment(List<String> paramList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Optional<String> getOptionalComment(String path) {
/*  41 */     return getOptionalComment(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Optional<String> getOptionalComment(List<String> path) {
/*  52 */     return Optional.ofNullable(getComment(path));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean containsComment(String path) {
/*  62 */     return containsComment(StringUtils.split(path, '.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean containsComment(List<String> paramList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Map<String, String> commentMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Map<String, CommentNode> getComments() {
/*  90 */     Map<String, CommentNode> map = new HashMap<>();
/*  91 */     getComments(map);
/*  92 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void getComments(Map<String, CommentNode> destination) {
/* 101 */     for (Entry entry : entrySet()) {
/* 102 */       String key = entry.getKey();
/* 103 */       String comment = entry.getComment();
/* 104 */       Object value = entry.getValue();
/* 105 */       if (comment != null || value instanceof UnmodifiableCommentedConfig) {
/*     */ 
/*     */         
/* 108 */         Map<String, CommentNode> children = (value instanceof UnmodifiableCommentedConfig) ? ((UnmodifiableCommentedConfig)value).getComments() : null;
/* 109 */         CommentNode node = new CommentNode(comment, children);
/* 110 */         destination.put(key, node);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   Set<? extends Entry> entrySet();
/*     */   
/*     */   public static interface Entry
/*     */     extends UnmodifiableConfig.Entry
/*     */   {
/*     */     String getComment();
/*     */   }
/*     */   
/*     */   public static final class CommentNode {
/*     */     private final String comment;
/*     */     private final Map<String, CommentNode> children;
/*     */     
/*     */     public CommentNode(String comment, Map<String, CommentNode> children) {
/* 128 */       if (comment == null && children == null) {
/* 129 */         throw new IllegalArgumentException("There is no point in creating a CommentNode if the comment AND the children are null.");
/*     */       }
/*     */       
/* 132 */       this.comment = comment;
/* 133 */       this.children = children;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getComment() {
/* 140 */       return this.comment;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Map<String, CommentNode> getChildren() {
/* 147 */       return this.children;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static UnmodifiableCommentedConfig fake(UnmodifiableConfig config) {
/* 174 */     if (config instanceof UnmodifiableCommentedConfig) {
/* 175 */       return (UnmodifiableCommentedConfig)config;
/*     */     }
/* 177 */     return (UnmodifiableCommentedConfig)new FakeUnmodifiableCommentedConfig(config);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\UnmodifiableCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */