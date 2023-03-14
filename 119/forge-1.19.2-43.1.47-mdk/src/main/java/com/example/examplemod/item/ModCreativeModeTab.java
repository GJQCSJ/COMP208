package com.example.examplemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab Test_Demo = new CreativeModeTab("comp208mod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPODUMENE_IGNOT.get());
        }
    };
}
