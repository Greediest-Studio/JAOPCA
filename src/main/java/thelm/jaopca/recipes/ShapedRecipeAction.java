//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.recipes;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.item.crafting.ShapedRecipes;
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
/*    */ public class ShapedRecipeAction
/*    */   implements IRecipeAction
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final ResourceLocation key;
/*    */   public final String group;
/*    */   public final Object output;
/*    */   public final int count;
/*    */   public final Object[] input;
/*    */   
/*    */   public ShapedRecipeAction(ResourceLocation key, Object output, int count, Object... input) {
/* 32 */     this(key, "", output, count, input);
/*    */   }
/*    */   
/*    */   public ShapedRecipeAction(ResourceLocation key, String group, Object output, int count, Object... input) {
/* 36 */     this.key = Objects.<ResourceLocation>requireNonNull(key);
/* 37 */     this.group = Strings.nullToEmpty(group);
/* 38 */     this.output = output;
/* 39 */     this.count = count;
/* 40 */     this.input = Objects.<Object[]>requireNonNull(input);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 45 */     ItemStack stack = MiscHelper.INSTANCE.getItemStack(this.output, this.count);
/* 46 */     if (stack.isEmpty()) {
/* 47 */       throw new IllegalArgumentException("Empty output in recipe " + this.key + ": " + this.output);
/*    */     }
/* 49 */     int width = 0, height = 0;
/* 50 */     String shape = "";
/* 51 */     int idx = 0;
/* 52 */     if (this.input[idx] instanceof String[]) {
/* 53 */       String[] parts = (String[])this.input[idx++];
/* 54 */       for (String s : parts) {
/* 55 */         width = s.length();
/* 56 */         shape = shape + s;
/*    */       } 
/* 58 */       height = parts.length;
/*    */     } else {
/*    */       
/* 61 */       while (this.input[idx] instanceof String) {
/* 62 */         String s = (String)this.input[idx++];
/* 63 */         shape = shape + s;
/* 64 */         width = s.length();
/* 65 */         height++;
/*    */       } 
/*    */     } 
/* 68 */     if (width * height != shape.length() || shape.length() == 0) {
/* 69 */       throw new IllegalArgumentException("Invalid shape in recipe " + this.key + ": " + shape + "," + width + "x" + height);
/*    */     }
/* 71 */     Map<Character, Ingredient> itemMap = new HashMap<>();
/* 72 */     itemMap.put(Character.valueOf(' '), Ingredient.EMPTY);
/* 73 */     for (; idx < this.input.length; idx += 2) {
/* 74 */       Character chr = (Character)this.input[idx];
/* 75 */       Object in = this.input[idx + 1];
/* 76 */       Ingredient ing = MiscHelper.INSTANCE.getIngredient(in);
/* 77 */       if (itemMap.containsKey(chr)) {
/* 78 */         throw new IllegalArgumentException("Invalid key entry in recipe " + this.key + ": Symbol '" + chr + "' is defined twice");
/*    */       }
/* 80 */       if (' ' == chr.charValue()) {
/* 81 */         throw new IllegalArgumentException("Invalid key entry in recipe " + this.key + ": Symbol ' ' is reserved");
/*    */       }
/* 83 */       if (ing == null) {
/* 84 */         throw new IllegalArgumentException("Empty ingredient in recipe " + this.key + ": " + in);
/*    */       }
/* 86 */       itemMap.put(chr, ing);
/*    */     } 
/* 88 */     NonNullList<Ingredient> inputList = NonNullList.withSize(width * height, Ingredient.EMPTY);
/* 89 */     int x = 0;
/* 90 */     for (char chr : shape.toCharArray()) {
/* 91 */       Ingredient ing = itemMap.get(Character.valueOf(chr));
/* 92 */       if (ing == null) {
/* 93 */         throw new IllegalArgumentException("Pattern in recipe " + this.key + " references symbol '" + chr + "' but it's not defined in the key");
/*    */       }
/* 95 */       inputList.set(x++, ing);
/*    */     } 
/* 97 */     ForgeRegistries.RECIPES.register((new ShapedRecipes(this.group, width, height, inputList, stack)).setRegistryName(this.key));
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\recipes\ShapedRecipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
