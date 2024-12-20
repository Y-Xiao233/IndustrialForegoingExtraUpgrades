package net.yxiao233.ifeu.client.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModItems;
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
        basicItem(ModContents.NETHERITE_GEAR.get());
        basicItem(ModContents.SCULK_GEAR.get());
        basicItem(ModFluids.LIQUID_SCULK_MATTER.getBucketFluid().get());
        basicItem(ModFluids.LIQUID_DRAGON_BREATH.getBucketFluid().get());
        basicItem(ModContents.DRAGON_STAR.get());
        basicItem(ModItems.EFFICIENCY_ADDON_3.get());
        basicItem(ModItems.EFFICIENCY_ADDON_4.get());
        basicItem(ModItems.EFFICIENCY_ADDON_5.get());
        basicItem(ModItems.EFFICIENCY_ADDON_6.get());
        basicItem(ModItems.PROCESSING_ADDON_3.get());
        basicItem(ModItems.PROCESSING_ADDON_4.get());
        basicItem(ModItems.PROCESSING_ADDON_5.get());
        basicItem(ModItems.PROCESSING_ADDON_6.get());
        basicItem(ModItems.SPEED_ADDON_3.get());
        basicItem(ModItems.SPEED_ADDON_4.get());
        basicItem(ModItems.SPEED_ADDON_5.get());
        basicItem(ModItems.SPEED_ADDON_6.get());
        basicItem(ModContents.LASER_LENS_SCULK.get());
    }
}
