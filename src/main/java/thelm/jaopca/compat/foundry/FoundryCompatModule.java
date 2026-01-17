/*     */ package thelm.jaopca.compat.foundry;
/*     */ 
/*     */ import exter.foundry.api.FoundryAPI;
/*     */ import exter.foundry.fluid.LiquidMetalRegistry;
/*     */ import exter.foundry.item.FoundryItems;
/*     */ import exter.foundry.item.ItemMold;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"foundry@[3,)"})
/*     */ public class FoundryCompatModule
/*     */   implements IModule
/*     */ {
/*  35 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  37 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*  38 */   private static Set<String> configMaterialToLiquidBlacklist = new TreeSet<>();
/*  39 */   private static Set<String> configBlockToLiquidBlacklist = new TreeSet<>();
/*  40 */   private static Set<String> configNuggetToLiquidBlacklist = new TreeSet<>();
/*  41 */   private static Set<String> configDustToLiquidBlacklist = new TreeSet<>();
/*  42 */   private static Set<String> configTinyDustToLiquidBlacklist = new TreeSet<>();
/*  43 */   private static Set<String> configSmallDustToLiquidBlacklist = new TreeSet<>();
/*  44 */   private static Set<String> configPlateToLiquidBlacklist = new TreeSet<>();
/*  45 */   private static Set<String> configGearToLiquidBlacklist = new TreeSet<>();
/*  46 */   private static Set<String> configToMaterialBlacklist = new TreeSet<>();
/*  47 */   private static Set<String> configTableToMaterialBlacklist = new TreeSet<>();
/*  48 */   private static Set<String> configToBlockBlacklist = new TreeSet<>();
/*  49 */   private static Set<String> configTableToBlockBlacklist = new TreeSet<>();
/*  50 */   private static Set<String> configToNuggetBlacklist = new TreeSet<>();
/*  51 */   private static Set<String> configToDustBlacklist = new TreeSet<>();
/*  52 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*  53 */   private static Set<String> configTableToPlateBlacklist = new TreeSet<>();
/*  54 */   private static Set<String> configToGearBlacklist = new TreeSet<>();
/*  55 */   private static Set<String> configToRodBlacklist = new TreeSet<>();
/*  56 */   private static Set<String> configTableToRodBlacklist = new TreeSet<>();
/*  57 */   private static Set<String> configMoltenToMaterialBlacklist = new TreeSet<>();
/*  58 */   private static Set<String> configTableMoltenToMaterialBlacklist = new TreeSet<>();
/*  59 */   private static Set<String> configMoltenToBlockBlacklist = new TreeSet<>();
/*  60 */   private static Set<String> configTableMoltenToBlockBlacklist = new TreeSet<>();
/*  61 */   private static Set<String> configMoltenToNuggetBlacklist = new TreeSet<>();
/*  62 */   private static Set<String> configMoltenToDustBlacklist = new TreeSet<>();
/*  63 */   private static Set<String> configMoltenToPlateBlacklist = new TreeSet<>();
/*  64 */   private static Set<String> configTableMoltenToPlateBlacklist = new TreeSet<>();
/*  65 */   private static Set<String> configMoltenToGearBlacklist = new TreeSet<>();
/*  66 */   private static Set<String> configMoltenToRodBlacklist = new TreeSet<>();
/*  67 */   private static Set<String> configTableMoltenToRodBlacklist = new TreeSet<>();
/*     */   
/*     */   private static boolean jaopcaOnly = false;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  73 */     return "foundry_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  78 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  83 */     BLACKLIST.addAll(LiquidMetalRegistry.INSTANCE.getFluidNames());
/*  84 */     Collections.addAll(BLACKLIST, new String[] { "Aluminum", "Constantan" });
/*  85 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  86 */     miscHelper.caclulateMaterialSet(config
/*  87 */         .getDefinedStringList("recipes.materialToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/*  88 */           .configMaterialPredicate(), "The materials that should not have material melting recipes added."), configMaterialToLiquidBlacklist);
/*     */     
/*  90 */     miscHelper.caclulateMaterialSet(config
/*  91 */         .getDefinedStringList("recipes.blockToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/*  92 */           .configMaterialPredicate(), "The materials that should not have block melting recipes added."), configBlockToLiquidBlacklist);
/*     */     
/*  94 */     miscHelper.caclulateMaterialSet(config
/*  95 */         .getDefinedStringList("recipes.nuggetToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/*  96 */           .configMaterialPredicate(), "The materials that should not have nugget melting recipes added."), configNuggetToLiquidBlacklist);
/*     */     
/*  98 */     miscHelper.caclulateMaterialSet(config
/*  99 */         .getDefinedStringList("recipes.dustToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/* 100 */           .configMaterialPredicate(), "The materials that should not have dust melting recipes added."), configDustToLiquidBlacklist);
/*     */     
/* 102 */     miscHelper.caclulateMaterialSet(config
/* 103 */         .getDefinedStringList("recipes.tinyDustToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/* 104 */           .configMaterialPredicate(), "The materials that should not have tiny dust melting recipes added."), configTinyDustToLiquidBlacklist);
/*     */     
/* 106 */     miscHelper.caclulateMaterialSet(config
/* 107 */         .getDefinedStringList("recipes.smallDustToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/* 108 */           .configMaterialPredicate(), "The materials that should not have small dust melting recipes added."), configSmallDustToLiquidBlacklist);
/*     */     
/* 110 */     miscHelper.caclulateMaterialSet(config
/* 111 */         .getDefinedStringList("recipes.plateToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/* 112 */           .configMaterialPredicate(), "The materials that should not have plate melting recipes added."), configPlateToLiquidBlacklist);
/*     */     
/* 114 */     miscHelper.caclulateMaterialSet(config
/* 115 */         .getDefinedStringList("recipes.gearToLiquidMaterialBlacklist", new ArrayList(), miscHelper
/* 116 */           .configMaterialPredicate(), "The materials that should not have gear melting recipes added."), configGearToLiquidBlacklist);
/*     */     
/* 118 */     miscHelper.caclulateMaterialSet(config
/* 119 */         .getDefinedStringList("recipes.toMaterialMaterialBlacklist", new ArrayList(), miscHelper
/* 120 */           .configMaterialPredicate(), "The materials that should not have material casting recipes added."), configToMaterialBlacklist);
/*     */     
/* 122 */     miscHelper.caclulateMaterialSet(config
/* 123 */         .getDefinedStringList("recipes.tableToMaterialMaterialBlacklist", new ArrayList(), miscHelper
/* 124 */           .configMaterialPredicate(), "The materials that should not have material casting table recipes added."), configTableToMaterialBlacklist);
/*     */     
/* 126 */     miscHelper.caclulateMaterialSet(config
/* 127 */         .getDefinedStringList("recipes.toBlockMaterialBlacklist", new ArrayList(), miscHelper
/* 128 */           .configMaterialPredicate(), "The materials that should not have block casting recipes added."), configToBlockBlacklist);
/*     */     
/* 130 */     miscHelper.caclulateMaterialSet(config
/* 131 */         .getDefinedStringList("recipes.tableToBlockMaterialBlacklist", new ArrayList(), miscHelper
/* 132 */           .configMaterialPredicate(), "The materials that should not have block casting table recipes added."), configTableToBlockBlacklist);
/*     */     
/* 134 */     miscHelper.caclulateMaterialSet(config
/* 135 */         .getDefinedStringList("recipes.toNuggetMaterialBlacklist", new ArrayList(), miscHelper
/* 136 */           .configMaterialPredicate(), "The materials that should not have nugget casting recipes added."), configToNuggetBlacklist);
/*     */     
/* 138 */     miscHelper.caclulateMaterialSet(config
/* 139 */         .getDefinedStringList("recipes.toDustMaterialBlacklist", new ArrayList(), miscHelper
/* 140 */           .configMaterialPredicate(), "The materials that should not have atomizer to dust recipes added."), configToDustBlacklist);
/*     */     
/* 142 */     miscHelper.caclulateMaterialSet(config
/* 143 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 144 */           .configMaterialPredicate(), "The materials that should not have plate casting recipes added."), configToPlateBlacklist);
/*     */     
/* 146 */     miscHelper.caclulateMaterialSet(config
/* 147 */         .getDefinedStringList("recipes.tableToPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 148 */           .configMaterialPredicate(), "The materials that should not have plate casting table recipes added."), configTableToPlateBlacklist);
/*     */     
/* 150 */     miscHelper.caclulateMaterialSet(config
/* 151 */         .getDefinedStringList("recipes.toGearMaterialBlacklist", new ArrayList(), miscHelper
/* 152 */           .configMaterialPredicate(), "The materials that should not have gear casting recipes added."), configToGearBlacklist);
/*     */     
/* 154 */     miscHelper.caclulateMaterialSet(config
/* 155 */         .getDefinedStringList("recipes.tableToRodMaterialBlacklist", new ArrayList(), miscHelper
/* 156 */           .configMaterialPredicate(), "The materials that should not have rod casting table recipes added."), configTableToRodBlacklist);
/*     */     
/* 158 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/* 159 */     miscHelper.caclulateMaterialSet(config
/* 160 */         .getDefinedStringList("recipes.moltenToMaterialMaterialBlacklist", new ArrayList(), miscHelper
/* 161 */           .configMaterialPredicate(), "The materials that should not have material casting recipes added."), configMoltenToMaterialBlacklist);
/*     */     
/* 163 */     miscHelper.caclulateMaterialSet(config
/* 164 */         .getDefinedStringList("recipes.tableMoltenToMaterialMaterialBlacklist", new ArrayList(), miscHelper
/* 165 */           .configMaterialPredicate(), "The materials that should not have material casting table recipes added."), configTableMoltenToMaterialBlacklist);
/*     */     
/* 167 */     miscHelper.caclulateMaterialSet(config
/* 168 */         .getDefinedStringList("recipes.moltenToBlockMaterialBlacklist", new ArrayList(), miscHelper
/* 169 */           .configMaterialPredicate(), "The materials that should not have molten block casting recipes added."), configMoltenToBlockBlacklist);
/*     */     
/* 171 */     miscHelper.caclulateMaterialSet(config
/* 172 */         .getDefinedStringList("recipes.tableMoltenToBlockMaterialBlacklist", new ArrayList(), miscHelper
/* 173 */           .configMaterialPredicate(), "The materials that should not have molten block casting table recipes added."), configTableMoltenToBlockBlacklist);
/*     */     
/* 175 */     miscHelper.caclulateMaterialSet(config
/* 176 */         .getDefinedStringList("recipes.moltenToNuggetMaterialBlacklist", new ArrayList(), miscHelper
/* 177 */           .configMaterialPredicate(), "The materials that should not have molten nugget casting recipes added."), configMoltenToNuggetBlacklist);
/*     */     
/* 179 */     miscHelper.caclulateMaterialSet(config
/* 180 */         .getDefinedStringList("recipes.moltenToDustMaterialBlacklist", new ArrayList(), miscHelper
/* 181 */           .configMaterialPredicate(), "The materials that should not have atomizer molten to dust recipes added."), configMoltenToDustBlacklist);
/*     */     
/* 183 */     miscHelper.caclulateMaterialSet(config
/* 184 */         .getDefinedStringList("recipes.moltenToPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 185 */           .configMaterialPredicate(), "The materials that should not have molten plate casting recipes added."), configMoltenToPlateBlacklist);
/*     */     
/* 187 */     miscHelper.caclulateMaterialSet(config
/* 188 */         .getDefinedStringList("recipes.tableMoltenToPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 189 */           .configMaterialPredicate(), "The materials that should not have molten plate casting table recipes added."), configTableMoltenToPlateBlacklist);
/*     */     
/* 191 */     miscHelper.caclulateMaterialSet(config
/* 192 */         .getDefinedStringList("recipes.moltenToGearMaterialBlacklist", new ArrayList(), miscHelper
/* 193 */           .configMaterialPredicate(), "The materials that should not have molten gear casting recipes added."), configMoltenToGearBlacklist);
/*     */     
/* 195 */     miscHelper.caclulateMaterialSet(config
/* 196 */         .getDefinedStringList("recipes.tableMoltenToRodMaterialBlacklist", new ArrayList(), miscHelper
/* 197 */           .configMaterialPredicate(), "The materials that should not have molten rod casting table recipes added."), configTableMoltenToRodBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 203 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 204 */     FoundryHelper helper = FoundryHelper.INSTANCE;
/* 205 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 206 */     Set<String> oredict = apiImpl.getOredict();
/* 207 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/* 208 */     int baseAmount = FoundryAPI.FLUID_AMOUNT_INGOT;
/* 209 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack);
/* 210 */     ToIntFunction<FluidStack> speedFunction = stack -> 100;
/* 211 */     ItemStack ingotCast = FoundryItems.mold(ItemMold.SubItem.INGOT);
/* 212 */     ItemStack blockCast = FoundryItems.mold(ItemMold.SubItem.BLOCK);
/* 213 */     ItemStack nuggetCast = FoundryItems.mold(ItemMold.SubItem.NUGGET);
/* 214 */     ItemStack plateCast = FoundryItems.mold(ItemMold.SubItem.PLATE);
/* 215 */     ItemStack gearCast = FoundryItems.mold(ItemMold.SubItem.GEAR);
/* 216 */     ItemStack rodCast = FoundryItems.mold(ItemMold.SubItem.ROD);
/* 217 */     for (IMaterial material : moduleData.getMaterials()) {
/* 218 */       MaterialType type = material.getType();
/* 219 */       String name = material.getName();
/* 220 */       if (type.isIngot() && !BLACKLIST.contains(name)) {
/* 221 */         String liquidName = miscHelper.getFluidName("foundry_liquid", name);
/* 222 */         if (FluidRegistry.isFluidRegistered(liquidName)) {
/* 223 */           if (!configMaterialToLiquidBlacklist.contains(name)) {
/* 224 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 225 */             helper.registerMeltingRecipe(miscHelper
/* 226 */                 .getRecipeKey("foundry.material_to_liquid", name), materialOredict, 1, liquidName, baseAmount, tempFunction, speedFunction);
/*     */           } 
/*     */ 
/*     */           
/* 230 */           if (!configBlockToLiquidBlacklist.contains(name)) {
/* 231 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 232 */             if (oredict.contains(blockOredict)) {
/* 233 */               helper.registerMeltingRecipe(miscHelper
/* 234 */                   .getRecipeKey("foundry.block_to_liquid", name), blockOredict, 1, liquidName, baseAmount * (
/* 235 */                   material.isSmallStorageBlock() ? 4 : 9), tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */           
/* 239 */           if (!configNuggetToLiquidBlacklist.contains(name)) {
/* 240 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 241 */             if (oredict.contains(nuggetOredict)) {
/* 242 */               helper.registerMeltingRecipe(miscHelper
/* 243 */                   .getRecipeKey("foundry.nugget_to_liquid", name), nuggetOredict, 1, liquidName, baseAmount / 9, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 248 */           if (!configDustToLiquidBlacklist.contains(name)) {
/* 249 */             String dustOredict = miscHelper.getOredictName("dust", name);
/* 250 */             if (oredict.contains(dustOredict)) {
/* 251 */               helper.registerMeltingRecipe(miscHelper
/* 252 */                   .getRecipeKey("foundry.dust_to_liquid", name), dustOredict, 1, liquidName, baseAmount, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 257 */           if (!configTinyDustToLiquidBlacklist.contains(name)) {
/* 258 */             String tinyDustOredict = miscHelper.getOredictName("dustTiny", name);
/* 259 */             if (oredict.contains(tinyDustOredict)) {
/* 260 */               helper.registerMeltingRecipe(miscHelper
/* 261 */                   .getRecipeKey("foundry.tiny_dust_to_liquid", name), tinyDustOredict, 1, liquidName, baseAmount / 9, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 266 */           if (!configSmallDustToLiquidBlacklist.contains(name)) {
/* 267 */             String smallDustOredict = miscHelper.getOredictName("dustSmall", name);
/* 268 */             if (oredict.contains(smallDustOredict)) {
/* 269 */               helper.registerMeltingRecipe(miscHelper
/* 270 */                   .getRecipeKey("foundry.small_dust_to_liquid", name), smallDustOredict, 1, liquidName, baseAmount / 4, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 275 */           if (!configPlateToLiquidBlacklist.contains(name)) {
/* 276 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 277 */             if (oredict.contains(plateOredict)) {
/* 278 */               helper.registerMeltingRecipe(miscHelper
/* 279 */                   .getRecipeKey("foundry.plate_to_liquid", name), plateOredict, 1, liquidName, baseAmount, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 284 */           if (!configGearToLiquidBlacklist.contains(name)) {
/* 285 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 286 */             if (oredict.contains(gearOredict)) {
/* 287 */               helper.registerMeltingRecipe(miscHelper
/* 288 */                   .getRecipeKey("foundry.gear_to_liquid", name), gearOredict, 1, liquidName, baseAmount * 4, tempFunction, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 293 */           if (!configToMaterialBlacklist.contains(name)) {
/* 294 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 295 */             helper.registerCastingRecipe(miscHelper
/* 296 */                 .getRecipeKey("foundry.liquid_to_material", name), liquidName, baseAmount, ingotCast, materialOredict, 1, speedFunction);
/*     */           } 
/*     */ 
/*     */           
/* 300 */           if (!configTableToMaterialBlacklist.contains(name)) {
/* 301 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 302 */             helper.registerCastingTableRecipe(miscHelper
/* 303 */                 .getRecipeKey("foundry.liquid_to_material_table", name), liquidName, baseAmount, materialOredict, 1, "ingot");
/*     */           } 
/*     */ 
/*     */           
/* 307 */           if (!configToBlockBlacklist.contains(name)) {
/* 308 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 309 */             if (oredict.contains(blockOredict)) {
/* 310 */               helper.registerCastingRecipe(miscHelper
/* 311 */                   .getRecipeKey("foundry.liquid_to_block", name), liquidName, baseAmount * (
/* 312 */                   material.isSmallStorageBlock() ? 4 : 9), blockCast, blockOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */           
/* 316 */           if (!configTableToBlockBlacklist.contains(name)) {
/* 317 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 318 */             if (oredict.contains(blockOredict)) {
/* 319 */               helper.registerCastingTableRecipe(miscHelper
/* 320 */                   .getRecipeKey("foundry.liquid_to_block_table", name), liquidName, baseAmount * (
/* 321 */                   material.isSmallStorageBlock() ? 4 : 9), blockOredict, 1, "block");
/*     */             }
/*     */           } 
/*     */           
/* 325 */           if (!configToNuggetBlacklist.contains(name)) {
/* 326 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 327 */             if (oredict.contains(nuggetOredict)) {
/* 328 */               helper.registerCastingRecipe(miscHelper
/* 329 */                   .getRecipeKey("foundry.liquid_to_nugget", name), liquidName, 
/* 330 */                   ceilDiv(baseAmount, 9), nuggetCast, nuggetOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */           
/* 334 */           if (!configToDustBlacklist.contains(name)) {
/* 335 */             String dustOredict = miscHelper.getOredictName("dust", name);
/* 336 */             if (oredict.contains(dustOredict)) {
/* 337 */               helper.registerAtomizerRecipe(miscHelper
/* 338 */                   .getRecipeKey("foundry.liquid_to_dust", name), liquidName, baseAmount, dustOredict, 1);
/*     */             }
/*     */           } 
/*     */           
/* 342 */           if (!configToPlateBlacklist.contains(name)) {
/* 343 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 344 */             if (oredict.contains(plateOredict)) {
/* 345 */               helper.registerCastingRecipe(miscHelper
/* 346 */                   .getRecipeKey("foundry.liquid_to_plate", name), liquidName, baseAmount, plateCast, plateOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 351 */           if (!configTableToPlateBlacklist.contains(name)) {
/* 352 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 353 */             if (oredict.contains(plateOredict)) {
/* 354 */               helper.registerCastingTableRecipe(miscHelper
/* 355 */                   .getRecipeKey("foundry.liquid_to_plate_table", name), liquidName, baseAmount, plateOredict, 1, "plate");
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 360 */           if (!configToGearBlacklist.contains(name)) {
/* 361 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 362 */             if (oredict.contains(gearOredict)) {
/* 363 */               helper.registerCastingRecipe(miscHelper
/* 364 */                   .getRecipeKey("foundry.liquid_to_gear", name), liquidName, baseAmount * 4, gearCast, gearOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 369 */           if (!configToRodBlacklist.contains(name)) {
/* 370 */             String rodOredict = miscHelper.getOredictName("rod", name);
/* 371 */             if (oredict.contains(rodOredict)) {
/* 372 */               helper.registerCastingRecipe(miscHelper
/* 373 */                   .getRecipeKey("foundry.liquid_to_rod", name), liquidName, 
/* 374 */                   ceilDiv(baseAmount, 2), rodCast, rodOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */           
/* 378 */           if (!configTableToRodBlacklist.contains(name)) {
/* 379 */             String rodOredict = miscHelper.getOredictName("rod", name);
/* 380 */             if (oredict.contains(rodOredict)) {
/* 381 */               helper.registerCastingTableRecipe(miscHelper
/* 382 */                   .getRecipeKey("foundry.liquid_to_rod_table", name), liquidName, 
/* 383 */                   ceilDiv(baseAmount, 2), rodOredict, 1, "rod");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 389 */       if (type.isIngot() && !BLACKLIST.contains(name) && (!jaopcaOnly || moltenMaterials.contains(material))) {
/* 390 */         String moltenName = miscHelper.getFluidName("", name);
/* 391 */         if (FluidRegistry.isFluidRegistered(moltenName)) {
/* 392 */           if (!configMoltenToMaterialBlacklist.contains(name)) {
/* 393 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 394 */             helper.registerCastingRecipe(miscHelper
/* 395 */                 .getRecipeKey("foundry.molten_to_material", name), moltenName, 144, ingotCast, materialOredict, 1, speedFunction);
/*     */           } 
/*     */ 
/*     */           
/* 399 */           if (!configTableMoltenToMaterialBlacklist.contains(name)) {
/* 400 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 401 */             helper.registerCastingTableRecipe(miscHelper
/* 402 */                 .getRecipeKey("foundry.molten_to_material_table", name), moltenName, 144, materialOredict, 1, "ingot");
/*     */           } 
/*     */ 
/*     */           
/* 406 */           if (!configMoltenToBlockBlacklist.contains(name)) {
/* 407 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 408 */             if (oredict.contains(blockOredict)) {
/* 409 */               helper.registerCastingRecipe(miscHelper
/* 410 */                   .getRecipeKey("foundry.molten_to_block", name), moltenName, 144 * (
/* 411 */                   material.isSmallStorageBlock() ? 4 : 9), blockCast, blockOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */           
/* 415 */           if (!configTableMoltenToBlockBlacklist.contains(name)) {
/* 416 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 417 */             if (oredict.contains(blockOredict)) {
/* 418 */               helper.registerCastingTableRecipe(miscHelper
/* 419 */                   .getRecipeKey("foundry.molten_to_block_table", name), moltenName, 144 * (
/* 420 */                   material.isSmallStorageBlock() ? 4 : 9), blockOredict, 1, "block");
/*     */             }
/*     */           } 
/*     */           
/* 424 */           if (!configMoltenToNuggetBlacklist.contains(name)) {
/* 425 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 426 */             if (oredict.contains(nuggetOredict)) {
/* 427 */               helper.registerCastingRecipe(miscHelper
/* 428 */                   .getRecipeKey("foundry.molten_to_nugget", name), moltenName, 16, nuggetCast, nuggetOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 433 */           if (!configMoltenToDustBlacklist.contains(name)) {
/* 434 */             String dustOredict = miscHelper.getOredictName("dust", name);
/* 435 */             if (oredict.contains(dustOredict)) {
/* 436 */               helper.registerAtomizerRecipe(miscHelper
/* 437 */                   .getRecipeKey("foundry.molten_to_dust", name), moltenName, 144, dustOredict, 1);
/*     */             }
/*     */           } 
/*     */           
/* 441 */           if (!configMoltenToPlateBlacklist.contains(name)) {
/* 442 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 443 */             if (oredict.contains(plateOredict)) {
/* 444 */               helper.registerCastingRecipe(miscHelper
/* 445 */                   .getRecipeKey("foundry.molten_to_plate", name), moltenName, 144, plateCast, plateOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 450 */           if (!configTableMoltenToPlateBlacklist.contains(name)) {
/* 451 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 452 */             if (oredict.contains(plateOredict)) {
/* 453 */               helper.registerCastingTableRecipe(miscHelper
/* 454 */                   .getRecipeKey("foundry.molten_to_plate_table", name), moltenName, 144, plateOredict, 1, "plate");
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 459 */           if (!configMoltenToGearBlacklist.contains(name)) {
/* 460 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 461 */             if (oredict.contains(gearOredict)) {
/* 462 */               helper.registerCastingRecipe(miscHelper
/* 463 */                   .getRecipeKey("foundry.molten_to_gear", name), moltenName, 576, gearCast, gearOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 468 */           if (!configMoltenToRodBlacklist.contains(name)) {
/* 469 */             String rodOredict = miscHelper.getOredictName("rod", name);
/* 470 */             if (oredict.contains(rodOredict)) {
/* 471 */               helper.registerCastingRecipe(miscHelper
/* 472 */                   .getRecipeKey("foundry.molten_to_rod", name), moltenName, 72, rodCast, rodOredict, 1, speedFunction);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 477 */           if (!configTableMoltenToRodBlacklist.contains(name)) {
/* 478 */             String rodOredict = miscHelper.getOredictName("rod", name);
/* 479 */             if (oredict.contains(rodOredict)) {
/* 480 */               helper.registerCastingTableRecipe(miscHelper
/* 481 */                   .getRecipeKey("foundry.molten_to_rod_table", name), moltenName, 72, rodOredict, 1, "rod");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int ceilDiv(int a, int b) {
/* 492 */     return a / b + ((a % b == 0) ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\FoundryCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */