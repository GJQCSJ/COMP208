package com.example.examplemod.world.feature;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.world.dimensions.CustomDimension;
import com.example.examplemod.world.ores.DimensionBiomeFilter;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModPlacedFeatures {
    @NotNull
    public static PlacedFeature createCustomDimensionOreGen(){
        OreConfiguration config = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.STARSTONE_ORE.get().defaultBlockState(), 25);
        return createPlacedFeature(new ConfiguredFeature<>(Feature.ORE, config),
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(CustomDimension.CUSTOM_DIM)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
    }


    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, comp208mod.MOD_ID);

    public static final RegistryObject<PlacedFeature> ORE_CUSTOM = PLACED_FEATURES.register("custom_starstone_ore", ModPlacedFeatures::createCustomDimensionOreGen);

    public static final RegistryObject<PlacedFeature> TEST_ORE_PLACED = PLACED_FEATURES.register(
            "test_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TEST_BLOCK_ORE.getHolder().get(),
                    commonOrePlacement(
                            7, /* Veins per chunk */
                            HeightRangePlacement.triangle(
                                    VerticalAnchor.absolute(-100),
                                    VerticalAnchor.absolute(40)
                            )
                    )
            )
    );

    public static final RegistryObject<PlacedFeature> END_TEST_ORE_PLACED = PLACED_FEATURES.register(
            "end_test_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TEST_BLOCK_ORE.getHolder().get(),
                    commonOrePlacement(
                            7, /* Veins per chunk */
                            HeightRangePlacement.triangle(
                                    VerticalAnchor.absolute(-100),
                                    VerticalAnchor.absolute(40)
                            )
                    )
            )
    );


    public static final RegistryObject<PlacedFeature> BLUE_MAPLE_CHECKED = PLACED_FEATURES.register("blue_maple_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.BLUE_MAPLE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.BLUE_MAPLE_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> BLUE_MAPLE_PLACED = PLACED_FEATURES.register("blue_maple_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.BLUE_MAPLE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3,0.1f,2))));






    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier placementModifiers) {
        return List.of(p_195347_, InSquarePlacement.spread(), placementModifiers, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    private static <C extends FeatureConfiguration, F extends  Feature<C>> PlacedFeature createPlacedFeature(ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers){
        return new PlacedFeature(Holder.hackyErase(Holder.direct(feature)), List.copyOf(List.of(placementModifiers)));
    }
    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
