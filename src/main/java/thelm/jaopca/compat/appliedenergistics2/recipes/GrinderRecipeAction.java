//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.appliedenergistics2.recipes;
/*    */ 
/*    */ import appeng.api.AEApi;
/*    */ import appeng.api.features.IGrinderRecipeBuilder;
/*    */ import appeng.api.features.IGrinderRegistry;
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
/*    */ public class GrinderRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputCount;
/*    */   public final float secondOutputChance;
/*    */   public final Object thirdOutput;
/*    */   public final int thirdOutputCount;
/*    */   public final float thirdOutputChance;
/*    */   public final int turns;
/*    */   
/*    */   public GrinderRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, int turns) {
/* 34 */     this(key, input, output, outputCount, ItemStack.EMPTY, 0, 0.0F, ItemStack.EMPTY, 0, 0.0F, turns);
/*    */   }
/*    */   
/*    */   public GrinderRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, int turns) {
/* 38 */     this(key, input, output, outputCount, secondOutput, secondOutputCount, secondOutputChance, ItemStack.EMPTY, 0, 0.0F, turns);
/*    */   }
/*    */   
/*    */   public GrinderRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, Object thirdOutput, int thirdOutputCount, float thirdOutputChance, int turns) {
/* 42 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 43 */     this.input = input;
/* 44 */     this.output = output;
/* 45 */     this.outputCount = outputCount;
/* 46 */     this.secondOutput = secondOutput;
/* 47 */     this.secondOutputCount = secondOutputCount;
/* 48 */     this.secondOutputChance = secondOutputChance;
/* 49 */     this.thirdOutput = thirdOutput;
/* 50 */     this.thirdOutputCount = thirdOutputCount;
/* 51 */     this.thirdOutputChance = thirdOutputChance;
/* 52 */     this.turns = turns;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 57 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 58 */     if (ing == null) {
/* 59 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 61 */     ItemStack stack1 = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 62 */     if (stack1.isEmpty()) {
/* 63 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 65 */     ItemStack stack2 = MiscHelper.INSTANCE.getItemStack(this.secondOutput, this.secondOutputCount);
/* 66 */     ItemStack stack3 = MiscHelper.INSTANCE.getItemStack(this.thirdOutput, this.thirdOutputCount);
/* 67 */     IGrinderRegistry registry = AEApi.instance().registries().grinder();
/* 68 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 69 */       IGrinderRecipeBuilder builder = registry.builder().withInput(in).withOutput(stack1).withTurns(this.turns);
/* 70 */       if (!stack2.isEmpty()) {
/* 71 */         builder.withFirstOptional(stack2, this.secondOutputChance);
/*    */       }
/* 73 */       if (!stack3.isEmpty()) {
/* 74 */         builder.withSecondOptional(stack3, this.thirdOutputChance);
/*    */       }
/* 76 */       registry.addRecipe(builder.build());
/*    */     } 
/* 78 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\appliedenergistics2\recipes\GrinderRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
