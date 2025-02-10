package net.yxiao233.ifeu.common.fluid.sculk_matter;

import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluidInstance;
import org.jetbrains.annotations.Nullable;

public class LiquidSculkMatterParticleAnimateFluidInstance extends AbstractAnimateFluidInstance {
    public LiquidSculkMatterParticleAnimateFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        super(helper, fluid, fluidTypeProperties, renderProperties, group);
    }

    @Override
    public AbstractAnimateFluid.Source<?> setSource(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidSculkMatterParticleAnimateFluid.Source(this);
    }

    @Override
    public AbstractAnimateFluid.Flowing<?> setFlowing(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidSculkMatterParticleAnimateFluid.Flowing(this);
    }
}
