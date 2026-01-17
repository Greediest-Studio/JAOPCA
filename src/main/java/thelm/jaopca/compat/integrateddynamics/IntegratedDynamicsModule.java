/*    */ package thelm.jaopca.compat.integrateddynamics;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"integrateddynamics"})
/*    */ public class IntegratedDynamicsModule
/*    */   implements IModule
/*    */ {
/* 23 */   static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Ardite", "Coal", "Cobalt", "Copper", "Dark", "Diamond", "Emerald", "Gold", "Iron", "Lapis", "Lead", "Mithril", "NetherQuartz", "Nickel", "Platinum", "Quartz", "Redstone", "Silver", "Tin" }));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 29 */     return "integrateddynamics";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 34 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 35 */     builder.put(Integer.valueOf(0), "dust");
/* 36 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 41 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 46 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 51 */     IntegratedDynamicsHelper helper = IntegratedDynamicsHelper.INSTANCE;
/* 52 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 53 */     for (IMaterial material : moduleData.getMaterials()) {
/* 54 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 55 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 56 */       helper.registerSqueezerRecipe(miscHelper
/* 57 */           .getRecipeKey("integrateddynamics.ore_to_dust", material.getName()), oreOredict, new Object[] { dustOredict, 
/*    */             
/* 59 */             Integer.valueOf(1), Float.valueOf(1.0F), dustOredict, 
/* 60 */             Integer.valueOf(1), Float.valueOf(0.75F) });
/*    */       
/* 62 */       helper.registerMechanicalSqueezerRecipe(miscHelper
/* 63 */           .getRecipeKey("integrateddynamics.ore_to_dust_mechanical", material.getName()), oreOredict, new Object[] { dustOredict, 
/*    */             
/* 65 */             Integer.valueOf(2), Float.valueOf(1.0F), dustOredict, 
/* 66 */             Integer.valueOf(1), Float.valueOf(0.5F) }, 40);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\integrateddynamics\IntegratedDynamicsModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */