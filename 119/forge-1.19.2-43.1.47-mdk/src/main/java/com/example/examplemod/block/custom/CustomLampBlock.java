package com.example.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CustomLampBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public CustomLampBlock(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel,
                                 BlockPos pPos, Player pPlayer,
                                 InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND ){
            pLevel.setBlock(pPos, pState.cycle(LIT), 3);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }
}
