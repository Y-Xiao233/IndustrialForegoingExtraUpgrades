package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.api.plant.PlantRecollectable;
import com.buuz135.industrial.block.agriculturehusbandry.tile.HydroponicBedTile;
import com.buuz135.industrial.block.tile.IndustrialAreaWorkingTile;
import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.buuz135.industrial.config.machine.resourceproduction.HydroponicBedConfig;
import com.buuz135.industrial.registry.IFRegistries;
import com.buuz135.industrial.utils.apihandlers.plant.TreePlantRecollectable;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.item.HydroponicSimulationProcessorItem;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Mixin(HydroponicBedTile.class)
public abstract class MixinHydroponicBedTile extends IndustrialWorkingTile<HydroponicBedTile>{
    @Shadow private ProgressBarComponent<HydroponicBedTile> etherBuffer;
    @Shadow private SidedFluidTankComponent<HydroponicBedTile> ether;
    @Shadow private SidedFluidTankComponent<HydroponicBedTile> water;
    @Shadow private SidedInventoryComponent<HydroponicBedTile> output;
    @Save
    private SidedInventoryComponent<HydroponicBedTile> simulation_slot;
    private PlantRecollectable cachedRecollectable = null;
    private int errorAttempts = 0;
    public MixinHydroponicBedTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, int estimatedPower, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, estimatedPower, blockPos, blockState);
    }


    @Inject(method = "<init>", at = @At("TAIL"))
    private void onTileInit(BlockPos blockPos, BlockState blockState, CallbackInfo ci){
        addInventory(this.simulation_slot = (SidedInventoryComponent<HydroponicBedTile>) new SidedInventoryComponent<HydroponicBedTile>("simulation", 70 + 18 * 2, 80, 1, 3)
                .setColor(DyeColor.LIME)
                .setInputFilter((stack, integer) -> stack.getItem().equals(ModContents.HYDROPONIC_SIMULATION_PROCESSOR.get()))
                .setOutputFilter((stack, integer) -> false)
        );
    }

    /**
     * @author y_xiao233
     * @reason mixin
     */
    @Overwrite
    public IndustrialWorkingTile<HydroponicBedTile>.WorkAction work(){
        if (this.etherBuffer.getProgress() <= 0 && this.ether.getFluidAmount() > 0) {
            this.ether.drainForced(1, IFluidHandler.FluidAction.EXECUTE);
            this.etherBuffer.setProgress(this.etherBuffer.getMaxProgress());
        }
        if (hasEnergy(1000)) {
            BlockPos up = this.worldPosition.above();
            BlockState state = this.level.getBlockState(up);
            Supplier<PlantRecollectable> plantRecollectableSupplier = () -> {
                if (errorAttempts >= 15) {
                    industrialForegoingExtraUpgrades$findRecollectable(level, up, state);
                    errorAttempts = 0;
                }
                if (cachedRecollectable == null) {
                    industrialForegoingExtraUpgrades$findRecollectable(level, up, state);
                } else if (cachedRecollectable != null && !cachedRecollectable.canBeHarvested(level, up, state)) {
                    ++errorAttempts;
                    return null;
                }
                return cachedRecollectable;
            };
            if (!this.level.isEmptyBlock(up) && this.water.getFluidAmount() >= 10) {
                if (state.getBlock() instanceof BonemealableBlock) {
                    BonemealableBlock growable = (BonemealableBlock) this.level.getBlockState(up).getBlock();
                    if (growable.isValidBonemealTarget(this.level, up, this.level.getBlockState(up),true)) {
                        if (this.etherBuffer.getProgress() > 0) {
                            growable.performBonemeal((ServerLevel) this.level, this.level.random, up, this.level.getBlockState(up));
                            this.etherBuffer.setProgress(this.etherBuffer.getProgress() - 1);
                        } else {
                            for (int i = 0; i < 4; i++) {
                                this.level.getBlockState(up).randomTick((ServerLevel) this.level, up, this.level.random);
                            }
                        }
                        this.water.drainForced(10, IFluidHandler.FluidAction.EXECUTE);
                        return new WorkAction(1, HydroponicBedConfig.powerPerOperation);
                    } else if (this.etherBuffer.getProgress() > 0) {
                        industrialForegoingExtraUpgrades$tryToHarvestReplant(this.level, up, state, this.output, this.etherBuffer, this, plantRecollectableSupplier, this.simulation_slot.getStackInSlot(0));
                        return new WorkAction(1, HydroponicBedConfig.powerPerOperation);
                    }
                } else {
                    if (!industrialForegoingExtraUpgrades$tryToHarvestReplant(this.level, up, state, this.output, this.etherBuffer, this, plantRecollectableSupplier, this.simulation_slot.getStackInSlot(0))) {
                        if (this.etherBuffer.getProgress() > 0) {
                            for (int i = 0; i < 10; i++) {
                                this.level.getBlockState(up).randomTick((ServerLevel) this.level, up, this.level.random);
                            }
                            this.etherBuffer.setProgress(this.etherBuffer.getProgress() - 1);
                        } else {
                            for (int i = 0; i < 4; i++) {
                                this.level.getBlockState(up).randomTick((ServerLevel) this.level, up, this.level.random);
                            }
                        }
                        this.water.drainForced(10, IFluidHandler.FluidAction.EXECUTE);
                    }
                    return new WorkAction(1, HydroponicBedConfig.powerPerOperation);
                }
            }
        }
        return new WorkAction(1, 0);
    }


    @Unique
    private static boolean industrialForegoingExtraUpgrades$tryToHarvestReplant(Level level, BlockPos up, BlockState state, IItemHandler output, ProgressBarComponent<?> etherBuffer, IndustrialWorkingTile tile, Supplier<PlantRecollectable> plantSupplier, ItemStack simulationOutput) {
        var cachedRecollectable = plantSupplier.get();
        if (cachedRecollectable != null) {
            List<ItemStack> drops = new ArrayList<>();
            if (cachedRecollectable instanceof TreePlantRecollectable) {
                while (cachedRecollectable.canBeHarvested(level, up, state)) {
                    drops.addAll(cachedRecollectable.doHarvestOperation(level, up, state));
                }
            } else {
                drops.addAll(cachedRecollectable.doHarvestOperation(level, up, state));
            }
            var planted = ItemStack.EMPTY;
            if (level.isEmptyBlock(up)) {
                for (ItemStack drop : drops) {
                    if (drop.getItem() instanceof IPlantable) {
                        planted = drop.copyWithCount(1);
                        level.setBlockAndUpdate(up, ((IPlantable)drop.getItem()).getPlant(level, up));
                        drop.shrink(1);
                        break;
                    }
                    if (drop.getItem() instanceof BlockItem && ((BlockItem)drop.getItem()).getBlock() instanceof IPlantable) {
                        planted = drop.copyWithCount(1);
                        level.setBlockAndUpdate(up, ((IPlantable)((BlockItem)drop.getItem()).getBlock()).getPlant(level, up));
                        drop.shrink(1);
                        break;
                    }
                }
            }
            if (!simulationOutput.isEmpty() && simulationOutput.getItem() instanceof HydroponicSimulationProcessorItem) {
                var sim = new HydroponicSimulationProcessorItem.Simulation(simulationOutput.getTag());
                sim.acceptExecution(planted, drops);
                simulationOutput.setTag(sim.toNBT(new CompoundTag()));
            }

            AtomicInteger apple = new AtomicInteger(0);
            List<ItemStack> list = new ArrayList<>();
            int tier = AugmentInventoryHelper.getAugmentTier(tile, IFEUAugmentTypes.APPLE);
            drops.forEach(stack -> {
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

            if (tile instanceof IndustrialAreaWorkingTile<?> && cachedRecollectable.shouldCheckNextPlant(level, up, level.getBlockState(up))) {
                ((IndustrialAreaWorkingTile<?>) tile).increasePointer();
            }
            etherBuffer.setProgress(etherBuffer.getProgress() - 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem){
            return AugmentInventoryHelper.canAccept(this.getAugmentInventory(),augment);
        }
        return super.canAcceptAugment(augment);
    }


    @Unique
    private void industrialForegoingExtraUpgrades$findRecollectable(Level level, BlockPos up, BlockState state) {
        Optional<PlantRecollectable> optional = IFRegistries.PLANT_RECOLLECTABLES_REGISTRY.get().getValues().stream().filter(plantRecollectable -> plantRecollectable.canBeHarvested(level, up, state)).findFirst();
        optional.ifPresent(plantRecollectable -> cachedRecollectable = plantRecollectable);
    }
}
