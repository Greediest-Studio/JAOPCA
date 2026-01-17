package thelm.jaopca.api.items;

import java.util.Set;
import java.util.function.Function;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public interface IItemModelFunctionCreator {
  Pair<Function<ItemStack, ModelResourceLocation>, Set<ModelResourceLocation>> create(IMaterialFormItem paramIMaterialFormItem, IItemFormSettings paramIItemFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\items\IItemModelFunctionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */