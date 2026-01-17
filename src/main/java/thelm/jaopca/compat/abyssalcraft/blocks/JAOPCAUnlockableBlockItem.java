/*    */ package thelm.jaopca.compat.abyssalcraft.blocks;
/*    */ 
/*    */ import com.shinoow.abyssalcraft.api.APIUtils;
/*    */ import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
/*    */ import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*    */ import thelm.jaopca.blocks.JAOPCABlockItem;
/*    */ 
/*    */ public class JAOPCAUnlockableBlockItem
/*    */   extends JAOPCABlockItem implements IUnlockableItem {
/*    */   private IUnlockCondition unlockCondition;
/*    */   
/*    */   public JAOPCAUnlockableBlockItem(IMaterialFormBlock block, IBlockFormSettings settings) {
/* 20 */     super(block, settings);
/*    */   }
/*    */ 
/*    */   
/*    */   public JAOPCAUnlockableBlockItem setUnlockCondition(IUnlockCondition unlockCondition) {
/* 25 */     this.unlockCondition = unlockCondition;
/* 26 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IUnlockCondition getUnlockCondition(ItemStack stack) {
/* 31 */     return this.unlockCondition;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public FontRenderer getFontRenderer(ItemStack stack) {
/* 37 */     return APIUtils.getFontRenderer(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\blocks\JAOPCAUnlockableBlockItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */