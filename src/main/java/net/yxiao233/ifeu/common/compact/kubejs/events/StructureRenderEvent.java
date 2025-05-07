package net.yxiao233.ifeu.common.compact.kubejs.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

@Mod.EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class StructureRenderEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        if(ModList.get().isLoaded("kubejs")){
            IFEUStructureRenderJS.registryRender(event);
        }
    }
}
