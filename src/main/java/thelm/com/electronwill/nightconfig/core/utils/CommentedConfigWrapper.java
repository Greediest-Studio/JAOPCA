/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CommentedConfigWrapper<C extends CommentedConfig>
/*    */   extends ConfigWrapper<C>
/*    */   implements CommentedConfig
/*    */ {
/*    */   protected CommentedConfigWrapper(C config) {
/* 17 */     super(config);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getComment(List<String> path) {
/* 22 */     return ((CommentedConfig)this.config).getComment(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsComment(List<String> path) {
/* 27 */     return ((CommentedConfig)this.config).containsComment(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public String setComment(List<String> path, String comment) {
/* 32 */     return ((CommentedConfig)this.config).setComment(path, comment);
/*    */   }
/*    */ 
/*    */   
/*    */   public String removeComment(List<String> path) {
/* 37 */     return ((CommentedConfig)this.config).removeComment(path);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> commentMap() {
/* 42 */     return ((CommentedConfig)this.config).commentMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends CommentedConfig.Entry> entrySet() {
/* 47 */     return ((CommentedConfig)this.config).entrySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearComments() {
/* 52 */     ((CommentedConfig)this.config).clearComments();
/*    */   }
/*    */ 
/*    */   
/*    */   public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
/* 57 */     ((CommentedConfig)this.config).putAllComments(comments);
/*    */   }
/*    */ 
/*    */   
/*    */   public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
/* 62 */     ((CommentedConfig)this.config).putAllComments(commentedConfig);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
/* 67 */     return ((CommentedConfig)this.config).getComments();
/*    */   }
/*    */ 
/*    */   
/*    */   public CommentedConfig createSubConfig() {
/* 72 */     return ((CommentedConfig)this.config).createSubConfig();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return getClass().getSimpleName() + ':' + this.config;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\CommentedConfigWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */