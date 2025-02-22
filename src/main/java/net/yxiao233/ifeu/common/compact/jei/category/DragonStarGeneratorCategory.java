package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.jei.category.IFEUItemFuelGeneratorCategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.DragonStarGeneratorRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class DragonStarGeneratorCategory extends IFEUItemFuelGeneratorCategory<DragonStarGeneratorRecipe> {
    public DragonStarGeneratorCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.DRAGON_STAR_GENERATOR,null,ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get().asItem());
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.DRAGON_STAR_GENERATOR_TYPE.get();
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonStarGeneratorConfig.class;
    }
}
