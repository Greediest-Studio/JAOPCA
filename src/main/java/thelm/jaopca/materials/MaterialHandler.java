/*     */ package thelm.jaopca.materials;
/*     */ 
/*     */ import com.google.common.collect.ListMultimap;
/*     */ import com.google.common.collect.MultimapBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaterialHandler
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*  27 */   private static final TreeSet<String> BLACKLISTED_NAMES = new TreeSet<>();
/*  28 */   private static final TreeSet<String> USED_PREFIXES = new TreeSet<>(Arrays.asList(new String[] { "ingotAny", "ingotHot", "ingotDouble", "ingotTriple", "ingotQuadruple", "ingotQuintuple", "gemAny", "gemOre", "gemRaw", "gemUncut", "gemPolished", "gemChipped", "gemFlawed", "gemFlawless", "gemExquisite", "gemLegendary", "crystalAny", "crystalFragment", "crystalShard", "crystalCluster", "crystalPure", "dustAny", "dustSmall", "dustTiny", "dustDirty", "dustDiv72", "dustImpure", "dustPure", "dustRefined", "dustRegular" }));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private static final ListMultimap<String, String> ALTERNATIVE_NAMES = MultimapBuilder.treeKeys().arrayListValues().build();
/*  34 */   private static final TreeMap<String, Material> MATERIALS = new TreeMap<>();
/*     */   
/*     */   public static Map<String, Material> getMaterialMap() {
/*  37 */     return MATERIALS;
/*     */   }
/*     */   
/*     */   public static Collection<Material> getMaterials() {
/*  41 */     return MATERIALS.values();
/*     */   }
/*     */   
/*     */   public static Material getMaterial(String name) {
/*  45 */     return MATERIALS.get(name);
/*     */   }
/*     */   
/*     */   public static boolean containsMaterial(String name) {
/*  49 */     return MATERIALS.containsKey(name);
/*     */   }
/*     */   
/*     */   public static boolean registerBlacklistedMaterialNames(String... names) {
/*  53 */     return Collections.addAll(BLACKLISTED_NAMES, names);
/*     */   }
/*     */   
/*     */   public static boolean registerUsedPlainPrefixes(String... prefixes) {
/*  57 */     return Collections.addAll(USED_PREFIXES, prefixes);
/*     */   }
/*     */   
/*     */   public static boolean registerMaterialAlternativeNames(String name, String... alternatives) {
/*  61 */     return ALTERNATIVE_NAMES.putAll(name, Arrays.asList(alternatives));
/*     */   }
/*     */   
/*     */   public static void findMaterials() {
/*  65 */     MATERIALS.clear();
/*     */     
/*  67 */     Set<String> oredict = ApiImpl.INSTANCE.getOredict();
/*     */     
/*  69 */     Set<String> allMaterials = new TreeSet<>();
/*     */     
/*  71 */     Set<String> nameBlacklist = new TreeSet<>();
/*     */     
/*  73 */     Set<String> nonPlainPrefixes = ConfigHandler.nonPlainUsedPrefix ? USED_PREFIXES : Collections.<String>emptySet();
/*     */     
/*  75 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/*  76 */     nameBlacklist.addAll(ConfigHandler.GEM_OVERRIDES);
/*  77 */     nameBlacklist.addAll(ConfigHandler.CRYSTAL_OVERRIDES);
/*  78 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/*  79 */     Set<String> ingots = ConfigHandler.ingot ? find(oredict, nameBlacklist, nonPlainPrefixes, "ingot", new String[] { "ore" }) : new LinkedHashSet<>();
/*  80 */     nameBlacklist.clear();
/*  81 */     allMaterials.addAll(ingots);
/*     */     
/*  83 */     nameBlacklist.addAll(allMaterials);
/*  84 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/*  85 */     nameBlacklist.addAll(ConfigHandler.CRYSTAL_OVERRIDES);
/*  86 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/*  87 */     Set<String> gems = ConfigHandler.gem ? find(oredict, nameBlacklist, nonPlainPrefixes, "gem", new String[] { "ore" }) : new LinkedHashSet<>();
/*  88 */     nameBlacklist.clear();
/*  89 */     allMaterials.addAll(gems);
/*     */     
/*  91 */     nameBlacklist.addAll(allMaterials);
/*  92 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/*  93 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/*  94 */     Set<String> crystals = ConfigHandler.crystal ? find(oredict, nameBlacklist, nonPlainPrefixes, "crystal", new String[] { "ore" }) : new LinkedHashSet<>();
/*  95 */     nameBlacklist.clear();
/*  96 */     allMaterials.addAll(crystals);
/*     */     
/*  98 */     nameBlacklist.addAll(allMaterials);
/*  99 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/* 100 */     Set<String> dusts = ConfigHandler.dust ? find(oredict, nameBlacklist, nonPlainPrefixes, "dust", new String[] { "ore" }) : new LinkedHashSet<>();
/* 101 */     nameBlacklist.clear();
/* 102 */     allMaterials.addAll(dusts);
/*     */     
/* 104 */     nameBlacklist.addAll(allMaterials);
/* 105 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/* 106 */     nameBlacklist.addAll(ConfigHandler.GEM_OVERRIDES);
/* 107 */     nameBlacklist.addAll(ConfigHandler.CRYSTAL_OVERRIDES);
/* 108 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/* 109 */     Set<String> ingotsPlain = ConfigHandler.ingotPlain ? find(oredict, nameBlacklist, USED_PREFIXES, "ingot", new String[0]) : new LinkedHashSet<>();
/* 110 */     nameBlacklist.clear();
/* 111 */     allMaterials.addAll(ingotsPlain);
/*     */     
/* 113 */     nameBlacklist.addAll(allMaterials);
/* 114 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/* 115 */     nameBlacklist.addAll(ConfigHandler.CRYSTAL_OVERRIDES);
/* 116 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/* 117 */     Set<String> gemsPlain = ConfigHandler.gemPlain ? find(oredict, nameBlacklist, USED_PREFIXES, "gem", new String[0]) : new LinkedHashSet<>();
/* 118 */     nameBlacklist.clear();
/* 119 */     allMaterials.addAll(gemsPlain);
/*     */     
/* 121 */     nameBlacklist.addAll(allMaterials);
/* 122 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/* 123 */     nameBlacklist.addAll(ConfigHandler.DUST_OVERRIDES);
/* 124 */     Set<String> crystalsPlain = ConfigHandler.crystalPlain ? find(oredict, nameBlacklist, USED_PREFIXES, "crystal", new String[0]) : new LinkedHashSet<>();
/* 125 */     nameBlacklist.clear();
/* 126 */     allMaterials.addAll(crystalsPlain);
/*     */     
/* 128 */     nameBlacklist.addAll(allMaterials);
/* 129 */     nameBlacklist.addAll(BLACKLISTED_NAMES);
/* 130 */     Set<String> dustsPlain = ConfigHandler.dustPlain ? find(oredict, nameBlacklist, USED_PREFIXES, "dust", new String[0]) : new LinkedHashSet<>();
/* 131 */     nameBlacklist.clear();
/* 132 */     allMaterials.addAll(dustsPlain);
/*     */     
/* 134 */     for (String name : ingots) {
/* 135 */       Material material = new Material(name, MaterialType.INGOT);
/* 136 */       MATERIALS.put(name, material);
/* 137 */       LOGGER.debug("Added ingot material {}", name);
/*     */     } 
/* 139 */     for (String name : gems) {
/* 140 */       Material material = new Material(name, MaterialType.GEM);
/* 141 */       MATERIALS.put(name, material);
/* 142 */       LOGGER.debug("Added gem material {}", name);
/*     */     } 
/* 144 */     for (String name : crystals) {
/* 145 */       Material material = new Material(name, MaterialType.CRYSTAL);
/* 146 */       MATERIALS.put(name, material);
/* 147 */       LOGGER.debug("Added crystal material {}", name);
/*     */     } 
/* 149 */     for (String name : dusts) {
/* 150 */       Material material = new Material(name, MaterialType.DUST);
/* 151 */       MATERIALS.put(name, material);
/* 152 */       LOGGER.debug("Added dust material {}", name);
/*     */     } 
/* 154 */     for (String name : ingotsPlain) {
/* 155 */       Material material = new Material(name, MaterialType.INGOT_PLAIN);
/* 156 */       MATERIALS.put(name, material);
/* 157 */       LOGGER.debug("Added plain ingot material {}", name);
/*     */     } 
/* 159 */     for (String name : gemsPlain) {
/* 160 */       Material material = new Material(name, MaterialType.GEM_PLAIN);
/* 161 */       MATERIALS.put(name, material);
/* 162 */       LOGGER.debug("Added plain gem material {}", name);
/*     */     } 
/* 164 */     for (String name : crystalsPlain) {
/* 165 */       Material material = new Material(name, MaterialType.CRYSTAL_PLAIN);
/* 166 */       MATERIALS.put(name, material);
/* 167 */       LOGGER.debug("Added plain crystal material {}", name);
/*     */     } 
/* 169 */     for (String name : dustsPlain) {
/* 170 */       Material material = new Material(name, MaterialType.DUST_PLAIN);
/* 171 */       MATERIALS.put(name, material);
/* 172 */       LOGGER.debug("Added plain dust material {}", name);
/*     */     } 
/* 174 */     LOGGER.info("Added {} materials", Integer.valueOf(MATERIALS.size()));
/*     */   }
/*     */   
/*     */   protected static Set<String> find(Set<String> entries, Set<String> nameBlacklist, Set<String> prefixBlacklist, String mainPrefix, String... prefixes) {
/* 178 */     Set<String> found = new TreeSet<>();
/* 179 */     for (String entry : entries) {
/* 180 */       if (entry.startsWith(mainPrefix)) {
/* 181 */         String name = entry.substring(mainPrefix.length());
/* 182 */         if (!name.isEmpty() && !Character.isLowerCase(name.charAt(0)) && !nameBlacklist.contains(name) && Arrays.<String>stream(prefixes).map(prefix -> prefix + name).allMatch(entries::contains)) {
/* 183 */           found.add(name);
/*     */         }
/*     */       } 
/*     */     } 
/* 187 */     Set<String> ret = new TreeSet<>();
/* 188 */     for (String name : found) {
/* 189 */       String entry = mainPrefix + name;
/* 190 */       if (prefixBlacklist.stream().noneMatch(bp -> {
/*     */             if (entry.startsWith(bp)) {
/*     */               String nName = entry.substring(bp.length());
/* 193 */               return (ConfigHandler.strictUsedPrefix || found.contains(nName) || nameBlacklist.contains(nName));
/*     */             } 
/*     */             return false;
/*     */           })) {
/* 197 */         ret.add(name);
/*     */       }
/*     */     } 
/* 200 */     return ret;
/*     */   }
/*     */   
/*     */   public static boolean addMaterial(String name, MaterialType type, Set<String> alternativeNames) {
/* 204 */     if (MATERIALS.containsKey(name)) {
/* 205 */       LOGGER.warn("Material {} already exists, skipping.", name);
/* 206 */       return false;
/*     */     } 
/* 208 */     Material material = new Material(name, type);
/* 209 */     if (alternativeNames != null && !alternativeNames.isEmpty()) {
/* 210 */       material.setAlternativeNames(alternativeNames);
/*     */     }
/* 212 */     MATERIALS.put(name, material);
/* 213 */     LOGGER.info("Added custom material {}", name);
/* 214 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\materials\MaterialHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */