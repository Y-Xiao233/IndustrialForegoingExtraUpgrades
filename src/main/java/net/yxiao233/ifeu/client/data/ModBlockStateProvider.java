package net.yxiao233.ifeu.client.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IndustrialForegoingExtraUpgrades.MODID, exFileHelper);
    }
    public static ResourceLocation getModel(Block block) {
        return new ResourceLocation(ForgeRegistries.BLOCKS.getKey(block).getNamespace(), "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath());
    }

    @Override
    protected void registerStatesAndModels() {
        blockItem(ModBlocks.INFUSER.getLeft());
        blockItem(ModBlocks.DRAGON_STAR_GENERATOR.getLeft());
        blockItem(ModBlocks.CREATIVE_CAPACITOR.getLeft());
        blockItem(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft());
        blockItem(ModContents.DEAD_DRAGON_EGG);
        blockItem(ModBlocks.WEATHER_CONTROLLER.getLeft());
        blockItem(ModBlocks.TIME_CONTROLLER.getLeft());
        blockItem(ModBlocks.RULE_CONTROLLER.getLeft());
        blockItem(ModContents.ULTIMATE_MACHINE_FRAME);
        blockItem(ModContents.DRAGON_STAR_BLOCK);
        blockItem(ModBlocks.FLUID_CRAFTING_TABLE.getLeft());
    }

    private void blockItem(RegistryObject<Block> registryObject){
        simpleBlockItem(registryObject.get(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(registryObject.get()).getPath()));
    }
}