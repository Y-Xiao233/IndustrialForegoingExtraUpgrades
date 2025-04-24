package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.LaserDrillFluidRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class LaserDrillFluidRecipeBuilder extends IFEURecipeBuilder{
    private LaserDrillFluidRecipeBuilder(ItemStack output) {
        super(output);
    }

    private LaserDrillFluidRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    public LaserDrillFluidRecipeBuilder(FluidStack output, String id){
        super(ItemStack.EMPTY);
        this.outputFluid(output);
        this.id(id);
    }

    @Override
    public void save(RecipeOutput output) {
        LaserDrillFluidRecipe.createRecipe(output,this.getId(),this.getNameSpace(),new LaserDrillFluidRecipe(this.getOutputFluid(),this.getCatalyst(),this.getEntity(),this.getRarity()));
    }
}
