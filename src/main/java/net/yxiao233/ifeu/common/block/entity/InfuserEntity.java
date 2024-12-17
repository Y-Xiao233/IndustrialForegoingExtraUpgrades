package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.jetbrains.annotations.NotNull;

public class InfuserEntity extends IndustrialProcessingTile<InfuserEntity> {
    private int maxProgress;
    private int powerPerTick;
    @Save
    private SidedInventoryComponent<InfuserEntity> input;
    @Save
    private SidedFluidTankComponent<InfuserEntity> inputFluid;
    @Save
    private SidedInventoryComponent<InfuserEntity> output;
    private InfuserRecipe currentRecipe;
    public InfuserEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.INFUSER, 102, 41, blockPos, blockState);
        int slotSpacing = 22;

        this.addInventory(this.input = (SidedInventoryComponent<InfuserEntity>) new SidedInventoryComponent<InfuserEntity>("input",54+slotSpacing,18+slotSpacing,1,0)
                .setColor(DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setInputFilter((itemStack, integer) -> !canIncrease())
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setComponentHarness(this));

        this.addTank(this.inputFluid = (SidedFluidTankComponent<InfuserEntity>) new SidedFluidTankComponent<InfuserEntity>("input_fluid",8000,52,19,1)
                .setColor(DyeColor.LIME)
                .setTankType(FluidTankComponent.Type.NORMAL)
                .setComponentHarness(this)
                .setOnContentChange(() -> checkForRecipe()));

        this.addInventory(this.output = (SidedInventoryComponent<InfuserEntity>) new SidedInventoryComponent<InfuserEntity>("output", 129, 22, 3, 2)
                .setColor(DyeColor.ORANGE)
                .setRange(1, 3)
                .setInputFilter((stack, integer) -> false)
                .setComponentHarness(this));

        this.maxProgress = 100;
        this.powerPerTick = 90;
    }

    private void checkForRecipe(){
        if(isServer()){
            if(currentRecipe != null && currentRecipe.matches(input, inputFluid)){
                return;
            }
            currentRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<InfuserRecipe>) ModRecipes.INFUSER_TYPE.get()).stream().filter(recipe -> recipe.matches(input,inputFluid)).findFirst().orElse(null);
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
        return currentRecipe != null && ItemHandlerHelper.insertItem(output, currentRecipe.output.copy(),true).isEmpty();
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            if (currentRecipe != null) {
                InfuserRecipe infuserRecipe = currentRecipe;
                inputFluid.drainForced(infuserRecipe.inputFluid, IFluidHandler.FluidAction.EXECUTE);
                input.getStackInSlot(0).shrink(1);

                ItemStack outputStack = infuserRecipe.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, null);
                ItemHandlerHelper.insertItem(output, outputStack, false);
                checkForRecipe();
            }
        };
    }
    @NotNull
    @Override
    protected EnergyStorageComponent<InfuserEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(10000,10,20);
    }
    @Override
    protected int getTickPower() {
        return powerPerTick;
    }

    @Override
    public int getMaxProgress() {
        return currentRecipe != null ? currentRecipe.processingTime : maxProgress;
    }

    @NotNull
    @Override
    public InfuserEntity getSelf() {
        return this;
    }

}
