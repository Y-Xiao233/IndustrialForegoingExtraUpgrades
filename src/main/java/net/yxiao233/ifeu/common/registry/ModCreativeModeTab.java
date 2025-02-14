package net.yxiao233.ifeu.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IndustrialForegoingExtraUpgrades.MODID);
    public static final RegistryObject<CreativeModeTab> IFEU_TAB = CREATIVE_MODE_TAB.register("ifeu_tab", () -> CreativeModeTab.builder()
            .icon(() -> ModBlocks.INFUSER.getLeft().get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {

                ModContents.ITEMS.getEntries().forEach((reg) ->{
                    if(!reg.get().getDefaultInstance().is(ModContents.AIR.get())){
                        output.accept(reg.get());
                    }
                });

                ModFluids.addBucketItemsToCreativeModeTab().forEach(item -> output.accept(item.get()));
                ModBlocks.addBlocksToCreativeModeTab().forEach(block -> output.accept(block.get()));
            })
            .title(Component.translatable("itemGroup.ifeu.extra_contents"))
            .build()
    );
}
