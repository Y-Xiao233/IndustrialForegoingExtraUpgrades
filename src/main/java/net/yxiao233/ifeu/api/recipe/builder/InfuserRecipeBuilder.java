package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

public class InfuserRecipeBuilder extends IFEURecipeBuilder{
    public InfuserRecipeBuilder(ItemStack output) {
        super(output);
    }

    public InfuserRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    @Override
    public void save() {
        new InfuserRecipe(this.getLocation(),this.getInput(),this.getInputFluid(),this.getTime(),this.getOutput());
    }
}
