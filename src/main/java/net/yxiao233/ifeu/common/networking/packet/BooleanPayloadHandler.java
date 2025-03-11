package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;

public class BooleanPayloadHandler {
    public static void handle(BooleanSyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(data.entityPos());
            if(blockEntity instanceof BooleanValueSyncS2C entity){
                entity.setValue(data.values());
                blockEntity.setChanged();
            }
        });
    }
}
