/*   */ package thelm.jaopca.api.blocks;
/*   */ 
/*   */ import net.minecraft.item.ItemBlock;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormBlockItem
/*   */   extends IMaterialForm {
/*   */   default ItemBlock toBlockItem() {
/* 9 */     return (ItemBlock)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IMaterialFormBlockItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */