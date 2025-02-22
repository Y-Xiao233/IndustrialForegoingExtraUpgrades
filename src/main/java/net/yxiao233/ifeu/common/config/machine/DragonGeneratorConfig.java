package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class DragonGeneratorConfig {
    @ConfigVal(comment = "Burn Time in Ticks [20 Ticks per Second] - Default: [2400 (120s)]")
    public static int maxProgress = 2400;

    @ConfigVal(comment = "Amount of Power Produced per Tick - Default: [4000FE]")
    public static int powerPerTick = 4000;

    @ConfigVal(comment = "Max Stored Power [FE] - Default: [800000 FE]")
    public static int maxStoredPower = 8000000;

    @ConfigVal(comment = "Amount of FE/t extracted from the Dragon Generator")
    public static int extractionRate = 160000;

    @ConfigVal(comment = "Max Amount of Stored Fluid [Input] - Default: [8000mB]")
    public static int maxInputTankSize = 8000;
}
