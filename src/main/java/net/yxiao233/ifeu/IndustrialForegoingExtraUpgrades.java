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
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.yxiao233.ifeu.common.provider.ModRecipeProvider;
import net.yxiao233.ifeu.common.registry.*;

@Mod(IndustrialForegoingExtraUpgrades.MODID)
public class IndustrialForegoingExtraUpgrades extends ModuleController {
    public static final String MODID = "ifeu";
    public IndustrialForegoingExtraUpgrades(IEventBus modEventBus, ModContainer modContainer) {
        super(modContainer);
//        NeoForge.EVENT_BUS.register(this);

        ModContents.BLOCKS.register(modEventBus);
        ModContents.ITEMS.register(modEventBus);
        ModCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
        ModDataComponentTypes.DATA_COMPONENTS.register(modEventBus);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLUID_CRAFTING_TABLE.getBlock(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLUID_TRANSFER.getBlock(), RenderType.translucent());
        }
    }

    @Override
    protected void initModules() {
        new ModItems().generateFeatures(getRegistries());
        new ModBlocks().generateFeatures(getRegistries());
        new ModRecipes().generateFeatures(getRegistries());
        new ModFluids().generateFeatures(getRegistries());
        this.addCreativeTab("extra_upgrades", () -> new ItemStack(ModItems.SPEED_ADDON_6.get()),IndustrialForegoingExtraUpgrades.MODID+ ".extra_upgrades", ModItems.TAB_ADDONS);
    }

    @Override
    public void addDataProvider(GatherDataEvent event) {
        super.addDataProvider(event);
        event.getGenerator().addProvider(event.includeServer(), new ModRecipeProvider(event.getGenerator().getPackOutput(),event.getLookupProvider()));
    }
}
