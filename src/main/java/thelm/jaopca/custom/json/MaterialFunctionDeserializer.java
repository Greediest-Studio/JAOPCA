/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2ObjectRBTreeMap;
/*    */ import java.lang.reflect.ParameterizedType;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.materials.Material;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterialFunctionDeserializer
/*    */   implements JsonDeserializer<Function<IMaterial, ?>>
/*    */ {
/* 26 */   private static final Logger LOGGER = LogManager.getLogger();
/* 27 */   public static final MaterialFunctionDeserializer INSTANCE = new MaterialFunctionDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Function<IMaterial, ?> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 33 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 34 */     Type[] typeArguments = ((ParameterizedType)typeOfT).getActualTypeArguments();
/* 35 */     Type parameterizedType = typeArguments[1];
/* 36 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "object");
/* 37 */     Object defaultValue = jsonHelper.deserializeType(json, "default", context, parameterizedType);
/* 38 */     if (defaultValue == null) {
/* 39 */       LOGGER.warn("Null default value: {}", jsonHelper.toSimpleString(json.get("default")));
/*    */     }
/* 41 */     Object2ObjectRBTreeMap object2ObjectRBTreeMap = new Object2ObjectRBTreeMap();
/* 42 */     object2ObjectRBTreeMap.defaultReturnValue(defaultValue);
/* 43 */     if (json.has("materialTypes")) {
/* 44 */       JsonObject materialTypesJson = jsonHelper.getJsonObject(json, "materialTypes");
/* 45 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialTypesJson.entrySet()) {
/* 46 */         Object materialTypeValue = jsonHelper.deserializeType(entry.getValue(), "element", context, parameterizedType);
/* 47 */         if (materialTypeValue == null) {
/* 48 */           LOGGER.warn("Null value for material type {}: {}", entry.getKey(), jsonHelper.toSimpleString(entry.getValue()));
/*    */         }
/* 50 */         switch ((String)entry.getKey()) {
/*    */           case "ingot":
/* 52 */             MaterialHandler.getMaterials().stream()
/* 53 */               .filter(m -> m.getType().isIngot())
/* 54 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           case "gem":
/* 56 */             MaterialHandler.getMaterials().stream()
/* 57 */               .filter(m -> m.getType().isGem())
/* 58 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           case "crystal":
/* 60 */             MaterialHandler.getMaterials().stream()
/* 61 */               .filter(m -> m.getType().isCrystal())
/* 62 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           case "dust":
/* 64 */             MaterialHandler.getMaterials().stream()
/* 65 */               .filter(m -> m.getType().isDust())
/* 66 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */         } 
/*    */       } 
/*    */     } 
/* 70 */     if (json.has("materials")) {
/* 71 */       JsonObject materialsJson = jsonHelper.getJsonObject(json, "materials");
/* 72 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialsJson.entrySet()) {
/* 73 */         if (MaterialHandler.containsMaterial(entry.getKey())) {
/* 74 */           Object materialValue = jsonHelper.deserializeType(entry.getValue(), "element", context, parameterizedType);
/* 75 */           if (materialValue == null) {
/* 76 */             LOGGER.warn("Null value for material {}: {}", entry.getKey(), jsonHelper.toSimpleString(entry.getValue()));
/*    */           }
/* 78 */           object2ObjectRBTreeMap.put(MaterialHandler.getMaterial(entry.getKey()), materialValue);
/*    */         } 
/*    */       } 
/*    */     } 
/* 82 */     return material -> map.get(material);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\MaterialFunctionDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */