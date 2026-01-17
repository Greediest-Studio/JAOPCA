/*    */ package thelm.jaopca.compat.integrateddynamics;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.integrateddynamics.recipes.MechanicalSqueezerRecipeAction;
/*    */ import thelm.jaopca.compat.integrateddynamics.recipes.SqueezerRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class IntegratedDynamicsHelper {
/* 10 */   public static final IntegratedDynamicsHelper INSTANCE = new IntegratedDynamicsHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerSqueezerRecipe(ResourceLocation key, Object input, Object[] itemOutput, Object fluidOutput, int fluidOutputAmount) {
/* 15 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SqueezerRecipeAction(key, input, itemOutput, fluidOutput, fluidOutputAmount));
/*    */   }
/*    */   
/*    */   public boolean registerSqueezerRecipe(ResourceLocation key, Object input, Object[] itemOutput) {
/* 19 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SqueezerRecipeAction(key, input, itemOutput));
/*    */   }
/*    */   
/*    */   public boolean registerMechanicalSqueezerRecipe(ResourceLocation key, Object input, Object[] itemOutput, Object fluidOutput, int fluidOutputAmount, int time) {
/* 23 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MechanicalSqueezerRecipeAction(key, input, itemOutput, fluidOutput, fluidOutputAmount, time));
/*    */   }
/*    */   
/*    */   public boolean registerMechanicalSqueezerRecipe(ResourceLocation key, Object input, Object[] itemOutput, int time) {
/* 27 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MechanicalSqueezerRecipeAction(key, input, itemOutput, time));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\integrateddynamics\IntegratedDynamicsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */