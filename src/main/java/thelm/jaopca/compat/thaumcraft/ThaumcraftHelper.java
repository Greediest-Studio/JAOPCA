/*    */ package thelm.jaopca.compat.thaumcraft;
/*    */ 
/*    */ import com.google.common.primitives.Ints;
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchEntry;
/*    */ import thaumcraft.api.research.ResearchStage;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.thaumcraft.recipes.CrucibleRecipeAction;
/*    */ import thelm.jaopca.compat.thaumcraft.recipes.SmeltingBonusRecipeAction;
/*    */ import thelm.jaopca.compat.thaumcraft.recipes.SpecialMiningRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThaumcraftHelper
/*    */ {
/* 23 */   public static final ThaumcraftHelper INSTANCE = new ThaumcraftHelper();
/*    */ 
/*    */ 
/*    */   
/*    */   public Aspect getAspect(Object obj) {
/* 28 */     if (obj instanceof java.util.function.Supplier) {
/* 29 */       return getAspect(obj);
/*    */     }
/* 31 */     if (obj instanceof String) {
/* 32 */       return Aspect.getAspect((String)obj);
/*    */     }
/* 34 */     if (obj instanceof Aspect) {
/* 35 */       return (Aspect)obj;
/*    */     }
/* 37 */     return null;
/*    */   }
/*    */   
/*    */   public boolean registerRecipesToResearch(String research, Collection<ResourceLocation> recipeKeys) {
/* 41 */     int stage = 0;
/* 42 */     String[] split = research.split("@(?=\\d*$)");
/* 43 */     if (split.length == 2) {
/* 44 */       stage = ((Integer)Optional.<Integer>ofNullable(Ints.tryParse(split[1])).orElse(Integer.valueOf(0))).intValue();
/*    */     }
/* 46 */     ResearchEntry researchEntry = ResearchCategories.getResearch(research);
/* 47 */     if (researchEntry == null) {
/* 48 */       return false;
/*    */     }
/* 50 */     ResearchStage[] stages = researchEntry.getStages();
/* 51 */     if (stage >= stages.length) {
/* 52 */       return false;
/*    */     }
/* 54 */     ResearchStage researchStage = stages[stage];
/* 55 */     researchStage.setRecipes((ResourceLocation[])ArrayUtils.addAll((Object[])researchStage.getRecipes(), recipeKeys.toArray((Object[])new ResourceLocation[recipeKeys.size()])));
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public boolean registerCrucibleRecipe(ResourceLocation key, String researchReq, Object input, Object[] aspects, Object output, int count) {
/* 60 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrucibleRecipeAction(key, researchReq, input, aspects, output, count));
/*    */   }
/*    */   
/*    */   public boolean registerSmeltingBonusRecipe(ResourceLocation key, Object input, Object output, int count, float chance) {
/* 64 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SmeltingBonusRecipeAction(key, input, output, count, chance));
/*    */   }
/*    */   
/*    */   public boolean registerSpecialMiningRecipe(ResourceLocation key, Object input, Object output, int count, float chance) {
/* 68 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new SpecialMiningRecipeAction(key, input, output, count, chance));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\thaumcraft\ThaumcraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */