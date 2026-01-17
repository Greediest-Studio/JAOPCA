/*   */ package thelm.jaopca.api.fluids;
/*   */ 
/*   */ import net.minecraft.block.Block;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormFluidBlock
/*   */   extends IMaterialForm {
/*   */   default Block toBlock() {
/* 9 */     return (Block)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IMaterialFormFluidBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */