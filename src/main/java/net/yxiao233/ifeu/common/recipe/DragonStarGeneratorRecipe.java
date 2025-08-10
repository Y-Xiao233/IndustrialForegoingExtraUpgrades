package net.yxiao233.ifeu.common.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.recipe.ItemGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

public class DragonStarGeneratorRecipe extends ItemGeneratorSerializableRecipe {


    public DragonStarGeneratorRecipe(ResourceLocation resourceLocation, ItemStack input, int progressTime, int powerPerTick) {
        super(resourceLocation, input, progressTime, powerPerTick);
    }

    @Override
    public boolean isOnlyForPreview() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getRecipeSerializer() {
        return IFEURecipes.DRAGON_STAR_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.DRAGON_STAR_GENERATOR_TYPE.get();
    }
}
