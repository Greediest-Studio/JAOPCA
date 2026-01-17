//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.ic2.recipes;
/*    */ 
/*    */ import ic2.api.recipe.IRecipeInput;
/*    */ import ic2.api.recipe.Recipes;
/*    */ import java.util.Collections;
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
/*    */ public class BlockCutterRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final int inputCount;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final int hardness;
/*    */   
/*    */   public BlockCutterRecipeAction(ResourceLocation key, Object input, int inputCount, Object output, int outputCount, int hardness) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.inputCount = inputCount;
/* 33 */     this.output = output;
/* 34 */     this.outputCount = outputCount;
/* 35 */     this.hardness = hardness;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 40 */     IRecipeInput ing = IC2Helper.INSTANCE.getRecipeInput(this.input, this.inputCount);
/* 41 */     if (ing == null) {
/* 42 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 44 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 45 */     if (stack.isEmpty()) {
/* 46 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 48 */     NBTTagCompound metadata = new NBTTagCompound();
/* 49 */     metadata.setInteger("hardness", this.hardness);
/* 50 */     return Recipes.blockcutter.addRecipe(ing, Collections.singletonList(stack), metadata, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\ic2\recipes\BlockCutterRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
