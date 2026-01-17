/*    */ package thelm.jaopca.api.modules;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSetMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IModule
/*    */   extends Comparable<IModule>
/*    */ {
/*    */   default boolean isPassive() {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   default void defineModuleConfigPre(IModuleData moduleData, IDynamicSpecConfig config) {}
/*    */   
/*    */   default void defineMaterialConfigPre(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {}
/*    */   
/*    */   default Multimap<Integer, String> getModuleDependencies() {
/* 31 */     return (Multimap<Integer, String>)ImmutableSetMultimap.of();
/*    */   }
/*    */   
/*    */   default List<IFormRequest> getFormRequests() {
/* 35 */     return Collections.emptyList();
/*    */   }
/*    */   
/*    */   default Set<MaterialType> getMaterialTypes() {
/* 39 */     return Collections.emptySet();
/*    */   }
/*    */   
/*    */   default Set<String> getDefaultMaterialBlacklist() {
/* 43 */     return Collections.emptyNavigableSet();
/*    */   }
/*    */   
/*    */   default void defineModuleConfig(IModuleData moduleData, IDynamicSpecConfig config) {}
/*    */   
/*    */   default void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {}
/*    */   
/*    */   default void onMaterialComputeComplete(IModuleData moduleData) {}
/*    */   
/*    */   default void onInit(IModuleData moduleData, FMLInitializationEvent event) {}
/*    */   
/*    */   default void onPostInit(IModuleData moduleData, FMLPostInitializationEvent event) {}
/*    */   
/*    */   default Map<String, String> getLegacyRemaps() {
/* 57 */     return Collections.emptyNavigableMap();
/*    */   }
/*    */ 
/*    */   
/*    */   default int compareTo(IModule other) {
/* 62 */     return getName().compareTo(other.getName());
/*    */   }
/*    */   
/*    */   String getName();
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\modules\IModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */