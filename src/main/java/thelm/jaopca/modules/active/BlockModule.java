/*    */ package thelm.jaopca.modules.active;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import thelm.jaopca.api.blocks.IBlockInfo;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.blocks.BlockFormType;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class BlockModule
/*    */   implements IModule
/*    */ {
/* 26 */   private final IForm storageBlockForm = ApiImpl.INSTANCE.newForm(this, "block", (IFormType)BlockFormType.INSTANCE);
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return "block";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 35 */     return Collections.singletonList(this.storageBlockForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(IModuleData moduleData, FMLInitializationEvent event) {
/* 40 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 41 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/* 42 */     for (IMaterial material : this.storageBlockForm.getMaterials()) {
/* 43 */       IBlockInfo storageBlockInfo = BlockFormType.INSTANCE.getMaterialFormInfo(this.storageBlockForm, material);
/* 44 */       String storageBlockOredict = miscHelper.getOredictName("block", material.getName());
/* 45 */       String materialOredict = miscHelper.getOredictName(material.getType().getFormName(), material.getName());
/* 46 */       if (material.isSmallStorageBlock()) {
/* 47 */         apiImpl.registerShapedRecipe(miscHelper
/* 48 */             .getRecipeKey("block.to_block", material.getName()), storageBlockInfo, 1, new Object[] { "MM", "MM", 
/*    */ 
/*    */ 
/*    */               
/* 52 */               Character.valueOf('M'), materialOredict });
/*    */       }
/*    */       else {
/*    */         
/* 56 */         apiImpl.registerShapedRecipe(miscHelper
/* 57 */             .getRecipeKey("block.to_block", material.getName()), storageBlockInfo, 1, new Object[] { "MMM", "MMM", "MMM", 
/*    */ 
/*    */ 
/*    */ 
/*    */               
/* 62 */               Character.valueOf('M'), materialOredict });
/*    */       } 
/*    */       
/* 65 */       apiImpl.registerShapelessRecipe(miscHelper
/* 66 */           .getRecipeKey("block.to_material", material.getName()), materialOredict, 
/* 67 */           material.isSmallStorageBlock() ? 4 : 9, new Object[] { storageBlockOredict });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 75 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 76 */     builder.put("block", "block");
/* 77 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\active\BlockModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */