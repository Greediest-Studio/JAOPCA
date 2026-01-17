/*    */ package thelm.jaopca.compat.enderio;
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
/*    */ @JAOPCAModule(modDependencies = {"enderio"})
/*    */ public class EnderIONonIngotModule
/*    */   implements IModule
/*    */ {
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */   
/*    */   public String getName() {
/* 30 */     return "enderio_non_ingot";
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
/* 47 */     return EnderIOModule.BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 52 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 57 */     EnderIOHelper helper = EnderIOHelper.INSTANCE;
/* 58 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 59 */     for (IMaterial material : moduleData.getMaterials()) {
/* 60 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 61 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*    */       
/* 63 */       IDynamicSpecConfig config = this.configs.get(material);
/* 64 */       String configByproduct = config.getDefinedString("enderio.byproduct", "minecraft:cobblestone", miscHelper
/* 65 */           .metaItemPredicate(), "The default byproduct material to output in Ender IO's sagmill.");
/* 66 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */       
/* 68 */       (new Object[9])[0] = materialOredict; (new Object[9])[1] = 
/* 69 */         Integer.valueOf(2); (new Object[9])[2] = Float.valueOf(1.0F); (new Object[9])[3] = materialOredict; (new Object[9])[4] = 
/* 70 */         Integer.valueOf(1); (new Object[9])[5] = Float.valueOf(0.5F); (new Object[9])[6] = byproduct; (new Object[9])[7] = 
/* 71 */         Integer.valueOf(1); (new Object[9])[8] = Float.valueOf(0.15F); (new Object[6])[0] = materialOredict; (new Object[6])[1] = 
/*    */         
/* 73 */         Integer.valueOf(4); (new Object[6])[2] = Float.valueOf(1.0F); (new Object[6])[3] = byproduct; (new Object[6])[4] = 
/* 74 */         Integer.valueOf(1); (new Object[6])[5] = Float.valueOf(0.15F); Object[] output = material.getType().isCrystalline() ? new Object[9] : new Object[6];
/*    */       
/* 76 */       if (material.hasExtra(1)) {
/* 77 */         String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/* 78 */         output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1), Float.valueOf(0.1F) });
/*    */       } 
/* 80 */       helper.registerSagMillRecipe(miscHelper
/* 81 */           .getRecipeKey("enderio.ore_to_material", material.getName()), oreOredict, 3600, "multiply_output", "ignore", output);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\enderio\EnderIONonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */