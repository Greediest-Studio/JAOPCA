//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.create.recipes;
/*    */ 
/*    */ import com.melonstudios.createlegacy.recipe.WashingRecipes;
/*    */ import com.melonstudios.createlegacy.util.RecipeEntry;
/*    */ import com.melonstudios.createlegacy.util.SimpleTuple;
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
/*    */ public class WashingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] output;
/*    */   
/*    */   public WashingRecipeAction(ResourceLocation key, Object input, Object... output) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 36 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 37 */     if (ing == null) {
/* 38 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 40 */     List<RecipeEntry> outputs = new ArrayList<>();
/* 41 */     int i = 0;
/* 42 */     while (i < this.output.length) {
/* 43 */       Object object = this.output[i];
/* 44 */       i++;
/* 45 */       Integer count = Integer.valueOf(1);
/* 46 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 47 */         count = (Integer)this.output[i];
/* 48 */         i++;
/*    */       } 
/* 50 */       Float chance = Float.valueOf(1.0F);
/* 51 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 52 */         chance = (Float)this.output[i];
/* 53 */         i++;
/*    */       } 
/* 55 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 56 */       if (stack.isEmpty()) {
/* 57 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 60 */       outputs.add(RecipeEntry.get(stack, chance.floatValue()));
/*    */     } 
/* 62 */     if (outputs.isEmpty()) {
/* 63 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 65 */     RecipeEntry[] out = outputs.<RecipeEntry>toArray(new RecipeEntry[outputs.size()]);
/* 66 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 67 */       WashingRecipes.addRecipe(in, (SimpleTuple[])out);
/*    */     }
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\recipes\WashingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
