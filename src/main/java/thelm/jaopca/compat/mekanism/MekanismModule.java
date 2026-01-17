/*     */ package thelm.jaopca.compat.mekanism;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import mekanism.common.MekanismFluids;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.items.IItemInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.compat.mekanism.api.gases.IGasInfo;
/*     */ import thelm.jaopca.compat.mekanism.gases.GasFormType;
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"mekanism"})
/*     */ public class MekanismModule
/*     */   implements IModule
/*     */ {
/*  39 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Osmium", "Silver", "Tin" }));
/*     */   
/*  41 */   private static final Set<String> MODULE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Copper", "Draconium", "Gold", "Iridium", "Iron", "Lead", "Mithril", "Nickel", "Osmium", "Silver", "Tin", "Uranium", "Yellorium" }));
/*     */ 
/*     */   
/*  44 */   static final String[] METALLURGY_LIST = new String[] { "Adamantine", "Alduorite", "Amordrine", "Angmallen", "AstralSilver", "Atlarus", "BlackSteel", "Brass", "Bronze", "Carmot", "Celenegil", "Ceruclase", "DamascusSteel", "DeepIron", "Desichalkos", "Electrum", "Eximite", "Haderoth", "Hepatizon", "Ignatius", "Infuscolium", "Inolashite", "Kalendrite", "Lemurite", "Lutetium", "Manganese", "Meutoite", "Midasium", "Orichalcum", "Oureclase", "Prometheum", "Quicksilver", "Rubracium", "Sanguinite", "ShadowIron", "ShadowSteel", "Tartarite", "Vulcanite", "Vyroxeres", "Zinc" };
/*     */   private final IForm dirtyDustForm;
/*     */   private final IForm clumpForm;
/*     */   private final IForm shardForm;
/*     */   private final IForm crystalForm;
/*     */   private final IForm cleanSlurryForm;
/*     */   private final IForm dirtySlurryForm;
/*     */   private final IFormRequest formRequest;
/*     */   
/*     */   static {
/*  54 */     if (Loader.isModLoaded("metallurgy")) {
/*  55 */       Collections.addAll(MODULE_BLACKLIST, METALLURGY_LIST);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MekanismModule() {
/*  63 */     this
/*  64 */       .dirtyDustForm = ApiImpl.INSTANCE.newForm(this, "mekanism_dirty_dust", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("dustDirty").setDefaultMaterialBlacklist(BLACKLIST);
/*  65 */     this
/*  66 */       .clumpForm = ApiImpl.INSTANCE.newForm(this, "mekanism_clump", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("clump").setDefaultMaterialBlacklist(BLACKLIST);
/*  67 */     this
/*  68 */       .shardForm = ApiImpl.INSTANCE.newForm(this, "mekanism_shard", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("shard").setDefaultMaterialBlacklist(BLACKLIST);
/*  69 */     this
/*  70 */       .crystalForm = ApiImpl.INSTANCE.newForm(this, "mekanism_crystal", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crystal").setDefaultMaterialBlacklist(BLACKLIST);
/*  71 */     this
/*     */       
/*  73 */       .cleanSlurryForm = ApiImpl.INSTANCE.newForm(this, "mekanism_clean_slurry", (IFormType)GasFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("clean_slurry").setDefaultMaterialBlacklist(BLACKLIST).setSkipGroupedCheck(true);
/*  74 */     this
/*     */       
/*  76 */       .dirtySlurryForm = ApiImpl.INSTANCE.newForm(this, "mekanism_slurry", (IFormType)GasFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("slurry").setDefaultMaterialBlacklist(BLACKLIST).setSkipGroupedCheck(true);
/*  77 */     this
/*  78 */       .formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.dirtyDustForm, this.clumpForm, this.shardForm, this.crystalForm, this.dirtySlurryForm, this.cleanSlurryForm }).setGrouped(true);
/*     */     GasFormType.init();
/*     */   }
/*     */   public String getName() {
/*  82 */     return "mekanism";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  87 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  88 */     builder.put(Integer.valueOf(0), "dust");
/*  89 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  94 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  99 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/* 104 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 109 */     MekanismHelper helper = MekanismHelper.INSTANCE;
/* 110 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 111 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/* 112 */     GasFormType gasFormType = GasFormType.INSTANCE;
/* 113 */     for (IMaterial material : this.formRequest.getMaterials()) {
/* 114 */       IGasInfo dirtySlurryInfo = gasFormType.getMaterialFormInfo(this.dirtySlurryForm, material);
/* 115 */       String dirtySlurryName = miscHelper.getFluidName("slurry", material.getName());
/* 116 */       IGasInfo cleanSlurryInfo = gasFormType.getMaterialFormInfo(this.cleanSlurryForm, material);
/* 117 */       String cleanSlurryName = miscHelper.getFluidName("clean_slurry", material.getName());
/* 118 */       IItemInfo crystalInfo = itemFormType.getMaterialFormInfo(this.crystalForm, material);
/* 119 */       String crystalOredict = miscHelper.getOredictName("crystal", material.getName());
/* 120 */       IItemInfo shardInfo = itemFormType.getMaterialFormInfo(this.shardForm, material);
/* 121 */       String shardOredict = miscHelper.getOredictName("shard", material.getName());
/* 122 */       IItemInfo clumpInfo = itemFormType.getMaterialFormInfo(this.clumpForm, material);
/* 123 */       String clumpOredict = miscHelper.getOredictName("clump", material.getName());
/* 124 */       IItemInfo dirtyDustInfo = itemFormType.getMaterialFormInfo(this.dirtyDustForm, material);
/* 125 */       String dirtyDustOredict = miscHelper.getOredictName("dustDirty", material.getName());
/* 126 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 127 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*     */       
/* 129 */       helper.registerChemicalDissolutionChamberRecipe(miscHelper
/* 130 */           .getRecipeKey("mekanism.ore_to_dirty_slurry", material.getName()), oreOredict, 1, dirtySlurryInfo, 1000);
/*     */ 
/*     */       
/* 133 */       helper.registerChemicalWasherRecipe(miscHelper
/* 134 */           .getRecipeKey("mekanism.dirty_to_clean_slurry", material.getName()), dirtySlurryName, 1, cleanSlurryInfo, 1);
/*     */ 
/*     */       
/* 137 */       helper.registerChemicalCrystallizerRecipe(miscHelper
/* 138 */           .getRecipeKey("mekanism.clean_slurry_to_crystal", material.getName()), cleanSlurryName, 200, crystalInfo, 1);
/*     */ 
/*     */       
/* 141 */       helper.registerChemicalInjectionChamberRecipe(miscHelper
/* 142 */           .getRecipeKey("mekanism.ore_to_shard", material.getName()), oreOredict, 1, MekanismFluids.HydrogenChloride, shardInfo, 4);
/*     */       
/* 144 */       helper.registerChemicalInjectionChamberRecipe(miscHelper
/* 145 */           .getRecipeKey("mekanism.crystal_to_shard", material.getName()), crystalOredict, 1, MekanismFluids.HydrogenChloride, shardInfo, 1);
/*     */ 
/*     */       
/* 148 */       helper.registerPurificationChamberRecipe(miscHelper
/* 149 */           .getRecipeKey("mekanism.ore_to_clump", material.getName()), oreOredict, 1, clumpInfo, 3);
/*     */       
/* 151 */       helper.registerPurificationChamberRecipe(miscHelper
/* 152 */           .getRecipeKey("mekanism.shard_to_clump", material.getName()), shardOredict, 1, clumpInfo, 1);
/*     */ 
/*     */       
/* 155 */       helper.registerCrusherRecipe(miscHelper
/* 156 */           .getRecipeKey("mekanism.clump_to_dirty_dust", material.getName()), clumpOredict, 1, dirtyDustInfo, 1);
/*     */ 
/*     */       
/* 159 */       helper.registerEnrichmentChamberRecipe(miscHelper
/* 160 */           .getRecipeKey("mekanism.dirty_dust_to_dust", material.getName()), dirtyDustOredict, 1, dustOredict, 1);
/*     */     } 
/*     */     
/* 163 */     for (IMaterial material : Sets.filter(moduleData.getMaterials(), m -> !MODULE_BLACKLIST.contains(m.getName()))) {
/* 164 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 165 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 166 */       helper.registerEnrichmentChamberRecipe(miscHelper
/* 167 */           .getRecipeKey("mekanism.ore_to_dust", material.getName()), oreOredict, 1, dustOredict, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 174 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 175 */     builder.put("dustdirty", "mekanism_dirty_dust");
/* 176 */     builder.put("clump", "mekanism_clump");
/* 177 */     builder.put("shard", "mekanism_shard");
/* 178 */     builder.put("crystal", "mekanism_crystal");
/* 179 */     builder.put("slurryclean", "mekanism_clean_slurry");
/* 180 */     builder.put("slurry", "mekanism_slurry");
/* 181 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\MekanismModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */