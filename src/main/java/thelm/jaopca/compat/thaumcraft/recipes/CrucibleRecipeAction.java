//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.thaumcraft.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.crafting.CrucibleRecipe;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.thaumcraft.ThaumcraftHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ public class CrucibleRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final String researchReq;
/*    */   public final Object input;
/*    */   public final Object[] aspects;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   
/*    */   public CrucibleRecipeAction(ResourceLocation key, String researchReq, Object input, Object[] aspects, Object output, int count) {
/* 31 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 32 */     this.researchReq = researchReq;
/* 33 */     this.input = input;
/* 34 */     this.aspects = aspects;
/* 35 */     this.output = output;
/* 36 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     Ingredient ing = MiscHelper.INSTANCE.getIngredient(this.input);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 45 */     AspectList aspectList = new AspectList();
/* 46 */     int i = 0;
/* 47 */     while (i < this.aspects.length) {
/* 48 */       Object in = this.aspects[i];
/* 49 */       i++;
/* 50 */       Integer inc = Integer.valueOf(1);
/* 51 */       if (i < this.aspects.length && this.aspects[i] instanceof Integer) {
/* 52 */         inc = (Integer)this.aspects[i];
/* 53 */         i++;
/*    */       } 
/* 55 */       Aspect aspect = ThaumcraftHelper.INSTANCE.getAspect(in);
/* 56 */       if (aspect == null) {
/* 57 */         throw new IllegalArgumentException("Non-existent aspect in recipe " + this.key + ": " + in);
/*    */       }
/* 59 */       aspectList.add(aspect, inc.intValue());
/*    */     } 
/* 61 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 62 */     if (stack.isEmpty()) {
/* 63 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 65 */     ThaumcraftApi.addCrucibleRecipe(this.key, new CrucibleRecipe(this.researchReq, stack, ing, aspectList));
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\thaumcraft\recipes\CrucibleRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
