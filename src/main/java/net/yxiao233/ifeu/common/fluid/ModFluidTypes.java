package net.yxiao233.ifeu.common.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import org.joml.Vector3f;

public class ModFluidTypes {
    private static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    private static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flowing");
    private static final ResourceLocation LIQUID_DRAGON_BREATH_OVERLAY_RL = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"misc/liquid_dragon_breath");
    private static final ResourceLocation LIQUID_SCULK_MATTER_OVERLAY_RL = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"misc/liquid_sculk_matter");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES,IndustrialForegoingExtraUpgrades.MODID);

    public static final RegistryObject<FluidType> LIQUID_DRAGON_BREATH_TYPE = FLUID_TYPES.register("liquid_dragon_breath",
            () -> new BaseFluidType(WATER_STILL_RL,WATER_FLOWING_RL,LIQUID_DRAGON_BREATH_OVERLAY_RL,
                    0xA1E038D0,new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(12).viscosity(5).sound(SoundAction.get("drink"),SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> LIQUID_SCULK_MATTER_TYPE = FLUID_TYPES.register("liquid_sculk_matter",
            () -> new BaseFluidType(WATER_STILL_RL,WATER_FLOWING_RL,LIQUID_SCULK_MATTER_OVERLAY_RL,
                    0xFF1A5E6C,new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(12).viscosity(5).sound(SoundAction.get("drink"),SoundEvents.HONEY_DRINK)));
}
