/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnumDeserializer
/*    */   implements JsonDeserializer<Enum<?>>
/*    */ {
/* 19 */   public static final EnumDeserializer INSTANCE = new EnumDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Enum<?> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 25 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 26 */     if (typeOfT instanceof Class && ((Class)typeOfT).isEnum()) {
/* 27 */       if (jsonHelper.isString(jsonElement)) {
/* 28 */         Map<String, Enum<?>> stringToEnum = new TreeMap<>();
/* 29 */         for (Enum<?> enum_ : (Enum[])((Class<Enum>)typeOfT).getEnumConstants()) {
/* 30 */           stringToEnum.put(enum_.name().toLowerCase(Locale.US), enum_);
/*    */         }
/* 32 */         String valueString = jsonHelper.getString(jsonElement, "value");
/* 33 */         Enum<?> value = stringToEnum.get(valueString);
/* 34 */         if (value == null) {
/* 35 */           throw new JsonSyntaxException("Invalid enum " + valueString);
/*    */         }
/* 37 */         return value;
/*    */       } 
/* 39 */       if (jsonHelper.isNumber(jsonElement)) {
/* 40 */         int valueOrdinal = jsonHelper.getInt(jsonElement, "value");
/* 41 */         Enum[] arrayOfEnum = ((Class<Enum>)typeOfT).getEnumConstants();
/* 42 */         if (valueOrdinal >= arrayOfEnum.length) {
/* 43 */           throw new JsonSyntaxException("Invalid enum ordinal " + valueOrdinal);
/*    */         }
/* 45 */         return arrayOfEnum[valueOrdinal];
/*    */       } 
/*    */     } 
/* 48 */     throw new JsonParseException("Unable to deserialize " + jsonHelper.toSimpleString(jsonElement) + " into an enum");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\EnumDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */