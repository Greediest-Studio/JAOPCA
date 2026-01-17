//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.abyssalcraft.recipes;
/*    */ 
/*    */ import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
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
/*    */ public class CrystallizationRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputCount;
/*    */   public final float experience;
/*    */   
/*    */   public CrystallizationRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float experience) {
/* 29 */     this(key, input, output, outputCount, ItemStack.EMPTY, 0, experience);
/*    */   }
/*    */   
/*    */   public CrystallizationRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float experience) {
/* 33 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 34 */     this.input = input;
/* 35 */     this.output = output;
/* 36 */     this.outputCount = outputCount;
/* 37 */     this.secondOutput = secondOutput;
/* 38 */     this.secondOutputCount = secondOutputCount;
/* 39 */     this.experience = experience;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 44 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 45 */     if (ing == null) {
/* 46 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 48 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 49 */     if (stack.isEmpty()) {
/* 50 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 52 */     ItemStack secondStack = MiscHelper.INSTANCE.getItemStack(this.secondOutput, this.secondOutputCount);
/* 53 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 54 */       AbyssalCraftAPI.addCrystallization(in.copy(), stack, secondStack, this.experience);
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\recipes\CrystallizationRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
