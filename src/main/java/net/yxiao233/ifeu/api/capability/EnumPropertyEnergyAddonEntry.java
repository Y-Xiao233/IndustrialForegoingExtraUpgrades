package net.yxiao233.ifeu.api.capability;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import net.yxiao233.ifeu.api.block.entity.EnumPropertyIndustrialMachineTile;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import net.yxiao233.ifeu.mixin.capability.EnergyStorageAccessor;

public class EnumPropertyEnergyAddonEntry {
    private final EnumPropertyIndustrialMachineTile<?,?> tile;
    private final int baseCapacity;
    private EnumPropertyEnergyAddonEntry(EnumPropertyIndustrialMachineTile<?,?> tile, int baseCapacity){
        this.tile = tile;
        this.baseCapacity = baseCapacity;
    }

    public static EnumPropertyEnergyAddonEntry create(EnumPropertyIndustrialMachineTile<?,?> tile, int baseCapacity){
        return new EnumPropertyEnergyAddonEntry(tile,baseCapacity);
    }
    public void updateEnergyCapacity(){
        if(tile.getAugmentInventory() == null){
            return;
        }
        int tier = AugmentInventoryHelper.getAugmentTier(tile.getAugmentInventory(),IFEUAugmentTypes.ENERGY);
        EnergyStorageComponent<?> energyStorage = tile.getEnergyStorage();
        boolean isCreative = AugmentInventoryHelper.contains(tile.getAugmentInventory(),IFEUAugmentTypes.CREATIVE);
        int cap = Math.min((int) (baseCapacity * Math.pow(10,(tier + 2) / 2D)),Integer.MAX_VALUE);
        cap = tier > 0 ? cap : this.baseCapacity;
        cap = isCreative ? Integer.MAX_VALUE : cap;
        int old = energyStorage.getEnergyStored();
        if(energyStorage instanceof EnergyStorageAccessor accessor){
            accessor.setCapacity(cap);
            if(old > cap){
                energyStorage.setEnergyStored(cap);
            }
            tile.markForUpdate();
        }
    }
}
