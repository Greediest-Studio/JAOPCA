/*    */ package thelm.jaopca.compat.enderio;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"enderio"})
/*    */ public class EnderIOCompatModule
/*    */   implements IModule
/*    */ {
/* 24 */   private static final Set<String> TO_DUST_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Adamantine", "Aluminium", "Aluminum", "AluminumBrass", "Amordrine", "Angmallen", "Ardite", "AstralSilver", "Atlarus", "BlackSteel", "Blutonium", "Brass", "Bronze", "Carmot", "Celenegil", "CertusQuartz", "Ceruclase", "Charcoal", "Cobalt", "ConductiveIron", "Coal", "Copper", "Cyanite", "DamascusSteel", "DarkSteel", "DeepIron", "Diamond", "Draconium", "ElectricalSteel", "Electrum", "Emerald", "Enderium", "EnergeticAlloy", "Fluix", "Gold", "Graphite", "Haderoth", "Hepatizon", "Ignatius", "Infuscolium", "Inolashite", "Invar", "Iron", "Kalendrite", "Lapis", "Lead", "Lemurite", "Ludicrite", "Lumium", "Magnesium", "Manganese", "Manyullyn", "Midasium", "Mithril", "NaturalAluminum", "NetherQuartz", "Nickel", "Orichalcum", "Osmium", "Oureclase", "Platinum", "Prismarine", "Prometheum", "PulsatingIron", "Quartz", "Quicksilver", "RedstoneAlloy", "Rubracium", "Rutile", "Sanguinite", "ShadowIron", "ShadowSteel", "Signalum", "Silver", "Soularium", "Steel", "Tartarite", "Tin", "Titanium", "Tungsten", "Uranium", "VibrantAlloy", "Vulcanite", "Vyroxeres", "Yellorium", "Zinc" }));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   private static Set<String> configMaterialToDustBlacklist = new TreeSet<>();
/* 37 */   private static Set<String> configBlockToDustBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 41 */     return "enderio_compat";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 46 */     return EnumSet.allOf(MaterialType.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 51 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 52 */     miscHelper.caclulateMaterialSet(config
/* 53 */         .getDefinedStringList("recipes.materialToDustMaterialBlacklist", new ArrayList(), miscHelper
/* 54 */           .configMaterialPredicate(), "The materials that should not have sagmill material to dust recipes added."), configMaterialToDustBlacklist);
/*    */     
/* 56 */     miscHelper.caclulateMaterialSet(config
/* 57 */         .getDefinedStringList("recipes.blockToDustMaterialBlacklist", new ArrayList(), miscHelper
/* 58 */           .configMaterialPredicate(), "The materials that should not have sagmill block to dust recipes added."), configBlockToDustBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 64 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 65 */     EnderIOHelper helper = EnderIOHelper.INSTANCE;
/* 66 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 67 */     Set<String> oredict = apiImpl.getOredict();
/* 68 */     for (IMaterial material : moduleData.getMaterials()) {
/* 69 */       MaterialType type = material.getType();
/* 70 */       String name = material.getName();
/* 71 */       if (!type.isDust() && !TO_DUST_BLACKLIST.contains(name) && !configMaterialToDustBlacklist.contains(name)) {
/* 72 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 73 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 74 */         if (oredict.contains(dustOredict)) {
/* 75 */           helper.registerSagMillRecipe(miscHelper
/* 76 */               .getRecipeKey("enderio.material_to_dust", name), materialOredict, 2400, "none", "ignore", new Object[] { dustOredict, 
/*    */                 
/* 78 */                 Integer.valueOf(1), Float.valueOf(1.0F) });
/*    */         }
/*    */       } 
/*    */       
/* 82 */       if (type.isIngot() && !TO_DUST_BLACKLIST.contains(name) && !configBlockToDustBlacklist.contains(name)) {
/* 83 */         String blockOredict = miscHelper.getOredictName("block", name);
/* 84 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 85 */         if (oredict.contains(dustOredict))
/* 86 */           helper.registerSagMillRecipe(miscHelper
/* 87 */               .getRecipeKey("enderio.block_to_dust", name), blockOredict, 3600, "none", "ignore", new Object[] { dustOredict, 
/*    */                 
/* 89 */                 Integer.valueOf(material.isSmallStorageBlock() ? 4 : 9), Float.valueOf(1.0F) }); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\enderio\EnderIOCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */