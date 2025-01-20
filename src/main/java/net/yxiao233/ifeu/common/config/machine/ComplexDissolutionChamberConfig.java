package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class ComplexDissolutionChamberConfig {
    @ConfigVal(comment = "Cooldown Time in Ticks [20 Ticks per Second] - Default: [100 (5s)]")
    public static int maxProgress = 100;
    @ConfigVal(comment = "Amount of Power Consumed per Tick - Default: [60FE]")
    public static int powerPerTick = 90;
    @ConfigVal(comment = "Max Stored Power [FE] - Default: [10000 FE]")
    public static int maxStoredPower = 10000;
    @ConfigVal(comment = "Max Amount of Stored Fluid [Input] - Default: [8000mB]")
    public static int maxInputTankSize = 8000;
    @ConfigVal(comment = "Max Amount of Stored Fluid [Output] - Default: [8000mB]")
    public static int maxOutputTankSize = 16000;
}
