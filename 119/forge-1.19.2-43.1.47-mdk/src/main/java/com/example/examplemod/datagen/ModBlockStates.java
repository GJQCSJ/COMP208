package com.example.examplemod.datagen;


import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(DataGenerator generatorIn, ExistingFileHelper helper){
        super(generatorIn, comp208mod.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels(){

        simpleBlock(ModBlocks.AUTO_TEST_BLOCK.get());
    }

//    private void
}
