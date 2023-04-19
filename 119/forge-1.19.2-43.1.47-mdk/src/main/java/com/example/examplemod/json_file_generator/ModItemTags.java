package com.example.examplemod.json_file_generator;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper){
        super(generator, blockTags, comp208mod.MOD_ID, helper);
    }

    protected void addTags(){
        tag(Tags.Items.ORES)
                .add(ModItems.AUTO_TEST_ITEM.get())
                .add(Item.byBlock(ModBlocks.SPODUMENE_ORE.get()))
                .add(Item.byBlock(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get()))
                .add(Item.byBlock(ModBlocks.STARSTONE_ORE.get()))
                .add(Item.byBlock(ModBlocks.DEEPSLATE_STARSTONE_ORE.get()));
        tag(ModTags.SPODUMENE_ORE_ITEM_TIER)
                .add(Item.byBlock(ModBlocks.SPODUMENE_ORE.get()))
                .add(Item.byBlock(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get()));
        tag(ModTags.STARONE_ORE_ITEM_TIER)
                .add(Item.byBlock(ModBlocks.DEEPSLATE_STARSTONE_ORE.get()))
                .add(Item.byBlock(ModBlocks.STARSTONE_ORE.get()));
        tag(ModTags.GENERATOR_GENERATED)
                .add(ModItems.GENERATED_ITEM.get());
    }

    @Override
    public String getName(){
        return "Auto Tags";
    }
}
