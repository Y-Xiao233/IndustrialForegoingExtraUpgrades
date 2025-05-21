package net.yxiao233.ifeu.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.common.block.entity.TimeControllerEntity;

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

    public static void handle(TimeControllerEntityKeyDownSyncC2SPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            TimeControllerEntity.isShiftDown = data.isShiftDown();
            TimeControllerEntity.isCtrlDown = data.isCtrlDown();
        });
    }
}
