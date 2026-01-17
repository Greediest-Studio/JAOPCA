//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.integrateddynamics.recipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.cyclops.cyclopscore.recipe.custom.api.IRecipeInput;
/*    */ import org.cyclops.cyclopscore.recipe.custom.api.IRecipeOutput;
/*    */ import org.cyclops.cyclopscore.recipe.custom.api.IRecipeProperties;
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.DurationRecipeProperties;
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.IngredientRecipeComponent;
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.IngredientsAndFluidStackRecipeComponent;
/*    */ import org.cyclops.integrateddynamics.block.BlockMechanicalSqueezer;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class MechanicalSqueezerRecipeAction implements IRecipeAction {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] itemOutput;
/*    */   public final Object fluidOutput;
/*    */   public final int fluidOutputAmount;
/*    */   public final int time;
/*    */   
/*    */   public MechanicalSqueezerRecipeAction(ResourceLocation key, Object input, Object[] itemOutput, int time) {
/* 34 */     this(key, input, itemOutput, null, 0, time);
/*    */   }
/*    */   
/*    */   public MechanicalSqueezerRecipeAction(ResourceLocation key, Object input, Object[] itemOutput, Object fluidOutput, int fluidOutputAmount, int time) {
/* 38 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 39 */     this.input = input;
/* 40 */     this.itemOutput = itemOutput;
/* 41 */     this.fluidOutput = fluidOutput;
/* 42 */     this.fluidOutputAmount = fluidOutputAmount;
/* 43 */     this.time = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 48 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 49 */     if (ing == null) {
/* 50 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 52 */     List<IngredientRecipeComponent> itemResults = new ArrayList<>();
/* 53 */     int i = 0;
/* 54 */     while (i < this.itemOutput.length) {
/* 55 */       Object object = this.itemOutput[i];
/* 56 */       i++;
/* 57 */       Integer count = Integer.valueOf(1);
/* 58 */       if (i < this.itemOutput.length && this.itemOutput[i] instanceof Integer) {
/* 59 */         count = (Integer)this.itemOutput[i];
/* 60 */         i++;
/*    */       } 
/* 62 */       Float chance = Float.valueOf(1.0F);
/* 63 */       if (i < this.itemOutput.length && this.itemOutput[i] instanceof Float) {
/* 64 */         chance = (Float)this.itemOutput[i];
/* 65 */         i++;
/*    */       } 
/* 67 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 68 */       if (stack.isEmpty()) {
/* 69 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 72 */       IngredientRecipeComponent component = new IngredientRecipeComponent(stack);
/* 73 */       component.setChance(chance.floatValue());
/* 74 */       itemResults.add(component);
/*    */     } 
/* 76 */     FluidStack fluidStack = MiscHelper.INSTANCE.getFluidStack(this.fluidOutput, this.fluidOutputAmount);
/* 77 */     if (itemResults.isEmpty() && fluidStack == null) {
/* 78 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.itemOutput) + ", " + this.fluidOutput);
/*    */     }
/* 80 */     IngredientRecipeComponent in = new IngredientRecipeComponent(ing);
/* 81 */     IngredientsAndFluidStackRecipeComponent out = new IngredientsAndFluidStackRecipeComponent(itemResults, fluidStack);
/* 82 */     DurationRecipeProperties props = new DurationRecipeProperties(this.time);
/* 83 */     BlockMechanicalSqueezer.getInstance().getRecipeRegistry().registerRecipe(this.key.toString(), (IRecipeInput)in, (IRecipeOutput)out, (IRecipeProperties)props);
/* 84 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\integrateddynamics\recipes\MechanicalSqueezerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
