/*     */ package thelm.jaopca.config;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.com.electronwill.nightconfig.core.CommentedConfig;
/*     */ import thelm.com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.api.modules.IModuleData;
/*     */ import thelm.jaopca.custom.CustomModule;
/*     */ import thelm.jaopca.materials.Material;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.modules.ModuleData;
/*     */ import thelm.jaopca.modules.ModuleHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ public class ConfigHandler
/*     */ {
/*  35 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private static Path configDir;
/*     */   private static Path customFormConfigFile;
/*     */   private static Path materialConfigDir;
/*     */   private static Path moduleConfigDir;
/*     */   private static IDynamicSpecConfig mainConfig;
/*  41 */   private static final TreeMap<IMaterial, IDynamicSpecConfig> MATERIAL_CONFIGS = new TreeMap<>();
/*  42 */   private static final TreeMap<IModule, IDynamicSpecConfig> MODULE_CONFIGS = new TreeMap<>();
/*     */   
/*     */   public static boolean ingot = true;
/*     */   
/*     */   public static boolean gem = true;
/*     */   public static boolean crystal = true;
/*     */   public static boolean dust = true;
/*     */   public static boolean ingotPlain = true;
/*     */   public static boolean gemPlain = true;
/*     */   public static boolean crystalPlain = true;
/*     */   public static boolean dustPlain = true;
/*     */   public static boolean nonPlainUsedPrefix = false;
/*     */   public static boolean strictUsedPrefix = false;
/*  55 */   public static final List<String> DEFAULT_GEM_OVERRIDES = Lists.newArrayList((Object[])new String[] { "Coal", "Diamond", "Emerald", "Lapis", "Prismarine", "Quartz" });
/*  56 */   public static final List<String> DEFAULT_CRYSTAL_OVERRIDES = Lists.newArrayList();
/*  57 */   public static final List<String> DEFAULT_DUST_OVERRIDES = Lists.newArrayList((Object[])new String[] { "Redstone" });
/*  58 */   public static final Set<String> GEM_OVERRIDES = new TreeSet<>();
/*  59 */   public static final Set<String> CRYSTAL_OVERRIDES = new TreeSet<>();
/*  60 */   public static final Set<String> DUST_OVERRIDES = new TreeSet<>();
/*     */   
/*  62 */   private static final List<String> DEFAULT_PREFERRED_MODS = Lists.newArrayList((Object[])new String[] { "minecraft", "jaopca" });
/*  63 */   public static final List<String> PREFERRED_MODS = new ArrayList<>();
/*     */   
/*  65 */   public static final Set<String> OREDICT_BLACKLIST = new TreeSet<>();
/*  66 */   public static final Set<String> OREDICT_MODULE_BLACKLIST = new TreeSet<>();
/*  67 */   public static final List<String> CUSTOM_OREDICT = new ArrayList<>();
/*     */   
/*  69 */   public static final Set<ResourceLocation> RECIPE_BLACKLIST = new TreeSet<>();
/*  70 */   public static final List<Pattern> RECIPE_REGEX_BLACKLIST = new ArrayList<>();
/*     */   
/*  72 */   public static double gammaValue = 2.0D;
/*     */   
/*     */   public static boolean resetColors = false;
/*     */   public static boolean checkL10nUpdates = true;
/*  76 */   public static double updateInterval = 3.0D;
/*     */   
/*     */   public static void setupMainConfig(Path modConfigDir) {
/*  79 */     configDir = modConfigDir.resolve("jaopca");
/*  80 */     if (!Files.exists(configDir, new java.nio.file.LinkOption[0]) || !Files.isDirectory(configDir, new java.nio.file.LinkOption[0])) {
/*     */       try {
/*  82 */         if (Files.exists(configDir, new java.nio.file.LinkOption[0]) && !Files.isDirectory(configDir, new java.nio.file.LinkOption[0])) {
/*  83 */           LOGGER.warn("Config directory {} is a file, deleting", configDir);
/*  84 */           Files.delete(configDir);
/*     */         } 
/*  86 */         Files.createDirectory(configDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/*  88 */       catch (Exception e) {
/*  89 */         throw new RuntimeException("Could not create config directory " + configDir, e);
/*     */       } 
/*     */     }
/*     */     
/*  93 */     mainConfig = new DynamicSpecConfig((CommentedConfig)CommentedFileConfig.builder(configDir.resolve("main.toml")).sync().backingMapCreator(java.util.LinkedHashMap::new).autosave().build());
/*     */     
/*  95 */     mainConfig.setComment("materials", "Configurations related to materials.");
/*  96 */     ingot = mainConfig.getDefinedBoolean("materials.ingot", ingot, "Should the mod find ingot materials with ores.");
/*  97 */     gem = mainConfig.getDefinedBoolean("materials.gem", gem, "Should the mod find gem materials with ores.");
/*  98 */     crystal = mainConfig.getDefinedBoolean("materials.crystal", crystal, "Should the mod find crystal materials with ores.");
/*  99 */     dust = mainConfig.getDefinedBoolean("materials.dust", dust, "Should the mod find dust materials with ores.");
/* 100 */     ingotPlain = mainConfig.getDefinedBoolean("materials.ingotPlain", ingotPlain, "Should the mod find ingot materials without ores.");
/* 101 */     gemPlain = mainConfig.getDefinedBoolean("materials.gemPlain", gemPlain, "Should the mod find gem materials without ores.");
/* 102 */     crystalPlain = mainConfig.getDefinedBoolean("materials.crystalPlain", crystalPlain, "Should the mod find crystal materials without ores.");
/* 103 */     dustPlain = mainConfig.getDefinedBoolean("materials.dustPlain", dustPlain, "Should the mod find dust materials without ores.");
/* 104 */     nonPlainUsedPrefix = mainConfig.getDefinedBoolean("materials.nonPlainUsedPrefix", nonPlainUsedPrefix, "Should the mod not find materials with ores with used prefixes.");
/* 105 */     strictUsedPrefix = mainConfig.getDefinedBoolean("materials.strictUsedPrefix", strictUsedPrefix, "Should the mod not find materials with used prefixes when the material name is not found yet.");
/*     */     
/* 107 */     mainConfig.setComment("materialOverrides", "Configurations related to material overrides.");
/* 108 */     GEM_OVERRIDES.addAll(mainConfig.getDefinedStringList("materialOverrides.gem", DEFAULT_GEM_OVERRIDES, "List of materials that should be gems."));
/* 109 */     CRYSTAL_OVERRIDES.addAll(mainConfig.getDefinedStringList("materialOverrides.crystal", DEFAULT_CRYSTAL_OVERRIDES, "List of materials that should be crystals."));
/* 110 */     DUST_OVERRIDES.addAll(mainConfig.getDefinedStringList("materialOverrides.dust", DEFAULT_DUST_OVERRIDES, "List of materials that should be dusts."));
/*     */     
/* 112 */     mainConfig.setComment("itemSelection", "Configurations related to item selection.");
/* 113 */     PREFERRED_MODS.addAll(mainConfig.getDefinedStringList("itemSelection.preferredMods", DEFAULT_PREFERRED_MODS, "List of mods that are preferred when selecting items in recipes."));
/*     */     
/* 115 */     mainConfig.setComment("oredict", "Configurations related to the ore dictionary.");
/* 116 */     OREDICT_BLACKLIST.addAll(mainConfig.getDefinedStringList("oredict.blacklist", new ArrayList(), "List of oredict names that should not be added."));
/* 117 */     OREDICT_MODULE_BLACKLIST.addAll(mainConfig.getDefinedStringList("oredict.moduleBlacklist", new ArrayList(), "List of oredict modules that should not be registered."));
/* 118 */     CUSTOM_OREDICT.addAll(mainConfig.getDefinedStringList("oredict.custom", new ArrayList(), "List of custom oredict entries to add. Format: <mod:item@meta=oredict>"));
/*     */     
/* 120 */     mainConfig.setComment("recipes", "Configurations related to recipes.");
/* 121 */     RECIPE_BLACKLIST.addAll(Lists.transform(mainConfig.getDefinedStringList("recipes.blacklist", new ArrayList(), "List of recipes that should not be added."), ResourceLocation::new));
/*     */     
/* 123 */     RECIPE_REGEX_BLACKLIST.addAll(Lists.transform(mainConfig.getDefinedStringList("recipes.regexBlacklist", new ArrayList(), "List of recipes by regex that should not be added."), Pattern::compile));
/*     */ 
/*     */     
/* 126 */     mainConfig.setComment("colors", "Configurations related to color generation.");
/* 127 */     gammaValue = mainConfig.getDefinedDouble("colors.gammaValue", gammaValue, "The gamma value used to blend colors.");
/* 128 */     resetColors = mainConfig.getDefinedBoolean("colors.resetColors", false, "Should colors of all materials be reset on next startup.");
/* 129 */     mainConfig.set("colors.resetColors", Boolean.valueOf(false));
/*     */     
/* 131 */     mainConfig.setComment("materialLocalization", "Configurations related to material localization.");
/* 132 */     checkL10nUpdates = mainConfig.getDefinedBoolean("materialLocalization.checkL10nUpdates", checkL10nUpdates, "Should the mod check for material localization updates.");
/* 133 */     updateInterval = mainConfig.getDefinedDouble("materialLocalization.updateInterval", updateInterval, "The update interval of localization files in days.");
/*     */   }
/*     */   
/*     */   public static void setupCustomFormConfig() {
/* 137 */     customFormConfigFile = configDir.resolve("custom_forms.json");
/*     */     try {
/* 139 */       if (!Files.exists(customFormConfigFile, new java.nio.file.LinkOption[0])) {
/* 140 */         Files.createFile(customFormConfigFile, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/*     */     }
/* 143 */     catch (Exception e) {
/* 144 */       throw new RuntimeException("Could not create config file " + customFormConfigFile, e);
/*     */     } 
/* 146 */     CustomModule.instance.setCustomFormConfigFile(customFormConfigFile);
/*     */   }
/*     */   
/*     */   public static void setupMaterialConfigs() {
/* 150 */     materialConfigDir = configDir.resolve("materials");
/* 151 */     if (!Files.exists(materialConfigDir, new java.nio.file.LinkOption[0]) || !Files.isDirectory(materialConfigDir, new java.nio.file.LinkOption[0])) {
/*     */       try {
/* 153 */         if (Files.exists(materialConfigDir, new java.nio.file.LinkOption[0]) && !Files.isDirectory(materialConfigDir, new java.nio.file.LinkOption[0])) {
/* 154 */           LOGGER.warn("Config directory {} is a file, deleting", materialConfigDir);
/* 155 */           Files.delete(materialConfigDir);
/*     */         } 
/* 157 */         Files.createDirectory(materialConfigDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/* 159 */       catch (Exception e) {
/* 160 */         throw new RuntimeException("Could not create config directory " + materialConfigDir, e);
/*     */       } 
/*     */     }
/* 163 */     MATERIAL_CONFIGS.clear();
/* 164 */     for (Material material : MaterialHandler.getMaterials()) {
/* 165 */       IDynamicSpecConfig config = new DynamicSpecConfig((CommentedConfig)CommentedFileConfig.builder(materialConfigDir.resolve(MiscHelper.INSTANCE.toLowercaseUnderscore(material.getName()) + ".toml")).sync().backingMapCreator(java.util.LinkedHashMap::new).autosave().build());
/* 166 */       MATERIAL_CONFIGS.put(material, config);
/* 167 */       material.setConfig(config);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setupModuleConfigsPre() {
/* 172 */     moduleConfigDir = configDir.resolve("modules");
/* 173 */     if (!Files.exists(moduleConfigDir, new java.nio.file.LinkOption[0]) || !Files.isDirectory(moduleConfigDir, new java.nio.file.LinkOption[0])) {
/*     */       try {
/* 175 */         if (Files.exists(moduleConfigDir, new java.nio.file.LinkOption[0]) && !Files.isDirectory(moduleConfigDir, new java.nio.file.LinkOption[0])) {
/* 176 */           LOGGER.warn("Config directory {} is a file, deleting", moduleConfigDir);
/* 177 */           Files.delete(moduleConfigDir);
/*     */         } 
/* 179 */         Files.createDirectory(moduleConfigDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/* 181 */       catch (Exception e) {
/* 182 */         throw new RuntimeException("Could not create config directory " + moduleConfigDir, e);
/*     */       } 
/*     */     }
/* 185 */     for (IModule module : ModuleHandler.getModules()) {
/* 186 */       IDynamicSpecConfig config = new DynamicSpecConfig((CommentedConfig)CommentedFileConfig.builder(moduleConfigDir.resolve(module.getName() + ".toml")).sync().backingMapCreator(java.util.LinkedHashMap::new).autosave().build());
/* 187 */       MODULE_CONFIGS.put(module, config);
/* 188 */       ModuleData data = ModuleHandler.getModuleData(module);
/* 189 */       data.setConfig(config);
/* 190 */       module.defineModuleConfigPre((IModuleData)data, config);
/* 191 */       module.defineMaterialConfigPre((IModuleData)data, Collections.unmodifiableNavigableMap(MATERIAL_CONFIGS));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setupModuleConfigs() {
/* 196 */     for (IModule module : ModuleHandler.getModules()) {
/* 197 */       IDynamicSpecConfig config = MODULE_CONFIGS.get(module);
/* 198 */       ModuleData data = ModuleHandler.getModuleData(module);
/* 199 */       module.defineModuleConfig((IModuleData)data, config);
/* 200 */       module.defineMaterialConfig((IModuleData)data, Collections.unmodifiableNavigableMap(MATERIAL_CONFIGS));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\config\ConfigHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */