/*    */ package thelm.jaopca.compat.magneticraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
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
/*    */ @JAOPCAModule(modDependencies = {"magneticraft"}, classDependencies = {"com.cout970.magneticraft.api.registries.machines.grinder.IGrinderRecipeManager"})
/*    */ public class MagneticraftNonIngotModule
/*    */   implements IModule
/*    */ {
/* 23 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Coal", "Diamond", "Emerald", "Galena", "Lapis", "NetherQuartz", "Pyrite", "Quartz", "Redstone", "Sulfur" }));
/*    */ 
/*    */   
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return "magneticraft_non_ingot";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 35 */     return EnumSet.of(MaterialType.GEM, MaterialType.CRYSTAL, MaterialType.DUST);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 40 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 45 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 50 */     MagneticraftHelper helper = MagneticraftHelper.INSTANCE;
/* 51 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 52 */     for (IMaterial material : moduleData.getMaterials()) {
/* 53 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 54 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*    */       
/* 56 */       IDynamicSpecConfig config = this.configs.get(material);
/* 57 */       String configByproduct = config.getDefinedString("magneticraft.grinderByproduct", "minecraft:gravel", miscHelper
/* 58 */           .metaItemPredicate(), "The default byproduct material to output in Magneticraft's grinder.");
/* 59 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */       
/* 61 */       helper.registerGrinderRecipe(miscHelper
/* 62 */           .getRecipeKey("magneticraft.ore_to_material", material.getName()), oreOredict, materialOredict, 
/* 63 */           material.getType().isCrystalline() ? 2 : 4, byproduct, 1, 0.15F, 50.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\MagneticraftNonIngotModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */