package thelm.jaopca.api.modules;

import java.util.Set;
import thelm.jaopca.api.materials.IMaterial;

public interface IModuleData {
  IModule getModule();
  
  Set<String> getConfigMaterialBlacklist();
  
  Set<String> getConfigPassiveMaterialWhitelist();
  
  Set<IMaterial> getMaterials();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\modules\IModuleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */