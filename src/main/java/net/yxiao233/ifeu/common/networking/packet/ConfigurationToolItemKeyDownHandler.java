package net.yxiao233.ifeu.common.networking.packet;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.common.item.ConfigurationToolItem;

public class ConfigurationToolItemKeyDownHandler {
    public static void handle(ConfigurationToolItemKeyDownSyncC2SPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            ConfigurationToolItem.isCtrlDown = data.isCtrlDown();
        });
    }
}
