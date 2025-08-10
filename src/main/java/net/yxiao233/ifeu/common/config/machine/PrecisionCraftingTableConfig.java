package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class PrecisionCraftingTableConfig {
    @ConfigVal(comment = "Amount of Power Consumed per Tick - Default: [90FE]")
    public static int powerPerTick = 90;
    @ConfigVal(comment = "Max Stored Power [FE] - Default: [10000 FE]")
    public static int maxStoredPower = 10000;
}