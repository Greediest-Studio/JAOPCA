/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingMode;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ReaderInput;
/*     */ 
/*     */ public final class TomlParser implements ConfigParser<CommentedConfig> {
/*  19 */   private int initialStringBuilderCapacity = 16; private int initialListCapacity = 10;
/*     */   
/*     */   private boolean lenientBareKeys = false;
/*     */   
/*     */   private boolean lenientSeparators = false;
/*     */   private boolean configWasEmpty = false;
/*     */   private ParsingMode parsingMode;
/*  26 */   private final Set<Config> inlineTables = Collections.newSetFromMap(new IdentityHashMap<>()); private String currentComment;
/*     */   
/*     */   void registerInlineTable(Config table) {
/*  29 */     this.inlineTables.add(table);
/*     */   }
/*     */   
/*     */   boolean isInlineTable(Config table) {
/*  33 */     return this.inlineTables.contains(table);
/*     */   }
/*     */   
/*     */   private void clearParsingState() {
/*  37 */     this.inlineTables.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CommentedConfig parse(Reader reader) {
/*  43 */     this.configWasEmpty = true;
/*  44 */     return parse((CharacterInput)new ReaderInput(reader), (CommentedConfig)TomlFormat.instance().createConfig(), ParsingMode.MERGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(Reader reader, Config destination, ParsingMode parsingMode) {
/*  49 */     if (parsingMode == ParsingMode.REPLACE) {
/*  50 */       this.configWasEmpty = true;
/*     */     }
/*  52 */     parse((CharacterInput)new ReaderInput(reader), destination, parsingMode);
/*     */   }
/*     */   
/*     */   private <T extends Config> T parse(CharacterInput input, T destination, ParsingMode parsingMode) {
/*  56 */     this.parsingMode = parsingMode;
/*  57 */     parsingMode.prepareParsing((Config)destination);
/*  58 */     CommentedConfig commentedConfig = CommentedConfig.fake((Config)destination);
/*  59 */     CommentedConfig rootTable = TableParser.parseNormal(input, this, commentedConfig);
/*     */     int next;
/*  61 */     while ((next = input.peek()) != -1) {
/*  62 */       boolean isArray = (next == 91);
/*  63 */       if (isArray) {
/*  64 */         input.skipPeeks();
/*     */       }
/*  66 */       List<String> path = TableParser.parseTableName(input, this, isArray);
/*  67 */       int lastIndex = path.size() - 1;
/*  68 */       String lastKey = path.get(lastIndex);
/*  69 */       List<String> parentPath = path.subList(0, lastIndex);
/*  70 */       Config parentConfig = getSubTable((Config)rootTable, parentPath);
/*     */       
/*  72 */       Map<String, Object> parentMap = (parentConfig != null) ? parentConfig.valueMap() : null;
/*  73 */       if (hasPendingComment()) {
/*  74 */         String comment = consumeComment();
/*  75 */         if (parentConfig instanceof CommentedConfig) {
/*  76 */           List<String> lastPath = Collections.singletonList(lastKey);
/*  77 */           ((CommentedConfig)parentConfig).setComment(lastPath, comment);
/*     */         } 
/*     */       } 
/*  80 */       if (isArray) {
/*  81 */         if (parentMap == null) {
/*  82 */           throw new ParsingException("Cannot create entry " + path + " because of an invalid parent that isn't a table.");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  87 */         CommentedConfig table = TableParser.parseNormal(input, this);
/*  88 */         List<CommentedConfig> arrayOfTables = (List<CommentedConfig>)parentMap.get(lastKey);
/*  89 */         if (arrayOfTables == null) {
/*  90 */           arrayOfTables = createList();
/*  91 */           parentMap.put(lastKey, arrayOfTables);
/*     */         } 
/*  93 */         arrayOfTables.add(table); continue;
/*     */       } 
/*  95 */       if (parentMap == null) {
/*  96 */         throw new ParsingException("Cannot create entry " + path + " because of an invalid parent that isn't a table.");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 101 */       Object alreadyDeclared = parentMap.get(lastKey);
/* 102 */       if (alreadyDeclared == null) {
/* 103 */         CommentedConfig table = TableParser.parseNormal(input, this);
/* 104 */         parentMap.put(lastKey, table); continue;
/*     */       } 
/* 106 */       if (alreadyDeclared instanceof Config) {
/* 107 */         Config table = (Config)alreadyDeclared;
/* 108 */         checkContainsOnlySubtables(table, path);
/* 109 */         CommentedConfig commentedTable = CommentedConfig.fake(table);
/* 110 */         TableParser.parseNormal(input, this, commentedTable); continue;
/* 111 */       }  if (this.configWasEmpty) {
/* 112 */         throw new ParsingException("Entry " + path + " has been defined twice.");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 117 */     clearParsingState();
/* 118 */     return destination;
/*     */   }
/*     */   
/*     */   private Config getSubTable(Config parentTable, List<String> path) {
/* 122 */     if (path.isEmpty()) {
/* 123 */       return parentTable;
/*     */     }
/* 125 */     Config currentConfig = parentTable;
/* 126 */     for (String key : path) {
/* 127 */       Object value = currentConfig.valueMap().get(key);
/* 128 */       if (value == null) {
/* 129 */         Config sub = TomlFormat.instance().createConfig();
/* 130 */         currentConfig.valueMap().put(key, sub);
/* 131 */         currentConfig = sub;
/* 132 */       } else if (value instanceof Config) {
/* 133 */         currentConfig = (Config)value;
/* 134 */       } else if (value instanceof List) {
/* 135 */         List<?> list = (List)value;
/* 136 */         Objects.requireNonNull(Config.class); if (!list.isEmpty() && list.stream().allMatch(Config.class::isInstance)) {
/* 137 */           int lastIndex = list.size() - 1;
/* 138 */           currentConfig = (Config)list.get(lastIndex);
/*     */         } else {
/* 140 */           return null;
/*     */         } 
/*     */       } else {
/* 143 */         return null;
/*     */       } 
/* 145 */       if (isInlineTable(currentConfig))
/*     */       {
/* 147 */         throw new ParsingException("Cannot modify an inline table after its creation. Key path: " + path);
/*     */       }
/*     */     } 
/* 150 */     return currentConfig;
/*     */   }
/*     */   
/*     */   private void checkContainsOnlySubtables(Config table, List<String> path) {
/* 154 */     for (Object value : table.valueMap().values()) {
/* 155 */       if (!(value instanceof Config)) {
/* 156 */         throw new ParsingException("Table with path " + path + " has been declared twice.");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLenientWithSeparators() {
/* 163 */     return this.lenientSeparators;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TomlParser setLenientWithSeparators(boolean lenientSeparators) {
/* 175 */     this.lenientSeparators = lenientSeparators;
/* 176 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isLenientWithBareKeys() {
/* 180 */     return this.lenientBareKeys;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TomlParser setLenientWithBareKeys(boolean lenientBareKeys) {
/* 192 */     this.lenientBareKeys = lenientBareKeys;
/* 193 */     return this;
/*     */   }
/*     */   
/*     */   public TomlParser setInitialStringBuilderCapacity(int initialStringBuilderCapacity) {
/* 197 */     this.initialStringBuilderCapacity = initialStringBuilderCapacity;
/* 198 */     return this;
/*     */   }
/*     */   
/*     */   public TomlParser setInitialListCapacity(int initialListCapacity) {
/* 202 */     this.initialListCapacity = initialListCapacity;
/* 203 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigFormat<CommentedConfig> getFormat() {
/* 208 */     return TomlFormat.instance();
/*     */   }
/*     */   
/*     */   boolean configWasEmpty() {
/* 212 */     return this.configWasEmpty;
/*     */   }
/*     */   
/*     */   ParsingMode getParsingMode() {
/* 216 */     return this.parsingMode;
/*     */   }
/*     */ 
/*     */   
/*     */   <T> List<T> createList() {
/* 221 */     return new ArrayList<>(this.initialListCapacity);
/*     */   }
/*     */   
/*     */   CharsWrapper.Builder createBuilder() {
/* 225 */     return new CharsWrapper.Builder(this.initialStringBuilderCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasPendingComment() {
/* 232 */     return (this.currentComment != null);
/*     */   }
/*     */   
/*     */   String consumeComment() {
/* 236 */     String comment = this.currentComment;
/* 237 */     this.currentComment = null;
/* 238 */     return comment;
/*     */   }
/*     */   
/*     */   void setComment(CharsWrapper comment) {
/* 242 */     if (comment != null) {
/* 243 */       if (this.currentComment == null) {
/* 244 */         this.currentComment = comment.toString();
/*     */       } else {
/* 246 */         this.currentComment += '\n' + comment.toString();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   void setComment(List<CharsWrapper> commentsList) {
/* 252 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(32);
/* 253 */     if (!commentsList.isEmpty()) {
/* 254 */       Iterator<CharsWrapper> it = commentsList.iterator();
/* 255 */       builder.append(it.next());
/* 256 */       while (it.hasNext()) {
/* 257 */         builder.append('\n');
/* 258 */         builder.append(it.next());
/*     */       } 
/* 260 */       setComment(builder.build());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TomlParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */