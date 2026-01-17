/*     */ package thelm.jaopca.custom.json;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectRBTreeMap;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.custom.CustomModule;
/*     */ import thelm.jaopca.materials.Material;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.utils.JsonHelper;
/*     */ 
/*     */ 
/*     */ public class MaterialMappedFunctionDeserializer<T>
/*     */   implements JsonDeserializer<Function<IMaterial, T>>
/*     */ {
/*  28 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final Function<String, T> stringToValue;
/*     */   private final Function<T, String> valueToString;
/*     */   
/*     */   public MaterialMappedFunctionDeserializer(Function<String, T> stringToValue, Function<T, String> valueToString) {
/*  34 */     this.stringToValue = Objects.<Function<String, T>>requireNonNull(stringToValue);
/*  35 */     this.valueToString = Objects.<Function<T, String>>requireNonNull(valueToString);
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, T> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  40 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/*  41 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "object");
/*  42 */     String defaultString = jsonHelper.getString(json, "default");
/*  43 */     T defaultValue = this.stringToValue.apply(defaultString);
/*  44 */     if (defaultValue == null) {
/*  45 */       LOGGER.warn("Null default value: {}", defaultString);
/*     */     }
/*  47 */     Object2ObjectRBTreeMap object2ObjectRBTreeMap = new Object2ObjectRBTreeMap();
/*  48 */     object2ObjectRBTreeMap.defaultReturnValue(defaultValue);
/*  49 */     if (json.has("materialTypes")) {
/*  50 */       JsonObject materialTypesJson = jsonHelper.getJsonObject(json, "materialTypes");
/*  51 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialTypesJson.entrySet()) {
/*  52 */         String materialTypeString = jsonHelper.getString(entry.getValue(), "element");
/*  53 */         T materialTypeValue = this.stringToValue.apply(materialTypeString.toLowerCase(Locale.US));
/*  54 */         if (materialTypeValue == null) {
/*  55 */           LOGGER.warn("Null value for material type {}: {}", entry.getKey(), materialTypeString);
/*     */         }
/*  57 */         switch ((String)entry.getKey()) {
/*     */           case "ingot":
/*  59 */             MaterialHandler.getMaterials().stream()
/*  60 */               .filter(m -> m.getType().isIngot())
/*  61 */               .forEach(m -> map.put(m, materialTypeValue));
/*     */           
/*     */           case "gem":
/*  64 */             MaterialHandler.getMaterials().stream()
/*  65 */               .filter(m -> m.getType().isGem())
/*  66 */               .forEach(m -> map.put(m, materialTypeValue));
/*     */           
/*     */           case "crystal":
/*  69 */             MaterialHandler.getMaterials().stream()
/*  70 */               .filter(m -> m.getType().isCrystal())
/*  71 */               .forEach(m -> map.put(m, materialTypeValue));
/*     */           
/*     */           case "dust":
/*  74 */             MaterialHandler.getMaterials().stream()
/*  75 */               .filter(m -> m.getType().isDust())
/*  76 */               .forEach(m -> map.put(m, materialTypeValue));
/*     */         } 
/*     */       
/*     */       } 
/*     */     } 
/*  81 */     if (json.has("materials")) {
/*  82 */       JsonObject materialsJson = jsonHelper.getJsonObject(json, "materials");
/*  83 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialsJson.entrySet()) {
/*  84 */         if (MaterialHandler.containsMaterial(entry.getKey())) {
/*  85 */           String materialString = jsonHelper.getString(entry.getValue(), "element");
/*  86 */           T materialValue = this.stringToValue.apply(materialString);
/*  87 */           if (materialValue == null) {
/*  88 */             LOGGER.warn("Null value for material {}: {}", entry.getKey(), materialString);
/*     */           }
/*  90 */           object2ObjectRBTreeMap.put(MaterialHandler.getMaterial(entry.getKey()), materialValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*  94 */     if (json.has("config") && 
/*  95 */       jsonHelper.getBoolean(json, "config")) {
/*  96 */       String comment, path = jsonHelper.getString(json, "path");
/*     */       
/*  98 */       if (json.has("comment")) {
/*  99 */         comment = jsonHelper.getString(json, "comment");
/*     */       } else {
/*     */         
/* 102 */         comment = "";
/*     */       } 
/* 104 */       CustomModule.instance.addCustomConfigDefiner((material, config) -> {
/*     */             T value = this.stringToValue.apply(config.getDefinedString(path, "" + (String)this.valueToString.apply((T)map.get(material)), comment));
/*     */             
/*     */             if (value == null) {
/*     */               LOGGER.warn("Null config value for material {}", material.getName());
/*     */             }
/*     */             map.put(material, value);
/*     */           });
/*     */     } 
/* 113 */     return material -> map.get(material);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\MaterialMappedFunctionDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */