package net.yxiao233.ifeu.common.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.yxiao233.ifeu.common.registry.ModRenderTypes;

public class RendererProvider {
    private final PoseStack poseStack;
    private final MultiBufferSource multiBufferSource;
    private final BlockPos entityPos;

    public RendererProvider(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos entityPos) {
        this.poseStack = poseStack;
        this.multiBufferSource = multiBufferSource;
        this.entityPos = entityPos;
    }

    public void renderSingleBatchedGhostBlock(BlockPos renderBlockPos, BlockState blockState){
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;
        VertexConsumer consumer = multiBufferSource.getBuffer(ModRenderTypes.GHOST);
        if(level == null){
            return;
        }
        RendererHelper.renderGhostBlockContext(entityPos,poseStack,() ->{
            poseStack.pushPose();
            poseStack.translate(renderBlockPos.getX(),renderBlockPos.getY(),renderBlockPos.getZ());
            minecraft.getBlockRenderer().renderBatched(blockState,renderBlockPos,level,poseStack,consumer,false,level.getRandom(), ModelData.EMPTY,null);
            poseStack.popPose();
        });
    }
}