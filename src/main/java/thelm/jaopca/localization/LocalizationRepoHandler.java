/*     */ package thelm.jaopca.localization;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSortedMap;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.utils.JsonHelper;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalizationRepoHandler
/*     */ {
/*  31 */   private static final Logger LOGGER = LogManager.getLogger();
/*  32 */   private static final int MAX_HTTP_REDIRECTS = Integer.getInteger("http.maxRedirects", 20).intValue();
/*     */   
/*     */   private static Path configDir;
/*     */   private static Path langDir;
/*  36 */   private static Map<String, String> currentLocalizationMap = (Map<String, String>)ImmutableSortedMap.of();
/*     */   
/*     */   public static void setup(Path modConfigDir) {
/*  39 */     configDir = modConfigDir.resolve("jaopca");
/*  40 */     langDir = configDir.resolve("lang");
/*  41 */     if (!Files.exists(langDir, new java.nio.file.LinkOption[0]) || !Files.isDirectory(langDir, new java.nio.file.LinkOption[0])) {
/*     */       try {
/*  43 */         if (Files.exists(langDir, new java.nio.file.LinkOption[0]) && !Files.isDirectory(langDir, new java.nio.file.LinkOption[0])) {
/*  44 */           LOGGER.warn("Directory {} is a file, deleting", langDir);
/*  45 */           Files.delete(langDir);
/*     */         } 
/*  47 */         Files.createDirectory(langDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/*  49 */       catch (Exception e) {
/*  50 */         throw new RuntimeException("Could not create directory " + langDir + ", please create manually", e);
/*     */       } 
/*     */     }
/*  53 */     reload();
/*     */   }
/*     */   
/*     */   public static void reload() {
/*  57 */     MiscHelper.INSTANCE.submitAsyncTask(() -> {
/*     */           JsonHelper jsonHelper = JsonHelper.INSTANCE;
/*     */           
/*     */           String language = LocalizationHandler.getLanguage();
/*     */           Path langFile = langDir.resolve(language + ".json");
/*     */           if (ConfigHandler.checkL10nUpdates) {
/*     */             try {
/*     */               if (!Files.exists(langFile, new java.nio.file.LinkOption[0]) || (System.currentTimeMillis() - Files.getLastModifiedTime(langFile, new java.nio.file.LinkOption[0]).toMillis()) > ConfigHandler.updateInterval * 24.0D * 3600.0D * 1000.0D) {
/*     */                 LOGGER.info("Downloading localization file for language {}", language);
/*     */                 URL url = new URL("https://raw.githubusercontent.com/TheLMiffy1111/JAOPCAMaterialLocalizations/v2/lang/" + language + ".json");
/*     */                 InputStream con = openUrlStream(url);
/*     */                 Files.copy(con, langFile, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */                 LOGGER.info("Downloaded localization file for language {}", language);
/*     */               } 
/*  71 */             } catch (Exception e) {
/*     */               LOGGER.info("Unable to download localization file for language {}", language, e);
/*     */               
/*     */               currentLocalizationMap = (Map<String, String>)ImmutableSortedMap.of();
/*     */             } 
/*     */           }
/*     */           
/*     */           if (Files.exists(langFile, new java.nio.file.LinkOption[0])) {
/*     */             try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(langFile, new java.nio.file.OpenOption[0]), StandardCharsets.UTF_8)) {
/*     */               LOGGER.info("Reading localization file", language);
/*     */               
/*     */               JsonElement jsonElement = (new JsonParser()).parse(reader);
/*     */               
/*     */               JsonObject json = jsonHelper.getJsonObject(jsonElement, "file");
/*     */               ImmutableSortedMap.Builder<String, String> builder = ImmutableSortedMap.naturalOrder();
/*     */               for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)json.entrySet()) {
/*     */                 if (((JsonElement)entry.getValue()).isJsonPrimitive()) {
/*     */                   String string = jsonHelper.getString(entry.getValue(), entry.getKey());
/*     */                   if (!string.isEmpty()) {
/*     */                     builder.put(entry.getKey(), string);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */               currentLocalizationMap = (Map<String, String>)builder.build();
/*     */               LOGGER.info("Finished reading localization file", language);
/*  96 */             } catch (Exception e) {
/*     */               LOGGER.info("Unable to read localization file", e);
/*     */               currentLocalizationMap = (Map<String, String>)ImmutableSortedMap.of();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static Map<String, String> getCurrentLocalizationMap() {
/* 105 */     return currentLocalizationMap;
/*     */   }
/*     */   
/*     */   private static InputStream openUrlStream(URL url) throws IOException {
/* 109 */     URL currentUrl = url;
/* 110 */     for (int redirects = 0; redirects < MAX_HTTP_REDIRECTS; redirects++) {
/* 111 */       URLConnection c = currentUrl.openConnection();
/* 112 */       if (c instanceof HttpURLConnection)
/* 113 */       { HttpURLConnection huc = (HttpURLConnection)c;
/* 114 */         huc.setInstanceFollowRedirects(false);
/* 115 */         int responseCode = huc.getResponseCode();
/* 116 */         if (responseCode >= 300 && responseCode <= 399)
/*     */         { 
/* 118 */           try { String loc = huc.getHeaderField("Location");
/* 119 */             currentUrl = new URL(currentUrl, loc);
/*     */ 
/*     */ 
/*     */             
/* 123 */             huc.disconnect(); } finally { huc.disconnect(); }
/*     */            }
/*     */         else
/*     */         
/* 127 */         { return c.getInputStream(); }  } else { return c.getInputStream(); }
/*     */     
/* 129 */     }  throw new IOException("Too many redirects while trying to fetch " + url);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\localization\LocalizationRepoHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */