package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.common.config.machine.ArcaneDragonForgingConfig;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.jetbrains.annotations.NotNull;

public class ArcaneDragonEggForgingEntity extends IndustrialProcessingTile<ArcaneDragonEggForgingEntity> {
    private int maxProgress;
    private int powerPerTick;
    @Save
    private SidedInventoryComponent<ArcaneDragonEggForgingEntity> input;
    @Save
    private SidedFluidTankComponent<ArcaneDragonEggForgingEntity> inputFluid1;
    @Save
    private SidedFluidTankComponent<ArcaneDragonEggForgingEntity> inputFluid2;
    @Save
    private SidedInventoryComponent<ArcaneDragonEggForgingEntity> output;
    @Save
    private SidedFluidTankComponent<ArcaneDragonEggForgingEntity> outputFluid;
    private ArcaneDragonEggForgingRecipe currentRecipe;
    public ArcaneDragonEggForgingEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.ARCANE_DRAGON_EGG_FORGING, 102, 41, blockPos, blockState);
        int slotSpacing = 22;

        this.addInventory(this.input = (SidedInventoryComponent<ArcaneDragonEggForgingEntity>) new SidedInventoryComponent<ArcaneDragonEggForgingEntity>("input",54+slotSpacing,18+slotSpacing,1,0)
                .setColor(DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setInputFilter((itemStack, integer) -> !canIncrease())
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setComponentHarness(this));

        this.addTank(this.inputFluid1 = (SidedFluidTankComponent<ArcaneDragonEggForgingEntity>) new SidedFluidTankComponent<ArcaneDragonEggForgingEntity>("input_fluid1", ArcaneDragonForgingConfig.maxInputTankSize,32,19,1)
                .setColor(DyeColor.LIME)
                .setTankType(FluidTankComponent.Type.NORMAL)
                .setComponentHarness(this)
                .setOnContentChange(() -> checkForRecipe()));

        this.addTank(this.inputFluid2 = (SidedFluidTankComponent<ArcaneDragonEggForgingEntity>) new SidedFluidTankComponent<ArcaneDragonEggForgingEntity>("input_fluid2", ArcaneDragonForgingConfig.maxInputTankSize,52,19,2)
                .setColor(DyeColor.ORANGE)
                .setTankType(FluidTankComponent.Type.NORMAL)
                .setComponentHarness(this)
                .setOnContentChange(() -> checkForRecipe()));

        this.addInventory(this.output = (SidedInventoryComponent<ArcaneDragonEggForgingEntity>) new SidedInventoryComponent<ArcaneDragonEggForgingEntity>("output", 129, 22, 3, 3)
                .setColor(DyeColor.MAGENTA)
                .setRange(1, 3)
                .setInputFilter((stack, integer) -> false)
                .setComponentHarness(this));

        this.addTank(this.outputFluid = (SidedFluidTankComponent<ArcaneDragonEggForgingEntity>) new SidedFluidTankComponent<ArcaneDragonEggForgingEntity>("output_fluid", ArcaneDragonForgingConfig.maxOutputTankSize, 149, 20, 4)
                .setColor(DyeColor.RED)
                .setComponentHarness(this)
                .setTankAction(FluidTankComponent.Action.DRAIN));

        this.maxProgress = 100;
        this.powerPerTick = ArcaneDragonForgingConfig.powerPerTick;
    }
    private void checkForRecipe(){
        if(isServer()){
            if(currentRecipe != null && currentRecipe.matches(input, inputFluid1,inputFluid2)){
                return;
            }

            currentRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<ArcaneDragonEggForgingRecipe>) ModRecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get()).stream().filter(recipe -> recipe.matches(input,inputFluid1,inputFluid2)).findFirst().orElse(null);
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        checkForRecipe();
    }

    @Override
    public void setLevel(Level p_155231_) {
        super.setLevel(p_155231_);
        checkForRecipe();
    }
    @Override
    public boolean canIncrease() {
        if(currentRecipe != null){
            boolean hasCurrentItem = ItemHandlerHelper.insertItem(output, currentRecipe.output.copy(),true).isEmpty();
            boolean hasEnoughCount = input.getStackInSlot(0).getCount() >= currentRecipe.input.getCount();
            boolean canFillFluid = (this.currentRecipe.outputFluid == null || this.outputFluid.fillForced(this.currentRecipe.outputFluid.copy(), IFluidHandler.FluidAction.SIMULATE) == this.currentRecipe.outputFluid.getAmount());

            return currentRecipe != null && hasCurrentItem && hasEnoughCount && canFillFluid;
        }else {
            return false;
        }
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            if (this.currentRecipe != null) {
                ArcaneDragonEggForgingRecipe arcaneDragonEggForgingRecipe = this.currentRecipe;

                this.inputFluid1.drainForced(arcaneDragonEggForgingRecipe.inputFluid1, IFluidHandler.FluidAction.EXECUTE);
                this.inputFluid2.drainForced(arcaneDragonEggForgingRecipe.inputFluid2, IFluidHandler.FluidAction.EXECUTE);

                input.getStackInSlot(0).shrink(arcaneDragonEggForgingRecipe.input.getCount());

                if (arcaneDragonEggForgingRecipe.outputFluid != null && !arcaneDragonEggForgingRecipe.outputFluid.isEmpty()) {
                    this.outputFluid.fillForced(arcaneDragonEggForgingRecipe.outputFluid.copy(), IFluidHandler.FluidAction.EXECUTE);
                }

                ItemStack outputStack = arcaneDragonEggForgingRecipe.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, (Player)null);
                ItemHandlerHelper.insertItem(this.output, outputStack, false);
                this.checkForRecipe();
            }

        };
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<ArcaneDragonEggForgingEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(ArcaneDragonForgingConfig.maxStoredPower,10,20);
    }
    @Override
    protected int getTickPower() {
        return ArcaneDragonForgingConfig.powerPerTick;
    }

    @Override
    public int getMaxProgress() {
        return currentRecipe != null ? currentRecipe.processingTime : maxProgress;
    }
    @NotNull
    @Override
    public ArcaneDragonEggForgingEntity getSelf() {
        return this;
    }
}
