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
/*    */ public final class Utils
/*    */ {
/*    */   public static boolean arrayContains(char[] array, char element) {
/* 19 */     for (char c : array) {
/* 20 */       if (c == element) {
/* 21 */         return true;
/*    */       }
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int arrayIndexOf(char[] array, char element) {
/* 36 */     for (int i = 0; i < array.length; i++) {
/* 37 */       if (array[i] == element) {
/* 38 */         return i;
/*    */       }
/*    */     } 
/* 41 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long parseLong(CharsWrapper chars, int base) {
/* 53 */     int offset = chars.offset;
/* 54 */     boolean negative = false;
/* 55 */     char firstChar = chars.charAt(0);
/* 56 */     if (firstChar == '-') {
/* 57 */       negative = true;
/* 58 */       offset++;
/* 59 */     } else if (firstChar == '+') {
/* 60 */       offset++;
/*    */     } 
/* 62 */     long value = 0L, coefficient = 1L;
/* 63 */     char[] array = chars.chars;
/* 64 */     for (int i = chars.limit - 1; i >= offset; i--) {
/* 65 */       int digitValue = Character.digit(array[i], base);
/* 66 */       if (digitValue == -1) {
/* 67 */         throw new ParsingException("Invalid value: " + chars);
/*    */       }
/* 69 */       value += digitValue * coefficient;
/* 70 */       coefficient *= base;
/*    */     } 
/* 72 */     return negative ? -value : value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int parseInt(CharsWrapper chars, int base) {
/* 83 */     return (int)parseLong(chars, base);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static double parseDouble(CharsWrapper chars) {
/* 93 */     return Double.parseDouble(chars.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */