/*    */ package thelm.jaopca.compat.magneticraft;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.magneticraft.recipes.CrushingTableRecipeAction;
/*    */ import thelm.jaopca.compat.magneticraft.recipes.GrinderRecipeAction;
/*    */ import thelm.jaopca.compat.magneticraft.recipes.HydraulicPressRecipeAction;
/*    */ import thelm.jaopca.compat.magneticraft.recipes.SieveRecipeAction;
/*    */ import thelm.jaopca.compat.magneticraft.recipes.SluiceBoxRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class MagneticraftHelper {
/* 13 */   public static final MagneticraftHelper INSTANCE = new MagneticraftHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerCrushingTableRecipe(ResourceLocation key, Object input, Object output, int count) {
/* 18 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrushingTableRecipeAction(key, input, output, count));
/*    */   }
/*    */   
/*    */   public boolean registerSluiceBoxRecipe(ResourceLocation key, Object input, Object... output) {
/* 22 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SluiceBoxRecipeAction(key, input, output));
/*    */   }
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, float time) {
/* 26 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, output, outputCount, secondOutput, secondOutputCount, secondOutputChance, time));
/*    */   }
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, Object output, int outputCount, float time) {
/* 30 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, output, outputCount, time));
/*    */   }
/*    */   
/*    */   public boolean registerSieveRecipe(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, Object secondOutput, int secondOutputCount, float secondOutputChance, Object thirdOutput, int thirdOutputCount, float thirdOutputChance, float time) {
/* 34 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SieveRecipeAction(key, input, output, outputCount, outputChance, secondOutput, secondOutputCount, secondOutputChance, thirdOutput, thirdOutputCount, thirdOutputChance, time));
/*    */   }
/*    */   
/*    */   public boolean registerSieveRecipe(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, Object secondOutput, int secondOutputCount, float secondOutputChance, float time) {
/* 38 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SieveRecipeAction(key, input, output, outputCount, outputChance, secondOutput, secondOutputCount, secondOutputChance, time));
/*    */   }
/*    */   
/*    */   public boolean registerSieveRecipe(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, float time) {
/* 42 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SieveRecipeAction(key, input, output, outputCount, outputChance, time));
/*    */   }
/*    */   
/*    */   public boolean registerHydraulicPressRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, float time, int mode) {
/* 46 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new HydraulicPressRecipeAction(key, input, inputCount, output, outputCount, time, mode));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\MagneticraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */