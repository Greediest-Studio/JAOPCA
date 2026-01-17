/*     */ package thelm.jaopca.compat.enderio;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"enderio"})
/*     */ public class EnderIOModule
/*     */   implements IModule
/*     */ {
/*  28 */   static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Adamantine", "Agate", "Alexandrite", "Aluminium", "Aluminum", "Amber", "Amethyst", "Ametrine", "Ammolite", "Apatite", "Aquamarine", "Ardite", "AstralSilver", "AstralStarmetal", "Atlarus", "Bauxite", "Beyrl", "BlackDiamond", "BlueTopaz", "Boron", "Carmot", "Carnelian", "CatsEye", "CertusQuartz", "Ceruclase", "Chaos", "ChargedCertusQuartz", "Chrysoprase", "Cinnibar", "Citrine", "Coal", "Cobalt", "Copper", "Coral", "DeepIron", "Diamond", "Dilithium", "DimensionalShard", "Draconium", "Emerald", "Ender", "EnderBiotite", "EnderEssence", "Fluorite", "Galena", "Garnet", "Gold", "GoldenBeryl", "Heliodor", "Ignatius", "Indicolite", "Infuscolium", "Iolite", "Iridium", "Iron", "Jade", "Jasper", "Kalendrite", "Kunzite", "Kyanite", "Lapis", "Lava", "Lead", "Lemurite", "Lepidolite", "Lithium", "Magnesium", "Malachite", "Midasium", "Mithril", "Moonstone", "Morganite", "NaturalAluminum", "NetherQuartz", "Nickel", "Niter", "Onyx", "Opal", "Orichalcum", "Osmium", "Oureclase", "Pearl", "Peridot", "Platinum", "Prometheum", "Prosperity", "Pyrite", "Pyrope", "Quartz", "QuartzBlack", "Redstone", "RoseQuartz", "Rubracium", "Ruby", "Rutile", "Saltpeter", "Sanguinite", "Sapphire", "ShadowIron", "Sheldonite", "Silver", "Sodalite", "Sphalerite", "Spinel", "Sulfur", "Sunstone", "Tanzanite", "Tektite", "Thorium", "Tin", "Titanium", "Topaz", "Tritanium", "Tungsten", "Turquoise", "Uranium", "VioletSapphire", "Vulcanite", "Vyroxeres", "Yellorium", "Zinc", "Zircon" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  49 */     return "enderio";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  54 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  55 */     builder.put(Integer.valueOf(0), "dust");
/*  56 */     builder.put(Integer.valueOf(1), "dust");
/*  57 */     builder.put(Integer.valueOf(2), "dust");
/*  58 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  63 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  68 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  73 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  78 */     EnderIOHelper helper = EnderIOHelper.INSTANCE;
/*  79 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  80 */     for (IMaterial material : moduleData.getMaterials()) {
/*  81 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  82 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*     */       
/*  84 */       IDynamicSpecConfig config = this.configs.get(material);
/*  85 */       String configByproduct = config.getDefinedString("enderio.byproduct", "minecraft:cobblestone", miscHelper
/*  86 */           .metaItemPredicate(), "The default byproduct material to output in Ender IO's sagmill.");
/*  87 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*     */ 
/*     */ 
/*     */       
/*  91 */       Object[] output = { dustOredict, Integer.valueOf(2), Float.valueOf(1.0F), byproduct, Integer.valueOf(1), Float.valueOf(0.15F) };
/*     */       
/*  93 */       if (material.hasExtra(1)) {
/*  94 */         String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*  95 */         output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1), Float.valueOf(0.1F) });
/*     */       } 
/*  97 */       if (material.hasExtra(2)) {
/*  98 */         String secondExtraDustOredict = miscHelper.getOredictName("dust", material.getExtra(2).getName());
/*  99 */         output = ArrayUtils.addAll(output, new Object[] { secondExtraDustOredict, Integer.valueOf(1), Float.valueOf(0.05F) });
/*     */       } 
/* 101 */       helper.registerSagMillRecipe(miscHelper
/* 102 */           .getRecipeKey("enderio.ore_to_dust", material.getName()), oreOredict, 3600, "multiply_output", "ignore", output);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\enderio\EnderIOModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */