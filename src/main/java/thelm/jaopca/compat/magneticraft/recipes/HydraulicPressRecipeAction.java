//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.magneticraft.recipes;
/*    */ 
/*    */ import com.cout970.magneticraft.api.MagneticraftApi;
/*    */ import com.cout970.magneticraft.api.registries.machines.hydraulicpress.HydraulicPressMode;
/*    */ import com.cout970.magneticraft.api.registries.machines.hydraulicpress.IHydraulicPressRecipeManager;
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
/*    */ public class HydraulicPressRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final float time;
/*    */   public final int mode;
/*    */   
/*    */   public HydraulicPressRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, float time, int mode) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.input = input;
/* 33 */     this.inputCount = inputCount;
/* 34 */     this.output = output;
/* 35 */     this.outputCount = outputCount;
/* 36 */     this.time = time;
/* 37 */     this.mode = mode;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 42 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 43 */     if (ing == null) {
/* 44 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 46 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 47 */     if (stack.isEmpty()) {
/* 48 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 50 */     IHydraulicPressRecipeManager manager = MagneticraftApi.getHydraulicPressRecipeManager();
/* 51 */     HydraulicPressMode pressMode = HydraulicPressMode.values()[this.mode];
/* 52 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 53 */       manager.registerRecipe(manager.createRecipe(MiscHelper.INSTANCE
/* 54 */             .resizeItemStack(in, this.inputCount), stack, this.time, pressMode, false));
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\recipes\HydraulicPressRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
