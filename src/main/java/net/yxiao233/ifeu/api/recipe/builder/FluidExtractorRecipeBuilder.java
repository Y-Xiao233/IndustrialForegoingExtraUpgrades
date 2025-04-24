package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.FluidExtractorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

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
    public void save() {
        new FluidExtractorRecipe(this.getLocation(),this.getInputBlock(),this.getResult(),this.getBreakChance(),this.getOutputFluid(),this.isDefault());
    }
}
