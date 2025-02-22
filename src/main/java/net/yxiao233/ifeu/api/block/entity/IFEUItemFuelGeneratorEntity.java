package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.block.tile.IndustrialGeneratorTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.common.utils.SlotElementsUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;

public abstract class IFEUItemFuelGeneratorEntity<T extends IFEUItemFuelGeneratorEntity<T>> extends BetterIndustrialGeneratorTile<T> {
    public int getPowerPerTick;
    public int getExtractionRate;
    public int getMaxProgress;
    public int getMaxStoredPower;
    @Save
    public SidedInventoryComponent<T> input;
    public IFEUItemFuelGeneratorEntity(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, blockPos, blockState);
        init();
    }

    public abstract SlotElementsUtil getSlotElements();
    public abstract Class<?> getGeneratorConfigClass();
    public abstract ItemStack getConsumeFuel();

    public void init(){
        SlotElementsUtil util = getSlotElements();
        this.addInventory(this.input = (SidedInventoryComponent<T>) new SidedInventoryComponent<T>(util.getId(), util.getXPos(), util.getYPos(), util.getSize(), util.getPosition())
                .setColor(util.getColor())
                .setRange(util.getRange()[0],util.getRange()[1])
                .setOutputFilter(util.getOutputFilter())
                .setInputFilter(util.getInputFilter())
                .setComponentHarness(this.getSelf()));

        int powerPerTick,extractionRate,maxProgress,maxStoredPower;
        try {
            Class<?> clazz = getGeneratorConfigClass();
            Field field1 = clazz.getField("powerPerTick");
            Field field2 = clazz.getField("extractionRate");
            Field field3 = clazz.getField("maxProgress");
            Field field4 = clazz.getField("maxStoredPower");
            powerPerTick = field1.getInt(field1.getName());
            extractionRate = field2.getInt(field2.getName());
            maxProgress = field3.getInt(field3.getName());
            maxStoredPower = field4.getInt(field4.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.getPowerPerTick = powerPerTick;
        this.getExtractionRate = extractionRate;
        this.getMaxProgress = maxProgress;
        this.getMaxStoredPower = maxStoredPower;
    }

    @Override
    public int consumeFuel() {
        ItemStack stack = getConsumeFuel();
        if(stack == null || stack == ItemStack.EMPTY){
            return 0;
        }
        if(input.getStackInSlot(0).is(stack.getItem()) && input.getStackInSlot(0).getCount() >= stack.getCount()){
            input.extractItem(0,stack.getCount(),false);
            return this.getMaxProgress;
        }
        return 0;
    }

    @Override
    public boolean canStart() {
        return input.getStackInSlot(0).getCount() >= getConsumeFuel().getCount() && this.getEnergyStorage().getEnergyStored() < this.getEnergyStorage().getMaxEnergyStored();
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
