package net.yxiao233.ifeu.common.block.entity;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.api.block.entity.BetterIndustrialGeneratorTile;
import net.yxiao233.ifeu.api.block.entity.IFEUFluidFuelGeneratorEntity;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.utils.FluidTankElementsUtil;
import org.jetbrains.annotations.NotNull;

public class DragonGeneratorEntity extends IFEUFluidFuelGeneratorEntity<DragonGeneratorEntity> {
    private static final FluidStack FLUID_FUEL = new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000);

    public DragonGeneratorEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.DRAGON_GENERATOR, blockPos, blockState);
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
