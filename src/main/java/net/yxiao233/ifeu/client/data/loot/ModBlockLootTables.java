package net.yxiao233.ifeu.client.data.loot;

import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;

import java.util.Arrays;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(),FeatureFlags.REGISTRY.allFlags(),provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(IFEUBlocks.INFUSER.getBlock());
        this.dropSelf(IFEUBlocks.DRAGON_STAR_GENERATOR.getBlock());
        this.dropSelf(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock());
        this.dropSelf(IFEUBlocks.CREATIVE_CAPACITOR.getBlock());
        this.dropSelf(IFEUBlocks.WEATHER_CONTROLLER.getBlock());
        this.dropSelf(IFEUBlocks.TIME_CONTROLLER.getBlock());
        this.dropSelf(IFEUBlocks.RULE_CONTROLLER.getBlock());
        this.dropSelf(IFEUContents.ULTIMATE_MACHINE_FRAME.get());
        this.dropSelf(IFEUContents.DRAGON_STAR_BLOCK.get());
        this.dropSelf(IFEUBlocks.FLUID_CRAFTING_TABLE.getBlock());
        this.dropSelf(IFEUBlocks.DRAGON_GENERATOR.getBlock());
        this.dropSelf(IFEUBlocks.FLUID_TRANSFER.getBlock());
        this.dropSelf(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock());
        this.dropSelf(ModuleCore.PITY.get());
        this.dropSelf(ModuleCore.SIMPLE.get());
        this.dropSelf(ModuleCore.ADVANCED.get());
        this.dropSelf(ModuleCore.SUPREME.get());
        this.dropSelf(IFEUBlocks.PLATFORM_BUILDER.getBlock());
        this.dropSelf(IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //注册方法时后面加上.noLootTable()就不需要在这里写
        Iterable<Block> iterable = Arrays.asList(
                IFEUBlocks.INFUSER.getBlock(),
                IFEUBlocks.DRAGON_STAR_GENERATOR.getBlock(),
                IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getBlock(),
                IFEUBlocks.CREATIVE_CAPACITOR.getBlock(),
                IFEUBlocks.RULE_CONTROLLER.getBlock(),
                IFEUBlocks.WEATHER_CONTROLLER.getBlock(),
                IFEUBlocks.TIME_CONTROLLER.getBlock(),
                IFEUContents.ULTIMATE_MACHINE_FRAME.get(),
                IFEUContents.DRAGON_STAR_BLOCK.get(),
                IFEUBlocks.FLUID_CRAFTING_TABLE.getBlock(),
                IFEUBlocks.DRAGON_GENERATOR.getBlock(),
                IFEUBlocks.FLUID_TRANSFER.getBlock(),
                IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getBlock(),
                ModuleCore.PITY.get(),
                ModuleCore.SIMPLE.get(),
                ModuleCore.ADVANCED.get(),
                ModuleCore.SUPREME.get(),
                IFEUBlocks.PLATFORM_BUILDER.getBlock(),
                IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock()
        );
        return iterable;
    }

//    public LootTable.Builder droppingSelfWithNbt(ItemLike itemProvider, CopyNbtFunction.Builder nbtBuilder) {
//        return LootTable.lootTable().withPool((LootPool.Builder)this.applyExplosionCondition(itemProvider, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemProvider).apply(nbtBuilder))));
//    }
}
