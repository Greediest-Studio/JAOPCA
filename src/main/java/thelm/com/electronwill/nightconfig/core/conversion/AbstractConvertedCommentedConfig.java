/*     */ package thelm.com.electronwill.nightconfig.core.conversion;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractConvertedCommentedConfig<C extends CommentedConfig>
/*     */   extends AbstractConvertedConfig<C>
/*     */   implements CommentedConfig
/*     */ {
/*     */   public AbstractConvertedCommentedConfig(C config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
/*  21 */     super(config, readConversion, writeConversion, supportPredicate);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComment(List<String> path) {
/*  26 */     return ((CommentedConfig)this.config).getComment(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsComment(List<String> path) {
/*  31 */     return ((CommentedConfig)this.config).containsComment(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public String setComment(List<String> path, String comment) {
/*  36 */     return ((CommentedConfig)this.config).setComment(path, comment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeComment(List<String> path) {
/*  41 */     return ((CommentedConfig)this.config).removeComment(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearComments() {
/*  46 */     ((CommentedConfig)this.config).clearComments();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
/*  51 */     return ((CommentedConfig)this.config).getComments();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
/*  56 */     ((CommentedConfig)this.config).putAllComments(comments);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
/*  61 */     ((CommentedConfig)this.config).putAllComments(commentedConfig);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> commentMap() {
/*  66 */     return ((CommentedConfig)this.config).commentMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<? extends CommentedConfig.Entry> entrySet() {
/*  72 */     Function<CommentedConfig.Entry, CommentedConfig.Entry> readTransfo = entry -> new CommentedConfig.Entry()
/*     */       {
/*     */         public String getComment() {
/*  75 */           return entry.getComment();
/*     */         }
/*     */ 
/*     */         
/*     */         public String setComment(String comment) {
/*  80 */           return entry.setComment(comment);
/*     */         }
/*     */ 
/*     */         
/*     */         public String removeComment() {
/*  85 */           return entry.removeComment();
/*     */         }
/*     */ 
/*     */         
/*     */         public String getKey() {
/*  90 */           return entry.getKey();
/*     */         }
/*     */ 
/*     */         
/*     */         public <T> T getRawValue() {
/*  95 */           return (T)AbstractConvertedCommentedConfig.this.readConversion.apply(entry.getRawValue());
/*     */         }
/*     */ 
/*     */         
/*     */         public <T> T setValue(Object value) {
/* 100 */           return (T)AbstractConvertedCommentedConfig.this.readConversion.apply(entry.setValue(AbstractConvertedCommentedConfig.this.writeConversion.apply(value)));
/*     */         }
/*     */       };
/* 103 */     return (Set<? extends CommentedConfig.Entry>)new TransformingSet(((CommentedConfig)this.config).entrySet(), readTransfo, o -> null, e -> e);
/*     */   }
/*     */ 
/*     */   
/*     */   public CommentedConfig createSubConfig() {
/* 108 */     return ((CommentedConfig)this.config).createSubConfig();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\AbstractConvertedCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */