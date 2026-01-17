/*    */ package thelm.jaopca.api.functions;
/*    */ 
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.DoubleSupplier;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.LongSupplier;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToDoubleFunction;
/*    */ import java.util.function.ToIntFunction;
/*    */ import java.util.function.ToLongFunction;
/*    */ 
/*    */ public class MemoizingSuppliers
/*    */ {
/*    */   public static MemoizingBooleanSupplier of(BooleanSupplier delegate) {
/* 17 */     return MemoizingBooleanSupplier.of(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingBooleanSupplier of(Predicate<T> function, Supplier<T> value) {
/* 21 */     return MemoizingBooleanSupplier.of(function, value);
/*    */   }
/*    */   
/*    */   public static MemoizingIntSupplier of(IntSupplier delegate) {
/* 25 */     return MemoizingIntSupplier.of(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingIntSupplier of(ToIntFunction<T> function, Supplier<T> value) {
/* 29 */     return MemoizingIntSupplier.of(function, value);
/*    */   }
/*    */   
/*    */   public static MemoizingDoubleSupplier of(DoubleSupplier delegate) {
/* 33 */     return MemoizingDoubleSupplier.of(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingDoubleSupplier of(ToDoubleFunction<T> function, Supplier<T> value) {
/* 37 */     return MemoizingDoubleSupplier.of(function, value);
/*    */   }
/*    */   
/*    */   public static MemoizingLongSupplier of(LongSupplier delegate) {
/* 41 */     return MemoizingLongSupplier.of(delegate);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingLongSupplier of(ToLongFunction<T> function, Supplier<T> value) {
/* 45 */     return MemoizingLongSupplier.of(function, value);
/*    */   }
/*    */   
/*    */   public static <T> MemoizingSupplier<T> of(Supplier<T> delegate) {
/* 49 */     return MemoizingSupplier.of(delegate);
/*    */   }
/*    */   
/*    */   public static <V, T> MemoizingSupplier<T> of(Function<V, T> function, Supplier<V> value) {
/* 53 */     return MemoizingSupplier.of(function, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\functions\MemoizingSuppliers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */