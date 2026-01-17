/*    */ package thelm.jaopca.compat.embers.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import teamroots.embers.recipe.ItemMeltingRecipe;
/*    */ import teamroots.embers.recipe.RecipeRegistry;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class MeltingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputAmount;
/*    */   
/*    */   public MeltingRecipeAction(ResourceLocation key, Object input, Object output, int outputAmount) {
/* 28 */     this(key, input, output, outputAmount, null, 0);
/*    */   }
/*    */   
/*    */   public MeltingRecipeAction(ResourceLocation key, Object input, Object output, int outputAmount, Object secondOutput, int secondOutputAmount) {
/* 32 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 33 */     this.input = input;
/* 34 */     this.output = output;
/* 35 */     this.outputAmount = outputAmount;
/* 36 */     this.secondOutput = secondOutput;
/* 37 */     this.secondOutputAmount = secondOutputAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 42 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 43 */     if (ing == null) {
/* 44 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 46 */     FluidStack stack = MiscHelper.INSTANCE.getFluidStack(this.output, this.outputAmount);
/* 47 */     if (stack == null) {
/* 48 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 50 */     FluidStack stack2 = MiscHelper.INSTANCE.getFluidStack(this.secondOutput, this.secondOutputAmount);
/* 51 */     return RecipeRegistry.meltingRecipes.add((new ItemMeltingRecipe(ing, stack)).addBonusOutput(stack2));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\embers\recipes\MeltingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */