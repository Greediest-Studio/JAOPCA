/*   */ package thelm.jaopca.api.entities;
/*   */ 
/*   */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*   */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*   */ 
/*   */ public interface IMaterialFormEntityEntry
/*   */   extends IMaterialForm {
/*   */   default EntityEntry asEntityEntry() {
/* 9 */     return (EntityEntry)this;
/*   */   }
/*   */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\entities\IMaterialFormEntityEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */