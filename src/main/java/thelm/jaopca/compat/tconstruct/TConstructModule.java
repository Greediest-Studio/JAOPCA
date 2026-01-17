/*    */ package thelm.jaopca.compat.tconstruct;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import slimeknights.tconstruct.common.config.Config;
/*    */ import slimeknights.tconstruct.library.MaterialIntegration;
/*    */ import slimeknights.tconstruct.library.TinkerRegistry;
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
/*    */ @JAOPCAModule(modDependencies = {"tconstruct"})
/*    */ public class TConstructModule
/*    */   implements IModule
/*    */ {
/* 31 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */   
/*    */   private static boolean jaopcaOnly = true;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return "tconstruct";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 42 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 43 */     builder.put(Integer.valueOf(0), "molten");
/* 44 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 49 */     return EnumSet.of(MaterialType.INGOT, MaterialType.GEM, MaterialType.CRYSTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 54 */     if (BLACKLIST.isEmpty()) {
/* 55 */       TinkerRegistry.getMaterialIntegrations().stream().filter(mi -> (mi.fluid != null))
/* 56 */         .map(mi -> mi.oreSuffix).filter(StringUtils::isNotEmpty).forEach(BLACKLIST::add);
/* 57 */       BLACKLIST.add("Emerald");
/*    */     } 
/* 59 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 64 */     jaopcaOnly = config.getDefinedBoolean("recipes.jaopcaOnly", jaopcaOnly, "Should the module only add recipes for materials with JAOPCA molten fluids.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 69 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 70 */     TConstructHelper helper = TConstructHelper.INSTANCE;
/* 71 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 72 */     Set<IMaterial> moltenMaterials = apiImpl.getForm("molten").getMaterials();
/* 73 */     ToIntFunction<FluidStack> tempFunction = stack -> stack.getFluid().getTemperature(stack) - 300;
/* 74 */     for (IMaterial material : moduleData.getMaterials()) {
/* 75 */       if (!jaopcaOnly || moltenMaterials.contains(material)) {
/* 76 */         String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 77 */         String moltenName = miscHelper.getFluidName("", material.getName());
/* 78 */         boolean isIngot = material.getType().isIngot();
/* 79 */         int amount = (int)Math.floor((isIngot ? '' : 'ʚ') * Config.oreToIngotRatio);
/* 80 */         helper.registerMeltingRecipe(miscHelper
/* 81 */             .getRecipeKey("tconstruct.ore_to_molten", material.getName()), oreOredict, moltenName, amount, tempFunction);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\tconstruct\TConstructModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */