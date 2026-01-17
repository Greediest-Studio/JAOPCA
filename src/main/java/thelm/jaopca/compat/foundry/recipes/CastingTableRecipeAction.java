//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.foundry.recipes;
/*    */ 
/*    */ import exter.foundry.api.FoundryAPI;
/*    */ import exter.foundry.api.recipe.ICastingTableRecipe;
/*    */ import exter.foundry.api.recipe.matcher.IItemMatcher;
/*    */ import exter.foundry.api.recipe.matcher.ItemStackMatcher;
/*    */ import java.util.Locale;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class CastingTableRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final ICastingTableRecipe.TableType type;
/*    */   
/*    */   public CastingTableRecipeAction(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount, String type) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.inputAmount = inputAmount;
/* 33 */     this.output = output;
/* 34 */     this.outputCount = outputCount;
/* 35 */     this.type = Objects.<ICastingTableRecipe.TableType>requireNonNull(ICastingTableRecipe.TableType.valueOf(type.toUpperCase(Locale.US)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 40 */     FluidStack ing = MiscHelper.INSTANCE.getFluidStack(this.input, this.inputAmount);
/* 41 */     if (ing == null) {
/* 42 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 44 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 45 */     if (stack.isEmpty()) {
/* 46 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 48 */     FoundryAPI.CASTING_TABLE_MANAGER.addRecipe((IItemMatcher)new ItemStackMatcher(stack), ing, this.type);
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\recipes\CastingTableRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
