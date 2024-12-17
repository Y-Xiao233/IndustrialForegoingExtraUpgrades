package net.yxiao233.ifeu.common.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.fluid.ModFluidTypes;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IndustrialForegoingExtraUpgrades.MODID);
    //Liquid Dragon Breath
    public static final RegistryObject<FlowingFluid> SOURCE_LIQUID_DRAGON_BREATH = FLUIDS.register("source_liquid_dragon_breath",
            () -> new ForgeFlowingFluid.Source(ModFluids.LIQUID_DRAGON_BREATH_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_LIQUID_DRAGON_BREATH = FLUIDS.register("flowing_liquid_dragon_breath",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.LIQUID_DRAGON_BREATH_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_DRAGON_BREATH_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LIQUID_DRAGON_BREATH_TYPE,SOURCE_LIQUID_DRAGON_BREATH,FLOWING_LIQUID_DRAGON_BREATH)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModContents.LIQUID_DRAGON_BREATH_BLOCK)
            .bucket(ModContents.LIQUID_DRAGON_BREATH_BUCKET);

    //Liquid Sculk Matter
    public static final RegistryObject<FlowingFluid> SOURCE_LIQUID_SCULK_MATTER = FLUIDS.register("source_liquid_sculk_matter",
            () -> new ForgeFlowingFluid.Source(ModFluids.LIQUID_SCULK_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_LIQUID_SCULK_MATTER = FLUIDS.register("flowing_liquid_sculk_matter",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.LIQUID_SCULK_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_SCULK_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LIQUID_SCULK_MATTER_TYPE,SOURCE_LIQUID_SCULK_MATTER,FLOWING_LIQUID_SCULK_MATTER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModContents.LIQUID_SCULK_MATTER_BLOCK)
            .bucket(ModContents.LIQUID_SCULK_MATTER_BUCKET);
}
