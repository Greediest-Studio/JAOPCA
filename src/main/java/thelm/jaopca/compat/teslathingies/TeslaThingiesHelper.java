/*    */ package thelm.jaopca.compat.teslathingies;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.teslathingies.recipes.CompoundMakerRecipeAction;
/*    */ import thelm.jaopca.compat.teslathingies.recipes.PowderMakerRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class TeslaThingiesHelper {
/* 10 */   public static final TeslaThingiesHelper INSTANCE = new TeslaThingiesHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerCompoundMakerRecipe(ResourceLocation key, Object[] top, Object left, int leftAmount, Object output, int outputCount) {
/* 15 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompoundMakerRecipeAction(key, top, left, leftAmount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerCompoundMakerRecipe(ResourceLocation key, Object[] top, Object[] bottom, Object output, int outputCount) {
/* 19 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompoundMakerRecipeAction(key, top, bottom, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerCompoundMakerRecipe(ResourceLocation key, Object[] top, Object left, int leftAmount, Object[] bottom, Object output, int outputCount) {
/* 23 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompoundMakerRecipeAction(key, top, left, leftAmount, bottom, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerCompoundMakerRecipe(ResourceLocation key, Object[] top, Object left, int leftAmount, Object[] bottom, Object right, int rightAmount, Object output, int outputCount) {
/* 27 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompoundMakerRecipeAction(key, top, left, leftAmount, bottom, right, rightAmount, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerPowderMakerRecipe(ResourceLocation key, Object input, int inputCount, Object... output) {
/* 31 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new PowderMakerRecipeAction(key, input, inputCount, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\teslathingies\TeslaThingiesHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */