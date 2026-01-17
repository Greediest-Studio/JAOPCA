//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.bcoreprocessing;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.ndrei.bcoreprocessing.lib.fluids.BCFluidBase;
/*     */ import net.ndrei.bcoreprocessing.lib.fluids.FluidsRegistry;
/*     */ import thelm.jaopca.api.fluids.IFluidInfo;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.fluids.FluidFormType;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ @JAOPCAModule(modDependencies = {"bcoreprocessing"})
/*     */ public class BCOreProcessingModule
/*     */   implements IModule
/*     */ {
/*  33 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Adamantine", "Aluminium", "Aluminum", "AluminumBrass", "Antimony", "Aquarium", "Bismuth", "Brass", "Bronze", "Cadmium", "Coldiron", "Copper", "Cupronickel", "Electrum", "GalvanizedSteel", "Gold", "Invar", "Iridium", "Iron", "Lead", "Magnesium", "Manganese", "Mercury", "Mithril", "Nichrome", "Nickel", "Osmium", "Pewter", "Platinum", "Plutonium", "Rutile", "Silver", "StainlessSteel", "Starsteel", "Tantalum", "Tin", "Titanium", "Tungsten", "Uranium", "Zinc", "Zirconium" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private final IForm searingForm = ApiImpl.INSTANCE.newForm(this, "bcoreprocessing_searing", (IFormType)FluidFormType.INSTANCE)
/*  41 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setDefaultMaterialBlacklist(BLACKLIST)
/*  42 */     .setSettings((IFormSettings)FluidFormType.INSTANCE.getNewSettings()
/*  43 */       .setTemperatureFunction(material -> 1300).setMaterialFunction(material -> Material.LAVA));
/*  44 */   private final IForm hotForm = ApiImpl.INSTANCE.newForm(this, "bcoreprocessing_hot", (IFormType)FluidFormType.INSTANCE)
/*  45 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setDefaultMaterialBlacklist(BLACKLIST)
/*  46 */     .setSettings((IFormSettings)FluidFormType.INSTANCE.getNewSettings()
/*  47 */       .setTemperatureFunction(material -> 800).setMaterialFunction(material -> Material.LAVA));
/*  48 */   private final IForm coolForm = ApiImpl.INSTANCE.newForm(this, "bcoreprocessing_cool", (IFormType)FluidFormType.INSTANCE)
/*  49 */     .setMaterialTypes(new MaterialType[] { MaterialType.INGOT }).setDefaultMaterialBlacklist(BLACKLIST)
/*  50 */     .setSettings((IFormSettings)FluidFormType.INSTANCE.getNewSettings()
/*  51 */       .setTemperatureFunction(material -> 300).setMaterialFunction(material -> Material.LAVA));
/*  52 */   private final IFormRequest formRequest = ApiImpl.INSTANCE.newFormRequest(this, new IForm[] { this.searingForm, this.hotForm, this.coolForm }).setGrouped(true);
/*     */ 
/*     */   
/*     */   public String getName() {
/*  56 */     return "bcoreprocessing";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IFormRequest> getFormRequests() {
/*  61 */     return Collections.singletonList(this.formRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/*  66 */     BCOreProcessingHelper helper = BCOreProcessingHelper.INSTANCE;
/*  67 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  68 */     FluidFormType fluidFormType = FluidFormType.INSTANCE;
/*  69 */     BCFluidBase bCFluidBase = FluidsRegistry.GASEOUS_LAVA[2];
/*  70 */     for (IMaterial material : this.formRequest.getMaterials()) {
/*  71 */       IFluidInfo hotInfo = fluidFormType.getMaterialFormInfo(this.hotForm, material);
/*  72 */       String hotName = miscHelper.getFluidName("bcoreprocessing_hot", material.getName());
/*  73 */       IFluidInfo searingInfo = fluidFormType.getMaterialFormInfo(this.searingForm, material);
/*  74 */       String searingName = miscHelper.getFluidName("bcoreprocessing_searing", material.getName());
/*  75 */       IFluidInfo coolInfo = fluidFormType.getMaterialFormInfo(this.coolForm, material);
/*  76 */       String coolName = miscHelper.getFluidName("bcoreprocessing_cool", material.getName());
/*  77 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/*  78 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/*     */       
/*  80 */       helper.registerOreProcessorRecipe(miscHelper
/*  81 */           .getRecipeKey("bcoreprocessing.ore_to_searing", material.getName()), oreOredict, 1, searingInfo, 1000, bCFluidBase, 125, 40);
/*     */       
/*  83 */       helper.registerHeatableRecipe(miscHelper
/*  84 */           .getRecipeKey("bcoreprocessing.hot_to_searing", material.getName()), hotName, 100, searingInfo, 100, 1, 2);
/*     */       
/*  86 */       helper.registerCoolableRecipe(miscHelper
/*     */           
/*  88 */           .getRecipeKey("bcoreprocessing.searing_to_hot", material.getName()), searingName, 100, hotInfo, 100, 2, 1);
/*     */       
/*  90 */       helper.registerHeatableRecipe(miscHelper
/*  91 */           .getRecipeKey("bcoreprocessing.cool_to_hot", material.getName()), coolName, 100, hotInfo, 100, 0, 1);
/*     */ 
/*     */       
/*  94 */       helper.registerCoolableRecipe(miscHelper
/*  95 */           .getRecipeKey("bcoreprocessing.hot_to_cool", material.getName()), hotName, 100, coolInfo, 100, 1, 0);
/*     */ 
/*     */       
/*  98 */       helper.registerFluidProcessorRecipe(miscHelper
/*  99 */           .getRecipeKey("bcoreprocessing.searing_to_material", material.getName()), searingName, 1000, materialOredict, 1, bCFluidBase, 50, 40);
/*     */       
/* 101 */       helper.registerFluidProcessorRecipe(miscHelper
/* 102 */           .getRecipeKey("bcoreprocessing.hot_to_material", material.getName()), hotName, 1000, materialOredict, 2, bCFluidBase, 25, 40);
/*     */       
/* 104 */       helper.registerFluidProcessorRecipe(miscHelper
/* 105 */           .getRecipeKey("bcoreprocessing.cool_to_material", material.getName()), coolName, 1000, materialOredict, 3, bCFluidBase, 10, 40);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getLegacyRemaps() {
/* 112 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 113 */     builder.put("searingmolten", "bcoreprocessing_searing");
/* 114 */     builder.put("hotmolten", "bcoreprocessing_hot");
/* 115 */     builder.put("coolmolten", "bcoreprocessing_cool");
/* 116 */     return (Map<String, String>)builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\bcoreprocessing\BCOreProcessingModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
