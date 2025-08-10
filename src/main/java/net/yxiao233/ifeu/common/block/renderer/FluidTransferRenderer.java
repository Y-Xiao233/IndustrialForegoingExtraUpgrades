package net.yxiao233.ifeu.common.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.registry.IFEUDataComponentTypes;
import net.yxiao233.ifeu.common.utils.RendererProvider;
import net.yxiao233.ifeu.common.utils.TagUtil;

import java.awt.*;

public class FluidTransferRenderer implements BlockEntityRenderer<FluidTransferEntity> {
    public FluidTransferRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(FluidTransferEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        if(!entity.isFluidRender){
            return;
        }
        RendererProvider provider = new RendererProvider(poseStack,multiBufferSource,entity.getBlockPos());
        //render fluid
        provider.renderFullFluid(entity,entity.fluidStored.getFluid(),combinedLight);

        //render connected
        Player player = Minecraft.getInstance().player;
        if(player != null){
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof ConnectToolItem){
                CompoundTag tag = new CompoundTag();
                if(stack.has(IFEUDataComponentTypes.COMPOUND_TAG)){
                    tag = stack.get(IFEUDataComponentTypes.COMPOUND_TAG);
                }
                if(TagUtil.contains(tag,"mode")){
                    int mode = tag.getInt("mode");
                    if(mode == 0){
                        CompoundTag mode0 = tag.getCompound("mode0");
                        if(TagUtil.contains(mode0,"pos1")){
                            int[] temp = mode0.getIntArray("pos1");
                            Color color = new Color(55,255,55);
                            BlockPos pos = new BlockPos(temp[0],temp[1],temp[2]);
                            provider.renderBlockLineBox(pos,color);
                            provider.renderCenterVerticalLine(pos,5,color);
                        }
                        if(TagUtil.contains(mode0,"pos2")){
                            int[] temp = mode0.getIntArray("pos2");
                            Color color = new Color(0,255,255);
                            BlockPos pos = new BlockPos(temp[0],temp[1],temp[2]);
                            provider.renderBlockLineBox(pos,color);
                            provider.renderCenterVerticalLine(pos,5,color);
                        }
                    }
                }
            }
        }
    }
}
