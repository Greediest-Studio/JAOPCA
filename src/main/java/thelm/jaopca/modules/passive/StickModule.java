/*    */ package thelm.jaopca.modules.passive;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
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
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class StickModule
/*    */   implements IModule
/*    */ {
/* 28 */   private final IForm stickForm = ApiImpl.INSTANCE.newForm(this, "stick", (IFormType)ItemFormType.INSTANCE)
/* 29 */     .setMaterialTypes(MaterialType.NON_DUSTS);
/*    */   
/*    */   private static boolean registerRodOredict = true;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 35 */     return "stick";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 45 */     return Collections.singletonList(this.stickForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {
/* 50 */     registerRodOredict = config.getDefinedBoolean("oredict.registerRodOredict", registerRodOredict, "Should the module register rod oredict for sticks.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMaterialComputeComplete(IModuleData moduleData) {
/* 55 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 56 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 57 */     ItemFormType itemFormType = ItemFormType.INSTANCE;
/* 58 */     for (IMaterial material : this.stickForm.getMaterials()) {
/* 59 */       if (registerRodOredict) {
/* 60 */         IItemInfo stickInfo = itemFormType.getMaterialFormInfo(this.stickForm, material);
/* 61 */         apiImpl.registerOredict(miscHelper.getOredictName("rod", material.getName()), stickInfo.asItem());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 68 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 69 */     builder.put("stick", "stick");
/* 70 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\passive\StickModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */