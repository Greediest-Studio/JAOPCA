/*    */ package thelm.jaopca.compat.quark;
/*    */ 
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"quark"})
/*    */ public class QuarkOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 12 */     return "quark";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 17 */     ApiImpl.INSTANCE.registerOredict("oreEnderBiotite", "quark:biotite_ore");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\quark\QuarkOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */