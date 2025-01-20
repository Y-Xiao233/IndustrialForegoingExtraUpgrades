package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.common.block.entity.RuleControllerEntity;

import java.util.function.Supplier;

public class GameRuleSyncS2CPacket {
    private final Boolean value;
    private final BlockPos blockPos;
    public GameRuleSyncS2CPacket(boolean value, BlockPos blockPos) {
        this.value = value;
        this.blockPos = blockPos;
    }

    public GameRuleSyncS2CPacket(FriendlyByteBuf buffer) {
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
            if(Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof RuleControllerEntity entity){
                entity.value = this.value;
                entity.setChanged();
            }
        });
        return true;
    }
}
