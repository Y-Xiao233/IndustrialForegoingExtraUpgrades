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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yxiao233.ifeu.common.networking.ModNetWorking;
import net.yxiao233.ifeu.common.provider.ModRecipeProvider;
import net.yxiao233.ifeu.common.provider.ModSerializableProvider;
import net.yxiao233.ifeu.common.registry.*;

@Mod(IndustrialForegoingExtraUpgrades.MODID)
public class IndustrialForegoingExtraUpgrades extends ModuleController{

    public static final String MODID = "ifeu";
    public IndustrialForegoingExtraUpgrades(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        ModContents.BLOCKS.register(modEventBus);
        ModContents.ITEMS.register(modEventBus);
        ModCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
        ModNetWorking.register();
    }
    @SuppressWarnings("deprecation")
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLUID_TRANSFER.getLeft().get(), RenderType.translucent());
        }
    }

    @Override
    protected void initModules(){
        new ModItems().generateFeatures(getRegistries());
        new ModBlocks().generateFeatures(getRegistries());
        new ModRecipes().generateFeatures(getRegistries());
        new ModFluids().generateFeatures(getRegistries());
        this.addCreativeTab("extra_upgrades", () -> new ItemStack(ModItems.SPEED_ADDON_6.get()),IndustrialForegoingExtraUpgrades.MODID+ ".extra_upgrades", ModItems.TAB_ADDONS);
    }

    @Override
    public void addDataProvider(GatherDataEvent event){
        event.getGenerator().addProvider(event.includeServer(), new ModRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(event.includeServer(), new ModSerializableProvider(event.getGenerator(),IndustrialForegoingExtraUpgrades.MODID));
    }
}
