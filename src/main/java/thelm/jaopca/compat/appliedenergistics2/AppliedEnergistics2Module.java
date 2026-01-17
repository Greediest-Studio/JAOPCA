/*    */ package thelm.jaopca.compat.appliedenergistics2;
/*    */ 
/*    */ import appeng.core.AEConfig;
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collections;
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
/*    */ @JAOPCAModule(modDependencies = {"appliedenergistics2"})
/*    */ public class AppliedEnergistics2Module
/*    */   implements IModule
/*    */ {
/* 24 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return "appliedenergistics2";
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
/* 45 */     if (BLACKLIST.isEmpty()) {
/* 46 */       Collections.addAll(BLACKLIST, AEConfig.instance().getGrinderOres());
/*    */     }
/* 48 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 53 */     AppliedEnergistics2Helper helper = AppliedEnergistics2Helper.INSTANCE;
/* 54 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 55 */     float chance = (float)(AEConfig.instance().getOreDoublePercentage() / 100.0D);
/* 56 */     for (IMaterial material : moduleData.getMaterials()) {
/* 57 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 58 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 59 */       helper.registerGrinderRecipe(miscHelper
/* 60 */           .getRecipeKey("appliedenergistics2.ore_to_dust", material.getName()), oreOredict, dustOredict, 1, dustOredict, 1, chance, 8);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\appliedenergistics2\AppliedEnergistics2Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */