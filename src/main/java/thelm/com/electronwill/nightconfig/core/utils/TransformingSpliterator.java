/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Spliterator;
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
/*    */ 
/*    */ public final class TransformingSpliterator<InternalV, ExternalV>
/*    */   implements Spliterator<ExternalV>
/*    */ {
/*    */   private final Function<? super InternalV, ? extends ExternalV> readTransformation;
/*    */   private final Function<? super ExternalV, ? extends InternalV> writeTransformation;
/*    */   private final Spliterator<InternalV> internalSpliterator;
/*    */   
/*    */   public TransformingSpliterator(Spliterator<InternalV> internalSpliterator, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation) {
/* 26 */     this.readTransformation = readTransformation;
/* 27 */     this.writeTransformation = writeTransformation;
/* 28 */     this.internalSpliterator = internalSpliterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean tryAdvance(Consumer<? super ExternalV> action) {
/* 33 */     return this.internalSpliterator.tryAdvance(internalV -> action.accept(this.readTransformation.apply((InternalV)internalV)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forEachRemaining(Consumer<? super ExternalV> action) {
/* 39 */     this.internalSpliterator.forEachRemaining(internalV -> action.accept(this.readTransformation.apply((InternalV)internalV)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Spliterator<ExternalV> trySplit() {
/* 45 */     return new TransformingSpliterator(this.internalSpliterator.trySplit(), this.readTransformation, this.writeTransformation);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long estimateSize() {
/* 51 */     return this.internalSpliterator.estimateSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public long getExactSizeIfKnown() {
/* 56 */     return this.internalSpliterator.getExactSizeIfKnown();
/*    */   }
/*    */ 
/*    */   
/*    */   public int characteristics() {
/* 61 */     return this.internalSpliterator.characteristics();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasCharacteristics(int characteristics) {
/* 66 */     return this.internalSpliterator.hasCharacteristics(characteristics);
/*    */   }
/*    */ 
/*    */   
/*    */   public Comparator<? super ExternalV> getComparator() {
/* 71 */     return (o1, o2) -> this.internalSpliterator.getComparator().compare(this.writeTransformation.apply((ExternalV)o1), this.writeTransformation.apply((ExternalV)o2));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 78 */     return this.internalSpliterator.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 83 */     return this.internalSpliterator.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return this.internalSpliterator.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingSpliterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */