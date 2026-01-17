package thelm.jaopca.api.fluids;

import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.materials.IMaterial;

public interface IFluidCreator {
  IMaterialFormFluid create(IForm paramIForm, IMaterial paramIMaterial, IFluidFormSettings paramIFluidFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IFluidCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */