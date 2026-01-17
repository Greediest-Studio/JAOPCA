//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.fluids;
/*    */ 
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.DoubleSupplier;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluid;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.functions.MemoizingSuppliers;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ 
/*    */ public class JAOPCAFluidBlock
/*    */   extends BlockFluidClassic
/*    */   implements IMaterialFormFluidBlock
/*    */ {
/*    */   private final IMaterialFormFluid fluid;
/*    */   protected final IFluidFormSettings settings;
/*    */   protected Supplier<Material> blockMaterial;
/*    */   protected Supplier<MapColor> mapColor;
/*    */   protected DoubleSupplier blockHardness;
/*    */   protected DoubleSupplier explosionResistance;
/*    */   protected IntSupplier flammability;
/*    */   protected IntSupplier fireSpreadSpeed;
/*    */   protected BooleanSupplier isFireSource;
/*    */   
/*    */   public JAOPCAFluidBlock(IMaterialFormFluid fluid, IFluidFormSettings settings) {
/* 38 */     super(fluid.toFluid(), Material.WATER);
/* 39 */     this.fluid = fluid;
/* 40 */     this.settings = settings;
/*    */     
/* 42 */     setQuantaPerBlock(settings.getMaxLevelFunction().applyAsInt(fluid.getMaterial()));
/*    */     
/* 44 */     this.blockMaterial = (Supplier<Material>)MemoizingSuppliers.of(settings.getMaterialFunction(), fluid::getMaterial);
/* 45 */     this.mapColor = (Supplier<MapColor>)MemoizingSuppliers.of(settings.getMapColorFunction(), fluid::getMaterial);
/* 46 */     this.blockHardness = (DoubleSupplier)MemoizingSuppliers.of(settings.getBlockHardnessFunction(), fluid::getMaterial);
/* 47 */     this.explosionResistance = (DoubleSupplier)MemoizingSuppliers.of(settings.getExplosionResistanceFunction(), fluid::getMaterial);
/* 48 */     this.flammability = (IntSupplier)MemoizingSuppliers.of(settings.getFlammabilityFunction(), fluid::getMaterial);
/* 49 */     this.fireSpreadSpeed = (IntSupplier)MemoizingSuppliers.of(settings.getFireSpreadSpeedFunction(), fluid::getMaterial);
/* 50 */     this.isFireSource = (BooleanSupplier)MemoizingSuppliers.of(settings.getIsFireSourceFunction(), fluid::getMaterial);
/*    */   }
/*    */ 
/*    */   
/*    */   public IForm getForm() {
/* 55 */     return this.fluid.getForm();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterial getMaterial() {
/* 60 */     return this.fluid.getMaterial();
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getMaterial(IBlockState state) {
/* 65 */     return this.blockMaterial.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 70 */     return this.mapColor.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
/* 75 */     return (float)this.blockHardness.getAsDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getExplosionResistance(Entity exploder) {
/* 80 */     return (float)this.explosionResistance.getAsDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 85 */     return this.flammability.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 90 */     return this.fireSpreadSpeed.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
/* 95 */     return this.isFireSource.getAsBoolean();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\fluids\JAOPCAFluidBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
