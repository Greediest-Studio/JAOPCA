/*    */ package thelm.jaopca.compat.bcoreprocessing;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.bcoreprocessing.recipes.CoolableRecipeAction;
/*    */ import thelm.jaopca.compat.bcoreprocessing.recipes.FluidProcessorRecipeAction;
/*    */ import thelm.jaopca.compat.bcoreprocessing.recipes.HeatableRecipeAction;
/*    */ import thelm.jaopca.compat.bcoreprocessing.recipes.OreProcessorRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class BCOreProcessingHelper {
/* 12 */   public static final BCOreProcessingHelper INSTANCE = new BCOreProcessingHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerHeatableRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputAmount, int heatFrom, int heatTo) {
/* 17 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new HeatableRecipeAction(key, input, inputAmount, output, outputAmount, heatFrom, heatTo));
/*    */   }
/*    */   
/*    */   public boolean registerCoolableRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputAmount, int heatFrom, int heatTo) {
/* 21 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CoolableRecipeAction(key, input, inputAmount, output, outputAmount, heatFrom, heatTo));
/*    */   }
/*    */   
/*    */   public boolean registerOreProcessorRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputAmount, Object secondOutput, int secondOutputAmount, int time) {
/* 25 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new OreProcessorRecipeAction(key, input, inputCount, output, outputAmount, secondOutput, secondOutputAmount, time));
/*    */   }
/*    */   
/*    */   public boolean registerFluidProcessorRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount, Object fluidOutput, int fluidOutputAmount, int time) {
/* 29 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new FluidProcessorRecipeAction(key, input, inputAmount, output, outputCount, fluidOutput, fluidOutputAmount, time));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\bcoreprocessing\BCOreProcessingHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */