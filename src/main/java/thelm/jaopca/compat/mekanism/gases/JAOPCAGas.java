/*    */ package thelm.jaopca.compat.mekanism.gases;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import mekanism.api.gas.Gas;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IGasFormSettings;
/*    */ import thelm.jaopca.compat.mekanism.api.gases.IMaterialFormGas;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class JAOPCAGas
/*    */   extends Gas
/*    */   implements IMaterialFormGas
/*    */ {
/*    */   private final IForm form;
/*    */   private final IMaterial material;
/*    */   protected final IGasFormSettings settings;
/*    */   protected boolean isHidden;
/* 21 */   protected Optional<String> translationKey = Optional.empty();
/*    */   
/*    */   public JAOPCAGas(IForm form, IMaterial material, IGasFormSettings settings) {
/* 24 */     super(MiscHelper.INSTANCE.getFluidName(form.getSecondaryName(), material.getName()), new ResourceLocation("jaopca", "gas/" + material
/* 25 */           .getModelType() + '/' + form.getName()));
/* 26 */     this.form = form;
/* 27 */     this.material = material;
/* 28 */     this.settings = settings;
/*    */     
/* 30 */     this.isHidden = settings.getIsHidden();
/*    */   }
/*    */ 
/*    */   
/*    */   public IForm getForm() {
/* 35 */     return this.form;
/*    */   }
/*    */ 
/*    */   
/*    */   public IMaterial getMaterial() {
/* 40 */     return this.material;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVisible() {
/* 45 */     return !this.isHidden;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTint() {
/* 50 */     return 0xFF000000 | this.material.getColor();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getIcon() {
/* 55 */     if (MiscHelper.INSTANCE.hasResource(new ResourceLocation("jaopca", "textures/gas/" + this.form.getName() + '.' + this.material.getName() + ".png"))) {
/* 56 */       return new ResourceLocation("jaopca", "gas/" + this.form.getName() + '.' + this.material.getName());
/*    */     }
/* 58 */     return super.getIcon();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 63 */     if (!this.translationKey.isPresent()) {
/* 64 */       this.translationKey = Optional.of("gas.jaopca." + MiscHelper.INSTANCE.toLowercaseUnderscore(this.material.getName()));
/*    */     }
/* 66 */     return this.translationKey.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLocalizedName() {
/* 71 */     return ApiImpl.INSTANCE.currentLocalizer().localizeMaterialForm("gas.jaopca." + this.form.getName(), this.material, getTranslationKey());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\gases\JAOPCAGas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */