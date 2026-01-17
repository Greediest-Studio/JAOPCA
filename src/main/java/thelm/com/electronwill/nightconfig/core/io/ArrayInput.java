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
/*    */ public final class ArrayInput
/*    */   extends AbstractInput
/*    */ {
/*    */   private final char[] chars;
/*    */   private final int limit;
/*    */   private int cursor;
/*    */   
/*    */   public ArrayInput(CharsWrapper chars) {
/* 20 */     this(chars.chars, chars.offset, chars.limit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayInput(char[] chars) {
/* 30 */     this(chars, 0, chars.length);
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
/*    */   public ArrayInput(char[] chars, int offset, int limit) {
/* 42 */     this.chars = chars;
/* 43 */     this.cursor = offset;
/* 44 */     this.limit = limit;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int directRead() {
/* 49 */     if (this.cursor >= this.limit) {
/* 50 */       return -1;
/*    */     }
/* 52 */     return this.chars[this.cursor++];
/*    */   }
/*    */ 
/*    */   
/*    */   protected char directReadChar() throws ParsingException {
/* 57 */     if (this.cursor >= this.limit) {
/* 58 */       throw ParsingException.notEnoughData();
/*    */     }
/* 60 */     return this.chars[this.cursor++];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CharsWrapper read(int n) {
/* 67 */     int size = Math.min(n, this.limit - this.cursor + this.deque.size());
/* 68 */     int offset = Math.min(this.deque.size(), size);
/* 69 */     char[] array = new char[size];
/* 70 */     CharsWrapper smaller = consumeDeque(array, offset, false);
/* 71 */     if (smaller != null) {
/* 72 */       return smaller;
/*    */     }
/* 74 */     System.arraycopy(this.chars, this.cursor, array, offset, size - offset);
/* 75 */     this.cursor += size;
/* 76 */     return new CharsWrapper(array);
/*    */   }
/*    */ 
/*    */   
/*    */   public CharsWrapper readChars(int n) {
/* 81 */     if (this.limit - this.cursor + this.deque.size() < n) {
/* 82 */       throw ParsingException.notEnoughData();
/*    */     }
/* 84 */     int offset = Math.min(this.deque.size(), n);
/* 85 */     char[] array = new char[n];
/* 86 */     consumeDeque(array, offset, true);
/* 87 */     System.arraycopy(this.chars, this.cursor, array, offset, n - offset);
/* 88 */     this.cursor += n;
/* 89 */     return new CharsWrapper(array);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ArrayInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */