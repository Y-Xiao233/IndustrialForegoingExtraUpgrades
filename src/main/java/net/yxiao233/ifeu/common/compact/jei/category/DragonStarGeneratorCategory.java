package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.jei.category.IFEUItemFuelGeneratorCategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.DragonStarGeneratorRecipe;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

public class DragonStarGeneratorCategory extends IFEUItemFuelGeneratorCategory<DragonStarGeneratorRecipe> {
    public DragonStarGeneratorCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.DRAGON_STAR_GENERATOR, null, IFEUBlocks.DRAGON_STAR_GENERATOR.getBlock().asItem());
    }

    @Override
    public RecipeType getTypeInstance() {
        return IFEURecipes.DRAGON_STAR_GENERATOR_TYPE.get();
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonStarGeneratorConfig.class;
    }
}
