/*    */ package thelm.jaopca.compat.minestrapp;
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
/*    */ public class MinestrappModule
/*    */   implements IModule
/*    */ {
/* 26 */   static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Archantine", "Blazium", "Coal", "Copper", "Diamond", "Dimensium", "Emerald", "Gold", "Iron", "Irradium", "Lapis", "Meurodite", "NetherQuartz", "Quartz", "Redstone", "Salt", "Soul", "Tin", "Torite" }));
/*    */ 
/*    */ 
/*    */   
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 35 */     return "minestrapp";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 40 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 41 */     builder.put(Integer.valueOf(0), "dust");
/* 42 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 47 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 52 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 57 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 62 */     MinestrappHelper helper = MinestrappHelper.INSTANCE;
/* 63 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 64 */     for (IMaterial material : moduleData.getMaterials()) {
/* 65 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 66 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*    */       
/* 68 */       IDynamicSpecConfig config = this.configs.get(material);
/* 69 */       String configByproduct = config.getDefinedString("minestrapp.byproduct", "minestrapp:m_chunks@1", miscHelper
/* 70 */           .metaItemPredicate(), "The default byproduct material to output in Minestrappolation's grinder.");
/* 71 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*    */       
/* 73 */       helper.registerCrusherRecipe(miscHelper
/* 74 */           .getRecipeKey("minestrapp.ore_to_dust", material.getName()), oreOredict, dustOredict, 2, byproduct, 1, 40, 0.2F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\minestrapp\MinestrappModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */