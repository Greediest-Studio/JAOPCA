/*     */ package thelm.jaopca.fluids;
/*     */ 
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*     */ import thelm.jaopca.api.fluids.IMaterialFormFluid;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.functions.MemoizingSuppliers;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ public class JAOPCAFluid
/*     */   extends Fluid
/*     */   implements IMaterialFormFluid
/*     */ {
/*     */   private final IForm form;
/*     */   private final IMaterial material;
/*     */   protected final IFluidFormSettings settings;
/*     */   protected Supplier<EnumRarity> rarity;
/*     */   protected IntSupplier opacity;
/*     */   protected Supplier<String> translationKey;
/*     */   
/*     */   public JAOPCAFluid(IForm form, IMaterial material, IFluidFormSettings settings) {
/*  30 */     super(MiscHelper.INSTANCE.getFluidName(form.getSecondaryName(), material.getName()), new ResourceLocation("jaopca", "fluid/" + material
/*  31 */           .getModelType() + '/' + form.getName() + "_still"), new ResourceLocation("jaopca", "fluid/" + material
/*  32 */           .getModelType() + '/' + form.getName() + "_flow"));
/*  33 */     this.form = form;
/*  34 */     this.material = material;
/*  35 */     this.settings = settings;
/*     */     
/*  37 */     setLuminosity(settings.getLuminosityFunction().applyAsInt(material));
/*  38 */     setDensity(settings.getDensityFunction().applyAsInt(material));
/*  39 */     setTemperature(settings.getTemperatureFunction().applyAsInt(material));
/*  40 */     setViscosity(settings.getViscosityFunction().applyAsInt(material));
/*  41 */     setGaseous(settings.getIsGaseousFunction().test(material));
/*     */     
/*  43 */     this.rarity = (Supplier<EnumRarity>)MemoizingSuppliers.of(settings.getDisplayRarityFunction(), () -> material);
/*  44 */     this.opacity = (IntSupplier)MemoizingSuppliers.of(settings.getOpacityFunction(), () -> material);
/*  45 */     this.translationKey = (Supplier<String>)MemoizingSuppliers.of(() -> "fluid.jaopca." + MiscHelper.INSTANCE.toLowercaseUnderscore(material.getName()));
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm getForm() {
/*  50 */     return this.form;
/*     */   }
/*     */ 
/*     */   
/*     */   public IMaterial getMaterial() {
/*  55 */     return this.material;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity() {
/*  60 */     return this.rarity.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEvent getFillSound() {
/*  65 */     return this.settings.getFillSoundSupplier().get();
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEvent getEmptySound() {
/*  70 */     return this.settings.getEmptySoundSupplier().get();
/*     */   }
/*     */   
/*     */   public int getOpacity() {
/*  74 */     return this.opacity.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/*  79 */     return this.material.getColor() & 0xFFFFFF | getOpacity() << 24;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getStill() {
/*  84 */     if (MiscHelper.INSTANCE.hasResource(new ResourceLocation("jaopca", "textures/fluid/" + this.form.getName() + '.' + this.material.getName() + "_still.png"))) {
/*  85 */       return new ResourceLocation("jaopca", "fluid/" + this.form.getName() + '.' + this.material.getName() + "_still");
/*     */     }
/*  87 */     return super.getStill();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getFlowing() {
/*  92 */     if (MiscHelper.INSTANCE.hasResource(new ResourceLocation("jaopca", "textures/fluid/" + this.form.getName() + '.' + this.material.getName() + "_flow.png"))) {
/*  93 */       return new ResourceLocation("jaopca", "fluid/" + this.form.getName() + '.' + this.material.getName() + "_flow");
/*     */     }
/*  95 */     return super.getFlowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/* 100 */     return this.translationKey.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedName(FluidStack stack) {
/* 105 */     return ApiImpl.INSTANCE.currentLocalizer().localizeMaterialForm("fluid.jaopca." + getForm().getName(), getMaterial(), getUnlocalizedName());
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\fluids\JAOPCAFluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */