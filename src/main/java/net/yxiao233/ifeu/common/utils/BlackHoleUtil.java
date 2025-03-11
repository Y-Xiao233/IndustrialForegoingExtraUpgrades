package net.yxiao233.ifeu.common.utils;

import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.world.item.Rarity;
import net.yxiao233.ifeu.common.config.machine.BlackHoleCapacitorConfig;

public class BlackHoleUtil {
    public static int getMaxEnergyCapacityByRarity(Rarity rarity) {
        if (rarity.equals(ModuleCore.PITY_RARITY)) {
            return BlackHoleCapacitorConfig.pity;
        } else if (rarity.equals(ModuleCore.SIMPLE_RARITY)) {
            return BlackHoleCapacitorConfig.simple;
        } else if (rarity.equals(ModuleCore.ADVANCED_RARITY)) {
            return BlackHoleCapacitorConfig.advanced;
        } else {
            return rarity.equals(ModuleCore.SUPREME_RARITY) ? BlackHoleCapacitorConfig.supreme : Integer.MAX_VALUE;
        }
    }
}
