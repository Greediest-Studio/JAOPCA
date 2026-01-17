//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.client.models.fluids;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.BlockFluidBase;
/*    */ import thelm.jaopca.api.fluids.IFluidBlockModelMapCreator;
/*    */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class JAOPCAFluidBlockModelMapCreator
/*    */   implements IFluidBlockModelMapCreator
/*    */ {
/* 20 */   public static final JAOPCAFluidBlockModelMapCreator INSTANCE = new JAOPCAFluidBlockModelMapCreator();
/*    */ 
/*    */   
/*    */   public Map<IBlockState, ModelResourceLocation> create(IMaterialFormFluidBlock fluidBlock, IFluidFormSettings settings) {
/* 24 */     ResourceLocation baseModelLocation = getBaseModelLocation(fluidBlock);
/* 25 */     Map<IBlockState, ModelResourceLocation> map = new LinkedHashMap<>();
/* 26 */     for (UnmodifiableIterator<IBlockState> unmodifiableIterator = fluidBlock.toBlock().getBlockState().getValidStates().iterator(); unmodifiableIterator.hasNext(); ) { IBlockState state = unmodifiableIterator.next();
/* 27 */       map.put(state, getModelLocation(baseModelLocation, state)); }
/*    */     
/* 29 */     return map;
/*    */   }
/*    */   
/*    */   public ResourceLocation getBaseModelLocation(IMaterialFormFluidBlock materialFormFluidBlock) {
/* 33 */     Block block = materialFormFluidBlock.toBlock();
/* 34 */     ResourceLocation location = block.getRegistryName();
/* 35 */     ResourceLocation location1 = new ResourceLocation(location.getNamespace(), "blockstates/" + location.getPath() + ".json");
/* 36 */     if (MiscHelper.INSTANCE.hasResource(location1)) {
/* 37 */       return location;
/*    */     }
/*    */     
/* 40 */     return new ResourceLocation(location.getNamespace(), materialFormFluidBlock
/* 41 */         .getMaterial().getModelType() + '/' + materialFormFluidBlock.getForm().getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyString(Map<IProperty<?>, Comparable<?>> values) {
/* 46 */     StringBuilder stringbuilder = new StringBuilder();
/* 47 */     for (Map.Entry<IProperty<?>, Comparable<?>> entry : values.entrySet()) {
/* 48 */       if (stringbuilder.length() != 0) {
/* 49 */         stringbuilder.append(",");
/*    */       }
/* 51 */       IProperty<?> property = entry.getKey();
/* 52 */       stringbuilder.append(property.getName());
/* 53 */       stringbuilder.append("=");
/* 54 */       stringbuilder.append(getPropertyName(property, entry.getValue()));
/*    */     } 
/* 56 */     if (stringbuilder.length() == 0) {
/* 57 */       stringbuilder.append("normal");
/*    */     }
/* 59 */     return stringbuilder.toString();
/*    */   }
/*    */   
/*    */   public <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
/* 63 */     return property.getName(value);
/*    */   }
/*    */   
/*    */   public ModelResourceLocation getModelLocation(ResourceLocation location, IBlockState state) {
/* 67 */     Map<IProperty<?>, Comparable<?>> map = new LinkedHashMap<>((Map<? extends IProperty<?>, ? extends Comparable<?>>)state.getProperties());
/* 68 */     map.remove(BlockFluidBase.LEVEL);
/* 69 */     return new ModelResourceLocation(location, getPropertyString(map));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\models\fluids\JAOPCAFluidBlockModelMapCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
