package thelm.jaopca.api.blocks;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.forms.IFormType;
import thelm.jaopca.api.materialforms.IMaterialFormInfo;
import thelm.jaopca.api.materials.IMaterial;

public interface IBlockFormType extends IFormType {
  IBlockFormSettings getNewSettings();
  
  IBlockFormSettings deserializeSettings(JsonElement paramJsonElement, JsonDeserializationContext paramJsonDeserializationContext);
  
  IBlockInfo getMaterialFormInfo(IForm paramIForm, IMaterial paramIMaterial);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IBlockFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */