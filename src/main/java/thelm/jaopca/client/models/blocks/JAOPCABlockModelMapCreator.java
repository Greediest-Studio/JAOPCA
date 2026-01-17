//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.client.models.blocks;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*    */ import thelm.jaopca.api.blocks.IBlockModelMapCreator;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class JAOPCABlockModelMapCreator
/*    */   implements IBlockModelMapCreator
/*    */ {
/* 19 */   public static final JAOPCABlockModelMapCreator INSTANCE = new JAOPCABlockModelMapCreator();
/*    */ 
/*    */   
/*    */   public Map<IBlockState, ModelResourceLocation> create(IMaterialFormBlock block, IBlockFormSettings settings) {
/* 23 */     ResourceLocation baseModelLocation = getBaseModelLocation(block);
/* 24 */     Map<IBlockState, ModelResourceLocation> map = new LinkedHashMap<>();
/* 25 */     for (UnmodifiableIterator<IBlockState> unmodifiableIterator = block.toBlock().getBlockState().getValidStates().iterator(); unmodifiableIterator.hasNext(); ) { IBlockState state = unmodifiableIterator.next();
/* 26 */       map.put(state, getModelLocation(baseModelLocation, state)); }
/*    */     
/* 28 */     return map;
/*    */   }
/*    */   
/*    */   public ResourceLocation getBaseModelLocation(IMaterialFormBlock materialFormBlock) {
/* 32 */     Block block = materialFormBlock.toBlock();
/* 33 */     ResourceLocation location = block.getRegistryName();
/* 34 */     ResourceLocation location1 = new ResourceLocation(location.getNamespace(), "blockstates/" + location.getPath() + ".json");
/* 35 */     if (MiscHelper.INSTANCE.hasResource(location1)) {
/* 36 */       return location;
/*    */     }
/*    */     
/* 39 */     return new ResourceLocation(location.getNamespace(), materialFormBlock
/* 40 */         .getMaterial().getModelType() + '/' + materialFormBlock.getForm().getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyString(Map<IProperty<?>, Comparable<?>> values) {
/* 45 */     StringBuilder stringbuilder = new StringBuilder();
/* 46 */     for (Map.Entry<IProperty<?>, Comparable<?>> entry : values.entrySet()) {
/* 47 */       if (stringbuilder.length() != 0) {
/* 48 */         stringbuilder.append(",");
/*    */       }
/* 50 */       IProperty<?> property = entry.getKey();
/* 51 */       stringbuilder.append(property.getName());
/* 52 */       stringbuilder.append("=");
/* 53 */       stringbuilder.append(getPropertyName(property, entry.getValue()));
/*    */     } 
/* 55 */     if (stringbuilder.length() == 0) {
/* 56 */       stringbuilder.append("normal");
/*    */     }
/* 58 */     return stringbuilder.toString();
/*    */   }
/*    */   
/*    */   public <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
/* 62 */     return property.getName(value);
/*    */   }
/*    */   
/*    */   public ModelResourceLocation getModelLocation(ResourceLocation location, IBlockState state) {
/* 66 */     return new ModelResourceLocation(location, getPropertyString(new LinkedHashMap<>((Map<? extends IProperty<?>, ? extends Comparable<?>>)state.getProperties())));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\models\blocks\JAOPCABlockModelMapCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
