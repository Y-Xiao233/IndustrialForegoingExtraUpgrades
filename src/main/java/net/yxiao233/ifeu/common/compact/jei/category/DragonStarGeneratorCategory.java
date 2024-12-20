package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.common.compact.jei.AbstractJEICategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.registry.ModBlocks;

public class DragonStarGeneratorCategory extends AbstractJEICategory {
    public static final Component TITLE = Component.translatable("block.ifeu.dragon_star_generator");
    public DragonStarGeneratorCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.DRAGON_STAR_GENERATOR, TITLE, ModBlocks.DRAGON_STAR_GENERATOR.getKey().get().asItem(), 160, 82);
    }

    @Override
    public RecipeType getTypeInstance() {
        return null;
    }

    @Override
    public void draw(Recipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, Recipe recipe, IFocusGroup iFocusGroup) {

    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, Object object, IFocusGroup iFocusGroup) {

    }

    public static class DragonStarGeneratorRecipeWrapper {
        private TagKey<Item> stack;
        public DragonStarGeneratorRecipeWrapper(TagKey<Item> stack) {
            this.stack = stack;
        }
        public TagKey<Item> getStack() {
            return stack;
        }
    }
}
