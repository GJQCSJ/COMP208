package com.example.examplemod.block;

import com.example.examplemod.block.custom.BerryCropBlock;
import com.example.examplemod.block.custom.CustomLampBlock;
import com.example.examplemod.block.custom.JumpBlock;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    /*
    * Block inside the world -> blockstate
    * Block itself -> block class -> a type of the block */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, comp208mod.MOD_ID);

    public static final RegistryObject<Block> TEMPLATE_BLOCK =registerBlock("test_block_template",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f)),
            ModCreativeModeTab.Test_Demo);
                /*Create and register a new block, properties inside brackets:
            the name of it,
             supplier and type or
              in game properties (may in hexadecimal)
              of block*/

    public static final RegistryObject<Block> TEST_BLOCK1 = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).
                    jumpFactor(10).strength(6f, 9)
                    .requiresCorrectToolForDrops()),
            ModCreativeModeTab.Test_Demo);


    public static final RegistryObject<Block> TEST_BLOCK2 =registerBlock("test_block_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 9)
                    /*UniformInt.of will let block drop experience when being excavated,
                           ,two values means the range of experience it would drop*/
                    ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> JUMP_BLOCK = registerBlock("jump_block",
            () -> new JumpBlock(BlockBehaviour.Properties
                    .of(Material.LEAVES)
                    .strength(6F)
            ),
                    ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> CUSTOM_LAMP = registerBlock("block_custom_lamp",
            () -> new CustomLampBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(CustomLampBlock.LIT) ? 15 : 0)
                    .strength(6F)
                    /* 15 means light level when activated, 0 means when deactivated */
            ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BERRY_BLOCK = BLOCKS.register("custom_berry_crop",
            () -> new BerryCropBlock(BlockBehaviour.Properties
                    .copy(Blocks.WHEAT)
            )
    );


    private static <T extends Block>RegistryObject<T> registerBlock(
            String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
        /*register blocks*/
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(
            String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(
                name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


    public static void register(IEventBus eventBus){

        BLOCKS.register(eventBus);
    }

//    private static <T extends Block> RegistryObject<T> AdvancedRegister(String name, Supplier<T> supplier,
//                                                                      Item.Properties properties){
//        RegistryObject<T> block = BLOCKS.register(name, supplier);
//        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
//        return block;
//    }
}
