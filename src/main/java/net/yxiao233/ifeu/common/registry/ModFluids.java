package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.fluid.ClientFluidTypeExtensions;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.fluids.FluidType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.fluid.dragon_breath.LiquidDragonBreathParticleAnimateFluidInstance;
import net.yxiao233.ifeu.common.fluid.sculk_matter.LiquidSculkMatterParticleAnimateFluidInstance;

import java.util.ArrayList;

public class ModFluids implements IModule {
    public static LiquidDragonBreathParticleAnimateFluidInstance LIQUID_DRAGON_BREATH;
    public static LiquidSculkMatterParticleAnimateFluidInstance LIQUID_SCULK_MATTER;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        LIQUID_DRAGON_BREATH = new LiquidDragonBreathParticleAnimateFluidInstance(helper, "liquid_dragon_breath", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_still"),ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_flow")), null);
        LIQUID_SCULK_MATTER = new LiquidSculkMatterParticleAnimateFluidInstance(helper, "liquid_sculk_matter", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_still"),ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_flow")), null);
    }

    public static ArrayList<Item> addBucketItemsToCreativeModeTab(){
        ArrayList<Item> items = new ArrayList<>();

        items.add(LIQUID_DRAGON_BREATH.getBucketFluid().get());
        items.add(LIQUID_SCULK_MATTER.getBucketFluid().get());

        return items;
    }
}
