/*    */ package thelm.jaopca.compat.groovyscript;
/*    */ 
/*    */ import com.cleanroommc.groovyscript.api.GroovyPlugin;
/*    */ import com.cleanroommc.groovyscript.api.IObjectParser;
/*    */ import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
/*    */ import com.cleanroommc.groovyscript.sandbox.expand.ExpansionHelper;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.forms.FormHandler;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ 
/*    */ 
/*    */ public class JAOPCAGroovyPlugin
/*    */   implements GroovyPlugin
/*    */ {
/*    */   public String getModId() {
/* 21 */     return "jaopca";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getContainerName() {
/* 26 */     return "jaopca";
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCompatLoaded(GroovyContainer<?> container) {
/* 31 */     container.objectMapperBuilder("module", IModuleData.class).mod("jaopca")
/* 32 */       .addSignature(new Class[] { String.class
/* 33 */         }).parser(IObjectParser.wrapStringGetter(ModuleHandler::getModuleData))
/* 34 */       .completerOfNamed(ModuleHandler::getModules, IModule::getName);
/*    */     
/* 36 */     container.objectMapperBuilder("form", IForm.class).mod("jaopca")
/* 37 */       .addSignature(new Class[] { String.class
/* 38 */         }).parser(IObjectParser.wrapStringGetter(FormHandler::getForm))
/* 39 */       .completerOfNamed(FormHandler::getForms, IForm::getName);
/*    */     
/* 41 */     container.objectMapperBuilder("material", IMaterial.class).mod("jaopca")
/* 42 */       .addSignature(new Class[] { String.class
/* 43 */         }).parser(IObjectParser.wrapStringGetter(MaterialHandler::getMaterial))
/* 44 */       .completerOfNamed(MaterialHandler::getMaterials, IMaterial::getName);
/*    */     
/* 46 */     ExpansionHelper.mixinClass(IModuleData.class, ModuleDataExpansion.class);
/* 47 */     ExpansionHelper.mixinClass(IForm.class, FormExpansion.class);
/* 48 */     ExpansionHelper.mixinClass(IMaterial.class, MaterialExpansion.class);
/* 49 */     ExpansionHelper.mixinClass(IMaterialFormInfo.class, MaterialFormInfoExpansion.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\groovyscript\JAOPCAGroovyPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */