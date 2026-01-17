//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.utils;
/*     */ 
/*     */ import com.google.common.collect.Multiset;
/*     */ import com.google.common.collect.Streams;
/*     */ import com.google.common.collect.TreeMultiset;
/*     */ import com.google.common.primitives.Ints;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Locale;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.ModContainer;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.fml.common.versioning.ArtifactVersion;
/*     */ import net.minecraftforge.fml.common.versioning.InvalidVersionSpecificationException;
/*     */ import net.minecraftforge.fml.common.versioning.VersionRange;
/*     */ import net.minecraftforge.oredict.OreIngredient;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.fluids.IFluidProvider;
/*     */ import thelm.jaopca.api.helpers.IMiscHelper;
/*     */ import thelm.jaopca.api.items.IItemProvider;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.api.modules.IModule;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.materials.Material;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.modules.ModuleHandler;
/*     */ import thelm.jaopca.oredict.OredictHandler;
/*     */ 
/*     */ public class MiscHelper
/*     */   implements IMiscHelper {
/*  56 */   public static final MiscHelper INSTANCE = new MiscHelper(); private final ExecutorService executor; private static final Comparator<IForgeRegistryEntry<?>> ENTRY_PREFERENCE_COMPARATOR; private static final Predicate<String> META_ITEM_PREDICATE; private static final Predicate<String> CONFIG_MATERIAL_PREDICATE;
/*     */   private static final Predicate<String> CONFIG_MODULE_PREDICATE;
/*     */   
/*     */   private MiscHelper() {
/*  60 */     this.executor = Executors.newSingleThreadExecutor(r -> new Thread(r, "JAOPCA Executor Thread"));
/*     */   }
/*     */   
/*     */   public ResourceLocation getRecipeKey(String category, String material) {
/*  64 */     if (StringUtils.contains(category, 58)) {
/*  65 */       return new ResourceLocation(category + '.' + toLowercaseUnderscore(material));
/*     */     }
/*  67 */     return new ResourceLocation("jaopca", category + '.' + toLowercaseUnderscore(material));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOredictName(String form, String material) {
/*  72 */     return form + material;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFluidName(String form, String material) {
/*  77 */     return form + (form.isEmpty() ? "" : "_") + toLowercaseUnderscore(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemStack(Object obj, int count) {
/*  82 */     if (obj instanceof Supplier) {
/*  83 */       return getItemStack(((Supplier)obj).get(), count);
/*     */     }
/*  85 */     if (obj instanceof ItemStack) {
/*  86 */       ItemStack stack = (ItemStack)obj;
/*  87 */       if (!stack.isEmpty()) {
/*  88 */         return resizeItemStack(stack, count);
/*     */       }
/*     */     } else {
/*  91 */       if (obj instanceof Item && obj != Items.AIR) {
/*  92 */         return new ItemStack((Item)obj, count);
/*     */       }
/*  94 */       if (obj instanceof Block && obj != Blocks.AIR) {
/*  95 */         return new ItemStack((Block)obj, count);
/*     */       }
/*  97 */       if (obj instanceof IItemProvider) {
/*  98 */         Item item = ((IItemProvider)obj).asItem();
/*  99 */         if (item != Items.AIR) {
/* 100 */           return new ItemStack(item, count);
/*     */         }
/*     */       }
/* 103 */       else if (obj instanceof String && 
/* 104 */         OredictHandler.getOredict().contains(obj)) {
/* 105 */         return getPreferredItemStack(Arrays.asList((new OreIngredient((String)obj)).getMatchingStacks()), count);
/*     */       } 
/*     */     } 
/* 108 */     return ItemStack.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getIngredient(Object obj) {
/* 113 */     if (obj instanceof Supplier) {
/* 114 */       return getIngredient(((Supplier)obj).get());
/*     */     }
/* 116 */     if (obj instanceof Ingredient) {
/* 117 */       return (Ingredient)obj;
/*     */     }
/* 119 */     if (obj instanceof String) {
/* 120 */       if (OredictHandler.getOredict().contains(obj)) {
/* 121 */         return (Ingredient)new OreIngredient((String)obj);
/*     */       }
/*     */     }
/* 124 */     else if (obj instanceof ItemStack) {
/* 125 */       ItemStack stack = (ItemStack)obj;
/* 126 */       if (!stack.isEmpty()) {
/* 127 */         return Ingredient.fromStacks(new ItemStack[] { stack });
/*     */       }
/*     */     } else {
/* 130 */       if (obj instanceof Item) {
/* 131 */         return Ingredient.fromItem((Item)obj);
/*     */       }
/* 133 */       if (obj instanceof Block) {
/* 134 */         return Ingredient.fromItem(Item.getItemFromBlock((Block)obj));
/*     */       }
/* 136 */       if (obj instanceof IItemProvider)
/* 137 */         return Ingredient.fromItem(((IItemProvider)obj).asItem()); 
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getPreferredItemStack(Iterable<ItemStack> iterable, int count) {
/* 144 */     Optional<ItemStack> preferredEntry = Streams.stream(iterable).min(Comparator.comparing(ItemStack::getItem, entryPreferenceComparator()));
/* 145 */     return preferredEntry.<ItemStack>map(stack -> resizeItemStack(stack, count)).orElse(ItemStack.EMPTY);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack resizeItemStack(ItemStack stack, int count) {
/* 150 */     if (stack != null && !stack.isEmpty()) {
/* 151 */       ItemStack ret = stack.copy();
/* 152 */       ret.setCount(count);
/* 153 */       return ret;
/*     */     } 
/* 155 */     return ItemStack.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack getFluidStack(Object obj, int amount) {
/* 160 */     if (obj instanceof Supplier) {
/* 161 */       return getFluidStack(((Supplier)obj).get(), amount);
/*     */     }
/* 163 */     if (obj instanceof FluidStack) {
/* 164 */       return resizeFluidStack((FluidStack)obj, amount);
/*     */     }
/* 166 */     if (obj instanceof Fluid) {
/* 167 */       return new FluidStack((Fluid)obj, amount);
/*     */     }
/* 169 */     if (obj instanceof IFluidProvider) {
/* 170 */       return new FluidStack(((IFluidProvider)obj).asFluid(), amount);
/*     */     }
/* 172 */     if (obj instanceof String) {
/* 173 */       return FluidRegistry.getFluidStack((String)obj, amount);
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack resizeFluidStack(FluidStack stack, int amount) {
/* 180 */     if (stack != null) {
/* 181 */       FluidStack ret = stack.copy();
/* 182 */       ret.amount = amount;
/* 183 */       return ret;
/*     */     } 
/* 185 */     return null; }
/*     */   public Comparator<IForgeRegistryEntry<?>> entryPreferenceComparator() { return ENTRY_PREFERENCE_COMPARATOR; }
/*     */   public Predicate<String> metaItemPredicate() { return META_ITEM_PREDICATE; }
/* 188 */   static { ENTRY_PREFERENCE_COMPARATOR = ((entry1, entry2) -> {
/*     */         ResourceLocation key1 = entry1.getRegistryName();
/*     */         
/*     */         ResourceLocation key2 = entry2.getRegistryName();
/*     */         
/*     */         if (key1 == key2) {
/*     */           return 0;
/*     */         }
/*     */         
/*     */         if (key1 == null) {
/*     */           return 1;
/*     */         }
/*     */         if (key2 == null) {
/*     */           return -1;
/*     */         }
/*     */         int index1 = ConfigHandler.PREFERRED_MODS.indexOf(key1.getNamespace());
/*     */         int index2 = ConfigHandler.PREFERRED_MODS.indexOf(key2.getNamespace());
/*     */         return (index1 == index2) ? 0 : ((index1 == -1) ? 1 : ((index2 == -1) ? -1 : Integer.compare(index1, index2)));
/*     */       });
/* 207 */     META_ITEM_PREDICATE = (s -> ForgeRegistries.ITEMS.containsKey(new ResourceLocation(s.split("@(?=\\d*$)")[0])));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     CONFIG_MATERIAL_PREDICATE = (s -> (s.equals("*") || (s.startsWith("*") && MaterialType.fromName(s.substring(1)) != null) || MaterialHandler.containsMaterial(s)));
/* 272 */     CONFIG_MODULE_PREDICATE = (s -> (s.equals("*") || ModuleHandler.getModuleMap().containsKey(s))); }
/*     */   public ItemStack parseMetaItem(String str) { String[] split = str.split("@(?=\\d*$)"); int meta = 0;
/*     */     if (split.length == 2)
/*     */       meta = ((Integer)Optional.<Integer>ofNullable(Ints.tryParse(split[1])).orElse(Integer.valueOf(0))).intValue(); 
/* 276 */     return new ItemStack((Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(split[0])), 1, meta); } public Predicate<String> configMaterialPredicate() { return CONFIG_MATERIAL_PREDICATE; } public String toLowercaseUnderscore(String camelCase) { if (StringUtils.isEmpty(camelCase))
/*     */       return "";  LinkedList<String> list = new LinkedList<>(); for (String s : StringUtils.splitByCharacterTypeCamelCase(camelCase)) { if (StringUtils.isAllUpperCase(s)) { list.add(s.toLowerCase(Locale.US).chars().<CharSequence>mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining("_"))); } else if (StringUtils.isAllLowerCase(s) && !list.isEmpty()) { list.add((String)list.pollLast() + s); }
/*     */       else { list.add(StringUtils.uncapitalize(s)); }
/*     */        }
/*     */      return String.join("_", (Iterable)list); }
/* 281 */   public Predicate<String> configModulePredicate() { return CONFIG_MODULE_PREDICATE; } public void caclulateMaterialSet(Collection<String> configList, Collection<String> actualSet) { TreeMultiset<String> list = (TreeMultiset<String>)configList.stream().map(s -> s.startsWith("*") ? s.toLowerCase(Locale.US) : s).collect(Collectors.toCollection(TreeMultiset::create)); int listCount = list.count("*"); MaterialHandler.getMaterials().forEach(m -> list.add(m.getName(), listCount)); list.remove("*", listCount); for (MaterialType type : MaterialType.values()) {
/*     */       int listCount1 = list.count("*" + type.getName()); MaterialHandler.getMaterials().stream().filter(m -> (m.getType() == type)).forEach(m -> list.add(m.getName(), listCount1)); list.remove("*" + type.getName(), listCount1);
/*     */     } 
/*     */     actualSet.clear();
/*     */     list.entrySet().stream().filter(e -> ((e.getCount() & 0x1) == 1)).map(Multiset.Entry::getElement).forEach(actualSet::add); }
/* 286 */   public Runnable conditionalRunnable(BooleanSupplier conditionSupplier, Supplier<Runnable> trueRunnable, Supplier<Runnable> falseRunnable) { return () -> ((Runnable)(conditionSupplier.getAsBoolean() ? trueRunnable : falseRunnable).get()).run(); } public void caclulateModuleSet(Collection<String> configList, Collection<String> actualSet) { TreeMultiset<String> list = TreeMultiset.create(configList); int listCount = list.count("*");
/*     */     ModuleHandler.getModules().forEach(m -> list.add(m.getName(), listCount));
/*     */     list.remove("*", listCount);
/*     */     actualSet.clear();
/*     */     list.entrySet().stream().filter(e -> ((e.getCount() & 0x1) == 1)).map(Multiset.Entry::getElement).forEach(actualSet::add); }
/* 291 */   public <T> Supplier<T> conditionalSupplier(BooleanSupplier conditionSupplier, Supplier<Supplier<T>> trueSupplier, Supplier<Supplier<T>> falseSupplier) { return () -> ((Supplier)(conditionSupplier.getAsBoolean() ? trueSupplier : falseSupplier).get()).get(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasResource(ResourceLocation location) {
/* 296 */     return ((Boolean)conditionalSupplier(FMLCommonHandler.instance().getSide()::isClient, () -> (), () -> ())
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 303 */       .get()).booleanValue();
/*     */   }
/*     */   
/*     */   public <T> Future<T> submitAsyncTask(Callable<T> task) {
/* 307 */     return this.executor.submit(task);
/*     */   }
/*     */   
/*     */   public Future<?> submitAsyncTask(Runnable task) {
/* 311 */     return this.executor.submit(task);
/*     */   }
/*     */   
/*     */   public int squareColorDifference(int color1, int color2) {
/* 315 */     int diffR = (color1 << 16 & 0xFF) - (color2 << 16 & 0xFF);
/* 316 */     int diffG = (color1 << 8 & 0xFF) - (color2 << 8 & 0xFF);
/* 317 */     int diffB = (color1 & 0xFF) - (color2 & 0xFF);
/* 318 */     return diffR * diffR + diffG * diffG + diffB * diffB;
/*     */   }
/*     */   
/*     */   public Predicate<String> modVersionNotLoaded(Logger logger) {
/* 322 */     return dep -> {
/*     */         VersionRange versionRange;
/*     */         
/*     */         Loader modLoader = Loader.instance();
/*     */         int separatorIndex = dep.lastIndexOf('@');
/*     */         String modId = dep.substring(0, (separatorIndex == -1) ? dep.length() : separatorIndex);
/*     */         String spec = (separatorIndex == -1) ? "0" : dep.substring(separatorIndex + 1);
/*     */         try {
/*     */           versionRange = VersionRange.createFromVersionSpec(spec);
/* 331 */         } catch (InvalidVersionSpecificationException e) {
/*     */           logger.warn("Unable to parse version spec {} for mod id {}", spec, modId, e);
/*     */           return true;
/*     */         } 
/*     */         if (Loader.isModLoaded(modId)) {
/*     */           ArtifactVersion version = ((ModContainer)modLoader.getIndexedModList().get(modId)).getProcessedVersion();
/*     */           if (versionRange.containsVersion(version)) {
/*     */             return false;
/*     */           }
/*     */           logger.warn("Mod {} in version range {} was requested, was {}", modId, versionRange, version);
/*     */           return true;
/*     */         } 
/*     */         return true;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classNotExists(String className) {
/*     */     try {
/* 351 */       Class.forName(className, false, getClass().getClassLoader());
/* 352 */       return false;
/*     */     }
/* 354 */     catch (ClassNotFoundException e) {
/* 355 */       return true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopc\\utils\MiscHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
