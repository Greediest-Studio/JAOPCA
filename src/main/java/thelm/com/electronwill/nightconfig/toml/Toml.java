/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.util.List;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.io.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Toml
/*    */ {
/* 13 */   private static final char[] WHITESPACE_OR_NEWLINE = new char[] { '\t', ' ', '\n', '\r' };
/* 14 */   private static final char[] WHITESPACE = new char[] { '\t', ' ' };
/* 15 */   private static final char[] NEWLINE = new char[] { '\n' };
/* 16 */   private static final char[] FORBIDDEN_IN_ALL_BARE_KEYS = new char[] { '.', '[', ']', '#', '=' };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static char readUsefulChar(CharacterInput input) {
/* 22 */     char next = input.readCharAndSkip(WHITESPACE_OR_NEWLINE);
/* 23 */     while (next == '#') {
/* 24 */       input.readCharsUntil(NEWLINE);
/* 25 */       next = input.readCharAndSkip(WHITESPACE_OR_NEWLINE);
/*    */     } 
/* 27 */     return next;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static int readUseful(CharacterInput input, List<CharsWrapper> commentsList) {
/* 34 */     int next = input.readAndSkip(WHITESPACE_OR_NEWLINE);
/* 35 */     while (next == 35) {
/* 36 */       CharsWrapper comment = readLine(input);
/* 37 */       commentsList.add(comment);
/* 38 */       next = input.readAndSkip(WHITESPACE_OR_NEWLINE);
/*    */     } 
/* 40 */     return next;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static char readNonSpaceChar(CharacterInput input, boolean skipNewlines) {
/* 47 */     return skipNewlines ? input.readCharAndSkip(WHITESPACE_OR_NEWLINE) : 
/* 48 */       input.readCharAndSkip(WHITESPACE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static int readNonSpace(CharacterInput input, boolean skipNewlines) {
/* 55 */     return skipNewlines ? input.readAndSkip(WHITESPACE_OR_NEWLINE) : 
/* 56 */       input.readAndSkip(WHITESPACE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static CharsWrapper readLine(CharacterInput input) {
/* 63 */     CharsWrapper chars = input.readUntil(NEWLINE);
/* 64 */     int lastIndex = chars.length() - 1;
/* 65 */     if (lastIndex >= 0 && chars.get(lastIndex) == '\r') {
/* 66 */       return chars.subView(0, lastIndex);
/*    */     }
/* 68 */     return chars;
/*    */   }
/*    */   
/*    */   static boolean isValidInBareKey(char c, boolean lenient) {
/* 72 */     if (lenient) return (c > ' ' && !Utils.arrayContains(FORBIDDEN_IN_ALL_BARE_KEYS, c)); 
/* 73 */     return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '-' || c == '_');
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static boolean isValidBareKey(CharSequence csq, boolean lenient) {
/* 81 */     for (int i = 0; i < csq.length(); i++) {
/* 82 */       if (!isValidInBareKey(csq.charAt(i), lenient)) return false; 
/*    */     } 
/* 84 */     return true;
/*    */   }
/*    */   
/*    */   static boolean isKeyValueSeparator(char c, boolean lenient) {
/* 88 */     return (c == '=' || (lenient && c == ':'));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\Toml.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */