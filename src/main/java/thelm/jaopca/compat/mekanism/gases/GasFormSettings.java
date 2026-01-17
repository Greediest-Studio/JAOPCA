/*    */ package thelm.jaopca.compat.mekanism.gases;
/*    */ 
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasCreator;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasFormSettings;
/*    */ 
/*    */ 
/*    */ public class GasFormSettings
/*    */   implements IGasFormSettings
/*    */ {
/* 11 */   private IGasCreator gasCreator = JAOPCAGas::new;
/*    */   
/*    */   private boolean isHidden = false;
/*    */   
/*    */   public IFormType getType() {
/* 16 */     return (IFormType)GasFormType.INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public IGasFormSettings setGasCreator(IGasCreator gasCreator) {
/* 21 */     this.gasCreator = gasCreator;
/* 22 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IGasCreator getGasCreator() {
/* 27 */     return this.gasCreator;
/*    */   }
/*    */ 
/*    */   
/*    */   public IGasFormSettings setIsHidden(boolean isHidden) {
/* 32 */     this.isHidden = isHidden;
/* 33 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getIsHidden() {
/* 38 */     return this.isHidden;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\gases\GasFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */