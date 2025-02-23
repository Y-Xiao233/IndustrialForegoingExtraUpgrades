package net.yxiao233.ifeu.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

import java.util.List;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<BlockPos>> BLOCK_POS = DATA_COMPONENTS.register("block_pos",() -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC).networkSynchronized(BlockPos.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<List<BlockPos>>> LIST_BLOCK_POS = DATA_COMPONENTS.register("list_block_pos",() -> DataComponentType.<List<BlockPos>>builder().persistent(BlockPos.CODEC.listOf()).build());
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<CompoundTag>> COMPOUND_TAG = DATA_COMPONENTS.register("compound_tag",() -> DataComponentType.<CompoundTag>builder().persistent(CompoundTag.CODEC).build());
}
