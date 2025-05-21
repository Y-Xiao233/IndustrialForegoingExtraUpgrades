package net.yxiao233.ifeu.common.networking.packet;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;

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

    public static void handle(BooleanSyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() ->{
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(data.entityPos());
            if(blockEntity instanceof BooleanValueSyncS2C entity){
                entity.setValue(data.values());
                blockEntity.setChanged();
            }
        });
    }
}
