/*     */ package thelm.jaopca.compat.create;
/*     */ 
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
/*     */ @JAOPCAModule(modDependencies = {"create"})
/*     */ public class CreateModule
/*     */   implements IModule
/*     */ {
/*  35 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Zinc" }));
/*     */ 
/*     */   
/*     */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*     */   
/*  40 */   private final IForm crushedForm = ApiImpl.INSTANCE.newForm(this, "create_crushed", (IFormType)ItemFormType.INSTANCE)
/*  41 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crushed").setDefaultMaterialBlacklist(BLACKLIST);
/*     */ 
/*     */   
/*     */   public String getName() {
/*  45 */     return "create";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  50 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  51 */     builder.put(Integer.valueOf(0), "nugget");
/*  52 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  57 */     return Collections.singletonList(this.crushedForm.toRequest());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  62 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/*  67 */     this.configs = configs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  72 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  73 */     CreateHelper helper = CreateHelper.INSTANCE;
/*  74 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  75 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/*  76 */     for (IMaterial material : this.crushedForm.getMaterials()) {
/*  77 */       IItemInfo crushedInfo = itemFormType.getMaterialFormInfo(this.crushedForm, material);
/*  78 */       String crushedOredict = miscHelper.getOredictName("crushed", material.getName());
/*  79 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  80 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*  81 */       String nuggetOredict = miscHelper.getOredictName("nugget", material.getName());
/*     */       
/*  83 */       IDynamicSpecConfig config = this.configs.get(material);
/*  84 */       String configByproduct = config.getDefinedString("create.byproduct", "minecraft:cobblestone", miscHelper
/*  85 */           .metaItemPredicate(), "The default byproduct material to output in Create Legacy's crusher.");
/*  86 */       ItemStack byproduct = miscHelper.parseMetaItem(configByproduct);
/*     */       
/*  88 */       helper.registerCrushingRecipe(miscHelper
/*  89 */           .getRecipeKey("create.ore_to_crushed", material.getName()), oreOredict, 1280, new Object[] { crushedInfo, 
/*     */             
/*  91 */             Integer.valueOf(1), crushedInfo, 
/*  92 */             Integer.valueOf(1), Float.valueOf(0.5F), byproduct, 
/*  93 */             Integer.valueOf(1), Float.valueOf(0.25F) });
/*     */ 
/*     */       
/*  96 */       apiImpl.registerSmeltingRecipe(miscHelper
/*  97 */           .getRecipeKey("create.crushed_to_material", material.getName()), crushedOredict, materialOredict, 1, 0.1F);
/*     */       
/*  99 */       helper.registerWashingRecipe(miscHelper
/* 100 */           .getRecipeKey("create.crushed_to_nugget", material.getName()), crushedOredict, new Object[] { nuggetOredict, 
/*     */             
/* 102 */             Integer.valueOf(9) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\CreateModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */