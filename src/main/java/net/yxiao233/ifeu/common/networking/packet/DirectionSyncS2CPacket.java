package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.api.networking.DirectionSyncS2C;

import java.util.function.Supplier;

public class DirectionSyncS2CPacket {
    private final BlockPos entityBlockPos;
    private final Direction direction;
    public DirectionSyncS2CPacket(BlockPos entityBlockPos, Direction direction) {
        this.entityBlockPos = entityBlockPos;
        this.direction = direction;
    }

    public DirectionSyncS2CPacket(FriendlyByteBuf buffer) {
        this.entityBlockPos = buffer.readBlockPos();
        this.direction = buffer.readEnum(Direction.class);
    }


    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(entityBlockPos);
        buffer.writeEnum(direction);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(entityBlockPos);
            if(blockEntity instanceof DirectionSyncS2C entity){
                entity.setDirectionValue(direction);
                blockEntity.setChanged();
            }
        });
        return true;
    }
}
