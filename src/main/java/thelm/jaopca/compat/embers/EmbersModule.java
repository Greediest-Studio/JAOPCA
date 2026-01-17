/*    */ package thelm.jaopca.compat.embers;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import teamroots.embers.ConfigManager;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"embers@[1,)"})
/*    */ public class EmbersModule
/*    */   implements IModule
/*    */ {
/* 27 */   public static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Copper", "Gold", "Iron", "Lead", "Nickel", "Silver", "Tin" }));
/*    */ 
/*    */   
/*    */   private static boolean jaopcaOnly = false;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 34 */     return "embers";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 39 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 40 */     builder.put(Integer.valueOf(0), "molten");
/* 41 */     builder.put(Integer.valueOf(1), "molten");
/* 42 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 47 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 52 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 57 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 62 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 63 */     EmbersHelper helper = EmbersHelper.INSTANCE;
/* 64 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 65 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/* 66 */     int amount = ConfigManager.melterOreAmount * 2;
/* 67 */     for (IMaterial material : moduleData.getMaterials()) {
/* 68 */       if (!jaopcaOnly || moltenMaterials.contains(material)) {
/* 69 */         String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 70 */         String moltenName = miscHelper.getFluidName("", material.getName());
/* 71 */         if (material.hasExtra(1)) {
/* 72 */           String extraMoltenName = miscHelper.getFluidName("", material.getExtra(1).getName());
/* 73 */           helper.registerMeltingRecipe(miscHelper
/* 74 */               .getRecipeKey("embers.ore_to_molten", material.getName()), oreOredict, moltenName, amount, extraMoltenName, 16);
/*    */           
/*    */           continue;
/*    */         } 
/* 78 */         helper.registerMeltingRecipe(miscHelper
/* 79 */             .getRecipeKey("embers.ore_to_molten", material.getName()), oreOredict, moltenName, amount);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\embers\EmbersModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */