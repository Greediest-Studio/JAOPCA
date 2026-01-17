/*     */ package thelm.com.electronwill.nightconfig.core.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*     */ import thelm.com.electronwill.nightconfig.core.io.ParsingMode;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WritingException;
/*     */ import thelm.com.electronwill.nightconfig.core.io.WritingMode;
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
/*     */ public abstract class GenericBuilder<Base extends Config, Result extends FileConfig>
/*     */ {
/*     */   protected final Path file;
/*     */   private Base config;
/*     */   protected final ConfigFormat<? extends Base> format;
/*     */   protected final ConfigWriter writer;
/*     */   protected final ConfigParser<? extends Base> parser;
/*  41 */   protected Charset charset = StandardCharsets.UTF_8;
/*  42 */   protected WritingMode writingMode = WritingMode.REPLACE;
/*  43 */   protected ParsingMode parsingMode = ParsingMode.REPLACE;
/*  44 */   protected FileNotFoundAction nefAction = FileNotFoundAction.CREATE_EMPTY;
/*     */   protected boolean sync = false, autosave = false, autoreload = false, concurrent = false;
/*  46 */   protected boolean insertionOrder = Config.isInsertionOrderPreserved();
/*  47 */   protected Supplier<Map<String, Object>> mapCreator = null;
/*     */   
/*     */   GenericBuilder(Path file, ConfigFormat<? extends Base> format) {
/*  50 */     this.file = file;
/*  51 */     this.format = format;
/*  52 */     this.writer = format.createWriter();
/*  53 */     this.parser = format.createParser();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> charset(Charset charset) {
/*  62 */     this.charset = charset;
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> writingMode(WritingMode writingMode) {
/*  72 */     this.writingMode = writingMode;
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> parsingMode(ParsingMode parsingMode) {
/*  82 */     this.parsingMode = parsingMode;
/*  83 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> onFileNotFound(FileNotFoundAction nefAction) {
/*  92 */     this.nefAction = nefAction;
/*  93 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> defaultResource(String resourcePath) {
/* 104 */     return onFileNotFound(FileNotFoundAction.copyResource(resourcePath));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> defaultData(File file) {
/* 115 */     return onFileNotFound(FileNotFoundAction.copyData(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> defaultData(Path file) {
/* 126 */     return onFileNotFound(FileNotFoundAction.copyData(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> defaultData(URL url) {
/* 137 */     return onFileNotFound(FileNotFoundAction.copyData(url));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> sync() {
/* 147 */     this.sync = true;
/* 148 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> autosave() {
/* 158 */     this.autosave = true;
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> autoreload() {
/* 169 */     this.autoreload = true;
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> concurrent() {
/* 179 */     if (this.config == null) {
/* 180 */       this.config = (Base)this.format.createConcurrentConfig();
/*     */     }
/* 182 */     this.concurrent = true;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenericBuilder<Base, Result> preserveInsertionOrder() {
/* 192 */     this.insertionOrder = true;
/* 193 */     return this;
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
/*     */   public GenericBuilder<Base, Result> backingMapCreator(Supplier<Map<String, Object>> s) {
/* 208 */     this.mapCreator = s;
/* 209 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result build() {
/*     */     FileConfig<Base> fileConfig;
/* 219 */     if (this.sync) {
/* 220 */       fileConfig = new WriteSyncFileConfig<>(getConfig(), this.file, this.charset, this.writer, this.writingMode, this.parser, this.parsingMode, this.nefAction);
/*     */     } else {
/*     */       
/* 223 */       if (this.autoreload) {
/* 224 */         concurrent();
/*     */       }
/*     */ 
/*     */       
/* 228 */       fileConfig = new WriteAsyncFileConfig<>(getConfig(), this.file, this.charset, this.writer, this.writingMode, this.parser, this.parsingMode, this.nefAction);
/*     */     } 
/*     */     
/* 231 */     if (this.autoreload) {
/* 232 */       if (Files.notExists(this.file, new java.nio.file.LinkOption[0])) {
/*     */         try {
/* 234 */           this.nefAction.run(this.file, this.format);
/* 235 */         } catch (IOException e) {
/* 236 */           throw new WritingException("An exception occured while executing the FileNotFoundAction for file " + this.file, e);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 242 */       fileConfig = new AutoreloadFileConfig<>(fileConfig);
/*     */     } 
/* 244 */     if (this.autosave) {
/* 245 */       return buildAutosave(fileConfig);
/*     */     }
/* 247 */     return buildNormal(fileConfig);
/*     */   }
/*     */   
/*     */   protected abstract Result buildAutosave(FileConfig paramFileConfig);
/*     */   
/*     */   protected abstract Result buildNormal(FileConfig paramFileConfig);
/*     */   
/*     */   protected final Base getConfig() {
/* 255 */     if (this.config == null) {
/* 256 */       if (this.mapCreator == null) {
/* 257 */         this.mapCreator = Config.getDefaultMapCreator(this.concurrent, this.insertionOrder);
/*     */       }
/* 259 */       this.config = (Base)this.format.createConfig(this.mapCreator);
/*     */     } 
/* 261 */     return this.config;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\GenericBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */