package net.yxiao233.ifeu.common.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public enum AllGuiTextures implements ScreenElement {
    //Slot
    CHANCE_SLOT("widgets",20, 156, 18, 18),
    BASIC_SLOT("widgets",0,0,18,18),
    //click
    RIGHT_CLICK("widgets",227, 0, 10, 14),
    LEFT_CLICK("widgets",195,0,10,14);
    //Other

    public final ResourceLocation location;
    public final int width, height;
    public final int startX, startY;
    private AllGuiTextures(String location, int startX, int startY, int width, int height) {
        this.location = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "textures/gui/" + location + ".png");
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(location, x, y, startX, startY, width, height);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, int extraValue, ExtraType extraType) {
        if(extraType == ExtraType.HEIGHT){
            guiGraphics.blit(location, x, y, startX, startY, width, extraValue);
        }else{
            guiGraphics.blit(location, x, y, startX, startY, extraValue, height);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.blit(location, x, y, startX, startY, width, height);
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
