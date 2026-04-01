package net.yxiao233.ifeu.client.data;

import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.tree.DeferredTree;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;

import java.util.ArrayList;
import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {
    private final List<ResourceLocation> generated = new ArrayList<>();
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output,IndustrialForegoingExtraUpgrades.MODID,exFileHelper);
    }
    public static ResourceLocation getModel(Block block) {
        return ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(block).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithTileItem(IFEUBlocks.INFUSER);
        blockWithTileItem(IFEUBlocks.DRAGON_STAR_GENERATOR);
        blockWithTileItem(IFEUBlocks.CREATIVE_CAPACITOR);
        blockWithTileItem(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING);
        blockItem(IFEUContents.DEAD_DRAGON_EGG);
        blockWithTileItem(IFEUBlocks.WEATHER_CONTROLLER);
        blockWithTileItem(IFEUBlocks.TIME_CONTROLLER);
        blockWithTileItem(IFEUBlocks.RULE_CONTROLLER);
        blockItem(IFEUContents.ULTIMATE_MACHINE_FRAME);
        blockItem(IFEUContents.DRAGON_STAR_BLOCK);
        blockWithTileItem(IFEUBlocks.FLUID_CRAFTING_TABLE);
        blockWithTileItem(IFEUBlocks.DRAGON_GENERATOR);
        blockWithTileItem(IFEUBlocks.FLUID_TRANSFER);
        blockWithTileItem(IFEUBlocks.PLATFORM_BUILDER);
        blockWithTileItem(IFEUBlocks.PRECISION_CRAFTING_TABLE);
        blockWithTileItem(IFEUBlocks.SAUCEPAN);
        blockWithTileItem(IFEUBlocks.FERMENTER);


        DeferredTree.DeferredTreesRegister.getAllTrees().forEach(tree ->{
            if(shouldGenerated(tree.getLogBlock().get())){
                logBlock(((RotatedPillarBlock) tree.getLogBlock().get()));
                blockItem(tree.getLogBlock());
                generated(tree.getLogBlock().get());
            }
            if(shouldGenerated(tree.getStrippedLogBlock().get())){
                logBlock(((RotatedPillarBlock) tree.getStrippedLogBlock().get()));
                blockItem(tree.getStrippedLogBlock());
                generated(tree.getStrippedLogBlock().get());
            }

            if(shouldGenerated(tree.getWoodBlock().get())){
                axisBlock(((RotatedPillarBlock) tree.getWoodBlock().get()),blockTexture(tree.getLogBlock().get()), blockTexture(tree.getLogBlock().get()));
                blockItem(tree.getWoodBlock());
                generated(tree.getWoodBlock().get());
            }
            if(shouldGenerated(tree.getStrippedWoodBlock().get())){
                axisBlock(((RotatedPillarBlock) tree.getStrippedWoodBlock().get()),blockTexture(tree.getStrippedLogBlock().get()), blockTexture(tree.getStrippedLogBlock().get()));
                blockItem(tree.getStrippedWoodBlock());
                generated(tree.getStrippedWoodBlock().get());
            }


            if(shouldGenerated(tree.getPlanksBlock().get())){
                simpleBlockWithItem(tree.getPlanksBlock().get(), models().singleTexture(BuiltInRegistries.BLOCK.getKey(tree.getPlanksBlock().get()).getPath(), ResourceLocation.parse("minecraft:block/cube_all"),"all",blockTexture(tree.getPlanksBlock().get())));
                generated(tree.getPlanksBlock().get());
            }

            simpleBlock(tree.getSaplingBlock().get(), models().cross(BuiltInRegistries.BLOCK.getKey(tree.getSaplingBlock().get()).getPath(),blockTexture(tree.getSaplingBlock().get())).renderType("cutout"));

            if(shouldGenerated(tree.getLeavesBlock().get())){
                simpleBlockWithItem(tree.getLeavesBlock().get(), models().singleTexture(BuiltInRegistries.BLOCK.getKey(tree.getLeavesBlock().get()).getPath(),ResourceLocation.parse("minecraft:block/leaves"),"all", blockTexture(tree.getLeavesBlock().get())).renderType("cutout"));
                generated(tree.getLeavesBlock().get());
            }
        });
    }

    private boolean shouldGenerated(Block block){
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
        return !generated.contains(location);
    }

    private void generated(Block block){
        generated.add(BuiltInRegistries.BLOCK.getKey(block));
    }

    private void blockItem(DeferredHolder<Block,Block> registryObject){
        simpleBlockItem(registryObject.get(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(registryObject.get()).getPath()));
    }

    private void blockWithTileItem(BlockWithTile blockWithTile){
        simpleBlockItem(blockWithTile.getBlock(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockWithTile.getBlock()).getPath()));
    }

    private void blockWithItem(DeferredHolder<Block, Block> block){
        simpleBlockWithItem(block.get(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath()));
    }
}