package net.yxiao233.ifeu.api.networking;

import net.minecraft.core.BlockPos;

public interface BlockPosSyncS2C {
    void sendBlockPos(BlockPos pos);
    BlockPos getSendBlockPos();
}
