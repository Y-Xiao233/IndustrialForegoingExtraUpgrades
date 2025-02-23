package net.yxiao233.ifeu.client.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.Arrays;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(),FeatureFlags.REGISTRY.allFlags(),provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.INFUSER.getBlock());
        this.dropSelf(ModBlocks.DRAGON_STAR_GENERATOR.getBlock());
        this.dropSelf(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock());
        this.dropSelf(ModBlocks.CREATIVE_CAPACITOR.getBlock());
        this.dropSelf(ModBlocks.WEATHER_CONTROLLER.getBlock());
        this.dropSelf(ModBlocks.TIME_CONTROLLER.getBlock());
        this.dropSelf(ModBlocks.RULE_CONTROLLER.getBlock());
        this.dropSelf(ModContents.ULTIMATE_MACHINE_FRAME.get());
        this.dropSelf(ModContents.DRAGON_STAR_BLOCK.get());
        this.dropSelf(ModBlocks.FLUID_CRAFTING_TABLE.getBlock());
        this.dropSelf(ModBlocks.DRAGON_GENERATOR.getBlock());
        this.dropSelf(ModBlocks.FLUID_TRANSFER.getBlock());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //注册方法时后面加上.noLootTable()就不需要在这里写
        Iterable<Block> iterable = Arrays.asList(
                ModBlocks.INFUSER.getBlock(),
                ModBlocks.DRAGON_STAR_GENERATOR.getBlock(),
                ModBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock(),
                ModBlocks.CREATIVE_CAPACITOR.getBlock(),
                ModBlocks.RULE_CONTROLLER.getBlock(),
                ModBlocks.WEATHER_CONTROLLER.getBlock(),
                ModBlocks.TIME_CONTROLLER.getBlock(),
                ModContents.ULTIMATE_MACHINE_FRAME.get(),
                ModContents.DRAGON_STAR_BLOCK.get(),
                ModBlocks.FLUID_CRAFTING_TABLE.getBlock(),
                ModBlocks.DRAGON_GENERATOR.getBlock(),
                ModBlocks.FLUID_TRANSFER.getBlock()
        );
        return iterable;
    }
}
