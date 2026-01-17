//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.FurnaceRecipes;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class SmeltingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final float experience;
/*    */   
/*    */   public SmeltingRecipeAction(ResourceLocation key, Object input, Object output, int count, float experience) {
/* 26 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 27 */     this.input = input;
/* 28 */     this.output = output;
/* 29 */     this.count = count;
/* 30 */     this.experience = experience;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 35 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 36 */     if (ing == null) {
/* 37 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 39 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 40 */     if (stack.isEmpty()) {
/* 41 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 43 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 44 */       FurnaceRecipes.instance().addSmeltingRecipe(in.copy(), stack, this.experience);
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\recipes\SmeltingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
