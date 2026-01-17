/*    */ package thelm.jaopca.api;
/*    */ 
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import thelm.jaopca.api.blocks.IBlockFormType;
/*    */ import thelm.jaopca.api.entities.IEntityEntryFormType;
/*    */ import thelm.jaopca.api.fluids.IFluidFormType;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.helpers.IJsonHelper;
/*    */ import thelm.jaopca.api.helpers.IMiscHelper;
/*    */ import thelm.jaopca.api.items.IItemFormType;
/*    */ import thelm.jaopca.api.localization.ILocalizer;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class JAOPCAApi
/*    */ {
/*    */   private static JAOPCAApi instance;
/*    */   
/*    */   protected static void setInstance(JAOPCAApi api) {
/* 40 */     if (instance == null) {
/* 41 */       instance = api;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean initialized() {
/* 51 */     return (instance != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static JAOPCAApi instance() {
/* 60 */     if (instance == null) {
/* 61 */       throw new IllegalStateException("Got API instance before it is set");
/*    */     }
/* 63 */     return instance;
/*    */   }
/*    */   
/*    */   public abstract IBlockFormType blockFormType();
/*    */   
/*    */   public abstract IItemFormType itemFormType();
/*    */   
/*    */   public abstract IFluidFormType fluidFormType();
/*    */   
/*    */   public abstract IEntityEntryFormType entityTypeFormType();
/*    */   
/*    */   public abstract IFormType getFormType(String paramString);
/*    */   
/*    */   public abstract IForm newForm(IModule paramIModule, String paramString, IFormType paramIFormType);
/*    */   
/*    */   public abstract IFormRequest newFormRequest(IModule paramIModule, IForm... paramVarArgs);
/*    */   
/*    */   public abstract IMiscHelper miscHelper();
/*    */   
/*    */   public abstract IJsonHelper jsonHelper();
/*    */   
/*    */   public abstract JsonDeserializer<Enum<?>> enumDeserializer();
/*    */   
/*    */   public abstract JsonDeserializer<Function<IMaterial, Enum<?>>> materialEnumFunctionDeserializer();
/*    */   
/*    */   public abstract <T> JsonDeserializer<Function<IMaterial, T>> materialMappedFunctionDeserializer(Function<String, T> paramFunction, Function<T, String> paramFunction1);
/*    */   
/*    */   public abstract JsonDeserializer<Function<IMaterial, ?>> materialFunctionDeserializer();
/*    */   
/*    */   public abstract JsonDeserializer<Supplier<IForgeRegistryEntry<?>>> forgeRegistryEntrySupplierDeserializer();
/*    */   
/*    */   public abstract IForm getForm(String paramString);
/*    */   
/*    */   public abstract Set<IForm> getForms();
/*    */   
/*    */   public abstract IMaterial getMaterial(String paramString);
/*    */   
/*    */   public abstract Set<IMaterial> getMaterials();
/*    */   
/*    */   public abstract CreativeTabs creativeTab();
/*    */   
/*    */   public abstract Set<String> getOredict();
/*    */   
/*    */   public abstract Set<ResourceLocation> getRecipes();
/*    */   
/*    */   public abstract ILocalizer currentLocalizer();
/*    */   
/*    */   public abstract Map<String, String> currentMaterialLocalizationMap();
/*    */   
/*    */   public abstract boolean registerBlacklistedMaterialNames(String... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerUsedPlainPrefixes(String... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerMaterialAlternativeNames(String paramString, String... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerFormType(IFormType paramIFormType);
/*    */   
/*    */   public abstract boolean registerOredict(String paramString, Item paramItem);
/*    */   
/*    */   public abstract boolean registerOredict(String paramString, Block paramBlock);
/*    */   
/*    */   public abstract boolean registerOredict(String paramString, ItemStack paramItemStack);
/*    */   
/*    */   public abstract boolean registerOredict(String paramString1, String paramString2);
/*    */   
/*    */   public abstract void registerDefaultGemOverride(String paramString);
/*    */   
/*    */   public abstract void registerDefaultCrystalOverride(String paramString);
/*    */   
/*    */   public abstract void registerDefaultDustOverride(String paramString);
/*    */   
/*    */   public abstract boolean registerRecipe(ResourceLocation paramResourceLocation, IRecipeAction paramIRecipeAction);
/*    */   
/*    */   public abstract boolean registerLateRecipe(ResourceLocation paramResourceLocation, IRecipeAction paramIRecipeAction);
/*    */   
/*    */   public abstract boolean registerShapedRecipe(ResourceLocation paramResourceLocation, String paramString, Object paramObject, int paramInt, Object... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerShapedRecipe(ResourceLocation paramResourceLocation, Object paramObject, int paramInt, Object... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerShapelessRecipe(ResourceLocation paramResourceLocation, String paramString, Object paramObject, int paramInt, Object... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerShapelessRecipe(ResourceLocation paramResourceLocation, Object paramObject, int paramInt, Object... paramVarArgs);
/*    */   
/*    */   public abstract boolean registerSmeltingRecipe(ResourceLocation paramResourceLocation, Object paramObject1, Object paramObject2, int paramInt, float paramFloat);
/*    */   
/*    */   public abstract void registerTextures(Supplier<List<ResourceLocation>> paramSupplier);
/*    */   
/*    */   public abstract void registerLocalizer(ILocalizer paramILocalizer, String... paramVarArgs);
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\JAOPCAApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */