package net.yxiao233.ifeu.api.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.EnergyBarScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.jei.AbstractJEICategory;
import net.yxiao233.ifeu.api.recipe.FluidGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;

import java.lang.reflect.Field;
import java.util.Objects;

public abstract class IFEUFluidFuelGeneratorCategory<R extends FluidGeneratorSerializableRecipe> extends AbstractJEICategory<R> {
    private static final Component TITLE = Component.literal("null");
    private final IDrawable bigTank;
    public IFEUFluidFuelGeneratorCategory(IGuiHelper helper, mezz.jei.api.recipe.RecipeType<R> type, Component title, Item icon) {
        super(helper,type,TITLE, icon, 95, 85);
        if(icon != null){
            this.title = Objects.requireNonNullElseGet(title, icon::getDescription);
        }
        this.bigTank = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 177 + 3, 1 + 3, 12, 50);
    }
    public abstract Class<?> getGeneratorConfigClass();

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, R recipe, IFocusGroup iFocusGroup) {
        if(recipe.inputFluid != null && !recipe.inputFluid.isEmpty()){
            int maxInputTankSize;
            try {
                Field field = getGeneratorConfigClass().getField("maxInputTankSize");
                maxInputTankSize = field.getInt(field.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 16 + 3, 12 + 3).setFluidRenderer(Math.max(maxInputTankSize, 1000),false,12,50).setOverlay(bigTank,0,0).addIngredient(ForgeTypes.FLUID_STACK, recipe.inputFluid);
        }
    }

    @Override
    public void draw(R recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Background
        EnergyBarScreenAddon.drawBackground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 72, 12, 0, 0);
        //InputFluid
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 16, 12);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 42, 41 - 8);
        //EnergyBar
        int generated = recipe.progressTime * recipe.powerPerTick;
        EnergyBarScreenAddon.drawForeground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 72, 12, 0, 0, generated, (int) Math.max(50000, Math.ceil(generated)));
        addEnergyBarTooltip(guiGraphics,getGeneratorConfigClass(),18,56,72,12,mouseX,mouseY);
        //Information
        if(recipe.isOnlyForPreview()) {
            drawTextureWithTooltip(guiGraphics, AllGuiTextures.JEI_INFORMATION, Component.translatable("jei.ifeu.view.information").withStyle(ChatFormatting.RED), 0, 66, mouseX, mouseY);
        }
    }
}
