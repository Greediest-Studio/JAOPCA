/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ public final class ObservedIterator<E>
/*    */   extends AbstractObserved
/*    */   implements Iterator<E>
/*    */ {
/*    */   private final Iterator<E> iterator;
/*    */   
/*    */   public ObservedIterator(Iterator<E> iterator, Runnable callback) {
/* 13 */     super(callback);
/* 14 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 19 */     return this.iterator.hasNext();
/*    */   }
/*    */ 
/*    */   
/*    */   public E next() {
/* 24 */     return this.iterator.next();
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove() {
/* 29 */     this.iterator.remove();
/* 30 */     this.callback.run();
/*    */   }
/*    */ 
/*    */   
/*    */   public void forEachRemaining(Consumer<? super E> action) {
/* 35 */     this.iterator.forEachRemaining(action);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 40 */     return this.iterator.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 45 */     return this.iterator.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\ObservedIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */