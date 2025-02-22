package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.jei.category.IFEUFluidFuelGeneratorCategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.DragonGeneratorRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class DragonGeneratorCategory extends IFEUFluidFuelGeneratorCategory<DragonGeneratorRecipe> {

    public DragonGeneratorCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.DRAGON_GENERATOR,null, ModBlocks.DRAGON_GENERATOR.asItem());
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.DRAGON_GENERATOR_TYPE.get();
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonGeneratorConfig.class;
    }
}
