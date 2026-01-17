//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.abyssalcraft;
/*    */ 
/*    */ import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
/*    */ import com.shinoow.abyssalcraft.api.event.FuelBurnTimeEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.abyssalcraft.recipes.CrystallizationRecipeAction;
/*    */ import thelm.jaopca.compat.abyssalcraft.recipes.MaterializationRecipeAction;
/*    */ import thelm.jaopca.compat.abyssalcraft.recipes.TransmutationRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbyssalCraftHelper
/*    */ {
/* 23 */   public static final AbyssalCraftHelper INSTANCE = new AbyssalCraftHelper();
/* 24 */   private static final List<Pair<Ingredient, Integer>> FUELS = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean registerCrystal(Object crystal) {
/* 29 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(crystal);
/* 30 */     if (ing != null) {
/* 31 */       for (ItemStack stack : ing.getMatchingStacks()) {
/* 32 */         AbyssalCraftAPI.addCrystal(stack);
/*    */       }
/* 34 */       return true;
/*    */     } 
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public boolean registerFuel(Object fuel, int time) {
/* 40 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(fuel);
/* 41 */     if (ing != null) {
/* 42 */       FUELS.add(Pair.of(ing, Integer.valueOf(time)));
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public boolean registerCrystallizationRecipe(ResourceLocation key, Object input, Object output, int outputCount, Object secondOutput, int secondOutputCount, float experience) {
/* 48 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrystallizationRecipeAction(key, input, output, outputCount, secondOutput, secondOutputCount, experience));
/*    */   }
/*    */   
/*    */   public boolean registerCrystallizationRecipe(ResourceLocation key, Object input, Object output, int count, float experience) {
/* 52 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new CrystallizationRecipeAction(key, input, output, count, experience));
/*    */   }
/*    */   
/*    */   public boolean registerTransmutationRecipe(ResourceLocation key, Object input, Object output, int count, float experience) {
/* 56 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new TransmutationRecipeAction(key, input, output, count, experience));
/*    */   }
/*    */   
/*    */   public boolean registerMaterializationRecipe(ResourceLocation key, Object output, int count, Object... input) {
/* 60 */     return ApiImpl.INSTANCE.registerRecipe(key, (IRecipeAction)new MaterializationRecipeAction(key, output, count, input));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onFuelBurnTime(FuelBurnTimeEvent event) {
/* 65 */     ItemStack stack = event.getItemStack();
/* 66 */     for (Pair<Ingredient, Integer> pair : FUELS) {
/* 67 */       if (((Ingredient)pair.getLeft()).apply(stack)) {
/* 68 */         event.setBurnTime(((Integer)pair.getRight()).intValue());
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\AbyssalCraftHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
