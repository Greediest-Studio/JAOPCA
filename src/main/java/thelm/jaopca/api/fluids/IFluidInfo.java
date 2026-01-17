/*    */ package thelm.jaopca.api.fluids;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import thelm.jaopca.api.blocks.IBlockProvider;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IFluidInfo
/*    */   extends IMaterialFormInfo, IFluidProvider, IBlockProvider
/*    */ {
/*    */   default Fluid asFluid() {
/* 16 */     return getMaterialFormFluid().toFluid();
/*    */   }
/*    */ 
/*    */   
/*    */   default Block asBlock() {
/* 21 */     return getMaterialFormFluidBlock().toBlock();
/*    */   }
/*    */ 
/*    */   
/*    */   default IMaterialFormFluid getMaterialForm() {
/* 26 */     return getMaterialFormFluid();
/*    */   }
/*    */   
/*    */   IMaterialFormFluid getMaterialFormFluid();
/*    */   
/*    */   IMaterialFormFluidBlock getMaterialFormFluidBlock();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IFluidInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */