package net.yxiao233.ifeu.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IndustrialForegoingExtraUpgrades.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.INFUSER.getBlock())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getBlock())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock())
                .add(ModBlocks.CREATIVE_CAPACITOR.getBlock())
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get())
                .add(ModContents.DRAGON_STAR_BLOCK.get())
                .add(ModBlocks.WEATHER_CONTROLLER.getBlock())
                .add(ModBlocks.TIME_CONTROLLER.getBlock())
                .add(ModBlocks.RULE_CONTROLLER.getBlock())
                .add(ModBlocks.FLUID_CRAFTING_TABLE.getBlock())
                .add(ModBlocks.DRAGON_GENERATOR.getBlock())
                .add(ModBlocks.FLUID_TRANSFER.getBlock());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.INFUSER.getBlock())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getBlock())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock())
                .add(ModBlocks.CREATIVE_CAPACITOR.getBlock())
                .add(ModBlocks.WEATHER_CONTROLLER.getBlock())
                .add(ModBlocks.TIME_CONTROLLER.getBlock())
                .add(ModBlocks.RULE_CONTROLLER.getBlock())
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get())
                .add(ModContents.DRAGON_STAR_BLOCK.get())
                .add(ModBlocks.FLUID_CRAFTING_TABLE.getBlock())
                .add(ModBlocks.DRAGON_GENERATOR.getBlock())
                .add(ModBlocks.FLUID_TRANSFER.getBlock());


        this.tag(ModTags.Blocks.MACHINE_FRAME_ULTIMATE)
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get());
    }
}
