//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.advancedrocketry.recipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ import zmaster587.advancedRocketry.block.BlockSmallPlatePress;
/*    */ import zmaster587.libVulpes.recipe.RecipesMachine;
/*    */ 
/*    */ 
/*    */ public class SmallPlatePressRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   
/*    */   public SmallPlatePressRecipeAction(ResourceLocation key, Object input, Object output, int outputCount) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.output = output;
/* 32 */     this.outputCount = outputCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 37 */     List<Object> ins = new ArrayList();
/* 38 */     if (this.input instanceof String) {
/* 39 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.input)) {
/* 40 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 42 */       ins.add(this.input);
/*    */     } else {
/*    */       
/* 45 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 46 */       if (ing == null) {
/* 47 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 49 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 50 */         ins.add(is.copy());
/*    */       }
/*    */     } 
/* 53 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 54 */     if (stack.isEmpty()) {
/* 55 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 57 */     for (Object in : ins) {
/* 58 */       RecipesMachine.getInstance().addRecipe(BlockSmallPlatePress.class, stack, 0, 0, new Object[] { in });
/*    */     } 
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\advancedrocketry\recipes\SmallPlatePressRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
