package com.example.examplemod.world.ores;

import com.example.examplemod.comp208mod;
import com.example.examplemod.world.Structure_Biomes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record CustomBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {
    public static final String CUSTOM_BIOME_MODIFIER_NAME = "custom_biome_modifier";
    public static final ResourceLocation CUSTOM_BIOME_MODIFIER = new ResourceLocation(comp208mod.MOD_ID, CUSTOM_BIOME_MODIFIER_NAME);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder){
        if (phase == Phase.ADD && this.biomes.contains(biome)){
            builder.getSpecialEffects().ambientParticle(new AmbientParticleSettings(new SculkChargeParticleOptions(1.0f), 1.0f));
            builder.getGenerationSettings().getFeatures(GenerationStep.Decoration.LAKES);
            builder.getGenerationSettings().getFeatures(GenerationStep.Decoration.STRONGHOLDS);
            builder.getMobSpawnSettings();
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec(){
        return Structure_Biomes.CUSTOM_BIOME_MODIFIER.get();
    }

    public static Codec<CustomBiomeModifier> makeCodec(){
        return RecordCodecBuilder.create(
                builder -> builder.group(
                        Biome.LIST_CODEC.fieldOf("biomes")
                                .forGetter(CustomBiomeModifier::biomes)
                ).apply(builder, CustomBiomeModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name){
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name));
        } catch (Exception e){
            return DataResult.error("'"+ name + "'" + " is not a decoration stage");
        }
    }
}
