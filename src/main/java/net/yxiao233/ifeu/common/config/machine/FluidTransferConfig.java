package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

@ConfigFile.Child(MachineConfig.class)
public class FluidTransferConfig {
    @ConfigVal(comment = "Max Amount of Stored Fluid - Default: [8000mB]")
    public static int maxTankSize = 8000;
    @ConfigVal(comment = "Max Amount of transfer Fluid in Progresses - Default: [1000mB]")
    public static int maxTransfer = 1000;
}
