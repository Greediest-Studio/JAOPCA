/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.ListIterator;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public final class TransformingList<InternalV, ExternalV>
/*    */   extends TransformingCollection<InternalV, ExternalV>
/*    */   implements List<ExternalV>
/*    */ {
/*    */   public TransformingList(List<InternalV> internalList, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation, Function<Object, Object> searchTransformation) {
/* 14 */     super(internalList, readTransformation, writeTransformation, searchTransformation);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean addAll(int index, Collection<? extends ExternalV> c) {
/* 19 */     return ((List)this.internalCollection).addAll(index, new TransformingCollection<>(c, this.writeTransformation, this.readTransformation, this.searchTransformation));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ExternalV get(int index) {
/* 25 */     return this.readTransformation.apply(((List<InternalV>)this.internalCollection).get(index));
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV set(int index, ExternalV element) {
/* 30 */     return this.readTransformation.apply(((List<InternalV>)this.internalCollection).set(index, this.writeTransformation.apply(element)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(int index, ExternalV element) {
/* 35 */     ((List)this.internalCollection).add(index, this.writeTransformation.apply(element));
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV remove(int index) {
/* 40 */     return this.readTransformation.apply(((List<InternalV>)this.internalCollection).remove(index));
/*    */   }
/*    */ 
/*    */   
/*    */   public int indexOf(Object o) {
/* 45 */     return ((List)this.internalCollection).indexOf(this.searchTransformation.apply(o));
/*    */   }
/*    */ 
/*    */   
/*    */   public int lastIndexOf(Object o) {
/* 50 */     return ((List)this.internalCollection).lastIndexOf(this.searchTransformation.apply(o));
/*    */   }
/*    */ 
/*    */   
/*    */   public ListIterator<ExternalV> listIterator() {
/* 55 */     return new TransformingListIterator<>(((List<InternalV>)this.internalCollection).listIterator(), this.readTransformation, this.writeTransformation);
/*    */   }
/*    */ 
/*    */   
/*    */   public ListIterator<ExternalV> listIterator(int index) {
/* 60 */     return new TransformingListIterator<>(((List<InternalV>)this.internalCollection).listIterator(index), this.readTransformation, this.writeTransformation);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ExternalV> subList(int fromIndex, int toIndex) {
/* 65 */     return new TransformingList(((List<InternalV>)this.internalCollection).subList(fromIndex, toIndex), this.readTransformation, this.writeTransformation, this.searchTransformation);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */