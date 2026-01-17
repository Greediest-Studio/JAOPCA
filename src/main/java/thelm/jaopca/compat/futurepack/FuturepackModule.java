/*    */ package thelm.jaopca.compat.futurepack;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
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
/*    */ @JAOPCAModule(modDependencies = {"fp"})
/*    */ public class FuturepackModule
/*    */   implements IModule
/*    */ {
/* 28 */   static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Adrite", "Amethyst", "Apatite", "Bauxit", "Beryllium", "Cinnabar", "Coal", "Cobalt", "Copper", "DevilsIron", "Diamond", "Emerald", "Gold", "Iridium", "Iron", "Lapis", "Lead", "Magnesium", "Magnetite", "Manganese", "Molybdenum", "Naquadah", "NetherQuartz", "Nickel", "Olivine", "Platinum", "Pyrite", "Quartz", "Redstone", "Ruby", "Salt", "Silver", "Sulfur", "Sulphur", "Tin", "Titanium", "Tungsten", "Unobtainium", "Wulfenit", "Zinc" }));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return "fp";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 44 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 45 */     builder.put(Integer.valueOf(0), "dust");
/* 46 */     builder.put(Integer.valueOf(1), "dust");
/* 47 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 52 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 57 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 62 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 67 */     FuturepackHelper helper = FuturepackHelper.INSTANCE;
/* 68 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 69 */     for (IMaterial material : moduleData.getMaterials()) {
/* 70 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 71 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*    */       
/* 73 */       IDynamicSpecConfig config = this.configs.get(material);
/* 74 */       String configByproduct = config.getDefinedString("futurepack.byproduct", "minecraft:cobblestone", miscHelper
/* 75 */           .metaItemPredicate(), "The byproduct material to output in Futurepack's Centrifuge.");
/* 76 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */ 
/*    */ 
/*    */       
/* 80 */       Object[] output = { dustOredict, Integer.valueOf(10), byproduct, Integer.valueOf(3) };
/*    */       
/* 82 */       if (material.hasExtra(1)) {
/*    */         String exOredict;
/* 84 */         switch (material.getExtra(1).getType()) { case GEM:
/*    */           case GEM_PLAIN:
/* 86 */             exOredict = miscHelper.getOredictName("gem", material.getExtra(1).getName());
/*    */             break;
/*    */           case CRYSTAL:
/* 89 */             exOredict = miscHelper.getOredictName("crystal", material.getExtra(1).getName());
/*    */             break;
/*    */           default:
/* 92 */             exOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*    */             break; }
/*    */         
/* 95 */         output = ArrayUtils.addAll(output, new Object[] { exOredict, Integer.valueOf(2) });
/*    */       } 
/* 97 */       helper.registerZentrifugeRecipe(miscHelper
/* 98 */           .getRecipeKey("fp.ore_to_dust", material.getName()), oreOredict, 4, 6, 200, output);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\futurepack\FuturepackModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */