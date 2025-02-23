package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.api.networking.BlockPosSyncS2C;

public class BlockPosPayloadHandler {
    public static void handle(BlockPosSyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(data.entityPos());
            if(blockEntity instanceof BlockPosSyncS2C entity){
                entity.setSendBlockPos(data.sendPos());
                blockEntity.setChanged();
            }
        });
    }
}
