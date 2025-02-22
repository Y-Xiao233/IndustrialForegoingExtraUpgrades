package net.yxiao233.ifeu.api.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.EnergyBarScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.SlotsScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.api.jei.AbstractJEICategory;
import net.yxiao233.ifeu.api.recipe.ItemGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.Objects;

public abstract class IFEUItemFuelGeneratorCategory<R extends ItemGeneratorSerializableRecipe> extends AbstractJEICategory<R> {
    private static final Component TITLE = Component.literal("null");

    public IFEUItemFuelGeneratorCategory(IGuiHelper helper, RecipeType<R> type, Component title, Item icon) {
        super(helper,type,TITLE, icon, 95, 85);
        if(icon != null){
            this.title = Objects.requireNonNullElseGet(title, icon::getDescription);
        }
    }

    public abstract Class<?> getGeneratorConfigClass();

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, R recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,16,33).addIngredient(VanillaTypes.ITEM_STACK,recipe.input);
    }

    @Override
    public void draw(R recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Background
        EnergyBarScreenAddon.drawBackground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 72, 12, 0, 0);
        //Input
        SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER,16,33,0,0,1, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.LIGHT_BLUE.getFireworkColor()), integer -> true, 1);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 42, 41 - 8);
        //EnergyBar
        int generated = recipe.progressTime * recipe.powerPerTick;
        EnergyBarScreenAddon.drawForeground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 72, 12, 0, 0, generated, (int) Math.max(50000, Math.ceil(generated)));
        addEnergyBarTooltip(guiGraphics,getGeneratorConfigClass(),18,56,72,12,mouseX,mouseY);
        //Information
        if(recipe.isOnlyForPreview()){
            drawTextureWithTooltip(guiGraphics, AllGuiTextures.JEI_INFORMATION,Component.translatable("jei.ifeu.view.information").withStyle(ChatFormatting.RED),0,66,mouseX,mouseY);
        }
    }
}
