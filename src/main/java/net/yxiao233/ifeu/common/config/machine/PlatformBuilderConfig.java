package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class PlatformBuilderConfig {
    @ConfigVal(comment = "Cooldown Time in Ticks [20 Ticks per Second] - Default: [2 (0.1s)]")
    public static int maxProgress = 2;
    @ConfigVal(comment = "Amount of Power Consumed per Operation - Default: [10FE] - This is Calculated as [VALUE * (amount + 1)]")
    public static int powerPerOperation = 500;
    @ConfigVal(comment = "Max Stored Power [FE] - Default: [20000 FE]")
    public static int maxStoredPower = 20000;
    @ConfigVal(comment = "Max Land Range [int] - Default: [18 blocks]")
    public static int maxLandRange = 18;
    @ConfigVal(comment = "Max Frame Bounds [int] - Default: [9 blocks]")
    public static int maxFrameBounds = 9;
}
