package net.yxiao233.ifeu.api.networking;

public interface BooleanValueSyncS2C {
    void setValue(boolean... value);
    boolean[] getValues();
}
