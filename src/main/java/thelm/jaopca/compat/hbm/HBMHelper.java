//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.hbm;
/*    */ 
/*    */ import com.hbm.inventory.RecipesCommon;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.hbm.recipes.AnvilConstructionRecipeAction;
/*    */ import thelm.jaopca.compat.hbm.recipes.CentrifugeRecipeAction;
/*    */ import thelm.jaopca.compat.hbm.recipes.CrystallizerRecipeAction;
/*    */ import thelm.jaopca.compat.hbm.recipes.ShredderRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class HBMHelper
/*    */ {
/* 23 */   public static final HBMHelper INSTANCE = new HBMHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipesCommon.AStack getAStack(Object obj, int count) {
/* 28 */     if (obj instanceof Supplier) {
/* 29 */       return getAStack(((Supplier)obj).get(), count);
/*    */     }
/* 31 */     if (obj instanceof String && 
/* 32 */       ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 33 */       return (RecipesCommon.AStack)new RecipesCommon.OreDictStack((String)obj, count);
/*    */     }
/*    */     
/* 36 */     if (obj instanceof ItemStack) {
/* 37 */       ItemStack stack = (ItemStack)obj;
/* 38 */       if (!stack.isEmpty()) {
/* 39 */         return (RecipesCommon.AStack)new RecipesCommon.ComparableStack(MiscHelper.INSTANCE.resizeItemStack(stack, count));
/*    */       }
/*    */     } 
/* 42 */     if (obj instanceof Item && 
/* 43 */       obj != Items.AIR) {
/* 44 */       return (RecipesCommon.AStack)new RecipesCommon.ComparableStack(new ItemStack((Item)obj, count));
/*    */     }
/*    */     
/* 47 */     if (obj instanceof Block && 
/* 48 */       obj != Blocks.AIR) {
/* 49 */       return (RecipesCommon.AStack)new RecipesCommon.ComparableStack(new ItemStack((Block)obj, count));
/*    */     }
/*    */     
/* 52 */     if (obj instanceof IItemProvider) {
/* 53 */       Item item = ((IItemProvider)obj).asItem();
/* 54 */       if (item != Items.AIR) {
/* 55 */         return (RecipesCommon.AStack)new RecipesCommon.ComparableStack(new ItemStack(((IItemProvider)obj).asItem(), count));
/*    */       }
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerCrystallizerRecipe(ResourceLocation key, Object input, Object fluidInput, int fluidInputAmount, Object output, int count) {
/* 62 */     return ApiImpl.INSTANCE.registerLateRecipe(key, (IRecipeAction)new CrystallizerRecipeAction(key, input, fluidInput, fluidInputAmount, output, count));
/*    */   }
/*    */   
/*    */   public boolean registerShredderRecipe(ResourceLocation key, Object input, Object output, int count) {
/* 66 */     return ApiImpl.INSTANCE.registerLateRecipe(key, (IRecipeAction)new ShredderRecipeAction(key, input, output, count));
/*    */   }
/*    */   
/*    */   public boolean registerCentrifugeRecipe(ResourceLocation key, Object input, Object... output) {
/* 70 */     return ApiImpl.INSTANCE.registerLateRecipe(key, (IRecipeAction)new CentrifugeRecipeAction(key, input, output));
/*    */   }
/*    */   
/*    */   public boolean registerAnvilConstructionRecipe(ResourceLocation key, Object[] input, Object[] output, int tier) {
/* 74 */     return ApiImpl.INSTANCE.registerLateRecipe(key, (IRecipeAction)new AnvilConstructionRecipeAction(key, input, output, tier));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\HBMHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
