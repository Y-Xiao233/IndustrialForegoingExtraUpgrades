package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.fluid.ClientFluidTypeExtensions;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.fluids.FluidType;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.fluid.dragon_breath.LiquidDragonBreathParticleAnimateFluidInstance;
import net.yxiao233.ifeu.common.fluid.dragon_star_essence.DragonStarEssenceFluidInstance;
import net.yxiao233.ifeu.common.fluid.liquid_malic_acid.LiquidMalicAcidFluidInstance;
import net.yxiao233.ifeu.common.fluid.sculk_matter.LiquidSculkMatterParticleAnimateFluidInstance;

import java.util.ArrayList;

public class ModFluids implements IModule {
    public static LiquidDragonBreathParticleAnimateFluidInstance LIQUID_DRAGON_BREATH;
    public static LiquidSculkMatterParticleAnimateFluidInstance LIQUID_SCULK_MATTER;
    public static DragonStarEssenceFluidInstance DRAGON_STAR_ESSENCE;
    public static LiquidMalicAcidFluidInstance LIQUID_MALIC_ACID;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        LIQUID_DRAGON_BREATH = new LiquidDragonBreathParticleAnimateFluidInstance(helper, "liquid_dragon_breath", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_still"),ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_flow")), null);
        LIQUID_SCULK_MATTER = new LiquidSculkMatterParticleAnimateFluidInstance(helper, "liquid_sculk_matter", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_still"),ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_flow")), null);
        DRAGON_STAR_ESSENCE = new DragonStarEssenceFluidInstance(helper, "dragon_star_essence", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/dragon_star_essence_still"), ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/dragon_star_essence_flow")), null);
        LIQUID_MALIC_ACID = new LiquidMalicAcidFluidInstance(helper, "liquid_malic_acid", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_malic_acid_still"), ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_malic_acid_flow")), null);
    }

    public static ArrayList<Item> addBucketItemsToCreativeModeTab(){
        ArrayList<Item> items = new ArrayList<>();

        items.add(LIQUID_DRAGON_BREATH.getBucketFluid().get());
        items.add(LIQUID_SCULK_MATTER.getBucketFluid().get());
        items.add(LIQUID_MALIC_ACID.getBucketFluid().get());
        items.add(DRAGON_STAR_ESSENCE.getBucketFluid().get());

        return items;
    }
}
