/*     */ package thelm.jaopca.compat.techreborn;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
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
/*     */ public class TechRebornIndustrialIngotModule
/*     */   implements IModule
/*     */ {
/*  29 */   static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Amethyst", "Apatite", "Ardite", "Bauxite", "Cinnabar", "Coal", "Cobalt", "Copper", "Diamond", "Emerald", "Galena", "Gold", "Iridium", "Iron", "Lapis", "Lead", "Malachite", "NetherQuartz", "Nickel", "Niter", "Osmium", "Peridot", "Pitchblende", "Pyrite", "Quartz", "Redstone", "Ruby", "Saltpeter", "Sapphire", "Sheldonite", "Silver", "Sodalite", "Sphalerite", "Sulfur", "Tanzanite", "Teslatite", "Tin", "Topaz", "Tungsten", "Uranium", "Zinc" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static Set<String> configWaterBlacklist = new TreeSet<>();
/*  36 */   private static Set<String> configMercuryBlacklist = new TreeSet<>();
/*  37 */   private static Set<String> configSodiumPersulfateBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  41 */     return "techreborn_industrial_ingot";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  46 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  47 */     builder.put(Integer.valueOf(0), "dust");
/*  48 */     builder.put(Integer.valueOf(1), "dust");
/*  49 */     builder.put(Integer.valueOf(1), "small_dust");
/*  50 */     builder.put(Integer.valueOf(2), "small_dust");
/*  51 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  56 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  61 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  66 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  67 */     miscHelper.caclulateMaterialSet(config
/*  68 */         .getDefinedStringList("recipes.waterMaterialBlacklist", new ArrayList(), miscHelper
/*  69 */           .configMaterialPredicate(), "The materials that should not have water industrial grinder recipes added."), configWaterBlacklist);
/*     */     
/*  71 */     miscHelper.caclulateMaterialSet(config
/*  72 */         .getDefinedStringList("recipes.mercuryMaterialBlacklist", new ArrayList(), miscHelper
/*  73 */           .configMaterialPredicate(), "The materials that should not have mercury industrial grinder recipes added."), configMercuryBlacklist);
/*     */     
/*  75 */     miscHelper.caclulateMaterialSet(config
/*  76 */         .getDefinedStringList("recipes.sodiumPersulfateMaterialBlacklist", new ArrayList(), miscHelper
/*  77 */           .configMaterialPredicate(), "The materials that should not have sodium persulfate\t industrial grinder recipes added."), configSodiumPersulfateBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  83 */     TechRebornHelper helper = TechRebornHelper.INSTANCE;
/*  84 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  85 */     for (IMaterial material : moduleData.getMaterials()) {
/*  86 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  87 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*  88 */       String extraSmallDustOredict = miscHelper.getOredictName("dustSmall", material.getExtra(1).getName());
/*  89 */       String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*  90 */       String secondExtraSmallDustOredict = miscHelper.getOredictName("dustSmall", material.getExtra(2).getName());
/*  91 */       if (!configWaterBlacklist.contains(material.getName())) {
/*     */         
/*  93 */         Object[] output = { dustOredict, Integer.valueOf(2) };
/*     */         
/*  95 */         if (material.hasExtra(1)) {
/*  96 */           output = ArrayUtils.addAll(output, new Object[] { extraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/*  98 */         if (material.hasExtra(2)) {
/*  99 */           output = ArrayUtils.addAll(output, new Object[] { secondExtraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/* 101 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 102 */             .getRecipeKey("techreborn.ore_to_dust_water", material.getName()), oreOredict, 1, FluidRegistry.WATER, 1000, 100, 64, output);
/*     */       } 
/*     */       
/* 105 */       if (!configMercuryBlacklist.contains(material.getName())) {
/*     */         
/* 107 */         Object[] output = { dustOredict, Integer.valueOf(3) };
/*     */         
/* 109 */         if (material.hasExtra(1)) {
/* 110 */           output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1) });
/*     */         }
/* 112 */         if (material.hasExtra(2)) {
/* 113 */           output = ArrayUtils.addAll(output, new Object[] { secondExtraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/* 115 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 116 */             .getRecipeKey("techreborn.ore_to_dust_mercury", material.getName()), oreOredict, 1, ModFluids.MERCURY, 1000, 100, 64, output);
/*     */       } 
/*     */       
/* 119 */       if (!configSodiumPersulfateBlacklist.contains(material.getName())) {
/*     */         
/* 121 */         Object[] output = { dustOredict, Integer.valueOf(3) };
/*     */         
/* 123 */         if (material.hasExtra(1)) {
/* 124 */           output = ArrayUtils.addAll(output, new Object[] { extraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/* 126 */         if (material.hasExtra(2)) {
/* 127 */           output = ArrayUtils.addAll(output, new Object[] { secondExtraSmallDustOredict, Integer.valueOf(1) });
/*     */         }
/* 129 */         helper.registerIndustrialGrinderRecipe(miscHelper
/* 130 */             .getRecipeKey("techreborn.ore_to_dust_sodium_persulfate", material.getName()), oreOredict, 1, ModFluids.SODIUMPERSULFATE, 1000, 100, 64, output);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornIndustrialIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */