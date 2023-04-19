package com.example.examplemod.world.feature;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import com.google.common.base.Supplier;
import net.minecraftforge.registries.RegistryObject;

import java.io.ObjectInputFilter;
import java.lang.module.Configuration;
import java.util.List;

public class    ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, comp208mod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TEST_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                                    ModBlocks.SPODUMENE_ORE.get().defaultBlockState()),
                            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                                    ModBlocks.SPODUMENE_ORE.get().defaultBlockState())
                    )
            );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_TEST_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                                    ModBlocks.SPODUMENE_ORE.get().defaultBlockState()
                            )
                    )
            );



    public static final RegistryObject<ConfiguredFeature<?, ?>> TEST_BLOCK_ORE =
            CONFIGURED_FEATURES.register("test_block_ore",
                    () ->new ConfiguredFeature<>
                            (
                                    Feature.ORE,
                                    new OreConfiguration(OVERWORLD_TEST_ORES.get(),
                                            7)
                            )
            );

    public static final RegistryObject<ConfiguredFeature<?, ?>> END_TEST_BLOCK_ORE =
            CONFIGURED_FEATURES.register("end_test_block_ore",
                    () ->new ConfiguredFeature<>
                            (
                                    Feature.ORE,
                                    new OreConfiguration(OVERWORLD_TEST_ORES.get(),
                                            9)
                            )
            );



    public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_MAPLE =
            CONFIGURED_FEATURES.register("blue_maple", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.BLUE_MAPLE_LOG.get()),
                            new StraightTrunkPlacer(5,6,3),
                            BlockStateProvider.simple(ModBlocks.BLUE_MAPLE_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),4),
                            new TwoLayersFeatureSize(1,0,2)).build()));


    public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_MAPLE_SPAWN =
            CONFIGURED_FEATURES.register("blue_maple_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.BLUE_MAPLE_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.BLUE_MAPLE_CHECKED.getHolder().get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PURPLEGRASS = CONFIGURED_FEATURES.register("purplegrass",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLEGRASS.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> JASMINE = CONFIGURED_FEATURES.register("jasmin",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.JASMINE.get()))))));


    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }
}
