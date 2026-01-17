/*    */ package thelm.jaopca.forms;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import java.util.stream.Collectors;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FormHandler
/*    */ {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/* 25 */   private static final TreeMap<String, IForm> FORMS = new TreeMap<>();
/* 26 */   private static final List<IFormRequest> FORM_REQUESTS = new ArrayList<>();
/*    */   
/*    */   public static Map<String, IForm> getFormMap() {
/* 29 */     return FORMS;
/*    */   }
/*    */   
/*    */   public static Collection<IForm> getForms() {
/* 33 */     return FORMS.values();
/*    */   }
/*    */   
/*    */   public static IForm getForm(String name) {
/* 37 */     return FORMS.get(name);
/*    */   }
/*    */   
/*    */   public static boolean containsForm(String name) {
/* 41 */     return FORMS.containsKey(name);
/*    */   }
/*    */   
/*    */   public static void collectForms() {
/* 45 */     for (null = ModuleHandler.getModules().iterator(); null.hasNext(); ) { IModule module = null.next();
/* 46 */       List<IFormRequest> list = module.getFormRequests();
/* 47 */       if (list != null && !list.isEmpty()) {
/* 48 */         list.stream().filter(request -> (request.getModule() == module)).forEach(FORM_REQUESTS::add);
/*    */       } }
/*    */     
/* 51 */     for (IFormRequest request : FORM_REQUESTS) {
/* 52 */       for (IForm form : request.getForms()) {
/* 53 */         if (FORMS.putIfAbsent(form.getName(), form) != null) {
/* 54 */           throw new IllegalStateException(String.format("Form name conflict: %s for modules %s and %s", new Object[] { form
/* 55 */                   .getName(), ((IForm)FORMS.get(form.getName())).getModule().getName(), form.getModule().getName() }));
/*    */         }
/* 57 */         form.getType().addForm(form);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void computeValidMaterials() {
/* 63 */     for (IFormRequest request : FORM_REQUESTS) {
/* 64 */       if (request.isGrouped()) {
/* 65 */         List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(request::isMaterialGroupValid).collect(Collectors.toList());
/* 66 */         for (IForm form : request.getForms()) {
/* 67 */           form.setMaterials(materials);
/*    */         }
/* 69 */         request.setMaterials(materials);
/*    */         continue;
/*    */       } 
/* 72 */       for (IForm form : request.getForms()) {
/* 73 */         List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(form::isMaterialValid).collect(Collectors.toList());
/* 74 */         form.setMaterials(materials);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\forms\FormHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */