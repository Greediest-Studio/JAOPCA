//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.futurepack.recipes;
/*    */ 
/*    */ import futurepack.api.ItemPredicates;
/*    */ import futurepack.common.crafting.FPZentrifugeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.futurepack.FuturepackHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class ZentrifugeRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object[] output;
/*    */   public final int support;
/*    */   public final int time;
/*    */   
/*    */   public ZentrifugeRecipeAction(ResourceLocation key, Object input, int inputCount, int support, int time, Object... output) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.input = input;
/* 33 */     this.inputCount = inputCount;
/* 34 */     this.output = output;
/* 35 */     this.support = support;
/* 36 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     ItemPredicates ing = FuturepackHelper.INSTANCE.getItemPredicates(this.input, this.inputCount);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 45 */     List<ItemStack> outputs = new ArrayList<>();
/* 46 */     int i = 0;
/* 47 */     while (i < this.output.length) {
/* 48 */       Object out = this.output[i];
/* 49 */       i++;
/* 50 */       Integer count = Integer.valueOf(1);
/* 51 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 52 */         count = (Integer)this.output[i];
/* 53 */         i++;
/*    */       } 
/* 55 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 56 */       if (stack.isEmpty()) {
/* 57 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 60 */       outputs.add(stack);
/*    */     } 
/* 62 */     if (outputs.isEmpty()) {
/* 63 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 65 */     ItemStack[] outs = outputs.<ItemStack>toArray(new ItemStack[outputs.size()]);
/* 66 */     FPZentrifugeManager.addRecipe(ing, outs, this.support).setTime(this.time);
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\futurepack\recipes\ZentrifugeRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
