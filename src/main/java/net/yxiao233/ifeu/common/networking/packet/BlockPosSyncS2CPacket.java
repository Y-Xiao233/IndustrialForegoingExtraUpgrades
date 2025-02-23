package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.api.networking.BlockPosSyncS2C;

import java.util.function.Supplier;

public class BlockPosSyncS2CPacket {
    private final BlockPos entityBlockPos;
    private final BlockPos sendBlockPos;
    public BlockPosSyncS2CPacket(BlockPos entityBlockPos, BlockPos sendBlockPos) {
        this.entityBlockPos = entityBlockPos;
        this.sendBlockPos = sendBlockPos;
    }

    public BlockPosSyncS2CPacket(FriendlyByteBuf buffer) {
        this.entityBlockPos = buffer.readBlockPos();
        this.sendBlockPos = buffer.readBlockPos();
    }


    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(entityBlockPos);
        buffer.writeBlockPos(sendBlockPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(entityBlockPos);
            if(blockEntity instanceof BlockPosSyncS2C entity){
                entity.sendBlockPos(this.sendBlockPos);
                blockEntity.setChanged();
            }
        });
        return true;
    }
}
