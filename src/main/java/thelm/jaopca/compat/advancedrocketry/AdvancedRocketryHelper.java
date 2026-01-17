/*    */ package thelm.jaopca.compat.advancedrocketry;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.advancedrocketry.recipes.SmallPlatePressRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class AdvancedRocketryHelper {
/*  9 */   public static final AdvancedRocketryHelper INSTANCE = new AdvancedRocketryHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerSmallPlatePressRecipe(ResourceLocation key, Object input, Object output, int outputCount) {
/* 14 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SmallPlatePressRecipeAction(key, input, output, outputCount));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\advancedrocketry\AdvancedRocketryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */