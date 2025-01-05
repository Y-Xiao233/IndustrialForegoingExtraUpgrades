package net.yxiao233.ifeu.common.components;

import com.buuz135.industrial.item.infinity.InfinityEnergyStorage;
import com.hrznstudio.titanium.component.IComponentHarness;

public class CreativeEnergyStorageComponent<T extends IComponentHarness> extends InfinityEnergyStorage<T> {
    public CreativeEnergyStorageComponent(long capacity, int xPos, int yPos) {
        super(capacity, xPos, yPos);
    }

    @Override
    public boolean canExtract() {
        return true;
    }
}
