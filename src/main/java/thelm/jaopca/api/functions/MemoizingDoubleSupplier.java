/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.DoubleSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToDoubleFunction;
/*    */ 
/*    */ public class MemoizingDoubleSupplier
/*    */   implements DoubleSupplier {
/*    */   private DoubleSupplier delegate;
/*    */   private double value;
/*    */   
/*    */   private MemoizingDoubleSupplier(DoubleSupplier delegate) {
/* 14 */     this.delegate = Objects.<DoubleSupplier>requireNonNull(delegate);
/*    */   }
/*    */   
/*    */   public static MemoizingDoubleSupplier of(DoubleSupplier delegate) {
/* 18 */     return new MemoizingDoubleSupplier(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingDoubleSupplier of(ToDoubleFunction<T> function, Supplier<T> value) {
/* 22 */     return new MemoizingDoubleSupplier(() -> function.applyAsDouble(value.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public double getAsDouble() {
/* 27 */     if (this.delegate != null) {
/* 28 */       synchronized (this) {
/* 29 */         if (this.delegate != null) {
/* 30 */           this.value = this.delegate.getAsDouble();
/* 31 */           this.delegate = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 35 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingDoubleSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */