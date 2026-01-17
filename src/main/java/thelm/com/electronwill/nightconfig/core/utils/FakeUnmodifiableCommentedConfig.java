/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FakeUnmodifiableCommentedConfig
/*    */   extends UnmodifiableConfigWrapper<UnmodifiableConfig>
/*    */   implements UnmodifiableCommentedConfig
/*    */ {
/*    */   public FakeUnmodifiableCommentedConfig(UnmodifiableConfig config) {
/* 28 */     super(config);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getComment(List<String> path) {
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsComment(List<String> path) {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
/* 43 */     return Collections.emptyMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> commentMap() {
/* 48 */     return Collections.emptyMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<? extends UnmodifiableCommentedConfig.Entry> entrySet() {
/* 53 */     return new TransformingSet<>(this.config.entrySet(), x$0 -> new FakeCommentedEntry(x$0), o -> null, o -> o);
/*    */   }
/*    */   
/*    */   private static final class FakeCommentedEntry implements UnmodifiableCommentedConfig.Entry {
/*    */     private final UnmodifiableConfig.Entry entry;
/*    */     
/*    */     private FakeCommentedEntry(UnmodifiableConfig.Entry entry) {
/* 60 */       this.entry = entry;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getComment() {
/* 65 */       return null;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getKey() {
/* 70 */       return this.entry.getKey();
/*    */     }
/*    */ 
/*    */     
/*    */     public <T> T getRawValue() {
/* 75 */       return (T)this.entry.getRawValue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\FakeUnmodifiableCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */