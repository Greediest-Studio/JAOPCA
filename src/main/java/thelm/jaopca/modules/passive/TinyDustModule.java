/*    */ package thelm.jaopca.modules.passive;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.materials.IMaterial;
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
/*    */ public class TinyDustModule
/*    */   implements IModule
/*    */ {
/* 27 */   private final IForm tinyDustForm = ApiImpl.INSTANCE.newForm(this, "tiny_dust", (IFormType)ItemFormType.INSTANCE)
/* 28 */     .setSecondaryName("dustTiny");
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return "tiny_dust";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 42 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 43 */     builder.put(Integer.valueOf(0), "dust");
/* 44 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 49 */     return Collections.singletonList(this.tinyDustForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 54 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 55 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 56 */     for (IMaterial material : this.tinyDustForm.getMaterials()) {
/* 57 */       String tinyDustOredict = miscHelper.getOredictName("dustTiny", material.getName());
/* 58 */       String dustOredict = miscHelper.getOredictName("dust", material.getName());
/* 59 */       apiImpl.registerShapelessRecipe(miscHelper
/* 60 */           .getRecipeKey("tiny_dust.to_dust", material.getName()), dustOredict, 1, new Object[] { tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict, tinyDustOredict });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 71 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 72 */     builder.put("dusttiny", "tiny_dust");
/* 73 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\passive\TinyDustModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */