/*   */ package thelm.jaopca.compat.mekanism.api.gases;
/*   */ 
/*   */ import mekanism.api.gas.Gas;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormGas
/*   */   extends IMaterialForm {
/*   */   default Gas toGas() {
/* 9 */     return (Gas)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\api\gases\IMaterialFormGas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */