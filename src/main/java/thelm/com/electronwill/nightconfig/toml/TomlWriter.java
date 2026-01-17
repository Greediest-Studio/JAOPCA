/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ 
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*     */ import thelm.com.electronwill.nightconfig.core.io.IndentStyle;
/*     */ import thelm.com.electronwill.nightconfig.core.io.NewlineStyle;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WriterOutput;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.StringUtils;
/*     */ 
/*     */ public final class TomlWriter
/*     */   implements ConfigWriter {
/*     */   private boolean omitIntermediateLevels = false;
/*     */   private boolean lenientBareKeys = false;
/*  19 */   private Predicate<UnmodifiableConfig> writeTableInlinePredicate = UnmodifiableConfig::isEmpty;
/*     */   private Predicate<String> writeStringLiteralPredicate = c -> false;
/*     */   private Predicate<List<?>> indentArrayElementsPredicate = c -> false;
/*  22 */   private char[] indent = IndentStyle.TABS.chars;
/*  23 */   private char[] newline = (NewlineStyle.system()).chars;
/*     */   
/*     */   private int currentIndentLevel;
/*     */ 
/*     */   
/*     */   public void write(UnmodifiableConfig config, Writer writer) {
/*  29 */     this.currentIndentLevel = -1;
/*  30 */     WriterOutput writerOutput = new WriterOutput(writer);
/*  31 */     TableWriter.writeNormal(config, new ArrayList<>(), (CharacterOutput)writerOutput, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLenientWithBareKeys() {
/*  36 */     return this.lenientBareKeys;
/*     */   }
/*     */   
/*     */   public void setLenientWithBareKeys(boolean lenientBareKeys) {
/*  40 */     this.lenientBareKeys = lenientBareKeys;
/*     */   }
/*     */   
/*     */   public boolean isOmitIntermediateLevels() {
/*  44 */     return this.omitIntermediateLevels;
/*     */   }
/*     */   
/*     */   public void setOmitIntermediateLevels(boolean omitIntermediateLevels) {
/*  48 */     this.omitIntermediateLevels = omitIntermediateLevels;
/*     */   }
/*     */   
/*     */   public void setWriteTableInlinePredicate(Predicate<UnmodifiableConfig> writeTableInlinePredicate) {
/*  52 */     this.writeTableInlinePredicate = writeTableInlinePredicate;
/*     */   }
/*     */   
/*     */   public void setWriteStringLiteralPredicate(Predicate<String> writeStringLiteralPredicate) {
/*  56 */     this.writeStringLiteralPredicate = writeStringLiteralPredicate;
/*     */   }
/*     */   
/*     */   public void setIndentArrayElementsPredicate(Predicate<List<?>> indentArrayElementsPredicate) {
/*  60 */     this.indentArrayElementsPredicate = indentArrayElementsPredicate;
/*     */   }
/*     */   
/*     */   public void setIndent(IndentStyle indentStyle) {
/*  64 */     this.indent = indentStyle.chars;
/*     */   }
/*     */   
/*     */   public void setIndent(String indentString) {
/*  68 */     this.indent = indentString.toCharArray();
/*     */   }
/*     */   
/*     */   public void setNewline(NewlineStyle newlineStyle) {
/*  72 */     this.newline = newlineStyle.chars;
/*     */   }
/*     */   
/*     */   public void setNewline(String newlineString) {
/*  76 */     this.newline = newlineString.toCharArray();
/*     */   }
/*     */ 
/*     */   
/*     */   void increaseIndentLevel() {
/*  81 */     this.currentIndentLevel++;
/*     */   }
/*     */   
/*     */   void decreaseIndentLevel() {
/*  85 */     this.currentIndentLevel--;
/*     */   }
/*     */   
/*     */   void writeIndent(CharacterOutput output) {
/*  89 */     for (int i = 0; i < this.currentIndentLevel; i++) {
/*  90 */       output.write(this.indent);
/*     */     }
/*     */   }
/*     */   
/*     */   void writeNewline(CharacterOutput output) {
/*  95 */     output.write(this.newline);
/*     */   }
/*     */   
/*     */   void writeComment(String commentString, CharacterOutput output) {
/*  99 */     List<String> comments = StringUtils.splitLines(commentString);
/* 100 */     for (String comment : comments) {
/* 101 */       writeIndent(output);
/* 102 */       output.write('#');
/* 103 */       output.write(comment);
/* 104 */       output.write(this.newline);
/*     */     } 
/*     */   }
/*     */   
/*     */   void writeKey(String key, CharacterOutput output) {
/* 109 */     if (Toml.isValidBareKey(key, this.lenientBareKeys)) {
/* 110 */       output.write(key);
/* 111 */     } else if (this.writeStringLiteralPredicate.test(key)) {
/* 112 */       StringWriter.writeLiteral(key, output);
/*     */     } else {
/* 114 */       StringWriter.writeBasic(key, output);
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean writesInline(UnmodifiableConfig config) {
/* 119 */     return this.writeTableInlinePredicate.test(config);
/*     */   }
/*     */   
/*     */   boolean writesLiteral(String string) {
/* 123 */     return this.writeStringLiteralPredicate.test(string);
/*     */   }
/*     */   
/*     */   boolean writesIndented(List<?> list) {
/* 127 */     return this.indentArrayElementsPredicate.test(list);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TomlWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */