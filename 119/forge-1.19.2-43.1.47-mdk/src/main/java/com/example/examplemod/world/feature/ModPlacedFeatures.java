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
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModPlacedFeatures {
    public static ForgeConfigSpec.IntValue CUSTOM_SPO_SIZE;
    public static ForgeConfigSpec.IntValue CUSTOM_SPO_AMO;
    public static ForgeConfigSpec.IntValue CUSTOM_STAR_SIZE;
    public static ForgeConfigSpec.IntValue CUSTOM_STAR_AMO;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER){
            COMMON_BUILDER.comment("Ore generation settings").push("ores");

    CUSTOM_SPO_SIZE = COMMON_BUILDER
            .defineInRange("custom_size_spo", 4, 2, Integer.MAX_VALUE);
    CUSTOM_SPO_AMO = COMMON_BUILDER
            .defineInRange("custom_amo_spo", 5, 1, Integer.MAX_VALUE);
    CUSTOM_STAR_SIZE = COMMON_BUILDER
            .defineInRange("custom_size_star", 3, 1, Integer.MAX_VALUE);
    CUSTOM_STAR_AMO = COMMON_BUILDER
            .defineInRange("custom_amo_star", 3, 1, Integer.MAX_VALUE);
    COMMON_BUILDER.pop();
    }

    @NotNull
    public static PlacedFeature createCustomDimensionOreGenStar(){
        OreConfiguration config = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.STARSTONE_ORE.get().defaultBlockState(), 25);
        return createPlacedFeature(new ConfiguredFeature<>(Feature.ORE, config),
                CountPlacement.of(16),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(CustomDimension.CUSTOM_DIM)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
    }

    @NotNull
    public static PlacedFeature createCustomDimensionOreGenSpodumene(){
        OreConfiguration config = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPODUMENE_ORE.get().defaultBlockState(), 25);
        return createPlacedFeature(new ConfiguredFeature<>(Feature.ORE, config),
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(CustomDimension.CUSTOM_DIM)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(190)));
    }



    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, comp208mod.MOD_ID);

    public static final RegistryObject<PlacedFeature> ORE_CUSTOM_STAR = PLACED_FEATURES.register("custom_starstone_ore", ModPlacedFeatures::createCustomDimensionOreGenStar);
    public static final RegistryObject<PlacedFeature> ORE_CUSTOM_SPO = PLACED_FEATURES.register("custom_spodumene_ore", ModPlacedFeatures::createCustomDimensionOreGenSpodumene);

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
