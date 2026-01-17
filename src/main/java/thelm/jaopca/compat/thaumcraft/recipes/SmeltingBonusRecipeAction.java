//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.thaumcraft.recipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class SmeltingBonusRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final float chance;
/*    */   
/*    */   public SmeltingBonusRecipeAction(ResourceLocation key, Object input, Object output, int count, float chance) {
/* 29 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 30 */     this.input = input;
/* 31 */     this.output = output;
/* 32 */     this.count = count;
/* 33 */     this.chance = chance;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 38 */     List<Object> ins = new ArrayList();
/* 39 */     if (this.input instanceof String) {
/* 40 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.input)) {
/* 41 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 43 */       ins.add(this.input);
/*    */     } else {
/*    */       
/* 46 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 47 */       if (ing == null) {
/* 48 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */       }
/* 50 */       for (ItemStack is : ing.getMatchingStacks()) {
/* 51 */         ins.add(is.copy());
/*    */       }
/*    */     } 
/* 54 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 55 */     if (stack.isEmpty()) {
/* 56 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 58 */     for (Object in : ins) {
/* 59 */       ThaumcraftApi.addSmeltingBonus(in, stack, this.chance);
/*    */     }
/* 61 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\thaumcraft\recipes\SmeltingBonusRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
