package net.yxiao233.ifeu.common.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.registry.ModRenderTypes;

import java.awt.Color;
import java.util.List;


@OnlyIn(Dist.CLIENT)
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
    public void renderFullFluid(BlockEntity entity, FluidStack stack, int combinedLight){
        RendererHelper.renderFullFluid(poseStack,multiBufferSource,entity,stack,combinedLight);
    }

    public void renderLineBox(AABB aabb, BlockPos entityPos, Color color){
        RendererHelper.renderLineBox(poseStack,multiBufferSource.getBuffer(RenderType.lines()),aabb,entityPos,color);
    }

    public void renderCenterVerticalLine(BlockPos verticalPos, int high, Color color){
        RendererHelper.renderCenterVerticalLine(poseStack,multiBufferSource.getBuffer(RenderType.lines()),verticalPos,high,entityPos,color);
    }

    public void renderCenterVerticalLine(BlockPos verticalPos, int high, Color color, boolean absoluteCenter){
        if(!absoluteCenter){
            RendererHelper.renderVerticalLine(poseStack,multiBufferSource.getBuffer(RenderType.lines()),verticalPos,high,entityPos,color,0.5F,0F,0.5F);
        }else{
            renderCenterVerticalLine(verticalPos,high,color);
        }
    }

    public void renderAllBatchedGhostBlock(java.util.List<BlockPos> posList, BlockState blockState){
        RendererHelper.renderAllBatchedGhostBlock(poseStack,multiBufferSource,entityPos,posList,blockState);
    }

    public void renderAllBatchedGhostBlockWhileIsNotCurrent(Level level, List<BlockPos> posList, BlockState blockState){
        RendererHelper.renderAllBatchedGhostBlockWhileIsNotCurrent(poseStack,multiBufferSource,entityPos,level,posList,blockState);
    }

    public void renderBlockLineBox(BlockPos curPos, Color color){
        RendererHelper.renderBlockLineBox(poseStack,multiBufferSource,curPos,entityPos,color);
    }

    public void renderBlockLineBoxWithFaces(BlockPos curPos, Color color){
        RendererHelper.renderBlockLineBoxWithFaces(poseStack,multiBufferSource,curPos,entityPos,color);
    }
}