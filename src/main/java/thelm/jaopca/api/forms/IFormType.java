/*    */ package thelm.jaopca.api.forms;
/*    */ 
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.util.Set;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IFormType
/*    */   extends Comparable<IFormType>
/*    */ {
/*    */   default void registerMaterialForms() {}
/*    */   
/*    */   default int compareTo(IFormType other) {
/* 34 */     return getName().compareTo(other.getName());
/*    */   }
/*    */   
/*    */   String getName();
/*    */   
/*    */   void addForm(IForm paramIForm);
/*    */   
/*    */   Set<IForm> getForms();
/*    */   
/*    */   boolean shouldRegister(IForm paramIForm, IMaterial paramIMaterial);
/*    */   
/*    */   IFormSettings getNewSettings();
/*    */   
/*    */   GsonBuilder configureGsonBuilder(GsonBuilder paramGsonBuilder);
/*    */   
/*    */   IFormSettings deserializeSettings(JsonElement paramJsonElement, JsonDeserializationContext paramJsonDeserializationContext);
/*    */   
/*    */   IMaterialFormInfo getMaterialFormInfo(IForm paramIForm, IMaterial paramIMaterial);
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\forms\IFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */