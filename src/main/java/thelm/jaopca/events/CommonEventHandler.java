/*    */ package thelm.jaopca.events;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ import net.minecraftforge.event.RegistryEvent;
/*    */ import net.minecraftforge.fml.common.discovery.ASMDataTable;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thelm.jaopca.blocks.BlockFormType;
/*    */ import thelm.jaopca.config.ConfigHandler;
/*    */ import thelm.jaopca.fluids.FluidFormType;
/*    */ import thelm.jaopca.forms.FormHandler;
/*    */ import thelm.jaopca.forms.FormTypeHandler;
/*    */ import thelm.jaopca.items.ItemFormType;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ import thelm.jaopca.oredict.OredictHandler;
/*    */ import thelm.jaopca.recipes.RecipeHandler;
/*    */ import thelm.jaopca.registries.RegistryHandler;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.wrapup.event.InitializationWrapUpEvent;
/*    */ import thelm.wrapup.event.PostInitializationWrapUpEvent;
/*    */ import thelm.wrapup.event.PreInitializationWrapUpEvent;
/*    */ import thelm.wrapup.event.RegistryWrapUpEvent;
/*    */ 
/*    */ 
/*    */ public class CommonEventHandler
/*    */ {
/*    */   protected ASMDataTable asmDataTable;
/*    */   protected Path modConfigDir;
/*    */   
/*    */   public void onPreInit(FMLPreInitializationEvent event) {
/* 34 */     this.asmDataTable = event.getAsmData();
/* 35 */     this.modConfigDir = event.getModConfigurationDirectory().toPath();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPreInitWrapUp2(PreInitializationWrapUpEvent.Event2 event) {
/* 40 */     ApiImpl.INSTANCE.init();
/* 41 */     BlockFormType.init();
/* 42 */     ItemFormType.init();
/* 43 */     FluidFormType.init();
/* 44 */     ModuleHandler.findModules(this.asmDataTable);
/* 45 */     ConfigHandler.setupMainConfig(this.modConfigDir);
/* 46 */     OredictHandler.findOredictModules(this.asmDataTable);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRegistryWrapUp2(RegistryWrapUpEvent.Event2 event) {
/* 51 */     OredictHandler.register();
/* 52 */     MaterialHandler.findMaterials();
/* 53 */     ConfigHandler.setupMaterialConfigs();
/* 54 */     FormTypeHandler.setupGson();
/* 55 */     ConfigHandler.setupCustomFormConfig();
/* 56 */     ConfigHandler.setupModuleConfigsPre();
/* 57 */     FormHandler.collectForms();
/* 58 */     ModuleHandler.computeValidMaterials();
/* 59 */     FormHandler.computeValidMaterials();
/* 60 */     ConfigHandler.setupModuleConfigs();
/* 61 */     FormTypeHandler.registerMaterialForms();
/* 62 */     ModuleHandler.onMaterialComputeComplete();
/* 63 */     RecipeHandler.registerEarlyRecipes();
/*    */   }
/*    */   
/*    */   public void onInit(FMLInitializationEvent event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onInitWrapUp2(InitializationWrapUpEvent.Event2 event) {
/* 70 */     ModuleHandler.onInit(event.event);
/* 71 */     RecipeHandler.registerRecipes();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPostInitWrapUp2(PostInitializationWrapUpEvent.Event2 event) {
/* 76 */     ModuleHandler.onPostInit(event.event);
/* 77 */     RecipeHandler.registerLateRecipes();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onOreRegister(OreDictionary.OreRegisterEvent event) {
/* 82 */     OredictHandler.onOreRegister(event);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onMissingMappings(RegistryEvent.MissingMappings event) {
/* 88 */     RegistryHandler.onMissingMappings(event);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\events\CommonEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */