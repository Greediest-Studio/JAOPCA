/*     */ package thelm.com.electronwill.nightconfig.core.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import thelm.com.electronwill.nightconfig.core.Config;
/*     */ import thelm.com.electronwill.nightconfig.core.ConfigFormat;
/*     */ import thelm.com.electronwill.nightconfig.core.file.FileNotFoundAction;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.FastStringReader;
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
/*     */ 
/*     */ public interface ConfigParser<C extends Config>
/*     */ {
/*     */   ConfigFormat<C> getFormat();
/*     */   
/*     */   C parse(Reader paramReader);
/*     */   
/*     */   void parse(Reader paramReader, Config paramConfig, ParsingMode paramParsingMode);
/*     */   
/*     */   default C parse(String input) {
/*  55 */     return parse((Reader)new FastStringReader(input));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void parse(String input, Config destination, ParsingMode parsingMode) {
/*  66 */     parse(new StringReader(input), destination, parsingMode);
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
/*     */   default C parse(InputStream input) {
/*  78 */     return parse(input, StandardCharsets.UTF_8);
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
/*     */   default C parse(InputStream input, Charset charset) {
/*  90 */     return parse(new BufferedReader(new InputStreamReader(input, charset)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void parse(InputStream input, Config destination, ParsingMode parsingMode) {
/* 101 */     parse(input, destination, parsingMode, StandardCharsets.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void parse(InputStream input, Config destination, ParsingMode parsingMode, Charset charset) {
/* 112 */     Reader reader = new BufferedReader(new InputStreamReader(input, charset));
/* 113 */     parse(reader, destination, parsingMode);
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
/*     */   default C parse(File file, FileNotFoundAction nefAction) {
/* 125 */     return parse(file, nefAction, StandardCharsets.UTF_8);
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
/*     */   default C parse(File file, FileNotFoundAction nefAction, Charset charset) {
/* 137 */     return parse(file.toPath(), nefAction, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void parse(File file, Config destination, ParsingMode parsingMode, FileNotFoundAction nefAction) {
/* 148 */     parse(file, destination, parsingMode, nefAction, StandardCharsets.UTF_8);
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
/*     */   default void parse(File file, Config destination, ParsingMode parsingMode, FileNotFoundAction nefAction, Charset charset) {
/* 160 */     parse(file.toPath(), destination, parsingMode, nefAction, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default C parse(Path file, FileNotFoundAction nefAction) {
/* 171 */     return parse(file, nefAction, StandardCharsets.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default C parse(Path file, FileNotFoundAction nefAction, Charset charset) {
/*     */     
/* 183 */     try { if (Files.notExists(file, new java.nio.file.LinkOption[0]) && !nefAction.run(file, getFormat())) {
/* 184 */         return (C)getFormat().createConfig();
/*     */       }
/* 186 */       InputStream input = Files.newInputStream(file, new java.nio.file.OpenOption[0]); 
/* 187 */       try { C c = parse(input, charset);
/* 188 */         if (input != null) input.close();  return c; } catch (Throwable throwable) { if (input != null)
/* 189 */           try { input.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 190 */     { throw new WritingException("An I/O error occured", e); }
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
/*     */   default void parse(Path file, Config destination, ParsingMode parsingMode, FileNotFoundAction nefAction) {
/* 202 */     parse(file, destination, parsingMode, nefAction, StandardCharsets.UTF_8);
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
/*     */   default void parse(Path file, Config destination, ParsingMode parsingMode, FileNotFoundAction nefAction, Charset charset) {
/*     */     
/* 215 */     try { if (Files.notExists(file, new java.nio.file.LinkOption[0]) && !nefAction.run(file, getFormat())) {
/*     */         return;
/*     */       }
/* 218 */       InputStream input = Files.newInputStream(file, new java.nio.file.OpenOption[0]); 
/* 219 */       try { parse(input, destination, parsingMode, charset);
/* 220 */         if (input != null) input.close();  } catch (Throwable throwable) { if (input != null)
/* 221 */           try { input.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 222 */     { throw new WritingException("An I/O error occured", e); }
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
/*     */   default C parse(URL url) {
/*     */     URLConnection connection;
/*     */     try {
/* 237 */       connection = url.openConnection();
/* 238 */     } catch (IOException e) {
/* 239 */       throw new WritingException("Unable to connect to the URL", e);
/*     */     } 
/* 241 */     String encoding = connection.getContentEncoding();
/* 242 */     Charset charset = (encoding == null) ? StandardCharsets.UTF_8 : Charset.forName(encoding); 
/* 243 */     try { Reader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset)); 
/* 244 */       try { C c = parse(reader);
/* 245 */         reader.close(); return c; } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 246 */     { throw new WritingException("An I/O error occured", e); }
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
/*     */   default void parse(URL url, Config destination, ParsingMode parsingMode) {
/*     */     URLConnection connection;
/*     */     try {
/* 260 */       connection = url.openConnection();
/* 261 */     } catch (IOException e) {
/* 262 */       throw new WritingException("Unable to connect to the URL", e);
/*     */     } 
/* 264 */     String encoding = connection.getContentEncoding();
/* 265 */     Charset charset = (encoding == null) ? StandardCharsets.UTF_8 : Charset.forName(encoding); 
/* 266 */     try { Reader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset)); 
/* 267 */       try { parse(reader, destination, parsingMode);
/* 268 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 269 */     { throw new WritingException("An I/O error occured", e); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\ConfigParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */