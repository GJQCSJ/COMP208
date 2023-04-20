package com.example.examplemod.item.custom;

import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModTiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

import static com.example.examplemod.comp208mod.MOD_ID;
public class MultiPurposeToolItem extends AxeItem{
    private static final TagKey<Block> MINEABLE_TAG = BlockTags.create(new ResourceLocation(MOD_ID, "mineable/pickaxe"));
    private static final Tier THIS_TIER = ModTiers.TEST_TIER_TOOLS;
    public MultiPurposeToolItem(Tier pTier,
                                float pAttackDamageModifier,
                                float pAttackSpeedModifier,
                                Properties pProperties){
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state)
    {
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) return speed;
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) return speed;
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE))
            return TierSortingRegistry.isCorrectTierForDrops(THIS_TIER, state);
        if (state.is(BlockTags.MINEABLE_WITH_AXE))
            return TierSortingRegistry.isCorrectTierForDrops(THIS_TIER, state);
        if (state.is(MINEABLE_TAG))
            return TierSortingRegistry.isCorrectTierForDrops(THIS_TIER, state);
        return false;
    }
}
