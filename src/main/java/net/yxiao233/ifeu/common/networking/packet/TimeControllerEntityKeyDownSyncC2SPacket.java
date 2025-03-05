package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.common.block.entity.TimeControllerEntity;
import net.yxiao233.ifeu.common.item.ConfigurationToolItem;

import java.util.function.Supplier;

public class TimeControllerEntityKeyDownSyncC2SPacket {
    private final boolean[] values;
    public TimeControllerEntityKeyDownSyncC2SPacket(boolean... values) {
        this.values = values;
    }

    public TimeControllerEntityKeyDownSyncC2SPacket(FriendlyByteBuf buffer) {
        int size = buffer.readInt();

        boolean[] temp = new boolean[size];

        for (int i = 0; i < size; i++) {
            temp[i] = buffer.readBoolean();
        }

        this.values = temp;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(values.length);

        for (int i = 0; i < values.length; i++) {
            buffer.writeBoolean(values[i]);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            TimeControllerEntity.isShiftDown = values[0];
            TimeControllerEntity.isCtrlDown = values[1];
        });
        return true;
    }
}
