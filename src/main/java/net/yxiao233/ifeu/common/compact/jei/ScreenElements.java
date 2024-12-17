package net.yxiao233.ifeu.common.compact.jei;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ScreenElements {
    @OnlyIn(Dist.CLIENT)
    void render(GuiGraphics guiGraphics, int x, int y);
    void render(GuiGraphics guiGraphics, int x, int y, int extraValue,ExtraType extraType);
    void render(GuiGraphics guiGraphics, int x, int y, int width, int height);
    int getWidth();
    int getHeight();

    public enum ExtraType{
        HEIGHT,
        WIDTH;
    }
}
