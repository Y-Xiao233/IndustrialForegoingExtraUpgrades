package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public class FermenterCategory implements IRecipeCategory<FermenterCategory.FermenterRecipeWrapper> {
    private final IGuiHelper guiHelper;
    private final IDrawable tankOverlay;
    private final Component title;

    public FermenterCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.tankOverlay = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath("industrialforegoing", "textures/gui/jei.png"), 1, 207, 12, 48);
        this.title = Component.translatable("jei.ifeu.recipe.title.fermenter");
    }

    public @NotNull RecipeType<FermenterCategory.FermenterRecipeWrapper> getRecipeType() {
        return ModRecipeType.FERMENTER;
    }

    public @NotNull Component getTitle() {
        return this.title;
    }

    @SuppressWarnings("removal")
    public IDrawable getBackground() {
        return this.guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath("ifeu", "textures/gui/jei.png"), 0, 0, 70, 50);
    }

    @Nullable
    public IDrawable getIcon() {
        return null;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, FermenterCategory.FermenterRecipeWrapper recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 3).addIngredients(Ingredient.of(recipe.stack));
        builder.addSlot(RecipeIngredientRole.CATALYST, 1, 31).addIngredients(Ingredient.of(recipe.catalyst)).addRichTooltipCallback((recipeSlotView, tooltip) -> {
            tooltip.add(Component.translatable("jei.ifeu.catalyst").withStyle(ChatFormatting.GOLD));
        });
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 1).setFluidRenderer(1000L, false, 12, 48).setOverlay(this.tankOverlay, 0, 0).addIngredient(NeoForgeTypes.FLUID_STACK, recipe.fluid());
    }

    public record FermenterRecipeWrapper(TagKey<Item> stack, TagKey<Item> catalyst, FluidStack fluid) {
    }
}
