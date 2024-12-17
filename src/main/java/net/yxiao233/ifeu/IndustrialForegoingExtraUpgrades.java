package net.yxiao233.ifeu;

import com.hrznstudio.titanium.module.ModuleController;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yxiao233.ifeu.common.fluid.ModFluidTypes;
import net.yxiao233.ifeu.common.provider.ModRecipeProvider;
import net.yxiao233.ifeu.common.provider.ModSerializableProvider;
import net.yxiao233.ifeu.common.registry.*;

@Mod(IndustrialForegoingExtraUpgrades.MODID)
public class IndustrialForegoingExtraUpgrades extends ModuleController
{
    public static final String MODID = "ifeu";

    public IndustrialForegoingExtraUpgrades()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModFluids.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
        ModContents.BLOCKS.register(modEventBus);
        ModContents.ITEMS.register(modEventBus);
        ModCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LIQUID_DRAGON_BREATH.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LIQUID_DRAGON_BREATH.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LIQUID_SCULK_MATTER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LIQUID_SCULK_MATTER.get(), RenderType.translucent());
        }
    }

    @Override
    protected void initModules() {
        new ModItems().generateFeatures(getRegistries());
        new ModBlocks().generateFeatures(getRegistries());
        new ModRecipes().generateFeatures(getRegistries());
        this.addCreativeTab("extra_upgrades", () -> new ItemStack(ModItems.SPEED_ADDON_3.get()),IndustrialForegoingExtraUpgrades.MODID+ ".extra_upgrades", ModItems.TAB_ADDONS);
    }

    @Override
    public void addDataProvider(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), new ModRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(event.includeServer(), new ModSerializableProvider(event.getGenerator(),IndustrialForegoingExtraUpgrades.MODID));
    }
}
