/*    */ package thelm.com.electronwill.nightconfig.core.conversion;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class InvalidValueException
/*    */   extends RuntimeException
/*    */ {
/*    */   public InvalidValueException(String message) {
/* 11 */     super(message);
/*    */   }
/*    */   
/*    */   public InvalidValueException(String message, Throwable cause) {
/* 15 */     super(message, cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidValueException(String messageFormat, Object... args) {
/* 26 */     super(String.format(messageFormat, args));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\InvalidValueException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */