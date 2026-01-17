/*    */ package thelm.jaopca.compat.appliedenergistics2;
/*    */ 
/*    */ import appeng.items.materials.MaterialType;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"appliedenergistics2"})
/*    */ public class AppliedEnergistics2OredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 14 */     return "appliedenergistics2";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 19 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 20 */     apiImpl.registerOredict("gemCertusQuartz", MaterialType.CERTUS_QUARTZ_CRYSTAL.stack(1));
/* 21 */     apiImpl.registerOredict("gemChargedCertusQuartz", MaterialType.CERTUS_QUARTZ_CRYSTAL_CHARGED.stack(1));
/* 22 */     apiImpl.registerOredict("gemSilicon", MaterialType.SILICON.stack(1));
/* 23 */     apiImpl.registerOredict("gemFluix", MaterialType.FLUIX_CRYSTAL.stack(1));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\appliedenergistics2\AppliedEnergistics2OredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */