/*    */ package thelm.jaopca.compat.foundry.recipes;
/*    */ 
/*    */ import exter.foundry.api.FoundryAPI;
/*    */ import exter.foundry.api.recipe.matcher.IItemMatcher;
/*    */ import java.util.Objects;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.FoundryHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class MeltingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   public final ToIntFunction<FluidStack> temperature;
/*    */   public final ToIntFunction<FluidStack> speed;
/*    */   
/*    */   public MeltingRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputAmount, ToIntFunction<FluidStack> temperature, ToIntFunction<FluidStack> speed) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.inputCount = inputCount;
/* 33 */     this.output = output;
/* 34 */     this.outputAmount = outputAmount;
/* 35 */     this.temperature = temperature;
/* 36 */     this.speed = speed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     IItemMatcher ing = FoundryHelper.INSTANCE.getItemMatcher(this.input, this.inputCount);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 45 */     FluidStack stack = MiscHelper.INSTANCE.getFluidStack(this.output, this.outputAmount);
/* 46 */     if (stack == null) {
/* 47 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 49 */     FoundryAPI.MELTING_MANAGER.addRecipe(ing, stack, this.temperature.applyAsInt(stack), this.speed.applyAsInt(stack));
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\recipes\MeltingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */