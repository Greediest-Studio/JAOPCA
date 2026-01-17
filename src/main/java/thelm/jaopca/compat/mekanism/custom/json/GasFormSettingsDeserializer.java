/*    */ package thelm.jaopca.compat.mekanism.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasFormSettings;
/*    */ import thelm.jaopca.compat.mekanism.gases.GasFormType;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GasFormSettingsDeserializer
/*    */   implements JsonDeserializer<IGasFormSettings>
/*    */ {
/* 18 */   public static final GasFormSettingsDeserializer INSTANCE = new GasFormSettingsDeserializer();
/*    */ 
/*    */ 
/*    */   
/*    */   public IGasFormSettings deserialize(JsonElement jsonElement, JsonDeserializationContext context) {
/* 23 */     return deserialize(jsonElement, IGasFormSettings.class, context);
/*    */   }
/*    */ 
/*    */   
/*    */   public IGasFormSettings deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 28 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 29 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "settings");
/* 30 */     IGasFormSettings settings = GasFormType.INSTANCE.getNewSettings();
/* 31 */     if (json.has("isHidden")) {
/* 32 */       settings.setIsHidden(jsonHelper.getBoolean(json, "isHidden"));
/*    */     }
/* 34 */     return settings;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\custom\json\GasFormSettingsDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */