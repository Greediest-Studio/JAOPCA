/*    */ package thelm.jaopca.modules.passive;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.items.ItemFormType;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class CoinModule
/*    */   implements IModule
/*    */ {
/* 20 */   private final IForm coinForm = ApiImpl.INSTANCE.newForm(this, "coin", (IFormType)ItemFormType.INSTANCE)
/* 21 */     .setMaterialTypes(MaterialType.INGOTS);
/*    */ 
/*    */   
/*    */   public String getName() {
/* 25 */     return "coin";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 35 */     return Collections.singletonList(this.coinForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 40 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 41 */     builder.put("coin", "coin");
/* 42 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\passive\CoinModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */