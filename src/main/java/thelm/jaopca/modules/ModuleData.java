/*     */ package thelm.jaopca.modules;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSortedSet;
/*     */ import com.google.common.collect.Ordering;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanRBTreeMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleData
/*     */   implements IModuleData
/*     */ {
/*     */   private final IModule module;
/*  28 */   private IDynamicSpecConfig config = null;
/*  29 */   private final TreeSet<String> configMaterialBlacklist = new TreeSet<>();
/*  30 */   private final TreeSet<String> configPassiveMaterialWhitelist = new TreeSet<>();
/*  31 */   private final Object2BooleanRBTreeMap<IMaterial> dependencyValidMaterials = new Object2BooleanRBTreeMap();
/*  32 */   private final TreeSet<IMaterial> rejectedMaterials = new TreeSet<>();
/*  33 */   private final TreeSet<IMaterial> requestedMaterials = new TreeSet<>();
/*  34 */   private final TreeSet<IMaterial> materials = new TreeSet<>();
/*     */   
/*     */   public ModuleData(IModule module) {
/*  37 */     this.module = module;
/*     */   }
/*     */ 
/*     */   
/*     */   public IModule getModule() {
/*  42 */     return this.module;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getConfigMaterialBlacklist() {
/*  47 */     return Collections.unmodifiableNavigableSet(this.configMaterialBlacklist);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getConfigPassiveMaterialWhitelist() {
/*  52 */     if (this.module.isPassive()) {
/*  53 */       return Collections.unmodifiableNavigableSet(this.configPassiveMaterialWhitelist);
/*     */     }
/*  55 */     return (Set<String>)MaterialHandler.getMaterials().stream().map(IMaterial::getName).collect(ImmutableSortedSet.toImmutableSortedSet((Comparator)Ordering.natural()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IMaterial> getMaterials() {
/*  60 */     return Collections.unmodifiableNavigableSet(this.materials);
/*     */   }
/*     */   
/*     */   public void setConfig(IDynamicSpecConfig config) {
/*  64 */     this.config = config;
/*  65 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  66 */     miscHelper.caclulateMaterialSet(config
/*  67 */         .getDefinedStringList("general.materialBlacklist", new ArrayList(), miscHelper
/*  68 */           .configMaterialPredicate(), "The material blacklist of this module. \"*\" is an alias for all materials, and \"*\" followed with a material type name is an alias for all materials of that material type. If a material name occurs an odd number of times (including wildcards), then the material is blacklisted. These rules applies to all config material blacklists."), this.configMaterialBlacklist);
/*     */     
/*  70 */     if (this.module.isPassive()) {
/*  71 */       miscHelper.caclulateMaterialSet(config
/*  72 */           .getDefinedStringList("general.passiveMaterialWhitelist", new ArrayList(), miscHelper
/*  73 */             .configMaterialPredicate(), "The materials to force generate passive forms for this module. \"*\" is an alias for all materials, and \"*\" followed with a material type name is an alias for all materials of that material type. If a material name occurs an odd number of times (including wildcards), then the material is whitelisted. These rules apply to all config material whitelists."), this.configPassiveMaterialWhitelist);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMaterialConfigValid(IMaterial material) {
/*  79 */     return (!material.getConfigModuleBlacklist().contains(this.module.getName()) && 
/*  80 */       !this.configMaterialBlacklist.contains(material.getName()));
/*     */   }
/*     */   
/*     */   public boolean isMaterialDependencyValid(IMaterial material, Set<Pair<ModuleData, IMaterial>> prev) {
/*  84 */     return ((Boolean)this.dependencyValidMaterials.computeIfAbsent(material, mat -> { if (prev.contains(Pair.of(this, mat))) return Boolean.valueOf(true);  if (!isMaterialConfigValid(mat)) return Boolean.valueOf(false);  prev.add(Pair.of(this, mat)); for (Map.Entry<Integer, Collection<String>> entry : (Iterable<Map.Entry<Integer, Collection<String>>>)this.module.getModuleDependencies().asMap().entrySet()) { IMaterial extraMaterial = mat.getExtra(((Integer)entry.getKey()).intValue()); for (String requestedModule : entry.getValue()) { ModuleData requestedData = ModuleHandler.getModuleData(requestedModule); if (requestedData == null || !requestedData.isMaterialDependencyValid(extraMaterial, (Set)prev)) return Boolean.valueOf(false);  }  }  return Boolean.valueOf(true); })).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMaterialModuleValid(IMaterial material) {
/* 106 */     return (isMaterialDependencyValid(material, new HashSet<>()) && this.module
/* 107 */       .getMaterialTypes().contains(material.getType()) && 
/* 108 */       !this.module.getDefaultMaterialBlacklist().contains(material.getName()));
/*     */   }
/*     */   
/*     */   public boolean isMaterialValid(IMaterial material) {
/* 112 */     return (isMaterialModuleValid(material) && 
/* 113 */       !this.rejectedMaterials.contains(material) && (
/* 114 */       !this.module.isPassive() || this.configPassiveMaterialWhitelist
/* 115 */       .contains(material.getName()) || this.requestedMaterials
/* 116 */       .contains(material)));
/*     */   }
/*     */   
/*     */   public void addRejectedMaterial(IMaterial material) {
/* 120 */     this.rejectedMaterials.add(material);
/*     */   }
/*     */   
/*     */   public void addDependencyRequestedMaterial(IMaterial material) {
/* 124 */     for (Map.Entry<Integer, Collection<String>> entry : (Iterable<Map.Entry<Integer, Collection<String>>>)this.module.getModuleDependencies().asMap().entrySet()) {
/* 125 */       IMaterial extraMaterial = material.getExtra(((Integer)entry.getKey()).intValue());
/* 126 */       for (String requestedModule : entry.getValue()) {
/* 127 */         ModuleData requestedData = ModuleHandler.getModuleData(requestedModule);
/* 128 */         if (requestedData == null || !requestedData.isMaterialDependencyValid(extraMaterial, new HashSet<>())) {
/* 129 */           throw new IllegalStateException("Module " + this.module.getName() + " has wrongly accepted material " + material.getName());
/*     */         }
/* 131 */         requestedData.addRequestedMaterial(extraMaterial);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addRequestedMaterial(IMaterial material) {
/* 137 */     if (getModule().isPassive()) {
/* 138 */       this.requestedMaterials.add(material);
/* 139 */       addDependencyRequestedMaterial(material);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<IMaterial> getRejectedMaterials() {
/* 144 */     return this.rejectedMaterials;
/*     */   }
/*     */   
/*     */   public Set<IMaterial> getRequestedMaterials() {
/* 148 */     return this.requestedMaterials;
/*     */   }
/*     */   
/*     */   public void setMaterials(Collection<IMaterial> materials) {
/* 152 */     this.materials.clear();
/* 153 */     this.materials.addAll(materials);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\ModuleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */