/*    */ package thelm.jaopca.compat.foundry;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import exter.foundry.api.FoundryAPI;
/*    */ import exter.foundry.fluid.LiquidMetalRegistry;
/*    */ import java.util.Collections;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"foundry@[3,)"})
/*    */ public class FoundryModule
/*    */   implements IModule
/*    */ {
/* 29 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     return "foundry";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 38 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 39 */     builder.put(Integer.valueOf(0), "foundry_liquid");
/* 40 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 45 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 50 */     if (BLACKLIST.isEmpty()) {
/* 51 */       BLACKLIST.addAll(LiquidMetalRegistry.INSTANCE.getFluidNames());
/* 52 */       Collections.addAll(BLACKLIST, new String[] { "Aluminum", "Constantan" });
/*    */     } 
/* 54 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 59 */     FoundryHelper helper = FoundryHelper.INSTANCE;
/* 60 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 61 */     int oreAmount = FoundryAPI.getAmountOre();
/* 62 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack);
/* 63 */     ToIntFunction<FluidStack> speedFunction = stack -> 100;
/* 64 */     for (IMaterial material : moduleData.getMaterials()) {
/* 65 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 66 */       String liquidName = miscHelper.getFluidName("foundry_liquid", material.getName());
/* 67 */       helper.registerMeltingRecipe(miscHelper
/* 68 */           .getRecipeKey("foundry.ore_to_liquid", material.getName()), oreOredict, 1, liquidName, oreAmount, tempFunction, speedFunction);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 75 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 76 */     builder.put("liquid", "foundry_liquid");
/* 77 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\FoundryModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */