package net.yxiao233.ifeu.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput,existingFileHelper));


        generator.addProvider(event.includeServer(), ModBlockLootTablesProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput,existingFileHelper));

        ModBlockTagProvider blockTagProvider = generator.addProvider(event.includeClient(),
                new ModBlockTagProvider(packOutput,lookupProvider,existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemTagProvider(packOutput,lookupProvider,blockTagProvider.contentsGetter()));
    }
}
