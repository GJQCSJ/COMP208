package com.example.examplemod.datagen;


import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(DataGenerator generatorIn, ExistingFileHelper helper){
        super(generatorIn, comp208mod.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels(){
        registerExtractor();

        simpleBlock(ModBlocks.AUTO_TEST_BLOCK.get());
        simpleBlock(ModBlocks.SPODUMENE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_SPODUMENE_ORE.get());
        simpleBlock(ModBlocks.SPODUMENE_BLOCK.get());
        simpleBlock(ModBlocks.TEMPLATE_BLOCK.get());
        simpleBlock(ModBlocks.STARSTONE_ORE.get());
        simpleBlock(ModBlocks.STARSTONE_BLOCK.get());
        simpleBlock(ModBlocks.DEEPSLATE_STARSTONE_ORE.get());
        simpleBlock(ModBlocks.BLUE_MAPLE_LEAVES.get());
//        simpleBlock(ModBlocks.BLUE_MAPLE_LOG.get());
        simpleBlock(ModBlocks.BLUE_MAPLE_PLANKS.get());
//        simpleBlock(ModBlocks.BLUE_MAPLE_SAPLING.get());
//        simpleBlock(ModBlocks.BLUE_MAPLE_WOOD.get());
    }

    private void registerExtractor(){
        BlockModelBuilder frame = models().getBuilder("block/mana_extractor/main");
        frame.parent(models().getExistingFile(mcLoc("cube")));

        floatingCube(frame, 0f, 0f, 0f, 1f, 16f, 1f);
        floatingCube(frame, 15f, 0f, 0f, 16f, 16f, 1f);
        floatingCube(frame, 0f, 0f, 15f, 1f, 16f, 16f);
        floatingCube(frame, 15f, 0f, 15f, 16f, 16f, 16f);

        floatingCube(frame, 1f, 0f, 0f, 15f, 1f, 1f);
        floatingCube(frame, 1f, 15f, 0f, 15f, 16f, 1f);
        floatingCube(frame, 1f, 0f, 15f, 15f, 1f, 16f);
        floatingCube(frame, 1f, 15f, 15f, 15f, 16f, 16f);

        floatingCube(frame, 0f, 0f, 1f, 1f, 1f, 15f);
        floatingCube(frame, 15f, 0f, 1f, 16f, 1f, 15f);
        floatingCube(frame, 0f, 15f, 1f, 1f, 16f, 15f);
        floatingCube(frame, 15f, 15f, 1f, 16f, 16f, 15f);

        floatingCube(frame, 1f, 1f, 1f, 15f, 15f, 15f);

        frame.texture("window", modLoc("block/mana_extractor_window"));
        frame.texture("particle", modLoc("block/mana_extractor_off"));

        frame.renderType("translucent");

        createExtractorModel(ModBlocks.MANA_EXTRACTOR_BLOCK.get(), frame);
    }

    private void floatingCube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz){
        builder.element()
                .from(fx, fy, fz)
                .to(tx, ty, tz)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#window"))
                .end();
    }

    private void createExtractorModel(Block block, BlockModelBuilder frame){
        BlockModelBuilder singleOff = models().getBuilder("block/mana_extractor/singleoff")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/mana_extractor_off"));
        BlockModelBuilder singleOn = models().getBuilder("block/mana_extractor/singleon")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/mana_extractor_on"));

        MultiPartBlockStateBuilder build = getMultipartBuilder(block);

        build.part().modelFile(frame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[] {
                singleOff, singleOn
        };

        for (int i = 0; i < 2; i++){
            boolean powered = i == 1;
            build.part().modelFile(models[i]).addModel().condition(BlockStateProperties.POWERED, powered);
            build.part().modelFile(models[i]).rotationX(180).addModel().condition(BlockStateProperties.POWERED, powered);
            build.part().modelFile(models[i]).rotationX(90).addModel().condition(BlockStateProperties.POWERED, powered);
            build.part().modelFile(models[i]).rotationX(270).addModel().condition(BlockStateProperties.POWERED, powered);
            build.part().modelFile(models[i]).rotationX(90).rotationY(90).addModel().condition(BlockStateProperties.POWERED, powered);
            build.part().modelFile(models[i]).rotationX(90).rotationY(270).addModel().condition(BlockStateProperties.POWERED, powered);
        }
    }

//    private void
}
