package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper){
        super(generator, comp208mod.MOD_ID, helper);
    }

    @Override
    protected void addTags(){
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AUTO_TEST_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_BLOCK.get());
        tag(Tags.Blocks.ORES)
                .add(ModBlocks.AUTO_TEST_BLOCK.get())
                .add(ModBlocks.SPODUMENE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AUTO_TEST_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_BLOCK.get());
        tag(ModTags.SPODUMENE_ORE_TIER)
                .add(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_ORE.get())
                .add(ModBlocks.SPODUMENE_BLOCK.get());
        tag(ModTags.STARSTONE_ORE_TIER)
                .add(ModBlocks.STARSTONE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_STARSTONE_ORE.get());
    }

    @Override
    public String getName(){
        return "Auto Tags";
    }
}
