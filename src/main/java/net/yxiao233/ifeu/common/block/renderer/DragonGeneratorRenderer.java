package net.yxiao233.ifeu.common.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.yxiao233.ifeu.common.block.entity.DragonGeneratorEntity;
import net.yxiao233.ifeu.common.utils.RendererHelper;

public class DragonGeneratorRenderer implements BlockEntityRenderer<DragonGeneratorEntity> {
    public DragonGeneratorRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(DragonGeneratorEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        if(entity.isCurrentBlockAbove()){
            return;
        }

        RendererHelper.renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entity.getBlockPos(),entity.getBlockPos().above(), Blocks.DRAGON_EGG.defaultBlockState());
    }
}
