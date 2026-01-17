/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.custom.CustomModule;
/*    */ import thelm.jaopca.forms.Form;
/*    */ import thelm.jaopca.forms.FormTypeHandler;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ public class FormDeserializer
/*    */   implements JsonDeserializer<IForm>
/*    */ {
/* 22 */   public static final FormDeserializer INSTANCE = new FormDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IForm deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 28 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 29 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "element");
/* 30 */     String name = jsonHelper.getString(json, "name");
/* 31 */     IFormType type = FormTypeHandler.getFormType(jsonHelper.getString(json, "type"));
/* 32 */     Form form = new Form((IModule)CustomModule.instance, name, type);
/* 33 */     if (json.has("secondaryName")) {
/* 34 */       form.setSecondaryName(jsonHelper.getString(json, "secondaryName"));
/*    */     }
/* 36 */     if (json.has("materialTypes")) {
/* 37 */       form.setMaterialTypes((MaterialType[])jsonHelper.deserializeType(json, "materialTypes", context, MaterialType[].class));
/*    */     }
/* 39 */     if (json.has("defaultMaterialBlacklist")) {
/* 40 */       form.setDefaultMaterialBlacklist((String[])jsonHelper.deserializeType(json, "defaultMaterialBlacklist", context, String[].class));
/*    */     }
/* 42 */     if (json.has("skipGroupedCheck")) {
/* 43 */       form.setSkipGroupedCheck(jsonHelper.getBoolean(json, "skipGroupedCheck"));
/*    */     }
/* 45 */     if (json.has("settings")) {
/* 46 */       form.setSettings(type.deserializeSettings(json.get("settings"), context));
/*    */     }
/* 48 */     return (IForm)form;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\FormDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */