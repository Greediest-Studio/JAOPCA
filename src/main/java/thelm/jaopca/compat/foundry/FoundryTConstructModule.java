/*     */ package thelm.jaopca.compat.foundry;
/*     */ 
/*     */ import exter.foundry.api.FoundryAPI;
/*     */ import exter.foundry.fluid.LiquidMetalRegistry;
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
/*     */ import slimeknights.tconstruct.smeltery.TinkerSmeltery;
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
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"foundry@[3,)", "tconstruct"})
/*     */ public class FoundryTConstructModule
/*     */   implements IModule
/*     */ {
/*  35 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  37 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*  38 */   private static Set<String> configToMaterialBlacklist = new TreeSet<>();
/*  39 */   private static Set<String> configToBlockBlacklist = new TreeSet<>();
/*  40 */   private static Set<String> configToNuggetBlacklist = new TreeSet<>();
/*  41 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*  42 */   private static Set<String> configToGearBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  46 */     return "foundry_tconstruct";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  51 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  56 */     BLACKLIST.addAll(LiquidMetalRegistry.INSTANCE.getFluidNames());
/*  57 */     Collections.addAll(BLACKLIST, new String[] { "Aluminum", "Constantan" });
/*  58 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  59 */     miscHelper.caclulateMaterialSet(config
/*  60 */         .getDefinedStringList("recipes.toMaterialMaterialBlacklist", new ArrayList(), miscHelper
/*  61 */           .configMaterialPredicate(), "The materials that should not have material casting recipes added."), configToMaterialBlacklist);
/*     */     
/*  63 */     miscHelper.caclulateMaterialSet(config
/*  64 */         .getDefinedStringList("recipes.toBlockMaterialBlacklist", new ArrayList(), miscHelper
/*  65 */           .configMaterialPredicate(), "The materials that should not have block casting recipes added."), configToBlockBlacklist);
/*     */     
/*  67 */     miscHelper.caclulateMaterialSet(config
/*  68 */         .getDefinedStringList("recipes.toNuggetMaterialBlacklist", new ArrayList(), miscHelper
/*  69 */           .configMaterialPredicate(), "The materials that should not have nugget casting recipes added."), configToNuggetBlacklist);
/*     */     
/*  71 */     miscHelper.caclulateMaterialSet(config
/*  72 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/*  73 */           .configMaterialPredicate(), "The materials that should not have plate casting recipes added."), configToPlateBlacklist);
/*     */     
/*  75 */     miscHelper.caclulateMaterialSet(config
/*  76 */         .getDefinedStringList("recipes.toGearMaterialBlacklist", new ArrayList(), miscHelper
/*  77 */           .configMaterialPredicate(), "The materials that should not have gear casting recipes added."), configToGearBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  83 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  84 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  85 */     TConstructHelper helper = TConstructHelper.INSTANCE;
/*  86 */     Set<String> oredict = apiImpl.getOredict();
/*  87 */     int baseAmount = FoundryAPI.FLUID_AMOUNT_INGOT;
/*  88 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack) - 300;
/*  89 */     ItemStack ingotCast = TinkerSmeltery.castIngot;
/*  90 */     ItemStack nuggetCast = TinkerSmeltery.castNugget;
/*  91 */     ItemStack plateCast = TinkerSmeltery.castPlate;
/*  92 */     ItemStack gearCast = TinkerSmeltery.castGear;
/*  93 */     for (IMaterial material : moduleData.getMaterials()) {
/*  94 */       MaterialType type = material.getType();
/*  95 */       String name = material.getName();
/*  96 */       if (type.isIngot() && !BLACKLIST.contains(name)) {
/*  97 */         String liquidName = miscHelper.getFluidName("foundry_liquid", name);
/*  98 */         if (FluidRegistry.isFluidRegistered(liquidName)) {
/*  99 */           if (!configToMaterialBlacklist.contains(name)) {
/* 100 */             String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 101 */             helper.registerTableCastingRecipe(miscHelper
/* 102 */                 .getRecipeKey("foundry_tconstruct.liquid_to_material", name), ingotCast, liquidName, baseAmount, materialOredict, tempFunction, false, false);
/*     */           } 
/*     */ 
/*     */           
/* 106 */           if (!configToBlockBlacklist.contains(name)) {
/* 107 */             String blockOredict = miscHelper.getOredictName("block", name);
/* 108 */             if (oredict.contains(blockOredict)) {
/* 109 */               helper.registerBasinCastingRecipe(miscHelper
/* 110 */                   .getRecipeKey("foundry_tconstruct.liquid_to_block", name), null, liquidName, baseAmount * (
/* 111 */                   material.isSmallStorageBlock() ? 4 : 9), blockOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */           
/* 115 */           if (!configToNuggetBlacklist.contains(name)) {
/* 116 */             String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 117 */             if (oredict.contains(nuggetOredict)) {
/* 118 */               helper.registerTableCastingRecipe(miscHelper
/* 119 */                   .getRecipeKey("foundry_tconstruct.liquid_to_nugget", name), nuggetCast, liquidName, 
/* 120 */                   ceilDiv(baseAmount, 9), nuggetOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */           
/* 124 */           if (!configToPlateBlacklist.contains(name)) {
/* 125 */             String plateOredict = miscHelper.getOredictName("plate", name);
/* 126 */             if (oredict.contains(plateOredict)) {
/* 127 */               helper.registerTableCastingRecipe(miscHelper
/* 128 */                   .getRecipeKey("foundry_tconstruct.liquid_to_plate", name), plateCast, liquidName, baseAmount, plateOredict, tempFunction, false, false);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 133 */           if (!configToGearBlacklist.contains(name)) {
/* 134 */             String gearOredict = miscHelper.getOredictName("gear", name);
/* 135 */             if (oredict.contains(gearOredict)) {
/* 136 */               helper.registerTableCastingRecipe(miscHelper
/* 137 */                   .getRecipeKey("foundry_tconstruct.liquid_to_gear", name), gearCast, liquidName, baseAmount * 4, gearOredict, tempFunction, false, false);
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
/* 148 */     return a / b + ((a % b == 0) ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\FoundryTConstructModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */