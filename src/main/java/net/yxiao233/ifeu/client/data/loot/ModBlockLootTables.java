package net.yxiao233.ifeu.client.data.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.Arrays;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.INFUSER.getKey().get());
        this.dropSelf(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get());
        this.dropSelf(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get());
        this.dropSelf(ModBlocks.CREATIVE_CAPACITOR.getLeft().get());
        this.dropSelf(ModBlocks.WEATHER_CONTROLLER.getLeft().get());
        this.dropSelf(ModBlocks.TIME_CONTROLLER.getLeft().get());
        this.dropSelf(ModBlocks.RULE_CONTROLLER.getLeft().get());
        this.dropSelf(ModContents.ULTIMATE_MACHINE_FRAME.get());
        this.dropSelf(ModContents.DRAGON_STAR_BLOCK.get());
        this.dropSelf(ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //注册方法时后面加上.noLootTable()就不需要在这里写
        Iterable<Block> iterable = Arrays.asList(
                ModBlocks.INFUSER.getLeft().get(),
                ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get(),
                ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get(),
                ModBlocks.CREATIVE_CAPACITOR.getLeft().get(),
                ModBlocks.RULE_CONTROLLER.getLeft().get(),
                ModBlocks.WEATHER_CONTROLLER.getLeft().get(),
                ModBlocks.TIME_CONTROLLER.getLeft().get(),
                ModContents.ULTIMATE_MACHINE_FRAME.get(),
                ModContents.DRAGON_STAR_BLOCK.get(),
                ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get()
        );
        return iterable;
    }
}
