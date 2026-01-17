//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.ic2.recipes;
/*    */ 
/*    */ import ic2.api.recipe.IRecipeInput;
/*    */ import ic2.api.recipe.Recipes;
/*    */ import java.util.Collections;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.ic2.IC2Helper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CompressorRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public CompressorRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/* 28 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 29 */     this.input = input;
/* 30 */     this.inputCount = inputCount;
/* 31 */     this.output = output;
/* 32 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 37 */     IRecipeInput ing = IC2Helper.INSTANCE.getRecipeInput(this.input, this.inputCount);
/* 38 */     if (ing == null) {
/* 39 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 41 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 42 */     if (stack.isEmpty()) {
/* 43 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 45 */     return Recipes.compressor.addRecipe(ing, Collections.singletonList(stack), null, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\recipes\CompressorRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
