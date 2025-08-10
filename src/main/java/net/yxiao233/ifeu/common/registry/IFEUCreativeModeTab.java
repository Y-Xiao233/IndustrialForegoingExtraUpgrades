package net.yxiao233.ifeu.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class IFEUCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> IFEU_TAB = CREATIVE_MODE_TAB.register("ifeu_tab", () -> CreativeModeTab.builder()
            .icon(() -> IFEUBlocks.INFUSER.getBlock().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {

                IFEUContents.ITEMS.getEntries().forEach((reg) ->{
                    if(!reg.get().getDefaultInstance().is(IFEUContents.AIR)){
                        output.accept(reg.get());
                    }
                });

                IFEUFluids.addBucketItemsToCreativeModeTab().forEach(item -> output.accept(item.getDefaultInstance()));
                IFEUBlocks.addBlocksToCreativeModeTab().forEach(block -> output.accept(block.asItem()));
            })
            .title(Component.translatable("itemGroup.ifeu.extra_contents"))
            .build()
    );
}
