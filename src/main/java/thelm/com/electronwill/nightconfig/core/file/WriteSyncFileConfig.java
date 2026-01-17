/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.file.Path;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigParser;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ConfigWriter;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ParsingMode;
/*    */ import thelm.com.electronwill.nightconfig.core.io.WritingMode;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class WriteSyncFileConfig<C extends Config>
/*    */   extends ConfigWrapper<C>
/*    */   implements FileConfig
/*    */ {
/*    */   private final Path nioPath;
/*    */   private final Charset charset;
/*    */   private boolean closed;
/*    */   private final ConfigWriter writer;
/*    */   private final WritingMode writingMode;
/*    */   private final ConfigParser<?> parser;
/*    */   private final FileNotFoundAction nefAction;
/*    */   private final ParsingMode parsingMode;
/*    */   private volatile boolean currentlyWriting = false;
/*    */   
/*    */   WriteSyncFileConfig(C config, Path nioPath, Charset charset, ConfigWriter writer, WritingMode writingMode, ConfigParser<?> parser, ParsingMode parsingMode, FileNotFoundAction nefAction) {
/* 34 */     super((Config)config);
/* 35 */     this.nioPath = nioPath;
/* 36 */     this.charset = charset;
/* 37 */     this.writer = writer;
/* 38 */     this.parser = parser;
/* 39 */     this.parsingMode = parsingMode;
/* 40 */     this.nefAction = nefAction;
/* 41 */     this.writingMode = writingMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 46 */     return this.nioPath.toFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 51 */     return this.nioPath;
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 56 */     synchronized (this) {
/* 57 */       if (this.closed) {
/* 58 */         throw new IllegalStateException("Cannot save a closed FileConfig");
/*    */       }
/* 60 */       this.currentlyWriting = true;
/* 61 */       this.writer.write(this.config, this.nioPath, this.writingMode, this.charset);
/* 62 */       this.currentlyWriting = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 68 */     if (!this.currentlyWriting) {
/* 69 */       synchronized (this) {
/* 70 */         if (this.closed) {
/* 71 */           throw new IllegalStateException("Cannot (re)load a closed FileConfig");
/*    */         }
/* 73 */         this.parser.parse(this.nioPath, (Config)this.config, this.parsingMode, this.nefAction);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 80 */     this.closed = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\WriteSyncFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */