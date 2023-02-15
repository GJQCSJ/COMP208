package com.example.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JumpBlock extends Block {
    public JumpBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState,
                                 Level pLevel,
                                 BlockPos pPos,
                                 Player pPlayer,
                                 InteractionHand pHand,
                                 BlockHitResult pHit) {
        if (pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND)
        {
        pPlayer.sendSystemMessage(Component.literal("Clicked"));
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    /* Use is what happens if the player clicks the block */
    /* If statement checks this only activated when level is client and player uses main hand */
    /* Another approach to stop use method calling multiple times at once:
    * return InteractionResult.SUCCESS
    * to cancel passing the event
    * */

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 120));
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
