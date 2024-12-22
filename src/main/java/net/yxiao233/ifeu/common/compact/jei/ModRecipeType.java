package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.recipe.RecipeType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

public class ModRecipeType {
    private static final String nameSpace = IndustrialForegoingExtraUpgrades.MODID;
    public static RecipeType<InfuserRecipe> INFUSER = RecipeType.create(nameSpace,"infuseer", InfuserRecipe.class);
}
