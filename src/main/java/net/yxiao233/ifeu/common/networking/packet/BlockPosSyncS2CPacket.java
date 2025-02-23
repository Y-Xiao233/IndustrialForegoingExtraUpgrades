package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BlockPosSyncS2CPacket(BlockPos entityPos, BlockPos sendPos) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BlockPosSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "block_pos"));
    public static final StreamCodec<ByteBuf, BlockPosSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(BlockPos.CODEC,() -> new NbtAccounter(1024,1024)),
            BlockPosSyncS2CPacket::entityPos,
            ByteBufCodecs.fromCodec(BlockPos.CODEC,() -> new NbtAccounter(1024,1024)),
            BlockPosSyncS2CPacket::sendPos,
            BlockPosSyncS2CPacket::new
    );
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
