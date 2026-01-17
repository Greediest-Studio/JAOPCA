/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToIntFunction;
/*    */ 
/*    */ public class MemoizingIntSupplier
/*    */   implements IntSupplier {
/*    */   private IntSupplier delegate;
/*    */   private int value;
/*    */   
/*    */   private MemoizingIntSupplier(IntSupplier delegate) {
/* 14 */     this.delegate = Objects.<IntSupplier>requireNonNull(delegate);
/*    */   }
/*    */   
/*    */   public static MemoizingIntSupplier of(IntSupplier delegate) {
/* 18 */     return new MemoizingIntSupplier(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingIntSupplier of(ToIntFunction<T> function, Supplier<T> value) {
/* 22 */     return new MemoizingIntSupplier(() -> function.applyAsInt(value.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAsInt() {
/* 27 */     if (this.delegate != null) {
/* 28 */       synchronized (this) {
/* 29 */         if (this.delegate != null) {
/* 30 */           this.value = this.delegate.getAsInt();
/* 31 */           this.delegate = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 35 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingIntSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */