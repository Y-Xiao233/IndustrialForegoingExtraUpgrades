package net.yxiao233.ifeu.client.data.loot;

import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;

import java.util.Arrays;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(IFEUBlocks.INFUSER.getKey().get());
        this.dropSelf(IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft().get());
        this.dropSelf(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get());
        this.dropSelf(IFEUBlocks.CREATIVE_CAPACITOR.getLeft().get());
        this.dropSelf(IFEUBlocks.WEATHER_CONTROLLER.getLeft().get());
        this.dropSelf(IFEUBlocks.TIME_CONTROLLER.getLeft().get());
        this.dropSelf(IFEUBlocks.RULE_CONTROLLER.getLeft().get());
        this.dropSelf(IFEUContents.ULTIMATE_MACHINE_FRAME.get());
        this.dropSelf(IFEUContents.DRAGON_STAR_BLOCK.get());
        this.dropSelf(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get());
        this.dropSelf(IFEUBlocks.DRAGON_GENERATOR.getLeft().get());
        this.dropSelf(IFEUBlocks.FLUID_TRANSFER.getLeft().get());
        this.dropSelf(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get());
        this.dropSelf(ModuleCore.PITY.get());
        this.dropSelf(ModuleCore.SIMPLE.get());
        this.dropSelf(ModuleCore.ADVANCED.get());
        this.dropSelf(ModuleCore.SUPREME.get());
        this.dropSelf(IFEUBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get());
        this.dropSelf(IFEUBlocks.PLATFORM_BUILDER.getLeft().get());
        this.dropSelf(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //注册方法时后面加上.noLootTable()就不需要在这里写
        Iterable<Block> iterable = Arrays.asList(
                IFEUBlocks.INFUSER.getLeft().get(),
                IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft().get(),
                IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get(),
                IFEUBlocks.CREATIVE_CAPACITOR.getLeft().get(),
                IFEUBlocks.RULE_CONTROLLER.getLeft().get(),
                IFEUBlocks.WEATHER_CONTROLLER.getLeft().get(),
                IFEUBlocks.TIME_CONTROLLER.getLeft().get(),
                IFEUContents.ULTIMATE_MACHINE_FRAME.get(),
                IFEUContents.DRAGON_STAR_BLOCK.get(),
                IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get(),
                IFEUBlocks.DRAGON_GENERATOR.getLeft().get(),
                IFEUBlocks.FLUID_TRANSFER.getLeft().get(),
                IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get(),
                ModuleCore.PITY.get(),
                ModuleCore.SIMPLE.get(),
                ModuleCore.ADVANCED.get(),
                ModuleCore.SUPREME.get(),
                IFEUBlocks.SIMULATED_HYDROPONIC_BED.getLeft().get(),
                IFEUBlocks.PLATFORM_BUILDER.getLeft().get(),
                IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get()
        );
        return iterable;
    }

    public LootTable.Builder droppingSelfWithNbt(ItemLike itemProvider, CopyNbtFunction.Builder nbtBuilder) {
        return LootTable.lootTable().withPool((LootPool.Builder)this.applyExplosionCondition(itemProvider, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemProvider).apply(nbtBuilder))));
    }
}
