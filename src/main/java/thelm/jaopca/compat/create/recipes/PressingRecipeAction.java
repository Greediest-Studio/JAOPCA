//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.create.recipes;
/*    */ 
/*    */ import com.melonstudios.createlegacy.recipe.PressingRecipes;
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
/*    */ public class PressingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public PressingRecipeAction(ResourceLocation key, Object input, Object output, int outputCount) {
/* 26 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 27 */     this.input = input;
/* 28 */     this.output = output;
/* 29 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 34 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 35 */     if (ing == null) {
/* 36 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 38 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 39 */     if (stack.isEmpty()) {
/* 40 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 42 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 43 */       PressingRecipes.addRecipe(in, stack);
/*    */     }
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\create\recipes\PressingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
