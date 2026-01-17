//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.astralsorcery;
/*    */ 
/*    */ import hellfirepvp.astralsorcery.common.crafting.ItemHandle;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.astralsorcery.recipes.InfusionRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class AstralSorceryHelper
/*    */ {
/* 18 */   public static final AstralSorceryHelper INSTANCE = new AstralSorceryHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemHandle getItemHandle(Object obj) {
/* 23 */     if (obj instanceof Supplier) {
/* 24 */       return getItemHandle(((Supplier)obj).get());
/*    */     }
/* 26 */     if (obj instanceof String && 
/* 27 */       ApiImpl.INSTANCE.getOredict().contains(obj)) {
/* 28 */       return new ItemHandle((String)obj);
/*    */     }
/*    */     
/* 31 */     if (obj instanceof ItemStack) {
/* 32 */       ItemStack stack = (ItemStack)obj;
/* 33 */       if (!stack.isEmpty()) {
/* 34 */         return new ItemHandle(stack);
/*    */       }
/*    */     } 
/* 37 */     if (obj instanceof Item && 
/* 38 */       obj != Items.AIR) {
/* 39 */       return new ItemHandle(new ItemStack((Item)obj));
/*    */     }
/*    */     
/* 42 */     if (obj instanceof FluidStack) {
/* 43 */       return new ItemHandle((FluidStack)obj);
/*    */     }
/* 45 */     if (obj instanceof Fluid) {
/* 46 */       return new ItemHandle((Fluid)obj);
/*    */     }
/* 48 */     if (obj instanceof Ingredient) {
/* 49 */       return ItemHandle.of((Ingredient)obj);
/*    */     }
/* 51 */     if (obj instanceof ItemHandle) {
/* 52 */       return (ItemHandle)obj;
/*    */     }
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerInfusionRecipe(ResourceLocation key, Object input, Object output, int outputCount, float consumptionChance, boolean consumeMultiple, boolean acceptsChalices) {
/* 58 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new InfusionRecipeAction(key, input, output, outputCount, consumptionChance, consumeMultiple, acceptsChalices));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\astralsorcery\AstralSorceryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
