/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2BooleanRBTreeMap;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.custom.CustomModule;
/*    */ import thelm.jaopca.materials.Material;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ public class MaterialPredicateDeserializer
/*    */   implements JsonDeserializer<Predicate<IMaterial>>
/*    */ {
/* 23 */   public static final MaterialPredicateDeserializer INSTANCE = new MaterialPredicateDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate<IMaterial> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 29 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 30 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "object");
/* 31 */     boolean defaultValue = jsonHelper.getBoolean(json, "default");
/* 32 */     Object2BooleanRBTreeMap object2BooleanRBTreeMap = new Object2BooleanRBTreeMap();
/* 33 */     object2BooleanRBTreeMap.defaultReturnValue(defaultValue);
/* 34 */     if (json.has("materialTypes")) {
/* 35 */       JsonObject materialTypesJson = jsonHelper.getJsonObject(json, "materialTypes");
/* 36 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialTypesJson.entrySet()) {
/* 37 */         boolean materialTypeValue = jsonHelper.getBoolean(entry.getValue(), "element");
/* 38 */         switch ((String)entry.getKey()) {
/*    */           case "ingot":
/* 40 */             MaterialHandler.getMaterials().stream()
/* 41 */               .filter(m -> m.getType().isIngot())
/* 42 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           
/*    */           case "gem":
/* 45 */             MaterialHandler.getMaterials().stream()
/* 46 */               .filter(m -> m.getType().isGem())
/* 47 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           
/*    */           case "crystal":
/* 50 */             MaterialHandler.getMaterials().stream()
/* 51 */               .filter(m -> m.getType().isCrystal())
/* 52 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */           
/*    */           case "dust":
/* 55 */             MaterialHandler.getMaterials().stream()
/* 56 */               .filter(m -> m.getType().isDust())
/* 57 */               .forEach(m -> map.put(m, materialTypeValue));
/*    */         } 
/*    */       
/*    */       } 
/*    */     } 
/* 62 */     if (json.has("materials")) {
/* 63 */       JsonObject materialsJson = jsonHelper.getJsonObject(json, "materials");
/* 64 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialsJson.entrySet()) {
/* 65 */         if (MaterialHandler.containsMaterial(entry.getKey())) {
/* 66 */           object2BooleanRBTreeMap.put(MaterialHandler.getMaterial(entry.getKey()), jsonHelper.getBoolean(entry.getValue(), "element"));
/*    */         }
/*    */       } 
/*    */     } 
/* 70 */     if (json.has("config") && 
/* 71 */       jsonHelper.getBoolean(json, "config")) {
/* 72 */       String comment, path = jsonHelper.getString(json, "path");
/*    */       
/* 74 */       if (json.has("comment")) {
/* 75 */         comment = jsonHelper.getString(json, "comment");
/*    */       } else {
/*    */         
/* 78 */         comment = "";
/*    */       } 
/* 80 */       CustomModule.instance.addCustomConfigDefiner((material, config) -> map.put(material, config.getDefinedBoolean(path, map.getBoolean(material), comment)));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 85 */     return material -> map.getBoolean(material);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\MaterialPredicateDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */