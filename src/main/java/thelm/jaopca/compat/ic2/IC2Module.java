/*     */ package thelm.jaopca.compat.ic2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import ic2.api.item.IC2Items;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"ic2"})
/*     */ public class IC2Module
/*     */   implements IModule
/*     */ {
/*  36 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Tin", "Silver", "Uranium" }));
/*     */ 
/*     */   
/*  39 */   private final IForm crushedForm = ApiImpl.INSTANCE.newForm(this, "ic2_crushed", (IFormType)ItemFormType.INSTANCE)
/*  40 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crushed").setDefaultMaterialBlacklist(BLACKLIST);
/*  41 */   private final IForm purifiedCrushedForm = ApiImpl.INSTANCE.newForm(this, "ic2_purified_crushed", (IFormType)ItemFormType.INSTANCE)
/*  42 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crushedPurified").setDefaultMaterialBlacklist(BLACKLIST);
/*  43 */   private final IFormRequest formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.crushedForm, this.purifiedCrushedForm }).setGrouped(true);
/*     */ 
/*     */   
/*     */   public String getName() {
/*  47 */     return "ic2";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  52 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  53 */     builder.put(Integer.valueOf(0), "dust");
/*  54 */     builder.put(Integer.valueOf(0), "tiny_dust");
/*  55 */     builder.put(Integer.valueOf(1), "tiny_dust");
/*  56 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  61 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  66 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  71 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  76 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  77 */     IC2Helper helper = IC2Helper.INSTANCE;
/*  78 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  79 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/*  80 */     ItemStack stoneDust = IC2Items.getItem("dust", "stone");
/*  81 */     for (IMaterial material : this.crushedForm.getMaterials()) {
/*  82 */       IItemInfo crushedInfo = itemFormType.getMaterialFormInfo(this.crushedForm, material);
/*  83 */       String crushedOredict = miscHelper.getOredictName("crushed", material.getName());
/*  84 */       IItemInfo purifiedCrushedInfo = itemFormType.getMaterialFormInfo(this.purifiedCrushedForm, material);
/*  85 */       String purifiedCrushedOredict = miscHelper.getOredictName("crushedPurified", material.getName());
/*  86 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  87 */       String tinyDustOredict = miscHelper.getOredictName("dustTiny", material.getName());
/*  88 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*  89 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*  90 */       String extraTinyDustOredict = miscHelper.getOredictName("dustTiny", material.getExtra(1).getName());
/*     */       
/*  92 */       helper.registerMaceratorRecipe(miscHelper
/*  93 */           .getRecipeKey("ic2.ore_to_crushed", material.getName()), oreOredict, 1, crushedInfo, 2);
/*     */ 
/*     */       
/*  96 */       helper.registerOreWashingRecipe(miscHelper
/*  97 */           .getRecipeKey("ic2.crushed_to_purified_crushed", material.getName()), crushedOredict, 1, 1000, new Object[] { purifiedCrushedInfo, 
/*     */             
/*  99 */             Integer.valueOf(1), tinyDustOredict, Integer.valueOf(2), stoneDust, Integer.valueOf(1) });
/*     */ 
/*     */       
/* 102 */       apiImpl.registerSmeltingRecipe(miscHelper
/* 103 */           .getRecipeKey("ic2.crushed_to_material", material.getName()), crushedOredict, materialOredict, 1, 0.5F);
/*     */       
/* 105 */       apiImpl.registerSmeltingRecipe(miscHelper
/* 106 */           .getRecipeKey("ic2.purified_crushed_to_material", material.getName()), purifiedCrushedOredict, materialOredict, 1, 0.5F);
/*     */       
/* 108 */       helper.registerMaceratorRecipe(miscHelper
/* 109 */           .getRecipeKey("ic2.crushed_to_dust_macerator", material.getName()), crushedOredict, 1, dustOredict, 1);
/*     */       
/* 111 */       helper.registerMaceratorRecipe(miscHelper
/* 112 */           .getRecipeKey("ic2.purified_crushed_to_dust_macerator", material.getName()), purifiedCrushedOredict, 1, dustOredict, 1);
/*     */       
/* 114 */       helper.registerCentrifugeRecipe(miscHelper
/* 115 */           .getRecipeKey("ic2.crushed_to_dust_centrifuge", material.getName()), crushedOredict, 1, 1500, new Object[] { extraTinyDustOredict, 
/*     */             
/* 117 */             Integer.valueOf(1), dustOredict, Integer.valueOf(1), stoneDust, Integer.valueOf(1) });
/*     */       
/* 119 */       helper.registerCentrifugeRecipe(miscHelper
/* 120 */           .getRecipeKey("ic2.purified_crushed_to_dust_centrifuge", material.getName()), purifiedCrushedOredict, 1, 1500, new Object[] { extraTinyDustOredict, 
/*     */             
/* 122 */             Integer.valueOf(1), dustOredict, Integer.valueOf(1) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 129 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 130 */     builder.put("crushed", "ic2_crushed");
/* 131 */     builder.put("crushedpurified", "ic2_purified_crushed");
/* 132 */     builder.put("purified", "ic2_purified_crushed");
/* 133 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\IC2Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */