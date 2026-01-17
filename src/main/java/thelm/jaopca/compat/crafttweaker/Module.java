/*    */ package thelm.jaopca.compat.crafttweaker;
/*    */ 
/*    */ import crafttweaker.annotations.ZenRegister;
/*    */ import java.util.TreeMap;
/*    */ import java.util.function.Function;
/*    */ import stanhebben.zenscript.annotations.ZenClass;
/*    */ import stanhebben.zenscript.annotations.ZenGetter;
/*    */ import stanhebben.zenscript.annotations.ZenMethod;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.forms.FormHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ 
/*    */ @ZenRegister
/*    */ @ZenClass("mods.jaopca.Module")
/*    */ public class Module {
/* 19 */   private static final TreeMap<IModule, Module> MODULE_WRAPPERS = new TreeMap<>();
/*    */   private final IModule module;
/*    */   private final IModuleData moduleData;
/*    */   
/*    */   public static Module getModuleWrapper(IModule module) {
/* 24 */     return MODULE_WRAPPERS.computeIfAbsent(module, Module::new);
/*    */   }
/*    */   
/*    */   private Module(IModule module) {
/* 28 */     this.module = module;
/* 29 */     this.moduleData = (IModuleData)ModuleHandler.getModuleData(module);
/*    */   }
/*    */   
/*    */   public IModule getInternal() {
/* 33 */     return this.module;
/*    */   }
/*    */   
/*    */   @ZenGetter("name")
/*    */   public String getName() {
/* 38 */     return this.module.getName();
/*    */   }
/*    */   
/*    */   @ZenGetter("materialTypes")
/*    */   public String[] getMaterialTypes() {
/* 43 */     return (String[])this.module.getMaterialTypes().stream().map(MaterialType::getName).toArray(x$0 -> new String[x$0]);
/*    */   }
/*    */   
/*    */   @ZenGetter("materials")
/*    */   public Material[] getMaterials() {
/* 48 */     return (Material[])this.moduleData.getMaterials().stream().map(Material::getMaterialWrapper).toArray(x$0 -> new Material[x$0]);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public boolean containsMaterial(Material material) {
/* 53 */     return this.moduleData.getMaterials().contains(material.getInternal());
/*    */   }
/*    */   
/*    */   @ZenGetter("forms")
/*    */   public Form[] getForms() {
/* 58 */     return (Form[])FormHandler.getForms().stream().filter(f -> (f.getModule() == this.module)).map(Form::getFormWrapper).toArray(x$0 -> new Form[x$0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 63 */     if (obj instanceof Module) {
/* 64 */       return (this.module == ((Module)obj).module);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 71 */     return this.module.hashCode() + 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */