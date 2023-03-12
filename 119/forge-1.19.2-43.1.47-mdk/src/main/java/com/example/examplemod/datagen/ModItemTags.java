package com.example.examplemod.datagen;

import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper){
        super(generator, blockTags, comp208mod.MOD_ID, helper);
    }

    protected void addTags(){
        tag(Tags.Items.ORES)
                .add(ModItems.AUTO_TEST_ITEM.get());
    }

    @Override
    public String getName(){
        return "Auto Tags";
    }
}
