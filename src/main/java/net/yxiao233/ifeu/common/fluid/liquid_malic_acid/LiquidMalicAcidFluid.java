package net.yxiao233.ifeu.common.fluid.liquid_malic_acid;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluidInstance;

public class LiquidMalicAcidFluid extends AbstractAnimateFluid {
    public LiquidMalicAcidFluid(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        super(abstractAnimateFluidInstance);
    }

    @Override
    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
    }

    public static class Source extends AbstractAnimateFluid.Source<LiquidMalicAcidFluidInstance>{

        public Source(LiquidMalicAcidFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        }
    }


    public static class Flowing extends AbstractAnimateFluid.Flowing<LiquidMalicAcidFluidInstance>{

        public Flowing(LiquidMalicAcidFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        }
    }
}
