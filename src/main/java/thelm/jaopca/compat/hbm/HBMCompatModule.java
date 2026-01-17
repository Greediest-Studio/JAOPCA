/*    */ package thelm.jaopca.compat.hbm;
/*    */ 
/*    */ import com.hbm.forgefluid.ModForgeFluids;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"hbm"}, classDependencies = {"com.hbm.main.MainRegistry"})
/*    */ public class HBMCompatModule
/*    */   implements IModule
/*    */ {
/* 27 */   private static final Set<String> TO_PLATE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "AdvancedAlloy", "Aluminium", "Aluminum", "CMBSteel", "Copper", "Gold", "Iron", "Lead", "Saturnite", "Schrabidium", "Steel", "Titanium" }));
/*    */ 
/*    */   
/* 30 */   private static final Set<String> TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Diamond", "Emerald", "Lapis" }));
/*    */   
/* 32 */   private static Set<String> configAnvilToPlateBlacklist = new TreeSet<>();
/* 33 */   private static Set<String> configToCrystalBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return "hbm_compat";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 42 */     return EnumSet.allOf(MaterialType.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 47 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 48 */     miscHelper.caclulateMaterialSet(config
/* 49 */         .getDefinedStringList("recipes.anvilToPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 50 */           .configMaterialPredicate(), "The materials that should not have anvil to plate recipes added. (Why are press recipes hardcoded)"), configAnvilToPlateBlacklist);
/*    */     
/* 52 */     miscHelper.caclulateMaterialSet(config
/* 53 */         .getDefinedStringList("recipes.toCrystalMaterialBlacklist", new ArrayList(), miscHelper
/* 54 */           .configMaterialPredicate(), "The materials that should not have crystallizing to material recipes added."), configToCrystalBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 60 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 61 */     HBMHelper helper = HBMHelper.INSTANCE;
/* 62 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 63 */     Set<String> oredict = apiImpl.getOredict();
/* 64 */     Fluid acid = ModForgeFluids.acid;
/* 65 */     for (IMaterial material : moduleData.getMaterials()) {
/* 66 */       MaterialType type = material.getType();
/* 67 */       String name = material.getName();
/* 68 */       if (type.isIngot() && !TO_PLATE_BLACKLIST.contains(name) && !configAnvilToPlateBlacklist.contains(name)) {
/* 69 */         String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), name);
/* 70 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 71 */         if (oredict.contains(plateOredict)) {
/* 72 */           helper.registerAnvilConstructionRecipe(miscHelper
/* 73 */               .getRecipeKey("hbm.material_to_plate_anvil", name), new Object[] { materialOredict, 
/*    */                 
/* 75 */                 Integer.valueOf(1) }, new Object[] { plateOredict, 
/*    */                 
/* 77 */                 Integer.valueOf(1), Float.valueOf(1.0F) }, 3);
/*    */         }
/*    */       } 
/*    */       
/* 81 */       if (type.isCrystalline() && !TO_CRYSTAL_BLACKLIST.contains(name) && !configToCrystalBlacklist.contains(name)) {
/* 82 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 83 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 84 */         if (oredict.contains(dustOredict))
/* 85 */           helper.registerCrystallizerRecipe(miscHelper
/* 86 */               .getRecipeKey("hbm.dust_to_material", name), dustOredict, acid, 500, materialOredict, 1); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\HBMCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */