package net.yxiao233.ifeu.mixin.screen;

import com.hrznstudio.titanium.client.screen.addon.BasicScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.SlotsScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.config.machine.BigDissolutionChamberConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Mixin(SlotsScreenAddon.class)
public abstract class MixinSlotsScreenAddon extends BasicScreenAddon {
    protected MixinSlotsScreenAddon(int posX, int posY) {
        super(posX, posY);
    }

    @Inject(
            method = "drawAsset(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/screens/Screen;Lcom/hrznstudio/titanium/client/screen/asset/IAssetProvider;IIIIILjava/util/function/Function;Ljava/util/function/Function;ZLjava/util/function/Function;Ljava/util/function/Predicate;I)V",
            at = @At(value = "HEAD")
    )
    private static void ifeu$drawAsset(GuiGraphics guiGraphics, Screen screen, IAssetProvider provider, int handlerPosX, int handlerPosY, int guiX, int guiY, int slots, Function<Integer, Pair<Integer, Integer>> positionFunction, Function<Integer, ItemStack> slotToStackRenderMap, boolean drawColor, Function<Integer, Color> slotToColorRenderMap, Predicate<Integer> slotEnabled, int overlayDepth, CallbackInfo ci){
        if(BigDissolutionChamberConfig.inputRule == 0){
            RenderSystem.enableBlend();
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(0,0,500);
            for(int slotID = 0; slotID < slots; ++slotID) {
                if (slotEnabled.test(slotID)) {
                    int posX = (Integer)((Pair)positionFunction.apply(slotID)).getLeft();
                    int posY = (Integer)((Pair)positionFunction.apply(slotID)).getRight();
                    int count = slotToStackRenderMap.apply(slotID).getCount();
                    if(count > 1){
                        int x = count >= 10 ? 6 : 3;
                        guiGraphics.drawCenteredString(Minecraft.getInstance().font, String.valueOf(count), handlerPosX + posX + guiX + x, handlerPosY + posY + guiY , 0xC0FFFFFF);
                    }
                }
            }
            poseStack.popPose();
            RenderSystem.disableBlend();
        }
    }
}
