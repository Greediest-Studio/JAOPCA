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
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.DummyPropertiesComponent;
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.IngredientRecipeComponent;
/*    */ import org.cyclops.cyclopscore.recipe.custom.component.IngredientsAndFluidStackRecipeComponent;
/*    */ import org.cyclops.integrateddynamics.block.BlockSqueezer;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class SqueezerRecipeAction implements IRecipeAction {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] itemOutput;
/*    */   public final Object fluidOutput;
/*    */   public final int fluidOutputAmount;
/*    */   
/*    */   public SqueezerRecipeAction(ResourceLocation key, Object input, Object[] itemOutput) {
/* 33 */     this(key, input, itemOutput, null, 0);
/*    */   }
/*    */   
/*    */   public SqueezerRecipeAction(ResourceLocation key, Object input, Object[] itemOutput, Object fluidOutput, int fluidOutputAmount) {
/* 37 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 38 */     this.input = input;
/* 39 */     this.itemOutput = itemOutput;
/* 40 */     this.fluidOutput = fluidOutput;
/* 41 */     this.fluidOutputAmount = fluidOutputAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 46 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 47 */     if (ing == null) {
/* 48 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 50 */     List<IngredientRecipeComponent> itemResults = new ArrayList<>();
/* 51 */     int i = 0;
/* 52 */     while (i < this.itemOutput.length) {
/* 53 */       Object object = this.itemOutput[i];
/* 54 */       i++;
/* 55 */       Integer count = Integer.valueOf(1);
/* 56 */       if (i < this.itemOutput.length && this.itemOutput[i] instanceof Integer) {
/* 57 */         count = (Integer)this.itemOutput[i];
/* 58 */         i++;
/*    */       } 
/* 60 */       Float chance = Float.valueOf(1.0F);
/* 61 */       if (i < this.itemOutput.length && this.itemOutput[i] instanceof Float) {
/* 62 */         chance = (Float)this.itemOutput[i];
/* 63 */         i++;
/*    */       } 
/* 65 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 66 */       if (stack.isEmpty()) {
/* 67 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 70 */       IngredientRecipeComponent component = new IngredientRecipeComponent(stack);
/* 71 */       component.setChance(chance.floatValue());
/* 72 */       itemResults.add(component);
/*    */     } 
/* 74 */     FluidStack fluidStack = MiscHelper.INSTANCE.getFluidStack(this.fluidOutput, this.fluidOutputAmount);
/* 75 */     if (itemResults.isEmpty() && fluidStack == null) {
/* 76 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.itemOutput) + ", " + this.fluidOutput);
/*    */     }
/* 78 */     IngredientRecipeComponent in = new IngredientRecipeComponent(ing);
/* 79 */     IngredientsAndFluidStackRecipeComponent out = new IngredientsAndFluidStackRecipeComponent(itemResults, fluidStack);
/* 80 */     DummyPropertiesComponent props = new DummyPropertiesComponent();
/* 81 */     BlockSqueezer.getInstance().getRecipeRegistry().registerRecipe(this.key.toString(), (IRecipeInput)in, (IRecipeOutput)out, (IRecipeProperties)props);
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\integrateddynamics\recipes\SqueezerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
