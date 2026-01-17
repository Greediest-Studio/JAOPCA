/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum IndentStyle
/*    */ {
/* 12 */   TABS(new char[] { '\t'
/*    */ 
/*    */     
/*    */     }),
/* 16 */   SPACES_2(new char[] { ' ', ' '
/*    */ 
/*    */     
/*    */     }),
/* 20 */   SPACES_4(new char[] { ' ', ' ', ' ', ' '
/*    */ 
/*    */     
/*    */     }),
/* 24 */   SPACES_8(new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
/*    */ 
/*    */ 
/*    */     
/*    */     }),
/* 29 */   NONE(new char[0]);
/*    */   
/*    */   public final char[] chars;
/*    */   
/*    */   IndentStyle(char... chars) {
/* 34 */     this.chars = chars;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\IndentStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */