package net.yxiao233.ifeu.client.data.loot;

import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
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
        this.dropSelf(ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock());
        this.dropSelf(ModuleCore.PITY.get());
        this.dropSelf(ModuleCore.SIMPLE.get());
        this.dropSelf(ModuleCore.ADVANCED.get());
        this.dropSelf(ModuleCore.SUPREME.get());
        this.dropSelf(ModBlocks.PLATFORM_BUILDER.getBlock());
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
                ModBlocks.FLUID_TRANSFER.getBlock(),
                ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock(),
                ModuleCore.PITY.get(),
                ModuleCore.SIMPLE.get(),
                ModuleCore.ADVANCED.get(),
                ModuleCore.SUPREME.get(),
                ModBlocks.PLATFORM_BUILDER.getBlock()
        );
        return iterable;
    }

//    public LootTable.Builder droppingSelfWithNbt(ItemLike itemProvider, CopyNbtFunction.Builder nbtBuilder) {
//        return LootTable.lootTable().withPool((LootPool.Builder)this.applyExplosionCondition(itemProvider, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemProvider).apply(nbtBuilder))));
//    }
}
