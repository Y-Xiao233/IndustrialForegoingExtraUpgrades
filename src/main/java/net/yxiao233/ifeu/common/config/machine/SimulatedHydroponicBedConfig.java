package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class SimulatedHydroponicBedConfig {

    @ConfigVal(comment = "Cooldown Time in Ticks [20 Ticks per Second] - Default: [600 (30s)]")
    public static int maxProgress = 600;

    @ConfigVal(comment = "Amount of Power Consumed per Operation - Default: [3000FE]")
    public static int powerPerOperation = 3000;

    @ConfigVal(comment = "Max Stored Power [FE] - Default: [20000 FE]")
    public static int maxStoredPower = 20000;

    @ConfigVal(comment = "Chance to increase the executions of the current processor, 0.1 = 10% chance - Default: [0.1]")
    public static double chanceToIncreaseExecutions = 0.1;
}
