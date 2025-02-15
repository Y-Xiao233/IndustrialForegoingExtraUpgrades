package net.yxiao233.ifeu.common.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
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
        if(!entity.isFluidRender){
            return;
        }
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
    }
}
