/*    */ package thelm.jaopca.compat.calculator;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"calculator"})
/*    */ public class CalculatorOredictModule
/*    */   implements IOredictModule
/*    */ {
/* 12 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 16 */     return "calculator";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/*    */     try {
/* 22 */       Class.forName("sonar.calculator.mod.CalculatorOreDict").getMethod("registerOres", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 24 */     catch (Exception e) {
/* 25 */       LOGGER.error("Could not invoke Calculator oredict register method", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\calculator\CalculatorOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */