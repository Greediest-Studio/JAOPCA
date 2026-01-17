//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.crafttweaker;
/*    */ 
/*    */ import crafttweaker.annotations.ZenRegister;
/*    */ import crafttweaker.api.item.IIngredient;
/*    */ import crafttweaker.api.item.IItemStack;
/*    */ import crafttweaker.api.minecraft.CraftTweakerMC;
/*    */ import crafttweaker.api.oredict.IOreDictEntry;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import stanhebben.zenscript.annotations.ZenClass;
/*    */ import stanhebben.zenscript.annotations.ZenMethod;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ZenRegister
/*    */ @ZenClass("mods.jaopca.oreDictHelper")
/*    */ public class OreDictHelper
/*    */ {
/*    */   @ZenMethod
/*    */   public static void addOreDict(IOreDictEntry oredict, IIngredient item) {
/* 24 */     for (ItemStack stack : CraftTweakerMC.getItemStacks(new IItemStack[] { (IItemStack)item })) {
/* 25 */       if (!stack.isEmpty())
/* 26 */         ApiImpl.INSTANCE.registerOredict(oredict.getName(), stack); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\OreDictHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
