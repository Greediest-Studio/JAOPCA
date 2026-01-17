/*    */ package thelm.jaopca.api.oredict;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IOredictModule
/*    */   extends Comparable<IOredictModule>
/*    */ {
/*    */   default int compareTo(IOredictModule other) {
/* 11 */     return getName().compareTo(other.getName());
/*    */   }
/*    */   
/*    */   void register();
/*    */   
/*    */   String getName();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\oredict\IOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */