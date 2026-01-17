package thelm.jaopca.api.blocks;

import java.util.Set;
import java.util.function.Function;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public interface IBlockItemModelFunctionCreator {
  Pair<Function<ItemStack, ModelResourceLocation>, Set<ModelResourceLocation>> create(IMaterialFormBlockItem paramIMaterialFormBlockItem, IBlockFormSettings paramIBlockFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\blocks\IBlockItemModelFunctionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */