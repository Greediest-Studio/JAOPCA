//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.enderio;
/*    */ 
/*    */ import com.enderio.core.common.util.stackable.Things;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.enderio.recipes.SagMillRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ public class EnderIOHelper
/*    */ {
/* 19 */   public static final EnderIOHelper INSTANCE = new EnderIOHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public Things getThings(Object obj) {
/* 24 */     Things things = new Things();
/* 25 */     if (obj instanceof Supplier) {
/* 26 */       things.add(getThings(((Supplier)obj).get()));
/*    */     }
/* 28 */     else if (obj instanceof Things) {
/* 29 */       things.add((Things)obj);
/*    */     }
/* 31 */     else if (obj instanceof String) {
/* 32 */       if (ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 33 */         things.addOredict((String)obj);
/*    */       }
/*    */     }
/* 36 */     else if (obj instanceof ItemStack) {
/* 37 */       ItemStack stack = (ItemStack)obj;
/* 38 */       if (!stack.isEmpty()) {
/* 39 */         things.add((ItemStack)obj);
/*    */       }
/*    */     }
/* 42 */     else if (obj instanceof Item) {
/* 43 */       if (obj != Items.AIR) {
/* 44 */         things.add((Item)obj);
/*    */       }
/*    */     }
/* 47 */     else if (obj instanceof Block) {
/* 48 */       if (obj != Blocks.AIR) {
/* 49 */         things.add((Block)obj);
/*    */       }
/*    */     }
/* 52 */     else if (obj instanceof IItemProvider) {
/* 53 */       Item item = ((IItemProvider)obj).asItem();
/* 54 */       if (item != Items.AIR) {
/* 55 */         things.add(item);
/*    */       }
/*    */     } 
/* 58 */     return things;
/*    */   }
/*    */   
/*    */   public boolean registerSagMillRecipe(ResourceLocation key, Object input, int energy, String bonusType, String level, Object... output) {
/* 62 */     return ApiImpl.INSTANCE.registerLateRecipe(key, (IRecipeAction)new SagMillRecipeAction(key, input, energy, bonusType, level, output));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\enderio\EnderIOHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
