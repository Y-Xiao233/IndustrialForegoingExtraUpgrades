package net.yxiao233.ifeu.common.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.networking.packet.GameRuleSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;

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

        net.messageBuilder(GameRuleSyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(GameRuleSyncS2CPacket::new)
                .encoder(GameRuleSyncS2CPacket::toBytes)
                .consumerMainThread(GameRuleSyncS2CPacket::handle)
                .add();

        net.messageBuilder(BooleanSyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BooleanSyncS2CPacket::new)
                .encoder(BooleanSyncS2CPacket::toBytes)
                .consumerMainThread(BooleanSyncS2CPacket::handle)
                .add();
    }

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
