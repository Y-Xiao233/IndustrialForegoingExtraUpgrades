package net.yxiao233.ifeu.client.data.loot;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleTransportStorage;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.util.NonNullLazy;
import net.yxiao233.ifeu.api.loot_table.IBlockLootTableProvider;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.Arrays;
import java.util.List;
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
        this.dropSelf(ModBlocks.DRAGON_GENERATOR.getLeft().get());
        this.dropSelf(ModBlocks.FLUID_TRANSFER.getLeft().get());
        this.dropSelf(ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get());
        this.dropSelf(ModuleCore.PITY.get());
        this.dropSelf(ModuleCore.SIMPLE.get());
        this.dropSelf(ModuleCore.ADVANCED.get());
        this.dropSelf(ModuleCore.SUPREME.get());
        this.dropSelf(ModBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get());
        this.dropSelf(ModBlocks.PLATFORM_BUILDER.getLeft().get());
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
                ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get(),
                ModBlocks.DRAGON_GENERATOR.getLeft().get(),
                ModBlocks.FLUID_TRANSFER.getLeft().get(),
                ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get(),
                ModuleCore.PITY.get(),
                ModuleCore.SIMPLE.get(),
                ModuleCore.ADVANCED.get(),
                ModuleCore.SUPREME.get(),
                ModBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get(),
                ModBlocks.PLATFORM_BUILDER.getLeft().get()
        );
        return iterable;
    }

    public LootTable.Builder droppingSelfWithNbt(ItemLike itemProvider, CopyNbtFunction.Builder nbtBuilder) {
        return LootTable.lootTable().withPool((LootPool.Builder)this.applyExplosionCondition(itemProvider, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemProvider).apply(nbtBuilder))));
    }
}
