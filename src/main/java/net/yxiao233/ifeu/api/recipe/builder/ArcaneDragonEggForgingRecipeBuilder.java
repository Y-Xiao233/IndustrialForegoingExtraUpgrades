package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;

import java.util.Optional;

public class ArcaneDragonEggForgingRecipeBuilder extends IFEURecipeBuilder{
    public ArcaneDragonEggForgingRecipeBuilder(ItemStack output) {
        super(output);
    }

    public ArcaneDragonEggForgingRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    @Override
    public void save(RecipeOutput output) {
        FluidStack[] stacks = this.getInputFluids();
        Optional<FluidStack> fluidStack = this.getOutputFluid() == FluidStack.EMPTY ? Optional.empty() : Optional.of(this.getOutputFluid());
        Optional<ItemStack> itemStack = this.getOutput() == ItemStack.EMPTY ? Optional.empty() : Optional.of(this.getOutput());
        ArcaneDragonEggForgingRecipe.createRecipe(output,this.getId(),new ArcaneDragonEggForgingRecipe(this.getInput(),stacks[0],stacks[1],this.getTime(),itemStack,fluidStack));
    }
}
