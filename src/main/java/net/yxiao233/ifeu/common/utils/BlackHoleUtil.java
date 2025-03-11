package net.yxiao233.ifeu.common.utils;

import net.minecraft.world.item.Rarity;
import net.yxiao233.ifeu.common.config.machine.BlackHoleCapacitorConfig;

public class BlackHoleUtil {
    public static int getMaxEnergyCapacityByRarity(Rarity rarity) {
        if (rarity.equals(Rarity.COMMON)) {
            return BlackHoleCapacitorConfig.pity;
        } else if (rarity.equals(Rarity.UNCOMMON)) {
            return BlackHoleCapacitorConfig.simple;
        } else if (rarity.equals(Rarity.RARE)) {
            return BlackHoleCapacitorConfig.advanced;
        } else {
            return rarity.equals(Rarity.EPIC) ? BlackHoleCapacitorConfig.supreme : Integer.MAX_VALUE;
        }
    }
}
