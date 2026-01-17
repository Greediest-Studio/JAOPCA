/*    */ package thelm.jaopca.compat.futurepack;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import org.apache.commons.lang3.ArrayUtils;
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
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"fp"})
/*    */ public class FuturepackNonIngotModule
/*    */   implements IModule
/*    */ {
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */   
/*    */   public String getName() {
/* 30 */     return "fp_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 35 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 36 */     builder.put(Integer.valueOf(1), "dust");
/* 37 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 42 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 47 */     return FuturepackModule.BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 52 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 57 */     FuturepackHelper helper = FuturepackHelper.INSTANCE;
/* 58 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 59 */     for (IMaterial material : moduleData.getMaterials()) {
/* 60 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 61 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*    */       
/* 63 */       IDynamicSpecConfig config = this.configs.get(material);
/* 64 */       String configByproduct = config.getDefinedString("fp.byproduct", "minecraft:cobblestone", miscHelper
/* 65 */           .metaItemPredicate(), "The byproduct material to output in Futurepack's Centrifuge.");
/* 66 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */ 
/*    */ 
/*    */       
/* 70 */       Object[] output = { materialOredict, Integer.valueOf(material.getType().isCrystalline() ? 12 : 24), byproduct, Integer.valueOf(3) };
/*    */       
/* 72 */       if (material.hasExtra(1)) {
/*    */         String exOredict;
/* 74 */         switch (material.getExtra(1).getType()) { case GEM:
/*    */           case GEM_PLAIN:
/* 76 */             exOredict = miscHelper.getOredictName("gem", material.getExtra(1).getName());
/*    */             break;
/*    */           case CRYSTAL:
/* 79 */             exOredict = miscHelper.getOredictName("crystal", material.getExtra(1).getName());
/*    */             break;
/*    */           default:
/* 82 */             exOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*    */             break; }
/*    */         
/* 85 */         output = ArrayUtils.addAll(output, new Object[] { exOredict, Integer.valueOf(2) });
/*    */       } 
/* 87 */       helper.registerZentrifugeRecipe(miscHelper
/* 88 */           .getRecipeKey("fp.ore_to_material", material.getName()), oreOredict, 4, 
/* 89 */           material.getType().isCrystalline() ? 8 : 6, 200, output);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\futurepack\FuturepackNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */