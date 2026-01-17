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
/*    */ public class ChemicalInjectionChamberRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object gasInput;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public ChemicalInjectionChamberRecipeAction(ResourceLocation key, Object input, int inputCount, Object gasInput, Object output, int outputCount) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.inputCount = inputCount;
/* 32 */     this.gasInput = gasInput;
/* 33 */     this.output = output;
/* 34 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 39 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 40 */     if (ing == null) {
/* 41 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 43 */     GasStack gasIng = MekanismHelper.INSTANCE.getGasStack(this.gasInput, 1);
/* 44 */     if (gasIng == null) {
/* 45 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.gasInput);
/*    */     }
/* 47 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 48 */     if (stack.isEmpty()) {
/* 49 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 51 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 52 */       MekanismAPI.recipeHelper().addChemicalInjectionChamberRecipe(MiscHelper.INSTANCE
/* 53 */           .resizeItemStack(in, this.inputCount), gasIng.getGas(), stack);
/*    */     }
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\recipes\ChemicalInjectionChamberRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
