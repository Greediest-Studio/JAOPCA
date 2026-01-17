package thelm.jaopca.compat.mekanism.api.gases;

import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.materials.IMaterial;

public interface IGasCreator {
  IMaterialFormGas create(IForm paramIForm, IMaterial paramIMaterial, IGasFormSettings paramIGasFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\api\gases\IGasCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */