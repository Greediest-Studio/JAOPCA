//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.hbm.recipes;
/*    */ 
/*    */ import com.hbm.inventory.AnvilRecipes;
/*    */ import com.hbm.inventory.RecipesCommon;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.hbm.HBMHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnvilConstructionRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object[] input;
/*    */   public final Object[] output;
/*    */   public final int tier;
/*    */   
/*    */   public AnvilConstructionRecipeAction(ResourceLocation key, Object[] input, Object[] output, int tier) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.output = output;
/* 33 */     this.tier = tier;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     List<RecipesCommon.AStack> inputs = new ArrayList<>();
/* 39 */     int i = 0;
/* 40 */     while (i < this.input.length) {
/* 41 */       Object in = this.input[i];
/* 42 */       i++;
/* 43 */       Integer count = Integer.valueOf(1);
/* 44 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 45 */         count = (Integer)this.output[i];
/* 46 */         i++;
/*    */       } 
/* 48 */       RecipesCommon.AStack ing = HBMHelper.INSTANCE.getAStack(in, count.intValue());
/* 49 */       if (ing == null) {
/* 50 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*    */       }
/* 52 */       inputs.add(ing);
/*    */     } 
/* 54 */     if (inputs.isEmpty()) {
/* 55 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + Arrays.deepToString(this.input));
/*    */     }
/* 57 */     List<AnvilRecipes.AnvilOutput> outputs = new ArrayList<>();
/* 58 */     i = 0;
/* 59 */     while (i < this.output.length) {
/* 60 */       Object out = this.output[i];
/* 61 */       i++;
/* 62 */       Integer count = Integer.valueOf(1);
/* 63 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 64 */         count = (Integer)this.output[i];
/* 65 */         i++;
/*    */       } 
/* 67 */       Float chance = Float.valueOf(1.0F);
/* 68 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 69 */         chance = (Float)this.output[i];
/* 70 */         i++;
/*    */       } 
/* 72 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 73 */       if (stack.isEmpty()) {
/* 74 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 77 */       outputs.add(new AnvilRecipes.AnvilOutput(stack, chance.floatValue()));
/*    */     } 
/* 79 */     if (outputs.isEmpty()) {
/* 80 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 82 */     AnvilRecipes.AnvilOutput[] outs = outputs.<AnvilRecipes.AnvilOutput>toArray(new AnvilRecipes.AnvilOutput[outputs.size()]);
/* 83 */     RecipesCommon.AStack[] ins = inputs.<RecipesCommon.AStack>toArray(new RecipesCommon.AStack[inputs.size()]);
/* 84 */     AnvilRecipes.AnvilConstructionRecipe recipe = (new AnvilRecipes.AnvilConstructionRecipe(ins, outs)).setTier(this.tier);
/* 85 */     if (ins.length == 1 && outs.length == 1) {
/* 86 */       recipe.setOverlay(AnvilRecipes.OverlayType.SMITHING);
/*    */     }
/* 88 */     else if (ins.length == 1) {
/* 89 */       recipe.setOverlay(AnvilRecipes.OverlayType.RECYCLING);
/*    */     }
/* 91 */     else if (outs.length == 1) {
/* 92 */       recipe.setOverlay(AnvilRecipes.OverlayType.CONSTRUCTION);
/*    */     } 
/* 94 */     AnvilRecipes.getConstruction().add(recipe);
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\recipes\AnvilConstructionRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
