/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class MemoizingBooleanSupplier
/*    */   implements BooleanSupplier {
/*    */   private BooleanSupplier delegate;
/*    */   private boolean value;
/*    */   
/*    */   private MemoizingBooleanSupplier(BooleanSupplier delegate) {
/* 14 */     this.delegate = Objects.<BooleanSupplier>requireNonNull(delegate);
/*    */   }
/*    */   
/*    */   public static MemoizingBooleanSupplier of(BooleanSupplier delegate) {
/* 18 */     return new MemoizingBooleanSupplier(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingBooleanSupplier of(Predicate<T> function, Supplier<T> value) {
/* 22 */     return new MemoizingBooleanSupplier(() -> function.test(value.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getAsBoolean() {
/* 27 */     if (this.delegate != null) {
/* 28 */       synchronized (this) {
/* 29 */         if (this.delegate != null) {
/* 30 */           this.value = this.delegate.getAsBoolean();
/* 31 */           this.delegate = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 35 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingBooleanSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */