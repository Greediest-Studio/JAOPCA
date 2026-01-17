/*    */ package thelm.jaopca.compat.abyssalcraft.items;
/*    */ 
/*    */ import com.shinoow.abyssalcraft.api.APIUtils;
/*    */ import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
/*    */ import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.items.JAOPCAItem;
/*    */ 
/*    */ public class JAOPCAUnlockableItem
/*    */   extends JAOPCAItem implements IUnlockableItem {
/*    */   private IUnlockCondition unlockCondition;
/*    */   
/*    */   public JAOPCAUnlockableItem(IForm form, IMaterial material, IItemFormSettings settings) {
/* 21 */     super(form, material, settings);
/*    */   }
/*    */ 
/*    */   
/*    */   public JAOPCAUnlockableItem setUnlockCondition(IUnlockCondition unlockCondition) {
/* 26 */     this.unlockCondition = unlockCondition;
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IUnlockCondition getUnlockCondition(ItemStack stack) {
/* 32 */     return this.unlockCondition;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public FontRenderer getFontRenderer(ItemStack stack) {
/* 38 */     return APIUtils.getFontRenderer(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\items\JAOPCAUnlockableItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */