package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(DataGenerator generator, ExistingFileHelper helper){
        super(generator, comp208mod.MOD_ID, helper);
    }

    @Override
    protected void registerModels(){
        withExistingParent(ModItems.AUTO_TEST_ITEM.getId().getPath(), modLoc("block/auto_json_testing_block"));
        withExistingParent(ModBlocks.SPODUMENE_ORE.getId().getPath(), modLoc("block/spodumene_ore"));
        withExistingParent(ModBlocks.DEEPSLATE_SPODUMENE_ORE.getId().getPath(), modLoc("block/deepslate_spodumene_ore"));
        singleTexture(ModItems.RAW_SPODUMENE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/raw_spodumene"));
        singleTexture(ModItems.SPODUMENE_IGNOT.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spodumene"));
        singleTexture(ModItems.STARSTONE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/starstone"));
        singleTexture(ModItems.STARSTONE_RAW.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/starstone_raw"));
        withExistingParent(ModBlocks.DEEPSLATE_STARSTONE_ORE.getId().getPath(), modLoc("block/deepslate_starstone_ore"));
        withExistingParent(ModBlocks.STARSTONE_BLOCK.getId().getPath(), modLoc("block/starstone_block"));
        withExistingParent(ModBlocks.STARSTONE_ORE.getId().getPath(), modLoc("block/starstone_ore"));
        withExistingParent(ModBlocks.BLUE_MAPLE_LEAVES.getId().getPath(), modLoc("block/blue_maple_leaves"));
        withExistingParent(ModBlocks.BLUE_MAPLE_PLANKS.getId().getPath(), modLoc("block/blue_maple_planks"));
        withExistingParent(ModBlocks.SPODUMENE_BLOCK.getId().getPath(), modLoc("block/spodumene_block"));
//        withExistingParent(ModItems.SPODUMENE.getId().getPath(), modLoc("item/spodumene"));
    }
}
