/*     */ package thelm.com.electronwill.nightconfig.core.io;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface CharacterInput
/*     */ {
/*     */   int read();
/*     */   
/*     */   char readChar();
/*     */   
/*     */   default int readAndSkip(char[] toSkip) {
/*     */     int c;
/*     */     do {
/*  42 */       c = read();
/*  43 */     } while (c != -1 && Utils.arrayContains(toSkip, (char)c));
/*  44 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default char readCharAndSkip(char[] toSkip) {
/*     */     while (true) {
/*  59 */       char c = readChar();
/*  60 */       if (!Utils.arrayContains(toSkip, c)) {
/*  61 */         return c;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CharsWrapper read(int n) {
/*  72 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(n);
/*  73 */     for (int i = 0; i < n; i++) {
/*  74 */       int next = read();
/*  75 */       if (next == -1)
/*     */         break; 
/*  77 */       builder.append((char)next);
/*     */     } 
/*  79 */     return builder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CharsWrapper readChars(int n) {
/*  92 */     char[] chars = new char[n];
/*  93 */     for (int i = 0; i < n; i++) {
/*  94 */       int next = read();
/*  95 */       if (next == -1) {
/*  96 */         throw ParsingException.notEnoughData();
/*     */       }
/*  98 */       chars[i] = (char)next;
/*     */     } 
/* 100 */     return new CharsWrapper(chars);
/*     */   }
/*     */   
/*     */   CharsWrapper readUntil(char[] paramArrayOfchar);
/*     */   
/*     */   CharsWrapper readCharsUntil(char[] paramArrayOfchar);
/*     */   
/*     */   int peek();
/*     */   
/*     */   int peek(int paramInt);
/*     */   
/*     */   char peekChar();
/*     */   
/*     */   char peekChar(int paramInt);
/*     */   
/*     */   void skipPeeks();
/*     */   
/*     */   void pushBack(char paramChar);
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\CharacterInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */