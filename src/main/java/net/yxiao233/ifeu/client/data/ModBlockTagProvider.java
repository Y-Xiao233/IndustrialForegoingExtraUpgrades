package net.yxiao233.ifeu.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
                .add(ModBlocks.INFUSER.getLeft().get())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get())
                .add(ModBlocks.CREATIVE_CAPACITOR.getLeft().get())
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get())
                .add(ModContents.DRAGON_STAR_BLOCK.get())
                .add(ModBlocks.WEATHER_CONTROLLER.getLeft().get())
                .add(ModBlocks.TIME_CONTROLLER.getLeft().get())
                .add(ModBlocks.RULE_CONTROLLER.getLeft().get())
                .add(ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get())
                .add(ModBlocks.DRAGON_GENERATOR.getLeft().get())
                .add(ModBlocks.FLUID_TRANSFER.getLeft().get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.INFUSER.getLeft().get())
                .add(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get())
                .add(ModBlocks.CREATIVE_CAPACITOR.getLeft().get())
                .add(ModBlocks.WEATHER_CONTROLLER.getLeft().get())
                .add(ModBlocks.TIME_CONTROLLER.getLeft().get())
                .add(ModBlocks.RULE_CONTROLLER.getLeft().get())
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get())
                .add(ModContents.DRAGON_STAR_BLOCK.get())
                .add(ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get())
                .add(ModBlocks.DRAGON_GENERATOR.getLeft().get())
                .add(ModBlocks.FLUID_TRANSFER.getLeft().get());


        this.tag(ModTags.Blocks.MACHINE_FRAME_ULTIMATE)
                .add(ModContents.ULTIMATE_MACHINE_FRAME.get());

        this.tag(ModTags.Blocks.WRENCH_PICKUP)
                .addTag(Tags.Blocks.CHESTS)
                .addTag(BlockTags.SHULKER_BOXES);
    }
}
