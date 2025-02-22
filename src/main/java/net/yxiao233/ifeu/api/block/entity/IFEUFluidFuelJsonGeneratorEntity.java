package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.api.recipe.FluidGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.utils.FluidTankElementsUtil;

public abstract class IFEUFluidFuelJsonGeneratorEntity<T extends IFEUFluidFuelJsonGeneratorEntity<T>> extends IFEUFluidFuelGeneratorEntity<T>{
    private FluidGeneratorSerializableRecipe recipe;
    public IFEUFluidFuelJsonGeneratorEntity(BlockWithTile blockWithTile, BlockPos blockPos, BlockState blockState) {
        super(blockWithTile, blockPos, blockState);
    }

    public abstract <R extends FluidGeneratorSerializableRecipe> RecipeType<R> setRecipeType();

    @Override
    public FluidTankElementsUtil getFluidTankElements() {
        return new FluidTankElementsUtil();
    }

    @Override
    public FluidStack getConsumeFuel() {
        return FluidStack.EMPTY;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        checkForRecipe();
    }

    @Override
    public int consumeFuel() {
        if(recipe != null && extraStartCondition()){
            FluidStack stack = recipe.inputFluid;
            if(inputFluid.getFluid().is(stack.getFluid()) && inputFluid.getFluid().getAmount() >= stack.getAmount()){
                inputFluid.drainForced(stack, IFluidHandler.FluidAction.EXECUTE);
                return recipe.progressTime;
            }
        }else{
            checkForRecipe();
        }
        return 0;
    }

    @Override
    public boolean canStart() {
        if (recipe != null && extraStartCondition()) {
            FluidStack stack = recipe.inputFluid;
            return inputFluid.getFluid().is(stack.getFluid()) && inputFluid.getFluid().getAmount() >= stack.getAmount() && this.getEnergyStorage().getEnergyStored() < this.getEnergyStorage().getMaxEnergyStored();
        } else {
            checkForRecipe();
            return false;
        }
    }

    private void checkForRecipe(){
        if(isServer()){
            if(recipe != null && recipe.matches(inputFluid,this.getEnergyStorage())){
                return;
            }

            recipe = RecipeUtil.getRecipes(this.level,setRecipeType()).stream().filter(recipe -> recipe.matches(inputFluid,this.getEnergyStorage())).findFirst().orElse(null);
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
}
