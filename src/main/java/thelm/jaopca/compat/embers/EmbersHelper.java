/*    */ package thelm.jaopca.compat.embers;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.embers.recipes.MeltingRecipeAction;
/*    */ import thelm.jaopca.compat.embers.recipes.StampingRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class EmbersHelper {
/* 10 */   public static final EmbersHelper INSTANCE = new EmbersHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerMeltingRecipe(ResourceLocation key, Object input, Object output, int outputAmount, Object secondOutput, int secondOutputAmount) {
/* 15 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MeltingRecipeAction(key, input, output, outputAmount, secondOutput, secondOutputAmount));
/*    */   }
/*    */   
/*    */   public boolean registerMeltingRecipe(ResourceLocation key, Object input, Object output, int outputAmount) {
/* 19 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MeltingRecipeAction(key, input, output, outputAmount));
/*    */   }
/*    */   
/*    */   public boolean registerStampingRecipe(ResourceLocation key, Object itemInput, Object fluidInput, int fluidInputAmount, Object stamp, Object output, int outputCount) {
/* 23 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new StampingRecipeAction(key, itemInput, fluidInput, fluidInputAmount, stamp, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerStampingRecipe(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object stamp, Object output, int outputCount) {
/* 27 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new StampingRecipeAction(key, fluidInput, fluidInputAmount, stamp, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerStampingRecipe(ResourceLocation key, Object itemInput, Object stamp, Object output, int outputCount) {
/* 31 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new StampingRecipeAction(key, itemInput, stamp, output, outputCount));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\embers\EmbersHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */