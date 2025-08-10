package net.yxiao233.ifeu.client.data;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleTransportStorage;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
                .add(IFEUBlocks.INFUSER.getLeft().get())
                .add(IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get())
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getLeft().get())
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get())
                .add(IFEUContents.DRAGON_STAR_BLOCK.get())
                .add(IFEUBlocks.WEATHER_CONTROLLER.getLeft().get())
                .add(IFEUBlocks.TIME_CONTROLLER.getLeft().get())
                .add(IFEUBlocks.RULE_CONTROLLER.getLeft().get())
                .add(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get())
                .add(IFEUBlocks.DRAGON_GENERATOR.getLeft().get())
                .add(IFEUBlocks.FLUID_TRANSFER.getLeft().get())
                .add(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get())
                .add(ModuleCore.PITY.get())
                .add(ModuleCore.SIMPLE.get())
                .add(ModuleCore.ADVANCED.get())
                .add(ModuleCore.SUPREME.get())
                .add(IFEUBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get())
                .add(IFEUBlocks.PLATFORM_BUILDER.getLeft().get())
                .add(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(IFEUBlocks.INFUSER.getLeft().get())
                .add(IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft().get())
                .add(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get())
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getLeft().get())
                .add(IFEUBlocks.WEATHER_CONTROLLER.getLeft().get())
                .add(IFEUBlocks.TIME_CONTROLLER.getLeft().get())
                .add(IFEUBlocks.RULE_CONTROLLER.getLeft().get())
                .add(IFEUContents.ULTIMATE_MACHINE_FRAME.get())
                .add(IFEUContents.DRAGON_STAR_BLOCK.get())
                .add(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get())
                .add(IFEUBlocks.DRAGON_GENERATOR.getLeft().get())
                .add(IFEUBlocks.FLUID_TRANSFER.getLeft().get())
                .add(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get())
                .add(IFEUBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get())
                .add(IFEUBlocks.PLATFORM_BUILDER.getLeft().get())
                .add(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get());


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

        this.tag(IFEUTags.Blocks.BLACK_HOLE_TANK)
                .add(ModuleTransportStorage.BLACK_HOLE_TANK_COMMON.getLeft().get())
                .add(ModuleTransportStorage.BLACK_HOLE_TANK_PITY.getLeft().get())
                .add(ModuleTransportStorage.BLACK_HOLE_TANK_SIMPLE.getLeft().get())
                .add(ModuleTransportStorage.BLACK_HOLE_TANK_ADVANCED.getLeft().get())
                .add(ModuleTransportStorage.BLACK_HOLE_TANK_SUPREME.getLeft().get());


        this.tag(IFEUTags.Blocks.STORAGE_FLUID)
                .addTag(IFEUTags.Blocks.BLACK_HOLE_TANK)
                .add(IFEUBlocks.FLUID_TRANSFER.getLeft().get());

        this.tag(IFEUTags.Blocks.STORAGE_ITEM)
                .addTag(Tags.Blocks.BARRELS);

        this.tag(IFEUTags.Blocks.STORAGE_ENERGY)
                .add(IFEUBlocks.CREATIVE_CAPACITOR.getLeft().get());
    }
}
