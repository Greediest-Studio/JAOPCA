/*    */ package thelm.jaopca.forms;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ 
/*    */ 
/*    */ public class FormRequest
/*    */   implements IFormRequest
/*    */ {
/*    */   private final IModule module;
/*    */   private final List<IForm> forms;
/*    */   private boolean grouped = false;
/* 24 */   private final TreeSet<IMaterial> materials = new TreeSet<>();
/*    */   
/*    */   public FormRequest(IModule module, IForm... forms) {
/* 27 */     this.module = Objects.<IModule>requireNonNull(module);
/* 28 */     this
/*    */ 
/*    */       
/* 31 */       .forms = (List<IForm>)Arrays.<Object>stream((Object[])Objects.requireNonNull(forms)).filter(Objects::nonNull).filter(form -> (form.getModule() == module)).map(IForm::lock).collect(ImmutableList.toImmutableList());
/* 32 */     for (IForm form : this.forms) {
/* 33 */       form.setRequest(this);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public IModule getModule() {
/* 39 */     return this.module;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IForm> getForms() {
/* 44 */     return this.forms;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGrouped() {
/* 49 */     return this.grouped;
/*    */   }
/*    */ 
/*    */   
/*    */   public IFormRequest setGrouped(boolean grouped) {
/* 54 */     this.grouped = grouped;
/* 55 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<IMaterial> getMaterials() {
/* 60 */     return Collections.unmodifiableNavigableSet(this.materials);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMaterialGroupValid(IMaterial material) {
/* 65 */     return (!ModuleHandler.getModuleData(this.module).getRejectedMaterials().contains(material) && this.forms
/* 66 */       .stream().filter(form -> !form.skipGroupedCheck()).anyMatch(form -> form.isMaterialValid(material)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaterials(Collection<IMaterial> materials) {
/* 71 */     this.materials.clear();
/* 72 */     this.materials.addAll(materials);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\forms\FormRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */