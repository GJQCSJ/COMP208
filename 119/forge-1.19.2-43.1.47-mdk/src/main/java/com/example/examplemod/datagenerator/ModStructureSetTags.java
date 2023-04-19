package com.example.examplemod.datagenerator;

import com.example.examplemod.comp208mod;
import com.example.examplemod.util.ModTags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModStructureSetTags extends TagsProvider<StructureSet> {
    public ModStructureSetTags(DataGenerator generator, ExistingFileHelper helper){
        super(generator, BuiltinRegistries.STRUCTURE_SETS, comp208mod.MOD_ID, helper);
    }

    @Override
    protected void addTags(){
        tag(ModTags.CUSTOM_DIMENSION_STRUCTURES)
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "portal")))
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "house_1")))
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "sky_portal")));
        tag(ModTags.CUSTOM_DIMENSION_SKY_STRUCTURES)
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "test")))
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "house_1")))
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(comp208mod.MOD_ID, "sky_portal")));
    }

    @Override
    public String getName(){
        return "Mod Structure Tags";
    }
}
