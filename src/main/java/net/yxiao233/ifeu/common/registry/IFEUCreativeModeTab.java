package net.yxiao233.ifeu.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class IFEUCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IndustrialForegoingExtraUpgrades.MODID);
    public static final RegistryObject<CreativeModeTab> IFEU_TAB = CREATIVE_MODE_TAB.register("ifeu_tab", () -> CreativeModeTab.builder()
            .icon(() -> IFEUBlocks.INFUSER.getLeft().get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {

                IFEUContents.ITEMS.getEntries().forEach((reg) ->{
                    if(reg.get().getDefaultInstance().is(IFEUContents.AIR.get()) || reg.get().getDefaultInstance().is(IFEUContents.HYDROPONIC_SIMULATION_PROCESSOR.get())){
                        int i = 0;
                    }else{
                        output.accept(reg.get());
                    }
                });

                IFEUFluids.addBucketItemsToCreativeModeTab().forEach(item -> output.accept(item.get()));
                IFEUBlocks.addBlocksToCreativeModeTab().forEach(block -> output.accept(block.get()));
            })
            .title(Component.translatable("itemGroup.ifeu.extra_contents"))
            .build()
    );
}
