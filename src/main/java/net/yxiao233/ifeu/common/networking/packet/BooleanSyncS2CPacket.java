package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BooleanSyncS2CPacket(BlockPos entityPos, boolean value) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BooleanSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "boolean"));

    public static final StreamCodec<ByteBuf, BooleanSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(BlockPos.CODEC,() -> new NbtAccounter(1024,1024)),
            BooleanSyncS2CPacket::entityPos,
            ByteBufCodecs.BOOL,
            BooleanSyncS2CPacket::value,
            BooleanSyncS2CPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
