/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public final class ObservedEntry<K, V>
/*    */   extends AbstractObserved
/*    */   implements Map.Entry<K, V>
/*    */ {
/*    */   final Map.Entry<K, V> entry;
/*    */   
/*    */   protected ObservedEntry(Map.Entry<K, V> entry, Runnable callback) {
/* 12 */     super(callback);
/* 13 */     this.entry = entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public K getKey() {
/* 18 */     return this.entry.getKey();
/*    */   }
/*    */ 
/*    */   
/*    */   public V getValue() {
/* 23 */     return this.entry.getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public V setValue(V value) {
/* 28 */     V result = this.entry.setValue(value);
/* 29 */     this.callback.run();
/* 30 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 35 */     return this.entry.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 40 */     return this.entry.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\ObservedEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */