/*    */ package thelm.jaopca.compat.groovyscript;
/*    */ 
/*    */ import com.cleanroommc.groovyscript.api.IIngredient;
/*    */ import com.cleanroommc.groovyscript.helper.ingredient.OreDictIngredient;
/*    */ import com.cleanroommc.groovyscript.helper.ingredient.OreDictWildcardIngredient;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FormExpansion
/*    */ {
/*    */   public static boolean containsMaterial(IForm form, IMaterial material) {
/* 21 */     return form.getMaterials().contains(material);
/*    */   }
/*    */   
/*    */   public static IIngredient ore(IForm form, String suffix) {
/* 25 */     String oreDict = MiscHelper.INSTANCE.getOredictName(form.getSecondaryName(), suffix);
/* 26 */     return oreDict.contains("*") ? (IIngredient)new OreDictWildcardIngredient(oreDict) : (IIngredient)new OreDictIngredient(oreDict);
/*    */   }
/*    */   
/*    */   public static ItemStack item(IForm form, String suffix, int count) {
/* 30 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 31 */     return miscHelper.getItemStack(miscHelper.getOredictName(form.getSecondaryName(), suffix), count);
/*    */   }
/*    */   
/*    */   public static ItemStack item(IForm form, String suffix) {
/* 35 */     return item(form, suffix, 1);
/*    */   }
/*    */   
/*    */   public static FluidStack fluid(IForm form, String suffix, int amount) {
/* 39 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 40 */     return miscHelper.getFluidStack(miscHelper.getFluidName(form.getSecondaryName(), suffix), amount);
/*    */   }
/*    */   
/*    */   public static FluidStack liquid(IForm form, String suffix, int amount) {
/* 44 */     return fluid(form, suffix, amount);
/*    */   }
/*    */   
/*    */   public static IMaterialFormInfo getMaterialForm(IForm form, IMaterial material) {
/* 48 */     if (containsMaterial(form, material)) {
/* 49 */       return form.getType().getMaterialFormInfo(form, material);
/*    */     }
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public static List<IMaterialFormInfo> getMaterialForms(IForm form) {
/* 55 */     return (List<IMaterialFormInfo>)form.getMaterials().stream().map(m -> form.getType().getMaterialFormInfo(form, m)).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\groovyscript\FormExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */