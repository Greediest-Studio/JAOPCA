//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.abyssalcraft.recipes;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.shinoow.abyssalcraft.api.APIUtils;
/*    */ import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterializationRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final Object[] input;
/*    */   
/*    */   public MaterializationRecipeAction(ResourceLocation key, Object output, int count, Object... input) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.output = output;
/* 33 */     this.count = count;
/* 34 */     this.input = input;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 39 */     Ingredient out = MiscHelper.INSTANCE.getIngredient(this.output);
/* 40 */     if (out == null) {
/* 41 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 43 */     List<List<ItemStack>> inputs = new ArrayList<>();
/* 44 */     int i = 0;
/* 45 */     while (i < this.input.length) {
/* 46 */       Object in = this.input[i];
/* 47 */       i++;
/* 48 */       Integer inc = Integer.valueOf(1);
/* 49 */       if (i < this.input.length && this.input[i] instanceof Integer) {
/* 50 */         inc = (Integer)this.input[i];
/* 51 */         i++;
/*    */       } 
/* 53 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/* 54 */       if (ing == null || inc.intValue() == 0) {
/* 55 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*    */       }
/* 57 */       List<ItemStack> list = new ArrayList<>();
/* 58 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 59 */         if (APIUtils.isCrystal(is)) {
/* 60 */           list.add(MiscHelper.INSTANCE.resizeItemStack(is, inc.intValue()));
/*    */         }
/*    */       } 
/* 63 */       if (list.isEmpty()) {
/* 64 */         throw new IllegalArgumentException("Non-crystal ingredient in recipe " + this.key + ": " + in);
/*    */       }
/* 66 */       inputs.add(list);
/*    */     } 
/* 68 */     if (inputs.isEmpty()) {
/* 69 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + Arrays.deepToString(this.input));
/*    */     }
/* 71 */     for (List<ItemStack> in : (Iterable<List<ItemStack>>)Lists.cartesianProduct(inputs)) {
/* 72 */       for (ItemStack stack : out.getMatchingStacks()) {
/* 73 */         AbyssalCraftAPI.addMaterialization(MiscHelper.INSTANCE.resizeItemStack(stack, this.count), in.<ItemStack>toArray(new ItemStack[in.size()]));
/*    */       }
/*    */     } 
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\recipes\MaterializationRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
