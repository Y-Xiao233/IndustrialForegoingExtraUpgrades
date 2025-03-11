package net.yxiao233.ifeu.common.networking.packet;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record BooleanSyncS2CPacket(BlockPos entityPos, List<Boolean> values) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BooleanSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "boolean"));

    public static final StreamCodec<ByteBuf, BooleanSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(BlockPos.CODEC),
            BooleanSyncS2CPacket::entityPos,
            ByteBufCodecs.fromCodec(Codec.BOOL.listOf()),
            BooleanSyncS2CPacket::values,
            BooleanSyncS2CPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
