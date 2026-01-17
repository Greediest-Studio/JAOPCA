//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.utils;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSortedSet;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.LoaderState;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.JAOPCAApi;
/*     */ import thelm.jaopca.api.blocks.IBlockFormType;
/*     */ import thelm.jaopca.api.entities.IEntityEntryFormType;
/*     */ import thelm.jaopca.api.fluids.IFluidFormType;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.helpers.IJsonHelper;
/*     */ import thelm.jaopca.api.helpers.IMiscHelper;
/*     */ import thelm.jaopca.api.items.IItemFormType;
/*     */ import thelm.jaopca.api.localization.ILocalizer;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ import thelm.jaopca.blocks.BlockFormType;
/*     */ import thelm.jaopca.client.resources.ResourceHandler;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.custom.json.EnumDeserializer;
/*     */ import thelm.jaopca.custom.json.ForgeRegistryEntrySupplierDeserializer;
/*     */ import thelm.jaopca.custom.json.MaterialEnumFunctionDeserializer;
/*     */ import thelm.jaopca.custom.json.MaterialFunctionDeserializer;
/*     */ import thelm.jaopca.custom.json.MaterialMappedFunctionDeserializer;
/*     */ import thelm.jaopca.fluids.FluidFormType;
/*     */ import thelm.jaopca.forms.Form;
/*     */ import thelm.jaopca.forms.FormHandler;
/*     */ import thelm.jaopca.forms.FormRequest;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ import thelm.jaopca.localization.LocalizationHandler;
/*     */ import thelm.jaopca.localization.LocalizationRepoHandler;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.oredict.OredictHandler;
/*     */ import thelm.jaopca.recipes.RecipeHandler;
/*     */ import thelm.jaopca.recipes.ShapedRecipeAction;
/*     */ import thelm.jaopca.recipes.ShapelessRecipeAction;
/*     */ import thelm.jaopca.recipes.SmeltingRecipeAction;
/*     */ 
/*     */ 
/*     */ public class ApiImpl
/*     */   extends JAOPCAApi
/*     */ {
/*  67 */   private static final Logger LOGGER = LogManager.getLogger();
/*  68 */   public static final ApiImpl INSTANCE = new ApiImpl();
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  73 */     JAOPCAApi.setInstance(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormType blockFormType() {
/*  78 */     return (IBlockFormType)BlockFormType.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IItemFormType itemFormType() {
/*  83 */     return (IItemFormType)ItemFormType.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormType fluidFormType() {
/*  88 */     return (IFluidFormType)FluidFormType.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntityEntryFormType entityTypeFormType() {
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFormType getFormType(String name) {
/*  99 */     return FormTypeHandler.getFormType(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm newForm(IModule module, String name, IFormType type) {
/* 104 */     return (IForm)new Form(module, name, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public IFormRequest newFormRequest(IModule module, IForm... forms) {
/* 109 */     return (IFormRequest)new FormRequest(module, forms);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IMiscHelper miscHelper() {
/* 115 */     return MiscHelper.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IJsonHelper jsonHelper() {
/* 120 */     return JsonHelper.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonDeserializer<Enum<?>> enumDeserializer() {
/* 125 */     return (JsonDeserializer<Enum<?>>)EnumDeserializer.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonDeserializer<Function<IMaterial, Enum<?>>> materialEnumFunctionDeserializer() {
/* 130 */     return (JsonDeserializer<Function<IMaterial, Enum<?>>>)MaterialEnumFunctionDeserializer.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> JsonDeserializer<Function<IMaterial, T>> materialMappedFunctionDeserializer(Function<String, T> stringToValue, Function<T, String> valueToString) {
/* 135 */     return (JsonDeserializer<Function<IMaterial, T>>)new MaterialMappedFunctionDeserializer(stringToValue, valueToString);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonDeserializer<Function<IMaterial, ?>> materialFunctionDeserializer() {
/* 140 */     return (JsonDeserializer<Function<IMaterial, ?>>)MaterialFunctionDeserializer.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonDeserializer<Supplier<IForgeRegistryEntry<?>>> forgeRegistryEntrySupplierDeserializer() {
/* 145 */     return (JsonDeserializer<Supplier<IForgeRegistryEntry<?>>>)ForgeRegistryEntrySupplierDeserializer.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm getForm(String name) {
/* 150 */     return FormHandler.getForm(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IForm> getForms() {
/* 155 */     return (Set<IForm>)ImmutableSortedSet.copyOf(FormHandler.getForms());
/*     */   }
/*     */ 
/*     */   
/*     */   public IMaterial getMaterial(String name) {
/* 160 */     return (IMaterial)MaterialHandler.getMaterial(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IMaterial> getMaterials() {
/* 165 */     return (Set<IMaterial>)ImmutableSortedSet.copyOf(MaterialHandler.getMaterials());
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs creativeTab() {
/* 170 */     return ItemFormType.getCreativeTab();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getOredict() {
/* 175 */     return Collections.unmodifiableSet(OredictHandler.getOredict());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ResourceLocation> getRecipes() {
/* 180 */     return RecipeHandler.getRegisteredRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILocalizer currentLocalizer() {
/* 185 */     return LocalizationHandler.getCurrentLocalizer();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> currentMaterialLocalizationMap() {
/* 190 */     return LocalizationRepoHandler.getCurrentLocalizationMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerBlacklistedMaterialNames(String... names) {
/* 195 */     return MaterialHandler.registerBlacklistedMaterialNames(names);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerUsedPlainPrefixes(String... prefixes) {
/* 200 */     return MaterialHandler.registerUsedPlainPrefixes(prefixes);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerMaterialAlternativeNames(String name, String... alternatives) {
/* 205 */     return MaterialHandler.registerMaterialAlternativeNames(name, alternatives);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerFormType(IFormType type) {
/* 210 */     return FormTypeHandler.registerFormType(type);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerOredict(String oredict, Item item) {
/* 215 */     if (ConfigHandler.OREDICT_BLACKLIST.contains(oredict) || item == null || item == Items.AIR) {
/* 216 */       return false;
/*     */     }
/* 218 */     NonNullList<ItemStack> stacks = NonNullList.create();
/* 219 */     item.getSubItems(CreativeTabs.SEARCH, stacks);
/* 220 */     for (ItemStack stack : stacks) {
/* 221 */       OreDictionary.registerOre(oredict, stack);
/*     */     }
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerOredict(String oredict, Block block) {
/* 228 */     return registerOredict(oredict, (Item)ForgeRegistries.ITEMS.getValue(block.getRegistryName()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerOredict(String oredict, ItemStack stack) {
/* 233 */     if (ConfigHandler.OREDICT_BLACKLIST.contains(oredict) || stack.isEmpty()) {
/* 234 */       return false;
/*     */     }
/* 236 */     OreDictionary.registerOre(oredict, stack);
/* 237 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerOredict(String oredict, String metaItemString) {
/* 242 */     if (metaItemString.matches(".*?@\\d*$")) {
/* 243 */       return registerOredict(oredict, MiscHelper.INSTANCE.parseMetaItem(metaItemString));
/*     */     }
/*     */     
/* 246 */     return registerOredict(oredict, (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(metaItemString)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerDefaultGemOverride(String materialName) {
/* 252 */     ConfigHandler.DEFAULT_GEM_OVERRIDES.add(materialName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultCrystalOverride(String materialName) {
/* 257 */     ConfigHandler.DEFAULT_CRYSTAL_OVERRIDES.add(materialName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultDustOverride(String materialName) {
/* 262 */     ConfigHandler.DEFAULT_DUST_OVERRIDES.add(materialName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerRecipe(ResourceLocation key, IRecipeAction recipeAction) {
/* 267 */     if (ConfigHandler.RECIPE_BLACKLIST.contains(key) || ConfigHandler.RECIPE_REGEX_BLACKLIST
/* 268 */       .stream().anyMatch(p -> p.matcher(key.toString()).matches())) {
/* 269 */       return false;
/*     */     }
/* 271 */     if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
/* 272 */       return RecipeHandler.registerRecipe(key, recipeAction);
/*     */     }
/*     */     
/* 275 */     return RecipeHandler.registerLateRecipe(key, recipeAction);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean registerLateRecipe(ResourceLocation key, IRecipeAction recipeAction) {
/* 281 */     if (ConfigHandler.RECIPE_BLACKLIST.contains(key) || ConfigHandler.RECIPE_REGEX_BLACKLIST
/* 282 */       .stream().anyMatch(p -> p.matcher(key.toString()).matches())) {
/* 283 */       return false;
/*     */     }
/* 285 */     return RecipeHandler.registerLateRecipe(key, recipeAction);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerShapedRecipe(ResourceLocation key, String group, Object output, int count, Object... input) {
/* 290 */     return registerRecipe(key, (IRecipeAction)new ShapedRecipeAction(key, group, output, count, input));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerShapedRecipe(ResourceLocation key, Object output, int count, Object... input) {
/* 295 */     return registerRecipe(key, (IRecipeAction)new ShapedRecipeAction(key, output, count, input));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerShapelessRecipe(ResourceLocation key, String group, Object output, int count, Object... input) {
/* 300 */     return registerRecipe(key, (IRecipeAction)new ShapelessRecipeAction(key, group, output, count, input));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerShapelessRecipe(ResourceLocation key, Object output, int count, Object... input) {
/* 305 */     return registerRecipe(key, (IRecipeAction)new ShapelessRecipeAction(key, output, count, input));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerSmeltingRecipe(ResourceLocation key, Object input, Object output, int count, float experience) {
/* 310 */     return registerRecipe(key, (IRecipeAction)new SmeltingRecipeAction(key, input, output, count, experience));
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTextures(Supplier<List<ResourceLocation>> locations) {
/* 315 */     ResourceHandler.registerTextures(locations);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerLocalizer(ILocalizer translator, String... languages) {
/* 320 */     LocalizationHandler.registerLocalizer(translator, languages);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopc\\utils\ApiImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
