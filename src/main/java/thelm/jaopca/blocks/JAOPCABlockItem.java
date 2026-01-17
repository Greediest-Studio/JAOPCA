//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.blocks;
/*    */ 
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlock;
/*    */ import thelm.jaopca.api.blocks.IMaterialFormBlockItem;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.functions.MemoizingSuppliers;
/*    */ import thelm.jaopca.api.materialforms.IMaterialForm;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class JAOPCABlockItem
/*    */   extends ItemBlock
/*    */   implements IMaterialFormBlockItem
/*    */ {
/*    */   protected final IBlockFormSettings settings;
/*    */   protected IntSupplier itemStackLimit;
/*    */   protected BooleanSupplier hasEffect;
/*    */   protected Supplier<EnumRarity> rarity;
/*    */   protected IntSupplier burnTime;
/*    */   
/*    */   public JAOPCABlockItem(IMaterialFormBlock block, IBlockFormSettings settings) {
/* 29 */     super(block.toBlock());
/* 30 */     this.settings = settings;
/*    */     
/* 32 */     this.itemStackLimit = (IntSupplier)MemoizingSuppliers.of(settings.getItemStackLimitFunction(), block::getMaterial);
/* 33 */     this.hasEffect = (BooleanSupplier)MemoizingSuppliers.of(settings.getHasEffectFunction(), block::getMaterial);
/* 34 */     this.rarity = (Supplier<EnumRarity>)MemoizingSuppliers.of(settings.getDisplayRarityFunction(), block::getMaterial);
/* 35 */     this.burnTime = (IntSupplier)MemoizingSuppliers.of(settings.getBurnTimeFunction(), block::getMaterial);
/*    */   }
/*    */ 
/*    */   
/*    */   public IForm getForm() {
/* 40 */     return ((IMaterialForm)getBlock()).getForm();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterial getMaterial() {
/* 45 */     return ((IMaterialForm)getBlock()).getMaterial();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemStackLimit(ItemStack stack) {
/* 50 */     return this.itemStackLimit.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasEffect(ItemStack stack) {
/* 55 */     return (this.hasEffect.getAsBoolean() || super.hasEffect(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 60 */     return this.rarity.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemBurnTime(ItemStack itemStack) {
/* 65 */     return this.burnTime.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getItemStackDisplayName(ItemStack stack) {
/* 70 */     return ApiImpl.INSTANCE.currentLocalizer().localizeMaterialForm("block.jaopca." + getForm().getName(), getMaterial(), getTranslationKey(stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\blocks\JAOPCABlockItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
