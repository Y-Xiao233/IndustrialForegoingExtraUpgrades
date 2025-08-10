package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.recipe.PrecisionShapelessRecipe;

public class PrecisionShapelessRecipeBuilder extends IFEURecipeBuilder{
    public PrecisionShapelessRecipeBuilder(ItemStack output) {
        super(output);
    }

    public PrecisionShapelessRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        PrecisionShapelessRecipe.createRecipe(recipeOutput,this.getId(),new PrecisionShapelessRecipe(this.getInputs(),this.getOutput(),this.getChance()));
    }
}
