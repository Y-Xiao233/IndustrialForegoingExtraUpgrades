package net.yxiao233.ifeu.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IndustrialForegoingExtraUpgrades.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.INFUSER.getLeft().get())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.INFUSER.getLeft().get())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get());
    }
}
