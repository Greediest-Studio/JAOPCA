//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.tconstruct.recipes;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import slimeknights.mantle.util.RecipeMatch;
/*    */ import slimeknights.tconstruct.library.TinkerRegistry;
/*    */ import slimeknights.tconstruct.library.smeltery.CastingRecipe;
/*    */ import slimeknights.tconstruct.library.smeltery.ICastingRecipe;
/*    */ import slimeknights.tconstruct.library.smeltery.PreferenceCastingRecipe;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.compat.tconstruct.TConstructHelper;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class TableCastingRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final Object cast;
/*    */   public final Object input;
/*    */   public final int inputAmount;
/*    */   public final Object output;
/*    */   public final ToIntFunction<FluidStack> time;
/*    */   public final boolean consumeCast;
/*    */   public final boolean switchSlots;
/*    */   
/*    */   public TableCastingRecipeAction(ResourceLocation key, Object cast, Object input, int inputAmount, Object output, ToIntFunction<FluidStack> time, boolean consumeCast, boolean switchSlots) {
/* 35 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 36 */     this.cast = cast;
/* 37 */     this.input = input;
/* 38 */     this.inputAmount = inputAmount;
/* 39 */     this.output = output;
/* 40 */     this.time = time;
/* 41 */     this.consumeCast = consumeCast;
/* 42 */     this.switchSlots = switchSlots;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 47 */     FluidStack ing = MiscHelper.INSTANCE.getFluidStack(this.input, this.inputAmount);
/* 48 */     if (ing == null) {
/* 49 */       throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + this.input);
/*    */     }
/* 51 */     RecipeMatch match = TConstructHelper.INSTANCE.getRecipeMatch(this.cast, 1, 1);
/* 52 */     if (this.output instanceof String) {
/* 53 */       if (!ApiImpl.INSTANCE.getOredict().contains(this.output)) {
/* 54 */         throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */       }
/* 56 */       TinkerRegistry.registerTableCasting((ICastingRecipe)new PreferenceCastingRecipe((String)this.output, match, ing, this.time.applyAsInt(ing), this.consumeCast, this.switchSlots));
/*    */     } else {
/*    */       
/* 59 */       ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, 1);
/* 60 */       if (stack.isEmpty()) {
/* 61 */         throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */       }
/* 63 */       TinkerRegistry.registerTableCasting((ICastingRecipe)new CastingRecipe(stack, match, ing, this.time.applyAsInt(ing), this.consumeCast, this.switchSlots));
/*    */     } 
/* 65 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\tconstruct\recipes\TableCastingRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
