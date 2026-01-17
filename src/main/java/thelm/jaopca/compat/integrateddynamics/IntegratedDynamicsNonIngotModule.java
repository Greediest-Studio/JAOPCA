/*    */ package thelm.jaopca.compat.integrateddynamics;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"integrateddynamics"})
/*    */ public class IntegratedDynamicsNonIngotModule
/*    */   implements IModule
/*    */ {
/*    */   public String getName() {
/* 20 */     return "integrateddynamics_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 25 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 30 */     return IntegratedDynamicsModule.BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 35 */     IntegratedDynamicsHelper helper = IntegratedDynamicsHelper.INSTANCE;
/* 36 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 37 */     for (IMaterial material : moduleData.getMaterials()) {
/* 38 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 39 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 40 */       if (material.getType().isCrystalline()) {
/* 41 */         helper.registerSqueezerRecipe(miscHelper
/* 42 */             .getRecipeKey("integrateddynamics.ore_to_material", material.getName()), oreOredict, new Object[] { materialOredict, 
/*    */               
/* 44 */               Integer.valueOf(1), Float.valueOf(1.0F), materialOredict, 
/* 45 */               Integer.valueOf(1), Float.valueOf(0.75F) });
/*    */         
/* 47 */         helper.registerMechanicalSqueezerRecipe(miscHelper
/* 48 */             .getRecipeKey("integrateddynamics.ore_to_material_mechanical", material.getName()), oreOredict, new Object[] { materialOredict, 
/*    */               
/* 50 */               Integer.valueOf(2), Float.valueOf(1.0F), materialOredict, 
/* 51 */               Integer.valueOf(1), Float.valueOf(0.5F) }, 40);
/*    */         
/*    */         continue;
/*    */       } 
/* 55 */       helper.registerSqueezerRecipe(miscHelper
/* 56 */           .getRecipeKey("integrateddynamics.ore_to_material", material.getName()), oreOredict, new Object[] { materialOredict, 
/*    */             
/* 58 */             Integer.valueOf(6), Float.valueOf(1.0F), materialOredict, 
/* 59 */             Integer.valueOf(2), Float.valueOf(0.5F), materialOredict, 
/* 60 */             Integer.valueOf(2), Float.valueOf(0.5F) });
/*    */       
/* 62 */       helper.registerMechanicalSqueezerRecipe(miscHelper
/* 63 */           .getRecipeKey("integrateddynamics.ore_to_material_mechanical", material.getName()), oreOredict, new Object[] { materialOredict, 
/*    */             
/* 65 */             Integer.valueOf(10), Float.valueOf(1.0F), materialOredict, 
/* 66 */             Integer.valueOf(2), Float.valueOf(0.5F) }, 40);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\integrateddynamics\IntegratedDynamicsNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */