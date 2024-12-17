package net.yxiao233.ifeu.client.data;


import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.yxiao233.ifeu.client.data.loot.ModBlockLootTables;

import java.util.List;
import java.util.Set;

public class ModBlockLootTablesProvider {
    public static LootTableProvider create(PackOutput output){
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
