/*    */ package thelm.jaopca.compat.minestrapp;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.minestrapp.recipes.CrusherRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class MinestrappHelper {
/*  9 */   public static final MinestrappHelper INSTANCE = new MinestrappHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerCrusherRecipe(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, int secondOutputChance, float experience) {
/* 14 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrusherRecipeAction(key, input, output, outputCount, secondOutput, secondOutputCount, secondOutputChance, experience));
/*    */   }
/*    */   
/*    */   public boolean registerCrusherRecipe(ResourceLocation key, Object input, Object output, int outputCount, float experience) {
/* 18 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrusherRecipeAction(key, input, output, outputCount, experience));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\minestrapp\MinestrappHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */