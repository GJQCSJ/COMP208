package com.example.examplemod.world.ores;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.function.Predicate;

public class DimensionBiomeFilter extends PlacementFilter {
    private final Predicate<ResourceKey<Level>> level;
    public DimensionBiomeFilter(Predicate<ResourceKey<Level>> level){
        this.level = level;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource source, BlockPos pos){
        if (level.test(context.getLevel().getLevel().dimension())){
            PlacedFeature placedFeature = context.topFeature().orElseThrow(() -> new IllegalStateException("Tried to check an unassigned feature"));
            Holder<Biome> biome = context.getLevel().getBiome(pos);
            return biome.value().getGenerationSettings().hasFeature(placedFeature);
        } else {
            return false;
        }
    }

    @Override
    public PlacementModifierType<?> type(){
        return PlacementModifierType.BIOME_FILTER;
    }
}
