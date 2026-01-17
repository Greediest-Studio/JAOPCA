/*    */ package thelm.jaopca.compat.tconstruct.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import slimeknights.mantle.util.RecipeMatch;
/*    */ import slimeknights.tconstruct.library.TinkerRegistry;
/*    */ import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.tconstruct.TConstructHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class MeltingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   public final ToIntFunction<FluidStack> temperature;
/*    */   
/*    */   public MeltingRecipeAction(ResourceLocation key, Object input, Object output, int outputAmount, ToIntFunction<FluidStack> temperature) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.output = output;
/* 32 */     this.outputAmount = outputAmount;
/* 33 */     this.temperature = temperature;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     RecipeMatch ing = TConstructHelper.INSTANCE.getRecipeMatch(this.input, 1, this.outputAmount);
/* 39 */     if (ing == null) {
/* 40 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 42 */     FluidStack stack = MiscHelper.INSTANCE.getFluidStack(this.output, this.outputAmount);
/* 43 */     if (stack == null) {
/* 44 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 46 */     TinkerRegistry.registerMelting(new MeltingRecipe(ing, stack, this.temperature.applyAsInt(stack)));
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\tconstruct\recipes\MeltingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */