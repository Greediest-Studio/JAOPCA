//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.hbm.recipes;
/*    */ 
/*    */ import com.hbm.inventory.CentrifugeRecipes;
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
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CentrifugeRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 25 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] output;
/*    */   
/*    */   public CentrifugeRecipeAction(ResourceLocation key, Object input, Object... output) {
/* 32 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 33 */     this.input = input;
/* 34 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 39 */     List<Object> ins = new ArrayList();
/* 40 */     if (this.input instanceof String) {
/* 41 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.input)) {
/* 42 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 44 */       ins.add(this.input);
/*    */     } else {
/*    */       
/* 47 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 48 */       if (ing == null) {
/* 49 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 51 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 52 */         ins.add((new RecipesCommon.ComparableStack(is)).singulize());
/*    */       }
/*    */     } 
/* 55 */     List<ItemStack> outputs = new ArrayList<>();
/* 56 */     int i = 0;
/* 57 */     while (i < this.output.length) {
/* 58 */       Object object = this.output[i];
/* 59 */       i++;
/* 60 */       Integer count = Integer.valueOf(1);
/* 61 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 62 */         count = (Integer)this.output[i];
/* 63 */         i++;
/*    */       } 
/* 65 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(object, count.intValue());
/* 66 */       if (stack.isEmpty()) {
/* 67 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, object);
/*    */         continue;
/*    */       } 
/* 70 */       outputs.add(stack);
/*    */     } 
/* 72 */     if (outputs.isEmpty()) {
/* 73 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 75 */     ItemStack[] out = outputs.<ItemStack>toArray(new ItemStack[outputs.size()]);
/*    */     try {
/* 77 */       Field mapField = CentrifugeRecipes.class.getDeclaredField("recipes");
/* 78 */       mapField.setAccessible(true);
/* 79 */       Map<Object, ItemStack[]> map = (Map<Object, ItemStack[]>)mapField.get(null);
/* 80 */       for (Object in : ins) {
/* 81 */         map.put(in, out);
/*    */       }
/* 83 */       return true;
/*    */     }
/* 85 */     catch (Exception e) {
/* 86 */       throw new IllegalStateException("Could not access centrifuge recipe map.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\hbm\recipes\CentrifugeRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
