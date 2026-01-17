/*    */ package thelm.jaopca.client.models;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.block.model.ModelBakery;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.ModelLoader;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*    */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*    */ import thelm.jaopca.api.fluids.IMaterialFormFluidBlock;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.items.IMaterialFormItem;
/*    */ import thelm.jaopca.blocks.BlockFormType;
/*    */ import thelm.jaopca.fluids.FluidFormType;
/*    */ import thelm.jaopca.items.ItemFormType;
/*    */ 
/*    */ public class ModelHandler {
/*    */   public static void registerModels() {
/*    */     Iterator<IMaterialFormBlock> iterator;
/* 29 */     for (iterator = BlockFormType.getBlocks().iterator(); iterator.hasNext(); ) { IMaterialFormBlock materialFormBlock = iterator.next();
/* 30 */       Block block = materialFormBlock.toBlock();
/* 31 */       IBlockFormSettings settings = (IBlockFormSettings)materialFormBlock.getForm().getSettings();
/* 32 */       ModelLoader.setCustomStateMapper(block, b -> settings.getBlockModelMapCreator().create(materialFormBlock, settings)); }
/*    */     
/* 34 */     for (iterator = BlockFormType.getBlockItems().iterator(); iterator.hasNext(); ) { IMaterialFormBlockItem materialFormBlockItem = (IMaterialFormBlockItem)iterator.next();
/* 35 */       ItemBlock blockItem = materialFormBlockItem.toBlockItem();
/* 36 */       IBlockFormSettings settings = (IBlockFormSettings)materialFormBlockItem.getForm().getSettings();
/*    */       
/* 38 */       Pair<Function<ItemStack, ModelResourceLocation>, Set<ModelResourceLocation>> funcPair = settings.getBlockItemModelFunctionCreator().create(materialFormBlockItem, settings);
/* 39 */       ModelLoader.setCustomMeshDefinition((Item)blockItem, s -> (ModelResourceLocation)((Function<ItemStack, ModelResourceLocation>)funcPair.getLeft()).apply(s));
/* 40 */       for (ModelResourceLocation mrl : funcPair.getRight()) {
/* 41 */         ModelBakery.registerItemVariants((Item)blockItem, new ResourceLocation[] { (ResourceLocation)mrl });
/*    */       }  }
/*    */     
/* 44 */     for (iterator = ItemFormType.getItems().iterator(); iterator.hasNext(); ) { IMaterialFormItem materialFormItem = (IMaterialFormItem)iterator.next();
/* 45 */       Item item = materialFormItem.toItem();
/* 46 */       IItemFormSettings settings = (IItemFormSettings)materialFormItem.getForm().getSettings();
/*    */       
/* 48 */       Pair<Function<ItemStack, ModelResourceLocation>, Set<ModelResourceLocation>> funcPair = settings.getItemModelFunctionCreator().create(materialFormItem, settings);
/* 49 */       ModelLoader.setCustomMeshDefinition(item, s -> (ModelResourceLocation)((Function<ItemStack, ModelResourceLocation>)funcPair.getLeft()).apply(s));
/* 50 */       for (ModelResourceLocation mrl : funcPair.getRight()) {
/* 51 */         ModelBakery.registerItemVariants(item, new ResourceLocation[] { (ResourceLocation)mrl });
/*    */       }  }
/*    */     
/* 54 */     for (iterator = FluidFormType.getFluidBlocks().iterator(); iterator.hasNext(); ) { IMaterialFormFluidBlock materialFormFluidBlock = (IMaterialFormFluidBlock)iterator.next();
/* 55 */       Block fluidBlock = materialFormFluidBlock.toBlock();
/* 56 */       IFluidFormSettings settings = (IFluidFormSettings)materialFormFluidBlock.getForm().getSettings();
/* 57 */       ModelLoader.setCustomStateMapper(fluidBlock, b -> settings.getFluidBlockModelMapCreator().create(materialFormFluidBlock, settings)); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\models\ModelHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */