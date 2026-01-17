//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.astralsorcery.recipes;
/*    */ 
/*    */ import hellfirepvp.astralsorcery.common.crafting.ItemHandle;
/*    */ import hellfirepvp.astralsorcery.common.crafting.infusion.AbstractInfusionRecipe;
/*    */ import hellfirepvp.astralsorcery.common.crafting.infusion.InfusionRecipeRegistry;
/*    */ import hellfirepvp.astralsorcery.common.crafting.infusion.recipes.BasicInfusionRecipe;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.astralsorcery.AstralSorceryHelper;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class InfusionRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object input;
/*    */   public final Object output;
/*    */   public final int outputCount;
/*    */   public final float consumptionChance;
/*    */   public final boolean consumeMultiple;
/*    */   public final boolean acceptsChalices;
/*    */   
/*    */   public InfusionRecipeAction(ResourceLocation key, Object input, Object output, int outputCount, float consumptionChance, boolean consumeMultiple, boolean acceptsChalices) {
/* 30 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 31 */     this.input = input;
/* 32 */     this.output = output;
/* 33 */     this.outputCount = outputCount;
/* 34 */     this.consumptionChance = consumptionChance;
/* 35 */     this.consumeMultiple = consumeMultiple;
/* 36 */     this.acceptsChalices = acceptsChalices;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 41 */     ItemHandle ing = AstralSorceryHelper.INSTANCE.getItemHandle(this.input);
/* 42 */     if (ing == null) {
/* 43 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 45 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.outputCount);
/* 46 */     if (stack.isEmpty()) {
/* 47 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 49 */     BasicInfusionRecipe recipe = new BasicInfusionRecipe(stack, ing);
/* 50 */     recipe.setLiquidStarlightConsumptionChance(this.consumptionChance);
/* 51 */     if (this.consumeMultiple) {
/* 52 */       recipe.setConsumeMultiple();
/*    */     }
/* 54 */     recipe.setCanBeSupportedByChalices(this.acceptsChalices);
/* 55 */     return (InfusionRecipeRegistry.registerInfusionRecipe((AbstractInfusionRecipe)recipe) != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\astralsorcery\recipes\InfusionRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
