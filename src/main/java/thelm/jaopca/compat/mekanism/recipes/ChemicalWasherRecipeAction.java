/*    */ package thelm.jaopca.compat.mekanism.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import mekanism.api.MekanismAPI;
/*    */ import mekanism.api.gas.GasStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.MekanismHelper;
/*    */ 
/*    */ 
/*    */ public class ChemicalWasherRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 16 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   
/*    */   public ChemicalWasherRecipeAction(ResourceLocation key, Object input, int inputAmount, Object output, int outputAmount) {
/* 25 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 26 */     this.input = input;
/* 27 */     this.inputAmount = inputAmount;
/* 28 */     this.output = output;
/* 29 */     this.outputAmount = outputAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 34 */     GasStack ing = MekanismHelper.INSTANCE.getGasStack(this.input, this.inputAmount);
/* 35 */     if (ing == null) {
/* 36 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 38 */     GasStack stack = MekanismHelper.INSTANCE.getGasStack(this.output, this.outputAmount);
/* 39 */     if (stack == null) {
/* 40 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 42 */     MekanismAPI.recipeHelper().addChemicalWasherRecipe(ing, stack);
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\recipes\ChemicalWasherRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */