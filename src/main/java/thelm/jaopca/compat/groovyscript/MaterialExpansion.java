/*    */ package thelm.jaopca.compat.groovyscript;
/*    */ 
/*    */ import com.cleanroommc.groovyscript.api.IIngredient;
/*    */ import com.cleanroommc.groovyscript.helper.ingredient.OreDictIngredient;
/*    */ import com.cleanroommc.groovyscript.helper.ingredient.OreDictWildcardIngredient;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterialExpansion
/*    */ {
/*    */   public static IIngredient ore(IMaterial material, String prefix) {
/* 18 */     String oreDict = MiscHelper.INSTANCE.getOredictName(prefix, material.getName());
/* 19 */     return oreDict.contains("*") ? (IIngredient)new OreDictWildcardIngredient(oreDict) : (IIngredient)new OreDictIngredient(oreDict);
/*    */   }
/*    */   
/*    */   public static ItemStack item(IMaterial material, String prefix, int count) {
/* 23 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 24 */     return miscHelper.getItemStack(miscHelper.getOredictName(prefix, material.getName()), count);
/*    */   }
/*    */   
/*    */   public static ItemStack item(IMaterial material, String prefix) {
/* 28 */     return item(material, prefix, 1);
/*    */   }
/*    */   
/*    */   public static FluidStack fluid(IMaterial material, String prefix, int count) {
/* 32 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 33 */     return miscHelper.getFluidStack(miscHelper.getOredictName(prefix, material.getName()), count);
/*    */   }
/*    */   
/*    */   public static FluidStack liquid(IMaterial material, String prefix, int count) {
/* 37 */     return fluid(material, prefix, count);
/*    */   }
/*    */   
/*    */   public static IMaterialFormInfo getMaterialForm(IMaterial material, IForm form) {
/* 41 */     if (FormExpansion.containsMaterial(form, material)) {
/* 42 */       return form.getType().getMaterialFormInfo(form, material);
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\groovyscript\MaterialExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */