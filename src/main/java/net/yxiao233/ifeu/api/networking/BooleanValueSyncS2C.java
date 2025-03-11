package net.yxiao233.ifeu.api.networking;

import java.util.List;

public interface BooleanValueSyncS2C {
    void setValue(List<Boolean> values);
    List<Boolean> getValues();
}
