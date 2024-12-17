package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.fluid.ClientFluidTypeExtensions;
import com.hrznstudio.titanium.fluid.TitaniumFluidInstance;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

import java.util.ArrayList;

public class ModFluids implements IModule {
    public static TitaniumFluidInstance LIQUID_DRAGON_BREATH;
    public static TitaniumFluidInstance LIQUID_SCULK_MATTER;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        LIQUID_DRAGON_BREATH = new TitaniumFluidInstance(helper, "liquid_dragon_breath", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_still"), new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_dragon_breath_flow")), null);
        LIQUID_SCULK_MATTER = new TitaniumFluidInstance(helper, "liquid_sculk_matter", FluidType.Properties.create().density(1000), new ClientFluidTypeExtensions(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_still"), new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "block/fluids/liquid_sculk_matter_flow")), null);
    }

    public static ArrayList<RegistryObject<Item>> addBucketItemsToCreativeModeTab(){
        ArrayList<RegistryObject<Item>> items = new ArrayList<>();

        items.add(LIQUID_DRAGON_BREATH.getBucketFluid());
        items.add(LIQUID_SCULK_MATTER.getBucketFluid());

        return items;
    }
}
