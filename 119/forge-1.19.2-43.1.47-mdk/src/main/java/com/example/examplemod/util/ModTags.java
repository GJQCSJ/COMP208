package com.example.examplemod.util;

import com.example.examplemod.comp208mod;
import com.example.examplemod.world.Structure_Biomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static final TagKey<Block> SPODUMENE_ORE_TIER = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "spodumene_ore"));
    public static final TagKey<Item> SPODUMENE_ORE_ITEM_TIER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "spodumene_ore"));
    public static final TagKey<Block> STARSTONE_ORE_TIER = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "starstone_ore"));
    public static final TagKey<Item> STARONE_ORE_ITEM_TIER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "starstone_item"));
    public static final TagKey<Item> GENERATOR_GENERATED = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "generator_generated"));
    public static final TagKey<Biome> HAS_ORE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "has_ore"));
    public static final TagKey<Biome> HAS_PORTAL = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "has_structure/portal"));
    public static final TagKey<Biome> HAS_SKY_PORTAL = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(comp208mod.MOD_ID, "has_structure/sky_portal"));
    public static final TagKey<StructureSet> CUSTOM_DIMENSION_STRUCTURES = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, Structure_Biomes.CUSTOM_DIMENSION_SET);
    public static final TagKey<StructureSet> CUSTOM_DIMENSION_SKY_STRUCTURES = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, Structure_Biomes.CUSTOM_DIMENSION_SKY_SET);

}