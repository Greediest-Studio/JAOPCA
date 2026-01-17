/*     */ package thelm.com.electronwill.nightconfig.core.io;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import thelm.com.electronwill.nightconfig.core.UnmodifiableConfig;
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
/*     */ public interface ConfigWriter
/*     */ {
/*     */   void write(UnmodifiableConfig paramUnmodifiableConfig, Writer paramWriter);
/*     */   
/*     */   default void write(UnmodifiableConfig config, OutputStream output, Charset charset) {
/*  41 */     Writer writer = new BufferedWriter(new OutputStreamWriter(output, charset));
/*  42 */     write(config, writer);
/*     */     try {
/*  44 */       writer.flush();
/*  45 */     } catch (IOException e) {
/*  46 */       throw new WritingException("Failed to flush the writer", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void write(UnmodifiableConfig config, OutputStream output) {
/*  58 */     write(config, output, StandardCharsets.UTF_8);
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
/*     */   default void write(UnmodifiableConfig config, Path file, WritingMode writingMode) {
/*  70 */     write(config, file, writingMode, StandardCharsets.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void write(UnmodifiableConfig config, Path file, WritingMode writingMode, Charset charset) {
/*     */     StandardOpenOption[] options;
/*  82 */     if (writingMode == WritingMode.APPEND) {
/*  83 */       options = new StandardOpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND };
/*     */     } else {
/*  85 */       options = new StandardOpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING };
/*     */     }  
/*  87 */     try { OutputStream output = Files.newOutputStream(file, (OpenOption[])options); 
/*  88 */       try { write(config, output, charset);
/*  89 */         if (output != null) output.close();  } catch (Throwable throwable) { if (output != null) try { output.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/*  90 */     { throw new WritingException("An I/O error occured", e); }
/*     */   
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
/*     */   default void write(UnmodifiableConfig config, File file, WritingMode writingMode) {
/* 103 */     write(config, file, writingMode, StandardCharsets.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void write(UnmodifiableConfig config, File file, WritingMode writingMode, Charset charset) {
/* 114 */     write(config, file.toPath(), writingMode, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void write(UnmodifiableConfig config, URL url) {
/*     */     URLConnection connection;
/*     */     try {
/* 127 */       connection = url.openConnection();
/* 128 */     } catch (IOException e) {
/* 129 */       throw new WritingException("Unable to connect to the URL", e);
/*     */     } 
/* 131 */     String encoding = connection.getContentEncoding();
/* 132 */     Charset charset = (encoding == null) ? StandardCharsets.UTF_8 : Charset.forName(encoding); 
/* 133 */     try { OutputStream output = connection.getOutputStream(); 
/* 134 */       try { write(config, output, charset);
/* 135 */         if (output != null) output.close();  } catch (Throwable throwable) { if (output != null) try { output.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 136 */     { throw new WritingException("An I/O error occured", e); }
/*     */   
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
/*     */   default String writeToString(UnmodifiableConfig config) {
/* 149 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(64);
/* 150 */     write(config, builder);
/* 151 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ConfigWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */