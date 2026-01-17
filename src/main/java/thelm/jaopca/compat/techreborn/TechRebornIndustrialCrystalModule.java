/*    */ package thelm.jaopca.compat.techreborn;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fluids.FluidRegistry;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import techreborn.init.ModFluids;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"techreborn"})
/*    */ public class TechRebornIndustrialCrystalModule
/*    */   implements IModule
/*    */ {
/* 28 */   private static Set<String> configWaterBlacklist = new TreeSet<>();
/* 29 */   private static Set<String> configMercuryBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     return "techreborn_industrial_crystal";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 38 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 39 */     builder.put(Integer.valueOf(0), "small_dust");
/* 40 */     builder.put(Integer.valueOf(1), "small_dust");
/* 41 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 46 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 51 */     return TechRebornIndustrialIngotModule.BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 56 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 57 */     miscHelper.caclulateMaterialSet(config
/* 58 */         .getDefinedStringList("recipes.waterMaterialBlacklist", new ArrayList(), miscHelper
/* 59 */           .configMaterialPredicate(), "The materials that should not have water industrial grinder recipes added."), configWaterBlacklist);
/*    */     
/* 61 */     miscHelper.caclulateMaterialSet(config
/* 62 */         .getDefinedStringList("recipes.mercuryMaterialBlacklist", new ArrayList(), miscHelper
/* 63 */           .configMaterialPredicate(), "The materials that should not have mercury industrial grinder recipes added."), configMercuryBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 69 */     TechRebornHelper helper = TechRebornHelper.INSTANCE;
/* 70 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 71 */     for (IMaterial material : moduleData.getMaterials()) {
/* 72 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 73 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 74 */       String smallDustOredict = miscHelper.getOredictName("dustSmall", material.getName());
/* 75 */       String extraSmallDustOredict = miscHelper.getOredictName("dustSmall", material.getExtra(1).getName());
/* 76 */       if (!configWaterBlacklist.contains(material.getName())) {
/*    */         
/* 78 */         Object[] output = { materialOredict, Integer.valueOf(1), smallDustOredict, Integer.valueOf(6) };
/*    */         
/* 80 */         if (material.hasExtra(1)) {
/* 81 */           output = ArrayUtils.addAll(output, new Object[] { extraSmallDustOredict, Integer.valueOf(2) });
/*    */         }
/* 83 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 84 */             .getRecipeKey("techreborn.ore_to_material_water", material.getName()), oreOredict, 1, FluidRegistry.WATER, 1000, 100, 64, output);
/*    */       } 
/*    */       
/* 87 */       if (!configMercuryBlacklist.contains(material.getName())) {
/*    */         
/* 89 */         Object[] output = { materialOredict, Integer.valueOf(2), smallDustOredict, Integer.valueOf(3) };
/*    */         
/* 91 */         if (material.hasExtra(1)) {
/* 92 */           output = ArrayUtils.addAll(output, new Object[] { extraSmallDustOredict, Integer.valueOf(2) });
/*    */         }
/* 94 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 95 */             .getRecipeKey("techreborn.ore_to_material_mercury", material.getName()), oreOredict, 1, ModFluids.MERCURY, 1000, 100, 64, output);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornIndustrialCrystalModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */