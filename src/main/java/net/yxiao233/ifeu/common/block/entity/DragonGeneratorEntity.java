package net.yxiao233.ifeu.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.api.block.entity.IFEUFluidFuelGeneratorEntity;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUFluids;
import net.yxiao233.ifeu.common.utils.FluidTankElementsUtil;
import org.jetbrains.annotations.NotNull;

public class DragonGeneratorEntity extends IFEUFluidFuelGeneratorEntity<DragonGeneratorEntity> {
    private static final FluidStack FLUID_FUEL = new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000);

    public DragonGeneratorEntity(BlockPos blockPos, BlockState blockState) {
        super(IFEUBlocks.DRAGON_GENERATOR, blockPos, blockState);
    }

    @Override
    public boolean canIncrease() {
        return isCurrentBlockAbove();
    }

    @Override
    public FluidTankElementsUtil getFluidTankElements() {
        return new FluidTankElementsUtil();
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonGeneratorConfig.class;
    }

    @Override
    public FluidStack getConsumeFuel() {
        return FLUID_FUEL;
    }

    @Override
    public boolean extraStartCondition() {
        return isCurrentBlockAbove();
    }

    public boolean isCurrentBlockAbove(){
        if(level == null){
            return false;
        }
        return level.getBlockState(this.getBlockPos().above()).is(Blocks.DRAGON_EGG);
    }

    @NotNull
    @Override
    public DragonGeneratorEntity getSelf() {
        return this;
    }
}
