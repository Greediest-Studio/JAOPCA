/*    */ package thelm.jaopca.compat.railcraft;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collections;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.Loader;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
/*    */ @JAOPCAModule(modDependencies = {"railcraft"})
/*    */ public class RailcraftModule
/*    */   implements IModule
/*    */ {
/* 26 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */   
/*    */   static {
/* 29 */     if (Loader.isModLoaded("ic2")) {
/* 30 */       Collections.addAll(BLACKLIST, new String[] { "Copper", "Gold", "Iron", "Lead", "Tin", "Silver", "Uranium" });
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 36 */     return "railcraft";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 41 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 42 */     builder.put(Integer.valueOf(0), "dust");
/* 43 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 48 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 53 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 58 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 59 */     RailcraftHelper helper = RailcraftHelper.INSTANCE;
/* 60 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 61 */     Set<String> oredict = apiImpl.getOredict();
/* 62 */     for (IMaterial material : moduleData.getMaterials()) {
/* 63 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 64 */       String crushedOredict = miscHelper.getOredictName("crushed", material.getName());
/* 65 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 66 */       if (oredict.contains(crushedOredict)) {
/* 67 */         helper.registerRockCrusherRecipe(miscHelper
/* 68 */             .getRecipeKey("railcraft.ore_to_crushed", material.getName()), oreOredict, 100, new Object[] { crushedOredict, 
/*    */               
/* 70 */               Integer.valueOf(2), Float.valueOf(1.0F) });
/*    */         
/*    */         continue;
/*    */       } 
/* 74 */       helper.registerRockCrusherRecipe(miscHelper
/* 75 */           .getRecipeKey("railcraft.ore_to_dust", material.getName()), oreOredict, 100, new Object[] { dustOredict, 
/*    */             
/* 77 */             Integer.valueOf(2), Float.valueOf(1.0F) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\railcraft\RailcraftModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */