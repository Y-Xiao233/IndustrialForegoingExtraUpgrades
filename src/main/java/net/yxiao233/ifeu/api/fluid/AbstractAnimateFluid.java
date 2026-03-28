package net.yxiao233.ifeu.api.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractAnimateFluid extends FlowingFluid {
    private final AbstractAnimateFluidInstance abstractAnimateFluidInstance;

    public AbstractAnimateFluid(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        this.abstractAnimateFluidInstance = abstractAnimateFluidInstance;
    }

    @Override
    public abstract void animateTick(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull FluidState fluidState, @NotNull RandomSource randomSource);

    @Nonnull
    public Fluid getFlowing() {
        return this.abstractAnimateFluidInstance.getFlowingFluid().get();
    }

    @Nonnull
    public Fluid getSource() {
        return this.abstractAnimateFluidInstance.getSourceFluid().get();
    }

    public boolean canConvertToSource(@NotNull FluidState state, @NotNull Level level, @NotNull BlockPos pos) {
        return false;
    }

    protected boolean canConvertToSource(@NotNull Level level) {
        return false;
    }

    @ParametersAreNonnullByDefault
    protected void beforeDestroyingBlock(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (blockEntity != null) {
            Block.dropResources(state, worldIn, pos, blockEntity);
        }

    }

    protected int getSlopeFindDistance(@Nonnull LevelReader world) {
        return 4;
    }

    protected int getDropOff(@Nonnull LevelReader world) {
        return 1;
    }

    @Nonnull
    public Item getBucket() {
        return this.abstractAnimateFluidInstance.getBucketFluid().get();
    }

    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    protected boolean canBeReplacedWith(FluidState fluidState, BlockGetter level, BlockPos blockPos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER);
    }

    public int getTickDelay(@Nonnull LevelReader p_205569_1_) {
        return 5;
    }

    protected float getExplosionResistance() {
        return 1.0F;
    }

    @Nonnull
    protected BlockState createLegacyBlock(@Nonnull FluidState state) {
        return this.abstractAnimateFluidInstance.getBlockFluid().get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    public boolean isSource(@Nonnull FluidState state) {
        return false;
    }

    public int getAmount(@Nonnull FluidState p_207192_1_) {
        return 0;
    }

    public boolean isSame(@NotNull Fluid fluidIn) {
        return fluidIn == this.abstractAnimateFluidInstance.getFlowingFluid().get() || fluidIn == this.abstractAnimateFluidInstance.getSourceFluid().get();
    }

    public @NotNull FluidType getFluidType() {
        return this.abstractAnimateFluidInstance.getFluidType().get();
    }

    public abstract static class Source<T extends AbstractAnimateFluidInstance> extends AbstractAnimateFluid {
        public Source(T instance) {
            super(instance);
        }

        public int getAmount(@Nonnull FluidState p_207192_1_) {
            return 8;
        }

        public boolean isSource(@Nonnull FluidState state) {
            return true;
        }

        @Override
        public abstract void animateTick(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull FluidState fluidState, @NotNull RandomSource randomSource);
    }

    public abstract static class Flowing<T extends AbstractAnimateFluidInstance> extends AbstractAnimateFluid {
        public Flowing(T instance) {
            super(instance);
            this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(@Nonnull FluidState p_207192_1_) {
            return (Integer)p_207192_1_.getValue(LEVEL);
        }

        @Override
        public abstract void animateTick(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull FluidState fluidState, @NotNull RandomSource randomSource);
    }
}
