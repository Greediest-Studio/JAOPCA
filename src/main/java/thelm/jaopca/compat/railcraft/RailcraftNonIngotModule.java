/*    */ package thelm.jaopca.compat.railcraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.Loader;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"railcraft"})
/*    */ public class RailcraftNonIngotModule
/*    */   implements IModule
/*    */ {
/* 22 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Coal", "Diamond", "Emerald", "Lapis", "Niter", "Redstone", "Saltpeter", "Sulfur" }));
/*    */ 
/*    */   
/*    */   static {
/* 26 */     if (Loader.isModLoaded("forestry")) {
/* 27 */       Collections.addAll(BLACKLIST, new String[] { "Apatite" });
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     return "railcraft_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 38 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 43 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 48 */     RailcraftHelper helper = RailcraftHelper.INSTANCE;
/* 49 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 50 */     for (IMaterial material : moduleData.getMaterials()) {
/* 51 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 52 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 53 */       boolean isCrystal = (material.getType() != MaterialType.DUST);
/* 54 */       helper.registerRockCrusherRecipe(miscHelper
/* 55 */           .getRecipeKey("railcraft.ore_to_material", material.getName()), oreOredict, 100, new Object[] { materialOredict, 
/*    */             
/* 57 */             Integer.valueOf(isCrystal ? 1 : 5), Float.valueOf(1.0F), materialOredict, 
/* 58 */             Integer.valueOf(1), Float.valueOf(0.85F), materialOredict, 
/* 59 */             Integer.valueOf(1), Float.valueOf(isCrystal ? 0.25F : 0.35F) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\railcraft\RailcraftNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */