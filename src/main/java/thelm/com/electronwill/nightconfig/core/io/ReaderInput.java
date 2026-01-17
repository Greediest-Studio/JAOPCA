/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ReaderInput
/*    */   extends AbstractInput
/*    */ {
/*    */   private final Reader reader;
/*    */   
/*    */   public ReaderInput(Reader reader) {
/* 15 */     this.reader = reader;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int directRead() {
/*    */     try {
/* 21 */       return this.reader.read();
/* 22 */     } catch (IOException e) {
/* 23 */       throw ParsingException.readFailed(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected char directReadChar() throws ParsingException {
/*    */     int read;
/*    */     try {
/* 31 */       read = this.reader.read();
/* 32 */     } catch (IOException e) {
/* 33 */       throw ParsingException.readFailed(e);
/*    */     } 
/* 35 */     if (read == -1) {
/* 36 */       throw ParsingException.notEnoughData();
/*    */     }
/* 38 */     return (char)read;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CharsWrapper read(int n) {
/*    */     int nRead;
/* 45 */     char[] array = new char[n];
/* 46 */     int offset = Math.min(this.deque.size(), n);
/* 47 */     CharsWrapper smaller = consumeDeque(array, offset, false);
/* 48 */     if (smaller != null) {
/* 49 */       return smaller;
/*    */     }
/*    */     
/*    */     try {
/* 53 */       nRead = this.reader.read(array, offset, n - offset);
/* 54 */     } catch (IOException e) {
/* 55 */       throw ParsingException.readFailed(e);
/*    */     } 
/* 57 */     return new CharsWrapper(array, 0, offset + nRead);
/*    */   }
/*    */   
/*    */   public CharsWrapper readChars(int n) {
/*    */     int nRead;
/* 62 */     char[] array = new char[n];
/* 63 */     int offset = Math.min(this.deque.size(), n);
/* 64 */     consumeDeque(array, offset, true);
/* 65 */     int length = n - offset;
/*    */     
/*    */     try {
/* 68 */       nRead = this.reader.read(array, offset, length);
/* 69 */     } catch (IOException e) {
/* 70 */       throw ParsingException.readFailed(e);
/*    */     } 
/* 72 */     if (nRead != length) {
/* 73 */       throw ParsingException.notEnoughData();
/*    */     }
/* 75 */     return new CharsWrapper(array);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ReaderInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */