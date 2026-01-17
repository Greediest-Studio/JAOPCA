package thelm.jaopca.api.blocks;

import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.materials.IMaterial;

public interface IBlockCreator {
  IMaterialFormBlock create(IForm paramIForm, IMaterial paramIMaterial, IBlockFormSettings paramIBlockFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IBlockCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */