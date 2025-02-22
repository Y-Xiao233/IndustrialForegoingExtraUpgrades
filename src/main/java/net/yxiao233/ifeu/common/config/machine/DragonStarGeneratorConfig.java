package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class DragonStarGeneratorConfig {
    @ConfigVal(comment = "Burn Time in Ticks [20 Ticks per Second] - Default: [1200 (60s)]")
    public static int maxProgress = 1200;

    @ConfigVal(comment = "Amount of Power Produced per Tick - Default: [40000FE]")
    public static int powerPerTick = 40000;

    @ConfigVal(comment = "Max Stored Power [FE] - Default: [800000000 FE]")
    public static int maxStoredPower = 800000000;

    @ConfigVal(comment = "Amount of FE/t extracted from the Dragon Star Generator")
    public static int extractionRate = 1600000;
}
