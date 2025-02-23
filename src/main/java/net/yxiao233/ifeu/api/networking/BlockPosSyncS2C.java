package net.yxiao233.ifeu.api.networking;

import net.minecraft.core.BlockPos;

public interface BlockPosSyncS2C {
    void setSendBlockPos(BlockPos blockPos);
    BlockPos getSendBlockPos();
}
