package com.example.examplemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Test_AdvanceItem extends Item {
    public Test_AdvanceItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND){
            //OutputRandom(pPlayer);
            Meow(pPlayer);
            pPlayer.getCooldowns().addCooldown(this, 20);
        }

            return super.use(pLevel, pPlayer, pUsedHand);
    }
//    private void OutputRandom(Player player){
//        player.sendSystemMessage(Component.literal("Your number is:" + getNumber()));
//    }
    private void Meow(Player player){
        player.sendSystemMessage(Component.literal("Meow"));
    }

    private int getNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(114514);
    }
}
