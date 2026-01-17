package thelm.jaopca.api.fluids;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.SoundEvent;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.materials.IMaterial;

public interface IFluidFormSettings extends IFormSettings {
  IFluidFormSettings setFluidCreator(IFluidCreator paramIFluidCreator);
  
  IFluidCreator getFluidCreator();
  
  IFluidFormSettings setFillSoundSupplier(Supplier<SoundEvent> paramSupplier);
  
  Supplier<SoundEvent> getFillSoundSupplier();
  
  IFluidFormSettings setEmptySoundSupplier(Supplier<SoundEvent> paramSupplier);
  
  Supplier<SoundEvent> getEmptySoundSupplier();
  
  IFluidFormSettings setLuminosityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getLuminosityFunction();
  
  IFluidFormSettings setDensityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getDensityFunction();
  
  IFluidFormSettings setTemperatureFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getTemperatureFunction();
  
  IFluidFormSettings setViscosityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getViscosityFunction();
  
  IFluidFormSettings setOpacityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getOpacityFunction();
  
  IFluidFormSettings setIsGaseousFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getIsGaseousFunction();
  
  IFluidFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> paramFunction);
  
  Function<IMaterial, EnumRarity> getDisplayRarityFunction();
  
  IFluidFormSettings setFluidBlockCreator(IFluidBlockCreator paramIFluidBlockCreator);
  
  IFluidBlockCreator getFluidBlockCreator();
  
  IFluidFormSettings setMaxLevelFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getMaxLevelFunction();
  
  IFluidFormSettings setMaterialFunction(Function<IMaterial, Material> paramFunction);
  
  Function<IMaterial, Material> getMaterialFunction();
  
  IFluidFormSettings setMapColorFunction(Function<IMaterial, MapColor> paramFunction);
  
  Function<IMaterial, MapColor> getMapColorFunction();
  
  IFluidFormSettings setBlockHardnessFunction(ToDoubleFunction<IMaterial> paramToDoubleFunction);
  
  ToDoubleFunction<IMaterial> getBlockHardnessFunction();
  
  IFluidFormSettings setExplosionResistanceFunction(ToDoubleFunction<IMaterial> paramToDoubleFunction);
  
  ToDoubleFunction<IMaterial> getExplosionResistanceFunction();
  
  IFluidFormSettings setFlammabilityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getFlammabilityFunction();
  
  IFluidFormSettings setFireSpreadSpeedFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getFireSpreadSpeedFunction();
  
  IFluidFormSettings setIsFireSourceFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getIsFireSourceFunction();
  
  IFluidFormSettings setFluidBlockModelMapCreator(IFluidBlockModelMapCreator paramIFluidBlockModelMapCreator);
  
  IFluidBlockModelMapCreator getFluidBlockModelMapCreator();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IFluidFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */