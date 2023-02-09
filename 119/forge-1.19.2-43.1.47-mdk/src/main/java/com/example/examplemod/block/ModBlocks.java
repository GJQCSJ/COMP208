package com.example.examplemod.block;

import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS1 =
            DeferredRegister.create(ForgeRegistries.BLOCKS, comp208mod.MOD_ID);

    public static final RegistryObject<Block> TEMPLATE_BLOCK =registerBlock("test_block_template",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(1f)),
            ModCreativeModeTab.Test_Demo);
                /*Create and register a new block, properties inside brackets:
            the name of it,
             supplier and type or
              in game properties (may in hexadecimal)
              of block*/

    public static final RegistryObject<Block> TEST_BLOCK1 = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.SAND).
                    jumpFactor(10).strength(6f, 9)
                    .requiresCorrectToolForDrops()),
            ModCreativeModeTab.Test_Demo);


            public static final RegistryObject<Block> TEST_BLOCK2 =registerBlock("test_block_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6f).requiresCorrectToolForDrops(),
                            UniformInt.of(5, 9)
                            /*UniformInt.of will let block drop experience when being excavated,
                            ,two values means the range of experience it would drop*/
                    ),
                    ModCreativeModeTab.Test_Demo);


    private static <T extends Block>RegistryObject<T> registerBlock(
            String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS1.register(name, block);
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
        BLOCKS1.register(eventBus);
    }
}
