//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.client.colors;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.vecmath.TexCoord4f;
/*     */ import javax.vecmath.Tuple4f;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*     */ import net.minecraft.client.renderer.color.BlockColors;
/*     */ import net.minecraft.client.renderer.color.IBlockColor;
/*     */ import net.minecraft.client.renderer.color.IItemColor;
/*     */ import net.minecraft.client.renderer.color.ItemColors;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.client.event.ColorHandlerEvent;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*     */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*     */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*     */ import thelm.jaopca.api.items.IMaterialFormItem;
/*     */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*     */ import thelm.jaopca.blocks.BlockFormType;
/*     */ import thelm.jaopca.config.ConfigHandler;
/*     */ import thelm.jaopca.fluids.FluidFormType;
/*     */ import thelm.jaopca.items.ItemFormType;
/*     */ 
/*     */ public class ColorHandler {
/*     */   public static final IBlockColor BLOCK_COLOR;
/*     */   
/*     */   static {
/*  39 */     BLOCK_COLOR = ((state, world, pos, tintIndex) -> {
/*     */         if (tintIndex == 0) {
/*     */           Block block = state.getBlock();
/*     */           
/*     */           if (block instanceof IMaterialForm) {
/*     */             return ((IMaterialForm)block).getMaterial().getColor();
/*     */           }
/*     */         } 
/*     */         return -1;
/*     */       });
/*  49 */     ITEM_COLOR = ((stack, tintIndex) -> {
/*     */         if (tintIndex == 0 || tintIndex == 2) {
/*     */           Item item = stack.getItem();
/*     */           if (item instanceof IMaterialForm)
/*     */             return ((IMaterialForm)item).getMaterial().getColor(); 
/*     */         } 
/*     */         return -1;
/*     */       });
/*     */   }
/*     */   public static final IItemColor ITEM_COLOR;
/*     */   public static void setup(ColorHandlerEvent.Item event) {
/*  60 */     BlockColors blockColors = event.getBlockColors();
/*  61 */     ItemColors itemColors = event.getItemColors();
/*  62 */     for (IMaterialFormBlock block : BlockFormType.getBlocks()) {
/*  63 */       blockColors.registerBlockColorHandler(BLOCK_COLOR, new Block[] { block.toBlock() });
/*     */     } 
/*  65 */     for (IMaterialFormBlockItem blockItem : BlockFormType.getBlockItems()) {
/*  66 */       itemColors.registerItemColorHandler(ITEM_COLOR, new Item[] { (Item)blockItem.toBlockItem() });
/*     */     } 
/*  68 */     for (IMaterialFormItem item : ItemFormType.getItems()) {
/*  69 */       itemColors.registerItemColorHandler(ITEM_COLOR, new Item[] { item.toItem() });
/*     */     } 
/*  71 */     for (IMaterialFormFluidBlock fluidBlock : FluidFormType.getFluidBlocks()) {
/*  72 */       blockColors.registerBlockColorHandler(BLOCK_COLOR, new Block[] { fluidBlock.toBlock() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getAverageColor(String oredictName) {
/*  77 */     Tuple4f color = weightedAverageColor((Iterable<ItemStack>)OreDictionary.getOres(oredictName, false), ConfigHandler.gammaValue);
/*  78 */     return toColorInt(color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Tuple4f weightedAverageColor(Iterable<ItemStack> items, double gammaValue) {
/*  84 */     List<Tuple4f> colors = (List<Tuple4f>)Streams.stream(items).map(stack -> weightedAverageColor(stack, gammaValue)).collect(Collectors.toList());
/*  85 */     return weightedAverageColor(colors, gammaValue);
/*     */   }
/*     */   
/*     */   public static Tuple4f weightedAverageColor(ItemStack stack, double gammaValue) {
/*  89 */     List<BakedQuad> quads = getBakedQuads(stack);
/*  90 */     List<Tuple4f> colors = new ArrayList<>();
/*  91 */     for (BakedQuad quad : quads) {
/*  92 */       Tuple4f color = weightedAverageColor(quad.getSprite(), gammaValue);
/*  93 */       color = tintColor(color, getTint(stack, quad));
/*  94 */       colors.add(color);
/*     */     } 
/*  96 */     return weightedAverageColor(colors, gammaValue);
/*     */   }
/*     */   
/*     */   public static Tuple4f weightedAverageColor(TextureAtlasSprite texture, double gammaValue) {
/* 100 */     int width = texture.getIconWidth();
/* 101 */     int height = texture.getIconHeight();
/* 102 */     int frameCount = texture.getFrameCount();
/* 103 */     List<Tuple4f> colors = new ArrayList<>();
/* 104 */     for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 105 */       for (int x = 0; x < width; x++) {
/* 106 */         for (int y = 0; y < height; y++) {
/* 107 */           int color = texture.getFrameTextureData(frameIndex)[0][y * width + x];
/* 108 */           colors.add(toColorTuple(color));
/*     */         } 
/*     */       } 
/*     */     } 
/* 112 */     return weightedAverageColor(colors, gammaValue);
/*     */   }
/*     */   
/*     */   public static Tuple4f weightedAverageColor(List<Tuple4f> colors, double gammaValue) {
/* 116 */     double totalWeight = 0.0D, r = 0.0D, g = 0.0D, b = 0.0D;
/* 117 */     for (Tuple4f color : colors) {
/* 118 */       totalWeight += color.getW();
/*     */     }
/* 120 */     if (totalWeight <= 0.0D) {
/* 121 */       return (Tuple4f)new TexCoord4f(1.0F, 1.0F, 1.0F, 0.0F);
/*     */     }
/* 123 */     if (gammaValue == 0.0D) {
/* 124 */       r = 1.0D;
/* 125 */       g = 1.0D;
/* 126 */       b = 1.0D;
/* 127 */       for (Tuple4f color : colors) {
/* 128 */         r *= (color.getX() * color.getW());
/* 129 */         g *= (color.getY() * color.getW());
/* 130 */         b *= (color.getZ() * color.getW());
/*     */       } 
/* 132 */       r = Math.pow(r, 1.0D / totalWeight);
/* 133 */       g = Math.pow(g, 1.0D / totalWeight);
/* 134 */       b = Math.pow(b, 1.0D / totalWeight);
/*     */     } else {
/*     */       
/* 137 */       for (Tuple4f color : colors) {
/* 138 */         r += Math.pow(color.getX(), gammaValue) * color.getW();
/* 139 */         g += Math.pow(color.getY(), gammaValue) * color.getW();
/* 140 */         b += Math.pow(color.getZ(), gammaValue) * color.getW();
/*     */       } 
/* 142 */       r = Math.pow(r / totalWeight, 1.0D / gammaValue);
/* 143 */       g = Math.pow(g / totalWeight, 1.0D / gammaValue);
/* 144 */       b = Math.pow(b / totalWeight, 1.0D / gammaValue);
/*     */     } 
/* 146 */     return (Tuple4f)new TexCoord4f(
/* 147 */         (float)MathHelper.clamp(r, 0.0D, 1.0D), 
/* 148 */         (float)MathHelper.clamp(g, 0.0D, 1.0D), 
/* 149 */         (float)MathHelper.clamp(b, 0.0D, 1.0D), 
/* 150 */         (float)MathHelper.clamp(totalWeight / colors.size(), 0.0D, 1.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Tuple4f toColorTuple(int color) {
/* 155 */     return (Tuple4f)new TexCoord4f((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, (color >> 24 & 0xFF) / 255.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Tuple4f tintColor(Tuple4f color, int tint) {
/* 164 */     return (Tuple4f)new TexCoord4f(color
/* 165 */         .getX() * (tint >> 16 & 0xFF) / 255.0F, color
/* 166 */         .getY() * (tint >> 8 & 0xFF) / 255.0F, color
/* 167 */         .getZ() * (tint & 0xFF) / 255.0F, color
/* 168 */         .getW());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int toColorInt(Tuple4f color) {
/* 173 */     int ret = 0;
/* 174 */     ret |= (Math.round(MathHelper.clamp(color.getX() * 255.0F, 0.0F, 255.0F)) & 0xFF) << 16;
/* 175 */     ret |= (Math.round(MathHelper.clamp(color.getY() * 255.0F, 0.0F, 255.0F)) & 0xFF) << 8;
/* 176 */     ret |= Math.round(MathHelper.clamp(color.getZ() * 255.0F, 0.0F, 255.0F)) & 0xFF;
/* 177 */     return ret;
/*     */   }
/*     */   
/*     */   public static List<BakedQuad> getBakedQuads(ItemStack stack) {
/* 181 */     List<BakedQuad> quads = new ArrayList<>();
/* 182 */     IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null);
/* 183 */     model.getQuads(null, null, 0L).stream().filter(quad -> (quad.getFace() == EnumFacing.SOUTH)).forEach(quads::add);
/* 184 */     for (EnumFacing facing : EnumFacing.values()) {
/* 185 */       model.getQuads(null, facing, 0L).stream().filter(quad -> (quad.getFace() == EnumFacing.SOUTH)).forEach(quads::add);
/*     */     }
/* 187 */     return quads;
/*     */   }
/*     */   
/*     */   public static int getTint(ItemStack stack, BakedQuad quad) {
/* 191 */     return Minecraft.getMinecraft().getItemColors().colorMultiplier(stack, quad.getTintIndex());
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\colors\ColorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
