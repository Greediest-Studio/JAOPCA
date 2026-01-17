/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum NewlineStyle
/*    */ {
/* 12 */   UNIX(new char[] { '\n'
/*    */ 
/*    */     
/*    */     }),
/* 16 */   WINDOWS(new char[] { '\r', '\n' });
/*    */   
/*    */   public final char[] chars;
/*    */   
/*    */   NewlineStyle(char... chars) {
/* 21 */     this.chars = chars;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static NewlineStyle system() {
/* 28 */     String systemNewline = System.getProperty("line.separator");
/* 29 */     if (systemNewline.equals("\n"))
/* 30 */       return UNIX; 
/* 31 */     if (systemNewline.equals("\r\n")) {
/* 32 */       return WINDOWS;
/*    */     }
/* 34 */     throw new IllegalArgumentException("Unknown system line separator '" + systemNewline + "'. The NewlineStyle enum only supports LF and CRLF.");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\NewlineStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */