/*    */ package thelm.com.electronwill.nightconfig.core.file;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Objects;
/*    */ import thelm.com.electronwill.nightconfig.core.Config;
/*    */ import thelm.com.electronwill.nightconfig.core.utils.ConfigWrapper;
/*    */ 
/*    */ final class AutoreloadFileConfig<C extends FileConfig>
/*    */   extends ConfigWrapper<C>
/*    */   implements FileConfig {
/* 13 */   private final FileWatcher watcher = FileWatcher.defaultInstance();
/*    */   
/*    */   AutoreloadFileConfig(C config) {
/* 16 */     super((Config)config);
/*    */     try {
/* 18 */       Objects.requireNonNull(config); this.watcher.addWatch(config.getFile(), config::load);
/* 19 */     } catch (IOException e) {
/* 20 */       throw new RuntimeException("Unable to create the autoreloaded config", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 26 */     return ((FileConfig)this.config).getFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Path getNioPath() {
/* 31 */     return ((FileConfig)this.config).getNioPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 36 */     ((FileConfig)this.config).save();
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() {
/* 41 */     ((FileConfig)this.config).load();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 46 */     this.watcher.removeWatch(((FileConfig)this.config).getFile());
/* 47 */     ((FileConfig)this.config).close();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\file\AutoreloadFileConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */