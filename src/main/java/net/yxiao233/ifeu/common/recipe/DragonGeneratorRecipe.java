package net.yxiao233.ifeu.common.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.api.recipe.FluidGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class DragonGeneratorRecipe extends FluidGeneratorSerializableRecipe {
    public DragonGeneratorRecipe(ResourceLocation resourceLocation, FluidStack inputFluid, int progressTime, int powerPerTick) {
        super(resourceLocation, inputFluid, progressTime, powerPerTick);
    }

    @Override
    public boolean isOnlyForPreview() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getRecipeSerializer() {
        return ModRecipes.DRAGON_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DRAGON_GENERATOR_TYPE.get();
    }
}
