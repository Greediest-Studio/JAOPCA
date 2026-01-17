/*    */ package thelm.jaopca.compat.astralsorcery;
/*    */ 
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
/*    */ @JAOPCAModule(modDependencies = {"astralsorcery"})
/*    */ public class AstralSorceryModule
/*    */   implements IModule
/*    */ {
/* 20 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aquamarine", "AstralStarmetal", "Coal", "Diamond", "Emerald", "Gold", "Iron", "Lapis", "Redstone" }));
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 25 */     return "astralsorcery";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 30 */     return EnumSet.of(MaterialType.INGOT, MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 35 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 40 */     AstralSorceryHelper helper = AstralSorceryHelper.INSTANCE;
/* 41 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 42 */     for (IMaterial material : moduleData.getMaterials()) {
/* 43 */       int outputCount; String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 44 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*    */       
/* 46 */       switch (material.getType()) {
/*    */         
/*    */         default:
/* 49 */           outputCount = 3;
/*    */           break;
/*    */         case GEM:
/*    */         case CRYSTAL:
/* 53 */           outputCount = 4;
/*    */           break;
/*    */         case DUST:
/* 56 */           outputCount = 5;
/*    */           break;
/*    */       } 
/* 59 */       helper.registerInfusionRecipe(miscHelper
/* 60 */           .getRecipeKey("astralsorcery.ore_to_material", material.getName()), oreOredict, materialOredict, outputCount, 0.05F, false, true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\astralsorcery\AstralSorceryModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */