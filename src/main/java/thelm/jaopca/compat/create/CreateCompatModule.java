/*    */ package thelm.jaopca.compat.create;
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
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"create"})
/*    */ public class CreateCompatModule
/*    */   implements IModule
/*    */ {
/* 24 */   private static final Set<String> BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Bismuth", "Brass", "Californium", "Chrome", "Constantan", "Copper", "Electrum", "Gold", "Iron", "Lead", "Mingrade", "Nickel", "Steel", "Thorium", "Tin", "Titanium", "Tungsten", "Uranium", "Zinc", "Zirconium" }));
/*    */ 
/*    */   
/* 27 */   private static Set<String> configToPlateBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return "create_compat";
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
/* 43 */         .getDefinedStringList("recipes.toPlateMaterialBlacklist", new ArrayList(), miscHelper
/* 44 */           .configMaterialPredicate(), "The materials that should not have press to plate recipes added."), configToPlateBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 50 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 51 */     CreateHelper helper = CreateHelper.INSTANCE;
/* 52 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 53 */     Set<String> oredict = apiImpl.getOredict();
/* 54 */     for (IMaterial material : moduleData.getMaterials()) {
/* 55 */       MaterialType type = material.getType();
/* 56 */       String name = material.getName();
/* 57 */       if (type.isIngot() && !BLACKLIST.contains(name) && !configToPlateBlacklist.contains(name)) {
/* 58 */         String materialOredict = miscHelper.getOredictName(type.getFormName(), name);
/* 59 */         String plateOredict = miscHelper.getOredictName("plate", name);
/* 60 */         if (oredict.contains(plateOredict))
/* 61 */           helper.registerPressingRecipe(miscHelper
/* 62 */               .getRecipeKey("create.material_to_plate", name), materialOredict, plateOredict, 1); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\CreateCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */