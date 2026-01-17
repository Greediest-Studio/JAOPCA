//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.bcoreprocessing.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import kotlin.TuplesKt;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.ndrei.bcoreprocessing.api.recipes.OreProcessingRecipes;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class OreProcessorRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputAmount;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputAmount;
/*    */   public final int time;
/*    */   
/*    */   public OreProcessorRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputAmount, Object secondOutput, int secondOutputAmount, int time) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.input = input;
/* 33 */     this.inputCount = inputCount;
/* 34 */     this.output = output;
/* 35 */     this.outputAmount = outputAmount;
/* 36 */     this.secondOutput = secondOutput;
/* 37 */     this.secondOutputAmount = secondOutputAmount;
/* 38 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 43 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 44 */     if (ing == null) {
/* 45 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 47 */     FluidStack stack1 = MiscHelper.INSTANCE.getFluidStack(this.output, this.outputAmount);
/* 48 */     FluidStack stack2 = MiscHelper.INSTANCE.getFluidStack(this.secondOutput, this.secondOutputAmount);
/* 49 */     if (stack1 == null && stack2 == null) {
/* 50 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + this.output + ", " + this.secondOutput);
/*    */     }
/* 52 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 53 */       OreProcessingRecipes.INSTANCE.getOreProcessorRecipes().registerSimpleRecipe(MiscHelper.INSTANCE
/* 54 */           .resizeItemStack(in, this.inputCount), TuplesKt.to(stack1, stack2), this.inputCount);
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\bcoreprocessing\recipes\OreProcessorRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
