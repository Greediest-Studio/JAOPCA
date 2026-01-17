//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.client.models.fluids;
/*     */ 
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import javax.vecmath.Tuple4f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ItemOverrideList;
/*     */ import net.minecraft.client.renderer.block.model.ModelRotation;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.client.model.ICustomModelLoader;
/*     */ import net.minecraftforge.client.model.IModel;
/*     */ import net.minecraftforge.client.model.PerspectiveMapWrapper;
/*     */ import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
/*     */ import net.minecraftforge.common.model.IModelState;
/*     */ import net.minecraftforge.common.model.TRSRTransformation;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IUnlistedProperty;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TexturedFluidModel
/*     */   implements IModel
/*     */ {
/*  51 */   public static final TexturedFluidModel WATER = new TexturedFluidModel(new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
/*     */   private final ResourceLocation still;
/*     */   
/*     */   public TexturedFluidModel(ResourceLocation still, ResourceLocation flowing) {
/*  55 */     this.still = still;
/*  56 */     this.flowing = flowing;
/*     */   }
/*     */   private final ResourceLocation flowing;
/*     */   
/*     */   public Collection<ResourceLocation> getTextures() {
/*  61 */     return (Collection<ResourceLocation>)ImmutableSet.of(this.still, this.flowing);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
/*  66 */     ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> map = PerspectiveMapWrapper.getTransforms(state);
/*  67 */     return new Baked(state.apply(Optional.empty()), map, format, bakedTextureGetter.apply(this.still), bakedTextureGetter.apply(this.flowing), Optional.empty());
/*     */   }
/*     */ 
/*     */   
/*     */   public IModelState getDefaultState() {
/*  72 */     return (IModelState)ModelRotation.X0_Y0;
/*     */   }
/*     */   
/*     */   public enum Loader implements ICustomModelLoader {
/*  76 */     INSTANCE;
/*     */ 
/*     */     
/*     */     public void onResourceManagerReload(IResourceManager resourceManager) {}
/*     */ 
/*     */     
/*     */     public boolean accepts(ResourceLocation modelLocation) {
/*  83 */       return (modelLocation.getNamespace().equals("jaopca") && (modelLocation
/*  84 */         .getPath().equals("fluid") || modelLocation
/*  85 */         .getPath().equals("models/block/fluid") || modelLocation
/*  86 */         .getPath().equals("models/item/fluid")));
/*     */     }
/*     */ 
/*     */     
/*     */     public IModel loadModel(ResourceLocation modelLocation) {
/*  91 */       return TexturedFluidModel.WATER;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Baked implements IBakedModel {
/*  96 */     private static final int[] x = new int[] { 0, 0, 1, 1 };
/*  97 */     private static final int[] z = new int[] { 0, 1, 1, 0 };
/*     */     private static final float eps = 0.001F;
/*     */     
/* 100 */     private final LoadingCache<Long, Baked> modelCache = CacheBuilder.newBuilder().maximumSize(500L).build(new CacheLoader<Long, Baked>()
/*     */         {
/*     */           public TexturedFluidModel.Baked load(Long key) throws Exception {
/* 103 */             int opacity = (int)(key.longValue() & 0x3FFL);
/* 104 */             key = Long.valueOf(key.longValue() >>> 10L);
/* 105 */             boolean gas = ((key.longValue() & 0x1L) != 0L);
/* 106 */             key = Long.valueOf(key.longValue() >>> 1L);
/* 107 */             boolean statePresent = ((key.longValue() & 0x1L) != 0L);
/* 108 */             key = Long.valueOf(key.longValue() >>> 1L);
/* 109 */             int[] cornerRound = new int[4];
/* 110 */             for (int i = 0; i < 4; i++) {
/* 111 */               cornerRound[i] = (int)(key.longValue() & 0x3FFL);
/* 112 */               key = Long.valueOf(key.longValue() >>> 10L);
/*     */             } 
/* 114 */             int flowRound = (int)(key.longValue() & 0x7FFL) - 1024;
/* 115 */             return new TexturedFluidModel.Baked(TexturedFluidModel.Baked.this.transformation, TexturedFluidModel.Baked.this.transforms, TexturedFluidModel.Baked.this.format, opacity, TexturedFluidModel.Baked.this.still, TexturedFluidModel.Baked.this.flowing, gas, statePresent, cornerRound, flowRound);
/*     */           }
/*     */         });
/*     */     private final Optional<TRSRTransformation> transformation;
/*     */     private final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;
/*     */     private final VertexFormat format;
/*     */     private final TextureAtlasSprite still;
/*     */     private final TextureAtlasSprite flowing;
/*     */     private final EnumMap<EnumFacing, List<BakedQuad>> faceQuads;
/*     */     
/*     */     public Baked(Optional<TRSRTransformation> transformation, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms, VertexFormat format, TextureAtlasSprite still, TextureAtlasSprite flowing, Optional<IExtendedBlockState> stateOption) {
/* 126 */       this(transformation, transforms, format, 255, still, flowing, false, stateOption.isPresent(), getCorners(stateOption), getFlow(stateOption));
/*     */     }
/*     */     
/*     */     private static int[] getCorners(Optional<IExtendedBlockState> stateOption) {
/* 130 */       int[] cornerRound = { 0, 0, 0, 0 };
/* 131 */       if (stateOption.isPresent()) {
/* 132 */         IExtendedBlockState state = stateOption.get();
/* 133 */         for (int i = 0; i < 4; i++) {
/* 134 */           Float level = (Float)state.getValue((IUnlistedProperty)BlockFluidBase.LEVEL_CORNERS[i]);
/* 135 */           cornerRound[i] = Math.round(((level == null) ? 0.8888889F : level.floatValue()) * 768.0F);
/*     */         } 
/*     */       } 
/* 138 */       return cornerRound;
/*     */     }
/*     */     
/*     */     private static int getFlow(Optional<IExtendedBlockState> stateOption) {
/* 142 */       Float flow = Float.valueOf(-1000.0F);
/* 143 */       if (stateOption.isPresent()) {
/* 144 */         flow = (Float)((IExtendedBlockState)stateOption.get()).getValue((IUnlistedProperty)BlockFluidBase.FLOW_DIRECTION);
/* 145 */         if (flow == null) {
/* 146 */           flow = Float.valueOf(-1000.0F);
/*     */         }
/*     */       } 
/* 149 */       int flowRound = (int)Math.round(Math.toDegrees(flow.floatValue()));
/* 150 */       flowRound = MathHelper.clamp(flowRound, -1000, 1000);
/* 151 */       return flowRound;
/*     */     }
/*     */     
/*     */     public Baked(Optional<TRSRTransformation> transformation, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms, VertexFormat format, int opacity, TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas, boolean statePresent, int[] cornerRound, int flowRound) {
/* 155 */       this.transformation = transformation;
/* 156 */       this.transforms = transforms;
/* 157 */       this.format = format;
/* 158 */       this.still = still;
/* 159 */       this.flowing = flowing;
/* 160 */       this.faceQuads = Maps.newEnumMap(EnumFacing.class);
/* 161 */       for (EnumFacing side : EnumFacing.values()) {
/* 162 */         this.faceQuads.put(side, ImmutableList.of());
/*     */       }
/* 164 */       if (statePresent) {
/* 165 */         float[] y = new float[4];
/* 166 */         for (int i = 0; i < 4; i++) {
/* 167 */           if (gas) {
/* 168 */             y[i] = 1.0F - cornerRound[i] / 768.0F;
/*     */           } else {
/*     */             
/* 171 */             y[i] = cornerRound[i] / 768.0F;
/*     */           } 
/*     */         } 
/* 174 */         float flow = (float)Math.toRadians(flowRound);
/*     */         
/* 176 */         TextureAtlasSprite topSprite = flowing;
/* 177 */         float scale = 4.0F;
/* 178 */         if (flow < -17.0F) {
/* 179 */           flow = 0.0F;
/* 180 */           scale = 8.0F;
/* 181 */           topSprite = still;
/*     */         } 
/* 183 */         float c = MathHelper.cos(flow) * scale;
/* 184 */         float s = MathHelper.sin(flow) * scale;
/* 185 */         EnumFacing side = gas ? EnumFacing.DOWN : EnumFacing.UP;
/*     */         
/* 187 */         ImmutableList.Builder<BakedQuad> topFaceBuilder = ImmutableList.builder();
/* 188 */         for (int k = 0; k < 2; k++) {
/* 189 */           UnpackedBakedQuad.Builder builder1 = new UnpackedBakedQuad.Builder(format);
/* 190 */           builder1.setQuadOrientation(side);
/* 191 */           builder1.setTexture(topSprite);
/* 192 */           builder1.setQuadTint(0); int m;
/* 193 */           for (m = gas ? 3 : 0; m != (gas ? -1 : 4); m += gas ? -1 : 1) {
/* 194 */             int l = k * 3 + (1 - 2 * k) * m;
/* 195 */             putVertex(builder1, side, x[l], y[l], z[l], topSprite
/*     */ 
/*     */                 
/* 198 */                 .getInterpolatedU((8.0F + c * (x[l] * 2 - 1) + s * (z[l] * 2 - 1))), topSprite
/* 199 */                 .getInterpolatedV((8.0F + c * (x[(l + 1) % 4] * 2 - 1) + s * (z[(l + 1) % 4] * 2 - 1))), opacity);
/*     */           } 
/* 201 */           topFaceBuilder.add(builder1.build());
/*     */         } 
/* 203 */         this.faceQuads.put(side, topFaceBuilder.build());
/*     */         
/* 205 */         side = side.getOpposite();
/* 206 */         UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
/* 207 */         builder.setQuadOrientation(side);
/* 208 */         builder.setTexture(still);
/* 209 */         builder.setQuadTint(0); int j;
/* 210 */         for (j = gas ? 3 : 0; j != (gas ? -1 : 4); j += gas ? -1 : 1) {
/* 211 */           putVertex(builder, side, z[j], (gas ? true : false), x[j], still
/*     */ 
/*     */               
/* 214 */               .getInterpolatedU((z[j] * 16)), still
/* 215 */               .getInterpolatedV((x[j] * 16)), opacity);
/*     */         }
/* 217 */         this.faceQuads.put(side, ImmutableList.of(builder.build()));
/*     */         
/* 219 */         for (j = 0; j < 4; j++) {
/* 220 */           side = EnumFacing.byHorizontalIndex((5 - j) % 4);
/* 221 */           BakedQuad[] q = new BakedQuad[2];
/*     */           
/* 223 */           for (int m = 0; m < 2; m++) {
/* 224 */             builder = new UnpackedBakedQuad.Builder(format);
/* 225 */             builder.setQuadOrientation(side);
/* 226 */             builder.setTexture(flowing);
/* 227 */             builder.setQuadTint(0);
/* 228 */             for (int n = 0; n < 4; n++) {
/* 229 */               int l = m * 3 + (1 - 2 * m) * n;
/* 230 */               float yl = z[l] * y[(j + x[l]) % 4];
/* 231 */               if (gas && z[l] == 0) {
/* 232 */                 yl = 1.0F;
/*     */               }
/* 234 */               putVertex(builder, side, x[(j + x[l]) % 4], yl, z[(j + x[l]) % 4], flowing
/*     */ 
/*     */                   
/* 237 */                   .getInterpolatedU((x[l] * 8)), flowing
/* 238 */                   .getInterpolatedV(((gas ? yl : (1.0F - yl)) * 8.0F)), opacity);
/*     */             } 
/* 240 */             q[m] = (BakedQuad)builder.build();
/*     */           } 
/* 242 */           this.faceQuads.put(side, ImmutableList.of(q[0], q[1]));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 247 */         UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
/* 248 */         builder.setQuadOrientation(EnumFacing.UP);
/* 249 */         builder.setTexture(still);
/* 250 */         builder.setQuadTint(0);
/* 251 */         for (int i = 0; i < 4; i++) {
/* 252 */           putVertex(builder, EnumFacing.UP, z[i], x[i], 0.0F, still
/*     */ 
/*     */               
/* 255 */               .getInterpolatedU((z[i] * 16)), still
/* 256 */               .getInterpolatedV((x[i] * 16)), opacity);
/*     */         }
/* 258 */         this.faceQuads.put(EnumFacing.SOUTH, ImmutableList.of(builder.build()));
/*     */       } 
/*     */     }
/*     */     
/*     */     private void putVertex(UnpackedBakedQuad.Builder builder, EnumFacing side, float x, float y, float z, float u, float v, int opacity) {
/* 263 */       for (int e = 0; e < this.format.getElementCount(); e++) {
/* 264 */         float[] data; switch (this.format.getElement(e).getUsage()) {
/*     */           case POSITION:
/* 266 */             data = new float[] { x - side.getDirectionVec().getX() * 0.001F, y, z - side.getDirectionVec().getZ() * 0.001F, 1.0F };
/* 267 */             if (this.transformation.isPresent() && this.transformation.get() != TRSRTransformation.identity()) {
/* 268 */               Vector4f vec = new Vector4f(data);
/* 269 */               ((TRSRTransformation)this.transformation.get()).getMatrix().transform((Tuple4f)vec);
/* 270 */               vec.get(data);
/*     */             } 
/* 272 */             builder.put(e, data);
/*     */             break;
/*     */           case COLOR:
/* 275 */             builder.put(e, new float[] { 1.0F, 1.0F, 1.0F, opacity / 255.0F }); break;
/*     */           case UV:
/* 277 */             if (this.format.getElement(e).getIndex() == 0) {
/* 278 */               builder.put(e, new float[] { u, v, 0.0F, 1.0F });
/*     */               break;
/*     */             } 
/*     */           case NORMAL:
/* 282 */             builder.put(e, new float[] { side.getXOffset(), side.getYOffset(), side.getZOffset(), 0.0F });
/*     */             break;
/*     */           default:
/* 285 */             builder.put(e, new float[0]);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAmbientOcclusion() {
/* 293 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isGui3d() {
/* 298 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isBuiltInRenderer() {
/* 303 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public TextureAtlasSprite getParticleTexture() {
/* 308 */       return this.still;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
/* 313 */       Baked model = this;
/* 314 */       if (state instanceof IExtendedBlockState) {
/* 315 */         IExtendedBlockState exState = (IExtendedBlockState)state;
/* 316 */         int[] cornerRound = getCorners(Optional.of(exState));
/* 317 */         int flowRound = getFlow(Optional.of(exState));
/* 318 */         long key = (flowRound + 1024);
/* 319 */         for (int i = 3; i >= 0; i--) {
/* 320 */           key <<= 10L;
/* 321 */           key |= cornerRound[i];
/*     */         } 
/* 323 */         key <<= 1L;
/* 324 */         key |= 0x1L;
/* 325 */         Fluid fluid = ((IFluidBlock)state.getBlock()).getFluid();
/* 326 */         key <<= 1L;
/* 327 */         key |= fluid.isGaseous() ? 1L : 0L;
/* 328 */         key <<= 10L;
/* 329 */         key |= (fluid.getColor() >>> 24);
/* 330 */         model = (Baked)this.modelCache.getUnchecked(Long.valueOf(key));
/*     */       } 
/* 332 */       if (side == null)
/* 333 */         return (List<BakedQuad>)ImmutableList.of(); 
/* 334 */       return model.faceQuads.get(side);
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemOverrideList getOverrides() {
/* 339 */       return ItemOverrideList.NONE;
/*     */     }
/*     */ 
/*     */     
/*     */     public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type) {
/* 344 */       return PerspectiveMapWrapper.handlePerspective(this, this.transforms, type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IModel retexture(ImmutableMap<String, String> textures) {
/* 350 */     ResourceLocation still = this.still;
/* 351 */     ResourceLocation flowing = this.flowing;
/* 352 */     if (textures.containsKey("still")) {
/* 353 */       still = new ResourceLocation((String)textures.get("still"));
/*     */     }
/* 355 */     if (textures.containsKey("flowing")) {
/* 356 */       flowing = new ResourceLocation((String)textures.get("flowing"));
/*     */     }
/* 358 */     return new TexturedFluidModel(still, flowing);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\models\fluids\TexturedFluidModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
