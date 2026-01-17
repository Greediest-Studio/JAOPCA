/*     */ package thelm.jaopca.compat.mekanism;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"mekanism"})
/*     */ public class MekanismCompatModule
/*     */   implements IModule
/*     */ {
/*  31 */   private static final Set<String> TO_DUST_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Amber", "Charcoal", "Coal", "Copper", "Diamond", "Draconium", "Emerald", "Gold", "Iridium", "Iron", "Lapis", "Lead", "Malachite", "Mithril", "NetherQuartz", "Nickel", "Osmium", "Peridot", "Platinum", "Quartz", "Redstone", "RefinedGlowstone", "RefinedObsidian", "Ruby", "Sapphire", "Silver", "Steel", "Tanzanite", "Tin", "Topaz", "Uranium" }));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private static final Set<String> TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Amber", "Charcoal", "Coal", "Diamond", "Emerald", "Lapis", "NetherQuartz", "Malachite", "Peridot", "Quartz", "Ruby", "Sapphire", "Sulfur", "Tanzanite", "Topaz" }));
/*     */ 
/*     */   
/*  39 */   private static final Set<String> TO_ORE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Amber", "Amethyst", "Apatite", "Coal", "Copper", "Diamond", "Draconium", "Emerald", "Gold", "Iridium", "Iron", "Lapis", "Lead", "Malachite", "Mithril", "NetherQuartz", "Nickel", "Osmium", "Peridot", "Platinum", "Quartz", "Redstone", "Ruby", "Sapphire", "Silver", "Steel", "Tanzanite", "Tin", "Topaz", "Uranium" }));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static Set<String> configToDustBlacklist = new TreeSet<>();
/*  45 */   private static Set<String> configToCrystalBlacklist = new TreeSet<>();
/*  46 */   private static Set<String> configToOreBlacklist = new TreeSet<>(); private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */   
/*     */   static {
/*  49 */     if (Loader.isModLoaded("appliedenergistics2")) {
/*  50 */       Collections.addAll(TO_DUST_BLACKLIST, new String[] { "CertusQuartz", "ChargedCertusQuartz", "Fluix" });
/*  51 */       Collections.addAll(TO_CRYSTAL_BLACKLIST, new String[] { "CertusQuartz", "Fluix" });
/*     */     } 
/*  53 */     if (Loader.isModLoaded("metallurgy")) {
/*  54 */       Collections.addAll(TO_DUST_BLACKLIST, MekanismModule.METALLURGY_LIST);
/*  55 */       Collections.addAll(TO_ORE_BLACKLIST, MekanismModule.METALLURGY_LIST);
/*     */     } 
/*  57 */     if (Loader.isModLoaded("mysticalagriculture")) {
/*  58 */       Collections.addAll(TO_ORE_BLACKLIST, new String[] { "Inferium", "Prosperity" });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  66 */     return "mekanism_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  71 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  76 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  77 */     miscHelper.caclulateMaterialSet(config
/*  78 */         .getDefinedStringList("recipes.toDustMaterialBlacklist", new ArrayList(), miscHelper
/*  79 */           .configMaterialPredicate(), "The materials that should not have crushing to dust recipes added."), configToDustBlacklist);
/*     */     
/*  81 */     miscHelper.caclulateMaterialSet(config
/*  82 */         .getDefinedStringList("recipes.toCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  83 */           .configMaterialPredicate(), "The materials that should not have enriching to material recipes added."), configToCrystalBlacklist);
/*     */     
/*  85 */     miscHelper.caclulateMaterialSet(config
/*  86 */         .getDefinedStringList("recipes.toOreMaterialBlacklist", new ArrayList(), miscHelper
/*  87 */           .configMaterialPredicate(), "The materials that should not have combining to ore recipes added."), configToOreBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  93 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  98 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  99 */     MekanismHelper helper = MekanismHelper.INSTANCE;
/* 100 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 101 */     Set<String> oredict = apiImpl.getOredict();
/* 102 */     for (IMaterial material : moduleData.getMaterials()) {
/* 103 */       MaterialType type = material.getType();
/* 104 */       String name = material.getName();
/* 105 */       if (!type.isDust() && !TO_DUST_BLACKLIST.contains(name) && !configToDustBlacklist.contains(name)) {
/* 106 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 107 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 108 */         if (oredict.contains(dustOredict)) {
/* 109 */           helper.registerCrusherRecipe(miscHelper
/* 110 */               .getRecipeKey("mekanism.material_to_dust", name), materialOredict, 1, dustOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 114 */       if (type.isCrystalline() && !TO_CRYSTAL_BLACKLIST.contains(name) && !configToCrystalBlacklist.contains(name)) {
/* 115 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 116 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 117 */         if (oredict.contains(dustOredict)) {
/* 118 */           helper.registerEnrichmentChamberRecipe(miscHelper
/* 119 */               .getRecipeKey("mekanism.dust_to_material", name), dustOredict, 1, materialOredict, 1);
/*     */         }
/*     */       } 
/*     */       
/* 123 */       if (type.isOre() && !TO_ORE_BLACKLIST.contains(name) && !configToOreBlacklist.contains(name)) {
/* 124 */         String ingOredict = miscHelper.getOredictName("dust", name);
/* 125 */         String oreOredict = miscHelper.getOredictName("ore", name);
/* 126 */         if (!oredict.contains(ingOredict)) {
/* 127 */           ingOredict = miscHelper.getOredictName(type.getFormName(), name);
/*     */         }
/* 129 */         if (oredict.contains(ingOredict)) {
/* 130 */           IDynamicSpecConfig config = this.configs.get(material);
/* 131 */           String configOreBase = config.getDefinedString("mekanism.oreBase", "minecraft:cobblestone", this::isOredictOrItemValid, "The default base to use in Mekanism's Combiner to recreate ores.");
/*     */           
/* 133 */           Object oreBase = getOredictOrItem(configOreBase);
/* 134 */           helper.registerCombinerRecipe(miscHelper
/* 135 */               .getRecipeKey("mekanism.material_to_ore", name), ingOredict, 
/* 136 */               type.isCrystalline() ? 5 : 8, oreBase, 1, oreOredict, 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOredictOrItemValid(String s) {
/* 143 */     if (StringUtils.startsWith(s, "#")) {
/* 144 */       return ApiImpl.INSTANCE.getOredict().contains(s.substring(1));
/*     */     }
/*     */     
/* 147 */     return ForgeRegistries.ITEMS.containsKey(new ResourceLocation(s.split("@(?=\\d*$)")[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getOredictOrItem(String s) {
/* 152 */     if (StringUtils.startsWith(s, "#")) {
/* 153 */       return s.substring(1);
/*     */     }
/*     */     
/* 156 */     return MiscHelper.INSTANCE.parseMetaItem(s);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\MekanismCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */