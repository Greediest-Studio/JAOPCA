//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.techreborn.recipes;
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
/*    */ import reborncore.api.recipe.IBaseRecipeType;
/*    */ import reborncore.api.recipe.RecipeHandler;
/*    */ import reborncore.common.recipes.OreRecipeInput;
/*    */ import techreborn.api.recipe.machines.IndustrialGrinderRecipe;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class IndustrialGrinderRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object itemInput;
/*    */   public final int itemInputCount;
/*    */   public final Object fluidInput;
/*    */   public final int fluidInputAmount;
/*    */   public final int time;
/*    */   public final int energy;
/*    */   public final Object[] output;
/*    */   
/*    */   public IndustrialGrinderRecipeAction(ResourceLocation key, Object itemInput, int itemInputCount, Object fluidInput, int fluidInputAmount, int time, int energy, Object... output) {
/* 36 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 37 */     this.itemInput = itemInput;
/* 38 */     this.itemInputCount = itemInputCount;
/* 39 */     this.fluidInput = fluidInput;
/* 40 */     this.fluidInputAmount = fluidInputAmount;
/* 41 */     this.time = time;
/* 42 */     this.energy = energy;
/* 43 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 48 */     List<Object> ins = new ArrayList();
/* 49 */     if (this.itemInput instanceof String) {
/* 50 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.itemInput)) {
/* 51 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.itemInput);
/*    */       }
/* 53 */       ins.add(new OreRecipeInput((String)this.itemInput, this.itemInputCount));
/*    */     } else {
/*    */       
/* 56 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.itemInput);
/* 57 */       if (ing == null) {
/* 58 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.itemInput);
/*    */       }
/* 60 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 61 */         ins.add(MiscHelper.INSTANCE.resizeItemStack(is, this.itemInputCount));
/*    */       }
/*    */     } 
/* 64 */     FluidStack fluidStack = MiscHelper.INSTANCE.getFluidStack(this.fluidInput, this.fluidInputAmount);
/* 65 */     if (fluidStack == null) {
/* 66 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.fluidInput);
/*    */     }
/* 68 */     List<ItemStack> outputs = new ArrayList<>();
/* 69 */     int i = 0;
/* 70 */     while (i < this.output.length) {
/* 71 */       Object out = this.output[i];
/* 72 */       i++;
/* 73 */       Integer count = Integer.valueOf(1);
/* 74 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 75 */         count = (Integer)this.output[i];
/* 76 */         i++;
/*    */       } 
/* 78 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 79 */       if (stack.isEmpty()) {
/* 80 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 83 */       outputs.add(stack);
/*    */     } 
/* 85 */     if (outputs.isEmpty()) {
/* 86 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 88 */     for (Object in : ins) {
/* 89 */       IndustrialGrinderRecipe recipe = new IndustrialGrinderRecipe(in, fluidStack, null, null, null, null, this.time, this.energy);
/* 90 */       for (ItemStack out : outputs) {
/* 91 */         recipe.addOutput(out);
/*    */       }
/* 93 */       RecipeHandler.addRecipe((IBaseRecipeType)recipe);
/*    */     } 
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\recipes\IndustrialGrinderRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
