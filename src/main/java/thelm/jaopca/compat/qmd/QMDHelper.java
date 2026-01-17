/*    */ package thelm.jaopca.compat.qmd;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.qmd.recipes.OreLeacherRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class QMDHelper {
/*  9 */   public static final QMDHelper INSTANCE = new QMDHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerOreLeacherRecipe(ResourceLocation key, Object itemInput, int itemInputCount, Object[] fluidInput, Object[] output) {
/* 14 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new OreLeacherRecipeAction(key, itemInput, itemInputCount, fluidInput, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\qmd\QMDHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */