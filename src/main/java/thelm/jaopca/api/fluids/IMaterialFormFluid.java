/*   */ package thelm.jaopca.api.fluids;
/*   */ 
/*   */ import net.minecraftforge.fluids.Fluid;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormFluid
/*   */   extends IMaterialForm {
/*   */   default Fluid toFluid() {
/* 9 */     return (Fluid)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IMaterialFormFluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */