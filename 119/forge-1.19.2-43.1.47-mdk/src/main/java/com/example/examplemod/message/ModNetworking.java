package com.example.examplemod.message;

import com.example.examplemod.comp208mod;
import com.example.examplemod.message.packet.BasicPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {
    private static SimpleChannel INSTANCE;

    // This means all registered packets will have an ID
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named
                        (new ResourceLocation(comp208mod.MOD_ID, "messages"))
                .networkProtocolVersion( () -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        // What these "::" means?
        net.messageBuilder(BasicPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BasicPacket::new)
                .encoder(BasicPacket::toBytes)
                .consumerMainThread(BasicPacket::handle)
                .add();
    }

    public static <MSG> void sentToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void senToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with( () -> player), message);
    }
}
