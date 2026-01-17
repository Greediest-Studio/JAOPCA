//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.items;
/*     */ 
/*     */ import com.google.common.collect.TreeBasedTable;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.items.IItemFormSettings;
/*     */ import thelm.jaopca.api.items.IItemFormType;
/*     */ import thelm.jaopca.api.items.IItemInfo;
/*     */ import thelm.jaopca.api.items.IMaterialFormItem;
/*     */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.custom.json.EnumDeserializer;
/*     */ import thelm.jaopca.custom.json.ItemFormSettingsDeserializer;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ public class ItemFormType
/*     */   implements IItemFormType
/*     */ {
/*  38 */   public static final ItemFormType INSTANCE = new ItemFormType();
/*  39 */   private static final TreeSet<IForm> FORMS = new TreeSet<>();
/*  40 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormItem> ITEMS = TreeBasedTable.create();
/*  41 */   private static final TreeBasedTable<IForm, IMaterial, IItemInfo> ITEM_INFOS = TreeBasedTable.create();
/*     */   private static boolean registered = false;
/*     */   private static CreativeTabs creativeTab;
/*     */   
/*     */   public static void init() {
/*  46 */     FormTypeHandler.registerFormType((IFormType)INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  51 */     return "item";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addForm(IForm form) {
/*  56 */     FORMS.add(form);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IForm> getForms() {
/*  61 */     return Collections.unmodifiableNavigableSet(FORMS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRegister(IForm form, IMaterial material) {
/*  66 */     String oredictName = MiscHelper.INSTANCE.getOredictName(form.getSecondaryName(), material.getName());
/*  67 */     return !ApiImpl.INSTANCE.getOredict().contains(oredictName);
/*     */   }
/*     */ 
/*     */   
/*     */   public IItemInfo getMaterialFormInfo(IForm form, IMaterial material) {
/*  72 */     IItemInfo info = (IItemInfo)ITEM_INFOS.get(form, material);
/*  73 */     if (info == null && FORMS.contains(form) && form.getMaterials().contains(material)) {
/*  74 */       info = new ItemInfo((IMaterialFormItem)ITEMS.get(form, material));
/*  75 */       ITEM_INFOS.put(form, material, info);
/*     */     } 
/*  77 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   public IItemFormSettings getNewSettings() {
/*  82 */     return new ItemFormSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public GsonBuilder configureGsonBuilder(GsonBuilder builder) {
/*  87 */     return builder.registerTypeAdapter(EnumRarity.class, EnumDeserializer.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public IItemFormSettings deserializeSettings(JsonElement jsonElement, JsonDeserializationContext context) {
/*  92 */     return ItemFormSettingsDeserializer.INSTANCE.deserialize(jsonElement, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerMaterialForms() {
/*  97 */     if (registered) {
/*     */       return;
/*     */     }
/* 100 */     registered = true;
/* 101 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 102 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 103 */     for (IForm form : FORMS) {
/* 104 */       IItemFormSettings settings = (IItemFormSettings)form.getSettings();
/* 105 */       String secondaryName = form.getSecondaryName();
/* 106 */       for (IMaterial material : form.getMaterials()) {
/* 107 */         ResourceLocation registryName = new ResourceLocation("jaopca", form.getName() + '.' + miscHelper.toLowercaseUnderscore(material.getName()));
/*     */         
/* 109 */         IMaterialFormItem materialFormItem = settings.getItemCreator().create(form, material, settings);
/* 110 */         Item item = materialFormItem.toItem();
/* 111 */         item.setRegistryName(registryName);
/* 112 */         item.setCreativeTab(creativeTab);
/* 113 */         ITEMS.put(form, material, materialFormItem);
/* 114 */         ForgeRegistries.ITEMS.register((IForgeRegistryEntry)item);
/*     */         
/* 116 */         apiImpl.registerOredict(miscHelper.getOredictName(secondaryName, material.getName()), item);
/* 117 */         for (String alternativeName : material.getAlternativeNames()) {
/* 118 */           apiImpl.registerOredict(miscHelper.getOredictName(secondaryName, alternativeName), item);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static CreativeTabs getCreativeTab() {
/* 125 */     if (creativeTab == null) {
/* 126 */       creativeTab = new CreativeTabs("jaopca")
/*     */         {
/*     */           public ItemStack createIcon() {
/* 129 */             return new ItemStack(Items.GLOWSTONE_DUST);
/*     */           }
/*     */         };
/*     */     }
/* 133 */     return creativeTab;
/*     */   }
/*     */   
/*     */   public static Collection<IMaterialFormItem> getItems() {
/* 137 */     return ITEMS.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\items\ItemFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
