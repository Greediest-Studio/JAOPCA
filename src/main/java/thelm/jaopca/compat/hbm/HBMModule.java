/*     */ package thelm.jaopca.compat.hbm;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.hbm.forgefluid.ModForgeFluids;
/*     */ import com.hbm.items.ModItems;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.items.IItemInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"hbm"}, classDependencies = {"com.hbm.main.MainRegistry"})
/*     */ public class HBMModule
/*     */   implements IModule
/*     */ {
/*  40 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminum", "Aluminium", "Beryllium", "CertusQuartz", "Cinnabar", "Coal", "Cobalt", "Copper", "Diamond", "Fluorite", "Gold", "Iron", "Lead", "Lithium", "Niter", "Plutonium", "RareEarth", "Redstone", "Saltpeter", "Schrabidium", "Starmetal", "Sulfur", "Thorium", "Titanium", "Tungsten", "Uranium" }));
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static final Set<String> MODULE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminum", "Aluminium", "Beryllium", "CertusQuartz", "Cinnabar", "Coal", "Cobalt", "Copper", "Diamond", "Emerald", "Fluorite", "Gold", "Iron", "Lapis", "Lead", "Lignite", "Lithium", "NetherQuartz", "Niter", "Plutonium", "Quartz", "RareEarth", "Redstone", "Saltpeter", "Schrabidium", "Starmetal", "Sulfur", "Thorium", "Titanium", "Tungsten", "Uranium" }));
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */ 
/*     */ 
/*     */   
/*  52 */   private final IForm crystalForm = ApiImpl.INSTANCE.newForm(this, "hbm_crystal", (IFormType)ItemFormType.INSTANCE)
/*  53 */     .setMaterialTypes(MaterialType.ORE).setSecondaryName("hbm:crystal").setDefaultMaterialBlacklist(BLACKLIST);
/*     */   
/*     */   public HBMModule() {
/*  56 */     ApiImpl.INSTANCE.registerBlacklistedMaterialNames(new String[] { "Ac227", "Am241", "Am242", "At209", "Au198", "Co60", "Cs137", "Gh336", "I131", "Np237", "Pb209", "Po210", "Pu238", "Pu239", "Pu240", "Pu241", "Ra226", "Sr90", "Tc99", "Th232", "Thorium232", "U233", "U235", "U238", "Xe135" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  64 */     return "hbm";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  69 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  70 */     builder.put(Integer.valueOf(0), "dust");
/*  71 */     builder.put(Integer.valueOf(1), "dust");
/*  72 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  77 */     return Collections.singletonList(this.crystalForm.toRequest());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  82 */     return EnumSet.copyOf(Arrays.asList(MaterialType.ORE));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  87 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  92 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  97 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  98 */     HBMHelper helper = HBMHelper.INSTANCE;
/*  99 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 100 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/* 101 */     Fluid acid = ModForgeFluids.acid;
/* 102 */     Item tinyLithium = ModItems.powder_lithium_tiny;
/* 103 */     for (IMaterial material : this.crystalForm.getMaterials()) {
/* 104 */       IItemInfo crystalInfo = itemFormType.getMaterialFormInfo(this.crystalForm, material);
/* 105 */       String crystalOredict = miscHelper.getOredictName("hbm:crystal", material.getName());
/* 106 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 107 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 108 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 109 */       String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*     */       
/* 111 */       helper.registerCrystallizerRecipe(miscHelper
/* 112 */           .getRecipeKey("hbm.ore_to_crystal", material.getName()), oreOredict, acid, 500, crystalInfo, 1);
/*     */ 
/*     */       
/* 115 */       apiImpl.registerSmeltingRecipe(miscHelper
/* 116 */           .getRecipeKey("hbm.crystal_to_material", material.getName()), crystalOredict, materialOredict, 
/* 117 */           material.getType().isDust() ? 6 : 2, 2.0F);
/* 118 */       int count = material.getType().isDust() ? 8 : 3;
/* 119 */       helper.registerShredderRecipe(miscHelper
/* 120 */           .getRecipeKey("hbm.crystal_to_dust_shredder", material.getName()), crystalOredict, dustOredict, count);
/*     */       
/* 122 */       count = material.getType().isDust() ? 4 : 2;
/* 123 */       if (material.hasExtra(1)) {
/* 124 */         helper.registerCentrifugeRecipe(miscHelper
/* 125 */             .getRecipeKey("hbm.crystal_to_dust_centrifuge", material.getName()), crystalOredict, new Object[] { dustOredict, 
/*     */               
/* 127 */               Integer.valueOf(count), dustOredict, Integer.valueOf(count), extraDustOredict, Integer.valueOf(1), tinyLithium, Integer.valueOf(1) });
/*     */         
/*     */         continue;
/*     */       } 
/* 131 */       helper.registerCentrifugeRecipe(miscHelper
/* 132 */           .getRecipeKey("hbm.crystal_to_dust_centrifuge", material.getName()), crystalOredict, new Object[] { dustOredict, 
/*     */             
/* 134 */             Integer.valueOf(count), dustOredict, Integer.valueOf(count), tinyLithium, Integer.valueOf(1), tinyLithium, Integer.valueOf(1) });
/*     */     } 
/*     */ 
/*     */     
/* 138 */     for (IMaterial material : Sets.filter(moduleData.getMaterials(), m -> !MODULE_BLACKLIST.contains(m.getName()))) {
/* 139 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 140 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 141 */       String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*     */       
/* 143 */       IDynamicSpecConfig config = this.configs.get(material);
/* 144 */       String configByproduct = config.getDefinedString("hbm.byproduct", "minecraft:gravel", miscHelper
/* 145 */           .metaItemPredicate(), "The default byproduct material to output in HBMNTM's centrifuge.");
/* 146 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*     */       
/* 148 */       int count = material.getType().isDust() ? 3 : 1;
/* 149 */       helper.registerCentrifugeRecipe(miscHelper
/* 150 */           .getRecipeKey("hbm.ore_to_dust_centrifuge", material.getName()), oreOredict, new Object[] { dustOredict, 
/*     */             
/* 152 */             Integer.valueOf(count), dustOredict, Integer.valueOf(count), extraDustOredict, Integer.valueOf(1), byproduct, Integer.valueOf(1) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\HBMModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */