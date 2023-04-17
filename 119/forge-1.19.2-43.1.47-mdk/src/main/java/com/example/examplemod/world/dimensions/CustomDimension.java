package com.example.examplemod.world.dimensions;

import com.example.examplemod.comp208mod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class CustomDimension {
    public static final ResourceKey<Level> CUSTOM_DIM = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(comp208mod.MOD_ID, "custom_dimension"));
    public static void register(){
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(comp208mod.MOD_ID, "custom_dimension_chunk"),
                CustomChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(comp208mod.MOD_ID, "biomes"),
                CustomBiomeProvider.CODEC);
    }
}
