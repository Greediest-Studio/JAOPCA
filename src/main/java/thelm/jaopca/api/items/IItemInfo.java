/*    */ package thelm.jaopca.api.items;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ 
/*    */ public interface IItemInfo
/*    */   extends IMaterialFormInfo, IItemProvider
/*    */ {
/*    */   IMaterialFormItem getMaterialFormItem();
/*    */   
/*    */   default Item asItem() {
/* 13 */     return getMaterialFormItem().toItem();
/*    */   }
/*    */ 
/*    */   
/*    */   default IMaterialForm getMaterialForm() {
/* 18 */     return getMaterialFormItem();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\items\IItemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */