package net.yxiao233.ifeu.common.networking.packet;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.api.networking.DirectionSyncS2C;

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

    public static void handle(DirectionSyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(data.blockPos());
            if(blockEntity instanceof DirectionSyncS2C entity){
                entity.setDirectionValue(data.direction());
                blockEntity.setChanged();
            }
        });
    }
}
