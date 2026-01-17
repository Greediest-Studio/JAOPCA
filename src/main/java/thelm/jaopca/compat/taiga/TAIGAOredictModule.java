/*    */ package thelm.jaopca.compat.taiga;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"taiga"})
/*    */ public class TAIGAOredictModule
/*    */   implements IOredictModule
/*    */ {
/* 12 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 16 */     return "taiga";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/*    */     try {
/* 22 */       Class.forName("com.sosnitzka.taiga.Blocks").getMethod("register", new Class[] { boolean.class }).invoke(null, new Object[] { Boolean.valueOf(true) });
/*    */     }
/* 24 */     catch (Exception e) {
/* 25 */       LOGGER.error("Could not invoke TAIGA oredict register method", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\taiga\TAIGAOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */