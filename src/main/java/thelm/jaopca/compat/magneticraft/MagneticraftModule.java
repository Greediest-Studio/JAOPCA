/*     */ package thelm.jaopca.compat.magneticraft;
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
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ @JAOPCAModule(modDependencies = {"magneticraft"}, classDependencies = {"com.cout970.magneticraft.api.registries.machines.grinder.IGrinderRecipeManager"})
/*     */ public class MagneticraftModule
/*     */   implements IModule
/*     */ {
/*  36 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Cobalt", "Copper", "Galena", "Gold", "Iron", "Lead", "Mithril", "Nickel", "Osmium", "Silver", "Tin", "Tungsten", "Zinc" }));
/*     */ 
/*     */   
/*     */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */ 
/*     */   
/*  42 */   private final IForm rockyChunkForm = ApiImpl.INSTANCE.newForm(this, "magneticraft_rocky_chunk", (IFormType)ItemFormType.INSTANCE)
/*  43 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("rockyChunk").setDefaultMaterialBlacklist(BLACKLIST);
/*  44 */   private final IForm chunkForm = ApiImpl.INSTANCE.newForm(this, "magneticraft_chunk", (IFormType)ItemFormType.INSTANCE)
/*  45 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("chunk").setDefaultMaterialBlacklist(BLACKLIST);
/*  46 */   private final IFormRequest formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.rockyChunkForm, this.chunkForm }).setGrouped(true);
/*     */ 
/*     */   
/*     */   public String getName() {
/*  50 */     return "magneticraft";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  55 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  56 */     builder.put(Integer.valueOf(1), "dust");
/*  57 */     builder.put(Integer.valueOf(2), "dust");
/*  58 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  63 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  68 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  73 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  78 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  83 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  84 */     MagneticraftHelper helper = MagneticraftHelper.INSTANCE;
/*  85 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  86 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/*  87 */     for (IMaterial material : this.formRequest.getMaterials()) {
/*  88 */       IItemInfo rockyChunkInfo = itemFormType.getMaterialFormInfo(this.rockyChunkForm, material);
/*  89 */       String rockyChunkOredict = miscHelper.getOredictName("rockyChunk", material.getName());
/*  90 */       IItemInfo chunkInfo = itemFormType.getMaterialFormInfo(this.chunkForm, material);
/*  91 */       String chunkOredict = miscHelper.getOredictName("chunk", material.getName());
/*  92 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  93 */       String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/*  94 */       String secondExtraDustOredict = miscHelper.getOredictName("dust", material.getExtra(2).getName());
/*  95 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*     */       
/*  97 */       IDynamicSpecConfig config = this.configs.get(material);
/*  98 */       String configByproduct = config.getDefinedString("magneticraft.grinderByproduct", "minecraft:gravel", miscHelper
/*  99 */           .metaItemPredicate(), "The default byproduct material to output in Magneticraft's grinder.");
/* 100 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*     */       
/* 102 */       helper.registerCrushingTableRecipe(miscHelper
/* 103 */           .getRecipeKey("magneticraft.ore_to_rocky_chunk_crushing_table", material.getName()), oreOredict, rockyChunkInfo, 1);
/*     */       
/* 105 */       helper.registerGrinderRecipe(miscHelper
/* 106 */           .getRecipeKey("magneticraft.ore_to_rocky_chunk_grinder", material.getName()), oreOredict, rockyChunkInfo, 1, byproduct, 1, 0.15F, 50.0F);
/*     */ 
/*     */       
/* 109 */       configByproduct = config.getDefinedString("magneticraft.sluiceBoxByproduct", "minecraft:cobblestone", miscHelper
/* 110 */           .metaItemPredicate(), "The default byproduct material to output in Magneticraft's sluice box.");
/* 111 */       byproduct = miscHelper.parseMetaItem(configByproduct);
/*     */       
/* 113 */       if (material.hasExtra(2)) {
/* 114 */         helper.registerSluiceBoxRecipe(miscHelper
/* 115 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sluice_box", material.getName()), rockyChunkOredict, new Object[] { chunkInfo, 
/*     */               
/* 117 */               Integer.valueOf(1), Float.valueOf(1.0F), extraDustOredict, Integer.valueOf(1), Float.valueOf(0.15F), secondExtraDustOredict, Integer.valueOf(1), Float.valueOf(0.15F), byproduct, Integer.valueOf(1), Float.valueOf(0.15F) });
/*     */         
/* 119 */         helper.registerSieveRecipe(miscHelper
/* 120 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sieve", material.getName()), rockyChunkOredict, chunkInfo, 1, 1.0F, extraDustOredict, 1, 0.15F, secondExtraDustOredict, 1, 0.15F, 50.0F);
/*     */       
/*     */       }
/* 123 */       else if (material.hasExtra(1)) {
/* 124 */         helper.registerSluiceBoxRecipe(miscHelper
/* 125 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sluice_box", material.getName()), rockyChunkOredict, new Object[] { chunkInfo, 
/*     */               
/* 127 */               Integer.valueOf(1), Float.valueOf(1.0F), extraDustOredict, Integer.valueOf(1), Float.valueOf(0.15F), byproduct, Integer.valueOf(1), Float.valueOf(0.15F) });
/*     */         
/* 129 */         helper.registerSieveRecipe(miscHelper
/* 130 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sieve", material.getName()), rockyChunkOredict, chunkInfo, 1, 1.0F, extraDustOredict, 1, 0.15F, 50.0F);
/*     */       }
/*     */       else {
/*     */         
/* 134 */         helper.registerSluiceBoxRecipe(miscHelper
/* 135 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sluice_box", material.getName()), rockyChunkOredict, new Object[] { chunkInfo, 
/*     */               
/* 137 */               Integer.valueOf(1), Float.valueOf(1.0F), byproduct, Integer.valueOf(1), Float.valueOf(0.15F) });
/*     */         
/* 139 */         helper.registerSieveRecipe(miscHelper
/* 140 */             .getRecipeKey("magneticraft.rocky_chunk_to_chunk_sieve", material.getName()), rockyChunkOredict, chunkInfo, 1, 1.0F, 50.0F);
/*     */       } 
/*     */ 
/*     */       
/* 144 */       apiImpl.registerSmeltingRecipe(miscHelper
/* 145 */           .getRecipeKey("magneticraft.rocky_chunk_to_material", material.getName()), rockyChunkOredict, materialOredict, 1, 0.1F);
/*     */       
/* 147 */       apiImpl.registerSmeltingRecipe(miscHelper
/* 148 */           .getRecipeKey("magneticraft.chunk_to_material", material.getName()), chunkOredict, materialOredict, 2, 0.1F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 155 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 156 */     builder.put("rockychunk", "magneticraft_rocky_chunk");
/* 157 */     builder.put("chunk", "magneticraft_chunk");
/* 158 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\MagneticraftModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */