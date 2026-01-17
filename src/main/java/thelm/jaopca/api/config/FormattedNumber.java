/*    */ package thelm.jaopca.api.config;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class FormattedNumber
/*    */   extends Number {
/*    */   public final Function<Number, String> format;
/*    */   public final Number value;
/*    */   
/*    */   public FormattedNumber(Function<Number, String> format, Number value) {
/* 11 */     this.format = format;
/* 12 */     this.value = value;
/*    */   }
/*    */   
/*    */   public FormattedNumber(String format, Number value) {
/* 16 */     this.format = (n -> String.format(format, new Object[] { n }));
/* 17 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int intValue() {
/* 22 */     return this.value.intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public long longValue() {
/* 27 */     return this.value.longValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public float floatValue() {
/* 32 */     return this.value.floatValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public double doubleValue() {
/* 37 */     return this.value.doubleValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return this.format.apply(this.value);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\config\FormattedNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */