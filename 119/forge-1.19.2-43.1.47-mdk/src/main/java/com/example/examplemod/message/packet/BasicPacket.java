package com.example.examplemod.message.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.nio.channels.NetworkChannel;
import java.util.function.Supplier;

// This is from client to server
public class BasicPacket {

    private static final String MESSAGE_DRINK_WATER = "message.comp208mod.drink_water";

    private static final String MESSAGE_NO_WATER = "message.comp208mod.no_water";
    public BasicPacket(){

    }

    public BasicPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork( () -> {
            // Here is on the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if(nearWater(player, level, 2)){
                // Drink, raise thirst level, play drinking sound, output thirst level
                player.sendSystemMessage(Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.AQUA));
                // This is original play drink sound method
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5f, level.random.nextFloat() * 0.1f + 0.9f);
            } else {
                // Notify no water around, output thirst level
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.DARK_GRAY));
            }
//            EntityType.COW.spawn
//                    (level, null, null,
//                            player.blockPosition(), MobSpawnType.COMMAND,
//                            true, false);
        });
        return true;
    }

    private boolean nearWater(ServerPlayer player, ServerLevel level, int size){
        // Size is the range
        // getBoundingBox will get streams of blockstates
        return level.getBlockStates((player.getBoundingBox().inflate(size)))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
