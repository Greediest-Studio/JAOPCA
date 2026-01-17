/*    */ package thelm.jaopca.modules.active;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
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
/*    */ public class DustModule
/*    */   implements IModule
/*    */ {
/* 26 */   private final IForm dustForm = ApiImpl.INSTANCE.newForm(this, "dust", (IFormType)ItemFormType.INSTANCE)
/* 27 */     .setMaterialTypes(MaterialType.NON_DUSTS);
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return "dust";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 36 */     return Collections.singletonList(this.dustForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 41 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 42 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 43 */     for (IMaterial material : this.dustForm.getMaterials()) {
/* 44 */       if (material.getType().isIngot()) {
/* 45 */         String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 46 */         String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 47 */         apiImpl.registerSmeltingRecipe(miscHelper
/* 48 */             .getRecipeKey("dust.to_material", material.getName()), dustOredict, materialOredict, 1, 0.7F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 56 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 57 */     builder.put("dust", "dust");
/* 58 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\active\DustModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */