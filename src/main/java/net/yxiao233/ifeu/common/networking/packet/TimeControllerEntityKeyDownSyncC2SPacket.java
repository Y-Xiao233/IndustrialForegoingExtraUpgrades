package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TimeControllerEntityKeyDownSyncC2SPacket(boolean isShiftDown, boolean isCtrlDown) implements CustomPacketPayload {
    public static final Type<TimeControllerEntityKeyDownSyncC2SPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "time_controller_key_down"));
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<ByteBuf, TimeControllerEntityKeyDownSyncC2SPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            TimeControllerEntityKeyDownSyncC2SPacket::isShiftDown,
            ByteBufCodecs.BOOL,
            TimeControllerEntityKeyDownSyncC2SPacket::isCtrlDown,
            TimeControllerEntityKeyDownSyncC2SPacket::new
    );
}
