/*    */ package thelm.jaopca.compat.rftools;
/*    */ 
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"rftools"})
/*    */ public class RFToolsOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 12 */     return "rftools";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 17 */     ApiImpl.INSTANCE.registerOredict("gemDimensionalShard", "rftools:dimensional_shard");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\rftools\RFToolsOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */