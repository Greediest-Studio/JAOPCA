//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.foundry;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import exter.foundry.fluid.LiquidMetalRegistry;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import net.minecraft.block.material.Material;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.fluids.IFluidBlockCreator;
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
/*    */ @JAOPCAModule(modDependencies = {"foundry@[3,)"})
/*    */ public class FoundryLiquidModule
/*    */   implements IModule {
/* 29 */   private static final Set<String> BLACKLIST = new TreeSet<>();
/*    */   
/* 31 */   private final IForm liquidForm = ApiImpl.INSTANCE.newForm(this, "foundry_liquid", (IFormType)FluidFormType.INSTANCE)
/* 32 */     .setMaterialTypes(MaterialType.INGOTS).setSettings((IFormSettings)FluidFormType.INSTANCE.getNewSettings()
/* 33 */       .setFluidBlockCreator(thelm.jaopca.compat.foundry.fluids.JAOPCALiquidMetalFluidBlock::new)
/* 34 */       .setLuminosityFunction(material -> 15).setDensityFunction(material -> 2000)
/* 35 */       .setTemperatureFunction(this::getTemperature).setMaterialFunction(material -> Material.LAVA));
/*    */   
/*    */   private Map<IMaterial, IDynamicSpecConfig> configs;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 41 */     return "foundry_liquid";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassive() {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<Integer, String> getModuleDependencies() {
/* 51 */     ImmutableSetMultimap.Builder<Integer, String> builder = ImmutableSetMultimap.builder();
/* 52 */     builder.put(Integer.valueOf(0), "block");
/* 53 */     return (Multimap<Integer, String>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 58 */     if (BLACKLIST.isEmpty()) {
/* 59 */       BLACKLIST.addAll(LiquidMetalRegistry.INSTANCE.getFluidNames());
/* 60 */       Collections.addAll(BLACKLIST, new String[] { "Aluminum", "Constantan" });
/* 61 */       this.liquidForm.setDefaultMaterialBlacklist(BLACKLIST);
/*    */     } 
/* 63 */     return Collections.singletonList(this.liquidForm.toRequest());
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 68 */     this.configs = configs;
/*    */   }
/*    */   
/*    */   public int getTemperature(IMaterial material) {
/* 72 */     return ((IDynamicSpecConfig)this.configs.get(material)).getDefinedInt("foundry.temperature", 1000, "The temperature of this Foundry liquid metal.");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\foundry\FoundryLiquidModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
