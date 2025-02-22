package net.yxiao233.ifeu.api.jei;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
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
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.utils.TooltipCallBackHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJEICategory<T extends Recipe<?>> implements IRecipeCategory<T> {
    public final RecipeType<T> type;
    public Component title;
    public final IDrawable background;
    public final IDrawable icon;
    public abstract net.minecraft.world.item.crafting.RecipeType<T> getTypeInstance();

    public AbstractJEICategory(IGuiHelper helper, RecipeType<T> type, Component title, Item icon, int width, int height) {
        ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"textures/gui/empty.png");
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

    @Nullable
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
    public void addTooltips(GuiGraphics guiGraphics, int width, int height, Component[] components, int x, int y,double mouseX, double mouseY){
        Font font = Minecraft.getInstance().font;
        List<FormattedCharSequence> list = new ArrayList<>();

        if(mouseX >= x && mouseY >= y && mouseX <= width + x && mouseY <= height + y) {
            for (Component component : components) {
                list.add(component.getVisualOrderText());
            }

            guiGraphics.renderTooltip(font,list,(int) mouseX, (int) mouseY);
        }
    }

    public void addEnergyBarTooltip(GuiGraphics guiGraphics, Class<?> clazz,int width, int height, int x, int y,double mouseX, double mouseY){
        int powerPerTick = 0, maxProgress = 0;
        try {
            for (int i = 0; i < clazz.getFields().length; i++) {
                Field field = clazz.getFields()[i];
                if(field.getName().equals("powerPerTick")){
                    powerPerTick = field.getInt(field.getName());
                }else if(field.getName().equals("maxProgress")){
                    maxProgress = field.getInt(field.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addTooltips(guiGraphics,width,height,new Component[]{
                        Component.translatable("jei.ifeu.power").withStyle(ChatFormatting.GOLD).append(Component.literal(String.valueOf(powerPerTick)).withStyle(ChatFormatting.WHITE)).append(Component.literal(" FE/tick").withStyle(ChatFormatting.DARK_AQUA)),
                        Component.translatable("jei.ifeu.progress").withStyle(ChatFormatting.GOLD).append(Component.literal(String.valueOf(maxProgress)).withStyle(ChatFormatting.WHITE)).append(Component.literal(" tick").withStyle(ChatFormatting.DARK_AQUA)),
                        Component.translatable("jei.ifeu.total").withStyle(ChatFormatting.GOLD).append(Component.literal(String.valueOf(maxProgress * powerPerTick)).withStyle(ChatFormatting.WHITE)).append(Component.literal(" FE").withStyle(ChatFormatting.DARK_AQUA))},
                x,y,mouseX,mouseY
        );
    }

    public void addEnergyBarTooltip(ITooltipBuilder tooltip, double mouseX, double mouseY, int stored, int capacity){
        Rectangle rec = DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.ENERGY_BACKGROUND).getArea();
        if (new Rectangle(0, 12, rec.width, rec.height).contains(mouseX, mouseY)) {
            Component[] components = new Component[2];
            String s = String.valueOf(ChatFormatting.GOLD);
            components[0] = Component.literal(s + Component.translatable("tooltip.titanium.power").getString());
            s = (new DecimalFormat()).format((long)stored);
            components[1] = Component.literal(s + String.valueOf(ChatFormatting.GOLD) + "/" + String.valueOf(ChatFormatting.WHITE) + (new DecimalFormat()).format((long)capacity) + String.valueOf(ChatFormatting.DARK_AQUA) + " FE");
            tooltip.add(FormattedText.composite(components));
        }
    }

    public void addTooltipOnTexture(GuiGraphics guiGraphics, int width, int height, Component component, int x, int y,double mouseX, double mouseY){
        Font font = Minecraft.getInstance().font;

        if(mouseX >= x && mouseY >= y && mouseX <= width + x && mouseY <= height + y) {
            guiGraphics.renderTooltip(font, component, (int) mouseX, (int) mouseY);
        }
    }
    public void drawTextureWithTooltip(GuiGraphics guiGraphics, AllGuiTextures allGuiTextures, Component component, int x, int y, double mouseX, double mouseY){
        allGuiTextures.render(guiGraphics,x,y);
        addTooltipOnTexture(guiGraphics,allGuiTextures.width,allGuiTextures.height,component,x,y,mouseX,mouseY);
    }

    public IRecipeSlotRichTooltipCallback addText(String translatableKey, ChatFormatting style){
        return (view, tooltip) ->{
            tooltip.add(Component.translatable(translatableKey).withStyle(style));
        };
    }

    public IRecipeSlotRichTooltipCallback addText(TooltipCallBackHelper... tooltips){
        return (view, tooltip) ->{
            for (int i = 0; i < tooltips.length; i++) {
                tooltip.add(tooltips[i].getComponent());
            }
        };
    }
}
