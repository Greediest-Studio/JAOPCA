/*    */ package thelm.jaopca.api.forms;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IForm
/*    */   extends Comparable<IForm>
/*    */ {
/*    */   default int compareTo(IForm other) {
/* 56 */     return getName().compareTo(other.getName());
/*    */   }
/*    */   
/*    */   void setMaterials(Collection<IMaterial> paramCollection);
/*    */   
/*    */   boolean isMaterialValid(IMaterial paramIMaterial);
/*    */   
/*    */   IForm setRequest(IFormRequest paramIFormRequest);
/*    */   
/*    */   IFormRequest toRequest();
/*    */   
/*    */   IForm lock();
/*    */   
/*    */   Set<IMaterial> getMaterials();
/*    */   
/*    */   boolean skipGroupedCheck();
/*    */   
/*    */   IForm setSkipGroupedCheck(boolean paramBoolean);
/*    */   
/*    */   IFormSettings getSettings();
/*    */   
/*    */   IForm setSettings(IFormSettings paramIFormSettings);
/*    */   
/*    */   Set<String> getDefaultMaterialBlacklist();
/*    */   
/*    */   IForm setDefaultMaterialBlacklist(String... paramVarArgs);
/*    */   
/*    */   IForm setDefaultMaterialBlacklist(Collection<String> paramCollection);
/*    */   
/*    */   Set<MaterialType> getMaterialTypes();
/*    */   
/*    */   IForm setMaterialTypes(MaterialType... paramVarArgs);
/*    */   
/*    */   IForm setMaterialTypes(Collection<MaterialType> paramCollection);
/*    */   
/*    */   String getSecondaryName();
/*    */   
/*    */   IForm setSecondaryName(String paramString);
/*    */   
/*    */   IModule getModule();
/*    */   
/*    */   IFormType getType();
/*    */   
/*    */   String getName();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\forms\IForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */