/*    */ package thelm.jaopca.blocks;
/*    */ 
/*    */ import thelm.jaopca.api.blocks.IBlockInfo;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*    */ 
/*    */ class BlockInfo
/*    */   implements IBlockInfo {
/*    */   private final IMaterialFormBlock block;
/*    */   private final IMaterialFormBlockItem blockItem;
/*    */   
/*    */   BlockInfo(IMaterialFormBlock block, IMaterialFormBlockItem blockItem) {
/* 13 */     this.block = block;
/* 14 */     this.blockItem = blockItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormBlock getMaterialFormBlock() {
/* 19 */     return this.block;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormBlockItem getMaterialFormBlockItem() {
/* 24 */     return this.blockItem;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\blocks\BlockInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */