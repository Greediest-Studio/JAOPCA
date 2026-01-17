/*     */ package thelm.com.electronwill.nightconfig.toml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WritingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TableWriter
/*     */ {
/*  18 */   private static final char[] KEY_VALUE_SEPARATOR = new char[] { ' ', '=', ' ' };
/*  19 */   private static final char[] INLINE_ENTRY_SEPARATOR = ArrayWriter.ELEMENT_SEPARATOR;
/*  20 */   private static final char[] ARRAY_OF_TABLES_NAME_BEGIN = new char[] { '[', '[' };
/*  21 */   private static final char[] ARRAY_OF_TABLES_NAME_END = new char[] { ']', ']' };
/*  22 */   private static final char[] TABLE_NAME_BEGIN = new char[] { '[' };
/*  23 */   private static final char[] TABLE_NAME_END = new char[] { ']' };
/*     */   
/*     */   static void writeInline(UnmodifiableConfig config, CharacterOutput output, TomlWriter writer) {
/*  26 */     output.write('{');
/*  27 */     Iterator<Map.Entry<String, Object>> iterator = config.valueMap().entrySet().iterator();
/*  28 */     while (iterator.hasNext()) {
/*  29 */       Map.Entry<String, Object> entry = iterator.next();
/*  30 */       String key = entry.getKey();
/*  31 */       Object value = entry.getValue();
/*     */       
/*  33 */       writer.writeKey(key, output);
/*  34 */       output.write(KEY_VALUE_SEPARATOR);
/*  35 */       ValueWriter.write(value, output, writer);
/*  36 */       if (iterator.hasNext()) {
/*  37 */         output.write(INLINE_ENTRY_SEPARATOR);
/*     */       }
/*     */     } 
/*  40 */     output.write('}');
/*     */   }
/*     */ 
/*     */   
/*     */   static void writeNormal(UnmodifiableConfig config, List<String> configPath, CharacterOutput output, TomlWriter writer) {
/*  45 */     UnmodifiableCommentedConfig commentedConfig = UnmodifiableCommentedConfig.fake(config);
/*  46 */     writeNormal(commentedConfig, configPath, output, writer);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeNormal(UnmodifiableCommentedConfig config, List<String> configPath, CharacterOutput output, TomlWriter writer) {
/*  51 */     List<UnmodifiableCommentedConfig.Entry> tablesEntries = new ArrayList<>();
/*  52 */     List<UnmodifiableCommentedConfig.Entry> tableArraysEntries = new ArrayList<>();
/*     */ 
/*     */     
/*  55 */     writer.increaseIndentLevel();
/*  56 */     for (UnmodifiableCommentedConfig.Entry entry : config.entrySet()) {
/*  57 */       String key = entry.getKey();
/*  58 */       Object value = entry.getValue();
/*  59 */       String comment = entry.getComment();
/*  60 */       if (value instanceof UnmodifiableConfig && 
/*  61 */         !writer.writesInline((UnmodifiableConfig)value)) {
/*  62 */         tablesEntries.add(entry); continue;
/*     */       } 
/*  64 */       if (value instanceof List) {
/*  65 */         List<?> list = (List)value;
/*  66 */         Objects.requireNonNull(UnmodifiableConfig.class); if (!list.isEmpty() && list.stream().allMatch(UnmodifiableConfig.class::isInstance)) {
/*  67 */           tableArraysEntries.add(entry);
/*     */           continue;
/*     */         } 
/*     */       } 
/*  71 */       writer.writeComment(comment, output);
/*  72 */       writer.writeIndent(output);
/*  73 */       writer.writeKey(key, output);
/*  74 */       output.write(KEY_VALUE_SEPARATOR);
/*  75 */       ValueWriter.write(value, output, writer);
/*  76 */       writer.writeNewline(output);
/*     */     } 
/*     */ 
/*     */     
/*  80 */     int nonSimpleValuesCount = tablesEntries.size() + tableArraysEntries.size();
/*  81 */     int simpleValuesCount = config.size() - nonSimpleValuesCount;
/*  82 */     if (simpleValuesCount > 0 && nonSimpleValuesCount > 0) {
/*  83 */       writer.writeNewline(output);
/*     */     }
/*     */ 
/*     */     
/*  87 */     for (UnmodifiableCommentedConfig.Entry entry : tablesEntries) {
/*     */       
/*  89 */       writer.writeComment(entry.getComment(), output);
/*     */ 
/*     */       
/*  92 */       configPath.add(entry.getKey());
/*  93 */       writeTableName(configPath, output, writer);
/*  94 */       writer.writeNewline(output);
/*     */ 
/*     */       
/*  97 */       writeNormal((UnmodifiableConfig)entry.getValue(), configPath, output, writer);
/*  98 */       configPath.remove(configPath.size() - 1);
/*     */     } 
/*     */ 
/*     */     
/* 102 */     for (UnmodifiableCommentedConfig.Entry entry : tableArraysEntries) {
/*     */       
/* 104 */       writer.writeComment(entry.getComment(), output);
/*     */ 
/*     */       
/* 107 */       configPath.add(entry.getKey());
/* 108 */       List<Config> tableArray = (List<Config>)entry.getValue();
/* 109 */       for (UnmodifiableConfig table : tableArray) {
/* 110 */         writeTableArrayName(configPath, output, writer);
/* 111 */         writer.writeNewline(output);
/* 112 */         writeNormal(table, configPath, output, writer);
/*     */       } 
/* 114 */       configPath.remove(configPath.size() - 1);
/*     */     } 
/* 116 */     writer.decreaseIndentLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeTableArrayName(List<String> name, CharacterOutput output, TomlWriter writer) {
/* 121 */     writeTableName(name, output, writer, ARRAY_OF_TABLES_NAME_BEGIN, ARRAY_OF_TABLES_NAME_END);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeTableName(List<String> name, CharacterOutput output, TomlWriter writer) {
/* 126 */     writeTableName(name, output, writer, TABLE_NAME_BEGIN, TABLE_NAME_END);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeTableName(List<String> name, CharacterOutput output, TomlWriter writer, char[] begin, char[] end) {
/* 131 */     if (name.isEmpty()) {
/* 132 */       throw new WritingException("Invalid empty table name.");
/*     */     }
/* 134 */     writer.writeIndent(output);
/* 135 */     output.write(begin);
/* 136 */     Iterator<String> it = name.iterator();
/* 137 */     writer.writeKey(it.next(), output);
/* 138 */     while (it.hasNext()) {
/* 139 */       output.write('.');
/* 140 */       writer.writeKey(it.next(), output);
/*     */     } 
/* 142 */     output.write(end);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TableWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */