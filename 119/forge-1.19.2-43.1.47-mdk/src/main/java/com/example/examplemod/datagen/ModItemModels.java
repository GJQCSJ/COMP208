package com.example.examplemod.datagen;

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
//        withExistingParent(ModItems.SPODUMENE.getId().getPath(), modLoc("item/spodumene"));
    }
}
