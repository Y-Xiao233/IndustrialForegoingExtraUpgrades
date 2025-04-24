package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;

public class ArcaneDragonEggForgingRecipeBuilder extends IFEURecipeBuilder{
    public ArcaneDragonEggForgingRecipeBuilder(ItemStack output) {
        super(output);
    }

    public ArcaneDragonEggForgingRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    @Override
    public void save() {
        FluidStack[] stacks = this.getInputFluids();
        new ArcaneDragonEggForgingRecipe(this.getLocation(),this.getInput(),stacks[0],stacks[1],this.getTime(),this.getOutput(),this.getOutputFluid());
    }
}
