/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StringWriter
/*    */ {
/*  9 */   private static final char[] ESCAPED_B = new char[] { '\\', 'b' };
/* 10 */   private static final char[] ESCAPED_F = new char[] { '\\', 'f' };
/* 11 */   private static final char[] ESCAPED_N = new char[] { '\\', 'n' };
/* 12 */   private static final char[] ESCAPED_R = new char[] { '\\', 'r' };
/* 13 */   private static final char[] ESCAPED_T = new char[] { '\\', 't' };
/* 14 */   private static final char[] ESCAPED_BACKSLASH = new char[] { '\\', '\\' };
/* 15 */   private static final char[] ESCAPED_QUOTE = new char[] { '\\', '"' };
/*    */   
/*    */   static void writeBasic(CharSequence csq, CharacterOutput output) {
/* 18 */     output.write('"');
/* 19 */     int l = csq.length();
/* 20 */     for (int i = 0; i < l; i++) {
/* 21 */       writeBasicChar(csq.charAt(i), output);
/*    */     }
/* 23 */     output.write('"');
/*    */   }
/*    */   
/*    */   static void writeLiteral(String str, CharacterOutput output) {
/* 27 */     output.write('\'');
/* 28 */     output.write(str);
/* 29 */     output.write('\'');
/*    */   }
/*    */   
/*    */   private static void writeBasicChar(char c, CharacterOutput output) {
/* 33 */     switch (c) {
/*    */       case '\\':
/* 35 */         output.write(ESCAPED_BACKSLASH);
/*    */         return;
/*    */       case '"':
/* 38 */         output.write(ESCAPED_QUOTE);
/*    */         return;
/*    */       case '\b':
/* 41 */         output.write(ESCAPED_B);
/*    */         return;
/*    */       case '\f':
/* 44 */         output.write(ESCAPED_F);
/*    */         return;
/*    */       case '\n':
/* 47 */         output.write(ESCAPED_N);
/*    */         return;
/*    */       case '\r':
/* 50 */         output.write(ESCAPED_R);
/*    */         return;
/*    */       case '\t':
/* 53 */         output.write(ESCAPED_T);
/*    */         return;
/*    */     } 
/* 56 */     output.write(c);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\StringWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */