package com.example.examplemod.block.custom;

import net.minecraftforge.common.ForgeConfigSpec;

public class PowerplantConfig {
    public static ForgeConfigSpec.IntValue MANA_CAP;
    public static ForgeConfigSpec.IntValue MANA_GEN;
    public static ForgeConfigSpec.IntValue MANA_SEND;
    public static ForgeConfigSpec.DoubleValue RENDER_SCALE;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER){
        SERVER_BUILDER.comment("settings fore mana_ext").push("mana_extractor");

        MANA_CAP = SERVER_BUILDER
                .comment("how much capacity of this")
                .defineInRange("capacity", 50000, 1, Integer.MAX_VALUE);

        MANA_GEN = SERVER_BUILDER
                .comment("how much generated")
                .defineInRange("generate", 60, 1, Integer.MAX_VALUE);

        MANA_SEND = SERVER_BUILDER
                .comment("how much send per tick")
                .defineInRange("send", 200, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER){
        CLIENT_BUILDER.comment("Client settings for extractor").push("mana_extractor");

        RENDER_SCALE = CLIENT_BUILDER
                .comment("scale of render")
                .defineInRange("scale", .3, 0.000001, 1000.0);

        CLIENT_BUILDER.pop();
    }
}
