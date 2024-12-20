package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class DragonStarGeneratorConfig {
    @ConfigVal(comment = "Burn Time in Ticks [20 Ticks per Second] - Default: [2400 (120s)]")
    public static int maxProgress = 2400;

    @ConfigVal(comment = "Amount of Power Produced per Tick - Default: [8000FE]")
    public static int powerPerTick = 8000;

    @ConfigVal(comment = "Max Stored Power [FE] - Default: [800000 FE]")
    public static int maxStoredPower = 8000000;

    @ConfigVal(comment = "Amount of FE/t extracted from the Biofuel Generator")
    public static int extractionRate = 16000;
}
