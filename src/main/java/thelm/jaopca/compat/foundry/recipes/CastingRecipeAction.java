//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.foundry.recipes;
/*    */ 
/*    */ import exter.foundry.api.FoundryAPI;
/*    */ import exter.foundry.api.recipe.matcher.IItemMatcher;
/*    */ import exter.foundry.api.recipe.matcher.ItemStackMatcher;
/*    */ import java.util.Objects;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.FoundryHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CastingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object fluidInput;
/*    */   public final int fluidInputAmount;
/*    */   public final Object mold;
/*    */   public final Object itemInput;
/*    */   public final int itemInputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final ToIntFunction<FluidStack> speed;
/*    */   
/*    */   public CastingRecipeAction(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object mold, Object output, int outputCount, ToIntFunction<FluidStack> speed) {
/* 35 */     this(key, fluidInput, fluidInputAmount, mold, ItemStack.EMPTY, 0, output, outputCount, speed);
/*    */   }
/*    */   
/*    */   public CastingRecipeAction(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object mold, Object itemInput, int itemInputCount, Object output, int outputCount, ToIntFunction<FluidStack> speed) {
/* 39 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 40 */     this.fluidInput = fluidInput;
/* 41 */     this.fluidInputAmount = fluidInputAmount;
/* 42 */     this.mold = mold;
/* 43 */     this.itemInput = itemInput;
/* 44 */     this.itemInputCount = itemInputCount;
/* 45 */     this.output = output;
/* 46 */     this.outputCount = outputCount;
/* 47 */     this.speed = speed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 52 */     FluidStack fluidIng = MiscHelper.INSTANCE.getFluidStack(this.fluidInput, this.fluidInputAmount);
/* 53 */     if (fluidIng == null) {
/* 54 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.fluidInput);
/*    */     }
/* 56 */     Ingredient moldIng = MiscHelper.INSTANCE.getIngredient(this.mold);
/* 57 */     if (moldIng == null) {
/* 58 */       throw new IllegalArgumentException("Empty mold in recipe " + this.key + ": " + this.mold);
/*    */     }
/* 60 */     IItemMatcher itemIng = FoundryHelper.INSTANCE.getItemMatcher(this.itemInput, this.itemInputCount);
/* 61 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 62 */     if (stack.isEmpty()) {
/* 63 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 65 */     for (ItemStack moldIn : moldIng.getMatchingStacks()) {
/* 66 */       FoundryAPI.CASTING_MANAGER.addRecipe((IItemMatcher)new ItemStackMatcher(stack), fluidIng, moldIn.copy(), itemIng, this.speed.applyAsInt(fluidIng));
/*    */     }
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\recipes\CastingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
