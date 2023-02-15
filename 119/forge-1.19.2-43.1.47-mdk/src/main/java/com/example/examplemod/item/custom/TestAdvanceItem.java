package com.example.examplemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestAdvanceItem extends Item {
    public TestAdvanceItem(Properties pProperties) {
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


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(
                    Component.literal("Right click to let cat meow")
                            .withStyle(ChatFormatting.GOLD)
            );
        }else{
            pTooltipComponents.add(
                    Component.literal("Press SHIFT for more information")
                            .withStyle(ChatFormatting.AQUA)
            );
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void Meow(Player player){
        player.sendSystemMessage(Component.literal("Meow"));
    }

    private int getNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(114514);
    }
}
