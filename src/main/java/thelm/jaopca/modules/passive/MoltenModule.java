//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.modules.passive;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.material.Material;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.forms.IFormSettings;
/*    */ import thelm.jaopca.api.forms.IFormType;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ import thelm.jaopca.fluids.FluidFormType;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class MoltenModule
/*    */   implements IModule {
/* 24 */   private final IForm moltenForm = ApiImpl.INSTANCE.newForm(this, "molten", (IFormType)FluidFormType.INSTANCE)
/* 25 */     .setMaterialTypes(MaterialType.NON_DUSTS).setSecondaryName("")
/* 26 */     .setSettings((IFormSettings)FluidFormType.INSTANCE.getNewSettings()
/* 27 */       .setViscosityFunction(material -> 10000).setLuminosityFunction(material -> 10)
/* 28 */       .setDensityFunction(material -> 2000).setTemperatureFunction(this::getTemperature)
/* 29 */       .setMaterialFunction(material -> Material.LAVA));
/*    */   
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 35 */     return "molten";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 45 */     return Collections.singletonList(this.moltenForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 50 */     this.configs = configs;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 55 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 56 */     builder.put("molten", "molten");
/* 57 */     return (Map<String, String>)builder.build();
/*    */   }
/*    */   
/*    */   public int getTemperature(IMaterial material) {
/* 61 */     return ((IDynamicSpecConfig)this.configs.get(material)).getDefinedInt("molten.temperature", 1000, "The temperature of this molten fluid.");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\passive\MoltenModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
