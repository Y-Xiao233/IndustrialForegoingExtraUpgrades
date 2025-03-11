package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class BlackHoleCapacitorConfig {
    @ConfigVal(comment = "Pity Max Stored Power [FE] - Default: [100000 FE]")
    public static int pity = 100000;
    @ConfigVal(comment = "Simple Max Stored Power [FE] - Default: [500000 FE]")
    public static int simple = 500000;
    @ConfigVal(comment = "Advanced Max Stored Power [FE] - Default: [5000000 FE]")
    public static int advanced = 5000000;
    @ConfigVal(comment = "Supreme Max Stored Power [FE] - Default: [50000000 FE]")
    public static int supreme = 50000000;
}
