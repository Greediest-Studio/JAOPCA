//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.foundry;
/*    */ 
/*    */ import exter.foundry.api.recipe.matcher.IItemMatcher;
/*    */ import exter.foundry.api.recipe.matcher.ItemStackMatcher;
/*    */ import exter.foundry.api.recipe.matcher.OreMatcher;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.recipes.AtomizerRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.recipes.CastingRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.recipes.CastingTableRecipeAction;
/*    */ import thelm.jaopca.compat.foundry.recipes.MeltingRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class FoundryHelper
/*    */ {
/* 26 */   public static final FoundryHelper INSTANCE = new FoundryHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public IItemMatcher getItemMatcher(Object obj, int count) {
/* 31 */     if (obj instanceof Supplier) {
/* 32 */       return getItemMatcher(((Supplier)obj).get(), count);
/*    */     }
/* 34 */     if (obj instanceof IItemMatcher) {
/* 35 */       return (IItemMatcher)obj;
/*    */     }
/* 37 */     if (obj instanceof String) {
/* 38 */       if (ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 39 */         return (IItemMatcher)new OreMatcher((String)obj, count);
/*    */       }
/*    */     }
/* 42 */     else if (obj instanceof ItemStack) {
/* 43 */       ItemStack stack = (ItemStack)obj;
/* 44 */       if (!stack.isEmpty()) {
/* 45 */         return (IItemMatcher)new ItemStackMatcher(MiscHelper.INSTANCE.resizeItemStack(stack, count));
/*    */       }
/*    */     }
/* 48 */     else if (obj instanceof Item) {
/* 49 */       if (obj != Items.AIR) {
/* 50 */         return (IItemMatcher)new ItemStackMatcher(new ItemStack((Item)obj, count, 32767));
/*    */       }
/*    */     }
/* 53 */     else if (obj instanceof Block) {
/* 54 */       if (obj != Blocks.AIR) {
/* 55 */         return (IItemMatcher)new ItemStackMatcher(new ItemStack(Item.getItemFromBlock((Block)obj), count, 32767));
/*    */       }
/*    */     }
/* 58 */     else if (obj instanceof IItemProvider) {
/* 59 */       Item item = ((IItemProvider)obj).asItem();
/* 60 */       if (item != Items.AIR) {
/* 61 */         return (IItemMatcher)new ItemStackMatcher(new ItemStack(item, count, 32767));
/*    */       }
/*    */     } 
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerMeltingRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputAmount, ToIntFunction<FluidStack> temperature, ToIntFunction<FluidStack> speed) {
/* 68 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MeltingRecipeAction(key, input, inputCount, output, outputAmount, temperature, speed));
/*    */   }
/*    */   
/*    */   public boolean registerCastingRecipe(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object mold, Object itemInput, int itemInputCount, Object output, int outputCount, ToIntFunction<FluidStack> speed) {
/* 72 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CastingRecipeAction(key, fluidInput, fluidInputAmount, mold, itemInput, itemInputCount, output, outputCount, speed));
/*    */   }
/*    */   
/*    */   public boolean registerCastingRecipe(ResourceLocation key, Object fluidInput, int fluidInputAmount, Object mold, Object output, int outputCount, ToIntFunction<FluidStack> speed) {
/* 76 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CastingRecipeAction(key, fluidInput, fluidInputAmount, mold, output, outputCount, speed));
/*    */   }
/*    */   
/*    */   public boolean registerCastingTableRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount, String type) {
/* 80 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CastingTableRecipeAction(key, input, inputAmount, output, outputCount, type));
/*    */   }
/*    */   
/*    */   public boolean registerAtomizerRecipe(ResourceLocation key, Object input, int inputAmount, Object output, int outputCount) {
/* 84 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new AtomizerRecipeAction(key, input, inputAmount, output, outputCount));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\FoundryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
