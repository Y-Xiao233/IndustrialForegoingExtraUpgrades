package net.yxiao233.ifeu.common.fluid.dragon_star_essence;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.api.fluid.AbstractAnimateFluidInstance;

public class DragonStarEssenceFluid extends AbstractAnimateFluid {
    public DragonStarEssenceFluid(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        super(abstractAnimateFluidInstance);
    }

    @Override
    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
    }

    public static class Source extends AbstractAnimateFluid.Source<DragonStarEssenceFluidInstance>{

        public Source(DragonStarEssenceFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        }
    }


    public static class Flowing extends AbstractAnimateFluid.Flowing<DragonStarEssenceFluidInstance>{

        public Flowing(DragonStarEssenceFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        }
    }
}