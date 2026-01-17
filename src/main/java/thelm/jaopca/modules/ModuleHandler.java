/*     */ package thelm.jaopca.modules;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraftforge.fml.common.discovery.ASMDataTable;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.JAOPCAModule;
/*     */ import thelm.jaopca.materials.Material;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleHandler
/*     */ {
/*  33 */   private static final Logger LOGGER = LogManager.getLogger();
/*  34 */   private static final String JAOPCA_MODULE = JAOPCAModule.class.getCanonicalName();
/*  35 */   private static final TreeMap<String, IModule> MODULES = new TreeMap<>();
/*  36 */   private static final TreeMap<IModule, ModuleData> MODULE_DATAS = new TreeMap<>();
/*     */   
/*     */   public static Map<String, IModule> getModuleMap() {
/*  39 */     return MODULES;
/*     */   }
/*     */   
/*     */   public static Collection<IModule> getModules() {
/*  43 */     return MODULES.values();
/*     */   }
/*     */   
/*     */   public static Map<IModule, ModuleData> getModuleDataMap() {
/*  47 */     return MODULE_DATAS;
/*     */   }
/*     */   
/*     */   public static ModuleData getModuleData(String name) {
/*  51 */     IModule module = MODULES.get(name);
/*  52 */     return (module != null) ? MODULE_DATAS.get(module) : null;
/*     */   }
/*     */   
/*     */   public static ModuleData getModuleData(IModule module) {
/*  56 */     return MODULE_DATAS.get(module);
/*     */   }
/*     */   
/*     */   public static Collection<ModuleData> getModuleDatas() {
/*  60 */     return MODULE_DATAS.values();
/*     */   }
/*     */   
/*     */   public static void findModules(ASMDataTable asmDataTable) {
/*  64 */     MODULES.clear();
/*  65 */     Set<ASMDataTable.ASMData> annotationData = asmDataTable.getAll(JAOPCA_MODULE);
/*  66 */     Predicate<String> modVersionNotLoaded = MiscHelper.INSTANCE.modVersionNotLoaded(LOGGER);
/*  67 */     Predicate<String> classNotExists = MiscHelper.INSTANCE::classNotExists;
/*  68 */     for (ASMDataTable.ASMData aData : annotationData) {
/*  69 */       List<String> modDeps = (List<String>)aData.getAnnotationInfo().get("modDependencies");
/*  70 */       List<String> classDeps = (List<String>)aData.getAnnotationInfo().get("classDependencies");
/*  71 */       String className = aData.getClassName();
/*  72 */       if (modDeps != null && modDeps.stream().filter((Predicate<? super String>)Predicates.notNull()).anyMatch(modVersionNotLoaded)) {
/*  73 */         LOGGER.info("Module {} has missing mod dependencies, skipping", className);
/*     */         continue;
/*     */       } 
/*  76 */       if (classDeps != null && classDeps.stream().filter((Predicate<? super String>)Predicates.notNull()).anyMatch(classNotExists)) {
/*  77 */         LOGGER.info("Module {} has missing class dependencies, skipping", className); continue;
/*     */       } 
/*     */       try {
/*     */         IModule module;
/*  81 */         Class<?> moduleClass = Class.forName(className);
/*  82 */         Class<? extends IModule> moduleInstanceClass = moduleClass.asSubclass(IModule.class);
/*     */         
/*     */         try {
/*  85 */           Method method = moduleClass.getMethod("getInstance", new Class[0]);
/*  86 */           module = (IModule)method.invoke(null, new Object[0]);
/*     */         }
/*  88 */         catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException e) {
/*  89 */           module = moduleInstanceClass.newInstance();
/*     */         } 
/*  91 */         if (MODULES.putIfAbsent(module.getName(), module) != null) {
/*  92 */           LOGGER.fatal("Module name conflict: {} for {} and {}", module.getName(), ((IModule)MODULES.get(module.getName())).getClass(), module.getClass());
/*     */           continue;
/*     */         } 
/*  95 */         ModuleData mData = new ModuleData(module);
/*  96 */         MODULE_DATAS.put(module, mData);
/*  97 */         LOGGER.debug("Loaded module {}", module.getName());
/*     */       }
/*  99 */       catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
/* 100 */         LOGGER.fatal("Unable to load module {}", className, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void computeValidMaterials() {
/* 106 */     for (null = getModuleDatas().iterator(); null.hasNext(); ) { ModuleData data = null.next();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(data.getModule().isPassive() ? (material -> data.getConfigPassiveMaterialWhitelist().contains(material.getName())) : data::isMaterialModuleValid).collect(Collectors.toList());
/* 112 */       for (IMaterial material : materials) {
/* 113 */         if (data.isMaterialDependencyValid(material, new HashSet<>())) {
/* 114 */           data.addDependencyRequestedMaterial(material);
/*     */           continue;
/*     */         } 
/* 117 */         data.addRejectedMaterial(material);
/*     */       }  }
/*     */ 
/*     */     
/* 121 */     for (ModuleData data : getModuleDatas()) {
/* 122 */       List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(data::isMaterialValid).collect(Collectors.toList());
/* 123 */       data.setMaterials(materials);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void onMaterialComputeComplete() {
/* 128 */     for (IModule module : getModules()) {
/* 129 */       module.onMaterialComputeComplete(getModuleData(module));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void onInit(FMLInitializationEvent event) {
/* 134 */     for (IModule module : getModules()) {
/* 135 */       module.onInit(getModuleData(module), event);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void onPostInit(FMLPostInitializationEvent event) {
/* 140 */     for (IModule module : getModules())
/* 141 */       module.onPostInit(getModuleData(module), event); 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\ModuleHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */