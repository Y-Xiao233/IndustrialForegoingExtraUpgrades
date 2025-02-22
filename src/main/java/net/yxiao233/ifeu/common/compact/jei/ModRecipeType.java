package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.recipe.RecipeType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.*;

public class ModRecipeType {
    private static final String nameSpace = IndustrialForegoingExtraUpgrades.MODID;
    public static RecipeType<InfuserRecipe> INFUSER = RecipeType.create(nameSpace,"infuser", InfuserRecipe.class);
    public static RecipeType<ArcaneDragonEggForgingRecipe> ARCANE_DRAGON_EGG_FORGING = RecipeType.create(nameSpace,"arcane_dragon_egg_forging", ArcaneDragonEggForgingRecipe.class);
    public static RecipeType<BlockRightClickRecipe> BLOCK_RIGHT_CLICK = RecipeType.create(nameSpace,"block_right_click", BlockRightClickRecipe.class);
    public static RecipeType<DragonStarGeneratorRecipe> DRAGON_STAR_GENERATOR = RecipeType.create(nameSpace,"dragon_star_generator", DragonStarGeneratorRecipe.class);
    public static RecipeType<ShapedRecipe> SHAPED = RecipeType.create(nameSpace,"shaped", ShapedRecipe.class);
    public static RecipeType<ShapelessRecipe> SHAPELESS = RecipeType.create(nameSpace,"shapeless", ShapelessRecipe.class);
    public static RecipeType<DragonGeneratorRecipe> DRAGON_GENERATOR = RecipeType.create(nameSpace,"dragon_generator", DragonGeneratorRecipe.class);
}
