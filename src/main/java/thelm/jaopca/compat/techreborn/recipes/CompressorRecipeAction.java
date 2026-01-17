//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.techreborn.recipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import reborncore.api.praescriptum.ingredients.input.InputIngredient;
/*    */ import reborncore.api.praescriptum.ingredients.input.ItemStackInputIngredient;
/*    */ import reborncore.api.praescriptum.ingredients.input.OreDictionaryInputIngredient;
/*    */ import techreborn.api.recipe.Recipes;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CompressorRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final int time;
/*    */   public final int energy;
/*    */   
/*    */   public CompressorRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, int time, int energy) {
/* 35 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 36 */     this.input = input;
/* 37 */     this.inputCount = inputCount;
/* 38 */     this.output = output;
/* 39 */     this.outputCount = outputCount;
/* 40 */     this.time = time;
/* 41 */     this.energy = energy;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 46 */     List<InputIngredient<?>> ins = new ArrayList<>();
/* 47 */     if (this.input instanceof String) {
/* 48 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.input)) {
/* 49 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 51 */       ins.add(OreDictionaryInputIngredient.of((String)this.input, this.inputCount));
/*    */     } else {
/*    */       
/* 54 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 55 */       if (ing == null) {
/* 56 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 58 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 59 */         ins.add(ItemStackInputIngredient.of(MiscHelper.INSTANCE.resizeItemStack(is, this.inputCount)));
/*    */       }
/*    */     } 
/* 62 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 63 */     if (stack.isEmpty()) {
/* 64 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 66 */     for (InputIngredient<?> in : ins) {
/* 67 */       Recipes.compressor.createRecipe().withInput(Collections.singleton(in)).withOutput(stack)
/* 68 */         .withOperationDuration(this.time).withEnergyCostPerTick(this.energy).register();
/*    */     }
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\recipes\CompressorRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
