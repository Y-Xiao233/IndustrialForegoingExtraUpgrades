package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.api.networking.IntValueSyncS2C;

import java.util.function.Supplier;

public class IntValueSyncC2SPacket {
    private final BlockPos entityBlockPos;
    private final int[] values;
    public IntValueSyncC2SPacket(BlockPos entityBlockPos, int[] values) {
        this.entityBlockPos = entityBlockPos;
        this.values = values;
    }

    public IntValueSyncC2SPacket(BlockPos entityBlockPos, int value) {
        this.entityBlockPos = entityBlockPos;
        this.values = new int[]{value};
    }

    public IntValueSyncC2SPacket(FriendlyByteBuf buffer) {
        this.entityBlockPos = buffer.readBlockPos();
        this.values = buffer.readVarIntArray();
    }


    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(entityBlockPos);
        buffer.writeVarIntArray(values);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(entityBlockPos);
            if(blockEntity instanceof IntValueSyncS2C entity){
                entity.setIntValue(this.values);
                blockEntity.setChanged();
            }
        });
        return true;
    }
}
