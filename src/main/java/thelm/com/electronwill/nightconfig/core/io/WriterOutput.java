/*    */ package thelm.com.electronwill.nightconfig.core.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WriterOutput
/*    */   implements CharacterOutput
/*    */ {
/*    */   private final Writer writer;
/*    */   
/*    */   public WriterOutput(Writer writer) {
/* 15 */     this.writer = writer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char c) {
/*    */     try {
/* 21 */       this.writer.write(c);
/* 22 */     } catch (IOException e) {
/* 23 */       throw new WritingException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char[] chars, int offset, int length) {
/*    */     try {
/* 30 */       this.writer.write(chars, offset, length);
/* 31 */     } catch (IOException e) {
/* 32 */       throw new WritingException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String s, int offset, int length) {
/*    */     try {
/* 39 */       this.writer.write(s, offset, length);
/* 40 */     } catch (IOException e) {
/* 41 */       throw new WritingException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\WriterOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */