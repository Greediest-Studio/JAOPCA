/*    */ package thelm.jaopca.compat.thaumcraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thaumcraft.api.items.ItemsTC;
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
/*    */ @JAOPCAModule(modDependencies = {"thaumcraft"})
/*    */ public class ThaumcraftCompatModule
/*    */   implements IModule
/*    */ {
/* 26 */   private static final Set<String> ORE_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Cinnabar", "Copper", "Diamond", "Emerald", "Gold", "Iron", "Lapis", "Lead", "NetherQuartz", "Quartz", "Redstone", "Silver", "Tin" }));
/*    */ 
/*    */   
/* 29 */   private static final Set<String> CLUSTER_BLACKLIST = new TreeSet<>(Arrays.asList(new String[] { "Cinnabar", "Copper", "Gold", "Iron", "Lead", "NetherQuartz", "Quartz", "Silver", "Tin" }));
/*    */   
/* 31 */   private static Set<String> configOreBlacklist = new TreeSet<>();
/* 32 */   private static Set<String> configClusterBlacklist = new TreeSet<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 36 */     return "thaumcraft_compat";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 41 */     return EnumSet.allOf(MaterialType.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 46 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 47 */     miscHelper.caclulateMaterialSet(config
/* 48 */         .getDefinedStringList("recipes.oreMaterialBlacklist", new ArrayList(), miscHelper
/* 49 */           .configMaterialPredicate(), "The materials that should not have smelting bonus ore to rare earth recipes added."), configOreBlacklist);
/*    */     
/* 51 */     miscHelper.caclulateMaterialSet(config
/* 52 */         .getDefinedStringList("recipes.clusterMaterialBlacklist", new ArrayList(), miscHelper
/* 53 */           .configMaterialPredicate(), "The materials that should not have smelting bonus cluster to rare earth recipes added."), configClusterBlacklist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 59 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 60 */     ThaumcraftHelper helper = ThaumcraftHelper.INSTANCE;
/* 61 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 62 */     Set<String> oredict = apiImpl.getOredict();
/* 63 */     ItemStack rareEarths = new ItemStack(ItemsTC.nuggets, 1, 10);
/* 64 */     for (IMaterial material : moduleData.getMaterials()) {
/* 65 */       MaterialType type = material.getType();
/* 66 */       String name = material.getName();
/* 67 */       if (type.isOre() && !ORE_BLACKLIST.contains(name) && !configOreBlacklist.contains(name)) {
/* 68 */         String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 69 */         helper.registerSmeltingBonusRecipe(miscHelper
/* 70 */             .getRecipeKey("thaumcraft.ore_to_rare_earths", material.getName()), oreOredict, rareEarths, 1, 0.01F);
/*    */       } 
/*    */       
/* 73 */       if (type == MaterialType.INGOT && !CLUSTER_BLACKLIST.contains(name) && !configClusterBlacklist.contains(name)) {
/* 74 */         String clusterOredict = miscHelper.getOredictName("cluster", material.getName());
/* 75 */         if (oredict.contains(clusterOredict))
/* 76 */           helper.registerSmeltingBonusRecipe(miscHelper
/* 77 */               .getRecipeKey("thaumcraft.cluster_to_rare_earths", material.getName()), clusterOredict, rareEarths, 1, 0.02F); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\thaumcraft\ThaumcraftCompatModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */