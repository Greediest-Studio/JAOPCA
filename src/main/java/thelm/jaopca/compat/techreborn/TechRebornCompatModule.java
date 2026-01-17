//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.techreborn;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import techreborn.items.ingredients.ItemDusts;
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
/*     */ @JAOPCAModule(modDependencies = {"techreborn"})
/*     */ public class TechRebornCompatModule
/*     */   implements IModule
/*     */ {
/*  28 */   private static final Set<String> TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Amethyst", "Apatite", "CertusQuartz", "Diamond", "Emerald", "Malachite", "Peridot", "RedGarnet", "Ruby", "Sapphire", "Tanzanite", "Topaz", "YellowGarnet" }));
/*     */ 
/*     */   
/*  31 */   private static Set<String> configMaterialToDustBlacklist = new TreeSet<>();
/*  32 */   private static Set<String> configPlateToDustBlacklist = new TreeSet<>();
/*  33 */   private static Set<String> configBlockToDustBlacklist = new TreeSet<>();
/*  34 */   private static Set<String> configCrystalToPlateBlacklist = new TreeSet<>();
/*  35 */   private static Set<String> configDustToPlateBlacklist = new TreeSet<>();
/*  36 */   private static Set<String> configToCrystalBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  40 */     return "techreborn_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  45 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  50 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  51 */     miscHelper.caclulateMaterialSet(config
/*  52 */         .getDefinedStringList("recipes.materialToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  53 */           .configMaterialPredicate(), "The materials that should not have grinder material to dust recipes added."), configMaterialToDustBlacklist);
/*     */     
/*  55 */     miscHelper.caclulateMaterialSet(config
/*  56 */         .getDefinedStringList("recipes.plateToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  57 */           .configMaterialPredicate(), "The materials that should not have grinder plate to dust recipes added."), configPlateToDustBlacklist);
/*     */     
/*  59 */     miscHelper.caclulateMaterialSet(config
/*  60 */         .getDefinedStringList("recipes.blockToDustMaterialBlacklist", new ArrayList(), miscHelper
/*  61 */           .configMaterialPredicate(), "The materials that should not have grinder block to dust recipes added."), configBlockToDustBlacklist);
/*     */     
/*  63 */     miscHelper.caclulateMaterialSet(config
/*  64 */         .getDefinedStringList("recipes.crystalToPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  65 */           .configMaterialPredicate(), "The materials that should not have compressor material to plate recipes added."), configCrystalToPlateBlacklist);
/*     */     
/*  67 */     miscHelper.caclulateMaterialSet(config
/*  68 */         .getDefinedStringList("recipes.dustToPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  69 */           .configMaterialPredicate(), "The materials that should not have compressor dust to plate recipes added."), configDustToPlateBlacklist);
/*     */     
/*  71 */     miscHelper.caclulateMaterialSet(config
/*  72 */         .getDefinedStringList("recipes.toCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  73 */           .configMaterialPredicate(), "The materials that should not have implosion compressor to material recipes added."), configToCrystalBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  79 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  80 */     TechRebornHelper helper = TechRebornHelper.INSTANCE;
/*  81 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  82 */     Set<String> oredict = apiImpl.getOredict();
/*  83 */     ItemStack darkAshes = ItemDusts.getDustByName("dark_ashes", 1);
/*  84 */     for (IMaterial material : moduleData.getMaterials()) {
/*  85 */       MaterialType type = material.getType();
/*  86 */       String name = material.getName();
/*  87 */       if (!type.isDust() && !configMaterialToDustBlacklist.contains(name)) {
/*  88 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/*  89 */         String dustOredict = miscHelper.getOredictName("dust", name);
/*  90 */         if (oredict.contains(dustOredict) && !containsTechRebornItem(dustOredict)) {
/*  91 */           helper.registerGrinderRecipe(miscHelper
/*  92 */               .getRecipeKey("techreborn.material_to_dust", name), materialOredict, 1, dustOredict, 1, 300, 2);
/*     */         }
/*     */       } 
/*     */       
/*  96 */       if (!configPlateToDustBlacklist.contains(name)) {
/*  97 */         String plateOredict = miscHelper.getOredictName("plate", name);
/*  98 */         String dustOredict = miscHelper.getOredictName("dust", name);
/*  99 */         if (oredict.contains(plateOredict) && oredict.contains(dustOredict) && !containsTechRebornItem(dustOredict)) {
/* 100 */           helper.registerGrinderRecipe(miscHelper
/* 101 */               .getRecipeKey("techreborn.plate_to_dust", name), plateOredict, 1, dustOredict, 1, 300, 2);
/*     */         }
/*     */       } 
/*     */       
/* 105 */       if (!configBlockToDustBlacklist.contains(name)) {
/* 106 */         String blockOredict = miscHelper.getOredictName("block", name);
/* 107 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 108 */         if (oredict.contains(blockOredict) && oredict.contains(dustOredict) && !containsTechRebornItem(dustOredict)) {
/* 109 */           helper.registerGrinderRecipe(miscHelper
/* 110 */               .getRecipeKey("techreborn.block_to_dust", name), blockOredict, 1, dustOredict, 
/* 111 */               material.isSmallStorageBlock() ? 4 : 9, 300, 2);
/*     */         }
/*     */       } 
/* 114 */       if (type.isCrystalline() && !configCrystalToPlateBlacklist.contains(name)) {
/* 115 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 116 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 117 */         if (oredict.contains(plateOredict) && !containsTechRebornItem(plateOredict)) {
/* 118 */           helper.registerCompressorRecipe(miscHelper
/* 119 */               .getRecipeKey("techreborn.material_to_plate", name), materialOredict, 1, plateOredict, 1, 400, 2);
/*     */         }
/*     */       } 
/*     */       
/* 123 */       if (!type.isIngot() && !configDustToPlateBlacklist.contains(name)) {
/* 124 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 125 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 126 */         if (oredict.contains(dustOredict) && oredict.contains(plateOredict) && (type.isDust() || !containsTechRebornItem(plateOredict))) {
/* 127 */           helper.registerCompressorRecipe(miscHelper
/* 128 */               .getRecipeKey("techreborn.dust_to_plate", name), dustOredict, 1, plateOredict, 1, 400, 2);
/*     */         }
/*     */       } 
/*     */       
/* 132 */       if (type.isCrystalline() && !TO_CRYSTAL_BLACKLIST.contains(name) && !configToCrystalBlacklist.contains(name)) {
/* 133 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 134 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 135 */         if (oredict.contains(dustOredict)) {
/* 136 */           helper.registerImplosionCompressorRecipe(miscHelper
/* 137 */               .getRecipeKey("techreborn.dust_to_material", name), new Object[] { dustOredict, 
/*     */                 
/* 139 */                 Integer.valueOf(4), Blocks.TNT, Integer.valueOf(16) }, new Object[] { materialOredict, 
/*     */                 
/* 141 */                 Integer.valueOf(3), darkAshes, Integer.valueOf(12) }, 20, 32);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsTechRebornItem(String oredict) {
/* 149 */     return OreDictionary.getOres(oredict, false).stream()
/* 150 */       .anyMatch(s -> s.getItem().getRegistryName().getNamespace().equals("techreborn"));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
