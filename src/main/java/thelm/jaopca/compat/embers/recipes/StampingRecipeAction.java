//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.embers.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import teamroots.embers.recipe.ItemStampingRecipe;
/*    */ import teamroots.embers.recipe.RecipeRegistry;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class StampingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object itemInput;
/*    */   public final Object fluidInput;
/*    */   public final int fluidInputAmount;
/*    */   public final Object stamp;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public StampingRecipeAction(ResourceLocation key, Object itemInput, Object stamp, Object output, int outputCount) {
/* 30 */     this(key, itemInput, null, 0, stamp, output, outputCount);
/*    */   }
/*    */   
/*    */   public StampingRecipeAction(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object stamp, Object output, int outputCount) {
/* 34 */     this(key, ItemStack.EMPTY, fluidInput, fluidInputAmount, stamp, output, outputCount);
/*    */   }
/*    */   
/*    */   public StampingRecipeAction(ResourceLocation key, Object itemInput, Object fluidInput, int fluidInputAmount, Object stamp, Object output, int outputCount) {
/* 38 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 39 */     this.itemInput = itemInput;
/* 40 */     this.fluidInput = fluidInput;
/* 41 */     this.fluidInputAmount = fluidInputAmount;
/* 42 */     this.stamp = stamp;
/* 43 */     this.output = output;
/* 44 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 49 */     Ingredient itemIng = MiscHelper.INSTANCE.getIngredient(this.itemInput);
/* 50 */     FluidStack fluidIng = MiscHelper.INSTANCE.getFluidStack(this.fluidInput, this.fluidInputAmount);
/* 51 */     if (itemIng == null && fluidIng == null) {
/* 52 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + this.itemInput + ", " + this.fluidInput);
/*    */     }
/* 54 */     Ingredient stampIng = MiscHelper.INSTANCE.getIngredient(this.stamp);
/* 55 */     if (stampIng == null) {
/* 56 */       throw new IllegalArgumentException("Empty stamp in recipe " + this.key + ": " + this.stamp);
/*    */     }
/* 58 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 59 */     if (stack.isEmpty()) {
/* 60 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 62 */     if (itemIng == null) {
/* 63 */       itemIng = Ingredient.EMPTY;
/*    */     }
/* 65 */     return RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(itemIng, fluidIng, stampIng, stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\embers\recipes\StampingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
