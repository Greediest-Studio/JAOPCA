/*    */ package thelm.jaopca.fluids;
/*    */ 
/*    */ import thelm.jaopca.api.fluids.IFluidInfo;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluid;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*    */ 
/*    */ class FluidInfo
/*    */   implements IFluidInfo {
/*    */   private final IMaterialFormFluid fluid;
/*    */   private final IMaterialFormFluidBlock fluidBlock;
/*    */   
/*    */   FluidInfo(IMaterialFormFluid fluid, IMaterialFormFluidBlock fluidBlock) {
/* 13 */     this.fluid = fluid;
/* 14 */     this.fluidBlock = fluidBlock;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormFluid getMaterialFormFluid() {
/* 19 */     return this.fluid;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormFluidBlock getMaterialFormFluidBlock() {
/* 24 */     return this.fluidBlock;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\fluids\FluidInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */