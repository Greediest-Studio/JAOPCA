/*    */ package thelm.jaopca.compat.qmd;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"qmd"}, classDependencies = {"lach_01298.qmd.QMD"})
/*    */ public class QMDModule
/*    */   implements IModule
/*    */ {
/* 25 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Aluminium", "Aluminum", "Boron", "Copper", "Gold", "Iridium", "Iron", "Lead", "Lithium", "Magnesium", "Nickel", "Osmium", "Platinum", "Silver", "Thorium", "Tin", "Titanium", "Uranium" }));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return "qmd";
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 36 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 37 */     builder.put(Integer.valueOf(0), "dust");
/* 38 */     builder.put(Integer.valueOf(1), "dust");
/* 39 */     builder.put(Integer.valueOf(2), "dust");
/* 40 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 45 */     return EnumSet.of(MaterialType.INGOT);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 50 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 55 */     QMDHelper helper = QMDHelper.INSTANCE;
/* 56 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*    */ 
/*    */ 
/*    */     
/* 60 */     Object[] fluidInput = { "nitric_acid", Integer.valueOf(16), "hydrochloric_acid", Integer.valueOf(16), "sulfuric_acid", Integer.valueOf(16) };
/*    */     
/* 62 */     for (IMaterial material : moduleData.getMaterials()) {
/* 63 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 64 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/*    */ 
/*    */       
/* 67 */       Object[] output = { dustOredict, Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(0) };
/*    */       
/* 69 */       if (material.hasExtra(1)) {
/* 70 */         String extraDustOredict = miscHelper.getOredictName("dust", material.getExtra(1).getName());
/* 71 */         output = ArrayUtils.addAll(output, new Object[] { extraDustOredict, Integer.valueOf(1), Integer.valueOf(10), Integer.valueOf(0) });
/*    */       } 
/* 73 */       if (material.hasExtra(2)) {
/* 74 */         String secondExtraDustOredict = miscHelper.getOredictName("dust", material.getExtra(2).getName());
/* 75 */         output = ArrayUtils.addAll(output, new Object[] { secondExtraDustOredict, Integer.valueOf(1), Integer.valueOf(10), Integer.valueOf(0) });
/*    */       } 
/* 77 */       helper.registerOreLeacherRecipe(miscHelper
/* 78 */           .getRecipeKey("qmd.ore_to_dust", material.getName()), oreOredict, 1, fluidInput, output);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\qmd\QMDModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */