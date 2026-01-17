/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ArrayWriter
/*    */ {
/* 11 */   private static final char[] EMPTY_ARRAY = new char[] { '[', ']' };
/* 12 */   static final char[] ELEMENT_SEPARATOR = new char[] { ',', ' ' };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void write(List<?> values, CharacterOutput output, TomlWriter writer) {
/* 18 */     if (values.isEmpty()) {
/* 19 */       output.write(EMPTY_ARRAY);
/*    */       return;
/*    */     } 
/* 22 */     output.write('[');
/* 23 */     boolean indent = writer.writesIndented(values);
/* 24 */     if (indent) {
/* 25 */       writer.increaseIndentLevel();
/*    */     }
/* 27 */     Iterator<?> iterator = values.iterator();
/* 28 */     for (boolean hasNext = iterator.hasNext(); hasNext; ) {
/* 29 */       if (indent) {
/* 30 */         writer.writeNewline(output);
/* 31 */         writer.writeIndent(output);
/*    */       } 
/* 33 */       Object value = iterator.next();
/* 34 */       ValueWriter.write(value, output, writer);
/* 35 */       if (hasNext = iterator.hasNext()) {
/* 36 */         if (indent) {
/* 37 */           output.write(','); continue;
/*    */         } 
/* 39 */         output.write(ELEMENT_SEPARATOR);
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     if (indent) {
/* 44 */       writer.decreaseIndentLevel();
/* 45 */       writer.writeNewline(output);
/*    */     } 
/* 47 */     output.write(']');
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\ArrayWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */