package net.yxiao233.ifeu.common.networking.packet;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.common.block.entity.TimeControllerEntity;
import net.yxiao233.ifeu.common.item.ConfigurationToolItem;

public class TimeControllerEntityKeyDownHandler {
    public static void handle(TimeControllerEntityKeyDownSyncC2SPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            TimeControllerEntity.isShiftDown = data.isShiftDown();
            TimeControllerEntity.isCtrlDown = data.isCtrlDown();
        });
    }
}
