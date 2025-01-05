package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.recipe.RecipeType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;
import net.yxiao233.ifeu.common.recipe.BlockRightClickRecipe;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

public class ModRecipeType {
    private static final String nameSpace = IndustrialForegoingExtraUpgrades.MODID;
    public static RecipeType<InfuserRecipe> INFUSER = RecipeType.create(nameSpace,"infuseer", InfuserRecipe.class);
    public static RecipeType<ArcaneDragonEggForgingRecipe> ARCANE_DRAGON_EGG_FORGING = RecipeType.create(nameSpace,"arcane_dragon_egg_forging", ArcaneDragonEggForgingRecipe.class);
    public static RecipeType<BlockRightClickRecipe> BLOCK_RIGHT_CLICK = RecipeType.create(nameSpace,"block_right_click", BlockRightClickRecipe.class);
}
