//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.foundry.recipes;
/*    */ 
/*    */ import exter.foundry.api.FoundryAPI;
/*    */ import exter.foundry.api.recipe.matcher.IItemMatcher;
/*    */ import exter.foundry.api.recipe.matcher.ItemStackMatcher;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class AtomizerRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public AtomizerRecipeAction(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount) {
/* 27 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 28 */     this.input = input;
/* 29 */     this.inputAmount = inputAmount;
/* 30 */     this.output = output;
/* 31 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 36 */     FluidStack ing = MiscHelper.INSTANCE.getFluidStack(this.input, this.inputAmount);
/* 37 */     if (ing == null) {
/* 38 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 40 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 41 */     if (stack.isEmpty()) {
/* 42 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 44 */     FoundryAPI.ATOMIZER_MANAGER.addRecipe((IItemMatcher)new ItemStackMatcher(stack), ing);
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\recipes\AtomizerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
