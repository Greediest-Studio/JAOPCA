/*    */ package thelm.jaopca.compat.techreborn;
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
/*    */ @JAOPCAModule(modDependencies = {"techreborn"})
/*    */ public class TechRebornModule
/*    */   implements IModule
/*    */ {
/* 23 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bauxite", "Cinnabar", "Coal", "Copper", "Diamond", "Emerald", "Galena", "Gold", "Iron", "Lapis", "Lead", "Olivine", "Peridot", "Platinum", "Pyrite", "Redstone", "Ruby", "Sapphire", "Silver", "Sodalite", "Sheldonite", "Sphalerite", "Tin", "Tungsten" }));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return "techreborn";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 35 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 36 */     builder.put(Integer.valueOf(0), "dust");
/* 37 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 42 */     return EnumSet.copyOf(Arrays.asList(MaterialType.ORE));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 47 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 52 */     TechRebornHelper helper = TechRebornHelper.INSTANCE;
/* 53 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 54 */     for (IMaterial material : moduleData.getMaterials()) {
/* 55 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 56 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 57 */       helper.registerGrinderRecipe(miscHelper
/* 58 */           .getRecipeKey("techreborn.ore_to_dust_grinder", material.getName()), oreOredict, 1, dustOredict, 
/* 59 */           material.getType().isDust() ? 4 : 2, 300, 2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\TechRebornModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */