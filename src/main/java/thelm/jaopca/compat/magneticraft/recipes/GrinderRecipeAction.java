//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.magneticraft.recipes;
/*    */ 
/*    */ import com.cout970.magneticraft.api.MagneticraftApi;
/*    */ import com.cout970.magneticraft.api.registries.machines.grinder.IGrinderRecipeManager;
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
/*    */   public final float time;
/*    */   
/*    */   public GrinderRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float time) {
/* 31 */     this(key, input, output, outputCount, ItemStack.EMPTY, 0, 0.0F, time);
/*    */   }
/*    */   
/*    */   public GrinderRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float secondOutputChance, float time) {
/* 35 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 36 */     this.input = input;
/* 37 */     this.output = output;
/* 38 */     this.outputCount = outputCount;
/* 39 */     this.secondOutput = secondOutput;
/* 40 */     this.secondOutputCount = secondOutputCount;
/* 41 */     this.secondOutputChance = secondOutputChance;
/* 42 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 47 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 48 */     if (ing == null) {
/* 49 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 51 */     ItemStack stack1 = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 52 */     if (stack1.isEmpty()) {
/* 53 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 55 */     ItemStack stack2 = MiscHelper.INSTANCE.getItemStack(this.secondOutput, this.secondOutputCount);
/* 56 */     IGrinderRecipeManager manager = MagneticraftApi.getGrinderRecipeManager();
/* 57 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 58 */       manager.registerRecipe(manager.createRecipe(in.copy(), stack1, stack2, this.secondOutputChance, this.time, false));
/*    */     }
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\recipes\GrinderRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
