package net.yxiao233.ifeu.common.fluid.liquid_malic_acid;

import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluidInstance;
import net.yxiao233.ifeu.common.fluid.dragon_star_essence.DragonStarEssenceFluid;
import org.jetbrains.annotations.Nullable;

public class LiquidMalicAcidFluidInstance extends AbstractAnimateFluidInstance {
    public LiquidMalicAcidFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        super(helper, fluid, fluidTypeProperties, renderProperties, group);
    }

    @Override
    public AbstractAnimateFluid.Source<?> setSource(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidMalicAcidFluid.Source(this);
    }

    @Override
    public AbstractAnimateFluid.Flowing<?> setFlowing(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidMalicAcidFluid.Flowing(this);
    }
}
