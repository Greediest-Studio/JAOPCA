/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.function.Consumer;
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
/*    */ public class TransformingIterator<InternalV, ExternalV>
/*    */   implements Iterator<ExternalV>
/*    */ {
/*    */   protected final Function<? super InternalV, ? extends ExternalV> readTransformation;
/*    */   protected final Iterator<InternalV> internalIterator;
/*    */   
/*    */   public TransformingIterator(Iterator<InternalV> internalIterator, Function<? super InternalV, ? extends ExternalV> readTransformation) {
/* 23 */     this.readTransformation = readTransformation;
/* 24 */     this.internalIterator = internalIterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 29 */     return this.internalIterator.hasNext();
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV next() {
/* 34 */     return this.readTransformation.apply(this.internalIterator.next());
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove() {
/* 39 */     this.internalIterator.remove();
/*    */   }
/*    */ 
/*    */   
/*    */   public void forEachRemaining(Consumer<? super ExternalV> action) {
/* 44 */     this.internalIterator.forEachRemaining(internalV -> action.accept(this.readTransformation.apply((InternalV)internalV)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     return this.internalIterator.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 55 */     return this.internalIterator.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return this.internalIterator.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */