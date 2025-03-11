package net.yxiao233.ifeu.common.networking.packet;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DirectionSyncS2CPacket(BlockPos blockPos, Direction direction) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DirectionSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "direction"));
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<ByteBuf, DirectionSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(BlockPos.CODEC),
            DirectionSyncS2CPacket::blockPos,
            ByteBufCodecs.fromCodec(Direction.CODEC),
            DirectionSyncS2CPacket::direction,
            DirectionSyncS2CPacket::new
    );
}
