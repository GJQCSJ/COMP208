package com.example.examplemod.item;

import com.example.examplemod.comp208mod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, comp208mod.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));
    public static final RegistryObject<Item> TEST2 = ITEMS.register("test_item2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
