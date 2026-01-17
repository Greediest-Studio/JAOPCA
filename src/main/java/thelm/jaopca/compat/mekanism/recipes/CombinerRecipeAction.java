//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.mekanism.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import mekanism.api.MekanismAPI;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CombinerRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object secondInput;
/*    */   public final int secondInputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public CombinerRecipeAction(ResourceLocation key, Object input, int inputCount, Object secondInput, int secondInputCount, Object output, int outputCount) {
/* 28 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 29 */     this.input = input;
/* 30 */     this.inputCount = inputCount;
/* 31 */     this.secondInput = secondInput;
/* 32 */     this.secondInputCount = secondInputCount;
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
/* 43 */     Ingredient secondIng = MiscHelper.INSTANCE.getIngredient(this.secondInput);
/* 44 */     if (secondIng == null) {
/* 45 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.secondInput);
/*    */     }
/* 47 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 48 */     if (stack.isEmpty()) {
/* 49 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 51 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 52 */       for (ItemStack in2 : secondIng.getMatchingStacks()) {
/* 53 */         MekanismAPI.recipeHelper().addCombinerRecipe(MiscHelper.INSTANCE
/* 54 */             .resizeItemStack(in, this.inputCount), MiscHelper.INSTANCE.resizeItemStack(in2, this.secondInputCount), stack);
/*    */       }
/*    */     } 
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\recipes\CombinerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
