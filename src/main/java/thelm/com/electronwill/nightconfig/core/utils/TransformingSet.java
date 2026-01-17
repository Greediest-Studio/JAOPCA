/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
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
/*    */ public final class TransformingSet<InternalV, ExternalV>
/*    */   extends TransformingCollection<InternalV, ExternalV>
/*    */   implements Set<ExternalV>
/*    */ {
/*    */   public TransformingSet(Set<InternalV> internalCollection, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation, Function<Object, Object> searchTransformation) {
/* 22 */     super(internalCollection, readTransformation, writeTransformation, searchTransformation);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */