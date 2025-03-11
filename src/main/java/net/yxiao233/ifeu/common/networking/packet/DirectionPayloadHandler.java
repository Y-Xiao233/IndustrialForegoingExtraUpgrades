package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.api.networking.DirectionSyncS2C;

public class DirectionPayloadHandler {
    public static void handle(DirectionSyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(data.blockPos());
            if(blockEntity instanceof DirectionSyncS2C entity){
                entity.setDirectionValue(data.direction());
                blockEntity.setChanged();
            }
        });
    }
}
