//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.groovyscript;
/*    */ 
/*    */ import com.cleanroommc.groovyscript.helper.ingredient.OreDictIngredient;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thelm.jaopca.api.blocks.IBlockProvider;
/*    */ import thelm.jaopca.api.fluids.IFluidProvider;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class MaterialFormInfoExpansion
/*    */ {
/*    */   public static IForm getForm(IMaterialFormInfo info) {
/* 20 */     return info.getMaterialForm().getForm();
/*    */   }
/*    */   
/*    */   public static IMaterial getMaterial(IMaterialFormInfo info) {
/* 24 */     return info.getMaterialForm().getMaterial();
/*    */   }
/*    */   
/*    */   public static OreDictIngredient asOre(IMaterialFormInfo info) {
/* 28 */     return new OreDictIngredient(MiscHelper.INSTANCE.getOredictName(info.getMaterialForm().getForm().getSecondaryName(), info.getMaterialForm().getMaterial().getName()));
/*    */   }
/*    */   
/*    */   public static ItemStack asItem(IMaterialFormInfo info, int count) {
/* 32 */     if (info instanceof IItemProvider) {
/* 33 */       return new ItemStack(((IItemProvider)info).asItem(), count);
/*    */     }
/* 35 */     return ItemStack.EMPTY;
/*    */   }
/*    */   
/*    */   public static ItemStack asItem(IMaterialFormInfo info) {
/* 39 */     return asItem(info, 1);
/*    */   }
/*    */   
/*    */   public static FluidStack asFluid(IMaterialFormInfo info, int amount) {
/* 43 */     if (info instanceof IFluidProvider) {
/* 44 */       return new FluidStack(((IFluidProvider)info).asFluid(), amount);
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */   
/*    */   public static FluidStack asLiquid(IMaterialFormInfo info, int amount) {
/* 50 */     return asFluid(info, amount);
/*    */   }
/*    */   
/*    */   public static Block asBlock(IMaterialFormInfo info) {
/* 54 */     if (info instanceof IBlockProvider) {
/* 55 */       return ((IBlockProvider)info).asBlock();
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   public static IBlockState asBlockState(IMaterialFormInfo info) {
/* 61 */     if (info instanceof IBlockProvider) {
/* 62 */       return ((IBlockProvider)info).asBlock().getDefaultState();
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\groovyscript\MaterialFormInfoExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
