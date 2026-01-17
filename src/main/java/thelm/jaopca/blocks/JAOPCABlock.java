//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.blocks;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.DoubleSupplier;
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.functions.MemoizingSuppliers;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ 
/*     */ 
/*     */ public class JAOPCABlock
/*     */   extends Block
/*     */   implements IMaterialFormBlock
/*     */ {
/*     */   private final IForm form;
/*     */   private final IMaterial material;
/*     */   protected final IBlockFormSettings settings;
/*     */   protected boolean blocksMovement;
/*     */   protected Supplier<Material> blockMaterial;
/*     */   protected Supplier<MapColor> mapColor;
/*     */   protected Supplier<SoundType> soundType;
/*     */   protected IntSupplier lightOpacity;
/*     */   protected IntSupplier lightValue;
/*     */   protected DoubleSupplier blockHardness;
/*     */   protected DoubleSupplier explosionResistance;
/*     */   protected DoubleSupplier slipperiness;
/*     */   protected AxisAlignedBB boundingBox;
/*     */   protected Supplier<String> harvestTool;
/*     */   protected IntSupplier harvestLevel;
/*     */   protected IntSupplier flammability;
/*     */   protected IntSupplier fireSpreadSpeed;
/*     */   protected BooleanSupplier isFireSource;
/*     */   protected BooleanSupplier isBeaconBase;
/*     */   protected Supplier<String> translationKey;
/*     */   
/*     */   public JAOPCABlock(IForm form, IMaterial material, IBlockFormSettings settings) {
/*  55 */     super(Material.IRON);
/*  56 */     this.form = form;
/*  57 */     this.material = material;
/*  58 */     this.settings = settings;
/*     */     
/*  60 */     this.blocksMovement = settings.getBlocksMovement();
/*  61 */     this.blockMaterial = (Supplier<Material>)MemoizingSuppliers.of(settings.getMaterialFunction(), () -> material);
/*  62 */     this.mapColor = (Supplier<MapColor>)MemoizingSuppliers.of(settings.getMapColorFunction(), () -> material);
/*  63 */     this.soundType = (Supplier<SoundType>)MemoizingSuppliers.of(settings.getSoundTypeFunction(), () -> material);
/*  64 */     this.lightOpacity = (IntSupplier)MemoizingSuppliers.of(settings.getLightOpacityFunction(), () -> material);
/*  65 */     this.lightValue = (IntSupplier)MemoizingSuppliers.of(settings.getLightValueFunction(), () -> material);
/*  66 */     this.blockHardness = (DoubleSupplier)MemoizingSuppliers.of(settings.getBlockHardnessFunction(), () -> material);
/*  67 */     this.explosionResistance = (DoubleSupplier)MemoizingSuppliers.of(settings.getExplosionResistanceFunction(), () -> material);
/*  68 */     this.slipperiness = (DoubleSupplier)MemoizingSuppliers.of(settings.getSlipperinessFunction(), () -> material);
/*  69 */     this.boundingBox = settings.getBoundingBox();
/*  70 */     this.harvestTool = (Supplier<String>)MemoizingSuppliers.of(settings.getHarvestToolFunction(), () -> material);
/*  71 */     this.harvestLevel = (IntSupplier)MemoizingSuppliers.of(settings.getHarvestLevelFunction(), () -> material);
/*  72 */     this.flammability = (IntSupplier)MemoizingSuppliers.of(settings.getFlammabilityFunction(), () -> material);
/*  73 */     this.fireSpreadSpeed = (IntSupplier)MemoizingSuppliers.of(settings.getFireSpreadSpeedFunction(), () -> material);
/*  74 */     this.isFireSource = (BooleanSupplier)MemoizingSuppliers.of(settings.getIsFireSourceFunction(), () -> material);
/*  75 */     this.isBeaconBase = (BooleanSupplier)MemoizingSuppliers.of(settings.getIsBeaconBaseFunction(), () -> material);
/*  76 */     this.translationKey = (Supplier<String>)MemoizingSuppliers.of(() -> {
/*     */           ResourceLocation id = getRegistryName();
/*     */           return "block." + id.getNamespace() + "." + id.getPath().replace('/', '.');
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public IForm getForm() {
/*  84 */     return this.form;
/*     */   }
/*     */ 
/*     */   
/*     */   public IMaterial getMaterial() {
/*  89 */     return this.material;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockRenderLayer getRenderLayer() {
/*  94 */     return BlockRenderLayer.TRANSLUCENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterial(IBlockState state) {
/*  99 */     return this.blockMaterial.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 104 */     return this.mapColor.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundType getSoundType() {
/* 109 */     return this.soundType.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLightOpacity(IBlockState state) {
/* 114 */     return this.lightOpacity.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockState state) {
/* 119 */     return this.lightValue.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
/* 124 */     return (float)this.blockHardness.getAsDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getExplosionResistance(Entity exploder) {
/* 129 */     return (float)this.explosionResistance.getAsDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSlipperiness(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
/* 134 */     return (float)this.slipperiness.getAsDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 139 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
/* 144 */     return this.blocksMovement ? blockState.getBoundingBox(worldIn, pos) : NULL_AABB;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHarvestTool(IBlockState state) {
/* 149 */     return Strings.emptyToNull(this.harvestTool.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHarvestLevel(IBlockState state) {
/* 154 */     return this.harvestLevel.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 159 */     return this.flammability.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 164 */     return this.fireSpreadSpeed.getAsInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
/* 169 */     return this.isFireSource.getAsBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
/* 174 */     return this.isBeaconBase.getAsBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube(IBlockState state) {
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube(IBlockState state) {
/* 184 */     return FULL_BLOCK_AABB.equals(this.boundingBox);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey() {
/* 189 */     return this.translationKey.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedName() {
/* 194 */     return ApiImpl.INSTANCE.currentLocalizer().localizeMaterialForm("block.jaopca." + this.form.getName(), this.material, getTranslationKey());
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\blocks\JAOPCABlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
