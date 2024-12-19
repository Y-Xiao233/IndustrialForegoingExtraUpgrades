package net.yxiao233.ifeu.common.config.misc;

import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import net.yxiao233.ifeu.common.config.MiscConfig;

@ConfigFile.Child(MiscConfig.class)
public class LiquidDragonBreathConfig {
    @ConfigVal(comment = "The probability of this fluid emitting ENDER_DRAGON_AMBIENT sound per tick - Default: [0.1%]")
    public static double ProbabilityOfProducingSound = 0.001;
    @ConfigVal(comment = "The probability of this fluid producing particle effects per tick - Default: [20%]")
    public static double ProbabilityOfParticleGeneration = 0.2;
    @ConfigVal(comment = "Is it allowed for the fluid to emit ENDER-DRAGON_AMBIENT sound - Default: [true]")
    public static boolean isFluidProduceSound = true;
    @ConfigVal(comment = "Is it allowed for the fluid to produce DRAGON_BREATH particles - Default: [true]")
    public static boolean isFluidGenerateParticles = true;
}
