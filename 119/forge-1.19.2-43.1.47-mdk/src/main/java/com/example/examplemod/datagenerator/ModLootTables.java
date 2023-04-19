package com.example.examplemod.datagenerator;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.DataGenerator;

public class ModLootTables extends BasicLootTableProvider {
    public ModLootTables(DataGenerator dataGeneratorIn){
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables(){
        lootTables.put(ModBlocks.STARSTONE_ORE.get(), createSilkTouchTable("starstone_overworld_ore", ModBlocks.STARSTONE_ORE.get(), ModItems.STARSTONE_RAW.get(), 1, 5));
        lootTables.put(ModBlocks.DEEPSLATE_STARSTONE_ORE.get(), createSilkTouchTable("starstone_overworld_deepslate_ore", ModBlocks.DEEPSLATE_STARSTONE_ORE.get(), ModItems.STARSTONE_RAW.get(), 1, 5));
        lootTables.put(ModBlocks.MANA_EXTRACTOR_BLOCK.get(), createStandardTable("mana_extractor", ModBlocks.MANA_EXTRACTOR_BLOCK.get(), ModBlocks.MANA_EXTRACTOR_BE.get()));
    }
}


