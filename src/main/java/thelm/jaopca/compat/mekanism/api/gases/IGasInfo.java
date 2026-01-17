/*    */ package thelm.jaopca.compat.mekanism.api.gases;
/*    */ 
/*    */ import mekanism.api.gas.Gas;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ 
/*    */ public interface IGasInfo
/*    */   extends IMaterialFormInfo, IGasProvider
/*    */ {
/*    */   IMaterialFormGas getMaterialFormGas();
/*    */   
/*    */   default Gas asGas() {
/* 13 */     return getMaterialFormGas().toGas();
/*    */   }
/*    */ 
/*    */   
/*    */   default IMaterialForm getMaterialForm() {
/* 18 */     return getMaterialFormGas();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\api\gases\IGasInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */