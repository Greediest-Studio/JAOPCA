//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.hbm.recipes;
/*    */ 
/*    */ import com.hbm.inventory.RecipesCommon;
/*    */ import com.hbm.inventory.ShredderRecipes;
/*    */ import java.util.Map;
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
/*    */ public class ShredderRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   
/*    */   public ShredderRecipeAction(ResourceLocation key, Object input, Object output, int count) {
/* 28 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 29 */     this.input = input;
/* 30 */     this.output = output;
/* 31 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 36 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 37 */     if (ing == null) {
/* 38 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 40 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 41 */     if (stack.isEmpty()) {
/* 42 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 44 */     Map<RecipesCommon.ComparableStack, ItemStack> map = ShredderRecipes.shredderRecipes;
/* 45 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 46 */       map.put((new RecipesCommon.ComparableStack(in)).makeSingular(), stack);
/*    */     }
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\recipes\ShredderRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
