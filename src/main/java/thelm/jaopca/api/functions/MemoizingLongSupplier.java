/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.LongSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToLongFunction;
/*    */ 
/*    */ public class MemoizingLongSupplier
/*    */   implements LongSupplier {
/*    */   private LongSupplier delegate;
/*    */   private long value;
/*    */   
/*    */   private MemoizingLongSupplier(LongSupplier delegate) {
/* 14 */     this.delegate = Objects.<LongSupplier>requireNonNull(delegate);
/*    */   }
/*    */   
/*    */   public static MemoizingLongSupplier of(LongSupplier delegate) {
/* 18 */     return new MemoizingLongSupplier(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingLongSupplier of(ToLongFunction<T> function, Supplier<T> value) {
/* 22 */     return new MemoizingLongSupplier(() -> function.applyAsLong(value.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public long getAsLong() {
/* 27 */     if (this.delegate != null) {
/* 28 */       synchronized (this) {
/* 29 */         if (this.delegate != null) {
/* 30 */           this.value = this.delegate.getAsLong();
/* 31 */           this.delegate = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 35 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingLongSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */