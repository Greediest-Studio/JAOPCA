package thelm.jaopca.api.items;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import net.minecraft.item.EnumRarity;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.materials.IMaterial;

public interface IItemFormSettings extends IFormSettings {
  IItemFormSettings setItemCreator(IItemCreator paramIItemCreator);
  
  IItemCreator getItemCreator();
  
  IItemFormSettings setItemStackLimitFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getItemStackLimitFunction();
  
  IItemFormSettings setHasEffectFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getHasEffectFunction();
  
  IItemFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> paramFunction);
  
  Function<IMaterial, EnumRarity> getDisplayRarityFunction();
  
  IItemFormSettings setBurnTimeFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getBurnTimeFunction();
  
  IItemFormSettings setItemModelFunctionCreator(IItemModelFunctionCreator paramIItemModelFunctionCreator);
  
  IItemModelFunctionCreator getItemModelFunctionCreator();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\items\IItemFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */