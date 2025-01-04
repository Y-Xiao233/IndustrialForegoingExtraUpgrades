package net.yxiao233.ifeu.client.data.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.common.registry.ModBlocks;

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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //注册方法时后面加上.noLootTable()就不会在这里生成
        Iterable<Block> iterable = Arrays.asList(
                ModBlocks.INFUSER.getLeft().get(),
                ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get(),
                ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get()
        );
        return iterable;
    }
}
