package net.yxiao233.ifeu.client.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import org.jetbrains.annotations.NotNull;

public class ModItemModuleProvider extends ItemModelProvider {
    public ModItemModuleProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IndustrialForegoingExtraUpgrades.MODID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return IndustrialForegoingExtraUpgrades.MODID + " - ItemModel";
    }

    @Override
    protected void registerModels() {
        //TODO
    }
}
