package net.yxiao233.ifeu.api.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.yxiao233.ifeu.api.block.entity.IFEUAreaWorkingTile;
import net.yxiao233.ifeu.common.utils.PlatformBuilderUtil;
import net.yxiao233.ifeu.common.utils.RendererProvider;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PlatformBuilderRenderer implements BlockEntityRenderer<IFEUAreaWorkingTile<?>> {
    public PlatformBuilderRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(IFEUAreaWorkingTile<?> entity, float v, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, int i1) {
        if(entity != null && entity.isShowingArea()){
            BlockPos entityPos = entity.getBlockPos();
            RendererProvider provider = new RendererProvider(poseStack,multiBufferSource,entityPos);

            Color color = new Color(255,255,0);
            provider.renderCenterVerticalLine(entityPos,5,color);

            provider.renderAllBatchedGhostBlockWhileIsNotCurrent(entity.getLevel(), PlatformBuilderUtil.getFrameBlockPosList(entity),entity.getFrameBlockState());
            provider.renderAllBatchedGhostBlockWhileIsNotCurrent(entity.getLevel(),PlatformBuilderUtil.getLandPosList(entity),entity.getLandBlockState());
        }
    }


    @Override
    public boolean shouldRenderOffScreen(@NotNull IFEUAreaWorkingTile<?> pBlockEntity) {
        return true;
    }
}