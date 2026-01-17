//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.enderio.recipes;
/*    */ 
/*    */ import com.enderio.core.common.util.stackable.Things;
/*    */ import crazypants.enderio.base.recipe.IRecipeInput;
/*    */ import crazypants.enderio.base.recipe.Recipe;
/*    */ import crazypants.enderio.base.recipe.RecipeBonusType;
/*    */ import crazypants.enderio.base.recipe.RecipeLevel;
/*    */ import crazypants.enderio.base.recipe.RecipeOutput;
/*    */ import crazypants.enderio.base.recipe.ThingsRecipeInput;
/*    */ import crazypants.enderio.base.recipe.sagmill.SagMillRecipeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.enderio.EnderIOHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SagMillRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 29 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int energy;
/*    */   public final RecipeBonusType bonusType;
/*    */   public final RecipeLevel level;
/*    */   public final Object[] output;
/*    */   
/*    */   public SagMillRecipeAction(ResourceLocation key, Object input, int energy, String bonusType, String level, Object... output) {
/* 39 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 40 */     this.input = input;
/* 41 */     this.energy = energy;
/* 42 */     this.bonusType = Objects.<RecipeBonusType>requireNonNull(RecipeBonusType.valueOf(bonusType.toUpperCase(Locale.US)));
/* 43 */     this.level = Objects.<RecipeLevel>requireNonNull(RecipeLevel.valueOf(level.toUpperCase(Locale.US)));
/* 44 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 49 */     Things ing = EnderIOHelper.INSTANCE.getThings(this.input);
/* 50 */     if (ing.isEmpty()) {
/* 51 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 53 */     List<RecipeOutput> outputs = new ArrayList<>();
/* 54 */     int i = 0;
/* 55 */     while (i < this.output.length) {
/* 56 */       Object object = this.output[i];
/* 57 */       i++;
/* 58 */       Integer count = Integer.valueOf(1);
/* 59 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 60 */         count = (Integer)this.output[i];
/* 61 */         i++;
/*    */       } 
/* 63 */       Float chance = Float.valueOf(1.0F);
/* 64 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 65 */         chance = (Float)this.output[i];
/* 66 */         i++;
/*    */       } 
/* 68 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 69 */       if (stack.isEmpty()) {
/* 70 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 73 */       outputs.add(new RecipeOutput(stack, chance.floatValue()));
/*    */     } 
/* 75 */     if (outputs.isEmpty()) {
/* 76 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 78 */     ThingsRecipeInput thingsRecipeInput = new ThingsRecipeInput(ing);
/* 79 */     RecipeOutput[] out = outputs.<RecipeOutput>toArray(new RecipeOutput[outputs.size()]);
/* 80 */     SagMillRecipeManager.getInstance().addRecipe(new Recipe((IRecipeInput)thingsRecipeInput, this.energy, this.bonusType, this.level, out));
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\enderio\recipes\SagMillRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
