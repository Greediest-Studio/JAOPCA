/*    */ package thelm.jaopca.compat.nuclearcraft;
/*    */ 
/*    */ import nc.recipe.RecipeHelper;
/*    */ import nc.recipe.ingredient.EmptyFluidIngredient;
/*    */ import nc.recipe.ingredient.EmptyItemIngredient;
/*    */ import nc.recipe.ingredient.IFluidIngredient;
/*    */ import nc.recipe.ingredient.IItemIngredient;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuclearCraftHelper
/*    */ {
/* 13 */   public static final NuclearCraftHelper INSTANCE = new NuclearCraftHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public IItemIngredient getItemIngredient(Object obj, int count) {
/* 18 */     if (obj instanceof java.util.function.Supplier) {
/* 19 */       return getItemIngredient(obj, count);
/*    */     }
/* 21 */     IItemIngredient ing = RecipeHelper.buildItemIngredient(obj);
/* 22 */     if (ing != null) {
/* 23 */       ing.setMaxStackSize(count);
/* 24 */       return ing;
/*    */     } 
/* 26 */     return (IItemIngredient)new EmptyItemIngredient();
/*    */   }
/*    */   
/*    */   public IFluidIngredient getFluidIngredient(Object obj, int amount) {
/* 30 */     if (obj instanceof java.util.function.Supplier) {
/* 31 */       return getFluidIngredient(obj, amount);
/*    */     }
/* 33 */     IFluidIngredient ing = RecipeHelper.buildFluidIngredient(obj);
/* 34 */     if (ing != null) {
/* 35 */       ing.setMaxStackSize(amount);
/* 36 */       return ing;
/*    */     } 
/* 38 */     return (IFluidIngredient)new EmptyFluidIngredient();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\nuclearcraft\NuclearCraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */