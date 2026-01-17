//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.oredict;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.base.Splitter;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.common.discovery.ASMDataTable;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.oredict.IOredictModule;
/*     */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OredictHandler
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*  31 */   private static final String JAOPCA_OREDICT_MODULE = JAOPCAOredictModule.class.getCanonicalName();
/*  32 */   private static final TreeMap<String, IOredictModule> OREDICT_MODULES = new TreeMap<>();
/*  33 */   private static final TreeSet<String> OREDICT_NAMES = new TreeSet<>();
/*     */   private static boolean initialized = false;
/*     */   
/*     */   public static void initialize() {
/*  37 */     initialized = true;
/*  38 */     Arrays.<String>stream(OreDictionary.getOreNames())
/*  39 */       .filter(name -> !OreDictionary.getOres(name, false).isEmpty())
/*  40 */       .forEach(OREDICT_NAMES::add);
/*     */   }
/*     */   
/*     */   public static void onOreRegister(OreDictionary.OreRegisterEvent event) {
/*  44 */     if (!initialized) {
/*  45 */       initialize();
/*     */     }
/*  47 */     OREDICT_NAMES.add(event.getName());
/*     */   }
/*     */   
/*     */   public static Set<String> getOredict() {
/*  51 */     if (!initialized) {
/*  52 */       initialize();
/*     */     }
/*  54 */     return OREDICT_NAMES;
/*     */   }
/*     */   
/*     */   public static Collection<IOredictModule> getOredictModules() {
/*  58 */     return OREDICT_MODULES.values();
/*     */   }
/*     */   
/*     */   public static void findOredictModules(ASMDataTable asmDataTable) {
/*  62 */     OREDICT_MODULES.clear();
/*  63 */     Set<ASMDataTable.ASMData> annotationData = asmDataTable.getAll(JAOPCA_OREDICT_MODULE);
/*  64 */     Predicate<String> modVersionNotLoaded = MiscHelper.INSTANCE.modVersionNotLoaded(LOGGER);
/*  65 */     Predicate<String> classNotExists = MiscHelper.INSTANCE::classNotExists;
/*  66 */     for (ASMDataTable.ASMData aData : annotationData) {
/*  67 */       List<String> modDeps = (List<String>)aData.getAnnotationInfo().get("modDependencies");
/*  68 */       List<String> classDeps = (List<String>)aData.getAnnotationInfo().get("classDependencies");
/*  69 */       String className = aData.getClassName();
/*  70 */       if (modDeps != null && modDeps.stream().filter((Predicate<? super String>)Predicates.notNull()).anyMatch(modVersionNotLoaded)) {
/*  71 */         LOGGER.info("Oredict module {} has missing mod dependencies, skipping", className);
/*     */         continue;
/*     */       } 
/*  74 */       if (classDeps != null && classDeps.stream().filter((Predicate<? super String>)Predicates.notNull()).anyMatch(classNotExists)) {
/*  75 */         LOGGER.info("Oredict module {} has missing class dependencies, skipping", className); continue;
/*     */       } 
/*     */       try {
/*     */         IOredictModule module;
/*  79 */         Class<?> moduleClass = Class.forName(className);
/*  80 */         Class<? extends IOredictModule> moduleInstanceClass = moduleClass.asSubclass(IOredictModule.class);
/*     */         
/*     */         try {
/*  83 */           Method method = moduleClass.getMethod("getInstance", new Class[0]);
/*  84 */           module = (IOredictModule)method.invoke(null, new Object[0]);
/*     */         }
/*  86 */         catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException e) {
/*  87 */           module = moduleInstanceClass.newInstance();
/*     */         } 
/*  89 */         if (ConfigHandler.OREDICT_MODULE_BLACKLIST.contains(module.getName())) {
/*  90 */           LOGGER.info("Oredict module {} is disabled in config, skipping", module.getName());
/*     */         }
/*  92 */         if (OREDICT_MODULES.putIfAbsent(module.getName(), module) != null) {
/*  93 */           LOGGER.fatal("Oredict module name conflict: {} for {} and {}", module.getName(), ((IOredictModule)OREDICT_MODULES.get(module.getName())).getClass(), module.getClass());
/*     */           continue;
/*     */         } 
/*  96 */         LOGGER.debug("Loaded oredict module {}", module.getName());
/*     */       }
/*  98 */       catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
/*  99 */         LOGGER.fatal("Unable to load oredict module {}", className, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void register() {
/* 105 */     for (IOredictModule module : getOredictModules()) {
/* 106 */       module.register();
/*     */     }
/* 108 */     Splitter lineSplitter = Splitter.on(',').omitEmptyStrings().trimResults();
/* 109 */     Splitter oredictSplitter = Splitter.on('=').limit(2).trimResults();
/* 110 */     for (String line : ConfigHandler.CUSTOM_OREDICT) {
/* 111 */       for (String entry : lineSplitter.split(line)) {
/* 112 */         List<String> split = oredictSplitter.splitToList(entry);
/* 113 */         if (split.size() != 2) {
/* 114 */           LOGGER.warn("Custom oredict entry [{}] has no specified name", entry);
/*     */           continue;
/*     */         } 
/* 117 */         ItemStack stack = MiscHelper.INSTANCE.parseMetaItem(split.get(0));
/* 118 */         if (stack.isEmpty()) {
/* 119 */           LOGGER.warn("Custom oredict entry [{}] has empty item", entry);
/*     */           
/*     */           continue;
/*     */         } 
/* 123 */         OreDictionary.registerOre(split.get(1), stack);
/* 124 */         LOGGER.info("Registered custom oredict entry [{}]", entry);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\oredict\OredictHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
