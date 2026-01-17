/*    */ package thelm.jaopca.items;
/*    */ 
/*    */ import thelm.jaopca.api.items.IItemInfo;
/*    */ import thelm.jaopca.api.items.IMaterialFormItem;
/*    */ 
/*    */ class ItemInfo
/*    */   implements IItemInfo {
/*    */   private final IMaterialFormItem item;
/*    */   
/*    */   ItemInfo(IMaterialFormItem item) {
/* 11 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormItem getMaterialFormItem() {
/* 16 */     return this.item;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\items\ItemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */