package net.yxiao233.ifeu.api.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.yxiao233.ifeu.api.block.entity.IFEUBlackHoleCapacitorEntity;

import java.util.Objects;

public class IFEUBlackHoleCapacitorRenderer implements BlockEntityRenderer<IFEUBlackHoleCapacitorEntity> {
    public IFEUBlackHoleCapacitorRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(IFEUBlackHoleCapacitorEntity tile, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tile.shouldDisplay()) {
            Direction facing = tile.getFacingDirection();

            this.renderText(matrixStack, bufferIn, combinedOverlayIn,tile.getRenderText(),facing, 0.015F);
        }

    }

    private void renderText(PoseStack matrix, MultiBufferSource renderer, int overlayLight, Component text, Direction side, float maxScale) {
        matrix.pushPose();
        matrix.translate(0.0, -0.3725, 0.0);
        switch (side) {
            case SOUTH:
                matrix.translate(0.0, 1.0, 1.0E-4);
                matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
                break;
            case NORTH:
                matrix.translate(1.0, 1.0, 0.9999);
                matrix.mulPose(Axis.YP.rotationDegrees(180.0F));
                matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
                break;
            case EAST:
                matrix.translate(1.0E-4, 1.0, 1.0);
                matrix.mulPose(Axis.YP.rotationDegrees(90.0F));
                matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
                break;
            case WEST:
                matrix.translate(0.9999, 1.0, 0.0);
                matrix.mulPose(Axis.YP.rotationDegrees(-90.0F));
                matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
        }

        float displayWidth = 1.0F;
        float displayHeight = 1.0F;
        matrix.translate(displayWidth / 2.0F, 1.0F, displayHeight / 2.0F);
        matrix.mulPose(Axis.XP.rotationDegrees(-90.0F));
        Font font = Minecraft.getInstance().font;
        int requiredWidth = Math.max(font.width(text), 1);
        Objects.requireNonNull(font);
        int requiredHeight = 9 + 2;
        float scaler = 0.4F;
        float scaleX = displayWidth / (float)requiredWidth;
        float scale = scaleX * scaler;
        if (maxScale > 0.0F) {
            scale = Math.min(scale, maxScale);
        }

        matrix.scale(scale, -scale, scale);
        int realHeight = (int)Math.floor((double)(displayHeight / scale));
        int realWidth = (int)Math.floor((double)(displayWidth / scale));
        int offsetX = (realWidth - requiredWidth) / 2;
        int offsetY = (realHeight - requiredHeight) / 2;
        font.drawInBatch(text, (float)offsetX - (float)realWidth / 2.0F, (float)(3 + offsetY) - (float)realHeight / 2.0F, overlayLight, false, matrix.last().pose(), renderer, Font.DisplayMode.NORMAL, 0, 15728880);
        matrix.popPose();
    }
}
