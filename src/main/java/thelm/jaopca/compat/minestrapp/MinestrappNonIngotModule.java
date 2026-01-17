/*    */ package thelm.jaopca.compat.minestrapp;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"minestrapp"})
/*    */ public class MinestrappNonIngotModule
/*    */   implements IModule
/*    */ {
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */   
/*    */   public String getName() {
/* 25 */     return "minestrapp_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 30 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 35 */     return MinestrappModule.BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 40 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 45 */     MinestrappHelper helper = MinestrappHelper.INSTANCE;
/* 46 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 47 */     for (IMaterial material : moduleData.getMaterials()) {
/* 48 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 49 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*    */       
/* 51 */       IDynamicSpecConfig config = this.configs.get(material);
/* 52 */       String configByproduct = config.getDefinedString("minestrapp.byproduct", "minestrapp:m_chunks@1", miscHelper
/* 53 */           .metaItemPredicate(), "The default byproduct material to output in Minestrappolation's grinder.");
/* 54 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */       
/* 56 */       helper.registerCrusherRecipe(miscHelper
/* 57 */           .getRecipeKey("minestrapp.ore_to_dust", material.getName()), oreOredict, materialOredict, 
/* 58 */           material.getType().isCrystalline() ? 2 : 5, byproduct, 1, 40, 0.2F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\minestrapp\MinestrappNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */