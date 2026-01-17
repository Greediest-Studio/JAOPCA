/*    */ package thelm.jaopca.compat.appliedenergistics2;
/*    */ 
/*    */ import appeng.core.AEConfig;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"appliedenergistics2"})
/*    */ public class AppliedEnergistics2CompatModule
/*    */   implements IModule
/*    */ {
/* 23 */   private static final Set<String> TO_DUST_BLACKLIST = new TreeSet<>();
/* 24 */   private static Set<String> configToDustBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return "appliedenergistics2_compat";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 33 */     return EnumSet.allOf(MaterialType.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 38 */     Collections.addAll(TO_DUST_BLACKLIST, AEConfig.instance().getGrinderOres());
/* 39 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 40 */     miscHelper.caclulateMaterialSet(config
/* 41 */         .getDefinedStringList("recipes.toDustMaterialBlacklist", new ArrayList(), miscHelper
/* 42 */           .configMaterialPredicate(), "The materials that should not have grinder to dust recipes added."), configToDustBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 48 */     AppliedEnergistics2Helper helper = AppliedEnergistics2Helper.INSTANCE;
/* 49 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 50 */     for (IMaterial material : moduleData.getMaterials()) {
/* 51 */       MaterialType type = material.getType();
/* 52 */       String name = material.getName();
/* 53 */       if (type.isIngot() && !TO_DUST_BLACKLIST.contains(name) && !configToDustBlacklist.contains(name)) {
/* 54 */         String oreOredict = miscHelper.getOredictName(type.getFormName(), material.getName());
/* 55 */         String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 56 */         helper.registerGrinderRecipe(miscHelper
/* 57 */             .getRecipeKey("appliedenergistics2.material_to_dust", material.getName()), oreOredict, dustOredict, 1, 4);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\appliedenergistics2\AppliedEnergistics2CompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */