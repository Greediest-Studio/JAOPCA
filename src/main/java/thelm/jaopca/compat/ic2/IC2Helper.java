//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.ic2;
/*     */ 
/*     */ import ic2.api.recipe.IRecipeInput;
/*     */ import ic2.api.recipe.IRecipeInputFactory;
/*     */ import ic2.api.recipe.Recipes;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import thelm.jaopca.api.items.IItemProvider;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.BlockCutterRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.CentrifugeRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.CompressorRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.MaceratorRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.MetalFormerRollingRecipeAction;
/*     */ import thelm.jaopca.compat.ic2.recipes.OreWashingRecipeAction;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ 
/*     */ public class IC2Helper
/*     */ {
/*  28 */   public static final IC2Helper INSTANCE = new IC2Helper();
/*     */ 
/*     */ 
/*     */   
/*     */   public IRecipeInput getRecipeInput(Object obj, int amount) {
/*  33 */     IRecipeInputFactory inputFactory = Recipes.inputFactory;
/*  34 */     if (obj instanceof Supplier) {
/*  35 */       return getRecipeInput(((Supplier)obj).get(), amount);
/*     */     }
/*  37 */     if (obj instanceof String && 
/*  38 */       ApiImpl.INSTANCE.getOredict().contains(obj)) {
/*  39 */       return inputFactory.forOreDict((String)obj, amount);
/*     */     }
/*     */     
/*  42 */     if (obj instanceof ItemStack) {
/*  43 */       ItemStack stack = (ItemStack)obj;
/*  44 */       if (!stack.isEmpty()) {
/*  45 */         return inputFactory.forStack(stack, amount);
/*     */       }
/*     */     } 
/*  48 */     if (obj instanceof Item && 
/*  49 */       obj != Items.AIR) {
/*  50 */       return inputFactory.forStack(new ItemStack((Item)obj, amount));
/*     */     }
/*     */     
/*  53 */     if (obj instanceof Block && 
/*  54 */       obj != Blocks.AIR) {
/*  55 */       return inputFactory.forStack(new ItemStack((Block)obj, amount));
/*     */     }
/*     */     
/*  58 */     if (obj instanceof IItemProvider) {
/*  59 */       Item item = ((IItemProvider)obj).asItem();
/*  60 */       if (item != Items.AIR) {
/*  61 */         return inputFactory.forStack(new ItemStack(item, amount));
/*     */       }
/*     */     } 
/*  64 */     if (obj instanceof FluidStack) {
/*  65 */       return inputFactory.forFluidContainer(((FluidStack)obj).getFluid(), amount);
/*     */     }
/*  67 */     if (obj instanceof Fluid) {
/*  68 */       return inputFactory.forFluidContainer((Fluid)obj, amount);
/*     */     }
/*  70 */     if (obj instanceof Ingredient) {
/*  71 */       return inputFactory.forIngredient((Ingredient)obj);
/*     */     }
/*  73 */     if (obj instanceof IRecipeInput) {
/*  74 */       return (IRecipeInput)obj;
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   public boolean registerMaceratorRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/*  80 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MaceratorRecipeAction(key, input, inputCount, output, outputCount));
/*     */   }
/*     */   
/*     */   public boolean registerCentrifugeRecipe(ResourceLocation key, Object input, int inputCount, int minHeat, Object... output) {
/*  84 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CentrifugeRecipeAction(key, input, inputCount, minHeat, output));
/*     */   }
/*     */   
/*     */   public boolean registerOreWashingRecipe(ResourceLocation key, Object input, int inputCount, int waterAmount, Object... output) {
/*  88 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new OreWashingRecipeAction(key, input, inputCount, waterAmount, output));
/*     */   }
/*     */   
/*     */   public boolean registerCompressorRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/*  92 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CompressorRecipeAction(key, input, inputCount, output, outputCount));
/*     */   }
/*     */   
/*     */   public boolean registerMetalFormerRollingRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount) {
/*  96 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MetalFormerRollingRecipeAction(key, input, inputCount, output, outputCount));
/*     */   }
/*     */   
/*     */   public boolean registerBlockCutterRecipe(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, int hardness) {
/* 100 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new BlockCutterRecipeAction(key, input, inputCount, output, outputCount, hardness));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\IC2Helper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
