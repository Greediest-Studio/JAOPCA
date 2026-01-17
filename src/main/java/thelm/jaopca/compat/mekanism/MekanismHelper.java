/*    */ package thelm.jaopca.compat.mekanism;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import mekanism.api.gas.Gas;
/*    */ import mekanism.api.gas.GasRegistry;
/*    */ import mekanism.api.gas.GasStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasProvider;
/*    */ import thelm.jaopca.compat.mekanism.recipes.ChemicalCrystallizerRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.ChemicalDissolutionChamberRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.ChemicalInjectionChamberRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.ChemicalWasherRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.CombinerRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.CrusherRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.EnrichmentChamberRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.recipes.PurificationChamberRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class MekanismHelper
/*    */ {
/* 22 */   public static final MekanismHelper INSTANCE = new MekanismHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public GasStack getGasStack(Object obj, int amount) {
/* 27 */     if (obj instanceof Supplier) {
/* 28 */       return getGasStack(((Supplier)obj).get(), amount);
/*    */     }
/* 30 */     if (obj instanceof GasStack) {
/* 31 */       return resizeGasStack((GasStack)obj, amount);
/*    */     }
/* 33 */     if (obj instanceof Gas) {
/* 34 */       return new GasStack((Gas)obj, amount);
/*    */     }
/* 36 */     if (obj instanceof IGasProvider) {
/* 37 */       return new GasStack(((IGasProvider)obj).asGas(), amount);
/*    */     }
/* 39 */     if (obj instanceof String) {
/* 40 */       return new GasStack(GasRegistry.getGas((String)obj), amount);
/*    */     }
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   public GasStack resizeGasStack(GasStack stack, int amount) {
/* 46 */     if (stack != null) {
/* 47 */       GasStack ret = stack.copy();
/* 48 */       ret.amount = amount;
/* 49 */       return ret;
/*    */     } 
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerCrusherRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 55 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrusherRecipeAction(key, input, inputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerEnrichmentChamberRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 59 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new EnrichmentChamberRecipeAction(key, input, inputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerCombinerRecipe(ResourceLocation key, Object input, int inputCount, Object secondInput, int secondInputCount, Object output, int outputCount) {
/* 63 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CombinerRecipeAction(key, input, inputCount, secondInput, secondInputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerChemicalWasherRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputAmount) {
/* 67 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ChemicalWasherRecipeAction(key, input, inputAmount, output, outputAmount));
/*    */   }
/*    */   
/*    */   public boolean registerChemicalCrystallizerRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 71 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ChemicalCrystallizerRecipeAction(key, input, inputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerChemicalDissolutionChamberRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 75 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ChemicalDissolutionChamberRecipeAction(key, input, inputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerPurificationChamberRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 79 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new PurificationChamberRecipeAction(key, input, inputCount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerChemicalInjectionChamberRecipe(ResourceLocation key, Object input, int inputCount, Object gasInput, Object output, int outputCount) {
/* 83 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ChemicalInjectionChamberRecipeAction(key, input, inputCount, gasInput, output, outputCount));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\MekanismHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */