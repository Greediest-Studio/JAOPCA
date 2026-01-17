/*    */ package thelm.jaopca.compat.groovyscript;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.forms.FormHandler;
/*    */ 
/*    */ 
/*    */ public class ModuleDataExpansion
/*    */ {
/*    */   public static String getName(IModuleData data) {
/* 16 */     return data.getModule().getName();
/*    */   }
/*    */   
/*    */   public static Set<MaterialType> getMaterialTypes(IModuleData data) {
/* 20 */     return data.getModule().getMaterialTypes();
/*    */   }
/*    */   
/*    */   public static boolean containsMaterial(IModuleData data, IMaterial material) {
/* 24 */     return data.getMaterials().contains(material);
/*    */   }
/*    */   
/*    */   public static List<IForm> getForms(IModuleData data) {
/* 28 */     return (List<IForm>)FormHandler.getForms().stream().filter(f -> (f.getModule() == data.getModule())).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\groovyscript\ModuleDataExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */