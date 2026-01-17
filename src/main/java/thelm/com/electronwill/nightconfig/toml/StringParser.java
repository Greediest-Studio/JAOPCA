/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ 
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*     */ import thelm.com.electronwill.nightconfig.core.io.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class StringParser
/*     */ {
/*  13 */   private static final char[] SINGLE_QUOTE = new char[] { '\'' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String parseBasic(CharacterInput input, TomlParser parser) {
/*  20 */     CharsWrapper.Builder builder = parser.createBuilder();
/*  21 */     boolean escape = false;
/*     */     char c;
/*  23 */     while ((c = input.readChar()) != '"' || escape) {
/*  24 */       if (escape) {
/*  25 */         builder.write(escape(c, input));
/*  26 */         escape = false; continue;
/*  27 */       }  if (c == '\\') {
/*  28 */         escape = true; continue;
/*     */       } 
/*  30 */       builder.write(c);
/*     */     } 
/*     */     
/*  33 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String parseLiteral(CharacterInput input, TomlParser parser) {
/*  41 */     String str = input.readCharsUntil(SINGLE_QUOTE).toString();
/*  42 */     input.readChar();
/*  43 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String parseMultiBasic(CharacterInput input, TomlParser parser) {
/*  51 */     CharsWrapper.Builder builder = parser.createBuilder();
/*     */     char c;
/*  53 */     while ((c = input.readChar()) != '"' || input.peek() != 34 || input.peek(1) != 34) {
/*  54 */       if (c == '\\') {
/*  55 */         char next = input.readChar();
/*  56 */         if (next == '\n' || (next == '\r' && input
/*  57 */           .peekChar() == '\n') || ((next == '\t' || next == ' ') && 
/*  58 */           isWhitespace((CharSequence)Toml.readLine(input)))) {
/*     */           
/*  60 */           char nextNonSpace = Toml.readNonSpaceChar(input, true);
/*  61 */           input.pushBack(nextNonSpace); continue;
/*     */         } 
/*  63 */         if (next == '\t' || next == ' ') {
/*  64 */           throw new ParsingException("Invalid escapement: \\" + next);
/*     */         }
/*  66 */         builder.write(escape(next, input)); continue;
/*     */       } 
/*  68 */       builder.write(c);
/*     */     } 
/*     */     
/*  71 */     input.skipPeeks();
/*  72 */     return buildMultilineString(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String parseMultiLiteral(CharacterInput input, TomlParser parser) {
/*  80 */     CharsWrapper.Builder builder = parser.createBuilder();
/*     */     char c;
/*  82 */     while ((c = input.readChar()) != '\'' || input.peek() != 39 || input.peek(1) != 39) {
/*  83 */       builder.append(c);
/*     */     }
/*  85 */     input.skipPeeks();
/*  86 */     return buildMultilineString(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String buildMultilineString(CharsWrapper.Builder builder) {
/*  94 */     if (builder.get(0) == '\n') {
/*  95 */       return builder.toString(1);
/*     */     }
/*  97 */     if (builder.get(0) == '\r' && builder.get(1) == '\n') {
/*  98 */       return builder.toString(2);
/*     */     }
/* 100 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static char escape(char c, CharacterInput input) {
/*     */     CharsWrapper chars;
/* 109 */     switch (c) {
/*     */       case '"':
/*     */       case '\\':
/* 112 */         return c;
/*     */       case 'b':
/* 114 */         return '\b';
/*     */       case 'f':
/* 116 */         return '\f';
/*     */       case 'n':
/* 118 */         return '\n';
/*     */       case 'r':
/* 120 */         return '\r';
/*     */       case 't':
/* 122 */         return '\t';
/*     */       case 'u':
/* 124 */         chars = input.readChars(4);
/* 125 */         return (char)Utils.parseInt(chars, 16);
/*     */       case 'U':
/* 127 */         chars = input.readChars(8);
/* 128 */         return (char)Utils.parseInt(chars, 16);
/*     */     } 
/* 130 */     throw new ParsingException("Invalid escapement: \\" + c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isWhitespace(CharSequence csq) {
/* 140 */     for (int i = 0; i < csq.length(); i++) {
/* 141 */       char c = csq.charAt(i);
/* 142 */       if (c != '\t' && c != ' ') {
/* 143 */         return false;
/*     */       }
/*     */     } 
/* 146 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\StringParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */