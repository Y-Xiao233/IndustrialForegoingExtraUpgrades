package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialGeneratorTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import org.jetbrains.annotations.NotNull;

public class DragonStarGeneratorEntity extends IndustrialGeneratorTile<DragonStarGeneratorEntity> {
    private int getPowerPerTick;
    private int getExtractionRate;
    @Save
    private SidedInventoryComponent<DragonStarGeneratorEntity> input;
    public DragonStarGeneratorEntity(BlockPos blockPos, BlockState blockState) {
        //TODO 调整输入槽位置,为该机器添加JEI Category
        super(ModBlocks.DRAGON_STAR_GENERATOR, blockPos, blockState);

        this.addInventory(this.input = (SidedInventoryComponent<DragonStarGeneratorEntity>) new SidedInventoryComponent<DragonStarGeneratorEntity>("input",44,22,1,0)
                .setColor(DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setInputFilter((itemStack, integer) -> itemStack.is(ModContents.DRAGON_STAR.get()))
                .setComponentHarness(this));

        this.getPowerPerTick = DragonStarGeneratorConfig.powerPerTick;
        this.getExtractionRate = DragonStarGeneratorConfig.extractionRate;
    }

    @Override
    public int consumeFuel() {
        if(input.getStackInSlot(0).getCount() > 0){
            input.extractItem(0,1, false);
            return DragonStarGeneratorConfig.maxProgress;
        }
        return 0;
    }

    @Override
    public boolean canStart() {
        return input.getStackInSlot(0).getCount() > 0 && this.getEnergyStorage().getEnergyStored() < this.getEnergyStorage().getMaxEnergyStored();
    }

    @Override
    public int getEnergyProducedEveryTick() {
        return getPowerPerTick;
    }

    @Override
    public ProgressBarComponent<DragonStarGeneratorEntity> getProgressBar() {
        return new ProgressBarComponent(30,20,0,15)
                .setComponentHarness(this)
                .setBarDirection(ProgressBarComponent.BarDirection.VERTICAL_UP)
                .setColor(DyeColor.CYAN);
    }

    @Override
    public int getEnergyCapacity() {
        return DragonStarGeneratorConfig.maxStoredPower;
    }

    @Override
    public int getExtractingEnergy() {
        return DragonStarGeneratorConfig.extractionRate;
    }

    @NotNull
    @Override
    public DragonStarGeneratorEntity getSelf() {
        return this;
    }
}
