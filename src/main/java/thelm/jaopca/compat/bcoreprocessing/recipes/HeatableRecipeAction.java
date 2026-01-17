/*    */ package thelm.jaopca.compat.bcoreprocessing.recipes;
/*    */ 
/*    */ import buildcraft.api.recipes.BuildcraftRecipeRegistry;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class HeatableRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 16 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   public final int heatFrom;
/*    */   public final int heatTo;
/*    */   
/*    */   public HeatableRecipeAction(ResourceLocation key, Object input, int inputAmount, Object output, int outputAmount, int heatFrom, int heatTo) {
/* 27 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 28 */     this.input = input;
/* 29 */     this.inputAmount = inputAmount;
/* 30 */     this.output = output;
/* 31 */     this.outputAmount = outputAmount;
/* 32 */     this.heatFrom = heatFrom;
/* 33 */     this.heatTo = heatTo;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     FluidStack ing = MiscHelper.INSTANCE.getFluidStack(this.input, this.inputAmount);
/* 39 */     if (ing == null) {
/* 40 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 42 */     FluidStack stack = MiscHelper.INSTANCE.getFluidStack(this.output, this.outputAmount);
/* 43 */     if (stack == null) {
/* 44 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 46 */     BuildcraftRecipeRegistry.refineryRecipes.addHeatableRecipe(ing, stack, this.heatFrom, this.heatTo);
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\bcoreprocessing\recipes\HeatableRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */