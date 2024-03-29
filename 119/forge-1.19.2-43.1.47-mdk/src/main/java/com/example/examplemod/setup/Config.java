package com.example.examplemod.setup;

import com.example.examplemod.block.custom.GeneratorConfig;
import com.example.examplemod.world.feature.ModPlacedFeatures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import com.example.examplemod.block.custom.PowerplantConfig;
public class Config {
    public static void register(){
        registerServerConfigs();
        registerCommonConfigs();
        registerClientConfigs();
    }

    private static void registerServerConfigs(){
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        PowerplantConfig.registerServerConfig(SERVER_BUILDER);
        GeneratorConfig.registerServerConfig(SERVER_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    private static void registerCommonConfigs(){
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
        ModPlacedFeatures.registerCommonConfig(COMMON_BUILDER);
    }

    private static void registerClientConfigs(){
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        PowerplantConfig.registerClientConfig(CLIENT_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }
}
