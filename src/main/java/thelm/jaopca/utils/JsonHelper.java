/*     */ package thelm.jaopca.utils;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import thelm.jaopca.api.helpers.IJsonHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JsonHelper
/*     */   implements IJsonHelper
/*     */ {
/*  18 */   public static final JsonHelper INSTANCE = new JsonHelper();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isString(JsonObject json, String memberName) {
/*  24 */     return !isJsonPrimitive(json, memberName) ? false : json.getAsJsonPrimitive(memberName).isString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isString(JsonElement json) {
/*  29 */     return !json.isJsonPrimitive() ? false : json.getAsJsonPrimitive().isString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNumber(JsonObject json, String memberName) {
/*  34 */     return !isJsonPrimitive(json, memberName) ? false : json.getAsJsonPrimitive(memberName).isNumber();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNumber(JsonElement json) {
/*  39 */     return !json.isJsonPrimitive() ? false : json.getAsJsonPrimitive().isNumber();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBoolean(JsonObject json, String memberName) {
/*  44 */     return !isJsonPrimitive(json, memberName) ? false : json.getAsJsonPrimitive(memberName).isBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isJsonArray(JsonObject json, String memberName) {
/*  49 */     return !hasField(json, memberName) ? false : json.get(memberName).isJsonArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isJsonPrimitive(JsonObject json, String memberName) {
/*  54 */     return !hasField(json, memberName) ? false : json.get(memberName).isJsonPrimitive();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasField(JsonObject json, String memberName) {
/*  59 */     return (json == null) ? false : json.has(memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(JsonElement json, String memberName) {
/*  64 */     if (json.isJsonPrimitive()) {
/*  65 */       return json.getAsString();
/*     */     }
/*     */     
/*  68 */     throw new JsonSyntaxException("Expected " + memberName + " to be a string, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(JsonObject json, String memberName) {
/*  74 */     if (json.has(memberName)) {
/*  75 */       return getString(json.get(memberName), memberName);
/*     */     }
/*     */     
/*  78 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find a string");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(JsonObject json, String memberName, String fallback) {
/*  84 */     return json.has(memberName) ? getString(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(JsonElement json, String memberName) {
/*  89 */     if (json.isJsonPrimitive()) {
/*  90 */       return json.getAsBoolean();
/*     */     }
/*     */     
/*  93 */     throw new JsonSyntaxException("Expected " + memberName + " to be a boolean, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(JsonObject json, String memberName) {
/*  99 */     if (json.has(memberName)) {
/* 100 */       return getBoolean(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 103 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find a boolean");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(JsonObject json, String memberName, boolean fallback) {
/* 109 */     return json.has(memberName) ? getBoolean(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble(JsonElement json, String memberName) {
/* 114 */     if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
/* 115 */       return json.getAsDouble();
/*     */     }
/*     */     
/* 118 */     throw new JsonSyntaxException("Expected " + memberName + " to be a number, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(JsonObject json, String memberName) {
/* 124 */     if (json.has(memberName)) {
/* 125 */       return getDouble(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 128 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find a number");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(JsonObject json, String memberName, double fallback) {
/* 134 */     return json.has(memberName) ? getDouble(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(JsonElement json, String memberName) {
/* 139 */     if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
/* 140 */       return json.getAsInt();
/*     */     }
/*     */     
/* 143 */     throw new JsonSyntaxException("Expected " + memberName + " to be an number, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(JsonObject json, String memberName) {
/* 149 */     if (json.has(memberName)) {
/* 150 */       return getInt(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 153 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find a number");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(JsonObject json, String memberName, int fallback) {
/* 159 */     return json.has(memberName) ? getInt(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(JsonElement json, String memberName) {
/* 164 */     if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
/* 165 */       return json.getAsLong();
/*     */     }
/*     */     
/* 168 */     throw new JsonSyntaxException("Expected " + memberName + " to be an number, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(JsonObject json, String memberName) {
/* 174 */     if (json.has(memberName)) {
/* 175 */       return getLong(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 178 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find a number");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(JsonObject json, String memberName, int fallback) {
/* 184 */     return json.has(memberName) ? getLong(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObject getJsonObject(JsonElement json, String memberName) {
/* 189 */     if (json.isJsonObject()) {
/* 190 */       return json.getAsJsonObject();
/*     */     }
/*     */     
/* 193 */     throw new JsonSyntaxException("Expected " + memberName + " to be an object, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonObject getJsonObject(JsonObject json, String memberName) {
/* 199 */     if (json.has(memberName)) {
/* 200 */       return getJsonObject(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 203 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find an object");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonObject getJsonObject(JsonObject json, String memberName, JsonObject fallback) {
/* 209 */     return json.has(memberName) ? getJsonObject(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArray getJsonArray(JsonElement json, String memberName) {
/* 214 */     if (json.isJsonArray()) {
/* 215 */       return json.getAsJsonArray();
/*     */     }
/*     */     
/* 218 */     throw new JsonSyntaxException("Expected " + memberName + " to be an array, was " + toSimpleString(json));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonArray getJsonArray(JsonObject json, String memberName) {
/* 224 */     if (json.has(memberName)) {
/* 225 */       return getJsonArray(json.get(memberName), memberName);
/*     */     }
/*     */     
/* 228 */     throw new JsonSyntaxException("Missing " + memberName + ", expected to find an array");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonArray getJsonArray(JsonObject json, String memberName, JsonArray fallback) {
/* 234 */     return json.has(memberName) ? getJsonArray(json.get(memberName), memberName) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T deserializeType(JsonElement json, String memberName, JsonDeserializationContext context, Type typeOfT) {
/* 239 */     if (json != null) {
/* 240 */       return (T)context.deserialize(json, typeOfT);
/*     */     }
/*     */     
/* 243 */     throw new JsonSyntaxException("Missing " + memberName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T deserializeType(JsonObject json, String memberName, JsonDeserializationContext context, Type typeOfT) {
/* 249 */     if (json.has(memberName)) {
/* 250 */       return deserializeType(json.get(memberName), memberName, context, typeOfT);
/*     */     }
/*     */     
/* 253 */     throw new JsonSyntaxException("Missing " + memberName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T deserializeType(JsonObject json, String memberName, T fallback, JsonDeserializationContext context, Type typeOfT) {
/* 259 */     return json.has(memberName) ? deserializeType(json.get(memberName), memberName, context, typeOfT) : fallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toSimpleString(JsonElement json) {
/* 264 */     String s = StringUtils.abbreviateMiddle(String.valueOf(json), "...", 10);
/* 265 */     if (json == null) {
/* 266 */       return "null (missing)";
/*     */     }
/* 268 */     if (json.isJsonNull()) {
/* 269 */       return "null (json)";
/*     */     }
/* 271 */     if (json.isJsonArray()) {
/* 272 */       return "an array (" + s + ")";
/*     */     }
/* 274 */     if (json.isJsonObject()) {
/* 275 */       return "an object (" + s + ")";
/*     */     }
/*     */     
/* 278 */     if (json.isJsonPrimitive()) {
/* 279 */       JsonPrimitive jsonprimitive = json.getAsJsonPrimitive();
/* 280 */       if (jsonprimitive.isNumber()) {
/* 281 */         return "a number (" + s + ")";
/*     */       }
/* 283 */       if (jsonprimitive.isBoolean()) {
/* 284 */         return "a boolean (" + s + ")";
/*     */       }
/* 286 */       if (jsonprimitive.isString()) {
/* 287 */         return "a string (" + s + ")";
/*     */       }
/*     */     } 
/* 290 */     return s;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopc\\utils\JsonHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */