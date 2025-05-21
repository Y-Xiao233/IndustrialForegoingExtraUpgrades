package net.yxiao233.ifeu.client.data;

import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output,IndustrialForegoingExtraUpgrades.MODID,exFileHelper);
    }
    public static ResourceLocation getModel(Block block) {
        return ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(block).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithTileItem(ModBlocks.INFUSER);
        blockWithTileItem(ModBlocks.DRAGON_STAR_GENERATOR);
        blockWithTileItem(ModBlocks.CREATIVE_CAPACITOR);
        blockWithTileItem(ModBlocks.ARCANE_DRAGON_EGG_FORGING);
        blockItem(ModContents.DEAD_DRAGON_EGG);
        blockWithTileItem(ModBlocks.WEATHER_CONTROLLER);
        blockWithTileItem(ModBlocks.TIME_CONTROLLER);
        blockWithTileItem(ModBlocks.RULE_CONTROLLER);
        blockItem(ModContents.ULTIMATE_MACHINE_FRAME);
        blockItem(ModContents.DRAGON_STAR_BLOCK);
        blockWithTileItem(ModBlocks.FLUID_CRAFTING_TABLE);
        blockWithTileItem(ModBlocks.DRAGON_GENERATOR);
        blockWithTileItem(ModBlocks.FLUID_TRANSFER);
        blockWithTileItem(ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE);
        blockWithTileItem(ModBlocks.PLATFORM_BUILDER);
    }

    private void blockItem(DeferredHolder<Block,Block> registryObject){
        simpleBlockItem(registryObject.get(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(registryObject.get()).getPath()));
    }

    private void blockWithTileItem(BlockWithTile blockWithTile){
        simpleBlockItem(blockWithTile.getBlock(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockWithTile.getBlock()).getPath()));
    }
}