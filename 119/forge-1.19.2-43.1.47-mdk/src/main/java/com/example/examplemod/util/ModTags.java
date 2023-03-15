package com.example.examplemod.util;

import com.example.examplemod.comp208mod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static final TagKey<Block> SPODUMENE_ORE_TIER = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "spodumene_ore"));
    public static final TagKey<Item> SPODUMENE_ORE_ITEM_TIER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "spodumene_ore"));
    public static final TagKey<Block> STARSTONE_ORE_TIER = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "starstone_ore"));
    public static final TagKey<Item> STARONE_ORE_ITEM_TIER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "starstone_item"));

}