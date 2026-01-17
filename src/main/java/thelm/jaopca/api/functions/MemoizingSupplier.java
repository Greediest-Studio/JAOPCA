/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class MemoizingSupplier<T>
/*    */   implements Supplier<T> {
/*    */   private Supplier<T> delegate;
/*    */   private T value;
/*    */   
/*    */   private MemoizingSupplier(Supplier<T> delegate) {
/* 13 */     this.delegate = Objects.<Supplier<T>>requireNonNull(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingSupplier<T> of(Supplier<T> delegate) {
/* 17 */     return new MemoizingSupplier<>(delegate);
/*    */   }
/*    */   
/*    */   public static <V, T> MemoizingSupplier<T> of(Function<V, T> function, Supplier<V> value) {
/* 21 */     return new MemoizingSupplier<>(() -> function.apply(value.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public T get() {
/* 26 */     if (this.delegate != null) {
/* 27 */       synchronized (this) {
/* 28 */         if (this.delegate != null) {
/* 29 */           this.value = this.delegate.get();
/* 30 */           this.delegate = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 34 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */