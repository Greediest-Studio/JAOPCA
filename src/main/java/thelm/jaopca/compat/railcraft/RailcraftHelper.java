/*    */ package thelm.jaopca.compat.railcraft;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.railcraft.recipes.RockCrusherRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class RailcraftHelper {
/*  9 */   public static final RailcraftHelper INSTANCE = new RailcraftHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerRockCrusherRecipe(ResourceLocation key, Object input, int time, Object... output) {
/* 14 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new RockCrusherRecipeAction(key, input, time, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\railcraft\RailcraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */