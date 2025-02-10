package net.yxiao233.ifeu.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput,existingFileHelper));

        generator.addProvider(event.includeServer(), ModBlockLootTablesProvider.create(packOutput,lookupProvider));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput,existingFileHelper));

        ModBlockTagProvider blockTagProvider = generator.addProvider(event.includeClient(),
                new ModBlockTagProvider(packOutput,lookupProvider,existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemTagProvider(packOutput,lookupProvider,blockTagProvider.contentsGetter()));
    }
}
