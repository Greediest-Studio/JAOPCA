/*   */ package thelm.jaopca.api.items;
/*   */ 
/*   */ import net.minecraft.item.Item;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormItem
/*   */   extends IMaterialForm {
/*   */   default Item toItem() {
/* 9 */     return (Item)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\items\IMaterialFormItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */