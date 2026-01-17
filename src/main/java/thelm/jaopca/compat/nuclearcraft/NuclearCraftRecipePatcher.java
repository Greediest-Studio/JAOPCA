/*    */ package thelm.jaopca.compat.nuclearcraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import nc.recipe.NCRecipes;
/*    */ import nc.recipe.RecipeHelper;
/*    */ import nc.recipe.processor.IngotFormerRecipes;
/*    */ import nc.recipe.processor.MelterRecipes;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ 
/*    */ 
/*    */ public class NuclearCraftRecipePatcher
/*    */ {
/* 18 */   private static final Set<String> GAS_BLACKLIST = new HashSet<>(Arrays.asList(new String[] { "Hydrogen", "Helium", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Chlorine", "Argon", "Krypton", "Xenon", "Radon" }));
/*    */   
/*    */   private static final double TIME = 1.0D;
/*    */   
/*    */   private static final double POWER = 1.0D;
/*    */ 
/*    */   
/*    */   public static void registerRecipes() {
/* 26 */     MelterRecipes melter = NCRecipes.melter;
/* 27 */     IngotFormerRecipes ingotFormer = NCRecipes.ingot_former;
/*    */     
/* 29 */     for (IMaterial material : MaterialHandler.getMaterials()) {
/* 30 */       String matName = material.getName();
/* 31 */       MaterialType type = material.getType();
/* 32 */       if (GAS_BLACKLIST.contains(matName) || (
/* 33 */         type != MaterialType.INGOT && type != MaterialType.INGOT_PLAIN))
/*    */         continue; 
/* 35 */       String ingotOredict = "ingot" + matName;
/* 36 */       String blockOredict = "block" + matName;
/* 37 */       String nuggetOredict = "nugget" + matName;
/* 38 */       String dustOredict = "dust" + matName;
/* 39 */       String oreOredict = "ore" + matName;
/* 40 */       String fluidName = matName.toLowerCase();
/*    */ 
/*    */       
/* 43 */       melter.addRecipe(new Object[] {
/* 44 */             RecipeHelper.buildItemIngredient(ingotOredict), 
/* 45 */             RecipeHelper.buildFluidIngredient(fluidName + "@144"), 
/* 46 */             Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */           });
/* 48 */       melter.addRecipe(new Object[] {
/* 49 */             RecipeHelper.buildItemIngredient(blockOredict), 
/* 50 */             RecipeHelper.buildFluidIngredient(fluidName + "@1296"), 
/* 51 */             Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */           });
/* 53 */       melter.addRecipe(new Object[] {
/* 54 */             RecipeHelper.buildItemIngredient(nuggetOredict), 
/* 55 */             RecipeHelper.buildFluidIngredient(fluidName + "@16"), 
/* 56 */             Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */           });
/* 58 */       melter.addRecipe(new Object[] {
/* 59 */             RecipeHelper.buildItemIngredient(dustOredict), 
/* 60 */             RecipeHelper.buildFluidIngredient(fluidName + "@144"), 
/* 61 */             Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */           });
/* 63 */       if (OreDictionary.doesOreNameExist(oreOredict)) {
/* 64 */         melter.addRecipe(new Object[] {
/* 65 */               RecipeHelper.buildItemIngredient(oreOredict), 
/* 66 */               RecipeHelper.buildFluidIngredient(fluidName + "@288"), 
/* 67 */               Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */             });
/*    */       }
/*    */ 
/*    */       
/* 72 */       ingotFormer.addRecipe(new Object[] {
/* 73 */             RecipeHelper.buildFluidIngredient(fluidName + "@144"), 
/* 74 */             RecipeHelper.buildItemIngredient(ingotOredict), 
/* 75 */             Double.valueOf(1.0D), Double.valueOf(1.0D)
/*    */           });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\nuclearcraft\NuclearCraftRecipePatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */