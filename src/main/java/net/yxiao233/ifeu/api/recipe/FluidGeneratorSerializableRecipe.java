package net.yxiao233.ifeu.api.recipe;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public abstract class FluidGeneratorSerializableRecipe implements Recipe<CraftingInput> {
    public FluidStack inputFluid;
    public int progressTime;
    public int powerPerTick;
    public FluidGeneratorSerializableRecipe(FluidStack inputFluid, int progressTime, int powerPerTick){
        this.inputFluid = inputFluid;
        this.progressTime = progressTime;
        this.powerPerTick = powerPerTick;
    }
    public abstract boolean isOnlyForPreview();
    public boolean matches(IFluidHandler fluidHandler, EnergyStorageComponent<?> energyStorageComponent) {
        return fluidHandler.getFluidInTank(0).is(this.inputFluid.getFluid()) && fluidHandler.getFluidInTank(0).getAmount() >= this.inputFluid.getAmount() && energyStorageComponent.getEnergyStored() <= energyStorageComponent.getMaxEnergyStored();
    }
    @Override
    public boolean matches(CraftingInput p_346065_, Level p_345375_) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput p_345149_, HolderLookup.Provider p_346030_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return ItemStack.EMPTY;
    }
}
