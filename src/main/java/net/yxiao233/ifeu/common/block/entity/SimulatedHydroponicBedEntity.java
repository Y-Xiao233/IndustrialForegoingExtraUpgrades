package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.config.machine.SimulatedHydroponicBedConfig;
import net.yxiao233.ifeu.common.item.HydroponicSimulationProcessorItem;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.jetbrains.annotations.NotNull;

public class SimulatedHydroponicBedEntity extends IndustrialWorkingTile<SimulatedHydroponicBedEntity> {
    @Save
    private final SidedInventoryComponent<SimulatedHydroponicBedEntity> output;
    @Save
    private final SidedInventoryComponent<SimulatedHydroponicBedEntity> simulation_slot;
    @Save
    private final SidedInventoryComponent<SimulatedHydroponicBedEntity> seed;
    private HydroponicSimulationProcessorItem.Simulation simulation;

    public SimulatedHydroponicBedEntity(BlockPos blockPos, BlockState blockState) {
        super(IFEUBlocks.SIMULATED_HYDROPONIC_BED, SimulatedHydroponicBedConfig.powerPerOperation, blockPos, blockState);

        this.addInventory(this.output = (SidedInventoryComponent<SimulatedHydroponicBedEntity>)(new SidedInventoryComponent<SimulatedHydroponicBedEntity>("output", 45, 22, 21, 0))
                .setColor(DyeColor.ORANGE)
                .setRange(7, 3)
                .setInputFilter((stack, integer) -> false));

        this.addInventory(this.simulation_slot = (SidedInventoryComponent<SimulatedHydroponicBedEntity>)(new SidedInventoryComponent<SimulatedHydroponicBedEntity>("simulation", 106, 80, 1, 1))
                .setColor(DyeColor.LIME)
                .setInputFilter((stack, integer) -> stack.getItem().equals(IFEUContents.HYDROPONIC_SIMULATION_PROCESSOR.get()))
                .setOutputFilter((stack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> this.simulation = null));

        this.addInventory(this.seed = (SidedInventoryComponent<SimulatedHydroponicBedEntity>)(new SidedInventoryComponent<SimulatedHydroponicBedEntity>("seed", 79, 80, 1, 2))
                .setColor(DyeColor.CYAN)
                .setInputFilter((stack, integer) -> true)
                .setOutputFilter((stack, integer) -> false));
    }

    public WorkAction work() {
        if (this.hasEnergy(SimulatedHydroponicBedConfig.powerPerOperation)) {
            ItemStack simulationProcessor = this.simulation_slot.getStackInSlot(0);
            ItemStack seed = this.seed.getStackInSlot(0);
            if (!seed.isEmpty() && !simulationProcessor.isEmpty() && simulationProcessor.getItem() instanceof HydroponicSimulationProcessorItem) {
                if (this.simulation == null) {
                    this.simulation = new HydroponicSimulationProcessorItem.Simulation(simulationProcessor.getTag());
                }

                if (!ItemStack.isSameItem(seed, this.simulation.getCrop())) {
                    return new IndustrialWorkingTile<SimulatedHydroponicBedEntity>.WorkAction(1.0F, 0);
                }

                ItemStack crop = this.simulation.getCrop();
                long executions = this.simulation.getExecutions();
                if (!crop.isEmpty() && executions > 0L) {
                    double efficiency = Math.floor(HydroponicSimulationProcessorItem.calculateEfficiency((double)executions) * 100.0) / 100.0;
                    List<ItemStack> generatedDrops = new ArrayList<>();
                    Iterator var9 = this.simulation.getStats().iterator();

                    ItemStack drop;
                    while(var9.hasNext()) {
                        HydroponicSimulationProcessorItem.CountedStack simulationStack = (HydroponicSimulationProcessorItem.CountedStack)var9.next();
                        ItemStack statStack = simulationStack.stack();
                        long statAmount = simulationStack.amount();
                        double amount = (double)statAmount / (double)executions * efficiency;
                        if (amount >= 1.0) {
                            int fullAmount = (int)Math.floor(amount);
                            drop = statStack.copy();
                            drop.setCount(fullAmount);
                            generatedDrops.add(drop);
                            double fraction = amount - (double)fullAmount;
                            if (fraction > 0.0 && this.level.random.nextDouble() < fraction) {
                                ItemStack extraDrop = statStack.copy();
                                extraDrop.setCount(1);
                                generatedDrops.add(extraDrop);
                            }
                        } else if (amount > 0.0 && this.level.random.nextDouble() < amount) {
                            ItemStack stack = statStack.copy();
                            stack.setCount(1);
                            generatedDrops.add(stack);
                        }
                    }

                    if (this.level.random.nextDouble() <= SimulatedHydroponicBedConfig.chanceToIncreaseExecutions) {
                        ArrayList<ItemStack> boostDrops = new ArrayList<>();
                        Iterator var23 = this.simulation.getStats().iterator();

                        while(var23.hasNext()) {
                            HydroponicSimulationProcessorItem.CountedStack simulationStack = (HydroponicSimulationProcessorItem.CountedStack)var23.next();
                            ItemStack statStack = simulationStack.stack();
                            long statAmount = simulationStack.amount();
                            double amount = (double)statAmount / (double)executions;
                            if (amount >= 1.0) {
                                int fullAmount = (int)Math.floor(amount);
                                ItemStack stack = statStack.copy();
                                stack.setCount(fullAmount);
                                boostDrops.add(stack);
                                double fraction = amount - (double)fullAmount;
                                if (fraction > 0.0 && this.level.random.nextDouble() < fraction) {
                                    ItemStack extraDrop = statStack.copy();
                                    extraDrop.setCount(1);
                                    boostDrops.add(extraDrop);
                                }
                            } else if (amount > 0.0 && this.level.random.nextDouble() < amount) {
                                drop = statStack.copy();
                                drop.setCount(1);
                                boostDrops.add(drop);
                            }
                        }

                        this.simulation.acceptExecution(crop, boostDrops);
                        simulationProcessor.setTag(this.simulation.toNBT(new CompoundTag()));
                    }

                    AtomicInteger apple = new AtomicInteger(0);
                    List<ItemStack> list = new ArrayList<>();
                    int tier = AugmentInventoryHelper.getAugmentTier(this, IFEUAugmentTypes.APPLE);
                    generatedDrops.forEach(stack -> {
                        if(!stack.is(Items.APPLE)){
                            list.add(stack);
                        }else{
                            apple.set(apple.get() + 1);
                        }
                    });

                    if(apple.get() != 0){
                        int times = tier == 0 ? 1 : tier;
                        list.add(new ItemStack(Items.APPLE,apple.get() * times));
                    }

                    list.forEach(stack ->{
                        ItemHandlerHelper.insertItem(output, stack, false);
                    });
                    return new IndustrialWorkingTile<SimulatedHydroponicBedEntity>.WorkAction(1.0F, SimulatedHydroponicBedConfig.powerPerOperation);
                }
            }
        }

        return new IndustrialWorkingTile<SimulatedHydroponicBedEntity>.WorkAction(1.0F, 0);
    }

    @Override
    public int getMaxProgress() {
        return SimulatedHydroponicBedConfig.maxProgress;
    }

    @Nonnull
    public SimulatedHydroponicBedEntity getSelf() {
        return this;
    }

    @Override
    protected @NotNull EnergyStorageComponent<SimulatedHydroponicBedEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(SimulatedHydroponicBedConfig.maxStoredPower, 10, 20);
    }


    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem){
            return AugmentInventoryHelper.canAccept(this.getAugmentInventory(),augment);
        }
        return super.canAcceptAugment(augment);
    }
}
