package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.FluidExtractorRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidExtractorRecipeBuilder extends IFEURecipeBuilder{
    private FluidExtractorRecipeBuilder(ItemStack output) {
        super(output);
    }

    private FluidExtractorRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    public FluidExtractorRecipeBuilder(FluidStack output, String id){
        super(ItemStack.EMPTY);
        this.outputFluid(output);
        this.id(id);
    }

    @Override
    public void save(RecipeOutput output) {
        FluidExtractorRecipe.createRecipe(output,this.getId(),new FluidExtractorRecipe(this.getInputBlock(),this.getResult(),this.getBreakChance(),this.getOutputFluid(),this.isDefault()));
    }
}
