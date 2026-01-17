//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.hbm.recipes;
/*    */ 
/*    */ import com.hbm.inventory.CrystallizerRecipes;
/*    */ import com.hbm.inventory.RecipesCommon;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrystallizerRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 26 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object itemInput;
/*    */   public final Object fluidInput;
/*    */   public final int fluidInputAmount;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   
/*    */   public CrystallizerRecipeAction(ResourceLocation key, Object itemInput, Object fluidInput, int fluidInputAmount, Object output, int count) {
/* 36 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 37 */     this.itemInput = itemInput;
/* 38 */     this.fluidInput = fluidInput;
/* 39 */     this.fluidInputAmount = fluidInputAmount;
/* 40 */     this.output = output;
/* 41 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 46 */     List<Object> ins = new ArrayList();
/* 47 */     if (this.itemInput instanceof String) {
/* 48 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.itemInput)) {
/* 49 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.itemInput);
/*    */       }
/* 51 */       ins.add(this.itemInput);
/*    */     } else {
/*    */       
/* 54 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.itemInput);
/* 55 */       if (ing == null) {
/* 56 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.itemInput);
/*    */       }
/* 58 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 59 */         ins.add((new RecipesCommon.ComparableStack(is)).singulize());
/*    */       }
/*    */     } 
/* 62 */     FluidStack fluidIng = MiscHelper.INSTANCE.getFluidStack(this.fluidInput, this.fluidInputAmount);
/* 63 */     if (fluidIng == null) {
/* 64 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.fluidInput);
/*    */     }
/* 66 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 67 */     if (stack.isEmpty()) {
/* 68 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/*    */     
/*    */     try {
/* 72 */       Field itemMapField = Arrays.<Field>stream(CrystallizerRecipes.class.getDeclaredFields()).filter(f -> ("recipes".equals(f.getName()) || "itemOutputRecipes".equals(f.getName()))).findAny().get();
/* 73 */       itemMapField.setAccessible(true);
/* 74 */       Map<Object, ItemStack> itemMap = (Map<Object, ItemStack>)itemMapField.get(null);
/* 75 */       for (Object in : ins) {
/* 76 */         itemMap.put(in, stack);
/*    */       }
/*    */     }
/* 79 */     catch (Exception e) {
/* 80 */       throw new IllegalStateException("Could not access crystallizer recipe map.");
/*    */     } 
/*    */     try {
/* 83 */       Field fluidMapField = CrystallizerRecipes.class.getDeclaredField("fluidInputRecipes");
/* 84 */       fluidMapField.setAccessible(true);
/* 85 */       Map<Object, FluidStack> fluidMap = (Map<Object, FluidStack>)fluidMapField.get(null);
/* 86 */       for (Object in : ins) {
/* 87 */         fluidMap.put(in, fluidIng);
/*    */       }
/*    */     }
/* 90 */     catch (Exception exception) {}
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\recipes\CrystallizerRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
