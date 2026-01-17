package thelm.jaopca.api.forms;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.modules.IModule;

public interface IFormRequest {
  IModule getModule();
  
  List<IForm> getForms();
  
  boolean isGrouped();
  
  IFormRequest setGrouped(boolean paramBoolean);
  
  Set<IMaterial> getMaterials();
  
  boolean isMaterialGroupValid(IMaterial paramIMaterial);
  
  void setMaterials(Collection<IMaterial> paramCollection);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\forms\IFormRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */