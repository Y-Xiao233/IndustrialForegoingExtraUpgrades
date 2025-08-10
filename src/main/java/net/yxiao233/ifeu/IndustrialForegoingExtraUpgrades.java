package net.yxiao233.ifeu;

import com.hrznstudio.titanium.module.ModuleController;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.yxiao233.ifeu.common.provider.ModRecipeProvider;
import net.yxiao233.ifeu.common.registry.*;

@Mod(IndustrialForegoingExtraUpgrades.MODID)
public class IndustrialForegoingExtraUpgrades extends ModuleController {
    public static final String MODID = "ifeu";
    public IndustrialForegoingExtraUpgrades(IEventBus modEventBus, ModContainer modContainer) {
        super(modContainer);
//        NeoForge.EVENT_BUS.register(this);

        IFEUContents.BLOCKS.register(modEventBus);
        IFEUContents.ITEMS.register(modEventBus);
        IFEUCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
        IFEUDataComponentTypes.DATA_COMPONENTS.register(modEventBus);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(IFEUBlocks.FLUID_CRAFTING_TABLE.getBlock(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(IFEUBlocks.FLUID_TRANSFER.getBlock(), RenderType.translucent());
        }
    }

    @Override
    protected void initModules() {
        new IFEUItems().generateFeatures(getRegistries());
        new IFEUBlocks().generateFeatures(getRegistries());
        new IFEURecipes().generateFeatures(getRegistries());
        new IFEUFluids().generateFeatures(getRegistries());
        this.addCreativeTab("extra_upgrades", () -> new ItemStack(IFEUItems.SPEED_ADDON_6.get()),IndustrialForegoingExtraUpgrades.MODID+ ".extra_upgrades", IFEUItems.TAB_ADDONS);
    }

    @Override
    public void addDataProvider(GatherDataEvent event) {
        super.addDataProvider(event);
        event.getGenerator().addProvider(event.includeServer(), new ModRecipeProvider(event.getGenerator().getPackOutput(),event.getLookupProvider()));
    }
}
