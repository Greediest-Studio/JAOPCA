//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.recipes;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.item.crafting.ShapelessRecipes;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.recipes.IRecipeAction;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShapelessRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final String group;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final Object[] input;
/*    */   
/*    */   public ShapelessRecipeAction(ResourceLocation key, Object output, int count, Object... input) {
/* 30 */     this(key, "", output, count, input);
/*    */   }
/*    */   
/*    */   public ShapelessRecipeAction(ResourceLocation key, String group, Object output, int count, Object... input) {
/* 34 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 35 */     this.group = Strings.nullToEmpty(group);
/* 36 */     this.output = output;
/* 37 */     this.count = count;
/* 38 */     this.input = Objects.<Object[]>requireNonNull(input);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 43 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 44 */     if (stack.isEmpty()) {
/* 45 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 47 */     NonNullList<Ingredient> inputList = NonNullList.create();
/* 48 */     for (Object in : this.input) {
/* 49 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/* 50 */       if (ing == null) {
/* 51 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*    */       }
/*    */       
/* 54 */       inputList.add(ing);
/*    */     } 
/*    */     
/* 57 */     ForgeRegistries.RECIPES.register((new ShapelessRecipes(this.group, stack, inputList)).setRegistryName(this.key));
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\recipes\ShapelessRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
