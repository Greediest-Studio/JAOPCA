//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.client.models.items;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.items.IItemModelFunctionCreator;
/*    */ import thelm.jaopca.api.items.IMaterialFormItem;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class JAOPCAItemModelFunctionCreator
/*    */   implements IItemModelFunctionCreator
/*    */ {
/* 20 */   public static final JAOPCAItemModelFunctionCreator INSTANCE = new JAOPCAItemModelFunctionCreator();
/*    */ 
/*    */   
/*    */   public Pair<Function<ItemStack, ModelResourceLocation>, Set<ModelResourceLocation>> create(IMaterialFormItem item, IItemFormSettings settings) {
/* 24 */     ResourceLocation baseModelLocation = getBaseModelLocation(item);
/* 25 */     ModelResourceLocation modelLocation = new ModelResourceLocation(baseModelLocation, "inventory");
/* 26 */     return Pair.of(s -> modelLocation, Collections.singleton(modelLocation));
/*    */   }
/*    */   
/*    */   public ResourceLocation getBaseModelLocation(IMaterialFormItem materialFormItem) {
/* 30 */     Item item = materialFormItem.toItem();
/* 31 */     ResourceLocation location = item.getRegistryName();
/* 32 */     ResourceLocation location1 = new ResourceLocation(location.getNamespace(), "blockstates/" + location.getPath() + ".json");
/* 33 */     ResourceLocation location2 = new ResourceLocation(location.getNamespace(), "models/item/" + location.getPath() + ".json");
/* 34 */     if (MiscHelper.INSTANCE.hasResource(location1) || MiscHelper.INSTANCE.hasResource(location2)) {
/* 35 */       return location;
/*    */     }
/*    */     
/* 38 */     return new ResourceLocation(location.getNamespace(), materialFormItem
/* 39 */         .getMaterial().getModelType() + '/' + materialFormItem.getForm().getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\models\items\JAOPCAItemModelFunctionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
