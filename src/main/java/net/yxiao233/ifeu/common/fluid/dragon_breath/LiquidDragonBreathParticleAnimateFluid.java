package net.yxiao233.ifeu.common.fluid.dragon_breath;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import net.yxiao233.ifeu.common.config.misc.LiquidDragonBreathConfig;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluidInstance;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class LiquidDragonBreathParticleAnimateFluid extends AbstractAnimateFluid {
    public LiquidDragonBreathParticleAnimateFluid(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        super(abstractAnimateFluidInstance);
    }

    public static void tick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource){
        BlockPos blockpos = blockPos.above();
        if (level.getBlockState(blockpos).isAir() && !level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
            if (LiquidDragonBreathConfig.isFluidGenerateParticles && randomSource.nextInt((int)(1 / LiquidDragonBreathConfig.ProbabilityOfParticleGeneration)) == 0) {
                double d0 = (double)blockPos.getX() + randomSource.nextDouble();
                double d1 = (double)blockPos.getY() + 1.0;
                double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
                level.addParticle(ParticleTypes.DRAGON_BREATH, d0, d1, d2, 0.0, 0.0, 0.0);
                level.playLocalSound(d0, d1, d2, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
            }

            if (LiquidDragonBreathConfig.isFluidProduceSound && randomSource.nextInt((int)(1 / LiquidDragonBreathConfig.ProbabilityOfProducingSound)) == 0) {
                level.playLocalSound(blockPos, SoundEvents.ENDER_DRAGON_AMBIENT, SoundSource.BLOCKS, 0.1F + randomSource.nextFloat() * 0.1F, 0.1F + randomSource.nextFloat() * 0.1F, false);
            }
        }
    }
    @Override
    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        LiquidDragonBreathParticleAnimateFluid.tick(level,blockPos,fluidState,randomSource);
    }

    public static class Source extends AbstractAnimateFluid.Source<LiquidDragonBreathParticleAnimateFluidInstance>{

        public Source(LiquidDragonBreathParticleAnimateFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
            LiquidDragonBreathParticleAnimateFluid.tick(level,blockPos,fluidState,randomSource);
        }
    }


    public static class Flowing extends AbstractAnimateFluid.Flowing<LiquidDragonBreathParticleAnimateFluidInstance>{

        public Flowing(LiquidDragonBreathParticleAnimateFluidInstance instance) {
            super(instance);
        }

        @Override
        public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
            LiquidDragonBreathParticleAnimateFluid.tick(level,blockPos,fluidState,randomSource);
        }
    }
}
