package net.yxiao233.ifeu.common.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.config.machine.FluidCraftingTableConfig;
import net.yxiao233.ifeu.common.utils.RendererHelper;
import org.apache.commons.lang3.tuple.Triple;
import org.joml.Matrix4f;

public class FluidCraftingTableRenderer implements BlockEntityRenderer<FluidCraftingTableEntity> {
    public FluidCraftingTableRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(FluidCraftingTableEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        FluidStack fluid = entity.inputFluid.getFluid();
        RendererHelper.renderFluidContext(fluid, poseStack, () -> {
            float height = ((fluid.getAmount() * 1.0F) / FluidCraftingTableConfig.maxInputTankSize);
            height = Math.min(height, 0.995F);
            int brightness = Math.max(combinedLight, fluid.getFluid().getFluidType().getLightLevel(fluid));
            int l2 = brightness >> 0x10 & 0xFFFF;
            int i3 = brightness & 0xFFFF;

            TextureAtlasSprite icon = RendererHelper.getFluidIcon(fluid, Direction.UP);
            IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid.getFluid());
            Triple<Float, Float, Float> color = RendererHelper.intToRGB(renderProperties.getTintColor(fluid.getFluid().defaultFluidState(), entity.getLevel(), entity.getBlockPos()));

            VertexConsumer vb = multiBufferSource.getBuffer(RenderType.text(icon.atlasLocation()));
            Matrix4f matrix = poseStack.last().pose();

            renderer(vb,matrix,height,color,icon,l2,i3);
        });
    }

    public void renderer(VertexConsumer vb,Matrix4f matrix, float height, Triple<Float,Float,Float> color, TextureAtlasSprite icon,int l2, int i3){

        // Top face
        vb.vertex(matrix, 0.005F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();

        // Bottom face
        vb.vertex(matrix, 0.005F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();

        // South face
        vb.vertex(matrix, 0.005F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();

        // North face
        vb.vertex(matrix, 0.005F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();

        // East face
        vb.vertex(matrix, 0.995F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.995F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();

        // West face
        vb.vertex(matrix, 0.005F, 0.005F, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV0()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, height, 0.995F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU0(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, height, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV1()).uv2(l2, i3).endVertex();
        vb.vertex(matrix, 0.005F, 0.005F, 0.005F).color(color.getLeft(), color.getMiddle(), color.getRight(), 1).uv(icon.getU1(), icon.getV0()).uv2(l2, i3).endVertex();
    }
}
