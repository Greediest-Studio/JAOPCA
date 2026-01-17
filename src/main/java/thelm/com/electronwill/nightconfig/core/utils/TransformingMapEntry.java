/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
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
/*    */ final class TransformingMapEntry<K, InternalV, ExternalV>
/*    */   implements Map.Entry<K, ExternalV>
/*    */ {
/*    */   private final Function<? super InternalV, ? extends ExternalV> readTransformation;
/*    */   private final Function<? super ExternalV, ? extends InternalV> writeTransformation;
/*    */   private final Map.Entry<K, InternalV> internalEntry;
/*    */   
/*    */   TransformingMapEntry(Map.Entry<K, InternalV> internalEntry, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation) {
/* 25 */     this.readTransformation = readTransformation;
/* 26 */     this.writeTransformation = writeTransformation;
/* 27 */     this.internalEntry = internalEntry;
/*    */   }
/*    */ 
/*    */   
/*    */   public K getKey() {
/* 32 */     return this.internalEntry.getKey();
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV getValue() {
/* 37 */     return this.readTransformation.apply(this.internalEntry.getValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public ExternalV setValue(ExternalV value) {
/* 42 */     return this.readTransformation.apply(this.internalEntry.setValue(this.writeTransformation.apply(value)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     if (obj == this)
/* 48 */       return true; 
/* 49 */     if (!(obj instanceof Map.Entry)) {
/* 50 */       return false;
/*    */     }
/* 52 */     Map.Entry<?, ?> entry = (Map.Entry<?, ?>)obj;
/* 53 */     return (Objects.equals(getKey(), entry.getKey()) && Objects.equals(getValue(), entry
/* 54 */         .getValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 59 */     return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingMapEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */