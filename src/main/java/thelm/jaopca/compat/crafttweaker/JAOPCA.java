/*    */ package thelm.jaopca.compat.crafttweaker;
/*    */ import stanhebben.zenscript.annotations.ZenClass;
/*    */ import stanhebben.zenscript.annotations.ZenMethod;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.forms.FormHandler;
/*    */ import thelm.jaopca.materials.Material;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ 
/*    */ @ZenRegister
/*    */ @ZenClass("mods.jaopca.JAOPCA")
/*    */ public class JAOPCA {
/*    */   @ZenMethod
/*    */   public static boolean containsModule(String moduleName) {
/* 17 */     return ModuleHandler.getModuleMap().containsKey(moduleName);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static Module getModule(String moduleName) {
/* 22 */     return Module.getModuleWrapper((IModule)ModuleHandler.getModuleMap().get(moduleName));
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static boolean containsForm(String formName) {
/* 27 */     return FormHandler.containsForm(formName);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static Form getForm(String formName) {
/* 32 */     return Form.getFormWrapper(FormHandler.getForm(formName));
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static boolean containsMaterial(String materialName) {
/* 37 */     return MaterialHandler.containsMaterial(materialName);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static Material getMaterial(String materialName) {
/* 42 */     return Material.getMaterialWrapper((IMaterial)MaterialHandler.getMaterial(materialName));
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static Material[] getMaterialsForType(String materialType) {
/* 47 */     return (Material[])MaterialHandler.getMaterials().stream().filter(m -> (m.getType() == MaterialType.fromName(materialType))).map(Material::getMaterialWrapper).toArray(x$0 -> new Material[x$0]);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public static Material[] getAllMaterials() {
/* 52 */     return (Material[])MaterialHandler.getMaterials().stream().map(Material::getMaterialWrapper).toArray(x$0 -> new Material[x$0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\JAOPCA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */