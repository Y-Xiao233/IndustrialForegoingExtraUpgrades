package net.yxiao233.ifeu.client.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.networking.packet.BooleanPayloadHandler;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NetWorkRegistryEvent {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playBidirectional(
                BooleanSyncS2CPacket.TYPE,
                BooleanSyncS2CPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        BooleanPayloadHandler::handle,
                        BooleanPayloadHandler::handle
                )
        );
        registrar = registrar.executesOn(HandlerThread.NETWORK);
    }
}
