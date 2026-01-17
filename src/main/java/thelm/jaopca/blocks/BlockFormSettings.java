//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.blocks;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*     */ import thelm.jaopca.api.blocks.IBlockItemCreator;
/*     */ import thelm.jaopca.api.blocks.IBlockItemModelFunctionCreator;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ 
/*     */ public class BlockFormSettings implements IBlockFormSettings {
/*     */   private IBlockCreator blockCreator;
/*     */   private Function<IMaterial, Material> materialFunction;
/*     */   private Function<IMaterial, MapColor> mapColorFunction;
/*     */   private boolean blocksMovement;
/*     */   private Function<IMaterial, SoundType> soundTypeFunction;
/*     */   private ToIntFunction<IMaterial> lightOpacityFunction;
/*     */   private ToIntFunction<IMaterial> lightValueFunction;
/*     */   private ToDoubleFunction<IMaterial> blockHardnessFunction;
/*     */   private ToDoubleFunction<IMaterial> explosionResistanceFunction;
/*     */   private ToDoubleFunction<IMaterial> slipperinessFunction;
/*     */   private AxisAlignedBB boundingBox;
/*     */   private Function<IMaterial, String> harvestToolFunction;
/*     */   
/*     */   BlockFormSettings() {
/*  28 */     this.blockCreator = JAOPCABlock::new;
/*  29 */     this.materialFunction = (material -> Material.IRON);
/*  30 */     this.mapColorFunction = this.materialFunction.andThen(Material::getMaterialMapColor);
/*  31 */     this.blocksMovement = true;
/*  32 */     this.soundTypeFunction = (material -> SoundType.METAL);
/*  33 */     this.lightOpacityFunction = (material -> 15);
/*  34 */     this.lightValueFunction = (material -> 0);
/*  35 */     this.blockHardnessFunction = (material -> 5.0D);
/*  36 */     this.explosionResistanceFunction = (material -> 6.0D);
/*  37 */     this.slipperinessFunction = (material -> 0.6D);
/*  38 */     this.boundingBox = Block.FULL_BLOCK_AABB;
/*  39 */     this.harvestToolFunction = (material -> "");
/*  40 */     this.harvestLevelFunction = (material -> -1);
/*  41 */     this.flammabilityFunction = (material -> 0);
/*  42 */     this.fireSpreadSpeedFunction = (material -> 0);
/*  43 */     this.isFireSourceFunction = (material -> false);
/*  44 */     this.isBeaconBaseFunction = (material -> false);
/*  45 */     this.blockModelMapCreator = (IBlockModelMapCreator)JAOPCABlockModelMapCreator.INSTANCE;
/*  46 */     this.blockItemCreator = JAOPCABlockItem::new;
/*  47 */     this.itemStackLimitFunction = (material -> 64);
/*  48 */     this.hasEffectFunction = (material -> material.hasEffect());
/*  49 */     this.displayRarityFunction = (material -> material.getDisplayRarity());
/*  50 */     this.burnTimeFunction = (material -> -1);
/*  51 */     this.blockItemModelFunctionCreator = (IBlockItemModelFunctionCreator)JAOPCABlockItemModelFunctionCreator.INSTANCE;
/*     */   }
/*     */   private ToIntFunction<IMaterial> harvestLevelFunction; private ToIntFunction<IMaterial> flammabilityFunction; private ToIntFunction<IMaterial> fireSpreadSpeedFunction; private Predicate<IMaterial> isFireSourceFunction; private Predicate<IMaterial> isBeaconBaseFunction; private IBlockModelMapCreator blockModelMapCreator; private IBlockItemCreator blockItemCreator; private ToIntFunction<IMaterial> itemStackLimitFunction; private Predicate<IMaterial> hasEffectFunction; private Function<IMaterial, EnumRarity> displayRarityFunction; private ToIntFunction<IMaterial> burnTimeFunction; private IBlockItemModelFunctionCreator blockItemModelFunctionCreator;
/*     */   public IFormType getType() {
/*  55 */     return (IFormType)BlockFormType.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlockCreator(IBlockCreator blockCreator) {
/*  60 */     this.blockCreator = blockCreator;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockCreator getBlockCreator() {
/*  66 */     return this.blockCreator;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setMaterialFunction(Function<IMaterial, Material> materialFunction) {
/*  71 */     this.materialFunction = materialFunction;
/*  72 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, Material> getMaterialFunction() {
/*  77 */     return this.materialFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setMapColorFunction(Function<IMaterial, MapColor> mapColorFunction) {
/*  82 */     this.mapColorFunction = mapColorFunction;
/*  83 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, MapColor> getMapColorFunction() {
/*  88 */     return this.mapColorFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlocksMovement(boolean blocksMovement) {
/*  93 */     this.blocksMovement = blocksMovement;
/*  94 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBlocksMovement() {
/*  99 */     return this.blocksMovement;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setSoundTypeFunction(Function<IMaterial, SoundType> soundTypeFunction) {
/* 104 */     this.soundTypeFunction = soundTypeFunction;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, SoundType> getSoundTypeFunction() {
/* 110 */     return this.soundTypeFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setLightOpacityFunction(ToIntFunction<IMaterial> lightOpacityFunction) {
/* 115 */     this.lightOpacityFunction = lightOpacityFunction;
/* 116 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getLightOpacityFunction() {
/* 121 */     return this.lightOpacityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setLightValueFunction(ToIntFunction<IMaterial> lightValueFunction) {
/* 126 */     this.lightValueFunction = lightValueFunction;
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getLightValueFunction() {
/* 132 */     return this.lightValueFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlockHardnessFunction(ToDoubleFunction<IMaterial> blockHardnessFunction) {
/* 137 */     this.blockHardnessFunction = blockHardnessFunction;
/* 138 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToDoubleFunction<IMaterial> getBlockHardnessFunction() {
/* 143 */     return this.blockHardnessFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setExplosionResistanceFunction(ToDoubleFunction<IMaterial> explosionResistanceFunction) {
/* 148 */     this.explosionResistanceFunction = explosionResistanceFunction;
/* 149 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToDoubleFunction<IMaterial> getExplosionResistanceFunction() {
/* 154 */     return this.explosionResistanceFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setSlipperinessFunction(ToDoubleFunction<IMaterial> slipperinessFunction) {
/* 159 */     this.slipperinessFunction = slipperinessFunction;
/* 160 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToDoubleFunction<IMaterial> getSlipperinessFunction() {
/* 165 */     return this.slipperinessFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBoundingBox(AxisAlignedBB boundingBox) {
/* 170 */     this.boundingBox = boundingBox;
/* 171 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/* 176 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setHarvestToolFunction(Function<IMaterial, String> harvestToolFunction) {
/* 181 */     this.harvestToolFunction = harvestToolFunction;
/* 182 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, String> getHarvestToolFunction() {
/* 187 */     return this.harvestToolFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setHarvestLevelFunction(ToIntFunction<IMaterial> harvestLevelFunction) {
/* 192 */     this.harvestLevelFunction = harvestLevelFunction;
/* 193 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getHarvestLevelFunction() {
/* 198 */     return this.harvestLevelFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setFlammabilityFunction(ToIntFunction<IMaterial> flammabilityFunction) {
/* 203 */     this.flammabilityFunction = flammabilityFunction;
/* 204 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getFlammabilityFunction() {
/* 209 */     return this.flammabilityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setFireSpreadSpeedFunction(ToIntFunction<IMaterial> fireSpreadSpeedFunction) {
/* 214 */     this.fireSpreadSpeedFunction = fireSpreadSpeedFunction;
/* 215 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getFireSpreadSpeedFunction() {
/* 220 */     return this.fireSpreadSpeedFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setIsFireSourceFunction(Predicate<IMaterial> isFireSourceFunction) {
/* 225 */     this.isFireSourceFunction = isFireSourceFunction;
/* 226 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IMaterial> getIsFireSourceFunction() {
/* 231 */     return this.isFireSourceFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setIsBeaconBaseFunction(Predicate<IMaterial> isBeaconBaseFunction) {
/* 236 */     this.isBeaconBaseFunction = isBeaconBaseFunction;
/* 237 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IMaterial> getIsBeaconBaseFunction() {
/* 242 */     return this.isBeaconBaseFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlockModelMapCreator(IBlockModelMapCreator blockModelMapCreator) {
/* 247 */     this.blockModelMapCreator = blockModelMapCreator;
/* 248 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockModelMapCreator getBlockModelMapCreator() {
/* 253 */     return this.blockModelMapCreator;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlockItemCreator(IBlockItemCreator blockItemCreator) {
/* 258 */     this.blockItemCreator = blockItemCreator;
/* 259 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockItemCreator getBlockItemCreator() {
/* 264 */     return this.blockItemCreator;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setItemStackLimitFunction(ToIntFunction<IMaterial> itemStackLimitFunction) {
/* 269 */     this.itemStackLimitFunction = itemStackLimitFunction;
/* 270 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getItemStackLimitFunction() {
/* 275 */     return this.itemStackLimitFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setHasEffectFunction(Predicate<IMaterial> hasEffectFunction) {
/* 280 */     this.hasEffectFunction = hasEffectFunction;
/* 281 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<IMaterial> getHasEffectFunction() {
/* 286 */     return this.hasEffectFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> displayRarityFunction) {
/* 291 */     this.displayRarityFunction = displayRarityFunction;
/* 292 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Function<IMaterial, EnumRarity> getDisplayRarityFunction() {
/* 297 */     return this.displayRarityFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBurnTimeFunction(ToIntFunction<IMaterial> burnTimeFunction) {
/* 302 */     this.burnTimeFunction = burnTimeFunction;
/* 303 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ToIntFunction<IMaterial> getBurnTimeFunction() {
/* 308 */     return this.burnTimeFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings setBlockItemModelFunctionCreator(IBlockItemModelFunctionCreator blockItemModelFunctionCreator) {
/* 313 */     this.blockItemModelFunctionCreator = blockItemModelFunctionCreator;
/* 314 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockItemModelFunctionCreator getBlockItemModelFunctionCreator() {
/* 319 */     return this.blockItemModelFunctionCreator;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\blocks\BlockFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
