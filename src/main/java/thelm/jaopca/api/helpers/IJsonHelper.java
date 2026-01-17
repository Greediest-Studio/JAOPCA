package thelm.jaopca.api.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;

public interface IJsonHelper {
  boolean isString(JsonObject paramJsonObject, String paramString);
  
  boolean isString(JsonElement paramJsonElement);
  
  boolean isNumber(JsonObject paramJsonObject, String paramString);
  
  boolean isNumber(JsonElement paramJsonElement);
  
  boolean isBoolean(JsonObject paramJsonObject, String paramString);
  
  boolean isJsonArray(JsonObject paramJsonObject, String paramString);
  
  boolean isJsonPrimitive(JsonObject paramJsonObject, String paramString);
  
  boolean hasField(JsonObject paramJsonObject, String paramString);
  
  String getString(JsonElement paramJsonElement, String paramString);
  
  String getString(JsonObject paramJsonObject, String paramString);
  
  String getString(JsonObject paramJsonObject, String paramString1, String paramString2);
  
  boolean getBoolean(JsonElement paramJsonElement, String paramString);
  
  boolean getBoolean(JsonObject paramJsonObject, String paramString);
  
  boolean getBoolean(JsonObject paramJsonObject, String paramString, boolean paramBoolean);
  
  double getDouble(JsonElement paramJsonElement, String paramString);
  
  double getDouble(JsonObject paramJsonObject, String paramString);
  
  double getDouble(JsonObject paramJsonObject, String paramString, double paramDouble);
  
  int getInt(JsonElement paramJsonElement, String paramString);
  
  int getInt(JsonObject paramJsonObject, String paramString);
  
  int getInt(JsonObject paramJsonObject, String paramString, int paramInt);
  
  long getLong(JsonElement paramJsonElement, String paramString);
  
  long getLong(JsonObject paramJsonObject, String paramString);
  
  long getLong(JsonObject paramJsonObject, String paramString, int paramInt);
  
  JsonObject getJsonObject(JsonElement paramJsonElement, String paramString);
  
  JsonObject getJsonObject(JsonObject paramJsonObject, String paramString);
  
  JsonObject getJsonObject(JsonObject paramJsonObject1, String paramString, JsonObject paramJsonObject2);
  
  JsonArray getJsonArray(JsonElement paramJsonElement, String paramString);
  
  JsonArray getJsonArray(JsonObject paramJsonObject, String paramString);
  
  JsonArray getJsonArray(JsonObject paramJsonObject, String paramString, JsonArray paramJsonArray);
  
  <T> T deserializeType(JsonElement paramJsonElement, String paramString, JsonDeserializationContext paramJsonDeserializationContext, Type paramType);
  
  <T> T deserializeType(JsonObject paramJsonObject, String paramString, JsonDeserializationContext paramJsonDeserializationContext, Type paramType);
  
  <T> T deserializeType(JsonObject paramJsonObject, String paramString, T paramT, JsonDeserializationContext paramJsonDeserializationContext, Type paramType);
  
  String toSimpleString(JsonElement paramJsonElement);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\helpers\IJsonHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */