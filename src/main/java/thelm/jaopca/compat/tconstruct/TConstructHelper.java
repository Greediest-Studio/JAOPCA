//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.tconstruct;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import slimeknights.mantle.util.RecipeMatch;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.tconstruct.recipes.BasinCastingRecipeAction;
/*    */ import thelm.jaopca.compat.tconstruct.recipes.MeltingRecipeAction;
/*    */ import thelm.jaopca.compat.tconstruct.recipes.TableCastingRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class TConstructHelper
/*    */ {
/* 22 */   public static final TConstructHelper INSTANCE = new TConstructHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeMatch getRecipeMatch(Object obj, int count, int matched) {
/* 27 */     if (obj instanceof Supplier) {
/* 28 */       return getRecipeMatch(((Supplier)obj).get(), count, matched);
/*    */     }
/* 30 */     if (obj instanceof RecipeMatch) {
/* 31 */       return (RecipeMatch)obj;
/*    */     }
/* 33 */     if (obj instanceof String) {
/* 34 */       if (ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 35 */         return RecipeMatch.of((String)obj, count, matched);
/*    */       }
/*    */     }
/* 38 */     else if (obj instanceof ItemStack) {
/* 39 */       ItemStack stack = (ItemStack)obj;
/* 40 */       if (!stack.isEmpty()) {
/* 41 */         return RecipeMatch.of((ItemStack)obj, count, matched);
/*    */       }
/*    */     }
/* 44 */     else if (obj instanceof Item) {
/* 45 */       if (obj != Items.AIR) {
/* 46 */         return RecipeMatch.of((Item)obj, count, matched);
/*    */       }
/*    */     }
/* 49 */     else if (obj instanceof Block) {
/* 50 */       if (obj != Blocks.AIR) {
/* 51 */         return RecipeMatch.of(Item.getItemFromBlock((Block)obj), count, matched);
/*    */       }
/*    */     }
/* 54 */     else if (obj instanceof IItemProvider) {
/* 55 */       Item item = ((IItemProvider)obj).asItem();
/* 56 */       if (item != Items.AIR) {
/* 57 */         return RecipeMatch.of(item, count, matched);
/*    */       }
/*    */     } 
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerMeltingRecipe(ResourceLocation key, Object input, Object output, int outputAmount, ToIntFunction<FluidStack> temperature) {
/* 64 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MeltingRecipeAction(key, input, output, outputAmount, temperature));
/*    */   }
/*    */   
/*    */   public boolean registerTableCastingRecipe(ResourceLocation key, Object cast, Object input, int inputAmount, Object output, ToIntFunction<FluidStack> time, boolean consumeCast, boolean switchSlots) {
/* 68 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new TableCastingRecipeAction(key, cast, input, inputAmount, output, time, consumeCast, switchSlots));
/*    */   }
/*    */   
/*    */   public boolean registerBasinCastingRecipe(ResourceLocation key, Object cast, Object input, int inputAmount, Object output, ToIntFunction<FluidStack> time, boolean consumeCast, boolean switchSlots) {
/* 72 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new BasinCastingRecipeAction(key, cast, input, inputAmount, output, time, consumeCast, switchSlots));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\tconstruct\TConstructHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
