/*    */ package thelm.jaopca.compat.appliedenergistics2;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.appliedenergistics2.recipes.GrinderRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class AppliedEnergistics2Helper {
/*  9 */   public static final AppliedEnergistics2Helper INSTANCE = new AppliedEnergistics2Helper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, Object thirdOutput, int thirdOutputCount, float thirdOutputChance, int turns) {
/* 14 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, output, outputCount, secondOutput, secondOutputCount, secondOutputChance, thirdOutput, thirdOutputCount, thirdOutputChance, turns));
/*    */   }
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, int turns) {
/* 18 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, output, outputCount, secondOutput, secondOutputCount, secondOutputChance, turns));
/*    */   }
/*    */   
/*    */   public boolean registerGrinderRecipe(ResourceLocation key, Object input, Object output, int outputCount, int turns) {
/* 22 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new GrinderRecipeAction(key, input, output, outputCount, turns));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\appliedenergistics2\AppliedEnergistics2Helper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */