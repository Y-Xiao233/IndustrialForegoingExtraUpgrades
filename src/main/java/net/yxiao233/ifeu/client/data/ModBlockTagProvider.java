package net.yxiao233.ifeu.client.data;

import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IndustrialForegoingExtraUpgrades.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(IFEUBlocks.INFUSER.getBlock())
                .add(IFEUBlocks.DRAGON_STAR_GENERATOR.getBlock())
                .add(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock())
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getBlock())
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get())
                .add(IFEUContents.DRAGON_STAR_BLOCK.get())
                .add(IFEUBlocks.WEATHER_CONTROLLER.getBlock())
                .add(IFEUBlocks.TIME_CONTROLLER.getBlock())
                .add(IFEUBlocks.RULE_CONTROLLER.getBlock())
                .add(IFEUBlocks.FLUID_CRAFTING_TABLE.getBlock())
                .add(IFEUBlocks.DRAGON_GENERATOR.getBlock())
                .add(IFEUBlocks.FLUID_TRANSFER.getBlock())
                .add(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock())
                .add(ModuleCore.PITY.get())
                .add(ModuleCore.SIMPLE.get())
                .add(ModuleCore.ADVANCED.get())
                .add(ModuleCore.SUPREME.get())
                .add(IFEUBlocks.PLATFORM_BUILDER.getBlock())
                .add(IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(IFEUBlocks.INFUSER.getBlock())
                .add(IFEUBlocks.DRAGON_STAR_GENERATOR.getBlock())
                .add(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock())
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getBlock())
                .add(IFEUBlocks.WEATHER_CONTROLLER.getBlock())
                .add(IFEUBlocks.TIME_CONTROLLER.getBlock())
                .add(IFEUBlocks.RULE_CONTROLLER.getBlock())
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get())
                .add(IFEUContents.DRAGON_STAR_BLOCK.get())
                .add(IFEUBlocks.FLUID_CRAFTING_TABLE.getBlock())
                .add(IFEUBlocks.DRAGON_GENERATOR.getBlock())
                .add(IFEUBlocks.FLUID_TRANSFER.getBlock())
                .add(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock())
                .add(IFEUBlocks.PLATFORM_BUILDER.getBlock())
                .add(IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock());


        this.tag(IFEUTags.Blocks.MACHINE_FRAME_ULTIMATE)
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get());

        this.tag(IFEUTags.Blocks.WRENCH_PICKUP)
                .addTag(Tags.Blocks.CHESTS)
                .addTag(BlockTags.SHULKER_BOXES)
                .addTag(Tags.Blocks.BARRELS)
                .add(ModuleCore.PITY.get())
                .add(ModuleCore.SIMPLE.get())
                .add(ModuleCore.ADVANCED.get())
                .add(ModuleCore.SUPREME.get())
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get());

//        this.tag(IFEUTags.Blocks.BLACK_HOLE_CAPACITOR)
//                .add(IFEUBlocks.BLACK_HOLE_CAPACITOR_PITY.getLeft().get())
//                .add(IFEUBlocks.BLACK_HOLE_CAPACITOR_SIMPLE.getLeft().get())
//                .add(IFEUBlocks.BLACK_HOLE_CAPACITOR_ADVANCED.getLeft().get())
//                .add(IFEUBlocks.BLACK_HOLE_CAPACITOR_SUPREME.getLeft().get());

        this.tag(IFEUTags.Blocks.STORAGE_FLUID)
//                .addTag(IFEUTags.Blocks.BLACK_HOLE_TANK)
                .add(IFEUBlocks.FLUID_TRANSFER.getBlock());

        this.tag(IFEUTags.Blocks.STORAGE_ITEM)
                .addTag(Tags.Blocks.BARRELS);

        this.tag(IFEUTags.Blocks.STORAGE_ENERGY)
//                .addTag(IFEUTags.Blocks.BLACK_HOLE_CAPACITOR)
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getBlock());
    }
}
