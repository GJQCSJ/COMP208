package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public  ModLanguageProvider(DataGenerator generator, String locale){
        super(generator, comp208mod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations(){
//        add("itemGroup." + TAB_NAME, "comp208");
        add(ModBlocks.AUTO_TEST_BLOCK.get(), "Auto json test");
        add(ModBlocks.CUSTOM_LAMP.get(), "Custom Lamp Block");
        add(ModBlocks.BERRY_BLOCK.get(), "Custom Berry Crops");
        add(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get(), "Deepslate Spodumene Ore");
        add(ModBlocks.TEMPLATE_BLOCK.get(), "Template Block");
        add(ModBlocks.JUMP_BLOCK.get(), "Jump Block");
        add(ModBlocks.SPODUMENE_BLOCK.get(), "Block of Spodumene");
        add(ModBlocks.SPODUMENE_ORE.get(), "Spodumene Ore");
        add(ModItems.SPODUMENE_IGNOT.get(), "Spodumene ignot");
        add(ModItems.RAW_SPODUMENE.get(), "Raw Spodumene");
        add(ModItems.TEST_ADV.get(), "Advanced item1");
        add(ModItems.TEST_SWORD.get(), "Sword1");
        add(ModItems.TEST_PICKAXE.get(), "Pickaxe1");
        add(ModItems.TEST_AXE.get(), "Axe1");
        add(ModItems.TEST_HOE.get(), "Hoe1");
        add(ModItems.TEST_SHOVEL.get(), "Shovel1");
        add(ModItems.TEST_BOW.get(), "Test Bow");
        add(ModItems.GREAT_SWORD.get(), "Great Sword");
        add(ModItems.TEST_FOOD1.get(), "Food1");
        add(ModItems.CUSTOM_BERRY_SEEDS.get(), "Custom Berry Seed");
        add(ModItems.CUSTOM_BERRY.get(), "Custom Berry");
        add(ModItems.TEST_ARMOR_HELMET.get(), "Helmet1");
        add(ModItems.TEST_ARMOR_CHEST_PLATE.get(), "Chest plate1");
        add(ModItems.TEST_ARMOR_LEGGINGS.get(), "Leggings1");
        add(ModItems.TEST_ARMOR_BOOTS.get(), "Boots1");
        add(ModItems.MULTI_TOOL_SPODUMENE.get(), "Spodumene Multi-tool");
        add(ModItems.STARSTONE_RAW.get(), "Starstone Raw");
        add(ModBlocks.BLUE_MAPLE_LOG.get(), "Blue Maple Log");
//        add(ModItems.AUTO_TEST_ITEM.get(), "Auto json test");
    }
}
