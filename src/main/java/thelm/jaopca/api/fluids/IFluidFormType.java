package thelm.jaopca.api.fluids;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.forms.IFormType;
import thelm.jaopca.api.materialforms.IMaterialFormInfo;
import thelm.jaopca.api.materials.IMaterial;

public interface IFluidFormType extends IFormType {
  IFluidFormSettings getNewSettings();
  
  IFluidFormSettings deserializeSettings(JsonElement paramJsonElement, JsonDeserializationContext paramJsonDeserializationContext);
  
  IFluidInfo getMaterialFormInfo(IForm paramIForm, IMaterial paramIMaterial);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IFluidFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */