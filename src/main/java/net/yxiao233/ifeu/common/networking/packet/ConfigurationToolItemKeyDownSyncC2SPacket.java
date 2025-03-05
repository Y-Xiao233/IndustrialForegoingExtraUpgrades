package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ConfigurationToolItemKeyDownSyncC2SPacket(boolean isCtrlDown) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ConfigurationToolItemKeyDownSyncC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "configuration_tool_key_down"));
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<ByteBuf, ConfigurationToolItemKeyDownSyncC2SPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ConfigurationToolItemKeyDownSyncC2SPacket::isCtrlDown,
            ConfigurationToolItemKeyDownSyncC2SPacket::new
    );
}
