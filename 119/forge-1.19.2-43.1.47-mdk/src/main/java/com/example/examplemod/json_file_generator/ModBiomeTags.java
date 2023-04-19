package com.example.examplemod.json_file_generator;

import com.example.examplemod.comp208mod;
import com.example.examplemod.util.ModTags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeTags extends TagsProvider<Biome> {
    public ModBiomeTags(DataGenerator generators, ExistingFileHelper helper){
        super(generators, BuiltinRegistries.BIOME, comp208mod.MOD_ID, helper);

    }

    @Override
    protected void addTags(){
        ForgeRegistries.BIOMES.getValues().forEach(biome -> {
            tag(ModTags.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_OVERWORLD.location()));
            tag(ModTags.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_NETHER.location()));
            tag(ModTags.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_END.location()));
            tag(ModTags.HAS_PORTAL).add(biome);
            tag(ModTags.HAS_SKY_PORTAL).add(biome);
        });
    }

    @Override
    public String getName(){
        return "Mod Biome Tags";
    }
}
