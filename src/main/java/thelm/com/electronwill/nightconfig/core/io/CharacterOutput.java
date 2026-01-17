/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface CharacterOutput
/*    */ {
/*    */   void write(char paramChar);
/*    */   
/*    */   void write(char... chars) {
/* 22 */     write(chars, 0, chars.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(char[] paramArrayOfchar, int paramInt1, int paramInt2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void write(String s) {
/* 40 */     write(s, 0, s.length());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(String paramString, int paramInt1, int paramInt2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void write(CharsWrapper cw) {
/* 58 */     write(cw.chars, cw.offset, cw.limit - cw.offset);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\CharacterOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */