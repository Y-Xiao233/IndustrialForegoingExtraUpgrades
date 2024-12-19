package net.yxiao233.ifeu.client.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
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
        simpleItem(ModContents.NETHERITE_GEAR);
        simpleItem(ModContents.SCULK_GEAR);
        simpleItem(ModFluids.LIQUID_SCULK_MATTER.getBucketFluid());
        simpleItem(ModFluids.LIQUID_DRAGON_BREATH.getBucketFluid());
        simpleItem(ModContents.DRAGON_STAR);
        simpleItem(ModItems.EFFICIENCY_ADDON_3);
        simpleItem(ModItems.EFFICIENCY_ADDON_4);
        simpleItem(ModItems.EFFICIENCY_ADDON_5);
        simpleItem(ModItems.EFFICIENCY_ADDON_6);
        simpleItem(ModItems.PROCESSING_ADDON_3);
        simpleItem(ModItems.PROCESSING_ADDON_4);
        simpleItem(ModItems.PROCESSING_ADDON_5);
        simpleItem(ModItems.PROCESSING_ADDON_6);
        simpleItem(ModItems.SPEED_ADDON_3);
        simpleItem(ModItems.SPEED_ADDON_4);
        simpleItem(ModItems.SPEED_ADDON_5);
        simpleItem(ModItems.SPEED_ADDON_6);
        simpleItem(ModContents.LASER_LENS_SCULK);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("items/generated")).texture("layer0",
                new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"items/" + item.getId().getPath()));
    }
}
