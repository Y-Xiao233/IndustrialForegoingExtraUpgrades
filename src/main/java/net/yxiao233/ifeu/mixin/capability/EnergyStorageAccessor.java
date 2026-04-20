package net.yxiao233.ifeu.mixin.capability;

import net.neoforged.neoforge.energy.EnergyStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EnergyStorage.class)
public interface EnergyStorageAccessor {
    @Accessor("capacity")
    void setCapacity(int capacity);

    @Accessor("maxReceive")
    void setMaxReceive(int maxReceive);

    @Accessor("maxExtract")
    void setMaxExtract(int maxExtract);
}
