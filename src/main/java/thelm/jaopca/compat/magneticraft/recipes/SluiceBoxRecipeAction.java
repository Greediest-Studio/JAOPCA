//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.magneticraft.recipes;
/*    */ 
/*    */ import com.cout970.magneticraft.api.MagneticraftApi;
/*    */ import com.cout970.magneticraft.api.registries.machines.sluicebox.ISluiceBoxRecipeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import kotlin.Pair;
/*    */ import kotlin.TuplesKt;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SluiceBoxRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object[] output;
/*    */   
/*    */   public SluiceBoxRecipeAction(ResourceLocation key, Object input, Object... output) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.input = input;
/* 33 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 39 */     if (ing == null) {
/* 40 */       throw new IllegalArgumentException("Empty input in recipe " + this.key + ": " + this.input);
/*    */     }
/* 42 */     List<Pair<ItemStack, Float>> outputs = new ArrayList<>();
/* 43 */     int i = 0;
/* 44 */     while (i < this.output.length) {
/* 45 */       Object out = this.output[i];
/* 46 */       i++;
/* 47 */       Integer count = Integer.valueOf(1);
/* 48 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 49 */         count = (Integer)this.output[i];
/* 50 */         i++;
/*    */       } 
/* 52 */       Float chance = Float.valueOf(1.0F);
/* 53 */       if (i < this.output.length && this.output[i] instanceof Float) {
/* 54 */         chance = (Float)this.output[i];
/* 55 */         i++;
/*    */       } 
/* 57 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 58 */       if (stack.isEmpty()) {
/* 59 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 62 */       outputs.add(TuplesKt.to(stack, chance));
/*    */     } 
/* 64 */     if (outputs.isEmpty()) {
/* 65 */       throw new IllegalArgumentException("Empty outputs in recipe " + this.key + ": " + Arrays.deepToString(this.output));
/*    */     }
/* 67 */     ISluiceBoxRecipeManager manager = MagneticraftApi.getSluiceBoxRecipeManager();
/* 68 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 69 */       manager.registerRecipe(manager.createRecipe(in.copy(), outputs, false));
/*    */     }
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\magneticraft\recipes\SluiceBoxRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
