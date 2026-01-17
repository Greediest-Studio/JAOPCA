//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.minestrapp.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import minestrapp.crafting.CrusherRecipes;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CrusherRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputCount;
/*    */   public final int secondOutputChance;
/*    */   public final float experience;
/*    */   
/*    */   public CrusherRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float experience) {
/* 29 */     this(key, input, output, outputCount, ItemStack.EMPTY, 0, 0, experience);
/*    */   }
/*    */   
/*    */   public CrusherRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, int secondOutputChance, float experience) {
/* 33 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 34 */     this.input = input;
/* 35 */     this.output = output;
/* 36 */     this.outputCount = outputCount;
/* 37 */     this.secondOutput = secondOutput;
/* 38 */     this.secondOutputCount = secondOutputCount;
/* 39 */     this.secondOutputChance = secondOutputChance;
/* 40 */     this.experience = experience;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 45 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 46 */     if (ing == null) {
/* 47 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 49 */     ItemStack stack1 = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 50 */     if (stack1.isEmpty()) {
/* 51 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 53 */     ItemStack stack2 = MiscHelper.INSTANCE.getItemStack(this.secondOutput, this.secondOutputCount);
/* 54 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 55 */       CrusherRecipes.instance().addCrusherRecipe(in, stack1, stack2, Integer.valueOf(this.secondOutputChance), this.experience);
/*    */     }
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\minestrapp\recipes\CrusherRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
