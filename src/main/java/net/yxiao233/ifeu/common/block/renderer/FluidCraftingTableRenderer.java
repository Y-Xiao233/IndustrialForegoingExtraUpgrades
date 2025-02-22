package net.yxiao233.ifeu.common.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.utils.RendererHelper;

public class FluidCraftingTableRenderer implements BlockEntityRenderer<FluidCraftingTableEntity> {
    public FluidCraftingTableRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(FluidCraftingTableEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        if(!entity.isFluidRender){
            return;
        }
        FluidStack fluid = entity.inputFluid.getFluid();
        RendererHelper.renderFullFluid(poseStack,multiBufferSource,entity,fluid,combinedLight);
    }
}
