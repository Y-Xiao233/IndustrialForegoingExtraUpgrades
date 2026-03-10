package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class FermenterConfig {
    @ConfigVal(comment = "Cooldown Time in Ticks [20 Ticks per Second] - Default: [100 (5s)]")
    public static int maxProgress = 100;
    @ConfigVal(comment = "Amount of Power Consumed per Tick - Default: [400FE]")
    public static int powerPerOperation = 400;
    @ConfigVal(comment = "Max Stored Power [FE] - Default: [10000 FE]")
    public static int maxStoredPower = 10000;
    @ConfigVal
    public static int maxWaterTankStorage = 16000;
    @ConfigVal
    public static int maxSludgeTankStorage = 16000;
}
