/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TableParser
/*     */ {
/*  18 */   private static final char[] KEY_END = new char[] { '\t', ' ', '=', '.', '\n', '\r', ']', ':' };
/*     */   
/*     */   static CommentedConfig parseInline(CharacterInput input, TomlParser parser) {
/*  21 */     CommentedConfig config = (CommentedConfig)TomlFormat.instance().createConfig();
/*  22 */     parser.registerInlineTable((Config)config);
/*     */     while (true) {
/*  24 */       char keyFirst = Toml.readNonSpaceChar(input, false);
/*  25 */       if (keyFirst == '}') {
/*  26 */         return config;
/*     */       }
/*  28 */       String key = parseKey(input, keyFirst, parser);
/*  29 */       char sep = Toml.readNonSpaceChar(input, false);
/*  30 */       checkInvalidSeparator(sep, key, parser);
/*     */       
/*  32 */       Object value = ValueParser.parse(input, parser);
/*  33 */       Object previous = parser.getParsingMode().put(config.valueMap(), key, value);
/*  34 */       checkDuplicateKey(key, previous, true);
/*     */       
/*  36 */       char after = Toml.readNonSpaceChar(input, false);
/*  37 */       if (after == '}') {
/*  38 */         return config;
/*     */       }
/*  40 */       if (after != ',') {
/*  41 */         throw new ParsingException("Invalid entry separator '" + after + "' in inline table.");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static <T extends CommentedConfig> T parseNormal(CharacterInput input, TomlParser parser, T config) {
/*     */     while (true) {
/*  50 */       List<CharsWrapper> commentsList = new ArrayList<>(2);
/*  51 */       int keyFirst = Toml.readUseful(input, commentsList);
/*  52 */       if (keyFirst == -1 || keyFirst == 91) {
/*  53 */         parser.setComment(commentsList);
/*  54 */         return config;
/*     */       } 
/*  56 */       List<String> key = parseDottedKey(input, (char)keyFirst, parser);
/*     */       
/*  58 */       Object value = ValueParser.parse(input, parser);
/*  59 */       Object previous = parser.getParsingMode().put((Config)config, key, value);
/*  60 */       checkDuplicateKey(key, previous, parser.configWasEmpty());
/*     */       
/*  62 */       int after = Toml.readNonSpace(input, false);
/*  63 */       if (after == -1) {
/*  64 */         return config;
/*     */       }
/*  66 */       if (after == 35) {
/*  67 */         CharsWrapper comment = Toml.readLine(input);
/*  68 */         commentsList.add(comment);
/*  69 */       } else if (after != 10 && after != 13) {
/*  70 */         throw new ParsingException("Invalid character '" + (char)after + "' after table entry \"" + key + "\" = " + value);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       parser.setComment(commentsList);
/*  78 */       config.setComment(key, parser.consumeComment());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkDuplicateKey(Object key, Object previousValue, boolean emptyConfig) {
/*  88 */     if (previousValue != null && emptyConfig) {
/*  89 */       throw new ParsingException("Invalid TOML data: entry \"" + key + "\" defined twice in its table.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkInvalidSeparator(char sep, String key, TomlParser parser) {
/*  95 */     if (!Toml.isKeyValueSeparator(sep, parser.isLenientWithSeparators())) {
/*  96 */       throw new ParsingException("Invalid separator '" + sep + "'after key \"" + key + "\" in some table.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static CommentedConfig parseNormal(CharacterInput input, TomlParser parser) {
/* 102 */     return parseNormal(input, parser, (CommentedConfig)TomlFormat.instance().createConfig());
/*     */   }
/*     */   
/*     */   static List<String> parseTableName(CharacterInput input, TomlParser parser, boolean array) {
/* 106 */     List<String> list = parser.createList();
/*     */     while (true) {
/* 108 */       char firstChar = Toml.readNonSpaceChar(input, false);
/* 109 */       if (firstChar == ']') {
/* 110 */         throw new ParsingException("Tables names must not be empty.");
/*     */       }
/* 112 */       String key = parseKey(input, firstChar, parser);
/* 113 */       list.add(key);
/*     */       
/* 115 */       char separator = Toml.readNonSpaceChar(input, false);
/* 116 */       if (separator == ']') {
/* 117 */         if (array) {
/* 118 */           char c = input.readChar();
/* 119 */           if (c != ']') {
/* 120 */             throw new ParsingException("Invalid declaration of an element of an array of tables: it ends by ]" + c + " but should end by ]]");
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         char after = Toml.readNonSpaceChar(input, false);
/* 128 */         if (after == '#') {
/* 129 */           CharsWrapper comment = Toml.readLine(input);
/* 130 */           parser.setComment(comment);
/* 131 */         } else if (after != '\n' && after != '\r') {
/* 132 */           throw new ParsingException("Invalid character '" + after + "' after a table declaration.");
/*     */         } 
/*     */         
/* 135 */         return list;
/* 136 */       }  if (separator != '.') {
/* 137 */         throw new ParsingException("Invalid separator '" + separator + "' in table name.");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   static List<String> parseDottedKey(CharacterInput input, char firstChar, TomlParser parser) {
/* 143 */     List<String> list = parser.createList();
/* 144 */     char first = firstChar;
/*     */     while (true) {
/* 146 */       String part = parseKey(input, first, parser);
/* 147 */       list.add(part);
/*     */       
/* 149 */       char sep = Toml.readNonSpaceChar(input, false);
/* 150 */       if (Toml.isKeyValueSeparator(sep, parser.isLenientWithSeparators()))
/* 151 */         return list; 
/* 152 */       if (sep != '.') {
/* 153 */         throw new ParsingException("Invalid character '" + sep + "' after key " + list);
/*     */       }
/* 155 */       first = Toml.readNonSpaceChar(input, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static String parseKey(CharacterInput input, char firstChar, TomlParser parser) {
/* 162 */     if (firstChar == '"')
/* 163 */       return StringParser.parseBasic(input, parser); 
/* 164 */     if (firstChar == '\'') {
/* 165 */       return StringParser.parseLiteral(input, parser);
/*     */     }
/* 167 */     CharsWrapper restOfKey = input.readCharsUntil(KEY_END);
/*     */ 
/*     */     
/* 170 */     String bareKey = (new CharsWrapper.Builder(restOfKey.length() + 1)).append(firstChar).append(restOfKey).toString();
/*     */     
/* 172 */     if (bareKey.isEmpty()) {
/* 173 */       throw new ParsingException("Empty bare keys aren't allowed.");
/*     */     }
/* 175 */     if (!Toml.isValidBareKey(bareKey, parser.isLenientWithBareKeys())) {
/* 176 */       throw new ParsingException("Invalid bare key: " + bareKey);
/*     */     }
/* 178 */     return bareKey;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TableParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */