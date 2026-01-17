//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.bcoreprocessing.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.ndrei.bcoreprocessing.api.recipes.OreProcessingRecipes;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class FluidProcessorRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final Object fluidOutput;
/*    */   public final int fluidOutputAmount;
/*    */   public final int time;
/*    */   
/*    */   public FluidProcessorRecipeAction(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount, Object fluidOutput, int fluidOutputAmount, int time) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.inputAmount = inputAmount;
/* 32 */     this.output = output;
/* 33 */     this.outputCount = outputCount;
/* 34 */     this.fluidOutput = fluidOutput;
/* 35 */     this.fluidOutputAmount = fluidOutputAmount;
/* 36 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     FluidStack ing = MiscHelper.INSTANCE.getFluidStack(this.input, this.inputAmount);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 45 */     ItemStack itemStack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 46 */     FluidStack fluidStack = MiscHelper.INSTANCE.getFluidStack(this.fluidOutput, this.fluidOutputAmount);
/* 47 */     if (itemStack.isEmpty() && fluidStack == null) {
/* 48 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + this.output + ", " + this.fluidOutput);
/*    */     }
/* 50 */     OreProcessingRecipes.INSTANCE.getFluidProcessorRecipes().registerSimpleRecipe(ing, itemStack, fluidStack, this.time);
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\bcoreprocessing\recipes\FluidProcessorRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
