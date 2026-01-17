/*     */ package thelm.jaopca.compat.embers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import teamroots.embers.RegistryManager;
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
/*     */ @JAOPCAModule(modDependencies = {"embers@[1,)"})
/*     */ public class EmbersCompatModule
/*     */   implements IModule
/*     */ {
/*  27 */   public static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Bronze", "Copper", "Dawnstone", "Electrum", "Gold", "Iron", "Lead", "Nickel", "Silver", "Tin" }));
/*     */ 
/*     */   
/*  30 */   private static Set<String> configMaterialToMoltenBlacklist = new TreeSet<>();
/*  31 */   private static Set<String> configNuggetToMoltenBlacklist = new TreeSet<>();
/*  32 */   private static Set<String> configPlateToMoltenBlacklist = new TreeSet<>();
/*  33 */   private static Set<String> configToMaterialBlacklist = new TreeSet<>();
/*  34 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*     */   
/*     */   private static boolean jaopcaOnly = false;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  40 */     return "embers_compat";
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
/*  51 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/*  52 */     miscHelper.caclulateMaterialSet(config
/*  53 */         .getDefinedStringList("recipes.materialToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  54 */           .configMaterialPredicate(), "The materials that should not have material melting recipes added."), configMaterialToMoltenBlacklist);
/*     */     
/*  56 */     miscHelper.caclulateMaterialSet(config
/*  57 */         .getDefinedStringList("recipes.nuggetToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  58 */           .configMaterialPredicate(), "The materials that should not have nugget melting recipes added."), configNuggetToMoltenBlacklist);
/*     */     
/*  60 */     miscHelper.caclulateMaterialSet(config
/*  61 */         .getDefinedStringList("recipes.plateToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  62 */           .configMaterialPredicate(), "The materials that should not have plate melting recipes added."), configPlateToMoltenBlacklist);
/*     */     
/*  64 */     miscHelper.caclulateMaterialSet(config
/*  65 */         .getDefinedStringList("recipes.toMaterialMaterialBlacklist", new ArrayList(), miscHelper
/*  66 */           .configMaterialPredicate(), "The materials that should not have material stamping recipes added."), configToMaterialBlacklist);
/*     */     
/*  68 */     miscHelper.caclulateMaterialSet(config
/*  69 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  70 */           .configMaterialPredicate(), "The materials that should not have plate stamping recipes added."), configToPlateBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  76 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  77 */     EmbersHelper helper = EmbersHelper.INSTANCE;
/*  78 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  79 */     Set<String> oredict = apiImpl.getOredict();
/*  80 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/*  81 */     Item ingotStamp = RegistryManager.stamp_bar;
/*  82 */     Item plateStamp = RegistryManager.stamp_plate;
/*  83 */     for (IMaterial material : moduleData.getMaterials()) {
/*  84 */       MaterialType type = material.getType();
/*  85 */       String name = material.getName();
/*  86 */       if (type.isIngot() && !BLACKLIST.contains(name) && (!jaopcaOnly || moltenMaterials.contains(material))) {
/*  87 */         String moltenName = miscHelper.getFluidName("", name);
/*  88 */         if (FluidRegistry.isFluidRegistered(moltenName)) {
/*  89 */           if (!configMaterialToMoltenBlacklist.contains(name)) {
/*  90 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/*  91 */             helper.registerMeltingRecipe(miscHelper
/*  92 */                 .getRecipeKey("embers.material_to_molten", name), materialOredict, moltenName, 144);
/*     */           } 
/*     */           
/*  95 */           if (!configNuggetToMoltenBlacklist.contains(name)) {
/*  96 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/*  97 */             if (oredict.contains(nuggetOredict)) {
/*  98 */               helper.registerMeltingRecipe(miscHelper
/*  99 */                   .getRecipeKey("embers.nugget_to_molten", name), nuggetOredict, moltenName, 16);
/*     */             }
/*     */           } 
/*     */           
/* 103 */           if (!configPlateToMoltenBlacklist.contains(name)) {
/* 104 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 105 */             if (oredict.contains(plateOredict)) {
/* 106 */               helper.registerMeltingRecipe(miscHelper
/* 107 */                   .getRecipeKey("embers.plate_to_molten", name), plateOredict, moltenName, 144);
/*     */             }
/*     */           } 
/*     */           
/* 111 */           if (!configToMaterialBlacklist.contains(name)) {
/* 112 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 113 */             helper.registerStampingRecipe(miscHelper
/* 114 */                 .getRecipeKey("embers.molten_to_material", name), null, moltenName, 144, ingotStamp, materialOredict, 1);
/*     */           } 
/*     */           
/* 117 */           if (!configToPlateBlacklist.contains(name)) {
/* 118 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 119 */             if (oredict.contains(plateOredict))
/* 120 */               helper.registerStampingRecipe(miscHelper
/* 121 */                   .getRecipeKey("tconstruct.molten_to_plate", name), null, moltenName, 144, plateStamp, plateOredict, 1); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\embers\EmbersCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */