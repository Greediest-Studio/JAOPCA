/*    */ package thelm.jaopca.compat.essentialcraft.recipes;
/*    */ 
/*    */ import essentialcraft.api.OreSmeltingRecipe;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ 
/*    */ public class MagmaticSmelterRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/*    */   public final ResourceLocation key;
/*    */   public final String input;
/*    */   public final String output;
/*    */   public final int count;
/*    */   public final Consumer<OreSmeltingRecipe> callback;
/*    */   
/*    */   public MagmaticSmelterRecipeAction(ResourceLocation key, String input, String output, int count, Consumer<OreSmeltingRecipe> callback) {
/* 19 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 20 */     this.input = input;
/* 21 */     this.output = output;
/* 22 */     this.count = count;
/* 23 */     this.callback = Objects.<Consumer<OreSmeltingRecipe>>requireNonNull(callback);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 28 */     this.callback.accept(OreSmeltingRecipe.addRecipe(this.input, this.output, 0, this.count));
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\essentialcraft\recipes\MagmaticSmelterRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */