//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.abyssalcraft.recipes;
/*    */ 
/*    */ import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
/*    */ import java.util.Objects;
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
/*    */ public class TransmutationRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final float experience;
/*    */   
/*    */   public TransmutationRecipeAction(ResourceLocation key, Object input, Object output, int count, float experience) {
/* 27 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 28 */     this.input = input;
/* 29 */     this.output = output;
/* 30 */     this.count = count;
/* 31 */     this.experience = experience;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 36 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 37 */     if (ing == null) {
/* 38 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 40 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 41 */     if (stack.isEmpty()) {
/* 42 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 44 */     for (ItemStack in : ing.getMatchingStacks()) {
/* 45 */       AbyssalCraftAPI.addTransmutation(in.copy(), stack, this.experience);
/*    */     }
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\abyssalcraft\recipes\TransmutationRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
