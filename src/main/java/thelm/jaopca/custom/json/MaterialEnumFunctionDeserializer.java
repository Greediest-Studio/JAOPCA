/*     */ package thelm.jaopca.custom.json;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectRBTreeMap;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.function.Function;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.custom.CustomModule;
/*     */ import thelm.jaopca.materials.Material;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.utils.JsonHelper;
/*     */ 
/*     */ public class MaterialEnumFunctionDeserializer
/*     */   implements JsonDeserializer<Function<IMaterial, Enum<?>>>
/*     */ {
/*  27 */   public static final MaterialEnumFunctionDeserializer INSTANCE = new MaterialEnumFunctionDeserializer();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Function<IMaterial, Enum<?>> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  33 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/*  34 */     Type[] typeArguments = ((ParameterizedType)typeOfT).getActualTypeArguments();
/*  35 */     Type parameterizedType = typeArguments[1];
/*  36 */     if (parameterizedType instanceof Class && ((Class)parameterizedType).isEnum()) {
/*  37 */       Map<String, Enum<?>> stringToEnum = new TreeMap<>();
/*  38 */       Map<Enum<?>, String> enumToString = new TreeMap<>();
/*  39 */       Enum[] arrayOfEnum = ((Class<Enum>)parameterizedType).getEnumConstants();
/*  40 */       for (Enum<?> value : (Enum[])((Class<Enum>)parameterizedType).getEnumConstants()) {
/*  41 */         stringToEnum.put(value.name().toLowerCase(Locale.US), value);
/*  42 */         enumToString.put(value, value.name());
/*     */       } 
/*  44 */       JsonObject json = jsonHelper.getJsonObject(jsonElement, "object");
/*  45 */       Enum<?> defaultValue = null;
/*  46 */       if (jsonHelper.isString(json, "default")) {
/*  47 */         String defaultString = jsonHelper.getString(json, "default");
/*  48 */         Enum<?> value = stringToEnum.get(defaultString.toLowerCase(Locale.US));
/*  49 */         if (value == null) {
/*  50 */           throw new JsonSyntaxException("Invalid enum " + defaultString);
/*     */         }
/*  52 */         defaultValue = value;
/*     */       }
/*  54 */       else if (jsonHelper.isNumber(json, "default")) {
/*  55 */         int defaultOrdinal = jsonHelper.getInt(json, "default");
/*  56 */         if (defaultOrdinal >= arrayOfEnum.length) {
/*  57 */           throw new JsonSyntaxException("Invalid enum ordinal " + defaultOrdinal);
/*     */         }
/*  59 */         defaultValue = arrayOfEnum[defaultOrdinal];
/*     */       } else {
/*     */         
/*  62 */         throw new JsonSyntaxException("Unable to deserialize enum");
/*     */       } 
/*  64 */       Object2ObjectRBTreeMap object2ObjectRBTreeMap = new Object2ObjectRBTreeMap();
/*  65 */       object2ObjectRBTreeMap.defaultReturnValue(defaultValue);
/*  66 */       if (json.has("materialTypes")) {
/*  67 */         JsonObject materialTypesJson = jsonHelper.getJsonObject(json, "materialTypes");
/*  68 */         for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialTypesJson.entrySet()) {
/*     */           Enum<?> materialTypeValue;
/*  70 */           if (jsonHelper.isString(entry.getValue())) {
/*  71 */             String materialTypeString = jsonHelper.getString(entry.getValue(), "element");
/*  72 */             Enum<?> value = stringToEnum.get(materialTypeString.toLowerCase(Locale.US));
/*  73 */             if (value == null) {
/*  74 */               throw new JsonSyntaxException("Invalid enum " + materialTypeString);
/*     */             }
/*  76 */             materialTypeValue = value;
/*     */           }
/*  78 */           else if (jsonHelper.isNumber(entry.getValue())) {
/*  79 */             int materialTypeOrdinal = jsonHelper.getInt(entry.getValue(), "element");
/*  80 */             if (materialTypeOrdinal >= arrayOfEnum.length) {
/*  81 */               throw new JsonSyntaxException("Invalid enum ordinal " + materialTypeOrdinal);
/*     */             }
/*  83 */             materialTypeValue = arrayOfEnum[materialTypeOrdinal];
/*     */           } else {
/*     */             
/*  86 */             throw new JsonSyntaxException("Unable to deserialize enum");
/*     */           } 
/*  88 */           switch ((String)entry.getKey()) {
/*     */             case "ingot":
/*  90 */               MaterialHandler.getMaterials().stream()
/*  91 */                 .filter(m -> m.getType().isIngot())
/*  92 */                 .forEach(m -> (Enum)map.put(m, materialTypeValue));
/*     */             
/*     */             case "gem":
/*  95 */               MaterialHandler.getMaterials().stream()
/*  96 */                 .filter(m -> m.getType().isGem())
/*  97 */                 .forEach(m -> (Enum)map.put(m, materialTypeValue));
/*     */             
/*     */             case "crystal":
/* 100 */               MaterialHandler.getMaterials().stream()
/* 101 */                 .filter(m -> m.getType().isCrystal())
/* 102 */                 .forEach(m -> (Enum)map.put(m, materialTypeValue));
/*     */             
/*     */             case "dust":
/* 105 */               MaterialHandler.getMaterials().stream()
/* 106 */                 .filter(m -> m.getType().isDust())
/* 107 */                 .forEach(m -> (Enum)map.put(m, materialTypeValue));
/*     */           } 
/*     */         
/*     */         } 
/*     */       } 
/* 112 */       if (json.has("materials")) {
/* 113 */         JsonObject materialsJson = jsonHelper.getJsonObject(json, "materials");
/* 114 */         for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)materialsJson.entrySet()) {
/* 115 */           if (MaterialHandler.containsMaterial(entry.getKey())) {
/*     */             Enum<?> materialValue;
/* 117 */             if (jsonHelper.isString(entry.getValue())) {
/* 118 */               String materialString = jsonHelper.getString(entry.getValue(), "element");
/* 119 */               Enum<?> value = stringToEnum.get(materialString.toLowerCase(Locale.US));
/* 120 */               if (value == null) {
/* 121 */                 throw new JsonSyntaxException("Invalid enum " + materialString);
/*     */               }
/* 123 */               materialValue = value;
/*     */             }
/* 125 */             else if (jsonHelper.isNumber(entry.getValue())) {
/* 126 */               int materialOrdinal = jsonHelper.getInt(entry.getValue(), "element");
/* 127 */               if (materialOrdinal >= arrayOfEnum.length) {
/* 128 */                 throw new JsonSyntaxException("Invalid enum ordinal " + materialOrdinal);
/*     */               }
/* 130 */               materialValue = arrayOfEnum[materialOrdinal];
/*     */             } else {
/*     */               
/* 133 */               throw new JsonSyntaxException("Unable to deserialize enum");
/*     */             } 
/* 135 */             object2ObjectRBTreeMap.put(MaterialHandler.getMaterial(entry.getKey()), materialValue);
/*     */           } 
/*     */         } 
/*     */       } 
/* 139 */       if (json.has("config") && 
/* 140 */         jsonHelper.getBoolean(json, "config")) {
/* 141 */         String comment, path = jsonHelper.getString(json, "path");
/*     */         
/* 143 */         if (json.has("comment")) {
/* 144 */           comment = jsonHelper.getString(json, "comment");
/*     */         } else {
/*     */           
/* 147 */           comment = "";
/*     */         } 
/* 149 */         CustomModule.instance.addCustomConfigDefiner((material, config) -> {
/*     */               Enum<?> value = config.getDefinedEnum(path, (Class)parameterizedType, (Enum)map.get(material), comment);
/*     */               
/*     */               if (value != null) {
/*     */                 map.put(material, value);
/*     */               }
/*     */             });
/*     */       } 
/*     */       
/* 158 */       return material -> (Enum)map.get(material);
/*     */     } 
/* 160 */     throw new JsonParseException("Unable to deserialize " + jsonHelper.toSimpleString(jsonElement) + " into an enum function");
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\MaterialEnumFunctionDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */