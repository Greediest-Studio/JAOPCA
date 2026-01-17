/*     */ package thelm.jaopca.compat.tconstruct;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import slimeknights.tconstruct.library.MaterialIntegration;
/*     */ import slimeknights.tconstruct.library.TinkerRegistry;
/*     */ import slimeknights.tconstruct.smeltery.TinkerSmeltery;
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
/*     */ @JAOPCAModule(modDependencies = {"tconstruct"})
/*     */ public class TConstructCompatModule
/*     */   implements IModule
/*     */ {
/*  32 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*  33 */   private static Set<String> configMaterialToMoltenBlacklist = new TreeSet<>();
/*  34 */   private static Set<String> configBlockToMoltenBlacklist = new TreeSet<>();
/*  35 */   private static Set<String> configNuggetToMoltenBlacklist = new TreeSet<>();
/*  36 */   private static Set<String> configDustToMoltenBlacklist = new TreeSet<>();
/*  37 */   private static Set<String> configPlateToMoltenBlacklist = new TreeSet<>();
/*  38 */   private static Set<String> configGearToMoltenBlacklist = new TreeSet<>();
/*  39 */   private static Set<String> configToMaterialBlacklist = new TreeSet<>();
/*  40 */   private static Set<String> configToBlockBlacklist = new TreeSet<>();
/*  41 */   private static Set<String> configToNuggetBlacklist = new TreeSet<>();
/*  42 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*  43 */   private static Set<String> configToGearBlacklist = new TreeSet<>();
/*  44 */   private static Set<String> configMaterialCastBlacklist = new TreeSet<>();
/*  45 */   private static Set<String> configNuggetCastBlacklist = new TreeSet<>();
/*  46 */   private static Set<String> configPlateCastBlacklist = new TreeSet<>();
/*  47 */   private static Set<String> configGearCastBlacklist = new TreeSet<>();
/*     */   
/*     */   private static boolean jaopcaOnly = true;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  53 */     return "tconstruct_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  58 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  63 */     TinkerRegistry.getMaterialIntegrations().stream().filter(mi -> (mi.fluid != null))
/*  64 */       .map(mi -> mi.oreSuffix).filter(StringUtils::isNotEmpty).forEach(BLACKLIST::add);
/*  65 */     BLACKLIST.add("Emerald");
/*  66 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  67 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/*  68 */     miscHelper.caclulateMaterialSet(config
/*  69 */         .getDefinedStringList("recipes.materialToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  70 */           .configMaterialPredicate(), "The materials that should not have material melting recipes added."), configMaterialToMoltenBlacklist);
/*     */     
/*  72 */     miscHelper.caclulateMaterialSet(config
/*  73 */         .getDefinedStringList("recipes.blockToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  74 */           .configMaterialPredicate(), "The materials that should not have block melting recipes added."), configBlockToMoltenBlacklist);
/*     */     
/*  76 */     miscHelper.caclulateMaterialSet(config
/*  77 */         .getDefinedStringList("recipes.nuggetToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  78 */           .configMaterialPredicate(), "The materials that should not have nugget melting recipes added."), configNuggetToMoltenBlacklist);
/*     */     
/*  80 */     miscHelper.caclulateMaterialSet(config
/*  81 */         .getDefinedStringList("recipes.dustToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  82 */           .configMaterialPredicate(), "The materials that should not have dust melting recipes added."), configDustToMoltenBlacklist);
/*     */     
/*  84 */     miscHelper.caclulateMaterialSet(config
/*  85 */         .getDefinedStringList("recipes.plateToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  86 */           .configMaterialPredicate(), "The materials that should not have plate melting recipes added."), configPlateToMoltenBlacklist);
/*     */     
/*  88 */     miscHelper.caclulateMaterialSet(config
/*  89 */         .getDefinedStringList("recipes.gearToMoltenMaterialBlacklist", new ArrayList(), miscHelper
/*  90 */           .configMaterialPredicate(), "The materials that should not have gear melting recipes added."), configGearToMoltenBlacklist);
/*     */     
/*  92 */     miscHelper.caclulateMaterialSet(config
/*  93 */         .getDefinedStringList("recipes.toMaterialMaterialBlacklist", new ArrayList(), miscHelper
/*  94 */           .configMaterialPredicate(), "The materials that should not have material casting recipes added."), configToMaterialBlacklist);
/*     */     
/*  96 */     miscHelper.caclulateMaterialSet(config
/*  97 */         .getDefinedStringList("recipes.toBlockMaterialBlacklist", new ArrayList(), miscHelper
/*  98 */           .configMaterialPredicate(), "The materials that should not have block casting recipes added."), configToBlockBlacklist);
/*     */     
/* 100 */     miscHelper.caclulateMaterialSet(config
/* 101 */         .getDefinedStringList("recipes.toNuggetMaterialBlacklist", new ArrayList(), miscHelper
/* 102 */           .configMaterialPredicate(), "The materials that should not have nugget casting recipes added."), configToNuggetBlacklist);
/*     */     
/* 104 */     miscHelper.caclulateMaterialSet(config
/* 105 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 106 */           .configMaterialPredicate(), "The materials that should not have plate casting recipes added."), configToPlateBlacklist);
/*     */     
/* 108 */     miscHelper.caclulateMaterialSet(config
/* 109 */         .getDefinedStringList("recipes.toGearMaterialBlacklist", new ArrayList(), miscHelper
/* 110 */           .configMaterialPredicate(), "The materials that should not have gear casting recipes added."), configToGearBlacklist);
/*     */     
/* 112 */     miscHelper.caclulateMaterialSet(config
/* 113 */         .getDefinedStringList("recipes.materialCastMaterialBlacklist", new ArrayList(), miscHelper
/* 114 */           .configMaterialPredicate(), "The materials that should not have material cast recipes added."), configMaterialCastBlacklist);
/*     */     
/* 116 */     miscHelper.caclulateMaterialSet(config
/* 117 */         .getDefinedStringList("recipes.nuggetCastMaterialBlacklist", new ArrayList(), miscHelper
/* 118 */           .configMaterialPredicate(), "The materials that should not have nugget cast recipes added."), configNuggetCastBlacklist);
/*     */     
/* 120 */     miscHelper.caclulateMaterialSet(config
/* 121 */         .getDefinedStringList("recipes.materialCastMaterialBlacklist", new ArrayList(), miscHelper
/* 122 */           .configMaterialPredicate(), "The materials that should not have plate cast recipes added."), configPlateCastBlacklist);
/*     */     
/* 124 */     miscHelper.caclulateMaterialSet(config
/* 125 */         .getDefinedStringList("recipes.materialCastMaterialBlacklist", new ArrayList(), miscHelper
/* 126 */           .configMaterialPredicate(), "The materials that should not have gear cast recipes added."), configGearCastBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 132 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 133 */     TConstructHelper helper = TConstructHelper.INSTANCE;
/* 134 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 135 */     Set<String> oredict = apiImpl.getOredict();
/* 136 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/* 137 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack) - 300;
/* 138 */     ItemStack ingotCast = TinkerSmeltery.castIngot;
/* 139 */     ItemStack gemCast = TinkerSmeltery.castGem;
/* 140 */     ItemStack nuggetCast = TinkerSmeltery.castNugget;
/* 141 */     ItemStack plateCast = TinkerSmeltery.castPlate;
/* 142 */     ItemStack gearCast = TinkerSmeltery.castGear;
/* 143 */     List<FluidStack> castFluids = TinkerSmeltery.castCreationFluids;
/* 144 */     for (IMaterial material : moduleData.getMaterials()) {
/* 145 */       MaterialType type = material.getType();
/* 146 */       String name = material.getName();
/* 147 */       if (!type.isDust() && !BLACKLIST.contains(name) && (!jaopcaOnly || moltenMaterials.contains(material))) {
/* 148 */         String moltenName = miscHelper.getFluidName("", name);
/* 149 */         int baseAmount = material.getType().isIngot() ? 144 : 666;
/* 150 */         if (FluidRegistry.isFluidRegistered(moltenName)) {
/* 151 */           if (!configMaterialToMoltenBlacklist.contains(name)) {
/* 152 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 153 */             helper.registerMeltingRecipe(miscHelper
/* 154 */                 .getRecipeKey("tconstruct.material_to_molten", name), materialOredict, moltenName, baseAmount, tempFunction);
/*     */           } 
/*     */           
/* 157 */           if (!configBlockToMoltenBlacklist.contains(name)) {
/* 158 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 159 */             if (oredict.contains(blockOredict)) {
/* 160 */               helper.registerMeltingRecipe(miscHelper
/* 161 */                   .getRecipeKey("tconstruct.block_to_molten", name), blockOredict, moltenName, baseAmount * (
/* 162 */                   material.isSmallStorageBlock() ? 4 : 9), tempFunction);
/*     */             }
/*     */           } 
/* 165 */           if (!configNuggetToMoltenBlacklist.contains(name)) {
/* 166 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 167 */             if (oredict.contains(nuggetOredict)) {
/* 168 */               helper.registerMeltingRecipe(miscHelper
/* 169 */                   .getRecipeKey("tconstruct.nugget_to_molten", name), nuggetOredict, moltenName, baseAmount / 9, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/* 173 */           if (!configDustToMoltenBlacklist.contains(name)) {
/* 174 */             String dustOredict = miscHelper.getOredictName("dust", name);
/* 175 */             if (oredict.contains(dustOredict)) {
/* 176 */               helper.registerMeltingRecipe(miscHelper
/* 177 */                   .getRecipeKey("tconstruct.dust_to_molten", name), dustOredict, moltenName, baseAmount, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/* 181 */           if (!configPlateToMoltenBlacklist.contains(name)) {
/* 182 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 183 */             if (oredict.contains(plateOredict)) {
/* 184 */               helper.registerMeltingRecipe(miscHelper
/* 185 */                   .getRecipeKey("tconstruct.plate_to_molten", name), plateOredict, moltenName, baseAmount, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/* 189 */           if (!configGearToMoltenBlacklist.contains(name)) {
/* 190 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 191 */             if (oredict.contains(gearOredict)) {
/* 192 */               helper.registerMeltingRecipe(miscHelper
/* 193 */                   .getRecipeKey("tconstruct.gear_to_molten", name), gearOredict, moltenName, baseAmount * 4, tempFunction);
/*     */             }
/*     */           } 
/*     */           
/* 197 */           if (!configToMaterialBlacklist.contains(name)) {
/* 198 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 199 */             helper.registerTableCastingRecipe(miscHelper
/* 200 */                 .getRecipeKey("tconstruct.molten_to_material", name), 
/* 201 */                 type.isIngot() ? ingotCast : gemCast, moltenName, baseAmount, materialOredict, tempFunction, false, false);
/*     */           } 
/*     */           
/* 204 */           if (!configToBlockBlacklist.contains(name)) {
/* 205 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 206 */             if (oredict.contains(blockOredict)) {
/* 207 */               helper.registerBasinCastingRecipe(miscHelper
/* 208 */                   .getRecipeKey("tconstruct.molten_to_block", name), null, moltenName, baseAmount * (
/* 209 */                   material.isSmallStorageBlock() ? 4 : 9), blockOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */           
/* 213 */           if (!configToNuggetBlacklist.contains(name)) {
/* 214 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 215 */             if (oredict.contains(nuggetOredict)) {
/* 216 */               helper.registerTableCastingRecipe(miscHelper
/* 217 */                   .getRecipeKey("tconstruct.molten_to_nugget", name), nuggetCast, moltenName, baseAmount / 9, nuggetOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 222 */           if (!configToPlateBlacklist.contains(name)) {
/* 223 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 224 */             if (oredict.contains(plateOredict)) {
/* 225 */               helper.registerTableCastingRecipe(miscHelper
/* 226 */                   .getRecipeKey("tconstruct.molten_to_plate", name), plateCast, moltenName, baseAmount, plateOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 231 */           if (!configToGearBlacklist.contains(name)) {
/* 232 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 233 */             if (oredict.contains(gearOredict)) {
/* 234 */               helper.registerTableCastingRecipe(miscHelper
/* 235 */                   .getRecipeKey("tconstruct.molten_to_gear", name), gearCast, moltenName, baseAmount * 4, gearOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 242 */       if (!type.isDust() && !BLACKLIST.contains(name) && !configMaterialCastBlacklist.contains(name)) {
/* 243 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 244 */         if (type.isIngot()) {
/* 245 */           int i = 0;
/* 246 */           for (FluidStack stack : castFluids) {
/* 247 */             helper.registerTableCastingRecipe(miscHelper
/* 248 */                 .getRecipeKey("tconstruct.material_cast_" + i++, name), materialOredict, stack, stack.amount, ingotCast, tempFunction, true, true);
/*     */           
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 254 */           int i = 0;
/* 255 */           for (FluidStack stack : castFluids) {
/* 256 */             helper.registerTableCastingRecipe(miscHelper
/* 257 */                 .getRecipeKey("tconstruct.material_cast_" + i++, name), materialOredict, stack, stack.amount, gemCast, tempFunction, true, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 263 */       if (!type.isDust() && !BLACKLIST.contains(name) && !configNuggetCastBlacklist.contains(name)) {
/* 264 */         String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 265 */         if (oredict.contains(nuggetOredict)) {
/* 266 */           int i = 0;
/* 267 */           for (FluidStack stack : castFluids) {
/* 268 */             helper.registerTableCastingRecipe(miscHelper
/* 269 */                 .getRecipeKey("tconstruct.nugget_cast_" + i++, name), nuggetOredict, stack, stack.amount, nuggetCast, tempFunction, true, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 275 */       if (!type.isDust() && !BLACKLIST.contains(name) && !configPlateCastBlacklist.contains(name)) {
/* 276 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 277 */         if (oredict.contains(plateOredict)) {
/* 278 */           int i = 0;
/* 279 */           for (FluidStack stack : castFluids) {
/* 280 */             helper.registerTableCastingRecipe(miscHelper
/* 281 */                 .getRecipeKey("tconstruct.plate_cast_" + i++, name), plateOredict, stack, stack.amount, plateCast, tempFunction, true, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 287 */       if (!type.isDust() && !BLACKLIST.contains(name) && !configGearCastBlacklist.contains(name)) {
/* 288 */         String gearOredict = miscHelper.getOredictName("gear", name);
/* 289 */         if (oredict.contains(gearOredict)) {
/* 290 */           int i = 0;
/* 291 */           for (FluidStack stack : castFluids)
/* 292 */             helper.registerTableCastingRecipe(miscHelper
/* 293 */                 .getRecipeKey("tconstruct.gear_cast_" + i++, name), gearOredict, stack, stack.amount, gearCast, tempFunction, true, true); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\tconstruct\TConstructCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */