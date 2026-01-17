/*     */ package thelm.jaopca.recipes;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSortedSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.LoaderState;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thelm.jaopca.api.recipes.IRecipeAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeHandler
/*     */ {
/*  21 */   private static final Logger LOGGER = LogManager.getLogger();
/*  22 */   private static final TreeMap<ResourceLocation, IRecipeAction> EARLY_RECIPE_ACTIONS = new TreeMap<>();
/*  23 */   private static final TreeMap<ResourceLocation, IRecipeAction> RECIPE_ACTIONS = new TreeMap<>();
/*  24 */   private static final TreeMap<ResourceLocation, IRecipeAction> LATE_RECIPE_ACTIONS = new TreeMap<>();
/*  25 */   private static final TreeSet<ResourceLocation> EXECUTED_RECIPE_ACTIONS = new TreeSet<>();
/*     */   
/*     */   public static boolean registerEarlyRecipe(ResourceLocation key, IRecipeAction recipeAction) {
/*  28 */     if (Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
/*  29 */       return false;
/*     */     }
/*  31 */     Objects.requireNonNull(key);
/*  32 */     Objects.requireNonNull(recipeAction);
/*  33 */     return (EARLY_RECIPE_ACTIONS.putIfAbsent(key, recipeAction) == null);
/*     */   }
/*     */   
/*     */   public static boolean registerRecipe(ResourceLocation key, IRecipeAction recipeAction) {
/*  37 */     if (Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
/*  38 */       return false;
/*     */     }
/*  40 */     Objects.requireNonNull(key);
/*  41 */     Objects.requireNonNull(recipeAction);
/*  42 */     return (RECIPE_ACTIONS.putIfAbsent(key, recipeAction) == null);
/*     */   }
/*     */   
/*     */   public static boolean registerLateRecipe(ResourceLocation key, IRecipeAction recipeAction) {
/*  46 */     if (Loader.instance().hasReachedState(LoaderState.AVAILABLE)) {
/*  47 */       return false;
/*     */     }
/*  49 */     Objects.requireNonNull(key);
/*  50 */     Objects.requireNonNull(recipeAction);
/*  51 */     return (LATE_RECIPE_ACTIONS.putIfAbsent(key, recipeAction) == null);
/*     */   }
/*     */   
/*     */   public static Set<ResourceLocation> getRegisteredRecipes() {
/*  55 */     return (Set<ResourceLocation>)ImmutableSortedSet.naturalOrder()
/*  56 */       .addAll(EARLY_RECIPE_ACTIONS.keySet())
/*  57 */       .addAll(RECIPE_ACTIONS.keySet())
/*  58 */       .addAll(LATE_RECIPE_ACTIONS.keySet())
/*  59 */       .addAll(EXECUTED_RECIPE_ACTIONS).build();
/*     */   }
/*     */   
/*     */   public static void registerEarlyRecipes() {
/*  63 */     AtomicInteger recipeCount = new AtomicInteger(0);
/*  64 */     EARLY_RECIPE_ACTIONS.forEach((key, recipeAction) -> {
/*     */           try {
/*     */             if (recipeAction.register()) {
/*     */               LOGGER.debug("Registered early recipe with key {}", key);
/*     */               
/*     */               recipeCount.incrementAndGet();
/*     */             } 
/*  71 */           } catch (IllegalArgumentException e) {
/*     */             LOGGER.warn("Early recipe with ID {} received invalid arguments: {}", key, e.getMessage());
/*     */             
/*     */             return;
/*  75 */           } catch (Throwable e) {
/*     */             LOGGER.error("Early recipe with ID {} errored", key, e);
/*     */             return;
/*     */           } 
/*     */         });
/*  80 */     EXECUTED_RECIPE_ACTIONS.addAll(EARLY_RECIPE_ACTIONS.keySet());
/*  81 */     EARLY_RECIPE_ACTIONS.clear();
/*  82 */     LOGGER.info("Registered {} early recipes", Integer.valueOf(recipeCount.get()));
/*     */   }
/*     */   
/*     */   public static void registerRecipes() {
/*  86 */     AtomicInteger recipeCount = new AtomicInteger(0);
/*  87 */     RECIPE_ACTIONS.forEach((key, recipeAction) -> {
/*     */           try {
/*     */             if (recipeAction.register()) {
/*     */               LOGGER.debug("Registered recipe with key {}", key);
/*     */               
/*     */               recipeCount.incrementAndGet();
/*     */             } 
/*  94 */           } catch (IllegalArgumentException e) {
/*     */             LOGGER.warn("Recipe with ID {} received invalid arguments: {}", key, e.getMessage());
/*     */             
/*     */             return;
/*  98 */           } catch (Throwable e) {
/*     */             LOGGER.error("Recipe with ID {} errored", key, e);
/*     */             return;
/*     */           } 
/*     */         });
/* 103 */     EXECUTED_RECIPE_ACTIONS.addAll(RECIPE_ACTIONS.keySet());
/* 104 */     RECIPE_ACTIONS.clear();
/* 105 */     LOGGER.info("Registered {} recipes", Integer.valueOf(recipeCount.get()));
/*     */   }
/*     */   
/*     */   public static void registerLateRecipes() {
/* 109 */     AtomicInteger recipeCount = new AtomicInteger(0);
/* 110 */     LATE_RECIPE_ACTIONS.forEach((key, recipeAction) -> {
/*     */           try {
/*     */             if (recipeAction.register()) {
/*     */               LOGGER.debug("Registered late recipe with key {}", key);
/*     */               
/*     */               recipeCount.incrementAndGet();
/*     */             } 
/* 117 */           } catch (IllegalArgumentException e) {
/*     */             LOGGER.warn("Late recipe with ID {} received invalid arguments: {}", key, e.getMessage());
/*     */             
/*     */             return;
/* 121 */           } catch (Throwable e) {
/*     */             LOGGER.error("Late recipe with ID {} errored", key, e);
/*     */             return;
/*     */           } 
/*     */         });
/* 126 */     EXECUTED_RECIPE_ACTIONS.addAll(LATE_RECIPE_ACTIONS.keySet());
/* 127 */     LATE_RECIPE_ACTIONS.clear();
/* 128 */     LOGGER.info("Registered {} late recipes", Integer.valueOf(recipeCount.get()));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\recipes\RecipeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */