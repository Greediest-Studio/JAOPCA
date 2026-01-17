/*     */ package thelm.jaopca.compat.magneticraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.compat.tconstruct.TConstructHelper;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"magneticraft", "tconstruct"}, classDependencies = {"com.cout970.magneticraft.api.registries.machines.grinder.IGrinderRecipeManager"})
/*     */ public class MagneticraftTConstructModule
/*     */   implements IModule
/*     */ {
/*  28 */   private static final Set<String> CHUNK_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Cobalt", "Copper", "Galena", "Gold", "Iron", "Lead", "Mithril", "Nickel", "Osmium", "Silver", "Tin", "Tungsten", "Zinc" }));
/*     */ 
/*     */   
/*  31 */   private static final Set<String> HEAVY_PLATE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Steel", "Tungsten" }));
/*     */   
/*  33 */   private static Set<String> configRockyChunkToMoltenBlacklist = new TreeSet<>();
/*  34 */   private static Set<String> configChunkToMoltenBlacklist = new TreeSet<>();
/*  35 */   private static Set<String> configHeavyPlateToMoltenBlacklist = new TreeSet<>();
/*     */   
/*     */   private static boolean jaopcaOnly = false;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  41 */     return "magneticraft_tconstruct";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  46 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  51 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  52 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/*  53 */     miscHelper.caclulateMaterialSet(config
/*  54 */         .getDefinedStringList("recipes.rockyChunkToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  55 */           .configMaterialPredicate(), "The materials that should not have rock chunk melting recipes added."), configRockyChunkToMoltenBlacklist);
/*     */     
/*  57 */     miscHelper.caclulateMaterialSet(config
/*  58 */         .getDefinedStringList("recipes.chunkToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  59 */           .configMaterialPredicate(), "The materials that should not have chunk melting recipes added."), configChunkToMoltenBlacklist);
/*     */     
/*  61 */     miscHelper.caclulateMaterialSet(config
/*  62 */         .getDefinedStringList("recipes.heavyPlateToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  63 */           .configMaterialPredicate(), "The materials that should not have heavy plate melting recipes added."), configHeavyPlateToMoltenBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  69 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  70 */     TConstructHelper helper = TConstructHelper.INSTANCE;
/*  71 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  72 */     Set<String> oredict = apiImpl.getOredict();
/*  73 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/*  74 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack) - 300;
/*  75 */     for (IMaterial material : moduleData.getMaterials()) {
/*  76 */       MaterialType type = material.getType();
/*  77 */       String name = material.getName();
/*  78 */       if (type.isIngot() && (!jaopcaOnly || moltenMaterials.contains(material))) {
/*  79 */         String moltenName = miscHelper.getFluidName("", name);
/*  80 */         if (FluidRegistry.isFluidRegistered(moltenName)) {
/*  81 */           if (type == MaterialType.INGOT && !CHUNK_BLACKLIST.contains(name) && !configRockyChunkToMoltenBlacklist.contains(name)) {
/*  82 */             String rockyChunkOredict = miscHelper.getOredictName("rockyChunk", name);
/*  83 */             if (oredict.contains(rockyChunkOredict)) {
/*  84 */               helper.registerMeltingRecipe(miscHelper
/*  85 */                   .getRecipeKey("magneticraft_tconstruct.rocky_chunk_to_molten", name), rockyChunkOredict, moltenName, 288, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/*  89 */           if (type == MaterialType.INGOT && !CHUNK_BLACKLIST.contains(name) && !configChunkToMoltenBlacklist.contains(name)) {
/*  90 */             String chunkOredict = miscHelper.getOredictName("chunk", name);
/*  91 */             if (oredict.contains(chunkOredict)) {
/*  92 */               helper.registerMeltingRecipe(miscHelper
/*  93 */                   .getRecipeKey("magneticraft_tconstruct.chunk_to_molten", name), chunkOredict, moltenName, 288, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/*  97 */           if (!HEAVY_PLATE_BLACKLIST.contains(name) && !configHeavyPlateToMoltenBlacklist.contains(name)) {
/*  98 */             String heavyPlateOredict = miscHelper.getOredictName("heavyPlate", name);
/*  99 */             if (oredict.contains(heavyPlateOredict))
/* 100 */               helper.registerMeltingRecipe(miscHelper
/* 101 */                   .getRecipeKey("magneticraft_tconstruct.heavy_plate_to_molten", name), heavyPlateOredict, moltenName, 576, tempFunction); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\MagneticraftTConstructModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */