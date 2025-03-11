package net.yxiao233.ifeu.api.loot_table;

import net.minecraft.world.level.storage.loot.LootTable;
import net.yxiao233.ifeu.client.data.loot.ModBlockLootTables;

public interface IBlockLootTableProvider {
    LootTable.Builder getLootTable(ModBlockLootTables tables);
}
