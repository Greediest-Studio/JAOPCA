/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.WriterSupplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ConfigFormat<C extends Config>
/*     */ {
/*     */   default C createConfig() {
/*  35 */     return createConfig(Config.getDefaultMapCreator(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default C createConcurrentConfig() {
/*  44 */     return createConfig(Config.getDefaultMapCreator(true));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean supportsType(Class<?> type) {
/*  72 */     return InMemoryFormat.DEFAULT_PREDICATE.test(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isInMemory() {
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void initEmptyFile(Path f) throws IOException {
/*  91 */     initEmptyFile(() -> Files.newBufferedWriter(f, new java.nio.file.OpenOption[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void initEmptyFile(File f) throws IOException {
/* 101 */     initEmptyFile(f.toPath());
/*     */   }
/*     */   
/*     */   default void initEmptyFile(WriterSupplier ws) throws IOException {}
/*     */   
/*     */   ConfigWriter createWriter();
/*     */   
/*     */   ConfigParser<C> createParser();
/*     */   
/*     */   C createConfig(Supplier<Map<String, Object>> paramSupplier);
/*     */   
/*     */   boolean supportsComments();
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\ConfigFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */