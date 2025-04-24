package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.Optional;

public class DissolutionChamberRecipeBuilder extends IFEURecipeBuilder{
    public DissolutionChamberRecipeBuilder(ItemStack output) {
        super(output);
    }

    public DissolutionChamberRecipeBuilder(ItemStack output, String id) {
        super(output,id);
    }

    @Override
    public void save(RecipeOutput output) {
        Optional<FluidStack> fluidStack = this.getOutputFluid() == FluidStack.EMPTY ? Optional.empty() : Optional.of(this.getOutputFluid());
        Optional<ItemStack> itemStack = this.getOutput() == ItemStack.EMPTY ? Optional.empty() : Optional.of(this.getOutput());
        DissolutionChamberRecipe.createRecipe(output,this.getId(),new DissolutionChamberRecipe(this.getInputs(),this.getInputFluid(),this.getTime(),itemStack,fluidStack));
    }
}
