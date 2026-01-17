/*    */ package thelm.jaopca.compat.create;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.create.recipes.CrushingRecipeAction;
/*    */ import thelm.jaopca.compat.create.recipes.MillingRecipeAction;
/*    */ import thelm.jaopca.compat.create.recipes.PressingRecipeAction;
/*    */ import thelm.jaopca.compat.create.recipes.WashingRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class CreateHelper {
/* 12 */   public static final CreateHelper INSTANCE = new CreateHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerCrushingRecipe(ResourceLocation key, Object input, int time, Object... output) {
/* 17 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrushingRecipeAction(key, input, time, output));
/*    */   }
/*    */   
/*    */   public boolean registerMillingRecipe(ResourceLocation key, Object input, int time, Object... output) {
/* 21 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MillingRecipeAction(key, input, time, output));
/*    */   }
/*    */   
/*    */   public boolean registerPressingRecipe(ResourceLocation key, Object input, Object output, int outputCount) {
/* 25 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new PressingRecipeAction(key, input, output, outputCount));
/*    */   }
/*    */   
/*    */   public boolean registerWashingRecipe(ResourceLocation key, Object input, Object... output) {
/* 29 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new WashingRecipeAction(key, input, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\CreateHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */