/*    */ package thelm.jaopca.api.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ 
/*    */ public interface IBlockInfo
/*    */   extends IMaterialFormInfo, IBlockProvider, IItemProvider
/*    */ {
/*    */   IMaterialFormBlock getMaterialFormBlock();
/*    */   
/*    */   IMaterialFormBlockItem getMaterialFormBlockItem();
/*    */   
/*    */   default Block asBlock() {
/* 18 */     return getMaterialFormBlock().toBlock();
/*    */   }
/*    */   
/*    */   default ItemBlock asBlockItem() {
/* 22 */     return getMaterialFormBlockItem().toBlockItem();
/*    */   }
/*    */ 
/*    */   
/*    */   default Item asItem() {
/* 27 */     return (Item)asBlockItem();
/*    */   }
/*    */ 
/*    */   
/*    */   default IMaterialForm getMaterialForm() {
/* 32 */     return getMaterialFormBlock();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IBlockInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */