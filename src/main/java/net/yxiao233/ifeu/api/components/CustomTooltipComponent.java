package net.yxiao233.ifeu.api.components;

import com.hrznstudio.titanium.client.screen.addon.BasicScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.yxiao233.ifeu.common.utils.ComponentUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomTooltipComponent extends BasicScreenAddon {
    protected CustomTooltipComponent(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public int getXSize() {
        return 0;
    }

    @Override
    public int getYSize() {
        return 0;
    }

    @Override
    public void drawBackgroundLayer(GuiGraphics guiGraphics, Screen screen, IAssetProvider iAssetProvider, int guiX, int guiY, int mouseX, int mouseY, float partialTicks) {
        Font font = Minecraft.getInstance().font;
        List<FormattedCharSequence> list = new ArrayList<>();
        if(!hasTooltip()){
            return;
        }

        if(isMouseOver(guiX,guiY,mouseX,mouseY)) {
            for (Component component : getComponents()) {
                list.add(component.getVisualOrderText());
            }
            guiGraphics.renderTooltip(font,list,mouseX,mouseY);
        }
    }

    @Override
    public void drawForegroundLayer(GuiGraphics guiGraphics, Screen screen, IAssetProvider iAssetProvider, int guiX, int guiY, int mouseX, int mouseY, float partialTicks) {

    }

    public boolean isMouseOver(int guiX, int guiY, double mouseX, double mouseY) {
        return mouseX >= guiX + getPosX() && mouseX <= guiX + getPosX() + width$height()[0] && mouseY >= guiY + getPosY() && mouseY <= guiY + getPosY() + width$height()[1];
    }

    public static Component[] defaultKeyDownTips(String type){
        return new Component[]{
                ComponentUtil.simpleComponent("key.ifeu.press", ChatFormatting.WHITE).append(ComponentUtil.simpleLiteralComponent(" SHIFT ",ChatFormatting.GOLD)).append(ComponentUtil.simpleComponent("key.ifeu."+type,ChatFormatting.WHITE)).append(ComponentUtil.simpleLiteralComponent(" 10",ChatFormatting.AQUA)),
                ComponentUtil.simpleComponent("key.ifeu.press", ChatFormatting.WHITE).append(ComponentUtil.simpleLiteralComponent(" CTRL ",ChatFormatting.GOLD)).append(ComponentUtil.simpleComponent("key.ifeu."+type,ChatFormatting.WHITE)).append(ComponentUtil.simpleLiteralComponent(" 100",ChatFormatting.AQUA)),
                ComponentUtil.simpleComponent("key.ifeu.press", ChatFormatting.WHITE).append(ComponentUtil.simpleLiteralComponent(" SHIFT+CTRL ",ChatFormatting.GOLD)).append(ComponentUtil.simpleComponent("key.ifeu."+type,ChatFormatting.WHITE)).append(ComponentUtil.simpleLiteralComponent(" 1000",ChatFormatting.AQUA)),
        };
    }
    public abstract int[] width$height();
    public abstract Component[] getComponents();
    public abstract boolean hasTooltip();
}
