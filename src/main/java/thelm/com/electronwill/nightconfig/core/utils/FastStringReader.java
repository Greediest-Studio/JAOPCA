/*    */ package thelm.com.electronwill.nightconfig.core.utils;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FastStringReader
/*    */   extends Reader
/*    */ {
/*    */   private final String str;
/*    */   private final int lim;
/* 14 */   private int cursor = 0;
/*    */   
/*    */   public FastStringReader(String str, int lim) {
/* 17 */     if (lim > str.length() || lim < 0) {
/* 18 */       throw new IllegalArgumentException("Invalid limit " + lim + ": must be >= 0 and < str.length()");
/*    */     }
/* 20 */     this.str = Objects.<String>requireNonNull(str, "The string must not be null.");
/* 21 */     this.lim = lim;
/*    */   }
/*    */   private int mark;
/*    */   public FastStringReader(String str) {
/* 25 */     this.str = Objects.<String>requireNonNull(str, "The string must not be null.");
/* 26 */     this.lim = str.length();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() {
/* 31 */     return (this.cursor < this.lim) ? this.str.charAt(this.cursor++) : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf, int off, int len) {
/* 36 */     if (this.cursor == this.lim) {
/* 37 */       return -1;
/*    */     }
/* 39 */     len = Math.min(len, this.lim - this.cursor);
/* 40 */     int srcEnd = this.cursor + len;
/* 41 */     this.str.getChars(this.cursor, srcEnd, cbuf, off);
/* 42 */     this.cursor = srcEnd;
/* 43 */     return len;
/*    */   }
/*    */ 
/*    */   
/*    */   public long skip(long n) {
/* 48 */     int skip = (int)n;
/* 49 */     this.cursor += skip;
/* 50 */     return skip;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean markSupported() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void mark(int readAheadLimit) {
/* 60 */     this.mark = this.cursor;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.cursor = this.mark;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean ready() {
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\FastStringReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */