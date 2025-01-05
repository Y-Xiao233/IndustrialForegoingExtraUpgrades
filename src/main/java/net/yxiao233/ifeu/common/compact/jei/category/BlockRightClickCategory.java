package net.yxiao233.ifeu.common.compact.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.common.compact.jei.AbstractJEICategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.recipe.BlockRightClickRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class BlockRightClickCategory extends AbstractJEICategory<BlockRightClickRecipe> {
    public static final Component TITLE = Component.translatable("recipe.ifeu.block_right_click");
    public BlockRightClickCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.BLOCK_RIGHT_CLICK, TITLE, ModContents.DEAD_DRAGON_EGG_ITEM.get(), 140, 62);
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.BLOCK_RIGHT_CLICK_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BlockRightClickRecipe recipe, IFocusGroup focusGroup) {
        //Block
        builder.addSlot(RecipeIngredientRole.INPUT,50,39)
                .addIngredient(VanillaTypes.ITEM_STACK,recipe.block.asItem().getDefaultInstance())
                .addTooltipCallback(addText("jei.ifeu.world",ChatFormatting.GOLD));
        //handItem
        builder.addSlot(RecipeIngredientRole.INPUT,22,17)
                .addIngredient(VanillaTypes.ITEM_STACK,recipe.handItem)
                .addTooltipCallback(addText("jei.ifeu.hand",ChatFormatting.AQUA));
        //result
        builder.addSlot(RecipeIngredientRole.OUTPUT,110,27)
                .addIngredient(VanillaTypes.ITEM_STACK,recipe.result.asItem().getDefaultInstance());
    }

    @Override
    public void draw(BlockRightClickRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //handItem
        AllGuiTextures.BASIC_SLOT.render(guiGraphics,21,16);
        //Click
        drawTextureWithTooltip(guiGraphics,AllGuiTextures.RIGHT_CLICK,Component.translatable("jei.ifeu.block_right_click"),53,18,mouseX,mouseY);
        //Block
        AllGuiTextures.BASIC_SLOT.render(guiGraphics,49,38);
        //result
        AllGuiTextures.BASIC_SLOT.render(guiGraphics,109,26);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 80, 30);
    }
}
