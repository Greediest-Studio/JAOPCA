/*     */ package thelm.jaopca.compat.ic2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"ic2"})
/*     */ public class IC2CompatModule
/*     */   implements IModule
/*     */ {
/*  24 */   private static final Set<String> MATERIAL_TO_DUST_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bronze", "Clay", "Coal", "Copper", "Diamond", "Emerald", "EnderEye", "EnderPearl", "Gold", "Iron", "Lapis", "Lead", "Obsidian", "Silver", "Steel", "Tin" }));
/*     */ 
/*     */   
/*  27 */   private static final Set<String> PLATE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bronze", "Copper", "Gold", "Iron", "Lapis", "Lead", "Obsidian", "Steel", "Tin" }));
/*     */   
/*  29 */   private static final Set<String> TO_BLOCK_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bronze", "Coal", "Copper", "Gold", "Iron", "Lapis", "Lead", "Redstone", "Silver", "Steel", "Tin" }));
/*     */   
/*  31 */   private static final Set<String> TINY_DUST_TO_DUST_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bronze", "Copper", "Gold", "Iron", "Lapis", "Lead", "Lithium", "Obsidian", "Silver", "Sulfur", "Tin" }));
/*     */   
/*  33 */   private static Set<String> configMaterialToDustBlacklist = new TreeSet<>();
/*  34 */   private static Set<String> configIngotToPlateBlacklist = new TreeSet<>();
/*  35 */   private static Set<String> configDustToPlateBlacklist = new TreeSet<>();
/*  36 */   private static Set<String> configBlockToPlateBlacklist = new TreeSet<>();
/*  37 */   private static Set<String> configPlateToDustBlacklist = new TreeSet<>();
/*  38 */   private static Set<String> configToDensePlateBlacklist = new TreeSet<>();
/*  39 */   private static Set<String> configDensePlateToDustBlacklist = new TreeSet<>();
/*  40 */   private static Set<String> configToBlockBlacklist = new TreeSet<>();
/*  41 */   private static Set<String> configTinyDustToDustBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  45 */     return "ic2_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  50 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  55 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  56 */     miscHelper.caclulateMaterialSet(config
/*  57 */         .getDefinedStringList("recipes.materialToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  58 */           .configMaterialPredicate(), "The materials that should not have macerator material to dust recipes added."), configMaterialToDustBlacklist);
/*     */     
/*  60 */     miscHelper.caclulateMaterialSet(config
/*  61 */         .getDefinedStringList("recipes.ingotToPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  62 */           .configMaterialPredicate(), "The materials that should not have metal former rolling to plate recipes added."), configIngotToPlateBlacklist);
/*     */     
/*  64 */     miscHelper.caclulateMaterialSet(config
/*  65 */         .getDefinedStringList("recipes.dustToPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  66 */           .configMaterialPredicate(), "The materials that should not have compressor to plate recipes added."), configDustToPlateBlacklist);
/*     */     
/*  68 */     miscHelper.caclulateMaterialSet(config
/*  69 */         .getDefinedStringList("recipes.blockToPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  70 */           .configMaterialPredicate(), "The materials that should not have metal cutting machine to plate recipes added."), configBlockToPlateBlacklist);
/*     */     
/*  72 */     miscHelper.caclulateMaterialSet(config
/*  73 */         .getDefinedStringList("recipes.plateToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  74 */           .configMaterialPredicate(), "The materials that should not have macerator plate to dust recipes added."), configPlateToDustBlacklist);
/*     */     
/*  76 */     miscHelper.caclulateMaterialSet(config
/*  77 */         .getDefinedStringList("recipes.toDensePlateMaterialBlacklist", new ArrayList(), miscHelper
/*  78 */           .configMaterialPredicate(), "The materials that should not have compressor to dense plate recipes added."), configToDensePlateBlacklist);
/*     */     
/*  80 */     miscHelper.caclulateMaterialSet(config
/*  81 */         .getDefinedStringList("recipes.densePlateToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  82 */           .configMaterialPredicate(), "The materials that should not have macerator dense plate to dust recipes added."), configDensePlateToDustBlacklist);
/*     */     
/*  84 */     miscHelper.caclulateMaterialSet(config
/*  85 */         .getDefinedStringList("recipes.toBlockMaterialBlacklist", new ArrayList(), miscHelper
/*  86 */           .configMaterialPredicate(), "The materials that should not have compressor to block recipes added."), configToBlockBlacklist);
/*     */     
/*  88 */     miscHelper.caclulateMaterialSet(config
/*  89 */         .getDefinedStringList("recipes.tinyDustToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  90 */           .configMaterialPredicate(), "The materials that should not have compressor to dust recipes added."), configTinyDustToDustBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  96 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  97 */     IC2Helper helper = IC2Helper.INSTANCE;
/*  98 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  99 */     Set<String> oredict = apiImpl.getOredict();
/* 100 */     for (IMaterial material : moduleData.getMaterials()) {
/* 101 */       MaterialType type = material.getType();
/* 102 */       String name = material.getName();
/* 103 */       if (!type.isDust() && !MATERIAL_TO_DUST_BLACKLIST.contains(name) && !configMaterialToDustBlacklist.contains(name)) {
/* 104 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 105 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 106 */         if (oredict.contains(dustOredict)) {
/* 107 */           helper.registerMaceratorRecipe(miscHelper
/* 108 */               .getRecipeKey("ic2.material_to_dust", name), materialOredict, 1, dustOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 112 */       if (type.isIngot() && !PLATE_BLACKLIST.contains(name) && !configIngotToPlateBlacklist.contains(name)) {
/* 113 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 114 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 115 */         if (oredict.contains(plateOredict)) {
/* 116 */           helper.registerMetalFormerRollingRecipe(miscHelper
/* 117 */               .getRecipeKey("ic2.material_to_plate", name), materialOredict, 1, plateOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 121 */       if (!type.isIngot() && !PLATE_BLACKLIST.contains(name) && !configDustToPlateBlacklist.contains(name)) {
/* 122 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 123 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 124 */         if (oredict.contains(dustOredict) && oredict.contains(plateOredict)) {
/* 125 */           helper.registerCompressorRecipe(miscHelper
/* 126 */               .getRecipeKey("ic2.dust_to_plate", name), dustOredict, 1, plateOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 130 */       if (!PLATE_BLACKLIST.contains(name) && !configBlockToPlateBlacklist.contains(name)) {
/* 131 */         String blockOredict = miscHelper.getOredictName("block", name);
/* 132 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 133 */         if (oredict.contains(blockOredict) && oredict.contains(plateOredict)) {
/* 134 */           helper.registerBlockCutterRecipe(miscHelper
/* 135 */               .getRecipeKey("ic2.block_to_plate", name), blockOredict, 1, plateOredict, 
/* 136 */               material.isSmallStorageBlock() ? 4 : 9, 5);
/*     */         }
/*     */       } 
/* 139 */       if (!PLATE_BLACKLIST.contains(name) && !configPlateToDustBlacklist.contains(name)) {
/* 140 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 141 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 142 */         if (oredict.contains(plateOredict) && oredict.contains(dustOredict)) {
/* 143 */           helper.registerMaceratorRecipe(miscHelper
/* 144 */               .getRecipeKey("ic2.plate_to_dust", name), plateOredict, 1, dustOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 148 */       if (!PLATE_BLACKLIST.contains(name) && !configToDensePlateBlacklist.contains(name)) {
/* 149 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 150 */         String densePlateOredict = miscHelper.getOredictName("plateDense", name);
/* 151 */         if (oredict.contains(plateOredict) && oredict.contains(densePlateOredict)) {
/* 152 */           helper.registerCompressorRecipe(miscHelper
/* 153 */               .getRecipeKey("ic2.plate_to_dense_plate", name), plateOredict, 9, densePlateOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 157 */       if (!PLATE_BLACKLIST.contains(name) && !configDensePlateToDustBlacklist.contains(name)) {
/* 158 */         String densePlateOredict = miscHelper.getOredictName("plateDense", name);
/* 159 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 160 */         if (oredict.contains(densePlateOredict) && oredict.contains(dustOredict)) {
/* 161 */           helper.registerMaceratorRecipe(miscHelper
/* 162 */               .getRecipeKey("ic2.dense_plate_to_dust", name), densePlateOredict, 1, dustOredict, 9);
/*     */         }
/*     */       } 
/*     */       
/* 166 */       if (!TO_BLOCK_BLACKLIST.contains(name) && !configToBlockBlacklist.contains(name)) {
/* 167 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 168 */         String blockOredict = miscHelper.getOredictName("block", name);
/* 169 */         if (oredict.contains(blockOredict)) {
/* 170 */           helper.registerCompressorRecipe(miscHelper
/* 171 */               .getRecipeKey("ic2.material_to_block", name), materialOredict, 
/* 172 */               material.isSmallStorageBlock() ? 4 : 9, blockOredict, 1);
/*     */         }
/*     */       } 
/* 175 */       if (!TINY_DUST_TO_DUST_BLACKLIST.contains(name) && !configTinyDustToDustBlacklist.contains(name)) {
/* 176 */         String tinyDustOredict = miscHelper.getOredictName("dustTiny", name);
/* 177 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 178 */         if (oredict.contains(tinyDustOredict) && oredict.contains(dustOredict))
/* 179 */           helper.registerCompressorRecipe(miscHelper
/* 180 */               .getRecipeKey("ic2.tiny_dust_to_dust", name), tinyDustOredict, 9, dustOredict, 1); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\IC2CompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */