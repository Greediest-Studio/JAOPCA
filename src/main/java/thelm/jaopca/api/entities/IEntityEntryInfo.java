/*    */ package thelm.jaopca.api.entities;
/*    */ 
/*    */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ 
/*    */ public interface IEntityEntryInfo
/*    */   extends IMaterialFormInfo
/*    */ {
/*    */   default EntityEntry getEntityEntry() {
/* 11 */     return getMaterialFormEntityEntry().asEntityEntry();
/*    */   }
/*    */ 
/*    */   
/*    */   default IMaterialFormEntityEntry getMaterialForm() {
/* 16 */     return getMaterialFormEntityEntry();
/*    */   }
/*    */   
/*    */   IMaterialFormEntityEntry getMaterialFormEntityEntry();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\entities\IEntityEntryInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */