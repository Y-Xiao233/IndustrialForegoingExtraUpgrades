package net.yxiao233.ifeu.common.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.networking.packet.*;

public class ModNetWorking {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id(){
        return packetId ++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"networks"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        net.messageBuilder(BooleanSyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BooleanSyncS2CPacket::new)
                .encoder(BooleanSyncS2CPacket::toBytes)
                .consumerMainThread(BooleanSyncS2CPacket::handle)
                .add();

        net.messageBuilder(BlockPosSyncS2CPacket.class,id(),NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BlockPosSyncS2CPacket::new)
                .encoder(BlockPosSyncS2CPacket::toBytes)
                .consumerMainThread(BlockPosSyncS2CPacket::handle)
                .add();

        net.messageBuilder(DirectionSyncS2CPacket.class,id(),NetworkDirection.PLAY_TO_CLIENT)
                .decoder(DirectionSyncS2CPacket::new)
                .encoder(DirectionSyncS2CPacket::toBytes)
                .consumerMainThread(DirectionSyncS2CPacket::handle)
                .add();

        net.messageBuilder(ConfigurationToolItemKeyDownSyncC2SPacket.class,id(),NetworkDirection.PLAY_TO_SERVER)
                .decoder(ConfigurationToolItemKeyDownSyncC2SPacket::new)
                .encoder(ConfigurationToolItemKeyDownSyncC2SPacket::toBytes)
                .consumerMainThread(ConfigurationToolItemKeyDownSyncC2SPacket::handle)
                .add();

        net.messageBuilder(TimeControllerEntityKeyDownSyncC2SPacket.class,id(),NetworkDirection.PLAY_TO_SERVER)
                .decoder(TimeControllerEntityKeyDownSyncC2SPacket::new)
                .encoder(TimeControllerEntityKeyDownSyncC2SPacket::toBytes)
                .consumerMainThread(TimeControllerEntityKeyDownSyncC2SPacket::handle)
                .add();

        net.messageBuilder(IntValueSyncC2SPacket.class,id(),NetworkDirection.PLAY_TO_CLIENT)
                .decoder(IntValueSyncC2SPacket::new)
                .encoder(IntValueSyncC2SPacket::toBytes)
                .consumerMainThread(IntValueSyncC2SPacket::handle)
                .add();
    }

    @OnlyIn(Dist.CLIENT)
    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),message);
    }

    public static <MSG> void sendToClient(MSG message){
        INSTANCE.send(PacketDistributor.ALL.noArg(),message);
    }
}
