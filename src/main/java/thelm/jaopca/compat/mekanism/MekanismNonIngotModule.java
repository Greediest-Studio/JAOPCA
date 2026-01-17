/*    */ package thelm.jaopca.compat.mekanism;
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
/*    */ @JAOPCAModule(modDependencies = {"mekanism"})
/*    */ public class MekanismNonIngotModule
/*    */   implements IModule
/*    */ {
/* 22 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Amber", "Amethyst", "Apatite", "Coal", "Diamond", "Emerald", "Lapis", "Malachite", "Peridot", "Quartz", "Redstone", "Ruby", "Sapphire", "Tanzanite", "Topaz" }));
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 27 */     if (Loader.isModLoaded("appliedenergistics2")) {
/* 28 */       Collections.addAll(BLACKLIST, new String[] { "CertusQuartz", "ChargedCertusQuartz" });
/*    */     }
/* 30 */     if (Loader.isModLoaded("mysticalagriculture")) {
/* 31 */       Collections.addAll(BLACKLIST, new String[] { "Inferium", "Prosperity" });
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return "mekanism_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 42 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 47 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 52 */     MekanismHelper helper = MekanismHelper.INSTANCE;
/* 53 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 54 */     for (IMaterial material : moduleData.getMaterials()) {
/* 55 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 56 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 57 */       helper.registerEnrichmentChamberRecipe(miscHelper
/* 58 */           .getRecipeKey("mekanism.ore_to_material", material.getName()), oreOredict, 1, materialOredict, 
/* 59 */           material.getType().isCrystalline() ? 2 : 5);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\MekanismNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */