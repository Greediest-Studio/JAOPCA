/*    */ package thelm.jaopca.compat.mekanism.gases;
/*    */ 
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasInfo;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IMaterialFormGas;
/*    */ 
/*    */ class GasInfo
/*    */   implements IGasInfo {
/*    */   private final IMaterialFormGas gas;
/*    */   
/*    */   GasInfo(IMaterialFormGas gas) {
/* 11 */     this.gas = gas;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterialFormGas getMaterialFormGas() {
/* 16 */     return this.gas;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\gases\GasInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */