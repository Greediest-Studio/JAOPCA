//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.items;
/*    */ 
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.functions.MemoizingSuppliers;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.items.IMaterialFormItem;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ public class JAOPCAItem
/*    */   extends Item
/*    */   implements IMaterialFormItem
/*    */ {
/*    */   private final IForm form;
/*    */   private final IMaterial material;
/*    */   protected final IItemFormSettings settings;
/*    */   protected IntSupplier itemStackLimit;
/*    */   protected BooleanSupplier hasEffect;
/*    */   protected Supplier<EnumRarity> rarity;
/*    */   protected IntSupplier burnTime;
/*    */   protected Supplier<String> translationKey;
/*    */   
/*    */   public JAOPCAItem(IForm form, IMaterial material, IItemFormSettings settings) {
/* 31 */     this.form = form;
/* 32 */     this.material = material;
/* 33 */     this.settings = settings;
/*    */     
/* 35 */     this.itemStackLimit = (IntSupplier)MemoizingSuppliers.of(settings.getItemStackLimitFunction(), () -> material);
/* 36 */     this.hasEffect = (BooleanSupplier)MemoizingSuppliers.of(settings.getHasEffectFunction(), () -> material);
/* 37 */     this.rarity = (Supplier<EnumRarity>)MemoizingSuppliers.of(settings.getDisplayRarityFunction(), () -> material);
/* 38 */     this.burnTime = (IntSupplier)MemoizingSuppliers.of(settings.getBurnTimeFunction(), () -> material);
/* 39 */     this.translationKey = (Supplier<String>)MemoizingSuppliers.of(() -> {
/*    */           ResourceLocation id = getRegistryName();
/*    */           return "item." + id.getNamespace() + "." + id.getPath().replace('/', '.');
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public IForm getForm() {
/* 47 */     return this.form;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterial getMaterial() {
/* 52 */     return this.material;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemStackLimit(ItemStack stack) {
/* 57 */     return this.itemStackLimit.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasEffect(ItemStack stack) {
/* 62 */     return (this.hasEffect.getAsBoolean() || super.hasEffect(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 67 */     return this.rarity.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemBurnTime(ItemStack itemStack) {
/* 72 */     return this.burnTime.getAsInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 77 */     return this.translationKey.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey(ItemStack stack) {
/* 82 */     return getTranslationKey();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getItemStackDisplayName(ItemStack stack) {
/* 87 */     return ApiImpl.INSTANCE.currentLocalizer().localizeMaterialForm("item.jaopca." + getForm().getName(), getMaterial(), getTranslationKey(stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\items\JAOPCAItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
