package thelm.jaopca.api.fluids;

import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IFluidBlockModelMapCreator {
  Map<IBlockState, ModelResourceLocation> create(IMaterialFormFluidBlock paramIMaterialFormFluidBlock, IFluidFormSettings paramIFluidFormSettings);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\fluids\IFluidBlockModelMapCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */