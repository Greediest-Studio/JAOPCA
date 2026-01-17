/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.custom.CustomModule;
/*    */ import thelm.jaopca.forms.FormRequest;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ public class FormRequestDeserializer
/*    */   implements JsonDeserializer<IFormRequest>
/*    */ {
/* 18 */   public static final FormRequestDeserializer INSTANCE = new FormRequestDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IFormRequest deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 24 */     if (jsonElement.isJsonObject()) {
/* 25 */       return ((IForm)context.deserialize(jsonElement, IForm.class)).toRequest();
/*    */     }
/* 27 */     if (jsonElement.isJsonArray()) {
/* 28 */       return (new FormRequest((IModule)CustomModule.instance, (IForm[])context.deserialize(jsonElement, IForm.class))).setGrouped(true);
/*    */     }
/* 30 */     throw new JsonParseException("Unable to deserialize " + JsonHelper.INSTANCE.toSimpleString(jsonElement) + " into a form request");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\FormRequestDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */