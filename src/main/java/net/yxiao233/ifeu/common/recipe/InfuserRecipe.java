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
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.List;

public class InfuserRecipe extends SerializableRecipe {
    public static List<InfuserRecipe> RECIPES = new ArrayList<>();
    public ItemStack input;
    public FluidStack inputFluid;
    public int processingTime;
    public ItemStack output;
    public InfuserRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public InfuserRecipe(ResourceLocation resourceLocation, ItemStack input, FluidStack inputFluid, int processingTime, ItemStack output){
        super(resourceLocation);
        this.input = input;
        this.inputFluid = inputFluid;
        this.processingTime = processingTime;
        this.output = output;
        this.output.getItem().onCraftedBy(this.output,null,null);
        RECIPES.add(this);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    public boolean matches(IItemHandler handler, FluidTankComponent tank){
        if (input == null || tank == null || inputFluid == null) return false;

        if(!ItemStack.isSameItem(handler.getStackInSlot(0),input)){
            return false;
        }
        return tank.drainForced(inputFluid, IFluidHandler.FluidAction.SIMULATE).getAmount() == inputFluid.getAmount();
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
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.INFUSER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INFUSER_TYPE.get();
    }
}
