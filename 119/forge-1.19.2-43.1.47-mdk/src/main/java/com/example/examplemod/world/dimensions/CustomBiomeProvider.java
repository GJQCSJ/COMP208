package com.example.examplemod.world.dimensions;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class    CustomBiomeProvider extends BiomeSource {
    public static final Codec<CustomBiomeProvider> CODEC = RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY)
            .xmap(CustomBiomeProvider::new, CustomBiomeProvider::getBiomeRegistry).codec();

    private final Holder<Biome> biome;
    private final Registry<Biome> biomeRegistry;
    private static final List<ResourceKey<Biome>> SPAWN = Collections.singletonList(Biomes.FLOWER_FOREST);
    public CustomBiomeProvider(Registry<Biome> biomeRegistry){
        super(getStartBiomes(biomeRegistry));
        this.biomeRegistry = biomeRegistry;
        biome = biomeRegistry.getHolderOrThrow(Biomes.FLOWER_FOREST);
    }

    private static List<Holder<Biome>> getStartBiomes(Registry<Biome> biomeRegistry){
        return SPAWN.stream().map(s -> biomeRegistry.getHolderOrThrow(ResourceKey.create(BuiltinRegistries.BIOME.key(), s.location()))).collect(Collectors.toList());
    }
    public Registry<Biome> getBiomeRegistry(){
        return biomeRegistry;
    }

    @Override
    protected Codec<? extends BiomeSource> codec(){
        return CODEC;
    }
    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler){
        return biome;
    }
}
