/*     */ package thelm.jaopca.compat.qmd.recipes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import lach_01298.qmd.recipes.QMDRecipes;
/*     */ import nc.recipe.ingredient.ChanceItemIngredient;
/*     */ import nc.recipe.ingredient.EmptyFluidIngredient;
/*     */ import nc.recipe.ingredient.EmptyItemIngredient;
/*     */ import nc.recipe.ingredient.IFluidIngredient;
/*     */ import nc.recipe.ingredient.IItemIngredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ import thelm.jaopca.compat.nuclearcraft.NuclearCraftHelper;
/*     */ 
/*     */ 
/*     */ public class OreLeacherRecipeAction
/*     */   implements IRecipeAction
/*     */ {
/*  23 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public final ResourceLocation key;
/*     */   public final Object itemInput;
/*     */   public final int itemInputCount;
/*     */   public final Object[] fluidInput;
/*     */   public final Object[] output;
/*     */   
/*     */   public OreLeacherRecipeAction(ResourceLocation key, Object itemInput, int itemInputCount, Object[] fluidInput, Object[] output) {
/*  32 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/*  33 */     this.itemInput = itemInput;
/*  34 */     this.itemInputCount = itemInputCount;
/*  35 */     this.fluidInput = fluidInput;
/*  36 */     this.output = output;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  41 */     IItemIngredient itemIng = NuclearCraftHelper.INSTANCE.getItemIngredient(this.itemInput, this.itemInputCount);
/*  42 */     List<IFluidIngredient> fluidIngs = new ArrayList<>(3);
/*  43 */     int i = 0;
/*  44 */     while (i < this.fluidInput.length) {
/*  45 */       Object in = this.fluidInput[i];
/*  46 */       i++;
/*  47 */       Integer amount = Integer.valueOf(1000);
/*  48 */       if (i < this.fluidInput.length && this.fluidInput[i] instanceof Integer) {
/*  49 */         amount = (Integer)this.fluidInput[i];
/*  50 */         i++;
/*     */       } 
/*  52 */       IFluidIngredient ing = NuclearCraftHelper.INSTANCE.getFluidIngredient(in, amount.intValue());
/*  53 */       if (ing instanceof EmptyFluidIngredient) {
/*  54 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*     */       }
/*  56 */       fluidIngs.add(ing);
/*     */     } 
/*  58 */     if (itemIng instanceof EmptyItemIngredient && fluidIngs.isEmpty()) {
/*  59 */       throw new IllegalArgumentException("Empty ingredients in recipe " + this.key + ": " + this.itemInput + Arrays.toString(this.fluidInput));
/*     */     }
/*  61 */     while (fluidIngs.size() < 3) {
/*  62 */       fluidIngs.add(new EmptyFluidIngredient());
/*     */     }
/*  64 */     List<IItemIngredient> outputs = new ArrayList<>(3);
/*  65 */     i = 0;
/*  66 */     while (i < this.output.length) {
/*  67 */       Object out = this.output[i];
/*  68 */       i++;
/*  69 */       Integer maxCount = Integer.valueOf(1);
/*  70 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/*  71 */         maxCount = (Integer)this.output[i];
/*  72 */         i++;
/*     */       } 
/*  74 */       Integer chance = Integer.valueOf(100);
/*  75 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/*  76 */         chance = (Integer)this.output[i];
/*  77 */         i++;
/*     */       } 
/*  79 */       Integer minCount = Integer.valueOf(0);
/*  80 */       if (chance.intValue() != 100 && i < this.output.length && this.output[i] instanceof Integer) {
/*  81 */         minCount = (Integer)this.output[i];
/*  82 */         i++;
/*     */       } 
/*  84 */       IItemIngredient ing = NuclearCraftHelper.INSTANCE.getItemIngredient(out, maxCount.intValue());
/*  85 */       if (ing instanceof EmptyItemIngredient) {
/*  86 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*     */         continue;
/*     */       } 
/*  89 */       outputs.add((chance.intValue() == 100) ? ing : (IItemIngredient)new ChanceItemIngredient(ing, chance.intValue(), minCount.intValue()));
/*     */     } 
/*  91 */     if (outputs.isEmpty()) {
/*  92 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.toString(this.output));
/*     */     }
/*  94 */     while (outputs.size() < 3) {
/*  95 */       outputs.add(new EmptyItemIngredient());
/*     */     }
/*  97 */     QMDRecipes.ore_leacher.addRecipe(new Object[] { itemIng, fluidIngs
/*     */           
/*  99 */           .get(0), fluidIngs.get(1), fluidIngs.get(2), outputs
/* 100 */           .get(0), outputs.get(1), outputs.get(2) });
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\qmd\recipes\OreLeacherRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */