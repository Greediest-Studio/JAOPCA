/*   */ package thelm.jaopca.api.blocks;
/*   */ 
/*   */ import net.minecraft.block.Block;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormBlock
/*   */   extends IMaterialForm {
/*   */   default Block toBlock() {
/* 9 */     return (Block)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IMaterialFormBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */