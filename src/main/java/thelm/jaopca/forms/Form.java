/*     */ package thelm.jaopca.forms;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Locale;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormRequest;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.modules.ModuleData;
/*     */ import thelm.jaopca.modules.ModuleHandler;
/*     */ 
/*     */ public class Form
/*     */   implements IForm
/*     */ {
/*     */   private final IModule module;
/*     */   private final String name;
/*     */   private final IFormType type;
/*     */   private String secondaryName;
/*  27 */   private final EnumSet<MaterialType> materialTypes = EnumSet.allOf(MaterialType.class);
/*  28 */   private final TreeSet<String> defaultMaterialBlacklist = new TreeSet<>();
/*  29 */   private final TreeSet<String> materialBlacklist = new TreeSet<>();
/*  30 */   private final TreeSet<String> materialWhitelist = new TreeSet<>();
/*     */   private IFormSettings settings;
/*     */   private boolean skipGroupCheck = false;
/*     */   private boolean locked = false;
/*     */   private IFormRequest request;
/*  35 */   private final TreeSet<IMaterial> materials = new TreeSet<>();
/*     */   
/*     */   public Form(IModule module, String name, IFormType type) {
/*  38 */     this.module = Objects.<IModule>requireNonNull(module);
/*  39 */     this.name = ((String)Objects.<String>requireNonNull(name)).toLowerCase(Locale.US);
/*  40 */     this.type = Objects.<IFormType>requireNonNull(type);
/*  41 */     this.secondaryName = name;
/*  42 */     this.settings = type.getNewSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  47 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFormType getType() {
/*  52 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public IModule getModule() {
/*  57 */     return this.module;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setSecondaryName(String secondaryName) {
/*  62 */     if (!this.locked) {
/*  63 */       this.secondaryName = secondaryName;
/*     */     }
/*  65 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSecondaryName() {
/*  70 */     return this.secondaryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setMaterialTypes(Collection<MaterialType> materialTypes) {
/*  75 */     if (!this.locked) {
/*  76 */       this.materialTypes.clear();
/*  77 */       this.materialTypes.addAll(materialTypes);
/*     */     } 
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setMaterialTypes(MaterialType... materialTypes) {
/*  84 */     if (!this.locked) {
/*  85 */       this.materialTypes.clear();
/*  86 */       Collections.addAll(this.materialTypes, materialTypes);
/*     */     } 
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MaterialType> getMaterialTypes() {
/*  93 */     return Collections.unmodifiableSet(this.materialTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setDefaultMaterialBlacklist(Collection<String> defaultMaterialBlacklist) {
/*  98 */     if (!this.locked) {
/*  99 */       this.defaultMaterialBlacklist.clear();
/* 100 */       this.defaultMaterialBlacklist.addAll(defaultMaterialBlacklist);
/*     */     } 
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setDefaultMaterialBlacklist(String... defaultMaterialBlacklist) {
/* 107 */     if (!this.locked) {
/* 108 */       this.defaultMaterialBlacklist.clear();
/* 109 */       Collections.addAll(this.defaultMaterialBlacklist, defaultMaterialBlacklist);
/*     */     } 
/* 111 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getDefaultMaterialBlacklist() {
/* 116 */     return Collections.unmodifiableNavigableSet(this.defaultMaterialBlacklist);
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setSettings(IFormSettings settings) {
/* 121 */     if (!this.locked && settings.getType() == this.type) {
/* 122 */       this.settings = settings;
/*     */     }
/* 124 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFormSettings getSettings() {
/* 129 */     return this.settings;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setSkipGroupedCheck(boolean skipGroupCheck) {
/* 134 */     if (!this.locked) {
/* 135 */       this.skipGroupCheck = skipGroupCheck;
/*     */     }
/* 137 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean skipGroupedCheck() {
/* 142 */     return this.skipGroupCheck;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IMaterial> getMaterials() {
/* 147 */     return Collections.unmodifiableNavigableSet(this.materials);
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm lock() {
/* 152 */     this.locked = true;
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFormRequest toRequest() {
/* 158 */     IFormRequest request = new FormRequest(this.module, new IForm[] { this });
/* 159 */     setRequest(request);
/* 160 */     return request;
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm setRequest(IFormRequest request) {
/* 165 */     if (request.getForms().contains(this)) {
/* 166 */       this.request = request;
/*     */     }
/* 168 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMaterialValid(IMaterial material) {
/* 173 */     ModuleData data = ModuleHandler.getModuleData(this.module);
/* 174 */     return (this.materialTypes.contains(material.getType()) && 
/* 175 */       !this.defaultMaterialBlacklist.contains(material.getName()) && 
/* 176 */       !material.getConfigModuleBlacklist().contains(this.module.getName()) && 
/* 177 */       !data.getConfigMaterialBlacklist().contains(material.getName()) && this.type
/* 178 */       .shouldRegister(this, material) && 
/* 179 */       !data.getRejectedMaterials().contains(material) && (
/* 180 */       !this.module.isPassive() || data
/* 181 */       .getConfigPassiveMaterialWhitelist().contains(material.getName()) || data
/* 182 */       .getRequestedMaterials().contains(material)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterials(Collection<IMaterial> materials) {
/* 187 */     this.materials.clear();
/* 188 */     this.materials.addAll(materials);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 193 */     return this.name.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 198 */     return "Form:" + this.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\forms\Form.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */