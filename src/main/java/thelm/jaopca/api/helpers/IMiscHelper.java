package thelm.jaopca.api.helpers;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IMiscHelper {
  ResourceLocation getRecipeKey(String paramString1, String paramString2);
  
  String getOredictName(String paramString1, String paramString2);
  
  String getFluidName(String paramString1, String paramString2);
  
  ItemStack getItemStack(Object paramObject, int paramInt);
  
  Ingredient getIngredient(Object paramObject);
  
  ItemStack getPreferredItemStack(Iterable<ItemStack> paramIterable, int paramInt);
  
  ItemStack resizeItemStack(ItemStack paramItemStack, int paramInt);
  
  FluidStack getFluidStack(Object paramObject, int paramInt);
  
  FluidStack resizeFluidStack(FluidStack paramFluidStack, int paramInt);
  
  Comparator<IForgeRegistryEntry<?>> entryPreferenceComparator();
  
  Predicate<String> metaItemPredicate();
  
  ItemStack parseMetaItem(String paramString);
  
  String toLowercaseUnderscore(String paramString);
  
  void caclulateMaterialSet(Collection<String> paramCollection1, Collection<String> paramCollection2);
  
  void caclulateModuleSet(Collection<String> paramCollection1, Collection<String> paramCollection2);
  
  Predicate<String> configMaterialPredicate();
  
  Predicate<String> configModulePredicate();
  
  Runnable conditionalRunnable(BooleanSupplier paramBooleanSupplier, Supplier<Runnable> paramSupplier1, Supplier<Runnable> paramSupplier2);
  
  <T> Supplier<T> conditionalSupplier(BooleanSupplier paramBooleanSupplier, Supplier<Supplier<T>> paramSupplier1, Supplier<Supplier<T>> paramSupplier2);
  
  boolean hasResource(ResourceLocation paramResourceLocation);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\helpers\IMiscHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */