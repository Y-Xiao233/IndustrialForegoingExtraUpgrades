package net.yxiao233.ifeu.common.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.gui.ScreenElement;

public enum AllGuiTextures implements ScreenElement {
    //Empty
    EMPTY("empty",0,0,0,0),
    //Slot
    CHANCE_SLOT(20, 156, 18, 18),
    BASIC_SLOT(0,0,18,18),
    //click
    RIGHT_CLICK(227, 0, 10, 14),
    LEFT_CLICK(195,0,10,14),
    //Information
    JEI_INFORMATION(240,0,16,16),
    //Controller
    TRUE(226,241,14,14),
    FALSE(241,241,14,14),
    WEATHER_CLEAR(173,240,16,16),
    WEATHER_RAIN(190,240,16,16),
    WEATHER_THUNDER(208,240,16,16),
    DAY(0,242,14,14),
    NOON(15,242,14,14),
    NIGHT(30,242,14,14),
    MIDNIGHT(45,242,14,14),
    //Shapeless
    SHAPELESS(240,21,15,15)
    ;
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

    private AllGuiTextures(int startX, int startY, int width, int height) {
        this.location = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "textures/gui/widgets.png");
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
