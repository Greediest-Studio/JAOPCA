/*    */ package thelm.jaopca.compat.essentialcraft;
/*    */ 
/*    */ import essentialcraft.api.OreSmeltingRecipe;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.essentialcraft.recipes.MagmaticSmelterRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class EssentialCraftHelper
/*    */ {
/* 12 */   public static final EssentialCraftHelper INSTANCE = new EssentialCraftHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerMagmaticSmelterRecipe(ResourceLocation key, String input, String output, int count, Consumer<OreSmeltingRecipe> callback) {
/* 17 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MagmaticSmelterRecipeAction(key, input, output, count, callback));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\essentialcraft\EssentialCraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */