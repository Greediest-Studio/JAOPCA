/*    */ package thelm.jaopca.modules.passive;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.items.IItemInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.items.ItemFormType;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class NuggetModule
/*    */   implements IModule
/*    */ {
/* 27 */   private final IForm nuggetForm = ApiImpl.INSTANCE.newForm(this, "nugget", (IFormType)ItemFormType.INSTANCE)
/* 28 */     .setMaterialTypes(MaterialType.NON_DUSTS);
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return "nugget";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 42 */     return Collections.singletonList(this.nuggetForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 47 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 48 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 49 */     for (IMaterial material : this.nuggetForm.getMaterials()) {
/* 50 */       IItemInfo nuggetInfo = ItemFormType.INSTANCE.getMaterialFormInfo(this.nuggetForm, material);
/* 51 */       String nuggetOredict = miscHelper.getOredictName("nugget", material.getName());
/* 52 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 53 */       apiImpl.registerShapelessRecipe(miscHelper
/* 54 */           .getRecipeKey("nugget.to_material", material.getName()), materialOredict, 1, new Object[] { nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict, nuggetOredict });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 60 */       apiImpl.registerShapelessRecipe(miscHelper
/* 61 */           .getRecipeKey("nugget.to_nugget", material.getName()), nuggetInfo, 9, new Object[] { materialOredict });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 70 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 71 */     builder.put("nugget", "nugget");
/* 72 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\passive\NuggetModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */