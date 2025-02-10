package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BooleanSyncS2CPacket(int x, int y, int z, boolean value) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BooleanSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "data"));

    public static final StreamCodec<ByteBuf, BooleanSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            BooleanSyncS2CPacket::x,
            ByteBufCodecs.VAR_INT,
            BooleanSyncS2CPacket::y,
            ByteBufCodecs.VAR_INT,
            BooleanSyncS2CPacket::z,
            ByteBufCodecs.BOOL,
            BooleanSyncS2CPacket::value,
            BooleanSyncS2CPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
