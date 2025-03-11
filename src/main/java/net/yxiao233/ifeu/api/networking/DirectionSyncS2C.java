package net.yxiao233.ifeu.api.networking;

import net.minecraft.core.Direction;

public interface DirectionSyncS2C {
    void setDirectionValue(Direction value);
    Direction getDirectionValue();
}
