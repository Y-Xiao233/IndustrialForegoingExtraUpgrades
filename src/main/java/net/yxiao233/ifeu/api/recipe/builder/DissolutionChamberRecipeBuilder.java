package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import net.minecraft.world.item.ItemStack;

public class DissolutionChamberRecipeBuilder extends IFEURecipeBuilder{
    public DissolutionChamberRecipeBuilder(ItemStack output) {
        super(output);
    }

    public DissolutionChamberRecipeBuilder(ItemStack output, String id) {
        super(output,id);
    }

    @Override
    public void save() {
        new DissolutionChamberRecipe(this.getLocation(),this.getInputs(),this.getInputFluid(),this.getTime(),this.getOutput(),this.getOutputFluid());
    }
}
