package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.recipe.ShapelessRecipe;

public class ShapelessRecipeBuilder extends IFEURecipeBuilder{
    public ShapelessRecipeBuilder(ItemStack output) {
        super(output);
    }

    public ShapelessRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    @Override
    public void save() {
        new ShapelessRecipe(this.getLocation(),this.getInputs(),this.getInputFluid(),this.getOutput());
    }
}
