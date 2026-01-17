//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.abyssalcraft;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.shinoow.abyssalcraft.api.necronomicon.condition.DimensionCondition;
/*     */ import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
/*     */ import com.shinoow.abyssalcraft.lib.ACConfig;
/*     */ import com.shinoow.abyssalcraft.lib.ACLib;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*     */ import thelm.jaopca.api.blocks.IBlockInfo;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.items.IItemFormSettings;
/*     */ import thelm.jaopca.api.items.IItemInfo;
/*     */ import thelm.jaopca.api.items.IMaterialFormItem;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.blocks.BlockFormType;
/*     */ import thelm.jaopca.compat.abyssalcraft.blocks.JAOPCAUnlockableBlockItem;
/*     */ import thelm.jaopca.compat.abyssalcraft.items.JAOPCAUnlockableItem;
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"abyssalcraft@[1.9.19,2.0.0-ALPHA-8)"})
/*     */ public class AbyssalCraftModule implements IModule {
/*  42 */   private static final Set<String> FORM_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Beryllium", "Calcium", "Carbon", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Magnesium", "Potassium", "Silicon", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  45 */   private static final Set<String> ORE_TO_CRYSTAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Copper", "Coralium", "Gold", "Iron", "LiquifiedCoralium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  48 */   private static final Set<String> TRANSMUTE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Aluminium", "Aluminum", "Calcium", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Magnesium", "Tin", "Zinc" }));
/*     */ 
/*     */   
/*  51 */   private static final Set<String> MATERIALIZE_TO_MATERIAL_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Copper", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium", "Tin" }));
/*     */   
/*  53 */   private static final Set<String> TO_ORE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Abyssalnite", "Coralium", "Dreadium", "Gold", "Iron", "LiquifiedCoralium" })); private final IForm crystalFragmentForm;
/*     */   private final IForm crystalShardForm;
/*     */   private final IForm crystalForm;
/*     */   private final IForm crystalClusterForm;
/*     */   private final IFormRequest formRequest;
/*     */   
/*     */   public AbyssalCraftModule() {
/*  60 */     this
/*     */       
/*  62 */       .crystalFragmentForm = ApiImpl.INSTANCE.newForm(this, "abyssalcraft_crystal_fragment", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crystalFragment").setDefaultMaterialBlacklist(FORM_BLACKLIST).setSettings((IFormSettings)ItemFormType.INSTANCE.getNewSettings()
/*  63 */         .setItemCreator((f, m, s) -> (new JAOPCAUnlockableItem(f, m, s)).setUnlockCondition((IUnlockCondition)new DimensionCondition(ACLib.dreadlands_id))));
/*     */ 
/*     */     
/*  66 */     this
/*     */       
/*  68 */       .crystalShardForm = ApiImpl.INSTANCE.newForm(this, "abyssalcraft_crystal_shard", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crystalShard").setDefaultMaterialBlacklist(FORM_BLACKLIST).setSettings((IFormSettings)ItemFormType.INSTANCE.getNewSettings()
/*  69 */         .setItemCreator((f, m, s) -> (new JAOPCAUnlockableItem(f, m, s)).setUnlockCondition((IUnlockCondition)new DimensionCondition(ACLib.dreadlands_id))));
/*     */ 
/*     */     
/*  72 */     this
/*     */       
/*  74 */       .crystalForm = ApiImpl.INSTANCE.newForm(this, "abyssalcraft_crystal", (IFormType)ItemFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crystal").setDefaultMaterialBlacklist(FORM_BLACKLIST).setSettings((IFormSettings)ItemFormType.INSTANCE.getNewSettings()
/*  75 */         .setItemCreator((f, m, s) -> (new JAOPCAUnlockableItem(f, m, s)).setUnlockCondition((IUnlockCondition)new DimensionCondition(ACLib.dreadlands_id))));
/*     */ 
/*     */     
/*  78 */     this
/*     */       
/*  80 */       .crystalClusterForm = ApiImpl.INSTANCE.newForm(this, "abyssalcraft_crystal_cluster", (IFormType)BlockFormType.INSTANCE).setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setSecondaryName("crystalCluster").setDefaultMaterialBlacklist(FORM_BLACKLIST).setSettings((IFormSettings)BlockFormType.INSTANCE.getNewSettings()
/*  81 */         .setBlockHardnessFunction(material -> 0.4D).setExplosionResistanceFunction(material -> 0.8D)
/*  82 */         .setLightOpacityFunction(material -> 0).setSoundTypeFunction(material -> SoundType.GLASS)
/*  83 */         .setHarvestToolFunction(material -> "pickaxe").setHarvestLevelFunction(material -> 3)
/*  84 */         .setBoundingBox(new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.7D, 0.8D))
/*  85 */         .setBlockItemCreator((mf, s) -> (new JAOPCAUnlockableBlockItem(mf, s)).setUnlockCondition((IUnlockCondition)new DimensionCondition(ACLib.dreadlands_id))));
/*     */ 
/*     */     
/*  88 */     this
/*  89 */       .formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.crystalFragmentForm, this.crystalShardForm, this.crystalForm, this.crystalClusterForm }).setGrouped(true);
/*     */     MinecraftForge.EVENT_BUS.register(AbyssalCraftHelper.INSTANCE);
/*     */   }
/*     */   public String getName() {
/*  93 */     return "abyssalcraft";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  98 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/* 103 */     return EnumSet.of(MaterialType.INGOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 108 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 109 */     AbyssalCraftHelper helper = AbyssalCraftHelper.INSTANCE;
/* 110 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 111 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/* 112 */     Set<String> oredict = apiImpl.getOredict();
/* 113 */     boolean rework = ACConfig.crystal_rework;
/* 114 */     for (IMaterial material : this.formRequest.getMaterials()) {
/* 115 */       IItemInfo crystalFragmentInfo = itemFormType.getMaterialFormInfo(this.crystalFragmentForm, material);
/* 116 */       String crystalFragmentOredict = miscHelper.getOredictName("crystalFragment", material.getName());
/* 117 */       IItemInfo crystalShardInfo = itemFormType.getMaterialFormInfo(this.crystalShardForm, material);
/* 118 */       String crystalShardOredict = miscHelper.getOredictName("crystalShard", material.getName());
/* 119 */       IItemInfo crystalInfo = itemFormType.getMaterialFormInfo(this.crystalForm, material);
/* 120 */       String crystalOredict = miscHelper.getOredictName("crystal", material.getName());
/* 121 */       IBlockInfo crystalClusterInfo = BlockFormType.INSTANCE.getMaterialFormInfo(this.crystalClusterForm, material);
/* 122 */       String crystalClusterOredict = miscHelper.getOredictName("crystalCluster", material.getName());
/*     */       
/* 124 */       apiImpl.registerShapelessRecipe(miscHelper
/* 125 */           .getRecipeKey("abyssalcraft.crystal_shard_to_crystal_fragment", material.getName()), crystalFragmentInfo, 9, new Object[] { crystalShardOredict });
/*     */ 
/*     */ 
/*     */       
/* 129 */       helper.registerCrystal(crystalFragmentInfo);
/* 130 */       if (!rework) {
/* 131 */         helper.registerFuel(crystalFragmentInfo, 17);
/*     */       }
/*     */       
/* 134 */       apiImpl.registerShapelessRecipe(miscHelper
/* 135 */           .getRecipeKey("abyssalcraft.crystal_fragment_to_crystal_shard", material.getName()), crystalShardInfo, 1, new Object[] { crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict, crystalFragmentOredict });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       apiImpl.registerShapelessRecipe(miscHelper
/* 142 */           .getRecipeKey("abyssalcraft.crystal_to_crystal_shard", material.getName()), crystalShardInfo, 9, new Object[] { crystalOredict });
/*     */ 
/*     */ 
/*     */       
/* 146 */       helper.registerCrystal(crystalShardInfo);
/* 147 */       if (!rework) {
/* 148 */         helper.registerFuel(crystalShardInfo, 150);
/*     */       }
/*     */       
/* 151 */       apiImpl.registerShapelessRecipe(miscHelper
/* 152 */           .getRecipeKey("abyssalcraft.crystal_shard_to_crystal", material.getName()), crystalInfo, 1, new Object[] { crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict, crystalShardOredict });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       apiImpl.registerShapelessRecipe(miscHelper
/* 159 */           .getRecipeKey("abyssalcraft.crystal_cluster_to_crystal", material.getName()), crystalInfo, 9, new Object[] { crystalClusterOredict });
/*     */ 
/*     */ 
/*     */       
/* 163 */       helper.registerCrystal(crystalInfo);
/* 164 */       if (!rework) {
/* 165 */         helper.registerFuel(crystalInfo, 1350);
/*     */       }
/*     */       
/* 168 */       apiImpl.registerShapelessRecipe(miscHelper
/* 169 */           .getRecipeKey("abyssalcraft.crystal_to_crystal_cluster", material.getName()), crystalClusterInfo, 1, new Object[] { crystalOredict, crystalOredict, crystalOredict, crystalOredict, crystalOredict, crystalOredict, crystalOredict, crystalOredict, crystalOredict });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 175 */       helper.registerCrystal(crystalClusterInfo);
/* 176 */       if (!rework) {
/* 177 */         helper.registerFuel(crystalClusterInfo, 12150);
/*     */       }
/*     */     } 
/* 180 */     for (IMaterial material : moduleData.getMaterials()) {
/* 181 */       String name = material.getName();
/* 182 */       if (!ORE_TO_CRYSTAL_BLACKLIST.contains(name)) {
/* 183 */         String oreOredict = miscHelper.getOredictName("ore", name);
/* 184 */         if (rework) {
/* 185 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 186 */           if (oredict.contains(crystalOredict)) {
/* 187 */             helper.registerCrystallizationRecipe(miscHelper
/* 188 */                 .getRecipeKey("abyssalcraft.ore_to_crystal", name), oreOredict, crystalOredict, 2, 0.1F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 193 */           String crystalShardOredict = miscHelper.getOredictName("crystalShard", name);
/* 194 */           if (oredict.contains(crystalShardOredict)) {
/* 195 */             helper.registerCrystallizationRecipe(miscHelper
/* 196 */                 .getRecipeKey("abyssalcraft.ore_to_crystal_shard", name), oreOredict, crystalShardOredict, 4, 0.1F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 201 */       if (!TRANSMUTE_BLACKLIST.contains(name)) {
/* 202 */         String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), name);
/* 203 */         if (rework) {
/* 204 */           String crystalClusterOredict = miscHelper.getOredictName("crystalCluster", name);
/* 205 */           if (oredict.contains(crystalClusterOredict)) {
/* 206 */             helper.registerTransmutationRecipe(miscHelper
/* 207 */                 .getRecipeKey("abyssalcraft.crystal_cluster_to_material", name), crystalClusterOredict, materialOredict, 1, 0.2F);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 212 */           String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 213 */           if (oredict.contains(crystalOredict)) {
/* 214 */             helper.registerTransmutationRecipe(miscHelper
/* 215 */                 .getRecipeKey("abyssalcraft.crystal_to_material_transmutation", name), crystalOredict, materialOredict, 1, 0.2F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 220 */       if (!MATERIALIZE_TO_MATERIAL_BLACKLIST.contains(name)) {
/* 221 */         String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 222 */         String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), name);
/* 223 */         if (oredict.contains(crystalOredict)) {
/* 224 */           helper.registerMaterializationRecipe(miscHelper
/* 225 */               .getRecipeKey("abyssalcraft.crystal_to_material", name), materialOredict, 1, new Object[] { crystalOredict, 
/*     */                 
/* 227 */                 Integer.valueOf(1) });
/*     */         }
/*     */       } 
/*     */       
/* 231 */       if (!TO_ORE_BLACKLIST.contains(name)) {
/* 232 */         String crystalOredict = miscHelper.getOredictName("crystal", name);
/* 233 */         String oreOredict = miscHelper.getOredictName("ore", name);
/* 234 */         if (oredict.contains(crystalOredict)) {
/* 235 */           helper.registerMaterializationRecipe(miscHelper
/* 236 */               .getRecipeKey("abyssalcraft.crystal_to_ore", name), oreOredict, 1, new Object[] { "crystalSilica", 
/*     */                 
/* 238 */                 Integer.valueOf(1), "crystalMagnesia", 
/* 239 */                 Integer.valueOf(1), crystalOredict, 
/* 240 */                 Integer.valueOf(1) });
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 249 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 250 */     builder.put("crystalfragment", "abyssalcraft_crystal_fragment");
/* 251 */     builder.put("crystalshard", "abyssalcraft_crystal_shard");
/* 252 */     builder.put("crystalabyss", "abyssalcraft_crystal");
/* 253 */     builder.put("crystalcluster", "abyssalcraft_crystal_cluster");
/* 254 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\AbyssalCraftModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
