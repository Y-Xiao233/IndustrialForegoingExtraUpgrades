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
    private final boolean[] value;
    private final BlockPos blockPos;
    private final int size;
    public BooleanSyncS2CPacket(BlockPos blockPos,boolean... value) {
        this.value = value;
        this.blockPos = blockPos;
        this.size = value.length;
    }

    public BooleanSyncS2CPacket(BlockPos blockPos,boolean value) {
        this.value = new boolean[]{value};
        this.blockPos = blockPos;
        this.size = this.value.length;
    }

    public BooleanSyncS2CPacket(FriendlyByteBuf buffer) {
        this.size = buffer.readInt();
        this.blockPos = buffer.readBlockPos();

        boolean[] temp = new boolean[size];
        for (int i = 0; i < this.size; i++) {
            temp[i] = buffer.readBoolean();
        }

        this.value = temp;
    }


    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(size);
        buffer.writeBlockPos(blockPos);

        for (boolean b : this.value) {
            buffer.writeBoolean(b);
        }
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
