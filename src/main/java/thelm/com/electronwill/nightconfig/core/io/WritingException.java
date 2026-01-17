/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WritingException
/*    */   extends RuntimeException
/*    */ {
/*    */   public WritingException(String message) {
/* 10 */     super(message);
/*    */   }
/*    */   
/*    */   public WritingException(String message, Throwable cause) {
/* 14 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public WritingException(Throwable cause) {
/* 18 */     this("Failed to write data: ", cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\WritingException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */