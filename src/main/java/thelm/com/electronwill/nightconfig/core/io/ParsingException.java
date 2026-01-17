/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParsingException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ParsingException(String message) {
/* 10 */     super(message);
/*    */   }
/*    */   
/*    */   public ParsingException(String message, Throwable cause) {
/* 14 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public static ParsingException readFailed(Throwable cause) {
/* 18 */     return new ParsingException("Failed to parse data from Reader", cause);
/*    */   }
/*    */   
/*    */   public static ParsingException notEnoughData() {
/* 22 */     return new ParsingException("Not enough data available");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ParsingException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */