/*     */ package thelm.jaopca.compat.teslathingies;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.ndrei.teslapoweredthingies.items.TeslifiedObsidian;
/*     */ import org.apache.commons.lang3.ArrayUtils;
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
/*     */ @JAOPCAModule(modDependencies = {"teslathingies"})
/*     */ public class TeslaThingiesModule
/*     */   implements IModule
/*     */ {
/*  37 */   public static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Iron", "Gold", "Coal", "Diamond", "Emerald", "Lapis", "Redstone", "Adamantine", "Antimony", "Aquarium", "Bismuth", "Brass", "Bronze", "Coldiron", "Copper", "Cupronickel", "Electrum", "Invar", "Lead", "Mercury", "Mithril", "Nickel", "Pewter", "Platinum", "Silver", "Starsteel", "Tin", "Zinc", "Aluminium", "AluminiumBrass", "Cadmium", "GalvanizedSteel", "Iridium", "Magnesium", "Manganese", "Nichrome", "Osmium", "Plutonium", "Rutile", "StainlessSteel", "Tantalum", "Titanium", "Tungsten", "Uranium", "Zirconium" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private final IForm teslaLumpForm = ApiImpl.INSTANCE.newForm(this, "teslathingies_tesla_lump", (IFormType)ItemFormType.INSTANCE)
/*  45 */     .setMaterialTypes(MaterialType.ORE).setSecondaryName("teslaLump").setDefaultMaterialBlacklist(BLACKLIST);
/*  46 */   private final IForm augmentedLumpForm = ApiImpl.INSTANCE.newForm(this, "teslathingies_augmented_lump", (IFormType)ItemFormType.INSTANCE)
/*  47 */     .setMaterialTypes(MaterialType.ORE).setSecondaryName("augmentedLump").setDefaultMaterialBlacklist(BLACKLIST);
/*  48 */   private final IFormRequest formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.teslaLumpForm, this.augmentedLumpForm }).setGrouped(true);
/*     */   
/*     */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  54 */     return "teslathingies";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  59 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  60 */     builder.put(Integer.valueOf(0), "dust");
/*  61 */     builder.put(Integer.valueOf(1), "dust");
/*  62 */     builder.put(Integer.valueOf(2), "dust");
/*  63 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  68 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  73 */     return EnumSet.copyOf(Arrays.asList(MaterialType.ORE));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  78 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  83 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  88 */     TeslaThingiesHelper helper = TeslaThingiesHelper.INSTANCE;
/*  89 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  90 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/*  91 */     for (IMaterial material : this.formRequest.getMaterials()) {
/*  92 */       int amount; String teslaLumpOredict = miscHelper.getOredictName("teslaLump", material.getName());
/*  93 */       IItemInfo augmentedLumpInfo = itemFormType.getMaterialFormInfo(this.augmentedLumpForm, material);
/*  94 */       String augmentedLumpOredict = miscHelper.getOredictName("augmentedLump", material.getName());
/*  95 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*     */       
/*  97 */       helper.registerCompoundMakerRecipe(miscHelper
/*  98 */           .getRecipeKey("teslathingies.tesla_lump_to_augmented_lump", material.getName()), new Object[] { teslaLumpOredict, 
/*     */             
/* 100 */             Integer.valueOf(1) }, new Object[] { TeslifiedObsidian.INSTANCE, 
/*     */             
/* 102 */             Integer.valueOf(1) }, augmentedLumpInfo, 1);
/*     */ 
/*     */ 
/*     */       
/* 106 */       switch (material.getType()) {
/*     */         default:
/* 108 */           amount = 2; break;
/*     */         case GEM:
/*     */         case CRYSTAL:
/* 111 */           amount = 3;
/*     */           break;
/*     */         case DUST:
/* 114 */           amount = 5;
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 119 */       Object[] output = { dustOredict, Integer.valueOf(amount), Float.valueOf(1.0F), dustOredict, Integer.valueOf(1), Float.valueOf(0.24F) };
/*     */       
/* 121 */       if (material.hasExtra(1)) {
/* 122 */         String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/* 123 */         output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1), Float.valueOf(0.24F) });
/*     */       } 
/* 125 */       helper.registerPowderMakerRecipe(miscHelper
/* 126 */           .getRecipeKey("teslathingies.augmented_lump_to_dust", material.getName()), augmentedLumpOredict, 1, output);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 133 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 134 */     builder.put("teslalump", "teslathingies_tesla_lump");
/* 135 */     builder.put("augmentedlump", "teslathingies_augmented_lump");
/* 136 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\teslathingies\TeslaThingiesModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */