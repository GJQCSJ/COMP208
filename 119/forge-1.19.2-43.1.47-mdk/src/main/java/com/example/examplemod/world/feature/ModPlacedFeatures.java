package com.example.examplemod.world.feature;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, comp208mod.MOD_ID);

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
    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
