package net.yxiao233.ifeu.client.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.networking.packet.*;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD)
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

        registrar.playBidirectional(
                BlockPosSyncS2CPacket.TYPE,
                BlockPosSyncS2CPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        BlockPosPayloadHandler::handle,
                        BlockPosPayloadHandler::handle
                )
        );

        registrar.playBidirectional(
                ConfigurationToolItemKeyDownSyncC2SPacket.TYPE,
                ConfigurationToolItemKeyDownSyncC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ConfigurationToolItemKeyDownHandler::handle,
                        ConfigurationToolItemKeyDownHandler::handle
                )
        );


        registrar.playBidirectional(
                TimeControllerEntityKeyDownSyncC2SPacket.TYPE,
                TimeControllerEntityKeyDownSyncC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        TimeControllerEntityKeyDownHandler::handle,
                        TimeControllerEntityKeyDownHandler::handle
                )
        );
    }
}
