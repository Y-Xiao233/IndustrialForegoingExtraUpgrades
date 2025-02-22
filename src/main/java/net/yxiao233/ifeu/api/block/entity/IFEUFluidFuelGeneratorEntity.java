package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.common.utils.FluidTankElementsUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;

public abstract class IFEUFluidFuelGeneratorEntity<T extends IFEUFluidFuelGeneratorEntity<T>> extends BetterIndustrialGeneratorTile<T>{
    public int getPowerPerTick;
    public int getExtractionRate;
    public int getMaxProgress;
    public int getMaxStoredPower;
    public int maxInputTankSize;
    @Save
    public SidedFluidTankComponent<T> inputFluid;
    public IFEUFluidFuelGeneratorEntity(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, blockPos, blockState);
        init();
    }

    public abstract FluidTankElementsUtil getFluidTankElements();
    public abstract Class<?> getGeneratorConfigClass();
    public abstract FluidStack getConsumeFuel();
    public abstract boolean extraStartCondition();

    public void init(){
        int powerPerTick,extractionRate,maxProgress,maxStoredPower,maxInputTankSize;
        try {
            Class<?> clazz = getGeneratorConfigClass();
            Field field1 = clazz.getField("powerPerTick");
            Field field2 = clazz.getField("extractionRate");
            Field field3 = clazz.getField("maxProgress");
            Field field4 = clazz.getField("maxStoredPower");
            Field field5 = clazz.getField("maxInputTankSize");
            powerPerTick = field1.getInt(field1.getName());
            extractionRate = field2.getInt(field2.getName());
            maxProgress = field3.getInt(field3.getName());
            maxStoredPower = field4.getInt(field4.getName());
            maxInputTankSize = field5.getInt(field5.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.getPowerPerTick = powerPerTick;
        this.getExtractionRate = extractionRate;
        this.getMaxProgress = maxProgress;
        this.getMaxStoredPower = maxStoredPower;
        this.maxInputTankSize = maxInputTankSize;

        FluidTankElementsUtil util = getFluidTankElements();
        this.addTank(this.inputFluid = (SidedFluidTankComponent<T>) new SidedFluidTankComponent<T>(util.getId(),this.maxInputTankSize, util.getXPos(), util.getYPos(), util.getPosition())
                .setColor(util.getColor())
                .setTankType(util.getType())
                .setComponentHarness(this.getSelf()));
    }

    @Override
    public int consumeFuel() {
        FluidStack stack = getConsumeFuel();
        if (inputFluid.getFluid().isFluidEqual(stack) && extraStartCondition()) {
            inputFluid.drainForced(stack, IFluidHandler.FluidAction.EXECUTE);
            return this.getMaxProgress;
        }else{
            return 0;
        }
    }


    @Override
    public boolean canStart() {
        return inputFluid.getFluid().isFluidEqual(getConsumeFuel()) && extraStartCondition();
    }

    @Override
    public int getEnergyProducedEveryTick() {
        int powerPerTick;
        try {
            Class<?> clazz = getGeneratorConfigClass();
            Field field4 = clazz.getField("powerPerTick");
            powerPerTick = field4.getInt(field4.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return powerPerTick;
    }

    @Override
    public ProgressBarComponent<T> getProgressBar() {
        return new ProgressBarComponent<T>(30,20,0,15)
                .setComponentHarness(this.getSelf())
                .setBarDirection(ProgressBarComponent.BarDirection.VERTICAL_UP)
                .setColor(DyeColor.CYAN);
    }

    @Override
    public int getEnergyCapacity() {
        int maxStoredPower;
        try {
            Class<?> clazz = getGeneratorConfigClass();
            Field field4 = clazz.getField("maxStoredPower");
            maxStoredPower = field4.getInt(field4.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return maxStoredPower;
    }

    @Override
    public int getExtractingEnergy() {
        int extractionRate;
        try {
            Class<?> clazz = getGeneratorConfigClass();
            Field field4 = clazz.getField("extractionRate");
            extractionRate = field4.getInt(field4.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return extractionRate;
    }
}
