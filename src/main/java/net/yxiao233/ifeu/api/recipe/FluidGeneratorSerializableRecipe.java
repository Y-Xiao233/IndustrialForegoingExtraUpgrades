package net.yxiao233.ifeu.api.recipe;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class FluidGeneratorSerializableRecipe extends SerializableRecipe {
    public static List<FluidGeneratorSerializableRecipe> RECIPES = new ArrayList<>();
    public FluidStack inputFluid;
    public int progressTime;
    public int powerPerTick;

    private FluidGeneratorSerializableRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }
    public FluidGeneratorSerializableRecipe(ResourceLocation resourceLocation, FluidStack inputFluid, int progressTime, int powerPerTick){
        super(resourceLocation);
        this.inputFluid = inputFluid;
        this.progressTime = progressTime;
        this.powerPerTick = powerPerTick;
        RECIPES.add(this);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    public boolean matches(IFluidHandler fluidHandler, EnergyStorageComponent<?> energyStorageComponent) {
        return fluidHandler.getFluidInTank(0).isFluidEqual(this.inputFluid) && energyStorageComponent.getEnergyStored() <= energyStorageComponent.getMaxEnergyStored();
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) getRecipeSerializer();
    }

    public abstract boolean isOnlyForPreview();
    public abstract RecipeSerializer<?> getRecipeSerializer();
}
