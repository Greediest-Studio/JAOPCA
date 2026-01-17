/*     */ package thelm.jaopca.compat.abyssalcraft;
/*     */ 
/*     */ import com.shinoow.abyssalcraft.lib.ACConfig;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
/*     */ @JAOPCAModule(modDependencies = {"abyssalcraft@[1.9.19,2.0.0-ALPHA-8)"})
/*     */ public class AbyssalCraftCompatModule
/*     */   implements IModule
/*     */ {
/*  26 */   private static final Set<String> BLOCK_TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  29 */   private static final Set<String> MATERIAL_TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Calcium", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Magnesium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  32 */   private static final Set<String> NUGGET_TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Calcium", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Magnesium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  35 */   private static final Set<String> DUST_TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Copper", "Gold", "Iron", "Tin" }));
/*     */   
/*  37 */   private static final Set<String> TRANSMUTE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Calcium", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Magnesium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  40 */   private static Set<String> configBlockToCrystalBlacklist = new TreeSet<>();
/*  41 */   private static Set<String> configMaterialToCrystalBlacklist = new TreeSet<>();
/*  42 */   private static Set<String> configNuggetToCrystalBlacklist = new TreeSet<>();
/*  43 */   private static Set<String> configDustToCrystalBlacklist = new TreeSet<>();
/*  44 */   private static Set<String> configToNuggetBlacklist = new TreeSet<>();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  48 */     return "abyssalcraft_compat";
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  53 */     return EnumSet.allOf(MaterialType.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/*  58 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  59 */     miscHelper.caclulateMaterialSet(config
/*  60 */         .getDefinedStringList("recipes.blockToCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  61 */           .configMaterialPredicate(), "The materials that should not have crystallizer block to crystal recipes added."), configBlockToCrystalBlacklist);
/*     */     
/*  63 */     miscHelper.caclulateMaterialSet(config
/*  64 */         .getDefinedStringList("recipes.materialToCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  65 */           .configMaterialPredicate(), "The materials that should not have crystallizer material to crystal recipes added."), configMaterialToCrystalBlacklist);
/*     */     
/*  67 */     miscHelper.caclulateMaterialSet(config
/*  68 */         .getDefinedStringList("recipes.nuggetToCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  69 */           .configMaterialPredicate(), "The materials that should not have crystallizer nugget to crystal recipes added."), configNuggetToCrystalBlacklist);
/*     */     
/*  71 */     miscHelper.caclulateMaterialSet(config
/*  72 */         .getDefinedStringList("recipes.dustToCrystalMaterialBlacklist", new ArrayList(), miscHelper
/*  73 */           .configMaterialPredicate(), "The materials that should not have crystallizer dust to crystal recipes added."), configDustToCrystalBlacklist);
/*     */     
/*  75 */     miscHelper.caclulateMaterialSet(config
/*  76 */         .getDefinedStringList("recipes.transmuteToNuggetMaterialBlacklist", new ArrayList(), miscHelper
/*  77 */           .configMaterialPredicate(), "The materials that should not have transmutator to nugget recipes added."), configToNuggetBlacklist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  83 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/*  84 */     AbyssalCraftHelper helper = AbyssalCraftHelper.INSTANCE;
/*  85 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  86 */     Set<String> oredict = apiImpl.getOredict();
/*  87 */     boolean rework = ACConfig.crystal_rework;
/*  88 */     for (IMaterial material : moduleData.getMaterials()) {
/*  89 */       MaterialType type = material.getType();
/*  90 */       String name = material.getName();
/*  91 */       if (type.isIngot() && !BLOCK_TO_CRYSTAL_BLACKLIST.contains(name) && !configBlockToCrystalBlacklist.contains(name)) {
/*  92 */         String blockOredict = miscHelper.getOredictName("block", name);
/*  93 */         if (rework) {
/*  94 */           String crystalClusterOredict = miscHelper.getOredictName("crystalCluster", name);
/*  95 */           if (oredict.contains(blockOredict) && oredict.contains(crystalClusterOredict)) {
/*  96 */             helper.registerCrystallizationRecipe(miscHelper
/*  97 */                 .getRecipeKey("abyssalcraft.block_to_crystal_cluster", name), blockOredict, crystalClusterOredict, 1, 0.9F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 102 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 103 */           if (oredict.contains(blockOredict) && oredict.contains(crystalOredict)) {
/* 104 */             helper.registerCrystallizationRecipe(miscHelper
/* 105 */                 .getRecipeKey("abyssalcraft.block_to_crystal", name), blockOredict, crystalOredict, 4, 0.9F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 110 */       if (type.isIngot() && !MATERIAL_TO_CRYSTAL_BLACKLIST.contains(name) && !configMaterialToCrystalBlacklist.contains(name)) {
/* 111 */         String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), name);
/* 112 */         if (rework) {
/* 113 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 114 */           if (oredict.contains(crystalOredict)) {
/* 115 */             helper.registerCrystallizationRecipe(miscHelper
/* 116 */                 .getRecipeKey("abyssalcraft.material_to_crystal", name), materialOredict, crystalOredict, 1, 0.1F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 121 */           String crystalShardOredict = miscHelper.getOredictName("crystalShard", name);
/* 122 */           if (oredict.contains(crystalShardOredict)) {
/* 123 */             helper.registerCrystallizationRecipe(miscHelper
/* 124 */                 .getRecipeKey("abyssalcraft.material_to_crystal_shard", name), materialOredict, crystalShardOredict, 4, 0.1F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 129 */       if (type.isIngot() && !NUGGET_TO_CRYSTAL_BLACKLIST.contains(name) && !configNuggetToCrystalBlacklist.contains(name)) {
/* 130 */         String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 131 */         if (rework) {
/* 132 */           String crystalShardOredict = miscHelper.getOredictName("crystalShard", name);
/* 133 */           if (oredict.contains(nuggetOredict) && oredict.contains(crystalShardOredict)) {
/* 134 */             helper.registerCrystallizationRecipe(miscHelper
/* 135 */                 .getRecipeKey("abyssalcraft.nugget_to_crystal_shard", name), nuggetOredict, crystalShardOredict, 1, 0.1F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 140 */           String crystalFragmentOredict = miscHelper.getOredictName("crystalFragment", name);
/* 141 */           if (oredict.contains(nuggetOredict) && oredict.contains(crystalFragmentOredict)) {
/* 142 */             helper.registerCrystallizationRecipe(miscHelper
/* 143 */                 .getRecipeKey("abyssalcraft.nugget_to_crystal_fragment", name), nuggetOredict, crystalFragmentOredict, 4, 0.1F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       if (type.isIngot() && !DUST_TO_CRYSTAL_BLACKLIST.contains(name) && !configDustToCrystalBlacklist.contains(name)) {
/* 149 */         String dustOredict = miscHelper.getOredictName("dust", name);
/* 150 */         if (rework) {
/* 151 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 152 */           if (oredict.contains(dustOredict) && oredict.contains(crystalOredict)) {
/* 153 */             helper.registerCrystallizationRecipe(miscHelper
/* 154 */                 .getRecipeKey("abyssalcraft.dust_to_crystal", name), dustOredict, crystalOredict, 1, 0.1F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 159 */           String crystalShardOredict = miscHelper.getOredictName("crystalShard", name);
/* 160 */           if (oredict.contains(dustOredict) && oredict.contains(crystalShardOredict)) {
/* 161 */             helper.registerCrystallizationRecipe(miscHelper
/* 162 */                 .getRecipeKey("abyssalcraft.dust_to_crystal_shard", name), dustOredict, crystalShardOredict, 4, 0.1F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 167 */       if (type.isIngot() && !TRANSMUTE_BLACKLIST.contains(name) && !configToNuggetBlacklist.contains(name)) {
/* 168 */         String nuggetOredict = miscHelper.getOredictName("nugget", name);
/* 169 */         if (rework) {
/* 170 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 171 */           if (oredict.contains(nuggetOredict) && oredict.contains(crystalOredict)) {
/* 172 */             helper.registerTransmutationRecipe(miscHelper
/* 173 */                 .getRecipeKey("abyssalcraft.crystal_to_nugget", name), crystalOredict, nuggetOredict, 1, 0.2F);
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/* 178 */         String crystalShardOredict = miscHelper.getOredictName("crystalShard", name);
/* 179 */         if (oredict.contains(nuggetOredict) && oredict.contains(crystalShardOredict))
/* 180 */           helper.registerTransmutationRecipe(miscHelper
/* 181 */               .getRecipeKey("abyssalcraft.crystal_shard_to_nugget", name), crystalShardOredict, nuggetOredict, 1, 0.2F); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\AbyssalCraftCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */