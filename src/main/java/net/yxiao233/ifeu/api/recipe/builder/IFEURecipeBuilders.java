package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class IFEURecipeBuilders {
    public static ShapedRecipeBuilder shapedRecipe(ItemStack output, String id){
        return new ShapedRecipeBuilder(output,id);
    }

    public static ShapedRecipeBuilder shapedRecipe(ItemStack output){
        return new ShapedRecipeBuilder(output);
    }

    public static DissolutionChamberRecipeBuilder dissolutionChamberRecipe(ItemStack output, String id){
        return new DissolutionChamberRecipeBuilder(output,id);
    }

    public static DissolutionChamberRecipeBuilder dissolutionChamberRecipe(ItemStack output){
        return new DissolutionChamberRecipeBuilder(output);
    }

    public static InfuserRecipeBuilder infuserRecipe(ItemStack output, String id){
        return new InfuserRecipeBuilder(output,id);
    }

    public static InfuserRecipeBuilder infuserRecipe(ItemStack output){
        return new InfuserRecipeBuilder(output);
    }

    public static LaserDrillFluidRecipeBuilder laserDrillFluidRecipe(FluidStack output, String id){
        return new LaserDrillFluidRecipeBuilder(output,id);
    }

    public static FluidExtractorRecipeBuilder fluidExtractorRecipe(FluidStack output, String id){
        return new FluidExtractorRecipeBuilder(output,id);
    }

    public static ArcaneDragonEggForgingRecipeBuilder arcaneDragonEggForgingRecipe(ItemStack output){
        return new ArcaneDragonEggForgingRecipeBuilder(output);
    }

    public static ArcaneDragonEggForgingRecipeBuilder arcaneDragonEggForgingRecipe(ItemStack output, String id){
        return new ArcaneDragonEggForgingRecipeBuilder(output,id);
    }

    public static ShapelessRecipeBuilder shapelessRecipe(ItemStack output){
        return new ShapelessRecipeBuilder(output);
    }

    public static ShapelessRecipeBuilder shapelessRecipe(ItemStack output, String id){
        return new ShapelessRecipeBuilder(output,id);
    }

    public static PrecisionShapedRecipeBuilder precisionShapedRecipe(ItemStack output){
        return new PrecisionShapedRecipeBuilder(output);
    }

    public static PrecisionShapedRecipeBuilder precisionShapedRecipe(ItemStack output, String id){
        return new PrecisionShapedRecipeBuilder(output,id);
    }

    public static PrecisionShapelessRecipeBuilder precisionShapelessRecipe(ItemStack output){
        return new PrecisionShapelessRecipeBuilder(output);
    }

    public static PrecisionShapelessRecipeBuilder precisionShapelessRecipe(ItemStack output, String id){
        return new PrecisionShapelessRecipeBuilder(output,id);
    }
}
