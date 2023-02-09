package com.example.examplemod.block;

import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

    public static final RegistryObject<Block> TEST_BLOCK1 = registerBlock("test_block1",
            () -> new Block(BlockBehaviour.Properties.of(Material.SAND).
                    jumpFactor(10).strength(1f, 9)
                    .requiresCorrectToolForDrops()),
            ModCreativeModeTab.Test_Demo);
            /*Create and register a new block, properties inside brackets:
            the name of it,
             supplier and type or
              in game properties (may in hexadecimal)
              of block*/

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
