//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.mekanism.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import mekanism.api.MekanismAPI;
/*    */ import mekanism.api.gas.GasStack;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.mekanism.MekanismHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class ChemicalDissolutionChamberRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   
/*    */   public ChemicalDissolutionChamberRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputAmount) {
/* 28 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 29 */     this.input = input;
/* 30 */     this.inputCount = inputCount;
/* 31 */     this.output = output;
/* 32 */     this.outputAmount = outputAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 37 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 38 */     if (ing == null) {
/* 39 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 41 */     GasStack stack = MekanismHelper.INSTANCE.getGasStack(this.output, this.outputAmount);
/* 42 */     if (stack == null) {
/* 43 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 45 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 46 */       MekanismAPI.recipeHelper().addChemicalDissolutionChamberRecipe(MiscHelper.INSTANCE
/* 47 */           .resizeItemStack(in, this.inputCount), stack);
/*    */     }
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\recipes\ChemicalDissolutionChamberRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
