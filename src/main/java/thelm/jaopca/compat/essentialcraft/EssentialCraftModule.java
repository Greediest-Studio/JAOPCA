/*    */ package thelm.jaopca.compat.essentialcraft;
/*    */ 
/*    */ import essentialcraft.api.OreSmeltingRecipe;
/*    */ import java.util.Arrays;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialColorEvent;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ @JAOPCAModule(modDependencies = {"essentialcraft"})
/*    */ public class EssentialCraftModule
/*    */   implements IModule
/*    */ {
/* 26 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */   
/* 28 */   private Map<IMaterial, OreSmeltingRecipe> addedRecipes = new TreeMap<>();
/*    */   private boolean colorsLoaded;
/*    */   
/*    */   public EssentialCraftModule() {
/* 32 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return "essentialcraft";
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MaterialType> getMaterialTypes() {
/* 42 */     return EnumSet.copyOf(Arrays.asList(MaterialType.ORE));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> getDefaultMaterialBlacklist() {
/* 47 */     if (BLACKLIST.isEmpty()) {
/* 48 */       OreSmeltingRecipe.RECIPES.stream().map(r -> r.oreName)
/* 49 */         .filter(n -> n.startsWith("ore")).map(n -> n.substring(3)).forEach(BLACKLIST::add);
/*    */     }
/* 51 */     return BLACKLIST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 56 */     EssentialCraftHelper helper = EssentialCraftHelper.INSTANCE;
/* 57 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 58 */     for (IMaterial material : moduleData.getMaterials()) {
/* 59 */       String oreOredict = miscHelper.getOredictName("ore", material.getName());
/* 60 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 61 */       helper.registerMagmaticSmelterRecipe(miscHelper
/* 62 */           .getRecipeKey("essentialcraft.magmatic_smelter", material.getName()), oreOredict, materialOredict, 
/* 63 */           material.getType().isDust() ? 2 : 1, r -> (OreSmeltingRecipe)this.addedRecipes.put(material, r));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onMaterialColor(MaterialColorEvent event) {
/* 70 */     if (this.addedRecipes.containsKey(event.getMaterial()))
/* 71 */       ((OreSmeltingRecipe)this.addedRecipes.get(event.getMaterial())).color = event.getColor(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\essentialcraft\EssentialCraftModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */