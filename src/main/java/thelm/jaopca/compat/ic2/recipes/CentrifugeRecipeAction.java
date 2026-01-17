//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.ic2.recipes;
/*    */ 
/*    */ import ic2.api.recipe.IRecipeInput;
/*    */ import ic2.api.recipe.Recipes;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.ic2.IC2Helper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CentrifugeRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final int minHeat;
/*    */   public final Object[] output;
/*    */   
/*    */   public CentrifugeRecipeAction(ResourceLocation key, Object input, int inputCount, int minHeat, Object... output) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.inputCount = inputCount;
/* 33 */     this.minHeat = minHeat;
/* 34 */     this.output = output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 39 */     IRecipeInput ing = IC2Helper.INSTANCE.getRecipeInput(this.input, this.inputCount);
/* 40 */     if (ing == null) {
/* 41 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 43 */     List<ItemStack> outputs = new ArrayList<>();
/* 44 */     int i = 0;
/* 45 */     while (i < this.output.length) {
/* 46 */       Object out = this.output[i];
/* 47 */       i++;
/* 48 */       Integer count = Integer.valueOf(1);
/* 49 */       if (i < this.output.length && this.output[i] instanceof Integer) {
/* 50 */         count = (Integer)this.output[i];
/* 51 */         i++;
/*    */       } 
/* 53 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(out, count.intValue());
/* 54 */       if (stack.isEmpty()) {
/* 55 */         LOGGER.warn("Empty output in recipe {}: {}", this.key, out);
/*    */         continue;
/*    */       } 
/* 58 */       outputs.add(stack);
/*    */     } 
/* 60 */     NBTTagCompound metadata = new NBTTagCompound();
/* 61 */     metadata.setInteger("minHeat", this.minHeat);
/* 62 */     return Recipes.centrifuge.addRecipe(ing, outputs, metadata, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\recipes\CentrifugeRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
