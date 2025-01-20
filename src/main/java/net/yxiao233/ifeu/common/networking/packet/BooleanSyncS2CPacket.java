package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.common.block.entity.TimeControllerEntity;
import net.yxiao233.ifeu.common.block.entity.WeatherControllerEntity;
import net.yxiao233.ifeu.common.networking.packet.interfaces.BooleanValueSyncS2C;

import java.util.function.Supplier;

public class BooleanSyncS2CPacket {
    private final Boolean value;
    private final BlockPos blockPos;
    public BooleanSyncS2CPacket(boolean value, BlockPos blockPos) {
        this.value = value;
        this.blockPos = blockPos;
    }

    public BooleanSyncS2CPacket(FriendlyByteBuf buffer) {
        this.value = buffer.readBoolean();
        this.blockPos = buffer.readBlockPos();
    }


    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBoolean(value);
        buffer.writeBlockPos(blockPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(blockPos);
            if(blockEntity instanceof BooleanValueSyncS2C entity){
                entity.setValue(this.value);
                blockEntity.setChanged();
            }
        });
        return true;
    }
}
