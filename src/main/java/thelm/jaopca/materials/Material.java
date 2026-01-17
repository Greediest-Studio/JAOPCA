/*     */ package thelm.jaopca.materials;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.LoaderState;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.config.FormattedNumber;
/*     */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialColorEvent;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.client.colors.ColorHandler;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ public class Material
/*     */   implements IMaterial
/*     */ {
/*  34 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  36 */   private static final Set<String> DEFAULT_SMALL_BLOCKS = new TreeSet<>(Arrays.asList(new String[] { "Quartz", "QuartzBlack" }));
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final MaterialType type;
/*     */   private String modelType;
/*  42 */   private final TreeSet<String> alternativeNames = new TreeSet<>();
/*  43 */   private OptionalInt color = OptionalInt.empty();
/*     */   private boolean hasEffect = false;
/*  45 */   private EnumRarity displayRarity = EnumRarity.COMMON;
/*  46 */   private final List<String> extras = new ArrayList<>();
/*  47 */   private final TreeSet<String> configModuleBlacklist = new TreeSet<>();
/*     */   private boolean isSmallStorageBlock;
/*     */   private IDynamicSpecConfig config;
/*     */   private String oredict;
/*     */   private boolean shouldFireColorEvent = true;
/*     */   
/*     */   public Material(String name, MaterialType type) {
/*  54 */     this.name = name;
/*  55 */     this.type = type;
/*     */     
/*  57 */     this.isSmallStorageBlock = DEFAULT_SMALL_BLOCKS.contains(name);
/*     */     
/*  59 */     switch (type) { case INGOT:
/*     */       case INGOT_PLAIN:
/*  61 */         this.modelType = "metallic"; break;
/*     */       case GEM: case GEM_PLAIN: case CRYSTAL:
/*     */       case CRYSTAL_PLAIN:
/*  64 */         this.modelType = "crystal"; break;
/*     */       case DUST:
/*     */       case DUST_PLAIN:
/*  67 */         this.modelType = "dust";
/*     */         break; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  74 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialType getType() {
/*  79 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getAlternativeNames() {
/*  84 */     return Collections.unmodifiableNavigableSet(this.alternativeNames);
/*     */   }
/*     */ 
/*     */   
/*     */   public IMaterial getExtra(int index) {
/*  89 */     return (index == 0 || !hasExtra(index)) ? this : Optional.<IMaterial>ofNullable(MaterialHandler.getMaterial(this.extras.get(index - 1))).orElse(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasExtra(int index) {
/*  94 */     return (index == 0 || (index - 1 < this.extras.size() && !StringUtils.isEmpty(this.extras.get(index - 1))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmallStorageBlock() {
/*  99 */     return this.isSmallStorageBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getConfigModuleBlacklist() {
/* 104 */     return Collections.unmodifiableNavigableSet(this.configModuleBlacklist);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModelType() {
/* 109 */     return this.modelType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 114 */     if (Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
/* 115 */       if (!this.color.isPresent() && this.config != null) {
/* 116 */         MiscHelper.INSTANCE.conditionalRunnable(FMLCommonHandler.instance().getSide()::isClient, () -> (), () -> ())
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
/* 131 */           .run();
/*     */       }
/* 133 */       if (this.color.isPresent() && this.shouldFireColorEvent) {
/* 134 */         this.shouldFireColorEvent = false;
/* 135 */         MinecraftForge.EVENT_BUS.post((Event)new MaterialColorEvent(this, this.color.getAsInt()));
/*     */       } 
/*     */     } else {
/*     */       
/* 139 */       LOGGER.warn("Tried to get color for material {} before post-init", this.name);
/*     */     } 
/* 141 */     return 0xFF000000 | this.color.orElse(16777215);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect() {
/* 146 */     return this.hasEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRarity getDisplayRarity() {
/* 151 */     return this.displayRarity;
/*     */   }
/*     */   
/*     */   public void setAlternativeNames(Collection<String> names) {
/* 155 */     this.alternativeNames.addAll(names);
/*     */   }
/*     */   
/*     */   public void setConfig(IDynamicSpecConfig config) {
/* 159 */     this.config = config;
/*     */     
/* 161 */     config.setComment("general", "Configurations for material " + this.name + ".");
/*     */     
/* 163 */     List<String> cfgList = config.getDefinedStringList("general.alternativeNames", new ArrayList<>(this.alternativeNames), "The alternative names of this material.");
/* 164 */     this.alternativeNames.clear();
/* 165 */     this.alternativeNames.addAll(cfgList);
/*     */     
/* 167 */     cfgList = config.getDefinedStringList("general.extras", this.extras, MaterialHandler::containsMaterial, "The byproducts of this material.");
/* 168 */     this.extras.clear();
/* 169 */     this.extras.addAll(cfgList);
/*     */     
/* 171 */     this.isSmallStorageBlock = config.getDefinedBoolean("general.isSmallStorageBlock", this.isSmallStorageBlock, "Is the storage block of this material small (2x2).");
/*     */     
/* 173 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 174 */     miscHelper.caclulateModuleSet(config
/* 175 */         .getDefinedStringList("general.moduleBlacklist", new ArrayList<>(this.configModuleBlacklist), miscHelper
/* 176 */           .configModulePredicate(), "The module blacklist of this material. \"*\" is an alias for all modules. If a module name occurs an odd number of times (including wildcards), then the module is blacklisted."), this.configModuleBlacklist);
/*     */ 
/*     */     
/* 179 */     this.hasEffect = config.getDefinedBoolean("general.hasEffect", this.hasEffect, "Should items of this material have the enchanted glow.");
/* 180 */     this.modelType = config.getDefinedString("general.modelType", this.modelType, s -> isModelTypeValid(s), "The model type of the material.");
/*     */     
/* 182 */     if (ConfigHandler.resetColors) {
/* 183 */       config.remove("general.color");
/*     */     }
/* 185 */     this.color = config.getOptionalInt("general.color");
/*     */   }
/*     */   
/*     */   private String getOredict() {
/* 189 */     if (this.oredict == null) {
/* 190 */       this.oredict = this.type.getFormName() + this.name;
/*     */     }
/* 192 */     return this.oredict;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 197 */     return "Material:" + this.name;
/*     */   }
/*     */   
/*     */   private static boolean isModelTypeValid(String modelType) {
/* 201 */     return modelType.chars().allMatch(c -> (c == 95 || c == 45 || (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || c == 47 || c == 46));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\materials\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */