//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.fluids;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import thelm.jaopca.api.fluids.IFluidBlockCreator;
/*     */ import thelm.jaopca.api.fluids.IFluidBlockModelMapCreator;
/*     */ import thelm.jaopca.api.fluids.IFluidCreator;
/*     */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.client.models.fluids.JAOPCAFluidBlockModelMapCreator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FluidFormSettings
/*     */   implements IFluidFormSettings
/*     */ {
/*  26 */   private IFluidCreator fluidCreator = JAOPCAFluid::new;
/*     */   private Supplier<SoundEvent> fillSoundSupplier = () -> SoundEvents.ITEM_BUCKET_FILL_LAVA;
/*     */   private Supplier<SoundEvent> emptySoundSupplier = () -> SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
/*     */   private ToIntFunction<IMaterial> luminosityFunction = material -> 0;
/*     */   private ToIntFunction<IMaterial> densityFunction = material -> 1000;
/*     */   private ToIntFunction<IMaterial> temperatureFunction = material -> 300;
/*     */   private ToIntFunction<IMaterial> viscosityFunction = material -> 1000;
/*     */   private ToIntFunction<IMaterial> opacityFunction = material -> 255;
/*     */   private Predicate<IMaterial> isGaseousFunction = material -> false;
/*     */   private Function<IMaterial, EnumRarity> displayRarityFunction = material -> EnumRarity.COMMON;
/*  36 */   private IFluidBlockCreator fluidBlockCreator = JAOPCAFluidBlock::new;
/*     */   private ToIntFunction<IMaterial> maxLevelFunction = material -> 8;
/*     */   private Function<IMaterial, Material> materialFunction = material -> Material.LAVA;
/*  39 */   private Function<IMaterial, MapColor> mapColorFunction = this.materialFunction.andThen(Material::getMaterialMapColor);
/*     */   private ToDoubleFunction<IMaterial> blockHardnessFunction = material -> 100.0D;
/*     */   private ToDoubleFunction<IMaterial> explosionResistanceFunction = material -> 100.0D;
/*     */   private ToIntFunction<IMaterial> flammabilityFunction = material -> 0;
/*     */   private ToIntFunction<IMaterial> fireSpreadSpeedFunction = material -> 0;
/*     */   private Predicate<IMaterial> isFireSourceFunction = material -> false;
/*  45 */   private IFluidBlockModelMapCreator fluidBlockModelMapCreator = (IFluidBlockModelMapCreator)JAOPCAFluidBlockModelMapCreator.INSTANCE;
/*     */ 
/*     */   
/*     */   public IFormType getType() {
/*  49 */     return (IFormType)FluidFormType.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFluidCreator(IFluidCreator fluidCreator) {
/*  54 */     this.fluidCreator = fluidCreator;
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidCreator getFluidCreator() {
/*  60 */     return this.fluidCreator;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFillSoundSupplier(Supplier<SoundEvent> fillSoundSupplier) {
/*  65 */     this.fillSoundSupplier = fillSoundSupplier;
/*  66 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Supplier<SoundEvent> getFillSoundSupplier() {
/*  71 */     return this.fillSoundSupplier;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setEmptySoundSupplier(Supplier<SoundEvent> emptySoundSupplier) {
/*  76 */     this.emptySoundSupplier = emptySoundSupplier;
/*  77 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Supplier<SoundEvent> getEmptySoundSupplier() {
/*  82 */     return this.emptySoundSupplier;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setLuminosityFunction(ToIntFunction<IMaterial> luminosityFunction) {
/*  87 */     this.luminosityFunction = luminosityFunction;
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getLuminosityFunction() {
/*  93 */     return this.luminosityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setDensityFunction(ToIntFunction<IMaterial> densityFunction) {
/*  98 */     this.densityFunction = densityFunction;
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getDensityFunction() {
/* 104 */     return this.densityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setTemperatureFunction(ToIntFunction<IMaterial> temperatureFunction) {
/* 109 */     this.temperatureFunction = temperatureFunction;
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getTemperatureFunction() {
/* 115 */     return this.temperatureFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setViscosityFunction(ToIntFunction<IMaterial> viscosityFunction) {
/* 120 */     this.viscosityFunction = viscosityFunction;
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getViscosityFunction() {
/* 126 */     return this.viscosityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setOpacityFunction(ToIntFunction<IMaterial> opacityFunction) {
/* 131 */     this.opacityFunction = opacityFunction;
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getOpacityFunction() {
/* 137 */     return this.opacityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setIsGaseousFunction(Predicate<IMaterial> isGaseousFunction) {
/* 142 */     this.isGaseousFunction = isGaseousFunction;
/* 143 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IMaterial> getIsGaseousFunction() {
/* 148 */     return this.isGaseousFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> displayRarityFunction) {
/* 153 */     this.displayRarityFunction = displayRarityFunction;
/* 154 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, EnumRarity> getDisplayRarityFunction() {
/* 159 */     return this.displayRarityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFluidBlockCreator(IFluidBlockCreator fluidBlockCreator) {
/* 164 */     this.fluidBlockCreator = fluidBlockCreator;
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidBlockCreator getFluidBlockCreator() {
/* 170 */     return this.fluidBlockCreator;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setMaxLevelFunction(ToIntFunction<IMaterial> maxLevelFunction) {
/* 175 */     this.maxLevelFunction = maxLevelFunction;
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getMaxLevelFunction() {
/* 181 */     return this.maxLevelFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setMaterialFunction(Function<IMaterial, Material> materialFunction) {
/* 186 */     this.materialFunction = materialFunction;
/* 187 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, Material> getMaterialFunction() {
/* 192 */     return this.materialFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setMapColorFunction(Function<IMaterial, MapColor> mapColorFunction) {
/* 197 */     this.mapColorFunction = mapColorFunction;
/* 198 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, MapColor> getMapColorFunction() {
/* 203 */     return this.mapColorFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setBlockHardnessFunction(ToDoubleFunction<IMaterial> blockHardnessFunction) {
/* 208 */     this.blockHardnessFunction = blockHardnessFunction;
/* 209 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToDoubleFunction<IMaterial> getBlockHardnessFunction() {
/* 214 */     return this.blockHardnessFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setExplosionResistanceFunction(ToDoubleFunction<IMaterial> explosionResistanceFunction) {
/* 219 */     this.explosionResistanceFunction = explosionResistanceFunction;
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToDoubleFunction<IMaterial> getExplosionResistanceFunction() {
/* 225 */     return this.explosionResistanceFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFlammabilityFunction(ToIntFunction<IMaterial> flammabilityFunction) {
/* 230 */     this.flammabilityFunction = flammabilityFunction;
/* 231 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getFlammabilityFunction() {
/* 236 */     return this.flammabilityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFireSpreadSpeedFunction(ToIntFunction<IMaterial> fireSpreadSpeedFunction) {
/* 241 */     this.fireSpreadSpeedFunction = fireSpreadSpeedFunction;
/* 242 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getFireSpreadSpeedFunction() {
/* 247 */     return this.fireSpreadSpeedFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setIsFireSourceFunction(Predicate<IMaterial> isFireSourceFunction) {
/* 252 */     this.isFireSourceFunction = isFireSourceFunction;
/* 253 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IMaterial> getIsFireSourceFunction() {
/* 258 */     return this.isFireSourceFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings setFluidBlockModelMapCreator(IFluidBlockModelMapCreator fluidBlockModelMapCreator) {
/* 263 */     this.fluidBlockModelMapCreator = fluidBlockModelMapCreator;
/* 264 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidBlockModelMapCreator getFluidBlockModelMapCreator() {
/* 269 */     return this.fluidBlockModelMapCreator;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\fluids\FluidFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
