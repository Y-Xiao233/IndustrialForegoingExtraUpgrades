package net.yxiao233.ifeu.common.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.yxiao233.ifeu.common.block.entity.PlatformBuilderEntity;

import java.util.function.Supplier;

public class PlatformBuilderEntityKeyDownSyncC2SPacket {
    private final boolean[] values;
    public PlatformBuilderEntityKeyDownSyncC2SPacket(boolean... values) {
        this.values = values;
    }

    public PlatformBuilderEntityKeyDownSyncC2SPacket(FriendlyByteBuf buffer) {
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
            PlatformBuilderEntity.isShiftDown = values[0];
        });
        return true;
    }
}
