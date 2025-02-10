package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.common.config.machine.InfuserConfig;
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

        this.addTank(this.inputFluid = (SidedFluidTankComponent<InfuserEntity>) new SidedFluidTankComponent<InfuserEntity>("input_fluid",InfuserConfig.maxInputTankSize,52,19,1)
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
        this.powerPerTick = InfuserConfig.powerPerTick;
    }

    private void checkForRecipe(){
        if(isServer()){
            if(currentRecipe != null && currentRecipe.matches(input, inputFluid)){
                return;
            }

            //Json Recipes
            currentRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<InfuserRecipe>) ModRecipes.INFUSER_TYPE.get()).stream().filter(recipe -> recipe.matches(input,inputFluid)).findFirst().orElse(null);


            //Compact Recipes
            if(currentRecipe == null && input.getStackInSlot(0).is(Items.BUCKET)){
                Item item = inputFluid.getFluid().getFluid().getBucket();
                if(item instanceof BucketItem){
                    FluidStack fluidStack = new FluidStack(inputFluid.getFluid().getFluid(),1000);
                    currentRecipe = new InfuserRecipe(Items.BUCKET.getDefaultInstance(),fluidStack,200,item.getDefaultInstance());
                }
            }
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

            return hasCurrentItem && hasEnoughCount;
        }else {
            return false;
        }
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            if (currentRecipe != null) {
                InfuserRecipe infuserRecipe = currentRecipe;

                input.getStackInSlot(0).shrink(infuserRecipe.input.getCount());

                inputFluid.drainForced(infuserRecipe.inputFluid, IFluidHandler.FluidAction.EXECUTE);

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
        return new EnergyStorageComponent<>(InfuserConfig.maxStoredPower,10,20);
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
