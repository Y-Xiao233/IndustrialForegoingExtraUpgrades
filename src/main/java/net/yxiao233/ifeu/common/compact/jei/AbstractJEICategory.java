package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.utils.TooltipCallBackHelper;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractJEICategory<T extends Recipe<?>> implements IRecipeCategory<T> {
    public final RecipeType<T> type;
    public final Component title;
    public final IDrawable background;
    public final IDrawable icon;
    public abstract net.minecraft.world.item.crafting.RecipeType<T> getTypeInstance();

    public AbstractJEICategory(IGuiHelper helper, RecipeType<T> type, Component title, Item icon, int width, int height) {
        ResourceLocation TEXTURE = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"textures/jei/empty.png");
        this.type = type;
        this.title = title;
        this.background = helper.createDrawable(TEXTURE,0,0,width,height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(icon));
    }
    @Override
    public RecipeType<T> getRecipeType() {
        return type;
    }
    @Override
    public Component getTitle() {
        return title;
    }
    @Override
    public IDrawable getBackground() {
        return background;
    }
    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }
    @Override
    public abstract void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, T t, IFocusGroup iFocusGroup);
    @Override
    public abstract void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY);

    public void drawTextureWithTooltip(GuiGraphics guiGraphics, AllGuiTextures allGuiTextures, Component component, int x, int y , double mouseX, double mouseY){
        Font font = Minecraft.getInstance().font;

        allGuiTextures.render(guiGraphics,x,y);

        if(mouseX >= x && mouseY >= y && mouseX <= allGuiTextures.width + x && mouseY <= allGuiTextures.height + y) {
            guiGraphics.renderTooltip(font, component, (int) mouseX, (int) mouseY);
        }
    }

    public IRecipeSlotTooltipCallback addText(String translatableKey, ChatFormatting style){
        return (view, tooltip) ->{
            tooltip.add(1,Component.translatable(translatableKey).withStyle(style));
        };
    }

    public IRecipeSlotTooltipCallback addText(TooltipCallBackHelper... tooltips){
        return (view, tooltip) ->{
            for (int i = 0; i < tooltips.length; i++) {
                tooltip.add(tooltips[i].getComponent());
            }
        };
    }
}
