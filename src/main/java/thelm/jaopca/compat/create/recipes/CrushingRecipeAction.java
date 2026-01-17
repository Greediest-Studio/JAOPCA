//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.create.recipes;
/*    */ 
/*    */ import com.melonstudios.createlegacy.recipe.CrushingRecipes;
/*    */ import com.melonstudios.createlegacy.util.RecipeEntry;
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
/*    */ public class CrushingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] output;
/*    */   public final int time;
/*    */   
/*    */   public CrushingRecipeAction(ResourceLocation key, Object input, int time, Object... output) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.output = output;
/* 33 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 39 */     if (ing == null) {
/* 40 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 42 */     List<RecipeEntry> outputs = new ArrayList<>();
/* 43 */     int i = 0;
/* 44 */     while (i < this.output.length) {
/* 45 */       Object object = this.output[i];
/* 46 */       i++;
/* 47 */       Integer count = Integer.valueOf(1);
/* 48 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 49 */         count = (Integer)this.output[i];
/* 50 */         i++;
/*    */       } 
/* 52 */       Float chance = Float.valueOf(1.0F);
/* 53 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 54 */         chance = (Float)this.output[i];
/* 55 */         i++;
/*    */       } 
/* 57 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 58 */       if (stack.isEmpty()) {
/* 59 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 62 */       outputs.add(RecipeEntry.get(stack, chance.floatValue()));
/*    */     } 
/* 64 */     if (outputs.isEmpty()) {
/* 65 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 67 */     RecipeEntry[] out = outputs.<RecipeEntry>toArray(new RecipeEntry[outputs.size()]);
/* 68 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 69 */       CrushingRecipes.addRecipe(in, this.time, out);
/*    */     }
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\recipes\CrushingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
