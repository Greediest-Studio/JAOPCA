/*    */ package thelm.jaopca.compat.techreborn;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.techreborn.recipes.CompressorRecipeAction;
/*    */ import thelm.jaopca.compat.techreborn.recipes.GrinderRecipeAction;
/*    */ import thelm.jaopca.compat.techreborn.recipes.ImplosionCompressorRecipeAction;
/*    */ import thelm.jaopca.compat.techreborn.recipes.IndustrialGrinderRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class TechRebornHelper {
/* 12 */   public static final TechRebornHelper INSTANCE = new TechRebornHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, int time, int energy) {
/* 17 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, inputCount, output, outputCount, time, energy));
/*    */   }
/*    */   
/*    */   public boolean registerIndustrialGrinderRecipe(ResourceLocation key, Object itemInput, int itemInputCount, Object fluidInput, int fluidInputAmount, int time, int energy, Object... output) {
/* 21 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new IndustrialGrinderRecipeAction(key, itemInput, itemInputCount, fluidInput, fluidInputAmount, time, energy, output));
/*    */   }
/*    */   
/*    */   public boolean registerCompressorRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, int time, int energy) {
/* 25 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompressorRecipeAction(key, input, inputCount, output, outputCount, time, energy));
/*    */   }
/*    */   
/*    */   public boolean registerImplosionCompressorRecipe(ResourceLocation key, Object[] input, Object[] output, int time, int energy) {
/* 29 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ImplosionCompressorRecipeAction(key, input, output, time, energy));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */