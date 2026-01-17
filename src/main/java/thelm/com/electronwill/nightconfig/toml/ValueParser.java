/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*     */ import thelm.com.electronwill.nightconfig.core.io.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ValueParser
/*     */ {
/*  16 */   private static final char[] END_OF_VALUE = new char[] { '\t', ' ', '\n', '\r', ',', ']', '}' };
/*  17 */   private static final char[] END_OF_VALUE_DATE = new char[] { '\t', '#', '\n', '\r', ',', ']', '}' };
/*  18 */   private static final char[] TRUE_END = new char[] { 'r', 'u', 'e' }, FALSE_END = new char[] { 'a', 'l', 's', 'e' };
/*  19 */   private static final char[] ONLY_IN_FP_NUMBER = new char[] { '.', 'e', 'E' };
/*  20 */   private static final char[] FP_INFINITY = new char[] { 'i', 'n', 'f' };
/*  21 */   private static final char[] FP_NAN = new char[] { 'n', 'a', 'n' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object parse(CharacterInput input, char firstChar, TomlParser parser) {
/*  28 */     switch (firstChar) {
/*     */       case '{':
/*  30 */         return TableParser.parseInline(input, parser);
/*     */       case '[':
/*  32 */         return ArrayParser.parse(input, parser);
/*     */       case '\'':
/*  34 */         if (input.peek() == 39 && input.peek(1) == 39) {
/*  35 */           input.skipPeeks();
/*  36 */           return StringParser.parseMultiLiteral(input, parser);
/*     */         } 
/*  38 */         return StringParser.parseLiteral(input, parser);
/*     */       case '"':
/*  40 */         if (input.peek() == 34 && input.peek(1) == 34) {
/*  41 */           input.skipPeeks();
/*  42 */           return StringParser.parseMultiBasic(input, parser);
/*     */         } 
/*  44 */         return StringParser.parseBasic(input, parser);
/*     */       case 't':
/*  46 */         return parseTrue(input);
/*     */       case 'f':
/*  48 */         return parseFalse(input);
/*     */       case '+':
/*     */       case '-':
/*  51 */         input.pushBack(firstChar);
/*  52 */         return parseNumber(input.readUntil(END_OF_VALUE));
/*     */     } 
/*  54 */     input.pushBack(firstChar);
/*  55 */     CharsWrapper valueChars = input.readUntil(END_OF_VALUE_DATE);
/*  56 */     if (shouldBeTemporal(valueChars)) {
/*  57 */       return TemporalParser.parse(valueChars);
/*     */     }
/*  59 */     CharsWrapper trimmed = valueChars.trimmedView();
/*  60 */     if (trimmed.isEmpty()) {
/*  61 */       throw new ParsingException("Invalid value containing only whitespaces");
/*     */     }
/*  63 */     return parseNumber(trimmed);
/*     */   }
/*     */ 
/*     */   
/*     */   static Object parse(CharacterInput input, TomlParser parser) {
/*  68 */     return parse(input, Toml.readNonSpaceChar(input, false), parser);
/*     */   }
/*     */   
/*     */   private static boolean shouldBeTemporal(CharsWrapper valueChars) {
/*  72 */     return (valueChars.length() >= 8 && (valueChars
/*  73 */       .get(2) == ':' || (valueChars.get(4) == '-' && valueChars.get(7) == '-')));
/*     */   } private static Number parseNumber(CharsWrapper valueChars) {
/*     */     CharsWrapper remaining;
/*     */     long longValue;
/*  77 */     valueChars = simplifyNumber(valueChars);
/*     */     
/*  79 */     char first = valueChars.get(0);
/*     */     
/*  81 */     if (first == '-') {
/*  82 */       remaining = valueChars.subView(1);
/*  83 */       if (remaining.contentEquals(FP_INFINITY)) {
/*  84 */         return Double.valueOf(Double.NEGATIVE_INFINITY);
/*     */       }
/*  86 */     } else if (first == '+') {
/*  87 */       remaining = valueChars.subView(1);
/*     */     } else {
/*  89 */       remaining = valueChars;
/*     */     } 
/*  91 */     if (remaining.contentEquals(FP_INFINITY))
/*  92 */       return Double.valueOf(Double.POSITIVE_INFINITY); 
/*  93 */     if (remaining.contentEquals(FP_NAN)) {
/*  94 */       return Double.valueOf(Double.NaN);
/*     */     }
/*     */     
/*  97 */     CharsWrapper numberChars = valueChars;
/*  98 */     int base = 10;
/*  99 */     if (valueChars.length() > 2) {
/* 100 */       switch (valueChars.subView(0, 2).toString()) {
/*     */         case "0x":
/* 102 */           base = 16;
/*     */           break;
/*     */         case "0b":
/* 105 */           base = 2;
/*     */           break;
/*     */         case "0o":
/* 108 */           base = 8;
/*     */           break;
/*     */       } 
/* 111 */       if (base != 10) {
/* 112 */         numberChars = valueChars.subView(2);
/*     */       }
/*     */     } 
/*     */     
/* 116 */     if (base == 10 && valueChars.indexOf('.') != -1) {
/*     */       try {
/* 118 */         return Double.valueOf(Utils.parseDouble(valueChars));
/* 119 */       } catch (NumberFormatException ex) {
/* 120 */         throw new ParsingException("Invalid floating-point value: " + valueChars);
/*     */       } 
/*     */     }
/*     */     
/*     */     try {
/* 125 */       longValue = Utils.parseLong(numberChars, base);
/* 126 */     } catch (NumberFormatException ex) {
/* 127 */       throw new ParsingException("Invalid integer value: " + valueChars);
/*     */     } 
/* 129 */     int intValue = (int)longValue;
/* 130 */     if (intValue == longValue) {
/* 131 */       return Integer.valueOf(intValue);
/*     */     }
/* 133 */     return Long.valueOf(longValue);
/*     */   }
/*     */   
/*     */   private static CharsWrapper simplifyNumber(CharsWrapper numberChars) {
/* 137 */     if (numberChars.charAt(0) == '_') {
/* 138 */       throw new ParsingException("Invalid leading underscore in number " + numberChars);
/*     */     }
/* 140 */     if (numberChars.charAt(numberChars.length() - 1) == '_') {
/* 141 */       throw new ParsingException("Invalid trailing underscore in number " + numberChars);
/*     */     }
/* 143 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(16);
/* 144 */     boolean nextCannotBeUnderscore = false;
/* 145 */     for (Iterator<Character> iterator = numberChars.iterator(); iterator.hasNext(); ) { char c = ((Character)iterator.next()).charValue();
/* 146 */       if (c == '_') {
/* 147 */         if (nextCannotBeUnderscore) {
/* 148 */           throw new ParsingException("Invalid underscore followed by another one in number " + numberChars);
/*     */         }
/*     */ 
/*     */         
/* 152 */         nextCannotBeUnderscore = true; continue;
/*     */       } 
/* 154 */       if (nextCannotBeUnderscore) {
/* 155 */         nextCannotBeUnderscore = false;
/*     */       }
/* 157 */       builder.append(c); }
/*     */ 
/*     */     
/* 160 */     return builder.build();
/*     */   }
/*     */   
/*     */   private static Boolean parseFalse(CharacterInput input) {
/* 164 */     CharsWrapper remaining = input.readUntil(END_OF_VALUE);
/* 165 */     if (!remaining.contentEquals(FALSE_END)) {
/* 166 */       throw new ParsingException("Invalid value f" + remaining + " - Expected the boolean value false.");
/*     */     }
/*     */     
/* 169 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private static Boolean parseTrue(CharacterInput input) {
/* 173 */     CharsWrapper remaining = input.readUntil(END_OF_VALUE);
/* 174 */     if (!remaining.contentEquals(TRUE_END)) {
/* 175 */       throw new ParsingException("Invalid value t" + remaining + " - Expected the boolean value true.");
/*     */     }
/*     */     
/* 178 */     return Boolean.valueOf(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\ValueParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */