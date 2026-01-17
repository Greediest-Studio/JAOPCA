//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*     */ package thelm.jaopca.compat.techreborn.recipes;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import reborncore.api.recipe.IBaseRecipeType;
/*     */ import reborncore.api.recipe.RecipeHandler;
/*     */ import reborncore.common.recipes.OreRecipeInput;
/*     */ import techreborn.api.recipe.machines.ImplosionCompressorRecipe;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ import thelm.jaopca.utils.ApiImpl;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ public class ImplosionCompressorRecipeAction
/*     */   implements IRecipeAction
/*     */ {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public final ResourceLocation key;
/*     */   public final Object[] input;
/*     */   public final Object[] output;
/*     */   public final int time;
/*     */   public final int energy;
/*     */   
/*     */   public ImplosionCompressorRecipeAction(ResourceLocation key, Object[] input, Object[] output, int time, int energy) {
/*  34 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/*  35 */     this.input = input;
/*  36 */     this.output = output;
/*  37 */     this.time = time;
/*  38 */     this.energy = energy;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  43 */     List<List<Object>> inputs = new ArrayList<>();
/*  44 */     int i = 0;
/*  45 */     while (i < this.input.length) {
/*  46 */       Object in = this.input[i];
/*  47 */       i++;
/*  48 */       Integer inc = Integer.valueOf(1);
/*  49 */       if (i < this.input.length && this.input[i] instanceof Integer) {
/*  50 */         inc = (Integer)this.input[i];
/*  51 */         i++;
/*     */       } 
/*  53 */       List<Object> list = new ArrayList();
/*  54 */       if (in instanceof String) {
/*  55 */         if (!ApiImpl.INSTANCE.getOredict().contains(in)) {
/*  56 */           throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*     */         }
/*  58 */         list.add(new OreRecipeInput((String)in, inc.intValue()));
/*     */       } else {
/*     */         
/*  61 */         Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/*  62 */         if (ing == null) {
/*  63 */           throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*     */         }
/*  65 */         for (ItemStack is : ing.getMatchingStacks()) {
/*  66 */           list.add(MiscHelper.INSTANCE.resizeItemStack(is, inc.intValue()));
/*     */         }
/*     */       } 
/*  69 */       inputs.add(list);
/*     */     } 
/*  71 */     if (inputs.isEmpty()) {
/*  72 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + Arrays.deepToString(this.input));
/*     */     }
/*  74 */     List<ItemStack> outputs = new ArrayList<>();
/*  75 */     i = 0;
/*  76 */     while (i < this.output.length) {
/*  77 */       Object out = this.output[i];
/*  78 */       i++;
/*  79 */       Integer count = Integer.valueOf(1);
/*  80 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/*  81 */         count = (Integer)this.output[i];
/*  82 */         i++;
/*     */       } 
/*  84 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/*  85 */       if (stack.isEmpty()) {
/*  86 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*     */         continue;
/*     */       } 
/*  89 */       outputs.add(stack);
/*     */     } 
/*  91 */     if (outputs.isEmpty()) {
/*  92 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*     */     }
/*  94 */     for (List<Object> ins : (Iterable<List<Object>>)Lists.cartesianProduct(inputs)) {
/*  95 */       ImplosionCompressorRecipe recipe = new ImplosionCompressorRecipe(null, null, null, null, this.time, this.energy);
/*  96 */       for (Object in : ins) {
/*  97 */         recipe.addInput(in);
/*     */       }
/*  99 */       for (ItemStack out : outputs) {
/* 100 */         recipe.addOutput(out);
/*     */       }
/* 102 */       RecipeHandler.addRecipe((IBaseRecipeType)recipe);
/*     */     } 
/* 104 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\techreborn\recipes\ImplosionCompressorRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
