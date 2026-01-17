/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.time.temporal.Temporal;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.NullObject;
/*    */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*    */ import thelm.com.electronwill.nightconfig.core.io.WritingException;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ValueWriter
/*    */ {
/*    */   private static void writeString(String string, CharacterOutput output, TomlWriter writer) {
/* 18 */     if (writer.writesLiteral(string)) {
/* 19 */       StringWriter.writeLiteral(string, output);
/*    */     } else {
/* 21 */       StringWriter.writeBasic(string, output);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static void write(Object value, CharacterOutput output, TomlWriter writer) {
/* 28 */     if (value instanceof Config)
/* 29 */     { TableWriter.writeInline((UnmodifiableConfig)value, output, writer); }
/* 30 */     else if (value instanceof List)
/* 31 */     { List<?> list = (List)value;
/* 32 */       Objects.requireNonNull(Config.class); if (!list.isEmpty() && list.stream().allMatch(Config.class::isInstance)) {
/* 33 */         Iterator<?> iterator = list.iterator();
/* 34 */         while (iterator.hasNext()) {
/* 35 */           Object table = iterator.next();
/* 36 */           TableWriter.writeInline((UnmodifiableConfig)table, output, writer);
/* 37 */           if (iterator.hasNext()) {
/* 38 */             output.write(ArrayWriter.ELEMENT_SEPARATOR);
/*    */           }
/*    */         } 
/*    */       } else {
/* 42 */         ArrayWriter.write((List)value, output, writer);
/*    */       }  }
/* 44 */     else if (value instanceof CharSequence)
/* 45 */     { writeString(value.toString(), output, writer); }
/* 46 */     else if (value instanceof Enum)
/* 47 */     { writeString(((Enum)value).name(), output, writer); }
/* 48 */     else if (value instanceof Temporal)
/* 49 */     { TemporalWriter.write((Temporal)value, output); }
/* 50 */     else if (value instanceof Float || value instanceof Double)
/* 51 */     { double d = ((Number)value).doubleValue();
/* 52 */       if (Double.isNaN(d)) {
/* 53 */         output.write("nan");
/* 54 */       } else if (d == Double.POSITIVE_INFINITY) {
/* 55 */         output.write("+inf");
/* 56 */       } else if (d == Double.NEGATIVE_INFINITY) {
/* 57 */         output.write("-inf");
/*    */       } else {
/* 59 */         output.write(value.toString());
/*    */       }  }
/* 61 */     else if (value instanceof Number || value instanceof Boolean)
/* 62 */     { output.write(value.toString()); }
/* 63 */     else { if (value == null || value == NullObject.NULL_OBJECT) {
/* 64 */         throw new WritingException("TOML doesn't support null values");
/*    */       }
/* 66 */       throw new WritingException("Unsupported value type: " + value.getClass()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\ValueWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */