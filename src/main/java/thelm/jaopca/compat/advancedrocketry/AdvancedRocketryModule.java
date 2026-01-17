/*    */ package thelm.jaopca.compat.advancedrocketry;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"advancedrocketry@[1.12.2-2,)"})
/*    */ public class AdvancedRocketryModule
/*    */   implements IModule
/*    */ {
/* 23 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Copper", "Gold", "Iridium", "Iron", "Tin", "Titanium" }));
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return "advancedrocketry";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 33 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 34 */     builder.put(Integer.valueOf(0), "dust");
/* 35 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 40 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 45 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 50 */     AdvancedRocketryHelper helper = AdvancedRocketryHelper.INSTANCE;
/* 51 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 52 */     for (IMaterial material : moduleData.getMaterials()) {
/* 53 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 54 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 55 */       helper.registerSmallPlatePressRecipe(miscHelper
/* 56 */           .getRecipeKey("advancedrocketry.ore_to_dust", material.getName()), oreOredict, dustOredict, 2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\advancedrocketry\AdvancedRocketryModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */