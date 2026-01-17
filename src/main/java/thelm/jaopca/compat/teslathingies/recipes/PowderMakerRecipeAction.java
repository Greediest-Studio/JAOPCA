//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.teslathingies.recipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.ndrei.teslapoweredthingies.api.IPoweredRecipe;
/*    */ import net.ndrei.teslapoweredthingies.common.IRecipeOutput;
/*    */ import net.ndrei.teslapoweredthingies.common.Output;
/*    */ import net.ndrei.teslapoweredthingies.common.SecondaryOutput;
/*    */ import net.ndrei.teslapoweredthingies.machines.powdermaker.PowderMakerRecipe;
/*    */ import net.ndrei.teslapoweredthingies.machines.powdermaker.PowderMakerRegistry;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class PowderMakerRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 25 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object[] output;
/*    */   
/*    */   public PowderMakerRecipeAction(ResourceLocation key, Object input, int inputCount, Object... output) {
/* 33 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 34 */     this.input = input;
/* 35 */     this.inputCount = inputCount;
/* 36 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/*    */     
/* 46 */     List<ItemStack> ingList = (List<ItemStack>)Arrays.<ItemStack>stream(ing.getMatchingStacks()).map(s -> MiscHelper.INSTANCE.resizeItemStack(s, this.inputCount)).collect(Collectors.toList());
/* 47 */     List<IRecipeOutput> outputs = new ArrayList<>();
/* 48 */     int i = 0;
/* 49 */     while (i < this.output.length) {
/* 50 */       Object out = this.output[i];
/* 51 */       i++;
/* 52 */       Integer count = Integer.valueOf(1);
/* 53 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 54 */         count = (Integer)this.output[i];
/* 55 */         i++;
/*    */       } 
/* 57 */       Float chance = Float.valueOf(1.0F);
/* 58 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 59 */         chance = (Float)this.output[i];
/* 60 */         i++;
/*    */       } 
/* 62 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 63 */       if (stack.isEmpty()) {
/* 64 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 67 */       if (chance.floatValue() == 1.0F) {
/* 68 */         outputs.add(new Output(stack));
/*    */         continue;
/*    */       } 
/* 71 */       outputs.add(new SecondaryOutput(chance.floatValue(), stack));
/*    */     } 
/*    */     
/* 74 */     if (outputs.isEmpty()) {
/* 75 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 77 */     PowderMakerRegistry.INSTANCE.addRecipe((IPoweredRecipe)new PowderMakerRecipe(this.key, ingList, outputs), true);
/* 78 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\teslathingies\recipes\PowderMakerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
