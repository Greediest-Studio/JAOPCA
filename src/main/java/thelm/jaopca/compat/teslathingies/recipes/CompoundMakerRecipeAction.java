//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.teslathingies.recipes;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.ndrei.teslapoweredthingies.api.IPoweredRecipe;
/*     */ import net.ndrei.teslapoweredthingies.machines.compoundmaker.CompoundMakerRecipe;
/*     */ import net.ndrei.teslapoweredthingies.machines.compoundmaker.CompoundMakerRegistry;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ public class CompoundMakerRecipeAction
/*     */   implements IRecipeAction
/*     */ {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public final ResourceLocation key;
/*     */   public final Object[] top;
/*     */   public final Object left;
/*     */   public final int leftAmount;
/*     */   public final Object[] bottom;
/*     */   public final Object right;
/*     */   public final int rightAmount;
/*     */   public final Object output;
/*     */   public final int outputCount;
/*     */   
/*     */   public CompoundMakerRecipeAction(ResourceLocation key, Object[] top, Object left, int leftAmount, Object output, int outputCount) {
/*  38 */     this(key, top, left, leftAmount, ObjectArrays.EMPTY_ARRAY, null, 0, output, outputCount);
/*     */   }
/*     */   
/*     */   public CompoundMakerRecipeAction(ResourceLocation key, Object[] top, Object[] bottom, Object output, int outputCount) {
/*  42 */     this(key, top, null, 0, bottom, null, 0, output, outputCount);
/*     */   }
/*     */   
/*     */   public CompoundMakerRecipeAction(ResourceLocation key, Object[] top, Object left, int leftAmount, Object[] bottom, Object output, int outputCount) {
/*  46 */     this(key, top, left, leftAmount, bottom, null, 0, output, outputCount);
/*     */   }
/*     */   
/*     */   public CompoundMakerRecipeAction(ResourceLocation key, Object[] top, Object left, int leftAmount, Object[] bottom, Object right, int rightAmount, Object output, int outputCount) {
/*  50 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/*  51 */     this.top = top;
/*  52 */     this.left = left;
/*  53 */     this.leftAmount = leftAmount;
/*  54 */     this.bottom = bottom;
/*  55 */     this.right = right;
/*  56 */     this.rightAmount = rightAmount;
/*  57 */     this.output = output;
/*  58 */     this.outputCount = outputCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  63 */     List<List<ItemStack>> topInputs = new ArrayList<>();
/*  64 */     int i = 0;
/*  65 */     while (i < this.top.length) {
/*  66 */       Object in = this.top[i];
/*  67 */       i++;
/*  68 */       Integer inc = Integer.valueOf(1);
/*  69 */       if (i < this.top.length && this.top[i] instanceof Integer) {
/*  70 */         inc = (Integer)this.top[i];
/*  71 */         i++;
/*     */       } 
/*  73 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/*  74 */       if (ing == null || inc.intValue() == 0) {
/*  75 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*     */       }
/*  77 */       List<ItemStack> list = new ArrayList<>();
/*  78 */       for (ItemStack is : ing.getMatchingStacks()) {
/*  79 */         list.add(MiscHelper.INSTANCE.resizeItemStack(is, inc.intValue()));
/*     */       }
/*  81 */       topInputs.add(list);
/*     */     } 
/*  83 */     FluidStack leftStack = MiscHelper.INSTANCE.getFluidStack(this.left, this.leftAmount);
/*  84 */     List<List<ItemStack>> bottomInputs = new ArrayList<>();
/*  85 */     i = 0;
/*  86 */     while (i < this.bottom.length) {
/*  87 */       Object in = this.bottom[i];
/*  88 */       i++;
/*  89 */       Integer inc = Integer.valueOf(1);
/*  90 */       if (i < this.bottom.length && this.bottom[i] instanceof Integer) {
/*  91 */         inc = (Integer)this.bottom[i];
/*  92 */         i++;
/*     */       } 
/*  94 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/*  95 */       if (ing == null || inc.intValue() == 0) {
/*  96 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*     */       }
/*  98 */       List<ItemStack> list = new ArrayList<>();
/*  99 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 100 */         list.add(MiscHelper.INSTANCE.resizeItemStack(is, inc.intValue()));
/*     */       }
/* 102 */       bottomInputs.add(list);
/*     */     } 
/* 104 */     FluidStack rightStack = MiscHelper.INSTANCE.getFluidStack(this.right, this.rightAmount);
/* 105 */     if (topInputs.isEmpty() && this.left == null && bottomInputs.isEmpty() && this.right == null) {
/* 106 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + Arrays.deepToString(this.top) + ", " + this.left + ", " + Arrays.deepToString(this.bottom) + ", " + this.right);
/*     */     }
/* 108 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 109 */     if (stack.isEmpty()) {
/* 110 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*     */     }
/* 112 */     for (List<ItemStack> topInput : (Iterable<List<ItemStack>>)Lists.cartesianProduct(topInputs)) {
/* 113 */       for (List<ItemStack> bottomInput : (Iterable<List<ItemStack>>)Lists.cartesianProduct(bottomInputs)) {
/* 114 */         CompoundMakerRegistry.INSTANCE.addRecipe((IPoweredRecipe)new CompoundMakerRecipe(this.key, stack, leftStack, topInput
/* 115 */               .<ItemStack>toArray(new ItemStack[topInput.size()]), rightStack, bottomInput
/* 116 */               .<ItemStack>toArray(new ItemStack[bottomInput.size()])), true);
/*     */       }
/*     */     } 
/* 119 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\teslathingies\recipes\CompoundMakerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
