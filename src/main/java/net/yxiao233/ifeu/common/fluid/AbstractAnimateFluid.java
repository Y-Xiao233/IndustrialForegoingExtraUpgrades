package net.yxiao233.ifeu.common.fluid;

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
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractAnimateFluid extends FlowingFluid {
    private final AbstractAnimateFluidInstance abstractAnimateFluidInstance;

    public AbstractAnimateFluid(AbstractAnimateFluidInstance abstractAnimateFluidInstance) {
        this.abstractAnimateFluidInstance = abstractAnimateFluidInstance;
    }

    @Override
    public abstract void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource);

    @Nonnull
    public Fluid getFlowing() {
        return (Fluid)this.abstractAnimateFluidInstance.getFlowingFluid().get();
    }

    @Nonnull
    public Fluid getSource() {
        return (Fluid)this.abstractAnimateFluidInstance.getSourceFluid().get();
    }

    public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
        return false;
    }

    protected boolean canConvertToSource(Level p_256009_) {
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
        return (Item)this.abstractAnimateFluidInstance.getBucketFluid().get();
    }

    @ParametersAreNonnullByDefault
    protected boolean canBeReplacedWith(FluidState p_215665_1_, BlockGetter p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
        return p_215665_5_ == Direction.DOWN && !p_215665_4_.is(FluidTags.WATER);
    }

    public int getTickDelay(@Nonnull LevelReader p_205569_1_) {
        return 5;
    }

    protected float getExplosionResistance() {
        return 1.0F;
    }

    @Nonnull
    protected BlockState createLegacyBlock(@Nonnull FluidState state) {
        return (BlockState)((Block)this.abstractAnimateFluidInstance.getBlockFluid().get()).defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    public boolean isSource(@Nonnull FluidState state) {
        return false;
    }

    public int getAmount(@Nonnull FluidState p_207192_1_) {
        return 0;
    }

    public boolean isSame(Fluid fluidIn) {
        return fluidIn == this.abstractAnimateFluidInstance.getFlowingFluid().get() || fluidIn == this.abstractAnimateFluidInstance.getSourceFluid().get();
    }

    public FluidType getFluidType() {
        return (FluidType) this.abstractAnimateFluidInstance.getFluidType().get();
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
        public abstract void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource);
    }

    public abstract static class Flowing<T extends AbstractAnimateFluidInstance> extends AbstractAnimateFluid {
        public Flowing(T instance) {
            super(instance);
            this.registerDefaultState((FluidState)((FluidState)this.getStateDefinition().any()).setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(new Property[]{LEVEL});
        }

        public int getAmount(@Nonnull FluidState p_207192_1_) {
            return (Integer)p_207192_1_.getValue(LEVEL);
        }

        public boolean isSource(@Nonnull FluidState state) {
            return false;
        }

        @Override
        public abstract void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource);
    }
}
