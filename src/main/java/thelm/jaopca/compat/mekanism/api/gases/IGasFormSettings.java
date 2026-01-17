package thelm.jaopca.compat.mekanism.api.gases;

import thelm.jaopca.api.forms.IFormSettings;

public interface IGasFormSettings extends IFormSettings {
  IGasFormSettings setGasCreator(IGasCreator paramIGasCreator);
  
  IGasCreator getGasCreator();
  
  IGasFormSettings setIsHidden(boolean paramBoolean);
  
  boolean getIsHidden();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\api\gases\IGasFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */