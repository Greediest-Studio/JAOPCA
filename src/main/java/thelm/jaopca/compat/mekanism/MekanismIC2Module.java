/*    */ package thelm.jaopca.compat.mekanism;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.compat.ic2.IC2Helper;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"mekanism", "ic2"})
/*    */ public class MekanismIC2Module
/*    */   implements IModule
/*    */ {
/* 25 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Copper", "Gold", "Iron", "Lead", "Osmium", "Silver", "Tin" }));
/*    */   
/* 27 */   private static Set<String> configToDirtyDustBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return "mekanism_ic2";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 36 */     return EnumSet.allOf(MaterialType.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 41 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 42 */     miscHelper.caclulateMaterialSet(config
/* 43 */         .getDefinedStringList("recipes.toDirtyDustMaterialBlacklist", new ArrayList(), miscHelper
/* 44 */           .configMaterialPredicate(), "The materials that should not have macerator to dirty dust recipes added."), configToDirtyDustBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 50 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 51 */     IC2Helper helper = IC2Helper.INSTANCE;
/* 52 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 53 */     Set<String> oredict = apiImpl.getOredict();
/* 54 */     for (IMaterial material : moduleData.getMaterials()) {
/* 55 */       MaterialType type = material.getType();
/* 56 */       String name = material.getName();
/* 57 */       if (type == MaterialType.INGOT && !BLACKLIST.contains(name) && !configToDirtyDustBlacklist.contains(name)) {
/* 58 */         String clumpOredict = miscHelper.getOredictName("clump", material.getName());
/* 59 */         String dirtyDustOredict = miscHelper.getOredictName("dustDirty", material.getName());
/* 60 */         if (oredict.contains(clumpOredict) && oredict.contains(dirtyDustOredict))
/* 61 */           helper.registerMaceratorRecipe(miscHelper
/* 62 */               .getRecipeKey("mekanism_ic2.clump_to_dirty_dust", material.getName()), clumpOredict, 1, dirtyDustOredict, 1); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\MekanismIC2Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */