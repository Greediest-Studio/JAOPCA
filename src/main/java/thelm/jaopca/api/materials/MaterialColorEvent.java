/*    */ package thelm.jaopca.api.materials;
/*    */ 
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ public class MaterialColorEvent
/*    */   extends Event {
/*    */   private final IMaterial material;
/*    */   private final int color;
/*    */   
/*    */   public MaterialColorEvent(IMaterial material, int color) {
/* 11 */     this.material = material;
/* 12 */     this.color = color;
/*    */   }
/*    */   
/*    */   public IMaterial getMaterial() {
/* 16 */     return this.material;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 20 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\materials\MaterialColorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */