/*     */ package thelm.jaopca.fluids;
/*     */ 
/*     */ import com.google.common.collect.TreeBasedTable;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*     */ import thelm.jaopca.api.fluids.IFluidFormType;
/*     */ import thelm.jaopca.api.fluids.IFluidInfo;
/*     */ import thelm.jaopca.api.fluids.IMaterialFormFluid;
/*     */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.custom.json.FluidFormSettingsDeserializer;
/*     */ import thelm.jaopca.custom.json.ForgeRegistryEntrySupplierDeserializer;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.materials.MaterialHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ public class FluidFormType
/*     */   implements IFluidFormType {
/*  41 */   public static final FluidFormType INSTANCE = new FluidFormType();
/*  42 */   private static final TreeSet<IForm> FORMS = new TreeSet<>();
/*  43 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormFluid> FLUIDS = TreeBasedTable.create();
/*  44 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormFluidBlock> FLUID_BLOCKS = TreeBasedTable.create();
/*  45 */   private static final TreeBasedTable<IForm, IMaterial, IFluidInfo> FLUID_INFOS = TreeBasedTable.create();
/*     */   
/*     */   private static boolean registered = false;
/*  48 */   public static final Type SOUND_EVENT_SUPPLIER_TYPE = (new TypeToken<Supplier<SoundEvent>>() {  }).getType();
/*     */   
/*     */   public static void init() {
/*  51 */     FormTypeHandler.registerFormType((IFormType)INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  56 */     return "fluid";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addForm(IForm form) {
/*  61 */     FORMS.add(form);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IForm> getForms() {
/*  66 */     return Collections.unmodifiableNavigableSet(FORMS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRegister(IForm form, IMaterial material) {
/*  71 */     String fluidName = MiscHelper.INSTANCE.getFluidName(form.getSecondaryName(), material.getName());
/*  72 */     return !FluidRegistry.isFluidRegistered(fluidName);
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidInfo getMaterialFormInfo(IForm form, IMaterial material) {
/*  77 */     IFluidInfo info = (IFluidInfo)FLUID_INFOS.get(form, material);
/*  78 */     if (info == null && FORMS.contains(form) && form.getMaterials().contains(material)) {
/*  79 */       info = new FluidInfo((IMaterialFormFluid)FLUIDS.get(form, material), (IMaterialFormFluidBlock)FLUID_BLOCKS.get(form, material));
/*  80 */       FLUID_INFOS.put(form, material, info);
/*     */     } 
/*  82 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings getNewSettings() {
/*  87 */     return new FluidFormSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public GsonBuilder configureGsonBuilder(GsonBuilder builder) {
/*  92 */     return builder.registerTypeAdapter(SOUND_EVENT_SUPPLIER_TYPE, ForgeRegistryEntrySupplierDeserializer.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings deserializeSettings(JsonElement jsonElement, JsonDeserializationContext context) {
/*  97 */     return FluidFormSettingsDeserializer.INSTANCE.deserialize(jsonElement, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerMaterialForms() {
/* 102 */     if (registered) {
/*     */       return;
/*     */     }
/* 105 */     registered = true;
/* 106 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*     */     
/* 108 */     for (IForm form : FORMS) {
/* 109 */       IFluidFormSettings settings = (IFluidFormSettings)form.getSettings();
/*     */ 
/*     */       
/* 112 */       Set<IMaterial> newMaterials = new HashSet<>(form.getMaterials());
/* 113 */       for (IMaterial material : MaterialHandler.getMaterials()) {
/* 114 */         MaterialType type = material.getType();
/* 115 */         if (type == MaterialType.INGOT || type == MaterialType.INGOT_PLAIN) {
/* 116 */           newMaterials.add(material);
/*     */         }
/*     */       } 
/* 119 */       form.setMaterials(newMaterials);
/*     */       
/* 121 */       for (IMaterial material : form.getMaterials()) {
/* 122 */         String fluidName = miscHelper.getFluidName(form.getSecondaryName(), material.getName());
/* 123 */         if (FluidRegistry.isFluidRegistered(fluidName)) {
/*     */           
/* 125 */           System.out.println("[JAOPCA] Fluid already registered: " + fluidName + ", skip.");
/*     */           
/*     */           continue;
/*     */         } 
/* 129 */         ResourceLocation registryName = new ResourceLocation("jaopca", form.getName() + '.' + miscHelper.toLowercaseUnderscore(material.getName()));
/*     */         
/* 131 */         IMaterialFormFluid materialFormFluid = settings.getFluidCreator().create(form, material, settings);
/* 132 */         Fluid fluid = materialFormFluid.toFluid();
/* 133 */         FLUIDS.put(form, material, materialFormFluid);
/* 134 */         FluidRegistry.registerFluid(fluid);
/* 135 */         FluidRegistry.addBucketForFluid(fluid);
/*     */         
/* 137 */         IMaterialFormFluidBlock materialFormFluidBlock = settings.getFluidBlockCreator().create(materialFormFluid, settings);
/* 138 */         Block fluidBlock = materialFormFluidBlock.toBlock();
/* 139 */         fluidBlock.setRegistryName(registryName);
/* 140 */         FLUID_BLOCKS.put(form, material, materialFormFluidBlock);
/* 141 */         ForgeRegistries.BLOCKS.register((IForgeRegistryEntry)fluidBlock);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static Collection<IMaterialFormFluid> getFluids() {
/* 146 */     return FLUIDS.values();
/*     */   }
/*     */   
/*     */   public static Collection<IMaterialFormFluidBlock> getFluidBlocks() {
/* 150 */     return FLUID_BLOCKS.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\fluids\FluidFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */