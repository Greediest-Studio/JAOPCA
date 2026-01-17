/*    */ package thelm.jaopca.api.materials;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IMaterial
/*    */   extends Comparable<IMaterial>
/*    */ {
/*    */   default int compareTo(IMaterial other) {
/* 33 */     return getName().compareTo(other.getName());
/*    */   }
/*    */   
/*    */   EnumRarity getDisplayRarity();
/*    */   
/*    */   boolean hasEffect();
/*    */   
/*    */   int getColor();
/*    */   
/*    */   String getModelType();
/*    */   
/*    */   Set<String> getConfigModuleBlacklist();
/*    */   
/*    */   boolean isSmallStorageBlock();
/*    */   
/*    */   boolean hasExtra(int paramInt);
/*    */   
/*    */   IMaterial getExtra(int paramInt);
/*    */   
/*    */   Set<String> getAlternativeNames();
/*    */   
/*    */   MaterialType getType();
/*    */   
/*    */   String getName();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\materials\IMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */