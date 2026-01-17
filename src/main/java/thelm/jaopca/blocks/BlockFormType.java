//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.blocks;
/*     */ 
/*     */ import com.google.common.collect.TreeBasedTable;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*     */ import thelm.jaopca.api.blocks.IBlockFormType;
/*     */ import thelm.jaopca.api.blocks.IBlockInfo;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.custom.json.AABBDeserializer;
/*     */ import thelm.jaopca.custom.json.BlockFormSettingsDeserializer;
/*     */ import thelm.jaopca.custom.json.MaterialMappedFunctionDeserializer;
/*     */ import thelm.jaopca.custom.utils.BlockDeserializationHelper;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ public class BlockFormType
/*     */   implements IBlockFormType
/*     */ {
/*  44 */   public static final BlockFormType INSTANCE = new BlockFormType();
/*  45 */   private static final TreeSet<IForm> FORMS = new TreeSet<>();
/*  46 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormBlock> BLOCKS = TreeBasedTable.create();
/*  47 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormBlockItem> BLOCK_ITEMS = TreeBasedTable.create();
/*  48 */   private static final TreeBasedTable<IForm, IMaterial, IBlockInfo> BLOCK_INFOS = TreeBasedTable.create();
/*     */   
/*     */   private static boolean registered = false;
/*  51 */   public static final Type MATERIAL_FUNCTION_TYPE = (new TypeToken<Function<IMaterial, Material>>() {  }).getType();
/*  52 */   public static final Type SOUND_TYPE_FUNCTION_TYPE = (new TypeToken<Function<IMaterial, SoundType>>() {  }).getType();
/*     */   
/*     */   public static void init() {
/*  55 */     FormTypeHandler.registerFormType((IFormType)INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  60 */     return "block";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addForm(IForm form) {
/*  65 */     FORMS.add(form);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IForm> getForms() {
/*  70 */     return Collections.unmodifiableNavigableSet(FORMS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRegister(IForm form, IMaterial material) {
/*  75 */     String oredictName = MiscHelper.INSTANCE.getOredictName(form.getSecondaryName(), material.getName());
/*  76 */     return !ApiImpl.INSTANCE.getOredict().contains(oredictName);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockInfo getMaterialFormInfo(IForm form, IMaterial material) {
/*  81 */     IBlockInfo info = (IBlockInfo)BLOCK_INFOS.get(form, material);
/*  82 */     if (info == null && FORMS.contains(form) && form.getMaterials().contains(material)) {
/*  83 */       info = new BlockInfo((IMaterialFormBlock)BLOCKS.get(form, material), (IMaterialFormBlockItem)BLOCK_ITEMS.get(form, material));
/*  84 */       BLOCK_INFOS.put(form, material, info);
/*     */     } 
/*  86 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings getNewSettings() {
/*  91 */     return new BlockFormSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public GsonBuilder configureGsonBuilder(GsonBuilder builder) {
/*  96 */     return builder
/*  97 */       .registerTypeAdapter(MATERIAL_FUNCTION_TYPE, new MaterialMappedFunctionDeserializer(BlockDeserializationHelper.INSTANCE::getBlockMaterial, BlockDeserializationHelper.INSTANCE::getBlockMaterialName))
/*     */ 
/*     */       
/* 100 */       .registerTypeAdapter(SOUND_TYPE_FUNCTION_TYPE, new MaterialMappedFunctionDeserializer(BlockDeserializationHelper.INSTANCE::getSoundType, BlockDeserializationHelper.INSTANCE::getSoundTypeName))
/*     */ 
/*     */       
/* 103 */       .registerTypeAdapter(AxisAlignedBB.class, AABBDeserializer.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings deserializeSettings(JsonElement jsonElement, JsonDeserializationContext context) {
/* 108 */     return BlockFormSettingsDeserializer.INSTANCE.deserialize(jsonElement, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerMaterialForms() {
/* 113 */     if (registered) {
/*     */       return;
/*     */     }
/* 116 */     registered = true;
/* 117 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 118 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 119 */     for (IForm form : FORMS) {
/* 120 */       IBlockFormSettings settings = (IBlockFormSettings)form.getSettings();
/* 121 */       String secondaryName = form.getSecondaryName();
/* 122 */       for (IMaterial material : form.getMaterials()) {
/* 123 */         ResourceLocation registryName = new ResourceLocation("jaopca", form.getName() + '.' + miscHelper.toLowercaseUnderscore(material.getName()));
/*     */         
/* 125 */         IMaterialFormBlock materialFormBlock = settings.getBlockCreator().create(form, material, settings);
/* 126 */         Block block = materialFormBlock.toBlock();
/* 127 */         block.setRegistryName(registryName);
/* 128 */         block.setCreativeTab(apiImpl.creativeTab());
/* 129 */         BLOCKS.put(form, material, materialFormBlock);
/* 130 */         ForgeRegistries.BLOCKS.register((IForgeRegistryEntry)block);
/*     */         
/* 132 */         IMaterialFormBlockItem materialFormBlockItem = settings.getBlockItemCreator().create(materialFormBlock, settings);
/* 133 */         ItemBlock blockItem = materialFormBlockItem.toBlockItem();
/* 134 */         blockItem.setRegistryName(registryName);
/* 135 */         BLOCK_ITEMS.put(form, material, materialFormBlockItem);
/* 136 */         ForgeRegistries.ITEMS.register((IForgeRegistryEntry)blockItem);
/*     */         
/* 138 */         apiImpl.registerOredict(miscHelper.getOredictName(secondaryName, material.getName()), (Item)blockItem);
/* 139 */         for (String alternativeName : material.getAlternativeNames()) {
/* 140 */           apiImpl.registerOredict(miscHelper.getOredictName(secondaryName, alternativeName), (Item)blockItem);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Collection<IMaterialFormBlock> getBlocks() {
/* 147 */     return BLOCKS.values();
/*     */   }
/*     */   
/*     */   public static Collection<IMaterialFormBlockItem> getBlockItems() {
/* 151 */     return BLOCK_ITEMS.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\blocks\BlockFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
