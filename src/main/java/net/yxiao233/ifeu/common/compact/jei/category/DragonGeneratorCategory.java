package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.yxiao233.ifeu.api.jei.category.IFEUFluidFuelGeneratorCategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.DragonGeneratorRecipe;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;

public class DragonGeneratorCategory extends IFEUFluidFuelGeneratorCategory<RecipeHolder<DragonGeneratorRecipe>, DragonGeneratorRecipe> {

    public DragonGeneratorCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.DRAGON_GENERATOR,null, IFEUBlocks.DRAGON_GENERATOR.asItem());
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonGeneratorConfig.class;
    }
}
