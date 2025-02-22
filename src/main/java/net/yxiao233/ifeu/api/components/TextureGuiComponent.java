package net.yxiao233.ifeu.api.components;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.BasicScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;

import java.util.Objects;

public abstract class TextureGuiComponent extends BasicScreenAddon {
    private boolean tooltip;
    private boolean itemBackGround;
    protected TextureGuiComponent(int x, int y) {
        super(x,y);
        this.itemBackGround = true;
        this.tooltip = true;
    }

    @Override
    public int getXSize() {
        return getTexture().getWidth();
    }

    @Override
    public int getYSize() {
        return getTexture().getHeight();
    }

    @Override
    public void drawBackgroundLayer(GuiGraphics guiGraphics, Screen screen, IAssetProvider provider, int guiX, int guiY, int mouseX, int mouseY, float partialTicks) {
        if(getTexture() != AllGuiTextures.EMPTY){
            if(this.itemBackGround){
                AssetUtil.drawAsset(guiGraphics, screen, provider.getAsset(AssetTypes.ITEM_BACKGROUND), guiX + getPosX() - 2, guiY + getPosY() - 2);
            }
        }
        getTexture().render(guiGraphics,guiX+getPosX(),guiY+getPosY());
        renderTooltip(guiGraphics,guiX,guiY,mouseX,mouseY);
    }

    @Override
    public void drawForegroundLayer(GuiGraphics guiGraphics, Screen screen, IAssetProvider iAssetProvider, int guiX, int guiY, int mouseX, int mouseY, float partialTicks) {
    }
    public TextureGuiComponent withoutTooltip() {
        this.tooltip = false;
        return this;
    }

    public TextureGuiComponent withoutItemBackGround(){
        this.itemBackGround = false;
        return this;
    }

    public void renderTooltip(GuiGraphics guiGraphics, int guiX, int guiY, int mouseX, int mouseY){
        if(this.tooltip && isMouseOver(guiX,guiY,mouseX,mouseY) && !Objects.equals(getComponent(), Component.empty())){
            guiGraphics.renderTooltip(Minecraft.getInstance().font,getComponent(),mouseX,mouseY);
        }
    }

    public boolean isMouseOver(int guiX, int guiY, double mouseX, double mouseY) {
        return mouseX >= (double)this.getPosX() + guiX && mouseX <= (double)(this.getPosX() + guiX + this.getXSize()) && mouseY >= (double)this.getPosY() + guiY && mouseY <= (double)(this.getPosY() + guiY + this.getYSize());
    }

    public abstract AllGuiTextures getTexture();
    public abstract Component getComponent();
}
