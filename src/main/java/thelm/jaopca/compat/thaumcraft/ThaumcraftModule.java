/*     */ package thelm.jaopca.compat.thaumcraft;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSetMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import thaumcraft.api.aspects.Aspect;
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
/*     */ @JAOPCAModule(modDependencies = {"thaumcraft"})
/*     */ public class ThaumcraftModule
/*     */   implements IModule
/*     */ {
/*  38 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Cinnabar", "Copper", "Gold", "Iron", "Lead", "NetherQuartz", "Quartz", "Silver", "Tin" }));
/*     */ 
/*     */   
/*  41 */   private final IForm clusterForm = ApiImpl.INSTANCE.newForm(this, "thaumcraft_cluster", (IFormType)ItemFormType.INSTANCE)
/*  42 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("cluster").setDefaultMaterialBlacklist(BLACKLIST);
/*     */   
/*  44 */   private List<ResourceLocation> recipeKeys = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  48 */     return "thaumcraft";
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<Integer, String> getModuleDependencies() {
/*  53 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/*  54 */     builder.put(Integer.valueOf(0), "nugget");
/*  55 */     return (Multimap<Integer, String>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  60 */     return Collections.singletonList(this.clusterForm.toRequest());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  65 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/*  70 */     return BLACKLIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  75 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  76 */     ThaumcraftHelper helper = ThaumcraftHelper.INSTANCE;
/*  77 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  78 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/*  79 */     for (IMaterial material : this.clusterForm.getMaterials()) {
/*  80 */       IItemInfo clusterInfo = itemFormType.getMaterialFormInfo(this.clusterForm, material);
/*  81 */       String clusterOredict = miscHelper.getOredictName("cluster", material.getName());
/*  82 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  83 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*  84 */       String nuggetOredict = miscHelper.getOredictName("nugget", material.getName());
/*     */       
/*  86 */       helper.registerSpecialMiningRecipe(miscHelper
/*  87 */           .getRecipeKey("thaumcraft.ore_to_cluster_mining", material.getName()), oreOredict, clusterInfo, 1, 1.0F);
/*     */       
/*  89 */       ResourceLocation recipeKey = miscHelper.getRecipeKey("thaumcraft.ore_to_cluster_crucible", material.getName());
/*  90 */       this.recipeKeys.add(recipeKey);
/*  91 */       helper.registerCrucibleRecipe(recipeKey, "METALPURIFICATION", oreOredict, new Object[] { Aspect.METAL, 
/*     */ 
/*     */             
/*  94 */             Integer.valueOf(5), Aspect.ORDER, Integer.valueOf(5) }, clusterInfo, 1);
/*     */ 
/*     */       
/*  97 */       apiImpl.registerSmeltingRecipe(miscHelper
/*  98 */           .getRecipeKey("thaumcraft.cluster_to_material", material.getName()), clusterOredict, materialOredict, 2, 1.0F);
/*     */       
/* 100 */       helper.registerSmeltingBonusRecipe(miscHelper
/* 101 */           .getRecipeKey("thaumcraft.cluster_to_nugget", material.getName()), clusterOredict, nuggetOredict, 1, 0.33F);
/*     */     } 
/*     */ 
/*     */     
/* 105 */     for (IMaterial material : moduleData.getMaterials()) {
/* 106 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 107 */       String nuggetOredict = miscHelper.getOredictName("nugget", material.getName());
/* 108 */       helper.registerSmeltingBonusRecipe(miscHelper
/* 109 */           .getRecipeKey("thaumcraft.ore_to_nugget", material.getName()), oreOredict, nuggetOredict, 1, 0.33F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPostInit(IModuleData moduleData, FMLPostInitializationEvent event) {
/* 116 */     ThaumcraftHelper.INSTANCE.registerRecipesToResearch("METALPURIFICATION", this.recipeKeys);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 121 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 122 */     builder.put("cluster", "thaumcraft_cluster");
/* 123 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\thaumcraft\ThaumcraftModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */