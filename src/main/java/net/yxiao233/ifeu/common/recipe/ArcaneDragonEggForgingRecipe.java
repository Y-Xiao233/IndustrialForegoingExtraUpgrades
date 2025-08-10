package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

import java.util.ArrayList;
import java.util.List;

public class ArcaneDragonEggForgingRecipe extends SerializableRecipe {
    public static List<ArcaneDragonEggForgingRecipe> RECIPES = new ArrayList<>();
    public ItemStack input;
    public FluidStack inputFluid1;
    public FluidStack inputFluid2;
    public int processingTime;
    public ItemStack output;
    public FluidStack outputFluid;
    public ArcaneDragonEggForgingRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public ArcaneDragonEggForgingRecipe(ResourceLocation resourceLocation, ItemStack input, FluidStack inputFluid1, FluidStack inputFluid2, int processingTime, ItemStack output, FluidStack outputFluid){
        super(resourceLocation);
        this.input = input;
        this.inputFluid1 = inputFluid1;
        this.inputFluid2 = inputFluid2;
        this.processingTime = processingTime;
        this.output = output;
        this.outputFluid = outputFluid;
        this.output.getItem().onCraftedBy(this.output,null,null);
        RECIPES.add(this);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    public boolean matches(IItemHandler handler, FluidTankComponent tank1, FluidTankComponent tank2){
        if (input == null && tank1 == null && tank2 == null && inputFluid1 == null && inputFluid2 == null) return false;

        if(!ItemStack.isSameItem(handler.getStackInSlot(0),input)){
            return false;
        }

        boolean if1 = tank1.drainForced(inputFluid1, IFluidHandler.FluidAction.SIMULATE).getAmount() == inputFluid1.getAmount();
        boolean if2 = tank2.drainForced(inputFluid2, IFluidHandler.FluidAction.SIMULATE).getAmount() == inputFluid2.getAmount();
        return if1 && if2;
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
        return output;
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) IFEURecipes.ARCANE_DRAGON_EGG_FORGING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get();
    }
}
