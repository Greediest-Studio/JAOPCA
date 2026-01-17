/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public final class TransformingListIterator<InternalV, ExternalV>
/*    */   extends TransformingIterator<InternalV, ExternalV>
/*    */   implements ListIterator<ExternalV>
/*    */ {
/*    */   private final Function<? super ExternalV, ? extends InternalV> writeTransformation;
/*    */   
/*    */   public TransformingListIterator(ListIterator<InternalV> internalIterator, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation) {
/* 13 */     super(internalIterator, readTransformation);
/* 14 */     this.writeTransformation = writeTransformation;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPrevious() {
/* 19 */     return ((ListIterator)this.internalIterator).hasPrevious();
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV previous() {
/* 24 */     return this.readTransformation.apply(((ListIterator<InternalV>)this.internalIterator).previous());
/*    */   }
/*    */ 
/*    */   
/*    */   public int nextIndex() {
/* 29 */     return ((ListIterator)this.internalIterator).nextIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   public int previousIndex() {
/* 34 */     return ((ListIterator)this.internalIterator).previousIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(ExternalV externalV) {
/* 39 */     ((ListIterator)this.internalIterator).set(this.writeTransformation.apply(externalV));
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(ExternalV externalV) {
/* 44 */     ((ListIterator)this.internalIterator).add(this.writeTransformation.apply(externalV));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingListIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */