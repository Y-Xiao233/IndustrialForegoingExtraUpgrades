package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.recipe.RecipeType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.DragonStarGeneratorCategory;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

public class ModRecipeType {
    public static String nameSpace = IndustrialForegoingExtraUpgrades.MODID;
    public static RecipeType<InfuserRecipe> INFUSER = RecipeType.create(nameSpace,"infuseer", InfuserRecipe.class);
    public static RecipeType<DragonStarGeneratorCategory.DragonStarGeneratorRecipeWrapper> DRAGON_STAR_GENERATOR = RecipeType.create(IndustrialForegoingExtraUpgrades.MODID, "dragon_star_generaot", DragonStarGeneratorCategory.DragonStarGeneratorRecipeWrapper.class);
}
