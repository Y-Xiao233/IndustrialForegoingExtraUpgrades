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
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IndustrialForegoingExtraUpgrades.MODID, exFileHelper);
    }
    public static ResourceLocation getModel(Block block) {
        return new ResourceLocation(ForgeRegistries.BLOCKS.getKey(block).getNamespace(), "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath());
    }

    @Override
    protected void registerStatesAndModels() {
        blockItem(IFEUBlocks.INFUSER.getLeft());
        blockItem(IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft());
        blockItem(IFEUBlocks.CREATIVE_CAPACITOR.getLeft());
        blockItem(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft());
        blockItem(IFEUContents.DEAD_DRAGON_EGG);
        blockItem(IFEUBlocks.WEATHER_CONTROLLER.getLeft());
        blockItem(IFEUBlocks.TIME_CONTROLLER.getLeft());
        blockItem(IFEUBlocks.RULE_CONTROLLER.getLeft());
        blockItem(IFEUContents.ULTIMATE_MACHINE_FRAME);
        blockItem(IFEUContents.DRAGON_STAR_BLOCK);
        blockItem(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft());
        blockItem(IFEUBlocks.DRAGON_GENERATOR.getLeft());
        blockItem(IFEUBlocks.FLUID_TRANSFER.getLeft());
        blockItem(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft());
        blockItem(IFEUBlocks.SIMULATED_HYDROPONIC_BED.getLeft());
        blockItem(IFEUBlocks.PLATFORM_BUILDER.getLeft());
        blockItem(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft());
    }

    private void blockItem(RegistryObject<Block> registryObject){
        simpleBlockItem(registryObject.get(),new ModelFile.UncheckedModelFile(IndustrialForegoingExtraUpgrades.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(registryObject.get()).getPath()));
    }
}