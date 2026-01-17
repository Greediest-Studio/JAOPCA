//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.futurepack;
/*    */ 
/*    */ import futurepack.api.ItemPredicates;
/*    */ import futurepack.depend.api.ItemStackPredicate;
/*    */ import futurepack.depend.api.OreDictPredicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.futurepack.recipes.ZentrifugeRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class FuturepackHelper
/*    */ {
/* 21 */   public static final FuturepackHelper INSTANCE = new FuturepackHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemPredicates getItemPredicates(Object obj, int count) {
/* 26 */     if (obj instanceof Supplier) {
/* 27 */       return getItemPredicates(((Supplier)obj).get(), count);
/*    */     }
/* 29 */     if (obj instanceof ItemPredicates) {
/* 30 */       return (ItemPredicates)obj;
/*    */     }
/* 32 */     if (obj instanceof String) {
/* 33 */       if (ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 34 */         return (ItemPredicates)new OreDictPredicate((String)obj, count);
/*    */       }
/*    */     }
/* 37 */     else if (obj instanceof ItemStack) {
/* 38 */       ItemStack stack = (ItemStack)obj;
/* 39 */       if (!stack.isEmpty()) {
/* 40 */         return (ItemPredicates)new ItemStackPredicate(MiscHelper.INSTANCE.resizeItemStack(stack, count));
/*    */       }
/*    */     }
/* 43 */     else if (obj instanceof Item) {
/* 44 */       if (obj != Items.AIR) {
/* 45 */         return (ItemPredicates)new ItemStackPredicate(new ItemStack((Item)obj, count, 32767));
/*    */       }
/*    */     }
/* 48 */     else if (obj instanceof Block) {
/* 49 */       if (obj != Blocks.AIR) {
/* 50 */         return (ItemPredicates)new ItemStackPredicate(new ItemStack(Item.getItemFromBlock((Block)obj), count, 32767));
/*    */       }
/*    */     }
/* 53 */     else if (obj instanceof IItemProvider) {
/* 54 */       Item item = ((IItemProvider)obj).asItem();
/* 55 */       if (item != Items.AIR) {
/* 56 */         return (ItemPredicates)new ItemStackPredicate(new ItemStack(item, count, 32767));
/*    */       }
/*    */     } 
/* 59 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerZentrifugeRecipe(ResourceLocation key, Object input, int inputCount, int support, int time, Object... output) {
/* 63 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new ZentrifugeRecipeAction(key, input, inputCount, support, time, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\futurepack\FuturepackHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
