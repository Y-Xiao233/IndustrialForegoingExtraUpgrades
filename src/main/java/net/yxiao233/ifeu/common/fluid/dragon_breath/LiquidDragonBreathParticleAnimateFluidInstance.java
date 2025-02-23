package net.yxiao233.ifeu.common.fluid.dragon_breath;

import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluidInstance;
import org.jetbrains.annotations.Nullable;

public class LiquidDragonBreathParticleAnimateFluidInstance extends AbstractAnimateFluidInstance {
    public LiquidDragonBreathParticleAnimateFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        super(helper, fluid, fluidTypeProperties, renderProperties, group);
    }

    @Override
    public AbstractAnimateFluid.Source<?> setSource(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidDragonBreathParticleAnimateFluid.Source(this);
    }

    @Override
    public AbstractAnimateFluid.Flowing<?> setFlowing(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        return new LiquidDragonBreathParticleAnimateFluid.Flowing(this);
    }
}
