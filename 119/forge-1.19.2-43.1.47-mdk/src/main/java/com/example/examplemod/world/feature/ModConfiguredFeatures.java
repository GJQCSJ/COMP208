package com.example.examplemod.world.feature;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import com.google.common.base.Supplier;
import net.minecraftforge.registries.RegistryObject;

import java.lang.module.Configuration;
import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, comp208mod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TEST_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                                    ModBlocks.TEST_BLOCK_ORE.get().defaultBlockState()),
                            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                                    ModBlocks.TEST_BLOCK_ORE.get().defaultBlockState())
                    )
            );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_TEST_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                                    ModBlocks.TEST_BLOCK_ORE.get().defaultBlockState()
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
    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }
}
