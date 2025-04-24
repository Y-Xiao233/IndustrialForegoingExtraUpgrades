package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
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
    public void save(RecipeOutput output) {
        InfuserRecipe.createRecipe(output,this.getId(),new InfuserRecipe(this.getInput(),this.getInputFluid(),this.getTime(),this.getOutput()));
    }
}
