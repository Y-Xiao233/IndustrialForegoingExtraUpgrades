package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.common.block.entity.PlatformBuilderEntity;
import net.yxiao233.ifeu.common.block.entity.TimeControllerEntity;

public record PlatformBuilderEntityKeyDownSyncC2SPacket(boolean isShiftDown) implements CustomPacketPayload {
    public static final Type<PlatformBuilderEntityKeyDownSyncC2SPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("ifeu", "platform_builder_key_down"));
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<ByteBuf, PlatformBuilderEntityKeyDownSyncC2SPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            PlatformBuilderEntityKeyDownSyncC2SPacket::isShiftDown,
            PlatformBuilderEntityKeyDownSyncC2SPacket::new
    );

    public static void handle(PlatformBuilderEntityKeyDownSyncC2SPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            PlatformBuilderEntity.isShiftDown = data.isShiftDown();
        });
    }
}
