package net.yxiao233.ifeu.common.config.machine;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MachineConfig;

import java.util.ArrayList;
import java.util.List;

@ConfigFile.Child(MachineConfig.class)
public class HydroponicBedConfig {
    @ConfigVal(comment = "BlackList for HydroponicBed")
    public static List<String> blackList = new ArrayList<>();
}
