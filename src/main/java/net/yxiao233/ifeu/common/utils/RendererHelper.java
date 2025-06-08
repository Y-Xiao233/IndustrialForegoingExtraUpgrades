package net.yxiao233.ifeu.common.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.config.machine.FluidCraftingTableConfig;
import net.yxiao233.ifeu.common.registry.ModRenderTypes;
import org.apache.commons.lang3.tuple.Triple;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class RendererHelper {
    //render fluid
    public static final Function<ResourceLocation, TextureAtlasSprite> TEXTURE_GETTER =
            location -> Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location);
    public static Triple<Float, Float, Float> intToRGB(int color) {
        float red, green, blue;
        red = (float)(color >> 16 & 255) / 255.0F;
        green = (float)(color >> 8 & 255) / 255.0F;
        blue = (float)(color & 255) / 255.0F;
        return Triple.of(red, green, blue);
    }

    @SuppressWarnings("deprecation")
    public static void renderFluidContext(FluidStack fluid, PoseStack matrixStack, IFluidContextRender render) {
        if(fluid != null && fluid.getAmount() > 0) {
            matrixStack.pushPose();

            RenderSystem.enableBlend();
            RenderSystem.disableCull();

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            Minecraft.getInstance().getTextureManager().bindForSetup(TextureAtlas.LOCATION_BLOCKS);

            render.render();

            RenderSystem.disableBlend();
            matrixStack.popPose();
        }
    }

    public static TextureAtlasSprite getFluidIcon(Fluid fluid, Direction side) {
        return getFluidIcon(new FluidStack(fluid, 1000), side);
    }

    public static TextureAtlasSprite getFluidIcon(FluidStack fluid, Direction side) {
        if(side == null) side = Direction.UP;

        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid.getFluid());
        TextureAtlasSprite icon = TEXTURE_GETTER.apply(renderProperties.getFlowingTexture(fluid));
        if(icon == null || (side == Direction.UP || side == Direction.DOWN)) {
            icon = TEXTURE_GETTER.apply(renderProperties.getStillTexture(fluid));
        }

        return icon;
    }

    public static void renderFullFluid(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockEntity entity, FluidStack fluidStack, int combinedLight){
        renderFluidContext(fluidStack,poseStack,()->{
            float height = ((fluidStack.getAmount() * 1.0F) / FluidCraftingTableConfig.maxInputTankSize);
            height = Math.min(height, 0.995F);
            int brightness = Math.max(combinedLight, fluidStack.getFluid().getFluidType().getLightLevel(fluidStack));
            int l2 = brightness >> 0x10 & 0xFFFF;
            int i3 = brightness & 0xFFFF;

            TextureAtlasSprite icon = RendererHelper.getFluidIcon(fluidStack, Direction.UP);
            IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluidStack.getFluid());
            Triple<Float, Float, Float> color = RendererHelper.intToRGB(renderProperties.getTintColor(fluidStack.getFluid().defaultFluidState(), entity.getLevel(), entity.getBlockPos()));

            VertexConsumer vb = multiBufferSource.getBuffer(RenderType.text(icon.atlasLocation()));
            Matrix4f matrix = poseStack.last().pose();

            // Top face
            vb.addVertex(matrix, 0.005F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);

            // Bottom face
            vb.addVertex(matrix, 0.005F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);

            // South face
            vb.addVertex(matrix, 0.005F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);

            // North face
            vb.addVertex(matrix, 0.005F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);

            // East face
            vb.addVertex(matrix, 0.995F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.995F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);

            // West face
            vb.addVertex(matrix, 0.005F, 0.005F, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV0()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, height, 0.995F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU0(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, height, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV1()).setUv2(l2, i3);
            vb.addVertex(matrix, 0.005F, 0.005F, 0.005F).setColor(color.getLeft(), color.getMiddle(), color.getRight(), 1).setUv(icon.getU1(), icon.getV0()).setUv2(l2, i3);
        });

    }

    public static interface IFluidContextRender {
        public void render();
    }

    //render ghost block
    public static void renderGhostBlockContext(BlockPos entityBlockPos, PoseStack poseStack, RendererHelper.IBlockContextRender render) {
        poseStack.pushPose();
        poseStack.translate(-entityBlockPos.getX(), -entityBlockPos.getY(), -entityBlockPos.getZ());

        render.render();

        poseStack.popPose();
    }

    public static void renderSingleBatchedGhostBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos entityBlockPos, BlockPos renderBlockPos, BlockState blockState){
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;
        VertexConsumer consumer = multiBufferSource.getBuffer(ModRenderTypes.GHOST);
        if(level == null){
            return;
        }
        renderGhostBlockContext(entityBlockPos,poseStack,() ->{
            poseStack.pushPose();
            poseStack.translate(renderBlockPos.getX(),renderBlockPos.getY(),renderBlockPos.getZ());
            minecraft.getBlockRenderer().renderBatched(blockState,renderBlockPos,level,poseStack,consumer,false,level.getRandom(), ModelData.EMPTY,null);
            poseStack.popPose();
        });
    }

    public static void renderSingleGhostBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos entityBlockPos, BlockPos renderBlockPos, BlockState blockState, int i1, int i2){
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;
        if(level == null){
            return;
        }
        renderGhostBlockContext(entityBlockPos,poseStack,() ->{
            poseStack.pushPose();
            poseStack.translate(renderBlockPos.getX(),renderBlockPos.getY(),renderBlockPos.getZ());
            minecraft.getBlockRenderer().renderSingleBlock(blockState,poseStack,multiBufferSource,i1,i2,ModelData.EMPTY,ModRenderTypes.GHOST);
            poseStack.popPose();
        });
    }

    public interface IBlockContextRender {
        public void render();
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderAllBatchedGhostBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos entityBlockPos, List<BlockPos> posList, BlockState blockState){
        posList.forEach(pos ->{
            renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityBlockPos,pos,blockState);
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderAllBatchedGhostBlockWhileIsNotCurrent(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos entityBlockPos, Level level, List<BlockPos> posList, BlockState blockState){
        posList.forEach(pos ->{
            if(!level.getBlockState(pos).is(blockState.getBlock())){
                renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityBlockPos,pos,blockState);
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderLineBox(PoseStack poseStack, VertexConsumer consumer, AABB aabb, BlockPos entityPos, Color color){
        RenderSystem.lineWidth(Math.max(2.5F, (float)Minecraft.getInstance().getWindow().getWidth() / 1920.0F * 2.5F));
        LevelRenderer.renderLineBox(poseStack,consumer,aabb.move(-entityPos.getX(),-entityPos.getY(),-entityPos.getZ()),(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderBlockLineBox(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos curPos, BlockPos entityPos, Color color){
        RenderSystem.lineWidth(Math.max(2.5F, (float)Minecraft.getInstance().getWindow().getWidth() / 1920.0F * 2.5F));
        LevelRenderer.renderLineBox(poseStack, multiBufferSource.getBuffer(RenderType.lines()), new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1).move((double)(-entityPos.getX()), (double)(-entityPos.getY()), (double)(-entityPos.getZ())), (float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderBlockLineBox(PoseStack poseStack, MultiBufferSource multiBufferSource, RenderType renderType,BlockPos curPos, BlockPos entityPos, Color color){
        RenderSystem.lineWidth(Math.max(2.5F, (float)Minecraft.getInstance().getWindow().getWidth() / 1920.0F * 2.5F));
        LevelRenderer.renderLineBox(poseStack, multiBufferSource.getBuffer(renderType), new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1).move((double)(-entityPos.getX()), (double)(-entityPos.getY()), (double)(-entityPos.getZ())), (float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderBlockLineBoxWithFaces(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos curPos, BlockPos entityPos, Color color){
        RenderSystem.lineWidth(Math.max(2.5F, (float)Minecraft.getInstance().getWindow().getWidth() / 1920.0F * 2.5F));
        LevelRenderer.renderLineBox(poseStack, multiBufferSource.getBuffer(RenderType.lines()), new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1).move((double)(-entityPos.getX()), (double)(-entityPos.getY()), (double)(-entityPos.getZ())), (float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
        renderFaces(poseStack, multiBufferSource, new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1), entityPos, color);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderFaces(PoseStack stack,MultiBufferSource multiBufferSource, BlockPos curPos, BlockPos entityPos, Color color){
        renderFaces(stack,multiBufferSource,new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1),entityPos,color);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderFaces(PoseStack stack,MultiBufferSource multiBufferSource,RenderType renderType, BlockPos curPos, BlockPos entityPos, Color color){
        renderFaces(stack,multiBufferSource,renderType,new AABB(curPos.getX(),curPos.getY(),curPos.getZ(),curPos.getX() + 1, curPos.getY() + 1, curPos.getZ() + 1),entityPos,color);
    }


    @OnlyIn(Dist.CLIENT)
    public static void renderFaces(PoseStack stack,MultiBufferSource multiBufferSource, AABB aabb, BlockPos entityPos, Color color){
        renderFaces(stack,multiBufferSource,ModRenderTypes.WORK_AREA,aabb,entityPos,color);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderFaces(PoseStack stack, MultiBufferSource renderTypeBuffer, RenderType renderType, AABB pos, BlockPos entityPos, Color color) {
        double x = -entityPos.getX();
        double y = -entityPos.getY();
        double z = -entityPos.getZ();
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        float alpha = 0.3F;
        float x1 = (float)(pos.minX + x);
        float x2 = (float)(pos.maxX + x);
        float y1 = (float)(pos.minY + y);
        float y2 = (float)(pos.maxY + y);
        float z1 = (float)(pos.minZ + z);
        float z2 = (float)(pos.maxZ + z);
        Matrix4f matrix = stack.last().pose();
        VertexConsumer buffer = renderTypeBuffer.getBuffer(renderType);
        buffer.addVertex(matrix, x1, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y1, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y1, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y1, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x1, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y2, z2).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, x2, y1, z2).setColor(red, green, blue, alpha);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderCenterVerticalLine(PoseStack poseStack, VertexConsumer consumer, BlockPos verticalPos, int high, BlockPos entityPos, Color color){
        LevelRenderer.renderLineBox(poseStack,consumer,new AABB(verticalPos.getX(),verticalPos.getY(),verticalPos.getZ(),verticalPos.getX(),verticalPos.getY() + high,verticalPos.getZ()).move(-entityPos.getX() + 0.5F,-entityPos.getY() + 0.5F,-entityPos.getZ() + 0.5F),(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderVerticalLine(PoseStack poseStack, VertexConsumer consumer, BlockPos verticalPos, int high, BlockPos entityPos, Color color, double prefixX, double prefixY, double prefixZ){
        LevelRenderer.renderLineBox(poseStack,consumer,new AABB(verticalPos.getX(),verticalPos.getY(),verticalPos.getZ(),verticalPos.getX(),verticalPos.getY() + high,verticalPos.getZ()).move(-entityPos.getX() + prefixX,-entityPos.getY() + prefixY,-entityPos.getZ() + prefixZ),(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 0.5F);
    }
}
