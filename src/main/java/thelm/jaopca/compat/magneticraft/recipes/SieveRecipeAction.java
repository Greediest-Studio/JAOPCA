//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.magneticraft.recipes;
/*    */ 
/*    */ import com.cout970.magneticraft.api.MagneticraftApi;
/*    */ import com.cout970.magneticraft.api.registries.machines.sifter.ISieveRecipeManager;
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
/*    */ public class SieveRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final float outputChance;
/*    */   public final Object secondOutput;
/*    */   public final int secondOutputCount;
/*    */   public final float secondOutputChance;
/*    */   public final Object thirdOutput;
/*    */   public final int thirdOutputCount;
/*    */   public final float thirdOutputChance;
/*    */   public final float time;
/*    */   
/*    */   public SieveRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, float time) {
/* 35 */     this(key, input, output, outputCount, outputChance, ItemStack.EMPTY, 0, 0.0F, ItemStack.EMPTY, 0, 0.0F, time);
/*    */   }
/*    */   
/*    */   public SieveRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, Object secondOutput, int secondOutputCount, float secondOutputChance, float time) {
/* 39 */     this(key, input, output, outputCount, outputChance, secondOutput, secondOutputCount, secondOutputChance, ItemStack.EMPTY, 0, 0.0F, time);
/*    */   }
/*    */   
/*    */   public SieveRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float outputChance, Object secondOutput, int secondOutputCount, float secondOutputChance, Object thirdOutput, int thirdOutputCount, float thirdOutputChance, float time) {
/* 43 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 44 */     this.input = input;
/* 45 */     this.output = output;
/* 46 */     this.outputCount = outputCount;
/* 47 */     this.outputChance = outputChance;
/* 48 */     this.secondOutput = secondOutput;
/* 49 */     this.secondOutputCount = secondOutputCount;
/* 50 */     this.secondOutputChance = secondOutputChance;
/* 51 */     this.thirdOutput = thirdOutput;
/* 52 */     this.thirdOutputCount = thirdOutputCount;
/* 53 */     this.thirdOutputChance = thirdOutputChance;
/* 54 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 59 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 60 */     if (ing == null) {
/* 61 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 63 */     ItemStack stack1 = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 64 */     if (stack1.isEmpty()) {
/* 65 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 67 */     ItemStack stack2 = MiscHelper.INSTANCE.getItemStack(this.secondOutput, this.secondOutputCount);
/* 68 */     ItemStack stack3 = MiscHelper.INSTANCE.getItemStack(this.thirdOutput, this.thirdOutputCount);
/* 69 */     ISieveRecipeManager manager = MagneticraftApi.getSieveRecipeManager();
/* 70 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 71 */       manager.registerRecipe(manager.createRecipe(in.copy(), stack1, this.outputChance, stack2, this.secondOutputChance, stack3, this.thirdOutputChance, this.time, false));
/*    */     }
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\recipes\SieveRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
