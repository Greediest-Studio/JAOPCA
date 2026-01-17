package thelm.jaopca.api.blocks;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.math.AxisAlignedBB;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.materials.IMaterial;

public interface IBlockFormSettings extends IFormSettings {
  IBlockFormSettings setBlockCreator(IBlockCreator paramIBlockCreator);
  
  IBlockCreator getBlockCreator();
  
  IBlockFormSettings setMaterialFunction(Function<IMaterial, Material> paramFunction);
  
  Function<IMaterial, Material> getMaterialFunction();
  
  IBlockFormSettings setMapColorFunction(Function<IMaterial, MapColor> paramFunction);
  
  Function<IMaterial, MapColor> getMapColorFunction();
  
  IBlockFormSettings setBlocksMovement(boolean paramBoolean);
  
  boolean getBlocksMovement();
  
  IBlockFormSettings setSoundTypeFunction(Function<IMaterial, SoundType> paramFunction);
  
  Function<IMaterial, SoundType> getSoundTypeFunction();
  
  IBlockFormSettings setLightOpacityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getLightOpacityFunction();
  
  IBlockFormSettings setLightValueFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getLightValueFunction();
  
  IBlockFormSettings setBlockHardnessFunction(ToDoubleFunction<IMaterial> paramToDoubleFunction);
  
  ToDoubleFunction<IMaterial> getBlockHardnessFunction();
  
  IBlockFormSettings setExplosionResistanceFunction(ToDoubleFunction<IMaterial> paramToDoubleFunction);
  
  ToDoubleFunction<IMaterial> getExplosionResistanceFunction();
  
  IBlockFormSettings setSlipperinessFunction(ToDoubleFunction<IMaterial> paramToDoubleFunction);
  
  ToDoubleFunction<IMaterial> getSlipperinessFunction();
  
  IBlockFormSettings setBoundingBox(AxisAlignedBB paramAxisAlignedBB);
  
  AxisAlignedBB getBoundingBox();
  
  IBlockFormSettings setHarvestToolFunction(Function<IMaterial, String> paramFunction);
  
  Function<IMaterial, String> getHarvestToolFunction();
  
  IBlockFormSettings setHarvestLevelFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getHarvestLevelFunction();
  
  IBlockFormSettings setFlammabilityFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getFlammabilityFunction();
  
  IBlockFormSettings setFireSpreadSpeedFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getFireSpreadSpeedFunction();
  
  IBlockFormSettings setIsFireSourceFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getIsFireSourceFunction();
  
  IBlockFormSettings setIsBeaconBaseFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getIsBeaconBaseFunction();
  
  IBlockFormSettings setBlockModelMapCreator(IBlockModelMapCreator paramIBlockModelMapCreator);
  
  IBlockModelMapCreator getBlockModelMapCreator();
  
  IBlockFormSettings setBlockItemCreator(IBlockItemCreator paramIBlockItemCreator);
  
  IBlockItemCreator getBlockItemCreator();
  
  IBlockFormSettings setItemStackLimitFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getItemStackLimitFunction();
  
  IBlockFormSettings setHasEffectFunction(Predicate<IMaterial> paramPredicate);
  
  Predicate<IMaterial> getHasEffectFunction();
  
  IBlockFormSettings setDisplayRarityFunction(Function<IMaterial, EnumRarity> paramFunction);
  
  Function<IMaterial, EnumRarity> getDisplayRarityFunction();
  
  IBlockFormSettings setBurnTimeFunction(ToIntFunction<IMaterial> paramToIntFunction);
  
  ToIntFunction<IMaterial> getBurnTimeFunction();
  
  IBlockFormSettings setBlockItemModelFunctionCreator(IBlockItemModelFunctionCreator paramIBlockItemModelFunctionCreator);
  
  IBlockItemModelFunctionCreator getBlockItemModelFunctionCreator();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IBlockFormSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */