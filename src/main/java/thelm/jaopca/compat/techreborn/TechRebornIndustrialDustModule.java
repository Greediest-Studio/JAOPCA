/*     */ package thelm.jaopca.compat.techreborn;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import techreborn.init.ModFluids;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"techreborn"})
/*     */ public class TechRebornIndustrialDustModule
/*     */   implements IModule
/*     */ {
/*  28 */   private static Set<String> configWaterBlacklist = new TreeSet<>();
/*  29 */   private static Set<String> configSodiumPersulfateBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  33 */     return "techreborn_industrial_dust";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  38 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  39 */     builder.put(Integer.valueOf(1), "dust");
/*  40 */     builder.put(Integer.valueOf(2), "small_dust");
/*  41 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  46 */     return EnumSet.of(MaterialType.DUST);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  51 */     return TechRebornIndustrialIngotModule.BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  56 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  57 */     miscHelper.caclulateMaterialSet(config
/*  58 */         .getDefinedStringList("recipes.waterMaterialBlacklist", new ArrayList(), miscHelper
/*  59 */           .configMaterialPredicate(), "The materials that should not have water industrial grinder recipes added."), configWaterBlacklist);
/*     */     
/*  61 */     miscHelper.caclulateMaterialSet(config
/*  62 */         .getDefinedStringList("recipes.sodiumPersulfateMaterialBlacklist", new ArrayList(), miscHelper
/*  63 */           .configMaterialPredicate(), "The materials that should not have sodium persulfate\t industrial grinder recipes added."), configSodiumPersulfateBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  69 */     TechRebornHelper helper = TechRebornHelper.INSTANCE;
/*  70 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  71 */     for (IMaterial material : moduleData.getMaterials()) {
/*  72 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  73 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*  74 */       String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*  75 */       String secondExtraSmallDustOredict = miscHelper.getOredictName("dustSmall", material.getExtra(2).getName());
/*  76 */       if (!configWaterBlacklist.contains(material.getName())) {
/*     */         
/*  78 */         Object[] output = { materialOredict, Integer.valueOf(5) };
/*     */         
/*  80 */         if (material.hasExtra(1)) {
/*  81 */           output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1) });
/*     */         }
/*  83 */         if (material.hasExtra(2)) {
/*  84 */           output = ArrayUtils.addAll(output, new Object[] { secondExtraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/*  86 */         helper.registerIndustrialGrinderRecipe(miscHelper
/*  87 */             .getRecipeKey("techreborn.ore_to_material_water", material.getName()), oreOredict, 1, FluidRegistry.WATER, 1000, 100, 64, output);
/*     */       } 
/*     */       
/*  90 */       if (!configSodiumPersulfateBlacklist.contains(material.getName())) {
/*     */         
/*  92 */         Object[] output = { materialOredict, Integer.valueOf(5) };
/*     */         
/*  94 */         if (material.hasExtra(1)) {
/*  95 */           output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(3) });
/*     */         }
/*  97 */         if (material.hasExtra(2)) {
/*  98 */           output = ArrayUtils.addAll(output, new Object[] { secondExtraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/* 100 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 101 */             .getRecipeKey("techreborn.ore_to_material_sodium_persulfate", material.getName()), oreOredict, 1, ModFluids.SODIUMPERSULFATE, 1000, 100, 64, output);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornIndustrialDustModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */