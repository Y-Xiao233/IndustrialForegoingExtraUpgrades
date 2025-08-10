package net.yxiao233.ifeu.client.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.registry.IFEUFluids;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IndustrialForegoingExtraUpgrades.MODID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return IndustrialForegoingExtraUpgrades.MODID + " - ItemModel";
    }

    @Override
    protected void registerModels() {
        basicItem(IFEUContents.NETHERITE_GEAR.get());
        basicItem(IFEUContents.SCULK_GEAR.get());
        basicItem(IFEUFluids.LIQUID_SCULK_MATTER.getBucketFluid().get());
        basicItem(IFEUFluids.LIQUID_DRAGON_BREATH.getBucketFluid().get());
        basicItem(IFEUFluids.LIQUID_MALIC_ACID.getBucketFluid().get());
        basicItem(IFEUFluids.DRAGON_STAR_ESSENCE.getBucketFluid().get());
        basicItem(IFEUContents.DRAGON_STAR.get());
        basicItem(IFEUItems.EFFICIENCY_ADDON_3.get());
        basicItem(IFEUItems.EFFICIENCY_ADDON_4.get());
        basicItem(IFEUItems.EFFICIENCY_ADDON_5.get());
        basicItem(IFEUItems.EFFICIENCY_ADDON_6.get());
        basicItem(IFEUItems.PROCESSING_ADDON_3.get());
        basicItem(IFEUItems.PROCESSING_ADDON_4.get());
        basicItem(IFEUItems.PROCESSING_ADDON_5.get());
        basicItem(IFEUItems.PROCESSING_ADDON_6.get());
        basicItem(IFEUItems.SPEED_ADDON_3.get());
        basicItem(IFEUItems.SPEED_ADDON_4.get());
        basicItem(IFEUItems.SPEED_ADDON_5.get());
        basicItem(IFEUItems.SPEED_ADDON_6.get());
        basicItem(IFEUContents.LASER_LENS_SCULK.get());
        basicItem(IFEUContents.DRAGON_STAR_HOE.get());
        basicItem(IFEUContents.DRAGON_STAR_SHOVEL.get());
        basicItem(IFEUContents.DRAGON_STAR_SWORD.get());
        basicItem(IFEUContents.DRAGON_STAR_PICKAXE.get());
        basicItem(IFEUContents.DRAGON_STAR_AXE.get());
        basicItem(IFEUContents.LASER_LENS_DRAGON.get());
        basicItem(IFEUContents.CONNECT_TOOL.get());
        basicItem(IFEUContents.WRENCH.get());
        basicItem(IFEUContents.CONFIGURATION_TOOL.get());
        basicItem(IFEUItems.THREAD_ADDON_1.get());
        basicItem(IFEUItems.THREAD_ADDON_2.get());
        basicItem(IFEUItems.THREAD_ADDON_3.get());
        basicItem(IFEUItems.THREAD_ADDON_4.get());
        basicItem(IFEUItems.THREAD_ADDON_5.get());
        basicItem(IFEUItems.THREAD_ADDON_6.get());
        basicItem(IFEUItems.APPLE_ADDON_1.get());
        basicItem(IFEUItems.APPLE_ADDON_2.get());
        basicItem(IFEUItems.APPLE_ADDON_3.get());
        basicItem(IFEUItems.APPLE_ADDON_4.get());
        basicItem(IFEUItems.APPLE_ADDON_5.get());
        basicItem(IFEUItems.APPLE_ADDON_6.get());
        basicItem(IFEUContents.ROUGH_DRAGON_STAR.get());
        basicItem(IFEUContents.EMPTY_NETHER_STAR.get());
        basicItem(IFEUContents.APPLE_CORE.get());
        basicItem(IFEUContents.BLUEPRINT.get());
        basicItem(IFEUItems.SILK_ADDON.get());
        basicItem(IFEUItems.HEAL_ADDON_1.get());
        basicItem(IFEUItems.HEAL_ADDON_2.get());
        basicItem(IFEUItems.HEAL_ADDON_3.get());
        basicItem(IFEUItems.HEAL_ADDON_4.get());
        basicItem(IFEUItems.HEAL_ADDON_5.get());
        basicItem(IFEUItems.HEAL_ADDON_6.get());
        basicItem(IFEUItems.CHANCE_ADDON_1.get());
        basicItem(IFEUItems.CHANCE_ADDON_2.get());
        basicItem(IFEUItems.CHANCE_ADDON_3.get());
        basicItem(IFEUItems.CHANCE_ADDON_4.get());
        basicItem(IFEUItems.CHANCE_ADDON_5.get());
        basicItem(IFEUItems.CHANCE_ADDON_6.get());
        basicItem(IFEUItems.CHANCE_ADDON_CREATIVE.get());
    }
}
