/*    */ package thelm.com.electronwill.nightconfig.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NullObject
/*    */ {
/* 10 */   public static final NullObject NULL_OBJECT = new NullObject();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "NULL_OBJECT";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 21 */     return (o == this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 26 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\NullObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */