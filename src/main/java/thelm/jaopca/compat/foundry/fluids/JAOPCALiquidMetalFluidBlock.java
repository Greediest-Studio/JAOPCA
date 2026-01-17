//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.foundry.fluids;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*     */ import thelm.jaopca.api.fluids.IMaterialFormFluid;
/*     */ import thelm.jaopca.fluids.JAOPCAFluidBlock;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JAOPCALiquidMetalFluidBlock
/*     */   extends JAOPCAFluidBlock
/*     */ {
/*  36 */   private Optional<IBlockState> solidState = Optional.empty();
/*     */   
/*     */   public JAOPCALiquidMetalFluidBlock(IMaterialFormFluid fluid, IFluidFormSettings settings) {
/*  39 */     super(fluid, settings);
/*     */   }
/*     */   
/*     */   public void tryHarden(World world, BlockPos pos, IBlockState state) {
/*  43 */     if (isSourceBlock((IBlockAccess)world, pos)) {
/*  44 */       if (!this.solidState.isPresent()) {
/*  45 */         MiscHelper miscHelper = MiscHelper.INSTANCE;
/*     */ 
/*     */         
/*  48 */         List<ItemStack> list = (List<ItemStack>)OreDictionary.getOres(miscHelper.getOredictName("block", getMaterial().getName()), false).stream().filter(is -> (ForgeRegistries.BLOCKS.getValue(is.getItem().getRegistryName()) != Blocks.AIR)).collect(Collectors.toList());
/*  49 */         ItemStack stack = miscHelper.getPreferredItemStack(list, 1);
/*  50 */         this.solidState = Optional.ofNullable(getBlockStateFromItemStack(stack));
/*     */       } 
/*  52 */       if (!this.solidState.isPresent()) {
/*     */         return;
/*     */       }
/*  55 */       for (EnumFacing facing : EnumFacing.VALUES) {
/*  56 */         if (world.getBlockState(pos.offset(facing)).getMaterial() == Material.WATER) {
/*  57 */           world.setBlockState(pos, this.solidState.get());
/*  58 */           world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F, false);
/*  59 */           for (int i = 0; i < 8; i++) {
/*  60 */             world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + 1.2D, pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           }
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public IBlockState getBlockStateFromItemStack(ItemStack stack) {
/*  69 */     Block block = (Block)ForgeRegistries.BLOCKS.getValue(stack.getItem().getRegistryName());
/*  70 */     if (block != Blocks.AIR) {
/*  71 */       int meta = stack.getMetadata();
/*  72 */       for (UnmodifiableIterator<IBlockState> unmodifiableIterator = block.getBlockState().getValidStates().iterator(); unmodifiableIterator.hasNext(); ) { IBlockState state = unmodifiableIterator.next();
/*  73 */         if (state != null && block.damageDropped(state) == meta) {
/*  74 */           return state;
/*     */         } }
/*     */     
/*     */     } 
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDisplace(IBlockAccess world, BlockPos pos) {
/*  83 */     return (!world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean displaceIfPossible(World world, BlockPos pos) {
/*  88 */     return (!world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
/*  93 */     super.neighborChanged(state, world, pos, block, fromPos);
/*  94 */     tryHarden(world, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
/*  99 */     super.onBlockAdded(world, pos, state);
/* 100 */     tryHarden(world, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 105 */     if (entity instanceof net.minecraft.entity.EntityLivingBase) {
/* 106 */       entity.motionX *= 0.5D;
/* 107 */       entity.motionZ *= 0.5D;
/*     */     } 
/* 109 */     if (!entity.isImmuneToFire()) {
/* 110 */       if (!(entity instanceof net.minecraft.entity.item.EntityItem)) {
/* 111 */         entity.attackEntityFrom(DamageSource.LAVA, 4.0F);
/*     */       }
/* 113 */       entity.setFire(15);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
/* 120 */     if (this.temperature < 1200) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 126 */     if (world.getBlockState(pos.up()).getMaterial() == Material.AIR && !world.getBlockState(pos.up()).isOpaqueCube()) {
/* 127 */       if (rand.nextInt(100) == 0) {
/* 128 */         double dx = (pos.getX() + rand.nextFloat());
/* 129 */         double dy = pos.getY() + (state.getBoundingBox((IBlockAccess)world, pos)).maxY;
/* 130 */         double dz = (pos.getZ() + rand.nextFloat());
/* 131 */         world.spawnParticle(EnumParticleTypes.LAVA, dx, dy, dz, 0.0D, 0.0D, 0.0D, new int[0]);
/* 132 */         world.playSound(dx, dy, dz, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
/*     */       } 
/* 134 */       if (rand.nextInt(200) == 0) {
/* 135 */         world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
/*     */       }
/*     */     } 
/* 138 */     BlockPos down = pos.down();
/* 139 */     if (rand.nextInt(10) == 0 && world.getBlockState(down).isSideSolid((IBlockAccess)world, down, EnumFacing.UP) && !world.getBlockState(down).getMaterial().blocksMovement()) {
/* 140 */       double dx = (pos.getX() + rand.nextFloat());
/* 141 */       double dy = pos.getY() - 1.05D;
/* 142 */       double dz = (pos.getZ() + rand.nextFloat());
/* 143 */       world.spawnParticle(EnumParticleTypes.DRIP_LAVA, dx, dy, dz, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\fluids\JAOPCALiquidMetalFluidBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
