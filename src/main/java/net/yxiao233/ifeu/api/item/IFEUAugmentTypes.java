package net.yxiao233.ifeu.api.item;

public enum IFEUAugmentTypes {
    THREAD,
    APPLE,
    SILK,
    HEAL,
    CHANCE;
    public boolean is(IFEUAugmentTypes other){
        return this == other;
    }
}
