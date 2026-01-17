//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.items;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.items.IItemCreator;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.items.IItemModelFunctionCreator;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.client.models.items.JAOPCAItemModelFunctionCreator;
/*    */ 
/*    */ public class ItemFormSettings implements IItemFormSettings {
/*    */   private IItemCreator itemCreator;
/*    */   private ToIntFunction<IMaterial> itemStackLimitFunction;
/*    */   private Predicate<IMaterial> hasEffectFunction;
/*    */   
/*    */   ItemFormSettings() {
/* 20 */     this.itemCreator = JAOPCAItem::new;
/* 21 */     this.itemStackLimitFunction = (material -> Items.AIR.getItemStackLimit());
/* 22 */     this.hasEffectFunction = (material -> material.hasEffect());
/* 23 */     this.displayRarityFunction = (material -> material.getDisplayRarity());
/* 24 */     this.burnTimeFunction = (material -> -1);
/* 25 */     this.itemModelFunctionCreator = (IItemModelFunctionCreator)JAOPCAItemModelFunctionCreator.INSTANCE;
/*    */   }
/*    */   private Function<IMaterial, EnumRarity> displayRarityFunction; private ToIntFunction<IMaterial> burnTimeFunction; private IItemModelFunctionCreator itemModelFunctionCreator;
/*    */   public IFormType getType() {
/* 29 */     return (IFormType)ItemFormType.INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setItemCreator(IItemCreator itemCreator) {
/* 34 */     this.itemCreator = itemCreator;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemCreator getItemCreator() {
/* 40 */     return this.itemCreator;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setItemStackLimitFunction(ToIntFunction<IMaterial> itemStackLimitFunction) {
/* 45 */     this.itemStackLimitFunction = itemStackLimitFunction;
/* 46 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ToIntFunction<IMaterial> getItemStackLimitFunction() {
/* 51 */     return this.itemStackLimitFunction;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setHasEffectFunction(Predicate<IMaterial> hasEffectFunction) {
/* 56 */     this.hasEffectFunction = hasEffectFunction;
/* 57 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<IMaterial> getHasEffectFunction() {
/* 62 */     return this.hasEffectFunction;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> displayRarityFunction) {
/* 67 */     this.displayRarityFunction = displayRarityFunction;
/* 68 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Function<IMaterial, EnumRarity> getDisplayRarityFunction() {
/* 73 */     return this.displayRarityFunction;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setBurnTimeFunction(ToIntFunction<IMaterial> burnTimeFunction) {
/* 78 */     this.burnTimeFunction = burnTimeFunction;
/* 79 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ToIntFunction<IMaterial> getBurnTimeFunction() {
/* 84 */     return this.burnTimeFunction;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings setItemModelFunctionCreator(IItemModelFunctionCreator itemModelFunctionCreator) {
/* 89 */     this.itemModelFunctionCreator = itemModelFunctionCreator;
/* 90 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemModelFunctionCreator getItemModelFunctionCreator() {
/* 95 */     return this.itemModelFunctionCreator;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\items\ItemFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
