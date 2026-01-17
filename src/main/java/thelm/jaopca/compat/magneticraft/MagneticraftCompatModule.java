/*     */ package thelm.jaopca.compat.magneticraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraftforge.fml.common.Loader;
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
/*     */ @JAOPCAModule(modDependencies = {"magneticraft"}, classDependencies = {"com.cout970.magneticraft.api.registries.machines.grinder.IGrinderRecipeManager"})
/*     */ public class MagneticraftCompatModule
/*     */   implements IModule
/*     */ {
/*  26 */   private static final Set<String> BLOCK_PLATE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Tungsten" }));
/*     */   
/*  28 */   private static final Set<String> TO_DUST_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Cobalt", "Copper", "Gold", "Iron", "Lead", "Mithril", "Nickel", "Osmium", "Silver", "Steel", "Tin", "Tungsten", "Zinc" }));
/*     */ 
/*     */   
/*  31 */   private static final Set<String> HEAVY_PLATE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Steel", "Tungsten" }));
/*     */   
/*  33 */   private static final Set<String> TO_PLATE_BLACKLIST = new TreeSet<>();
/*  34 */   private static Set<String> configBlockToLightPlateBlacklist = new TreeSet<>();
/*  35 */   private static Set<String> configToDustBlacklist = new TreeSet<>();
/*  36 */   private static Set<String> configToHeavyPlateBlacklist = new TreeSet<>();
/*  37 */   private static Set<String> configMaterialToLightPlateBlacklist = new TreeSet<>();
/*  38 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*     */   
/*     */   static {
/*  41 */     if (Loader.isModLoaded("thermalfoundation")) {
/*  42 */       Collections.addAll(TO_PLATE_BLACKLIST, new String[] { "Aluminium", "Aluminum", "Constantan", "Copper", "Electrum", "Enderium", "Gold", "Invar", "Iridium", "Iron", "Lead", "Lumium", "Mithril", "Nickel", "Platinum", "Signalum", "Steel", "Tin" });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  50 */     return "magneticraft_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  55 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  60 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  61 */     miscHelper.caclulateMaterialSet(config
/*  62 */         .getDefinedStringList("recipes.blockToLightPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  63 */           .configMaterialPredicate(), "The materials that should not have crushing table to light plate recipes added."), configBlockToLightPlateBlacklist);
/*     */     
/*  65 */     miscHelper.caclulateMaterialSet(config
/*  66 */         .getDefinedStringList("recipes.toDustMaterialBlacklist", new ArrayList(), miscHelper
/*  67 */           .configMaterialPredicate(), "The materials that should not have grinder to dust recipes added."), configToDustBlacklist);
/*     */     
/*  69 */     miscHelper.caclulateMaterialSet(config
/*  70 */         .getDefinedStringList("recipes.toHeavyPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  71 */           .configMaterialPredicate(), "The materials that should not have hydraulic press to heavy plate recipes added."), configToHeavyPlateBlacklist);
/*     */     
/*  73 */     miscHelper.caclulateMaterialSet(config
/*  74 */         .getDefinedStringList("recipes.materialToLightPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  75 */           .configMaterialPredicate(), "The materials that should not have hydraulic press to light plate recipes added."), configMaterialToLightPlateBlacklist);
/*     */     
/*  77 */     miscHelper.caclulateMaterialSet(config
/*  78 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  79 */           .configMaterialPredicate(), "The materials that should not have hydraulic press to plate recipes added."), configToPlateBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  85 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  86 */     MagneticraftHelper helper = MagneticraftHelper.INSTANCE;
/*  87 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  88 */     Set<String> oredict = apiImpl.getOredict();
/*  89 */     for (IMaterial material : moduleData.getMaterials()) {
/*  90 */       MaterialType type = material.getType();
/*  91 */       String name = material.getName();
/*  92 */       if (type.isIngot() && !BLOCK_PLATE_BLACKLIST.contains(name) && !configBlockToLightPlateBlacklist.contains(name)) {
/*  93 */         String blockOredict = miscHelper.getOredictName("block", name);
/*  94 */         String lightPlateOredict = miscHelper.getOredictName("lightPlate", name);
/*  95 */         if (oredict.contains(blockOredict) && oredict.contains(lightPlateOredict)) {
/*  96 */           helper.registerCrushingTableRecipe(miscHelper
/*  97 */               .getRecipeKey("magneticraft.block_to_light_plate", name), blockOredict, lightPlateOredict, 
/*  98 */               material.isSmallStorageBlock() ? 2 : 5);
/*     */         }
/*     */       } 
/* 101 */       if (type.isIngot() && !TO_DUST_BLACKLIST.contains(name) && !configToDustBlacklist.contains(name)) {
/* 102 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 103 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 104 */         if (oredict.contains(dustOredict)) {
/* 105 */           helper.registerGrinderRecipe(miscHelper
/* 106 */               .getRecipeKey("magneticraft.material_to_dust", name), materialOredict, dustOredict, 1, 50.0F);
/*     */         }
/*     */       } 
/*     */       
/* 110 */       if (type.isIngot() && !HEAVY_PLATE_BLACKLIST.contains(name) && !configToHeavyPlateBlacklist.contains(name)) {
/* 111 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 112 */         String heavyPlateOredict = miscHelper.getOredictName("heavyPlate", name);
/* 113 */         if (oredict.contains(heavyPlateOredict)) {
/* 114 */           helper.registerHydraulicPressRecipe(miscHelper
/* 115 */               .getRecipeKey("magneticraft.material_to_heavy_plate", name), materialOredict, 4, heavyPlateOredict, 1, 100.0F, 2);
/*     */         }
/*     */       } 
/*     */       
/* 119 */       if (type.isIngot() && !HEAVY_PLATE_BLACKLIST.contains(name) && !configMaterialToLightPlateBlacklist.contains(name)) {
/* 120 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 121 */         String lightPlateOredict = miscHelper.getOredictName("lightPlate", name);
/* 122 */         if (oredict.contains(lightPlateOredict)) {
/* 123 */           helper.registerHydraulicPressRecipe(miscHelper
/* 124 */               .getRecipeKey("magneticraft.material_to_light_plate", name), materialOredict, 1, lightPlateOredict, 1, 100.0F, 1);
/*     */         }
/*     */       } 
/*     */       
/* 128 */       if (type.isIngot() && !TO_PLATE_BLACKLIST.contains(name) && !configToPlateBlacklist.contains(name)) {
/* 129 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 130 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 131 */         if (oredict.contains(plateOredict))
/* 132 */           helper.registerHydraulicPressRecipe(miscHelper
/* 133 */               .getRecipeKey("magneticraft.material_to_plate", name), materialOredict, 1, plateOredict, 1, 80.0F, 0); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\MagneticraftCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */